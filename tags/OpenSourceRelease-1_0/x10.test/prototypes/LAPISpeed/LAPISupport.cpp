/***
Trans.[java|cpp] are SPMD programs that use LAPI to transpose large
arrays of doubles.  The idea is to determine if a serialization-less
JVM->JVM copy can be effected quickly enough to compete with C (or C++)

LAPISupport.cpp is the LAPI support library and is used by both the
Java and C++ versions.  For the Java version it requires Trans.h and
VMInfo.h which are generated by javah.

xlC -g -o Trans -O Trans.cpp LAPISupport.cpp -bmaxdata:0x80000000 -llapi_r
javac Trans.java
javah Trans
javah VMInfo
xlC_r -DFOR_JAVA -g -qnolm -qnoeh -qnotempinc -bM:SHR -bnoentry -bexpall -I${classesdir} -I$JAVA_HOME/bin/include -I$JAVA_HOME/include -llapi_r -o libLAPISupport.a LAPISupport.cpp
***/

#include <stdlib.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <strings.h>
#include <unistd.h>
#include <limits.h>
#include <assert.h>
#include <pthread.h>

#ifdef FOR_JAVA
#include "VMInfo.h"
#include "Trans.h"
#else
typedef void JNIEnv;
typedef char jdoubleArray;
typedef char jbooleanArray;
typedef char jboolean;
typedef char jdouble;
#endif
extern "C" {
#include <lapi.h>
}

#ifdef FOR_JAVA
#define THROWERR if (e) e->ThrowNew(e->FindClass("java/lang/Error"), msg_buff);

#else
#define THROWERR
#endif
#define CheckLapiProblem(e, lapi_rc) \
    if (lapi_rc != LAPI_SUCCESS) { \
        char  msg_buff[LAPI_MAX_ERR_STRING+128]; \
        sprintf(msg_buff,"Line %d: ", __LINE__); \
        LAPI_Msg_string(lapi_rc, msg_buff + strlen(msg_buff)); \
        THROWERR \
    }

#ifdef FOR_JAVA
#define CheckJNIException(e) \
    if (e->ExceptionOccurred()) { \
        fprintf(stderr, "A JNI exception occurred above line %d\n", __LINE__); \
        e->ExceptionDescribe(); \
    }
#define IfCheckJNIException(e) CheckJNIException(e)

static JavaVM *myJavaVM;
static jint myJNIVersion;
static jclass myVMInfoClass;
static unsigned long numCopiedSentArrays = 0;
static unsigned long numCopiedReceivedArrays = 0;
#endif
 
static unsigned int mp_procs;
static unsigned int mp_child;
static lapi_handle_t lapi_handle;
static unsigned int maxLAPIUHdrSz;

enum handlerTypes {
    IncomingBlockHandler,
    numHandlerTypes
};

static void *this_vms_IncomingBlock_hdr_handler(lapi_handle_t *hndl,
                                                void *uhdr,
                                                unsigned int *uhdr_len,
                                                lapi_return_info_t *ret_info,
                                                compl_hndlr_t **comp_h,
                                                void **info);
static struct {
    lapi_long_t *handlers;
    void *(*this_vms_handler)(lapi_handle_t *hndl,
                              void *uhdr,
                              unsigned int *uhdr_len,
                              lapi_return_info_t *ret_info,
                              compl_hndlr_t **comp_h,
                              void **info);
} hdr_handlers[numHandlerTypes] = {
    {NULL, this_vms_IncomingBlock_hdr_handler}
};
#define HdrHandler(X) hdr_handlers[(X)].handlers[xfer_struct.Am.tgt];

//////////////////////////////////////////////////////////////////////////////
// Transfer array block to other LAPI task
//////////////////////////////////////////////////////////////////////////////

static double *placeDataHere = NULL;
static volatile bool *updateThisWhenComplete = NULL;
static unsigned int sizeOfBuffer;

