#ifndef X10AUX_ATOMIC_OPS_H
#define X10AUX_ATOMIC_OPS_H

#include <x10aux/config.h>
#include <x10aux/ref.h>

#if !defined(_LP64)
namespace x10 {
    namespace runtime {
        class Lock;
    }
}
#endif

#if defined(_ARCH_PPC) && defined(__xlC__)
/* inline ppc asms for xlc; must be defined in global scope.  Ugh. */
void ppc_sync();
void ppc_isync();
void ppc_lwsync();
#pragma mc_func ppc_sync  {"7c0004ac"}
#pragma mc_func ppc_isync {"4c00012c"}
#pragma mc_func ppc_lwsync {"7c2004ac"}
#pragma reg_killed_by ppc_sync
#pragma reg_killed_by ppc_isync
#pragma reg_killed_by ppc_lwsync

x10_int ppc_compareAndSet32(x10_int oldValue, volatile x10_int *address, x10_int newValue);
#pragma mc_func ppc_compareAndSet32 {           \
	"7c002028" /* 0: lwarx r0,0,r4 */           \
	"7c001800" /* cmpw r0,r3 */                 \
	"4082000c" /* bne- 1f */                    \
	"7ca0212d" /* stwcx. r5,0,r4 */             \
	"40a2fff0" /* bne- 0b */                    \
	"7c030378" /* mr r3,r0 */                   \
    }
#pragma reg_killed_by ppc_cmpxchg32 gr0,cr0

#if defined(_LP64)
x10_long ppc_compareAndSet64(x10_long oldValue, volatile x10_long *address, x10_long newValue);
#pragma mc_func ppc_compareAndSet64 {           \
	"7c0020a8" /* 0: ldarx r0,0,r4 */           \
	"7c201800" /* cmpd r0,r3 */                 \
	"4082000c" /* bne- 1f */                    \
	"7ca021ad" /* stdcx. r5,0,r4 */             \
	"40a2fff0" /* bne- 0b */                    \
	"7c030378" /* mr r3,r0 */                   \
    }
#pragma reg_killed_by ppc_cmpxchg64 gr0,cr0
#endif
#endif


namespace x10aux {
    class atomic_ops {
    private:
#if !defined(_LP64)
        static x10aux::ref<x10::runtime::Lock> _longOperationLock;
        static void lock();
        static void unlock();
#endif
        
#if defined(_ARCH_PPC) && !defined(__xlC__)
        /* inline ppc asms for gcc; can be nicely defined as private class member functions  */
        static inline void ppc_isync() { asm("isync"); }
        static inline void ppc_lwsync(){ asm("lwsync"); }
        static inline void ppc_sync()  { asm("sync"); }

