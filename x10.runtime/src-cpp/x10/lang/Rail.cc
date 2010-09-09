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

#include <x10aux/config.h>
#include <x10aux/alloc.h>
#include <x10aux/RTT.h>

#include <x10/lang/Object.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/Runtime__FinishStates.h>

using namespace x10aux;

namespace x10 {
    namespace lang {

        RuntimeType Rail<void>::rtt;

        void
        _initRTTHelper_Rail(RuntimeType *location, const RuntimeType *element,
                            const RuntimeType *p1, const RuntimeType *p2) {
            const RuntimeType *parents[3] = { Object::getRTT(), p1, p2 };
            const RuntimeType *params[1] = { element };
            RuntimeType::Variance variances[1] = { RuntimeType::invariant };
            location->initStageTwo("x10.lang.Rail", 3, parents, 1, params, variances);
        }
    }
}

void x10::lang::Rail_notifyEnclosingFinish(deserialization_buffer& buf)
{
    ref<Reference> fs = buf.read<ref<Reference> >();
    ref<x10::lang::Runtime> rt = x10::lang::PlaceLocalHandle_methods<x10aux::ref<x10::lang::Runtime> >::apply(x10::lang::Runtime::FMGL(runtime));
    // olivier says the incr should be just after the notifySubActivitySpawn
    (fs.operator->()->*(findITable<x10::lang::Runtime__FinishState>(fs->_getITables())->notifyActivityCreation))();
    (fs.operator->()->*(findITable<x10::lang::Runtime__FinishState>(fs->_getITables())->notifyActivityTermination))();
}

void x10::lang::Rail_serialize_finish_state (place dst, serialization_buffer &buf)
{
    // dst is the place where the finish update will occur, i.e. where the notifier runs
    dst = x10aux::parent(dst);
    ref<x10::lang::Runtime> rt = x10::lang::PlaceLocalHandle_methods<x10aux::ref<x10::lang::Runtime> >::apply(x10::lang::Runtime::FMGL(runtime));
    ref<Reference> fs = rt->currentState();
    (fs.operator->()->*(findITable<x10::lang::Runtime__FinishState>(fs->_getITables())->notifySubActivitySpawn))(x10::lang::Place_methods::_make(dst));
    buf.write(fs);
}

void x10::lang::Rail_serializeAndSendPut(Place dst_place_, ref<Reference> df, x10_ubyte code,
                                         serialization_id_t _id, void* data, size_t size)
{
    serialization_buffer buf;
    buf.write(code);
    buf.write(df);
    Rail_serialize_finish_state (dst_place_.FMGL(id), buf);
    x10aux::send_put(dst_place_.FMGL(id), _id, buf, data, size);
}

void x10::lang::Rail_serializeAndSendGet(Place src_place_, ref<Reference> df, x10_ubyte code,
                                         serialization_id_t _id, void* data, size_t size)
{
    serialization_buffer buf;
    buf.write(code);
    buf.write(df);
    Rail_serialize_finish_state (x10aux::here, buf);
    x10aux::send_get(src_place_.FMGL(id), _id, buf, data, size);
}


// vim:tabstop=4:shiftwidth=4:expandtab
