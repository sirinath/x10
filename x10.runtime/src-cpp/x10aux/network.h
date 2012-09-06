/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

#ifndef X10AUX_NETWORK_H
#define X10AUX_NETWORK_H

#include <x10aux/config.h>

#include <x10rt_front.h>
#include <x10rt_cpp.h>

namespace x10 { namespace lang { class VoidFun_0_0; } }
namespace x10 { namespace lang { class FinishState; } }
namespace x10 { namespace lang { class Reference; } }
namespace x10 { namespace lang { class String; } }
namespace x10 { namespace util { template <class K, class V> class HashMap; } }

namespace x10aux {

    extern x10_int num_local_cores;

    typedef x10_short serialization_id_t;

    typedef x10rt_msg_type msg_type;
    typedef x10rt_copy_sz copy_sz;
    typedef x10_int place; // FIXME: should be x10rt_place, but place ids are signed everywhere
    typedef x10rt_endpoint endpoint;

    // a message type used for putting serialised kernel data on a gpu 
    extern msg_type kernel_put;

    // caches to avoid repeatedly calling into x10rt for trivial things
    extern place num_places;
    extern place num_hosts;
    extern place here;

    // can be used to test whether the above caches contain valid data
    extern bool x10rt_initialized;

    inline place num_children (place p)       { return x10rt_nchildren(p); }
    inline x10_boolean is_host (place p)      { return x10rt_is_host(p); }
    inline place parent (place p)             { return x10rt_parent(p); }
    inline place child (place p, place index) { return x10rt_child(p, index); }
    inline place child_index (place p)        { return x10rt_child_index(p); }
    inline x10_boolean is_spe (place p)       { return x10rt_is_spe(p); }
    inline x10_boolean is_cuda (place p)      { return x10rt_is_cuda(p); }
    inline void event_probe (void)            { x10rt_probe(); }
    inline void blocking_probe (void)         { x10rt_blocking_probe(); }

    extern const int cuda_cfgs[];
    void blocks_threads (place p, msg_type t, int shm, x10_ubyte &bs, x10_ubyte &ts, const int *cfgs=cuda_cfgs);
    void blocks_threads (place p, msg_type t, int shm, x10_byte &bs, x10_byte &ts, const int *cfgs=cuda_cfgs);
    void blocks_threads (place p, msg_type t, int shm, x10_ushort &bs, x10_ushort &ts, const int *cfgs=cuda_cfgs);
    void blocks_threads (place p, msg_type t, int shm, x10_short &bs, x10_short &ts, const int *cfgs=cuda_cfgs);
    void blocks_threads (place p, msg_type t, int shm, x10_uint &bs, x10_uint &ts, const int *cfgs=cuda_cfgs);
    void blocks_threads (place p, msg_type t, int shm, x10_int &bs, x10_int &ts, const int *cfgs=cuda_cfgs);
    void blocks_threads (place p, msg_type t, int shm, x10_ulong &bs, x10_ulong &ts, const int *cfgs=cuda_cfgs);
    void blocks_threads (place p, msg_type t, int shm, x10_long &bs, x10_long &ts, const int *cfgs=cuda_cfgs);


    inline x10_ulong remote_alloc (place p, size_t sz) {
        _X_(ANSI_BOLD<<ANSI_X10RT<<"Remote alloc: "<<ANSI_RESET
            <<"size "<<sz<<" to place: "<<p);
        if (sz==0) return 0;
        x10_ulong r = 0;
        x10rt_remote_alloc(p, sz, x10rt_remote_ptr_setter, &r);
        while (r==0) x10rt_probe();
        _X_(ANSI_X10RT<<"got 0x"<<std::hex<<r<<std::dec<<" ("<<r<<")");
        return r;
    }

    inline void remote_free (place p, x10_ulong ptr) {
        _X_(ANSI_BOLD<<ANSI_X10RT<<"Remote free: "<<ANSI_RESET
            <<"ptr "<<std::hex<<ptr<<std::dec<<" to place: "<<p);
        x10rt_remote_free(p, ptr);
    }

    extern x10rt_remote_op_params *opv;
    extern size_t opc;
    extern size_t remote_op_batch;

    inline void remote_op (x10rt_place place, x10rt_remote_ptr remote_addr,
                           x10rt_op_type type, unsigned long long value)
    {
        opv[opc].dest = place;
        opv[opc].dest_buf = remote_addr;
        opv[opc].op = type;
        opv[opc].value = value;
        opc++;
        if (opc == remote_op_batch) {
            x10rt_remote_ops(opv,opc);
            opc = 0;
        }
    }


    msg_type register_async_handler (const char *cubin=NULL, const char *kernel=NULL);
    msg_type register_put_handler (void);
    msg_type register_get_handler (void);

    void registration_complete (void);

    void network_init (int ac, char **av);

    extern volatile x10_long asyncs_sent;
    extern volatile x10_long asyncs_received;
    extern volatile x10_long serialized_bytes;
    extern volatile x10_long deserialized_bytes;

    // use templates to avoid including Runtime__X10RTStats.h and friends from x10aux
    template<class U> U get_X10RTMessageStats (x10rt_msg_stats &m)
    {
        return U::_make(m.bytes_sent, m.messages_sent, m.bytes_received, m.messages_received);
    }

    template<class T, class U> T get_X10RTStats (void)
    {
        x10rt_stats s;
        x10rt_get_stats(&s);
        return T::_make(x10aux::get_X10RTMessageStats<U>(s.msg),
                        x10aux::get_X10RTMessageStats<U>(s.put),
                        s.put_copied_bytes_sent,
                        s.put_copied_bytes_received,
                        x10aux::get_X10RTMessageStats<U>(s.get),
                        s.get_copied_bytes_sent,
                        s.get_copied_bytes_received);
    }

    extern x10_int platform_max_threads;
    extern x10_boolean default_static_threads;

    inline void shutdown() {
        _X_("X10RT shutdown starting");
        x10rt_finalize();
        _X_("X10RT shutdown complete");
    }

}

namespace x10aux {

    x10::util::HashMap<x10::lang::String*, x10::lang::String*>* loadenv();

    void run_closure_at (place p, x10::lang::VoidFun_0_0* body, endpoint e=0);
    void run_async_at (place p, x10::lang::VoidFun_0_0* body, x10::lang::FinishState* fs, endpoint e=0);

    class serialization_buffer;

    void send_get (place p, serialization_id_t id,
                   serialization_buffer &buf, void *data, x10aux::copy_sz len, endpoint e=0);
   
    void send_put (place p, serialization_id_t id,
                   serialization_buffer &buf, void *data, x10aux::copy_sz len, endpoint e=0);

    void cuda_put (place gpu, x10_ulong addr, void *var, size_t sz);

    // teams
    void *coll_enter();
    void coll_handler(void *arg);
    void *coll_enter2(void *arg);
    void coll_handler2(x10rt_team t, void *arg);
}
#endif
// vim:tabstop=4:shiftwidth=4:expandtab