        static inline x10_int ppc_compareAndSet32(x10_int oldValue, volatile x10_int *address, x10_int newValue) {
            x10_int result;
#if defined(_AIX)
            /* On AIX gcc uses the aix assembler for inline assembly so can't use labels */
            asm("lwarx %0,0,%2\n\t"      /* Load and reserve address into result */
                "cmpw %0,%4\n\t"         /* Compare old value with current */
                "bne- $+12\n\t"          /* oldvalue changed -- bail */
                "stwcx. %3,0,%2\n\t"     /* Store new value -- or set flags if no longer reserved */
                "bne- $-16\n\t"          /* lost reservation -- retry */
                "" : "=&r" (result), "+m" (*address) : "p" (address), "b" (newValue), "b" (oldValue) : "cc");
#else            
            asm("0: lwarx %0,0,%2\n\t"   /* Load and reserve address into result */
                "cmpw %0,%4\n\t"         /* Compare old value with current */
                "bne- 1f\n\t"            /* oldvalue changed -- bail */
                "stwcx. %3,0,%2\n\t"     /* Store new value -- or set flags if no longer reserved */
                "bne- 0b\n\t"            /* lost reservation -- retry */
                "1:" : "=&r" (result), "+m" (*address) : "p" (address), "b" (newValue), "b" (oldValue) : "cc");
#endif            
            return result;
        }

#if defined(_LP64)
        static inline x10_long ppc_compareAndSet64(x10_long oldValue, volatile x10_long *address, x10_long newValue) {
            x10_long result;
#if defined(_AIX)
            /* On AIX gcc uses the aix assembler for inline assembly so can't use labels */
            asm("ldarx %0,0,%2\n\t"     /* Load and reserve address into result */
                "cmpd %0,%4\n\t"        /* Compare old value with current */
                "bne- $+12\n\t"         /* oldvalue changed -- bail */
                "stdcx. %3,0,%2\n\t"    /* Store new value -- or set flags if no longer reserved */
                "bne- $-16\n\t"         /* lost reservation -- retry */
                "" : "=&r" (result), "+m" (*address) : "p" (address), "b" (newValue), "b" (oldValue) : "cc");
#else
            asm("0: ldarx %0,0,%2\n\t"  /* Load and reserve address into result */
                "cmpd %0,%4\n\t"        /* Compare old value with current */
                "bne- 1f\n\t"           /* oldvalue changed -- bail */
                "stdcx. %3,0,%2\n\t"    /* Store new value -- or set flags if no longer reserved */
                "bne- 0b\n\t"           /* lost reservation -- retry */
                "1:" : "=&r" (result), "+m" (*address) : "p" (address), "b" (newValue), "b" (oldValue) : "cc");
#endif
            return result;
        }
#endif 
#endif 

    public:
        static inline void load_load_barrier() {
#if defined(_ARCH_PPC)
            ppc_sync(); /* TODO: sync is overkill for this barrier */
#endif
        }

        static inline void load_store_barrier() {
#if defined(_ARCH_PPC)
            ppc_isync();
#endif
        }

        static inline void store_load_barrier() {
#if defined(_ARCH_PPC)
            ppc_sync();
#endif
        }

        static inline void store_store_barrier() {
#if defined(_ARCH_PPC)
            ppc_lwsync();
#endif
        }

        static inline x10_int compareAndSet_32(volatile x10_int* address, x10_int oldValue, x10_int newValue) {
#if defined(__i386__) || defined(__x86_64__)
            __asm ("lock cmpxchgl %2, %3"
                   : "=a" (oldValue), "+m" (*address)
                   : "q" (newValue), "m" (*address), "0" (oldValue)
                   : "cc");
            return oldValue;
#else
            return ppc_compareAndSet32(oldValue, address, newValue);
#endif 
        }

        static inline x10_long compareAndSet_64(volatile x10_long* address, x10_long oldValue, x10_long newValue) {
#if !defined(_LP64)
            /* TODO: in theory on i586 hardware we could do this with inline asm and cmpxchg8b instead of a mutex,
             * but it isn't trivial to get the register constraints to work correctly.
             */
            lock();
            x10_long curValue = *address;
            if (curValue == oldValue) {
                *address = newValue;
            }
            unlock();
            return curValue;
#else            
#if defined(__x86_64__)
            __asm ("lock cmpxchgq %2, %3"
			: "=a" (oldValue), "+m" (*address)
			: "q" (newValue), "m" (*address), "0" (oldValue)
			: "cc");
		return oldValue;
#else
		return ppc_compareAndSet64(oldValue, address, newValue);
#endif
#endif
        }

        static inline void* compareAndSet_ptr(volatile void** address, void* oldValue, void* newValue) {
#if defined(_LP64)
            return (void*)(compareAndSet_64((volatile x10_long*)address, (x10_long)oldValue, (x10_long)newValue));
#else
            return (void*)(compareAndSet_32((volatile x10_int*)address, (x10_int)oldValue, (x10_int)newValue));
#endif
        }
    };
}

#endif /* ATOMIC_OPERATIONS_HPP_ */
