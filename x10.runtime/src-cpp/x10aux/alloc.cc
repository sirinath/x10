#include <x10aux/config.h>

#include <x10aux/alloc.h>

#include <cstdio>
#include <cstdarg>

#include <x10aux/throw.h>
#include <x10/lang/OutOfMemoryError.h>

using namespace x10aux;

#ifdef __CYGWIN__
extern "C" int vsnprintf(char *, size_t, const char *, va_list); 
#endif

// do not call this if NO_EXCEPTIONS is defined
// defined here because it depends on OutOfMemoryError and we don't want a header cycle
void x10aux::throwOOME() {
    throwException<x10::lang::OutOfMemoryError>();
}

char *x10aux::alloc_printf(const char *fmt, ...) {
    va_list args;
    char try_buf[1];
    va_start(args, fmt);
    std::size_t sz = vsnprintf(try_buf, 0, fmt, args);
    va_end(args);
    char *r = x10aux::alloc<char>(sz+1, false);
    va_start(args, fmt);
    std::size_t s1 = vsnprintf(r, sz+1, fmt, args);
    (void) s1;
    assert (s1 == sz);
    va_end(args);
    return r;
}

char *x10aux::realloc_printf(char *buf, const char *fmt, ...) {
    std::size_t original_sz = strlen(buf);
    va_list args;
    char try_buf[1];
    va_start(args, fmt);
    std::size_t sz = original_sz + vsnprintf(try_buf, 0, fmt, args);
    va_end(args);
    char *r = x10aux::realloc(buf, sz+1);
    // append the new stuff onto the original stuff
    va_start(args, fmt);
    std::size_t s1 = vsnprintf(&r[original_sz], sz+1, fmt, args);
    (void) s1;
    assert (s1 == sz);
    va_end(args);
    return r;
}

#ifdef X10_USE_BDWGC        
	static bool gc_init_done = false;
#endif        

void *x10aux::alloc_internal (size_t size, bool containsPtrs) {
    void* ret;
#ifdef X10_USE_BDWGC        
    if (!gc_init_done) {
        GC_INIT();
        gc_init_done = true;
    }
    if (containsPtrs) {
        ret = GC_MALLOC(size);
    } else {
        ret = GC_MALLOC_ATOMIC(size);
    }
#else
    ret = ::malloc(size);
#endif        

    _M_("\t-> " << (void*)ret);
    if (ret == NULL && size > 0) {
        _M_("Out of memory allocating " << size << " bytes");
        #ifndef NO_EXCEPTIONS
        throwOOME();
        #else
        assert(false && "Out of memory");
        #endif
    }
    return ret;
}

void *x10aux::realloc_internal (void *src, size_t dsz) {
    void *ret;
#ifdef X10_USE_BDWGC
    ret = GC_REALLOC(src, dsz);
#else
    ret = ::realloc(src, dsz);
#endif
    if (ret==NULL && dsz>0) {
        _M_("Out of memory reallocating " << dsz << " bytes");
        #ifndef NO_EXCEPTIONS
        throwOOME();
        #else
        assert(false && "Out of memory");
        #endif
    }
    return ret;
}

void x10aux::dealloc_internal (const void *obj_) {
    void *obj = const_cast<void*>(obj_); // free does not take const void *
#ifdef X10_USE_BDWGC
    GC_FREE(obj);
#else
    ::free(obj);
#endif        
}

size_t x10aux::heap_size() {
#ifdef X10_USE_BDWGC
    return GC_get_heap_size();
#else
    // TODO: an actual useful implementation of this function when we aren't using GC.
    return (size_t)(-1);
#endif
}

    



        