static struct whereToDirectIncomingBlock_s {
    double *placeDataHere;
    volatile bool *updateThisWhenComplete;
    jdoubleArray targetJavaArray;
    jbooleanArray updateFlags;
    unsigned int indexIntoUpdateFlags;
} *bufferAddresses = NULL;

typedef struct incomingBlockMsgHdr_s {
    unsigned int        globalBlockNum;
    jboolean            is_copy;
    jdoubleArray        targetJavaArray;
    jdouble *           addressOfElements;
    JNIEnv *            e;
} incomingBlockMsgHdr_t;
static void this_vms_IncomingBlock_compl_handler(lapi_handle_t *hndl, void *args) {
    #ifdef FOR_JAVA
    incomingBlockMsgHdr_t *x = (incomingBlockMsgHdr_t *) args;
//    JNIEnv *e;
//    myJavaVM->GetEnv((void **) &e, myJNIVersion);
//    if (e == NULL) {
//        myJavaVM->AttachCurrentThreadAsDaemon((void **) &e, NULL);
//    }
//    e->PushLocalFrame(32);
    if (x->is_copy == JNI_TRUE) {
//        e->ReleaseDoubleArrayElements(x->targetJavaArray, x->addressOfElements, 0);
        ++numCopiedReceivedArrays;
        x->e->ReleasePrimitiveArrayCritical(x->targetJavaArray, x->addressOfElements, 0);
    }
    jboolean xtrue[] = { JNI_TRUE };
    x->e->SetBooleanArrayRegion(bufferAddresses[x->globalBlockNum].updateFlags,
                                bufferAddresses[x->globalBlockNum].indexIntoUpdateFlags,
                                1, xtrue);
    x->e->PopLocalFrame(NULL);
    #else
    *bufferAddresses[((incomingBlockMsgHdr_t *) args)->globalBlockNum].updateThisWhenComplete = true;
    #endif
    free(args);
}

static void *this_vms_IncomingBlock_hdr_handler(lapi_handle_t *hndl,
                                                void *uhdr,
                                                unsigned int *uhdr_len,
                                                lapi_return_info_t *ret_info,
                                                compl_hndlr_t **comp_h,
                                                void **info) {
    // ret_info->ret_flags = LAPI_LOCAL_STATE;  // ?? could be big blocks
    *comp_h = this_vms_IncomingBlock_compl_handler;
    incomingBlockMsgHdr_t *x = (incomingBlockMsgHdr_t *) uhdr;
    *info = malloc(sizeof(*x));
    ((incomingBlockMsgHdr_t *)(*info))->globalBlockNum = x->globalBlockNum;
    x = (incomingBlockMsgHdr_t *) *info;
    #ifdef FOR_JAVA
    JNIEnv *e;
    myJavaVM->GetEnv((void **) &e, myJNIVersion);
    if (e == NULL) {
        myJavaVM->AttachCurrentThreadAsDaemon((void **) &e, NULL);
    }
    e->PushLocalFrame(32);
    x->targetJavaArray = bufferAddresses[x->globalBlockNum].targetJavaArray;
//    x->addressOfElements = e->GetDoubleArrayElements(x->targetJavaArray, &x->is_copy);
    x->addressOfElements = (jdouble *) e->GetPrimitiveArrayCritical(x->targetJavaArray, &x->is_copy);
    ret_info->ret_flags = LAPI_LOCAL_STATE;  // MUST RUN ON THIS THREAD
    x->e = e;
//    e->PopLocalFrame(NULL);
    return x->addressOfElements;
    #else
    return bufferAddresses[x->globalBlockNum].placeDataHere;
    #endif
}

static void mySendCompletionHandler(lapi_handle_t *hndl, void *completion_param,
                                    lapi_sh_info_t *info) {
    volatile int *flag = (int *) completion_param;
    *flag = ~(int) 0;
}

