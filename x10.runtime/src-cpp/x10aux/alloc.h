#ifndef X10AUX_ALLOC_H
#define X10AUX_ALLOC_H

#include <x10aux/config.h>

#ifdef X10_USE_BDWGC
#define GC_THREADS
#include "gc.h"
#endif

#include <cstdlib>
#include <cstring>
#include <new> // [DC] took me an hour to work out that we needed this for placement new

namespace x10aux {

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

#include <x10aux/RTT.h>

namespace x10aux {

    void throwOOME() X10_PRAGMA_NORETURN;

    void *alloc_internal (size_t size);
    void *realloc_internal (void* src, size_t dsz);
    void *dealloc_internal (const void *obj_);

    template<class T> T* alloc(size_t size = sizeof(T)) {
        _M_("Allocating " << size << " bytes of type " << TYPENAME(T));
        return (T*)alloc_internal(size);
    }

    // Allocate an object with an x10_addr_t prepended to it
    template<class T> T* alloc_remote(size_t size = sizeof(T)) {
        _M_("Allocating a remote object of type " << TYPENAME(T));
        T* ret = alloc<T>(size+sizeof(x10_addr_t));
        return (T*)(((char*)ret)+sizeof(x10_addr_t));
    }


    template<class T> T* realloc(T* src, size_t dsz) {
        _M_("Reallocing chunk " << (void*)src << " of type " << TYPENAME(T));
        return (T*)realloc_internal(src, dsz);
    }


    template<class T> void dealloc(const T* obj_) {
        _M_("Freeing chunk " << (void*)obj_ << " of type " << TYPENAME(T));
        dealloc_internal(obj_);
    }

    // Deallocate an object with an x10_addr_t prepended to it
    template<class T> void dealloc_remote(const T* obj_) {
        _M_("Freeing a remote object "<< (void*)obj_ << " of type " << TYPENAME(T));
        const T* obj = (const T*)(((const char*)obj_)-sizeof(x10_addr_t));
        dealloc(obj);
    }

}

#endif
// vim:tabstop=4:shiftwidth=4:expandtab
