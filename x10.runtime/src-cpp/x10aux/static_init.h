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

#ifndef X10AUX_STATIC_INIT_H
#define X10AUX_STATIC_INIT_H

#include <x10aux/config.h>

#include <x10aux/deserialization_dispatcher.h>
#include <x10aux/serialization.h>
#include <x10aux/network.h>

namespace x10aux {

    class StaticInitBroadcastDispatcher {
        protected:
        static DeserializationDispatcher *it;
        static serialization_id_t const STATIC_BROADCAST_ID;
        static void doBroadcast(serialization_id_t id, char* the_buf, x10_uint sz);

        public:
        static serialization_id_t addRoutine(Deserializer init);
        static ref<x10::lang::Object> dispatch(deserialization_buffer& buf);
        template<class C> static void broadcastStaticField(C f, serialization_id_t id);
        static void await();
        static void notify();
    };

    template<class C>
    void StaticInitBroadcastDispatcher::broadcastStaticField(C f, serialization_id_t id) {
        if (num_hosts == 1) return;
        serialization_buffer buf;
        buf.write(id);
        buf.write(f);
        x10_uint sz = buf.length();
        serialized_bytes += sz; asyncs_sent++;
        doBroadcast(STATIC_BROADCAST_ID, buf.borrow(), sz);
        // buffer cleaned up when buf destructed
    }

    template<> inline const char *typeName<StaticInitBroadcastDispatcher>()
    { return "StaticInitBroadcastDispatcher"; }

    enum status {
        UNINITIALIZED = 0,
        INITIALIZING,
        INITIALIZED
    };
}

#endif
// vim:tabstop=4:shiftwidth=4:expandtab