void sendMyArrayBlock(unsigned int tgt, unsigned int globalBlockNum, double *data, unsigned int l, JNIEnv* e) {
    volatile int flag = 0;
    incomingBlockMsgHdr_t x;
    x.globalBlockNum = globalBlockNum;
    int lapi_rc;
    lapi_xfer_t xfer_struct;

    xfer_struct.Am.Xfer_type = LAPI_AM_XFER;
    xfer_struct.Am.flags = 0;
    xfer_struct.Am.tgt = tgt;
    xfer_struct.Am.hdr_hdl = HdrHandler(IncomingBlockHandler);
    xfer_struct.Am.uhdr_len = sizeof(x);
    xfer_struct.Am.uhdr = &x;
    xfer_struct.Am.udata = data;
    xfer_struct.Am.udata_len = l * sizeof(double);
    xfer_struct.Am.shdlr = mySendCompletionHandler;
    xfer_struct.Am.sinfo = (void *) &flag;
    xfer_struct.Am.tgt_cntr = 0;
    xfer_struct.Am.org_cntr = NULL;
    xfer_struct.Am.cmpl_cntr = NULL;
    lapi_rc = LAPI_Xfer(lapi_handle, &xfer_struct);
    CheckLapiProblem(e, lapi_rc);
    while (flag ==  0) {
        LAPI_Probe(lapi_handle);
    }
}

#ifdef FOR_JAVA
JNIEXPORT void JNICALL Java_Trans_sendMyArrayBlock(JNIEnv * e,
                                                   jclass transClass,
                                                   jint tgt,
                                                   jint globalBlockNum,
                                                   jdoubleArray data,
                                                   jint l) {
    jboolean is_copy;
//    jdouble *d = e->GetDoubleArrayElements(data, &is_copy);
    jdouble *d = (jdouble *) e->GetPrimitiveArrayCritical(data, &is_copy);
    sendMyArrayBlock(tgt, globalBlockNum, d, l, e);
    if (is_copy == JNI_TRUE) {
//        e->ReleaseDoubleArrayElements(data, d, JNI_ABORT);
        ++numCopiedSentArrays;
        e->ReleasePrimitiveArrayCritical(data, d, JNI_ABORT);
    }
}

JNIEXPORT void JNICALL Java_Trans_AcceptBlockHere(JNIEnv *e,
                                                  jclass transClass,
                                                  jint block,
                                                  jdoubleArray placeDataHere,
                                                  jbooleanArray updateFlags,
                                                  jint indexIntoUpdateFlags) {
    if (placeDataHere == NULL) {
        e->DeleteGlobalRef(bufferAddresses[block].targetJavaArray);
        bufferAddresses[block].targetJavaArray = NULL;
    } else {
        bufferAddresses[block].targetJavaArray = (jdoubleArray) e->NewGlobalRef(placeDataHere);
    }
    if (updateFlags == NULL) {
        e->DeleteGlobalRef(bufferAddresses[block].updateFlags);
        bufferAddresses[block].updateFlags = NULL;
    } else {
        bufferAddresses[block].updateFlags = (jbooleanArray) e->NewGlobalRef(updateFlags);
    }
    bufferAddresses[block].indexIntoUpdateFlags = indexIntoUpdateFlags;
}
#else
void CPP_Trans_AcceptBlockHere(unsigned int block, double *placeDataHere_, volatile bool *updateThisWhenComplete_) {
    bufferAddresses[block].placeDataHere = placeDataHere_;
    bufferAddresses[block].updateThisWhenComplete = updateThisWhenComplete_;
}
#endif

//////////////////////////////////////////////////////////////////////////////
// Make sure everyone has the updated array
//////////////////////////////////////////////////////////////////////////////
#ifdef FOR_JAVA
JNIEXPORT void JNICALL Java_VMInfo_globalFence(JNIEnv *e, jclass vmInfoClass) {
    int lapi_rc;
    lapi_rc = LAPI_Gfence(lapi_handle);
    CheckLapiProblem(e, lapi_rc);
}
#else
void LAPIGlobalFence() {
    int lapi_rc;
    lapi_rc = LAPI_Gfence(lapi_handle);
    CheckLapiProblem(NULL, lapi_rc);
}
#endif

