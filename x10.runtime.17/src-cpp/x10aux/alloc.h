#ifndef X10AUX_ALLOC_H
#define X10AUX_ALLOC_H

#include <x10aux/config.h>

#ifdef X10_USE_BDWGC
#ifdef __linux__
#define GC_LINUX_THREADS
#endif 
#include "gc.h"
#endif

#include <cstdlib>
#include <cstring>
#include <new> // [DC] took me an hour to work out that we needed this for placement new

#include <x10/x10.h> // pgas

namespace x10aux {

    void throwOOME() X10_PRAGMA_NORETURN;

    template<class T> T* alloc(size_t size = sizeof(T)) {
        _M_("Allocating " << size << " bytes of type " << TYPENAME(T));
#ifdef X10_USE_BDWGC        
        T* ret = (T*)GC_MALLOC(size);
#else        
        T* ret = (T*)x10_alloc(size);
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

    // there should probably be an optimised x10_realloc function but never mind
    // FIXME:  There is a GC_REALLOC macro, which we could use when this is actually calling realloc..
    template<class T> T* realloc(T* src, size_t ssz = sizeof(T), size_t dsz = sizeof(T)) {
        T *dest = alloc<T>(dsz);
        if (dest!=NULL && src!=NULL)
            memcpy(dest, src, ssz<dsz ? ssz : dsz);
        dealloc(src);
        return dest;
    }

    template<class T> void dealloc(T* obj) {
        _M_("Freeing chunk " << (void*)obj << " of type " << TYPENAME(T));
#ifdef X10_USE_BDWGC
        GC_FREE((x10_addr_t) obj);
#else        
        x10_free((x10_addr_t) obj);
#endif        
    }

#ifdef __GNUC__
    char *alloc_printf(const char *fmt, ...) 
                __attribute__ ((format (printf, 1, 2)));
#else
    char *alloc_printf(const char *fmt, ...);
#endif

    // if char *bob points to some allocated "bob", then realloc_printf(bob," likes %s","cats")
    // will yield either the same buffer or a replacement buffer containing "bob likes cats".
    // Any dangling pointers to the original buffer will be invalid as is standard with realloc.
#ifdef __GNUC__
    char *realloc_printf(char *buf, const char *fmt, ...) 
                __attribute__ ((format (printf, 2, 3)));
#else
    char *realloc_printf(char *buf, const char *fmt, ...);
#endif

}

#endif
// vim:tabstop=4:shiftwidth=4:expandtab