//////////////////////////////////////////////////////////////////////////////
// Start LAPI up
//////////////////////////////////////////////////////////////////////////////
bool initLAPI(JNIEnv *e,
              unsigned int numProcs,
              unsigned int thisIsTask,
              char *(*getNameOfHosti)(JNIEnv *, unsigned int),
              unsigned int (*getPortNumberi)(JNIEnv *, unsigned int),
              unsigned int numGlobalBlocks) {

    lapi_info_t     lapi_info;
    lapi_extend_t   extended_info;
    int             lapi_rc;
    char            msg_buff[LAPI_MAX_ERR_STRING+128];

    mp_procs = numProcs;
    mp_child = thisIsTask;
    if (mp_child < mp_procs) {
           
        sprintf(msg_buff, "%d", mp_procs);
        setenv("MP_PROCS", msg_buff, 1);
        sprintf(msg_buff, "%d", mp_child);
        setenv("MP_CHILD", msg_buff, 1);
        setenv("MP_MSG_API", "lapi", 1);
        setenv("MP_EUILIB", "ip", 1);
        
        bzero((void *) &lapi_info,     sizeof lapi_info);
        bzero((void *) &extended_info, sizeof extended_info);
        lapi_info.add_info          = &extended_info;
        extended_info.num_udp_addr  = mp_procs;
        extended_info.add_udp_addrs = (lapi_udp_t *) malloc(sizeof(lapi_udp_t) * mp_procs);
        bool allAllocOk = true;
        for (int ht = 0; ht < sizeof(hdr_handlers) / sizeof(hdr_handlers[0]); ++ht) {
            if ((hdr_handlers[ht].handlers = (lapi_long_t *) malloc(mp_procs * sizeof(hdr_handlers[ht].handlers[0]))) == NULL) {
                allAllocOk = false;
                break;
            }
        }
        
        if (extended_info.add_udp_addrs != NULL && allAllocOk) {
            for (unsigned int p = 0; p < mp_procs; ++p) {
                
                char *hostName;
                char nm[NI_MAXHOST];
                struct hostent *he;
                
                if ((p == mp_child) &&
                    (gethostname(nm, sizeof(nm)-1) == 0) &&
                    ((nm[sizeof(nm)-1] = '\0') == 0) &&
                    (strlen(nm) < sizeof(nm)-1)) {
                    hostName = nm;
                } else {
                    hostName = getNameOfHosti(e, p);
                }
                he = gethostbyname(hostName);
                if (he == NULL || he->h_addr_list == NULL) {
                    sprintf(msg_buff,"Cannot find hostname %s (%d)",hostName, p);
                    fprintf(stderr,"%s\n", msg_buff);
                    THROWERR;
                } else {
                    extended_info.add_udp_addrs[p].ip_addr = ((struct in_addr *) he->h_addr_list[0])->s_addr;
                    extended_info.add_udp_addrs[p].port_no = getPortNumberi(e, p);
                }
            }
            
            lapi_rc = LAPI_Init(&lapi_handle, &lapi_info);
            free(extended_info.add_udp_addrs);
            if (lapi_rc == LAPI_SUCCESS) {
                int task_id, n_tasks;
                int qenvtirc = LAPI_Qenv(lapi_handle, TASK_ID,   &task_id);
                int qenvntrc = LAPI_Qenv(lapi_handle, NUM_TASKS, &n_tasks);
                int qenvmuhs = LAPI_Qenv(lapi_handle, MAX_UHDR_SZ, (int *) &maxLAPIUHdrSz);
                if (qenvtirc == LAPI_SUCCESS &&
                    qenvntrc == LAPI_SUCCESS &&
                    qenvmuhs == LAPI_SUCCESS &&
                    task_id  == mp_child     &&
                    n_tasks  == mp_procs) {
                    int ht;
                    for (ht = 0; ht < sizeof(hdr_handlers) / sizeof(hdr_handlers[0]); ++ht) {
                        lapi_rc = LAPI_Address_init64(lapi_handle,
                                                      (lapi_long_t) hdr_handlers[ht].this_vms_handler,
                                                      hdr_handlers[ht].handlers);
                        if (lapi_rc != LAPI_SUCCESS) {
                            break;
                        }
                    }
                    if (lapi_rc == LAPI_SUCCESS) {
                        bufferAddresses = (struct whereToDirectIncomingBlock_s *) malloc(sizeof(struct whereToDirectIncomingBlock_s) * numGlobalBlocks);
                        if (bufferAddresses != NULL) {
                            for (unsigned int i = 0; i < numGlobalBlocks; ++i) {
                                bufferAddresses[i].placeDataHere = NULL;
                                bufferAddresses[i].updateThisWhenComplete = NULL;
                                bufferAddresses[i].targetJavaArray = NULL;
                                bufferAddresses[i].updateFlags = NULL;
                                bufferAddresses[i].indexIntoUpdateFlags = 0;
                            }
                            // good to go
                            return true;
                        } else {
                            sprintf(msg_buff,"could not allocate space for buffer addresses\n");
                            fprintf(stderr,"%s\n", msg_buff);
                            THROWERR;
                        }
                    } else {
                        sprintf(msg_buff,"LAPI_Address_init rc = %d: ht = %d", lapi_rc, ht);
                        LAPI_Msg_string(lapi_rc, msg_buff + strlen(msg_buff));
                        fprintf(stderr,"%s\n", msg_buff);
                        THROWERR;
                    }
                } else {
                    sprintf(msg_buff, "LAPI did not init correctly, %d(%d) %d(%d) %d(%d) %d(%d)\n",
                            qenvtirc, LAPI_SUCCESS,
                            qenvntrc, LAPI_SUCCESS,
                            task_id, mp_child, n_tasks, mp_procs);
                    fprintf(stderr,"%s\n", msg_buff);
                    THROWERR;
                }
            } else {
                sprintf(msg_buff,"LAPI_Init rc = %d: ", lapi_rc);
                LAPI_Msg_string(lapi_rc, msg_buff + strlen(msg_buff));
                fprintf(stderr,"%s\n", msg_buff);
                THROWERR;
            }
        } else {
            THROWERR;
        }
    } else {
        sprintf(msg_buff, "mp_child (%d) >= mp_procs (%d)", mp_child, mp_procs);
        fprintf(stderr,"%s\n", msg_buff);
        THROWERR;
    }
    return false;
}

#ifdef FOR_JAVA
static jobjectArray VM__;
static char horrible1[NI_MAXHOST];
static char * getHostNameFromJava(JNIEnv *e, unsigned int p) {
    char *hostName;
    jobject vm_i = e->GetObjectArrayElement(VM__, p);
    hostName = (char *) e->GetStringUTFChars((jstring) e->GetObjectField(vm_i, e->GetFieldID(e->GetObjectClass(vm_i), "hostName", "Ljava/lang/String;")), 0);
    strncpy(horrible1, hostName, sizeof(horrible1));
    e->ReleaseStringUTFChars((jstring) e->GetObjectField(vm_i, e->GetFieldID(e->GetObjectClass(vm_i), "hostName", "Ljava/lang/String;")), hostName);
    return horrible1;
}
static unsigned int getPortNumberFromJava(JNIEnv *e, unsigned int p) {
    jobject vm_i = e->GetObjectArrayElement(VM__, p);
    return e->GetIntField(vm_i, e->GetFieldID(e->GetObjectClass(vm_i), "portNumber", "I"));
}

JNIEXPORT void JNICALL Java_VMInfo_init
(JNIEnv *e, jclass vmInfoClass, jobjectArray VM_)
{
    VM__ = VM_;
    e->SetStaticObjectField(vmInfoClass,
                            e->GetStaticFieldID(vmInfoClass,"VM_","[LVMInfo;"),
                            VM_);
    CheckJNIException(e);
    e->GetJavaVM(&myJavaVM);
    myJNIVersion = e->GetVersion();
    myVMInfoClass = (jclass) e->NewGlobalRef(vmInfoClass);

    if (VM_ != NULL) {
        unsigned int numProcs = e->GetArrayLength(VM_);
        CheckJNIException(e);
        unsigned int thisIsTask = e->GetStaticIntField(vmInfoClass,
                                                       e->GetStaticFieldID(vmInfoClass, "thisIsTask", "I"));
        CheckJNIException(e);
        if (!initLAPI(e, numProcs, thisIsTask, getHostNameFromJava, getPortNumberFromJava, e->GetStaticIntField(vmInfoClass,e->GetStaticFieldID(vmInfoClass,"PtimesQ","I")))) {
        }
        CheckJNIException(e);
    } else {
        e->ThrowNew(e->FindClass("java/lang/Error"), "There is no VM_ info. with which to initialize LAPI.");
    }
}
#else
static unsigned int numProcs = 0;
static struct LAPINameStruct_s {
    char hostName[NI_MAXHOST];
    unsigned int portNumber;
} *VM_ = NULL;
static char *getHostNameFromCPP(JNIEnv *e, unsigned int p) {
    return VM_ ? VM_[p].hostName : NULL;
}
static unsigned int getPortNumberFromCPP(JNIEnv *e, unsigned int p) {
    return VM_ ? VM_[p].portNumber : 0;
}
static bool readConfFile(char *fname) {
    FILE *f = fopen(fname, "r");
    if (f) {
        fscanf(f, "NUMBER_OF_VMS=%u\n", &numProcs);
        VM_ = (struct LAPINameStruct_s *) malloc(sizeof(struct LAPINameStruct_s) * numProcs);
        if (VM_) {
            for (unsigned int i = 0; i < numProcs; ++i) {
                unsigned int vm_no;
                fscanf(f, "VM_[%u].hostName=%s\n", &vm_no, VM_[i].hostName);
                assert(vm_no == i);
                fscanf(f, "VM_[%u].portNumber=%u\n", &vm_no, &VM_[i].portNumber);
                assert(vm_no == i);
            }
            fclose(f);
            return true;
        }
        fclose(f);
    }
    return false;
}

bool CPP_VMInfo_init(char *confFileName, unsigned int thisIsTask, unsigned int numGlobalBlocks) {
    if (!readConfFile(confFileName)) {
        fprintf(stderr,"Could not read %s\n", confFileName);
        return false;
    } else {
        return initLAPI(NULL, numProcs, thisIsTask, getHostNameFromCPP, getPortNumberFromCPP, numGlobalBlocks);
    }
}
#endif

#ifdef FOR_JAVA
//////////////////////////////////////////////////////////////////////////////
// Shut LAPI down
//////////////////////////////////////////////////////////////////////////////
JNIEXPORT void JNICALL Java_VMInfo_term
  (JNIEnv *e, jclass vmInfoClass)
{
    LAPI_Term(lapi_handle);
    e->DeleteGlobalRef(myVMInfoClass);
    if (numCopiedSentArrays != 0 || numCopiedReceivedArrays != 0) {
        printf("primitive arrays copied: in %ld out %ld\n", numCopiedReceivedArrays, numCopiedSentArrays);
    }
}
#else
static void freeConf() {
    if (VM_) free(VM_);
}
void CPP_VMInfo_term() {
    freeConf();
    LAPI_Term(lapi_handle);
}
#endif

unsigned int numLAPITasks() {
    return mp_procs;
}
