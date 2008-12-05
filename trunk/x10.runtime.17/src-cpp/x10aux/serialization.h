#ifndef X10AUX_SERIALIZATION_H
#define X10AUX_SERIALIZATION_H


#include <x10aux/config.h>

#include <x10/x10.h> // pgas

#include <x10aux/pgas.h>
#include <x10aux/ref.h>
#include <x10aux/alloc.h>
#include <x10aux/deserialization_dispatcher.h>


/* --------------------- 
 * Serialisation support
 * ---------------------
 *
 * There are two separate mechanisms for (de)serialisation --
 *
 * 1) Instances of ref<T>
 *
 * 2) Everything else (primitives)
 *
 *
 * The mechanism (2) copies raw implementation-dependent bytes into the stream and we will discuss
 * it no further.
 *
 *
 * The mechanism (1) is designed for 5 cases:
 *
 * a) Subclasses of Ref (local)
 *
 * b) Subclasses of Ref (remote)
 *
 * c) Polymorphic Values (whose concrete type is not known at compile time)
 *
 * d) Final Values (whose concrete type is known at compile time)
 *
 * e) Interfaces (unknown(*) whether they are a Ref or a Value)
 *
 *
 * (*) We are autoboxing but rightly not in the case of function types.
 *
 *
 * The mechanism is used in exactly the same way by hand-written c++ classes and by c++ code that is
 * generated from X10 classes by the X10 compiler.
 *
 *
 * 'Object' declares a static function _serialize which behaves the same way regardless of the
 * concrete type of the target.  Object::_serialize will emit an id (via a virtual function
 * _serialize_id) that is unique to each class, and then serialise the objects representation (via
 * another virtual function _serialize_body).  For the special case of a remote reference (b) it
 * does not defer to these virtual functions since the object's vtable is not located at the current
 * place.  For all other cases, assuming all classes implement _serialize_id and _serialize_body
 * properly, this is sufficient.
 *
 * Unique ids are generated at runtime in a place-independent fashion.  Classes obtain their id by
 * registering a deserialisation function with DeserializationDispatcher at initialisation time, and
 * storing this id in a static field.  Every non-abstract value class has its own id, but all Refs
 * are represented by the same id.  The virtual _serialize_id function writes this id into the
 * buffer provided.
 *
 * To write data (of any kind) we use the method serialization_buffer::write(data) which does the
 * right thing, no matter which of the 5 categories (a-e) it is given.  An internal cursor is
 * incremented ready for the next write().  Note that a class's _serialize_body function should also
 * serialise its super class's representation, e.g. by deferring to the super class's
 * _serialize_body function.
 *
 * To implement (a) we have Ref provide a _serialize_body that serialises the address of the object
 * so that other places can use it as a remote reference.
 *
 * In the case where the object is statically known to be a particular class at deserialisation time
 * (e.g. if we are deserialising into a variable whose type is final), we would like to omit the id
 * from the communication, as it is not required.  This is achieved through a final value class C
 * providing its own _serialize function that does not call _serialize_id.  The write() function
 * will call C::_serialize() instead of resolving the call to Object::_serialize().  This does not
 * affect the behaviour of _serialize when invoked on an instance of C that has been up-cast to
 * Object because _serialize is a static function.  In this case the id would still be emitted.
 * This strategy is used to omit the id in the cases of (a,b,d), above.  In the case of (b),
 * Ref::_serialize does not invoke _serialize_body but just encodes the address.
 *
 *
 * Deserialisation is more complicated as an object has to be constructed.  In the case where we are
 * deserialising into a variable of non-final or interface type, the DeserializationDispatcher is
 * invoked to read an id from the stream and decide what to do.  Note that in such cases, the value
 * has always been serialised from a matching variable on the sending side, so an id will always be
 * present.  During initialisation time, classes register deserialisation functions with the
 * DeserializationDispatcher, which hands out the unique ids.  Thus the DeserializationDispatcher
 * can look up the id in its internal table and dispatch to the appropriate static function which
 * will construct the right kind of object and initialise it by deserialising the object's
 * representation from the stream (provided as a serialization_buffer).
 *
 * Object::_deserialize is the complement of Object::_serialize and defers deserialisation to
 * DeserializationDispatcher.  Ref and final value classes can provide their own static _deserialize
 * functions that do not use DeserializationDispatcher and assume that no id is found in the stream.
 * Thus classes should either define both _serialize and _deserialize or define neither.
 *
 * Classes define a _deserialize_body function that extracts the object's representation from the
 * stream.  The DeserializationDispatcher callback and _deserialize (if present) should usually just
 * create an object of the right type and then call _deserialize_body to handle the rest.  Arbitrary
 * data can be extracted from a serialization_buffer via its read<T>() function.  An internal cursor
 * is incremented so the buffer is ready for the next read().  This function will do the right thing
 * no matter what T is supplied.  Note that classes need to deserialise their parent class's
 * representation too, e.g. by calling their parent's _deserialize_body function.  The two functions
 * _serialize_body and _deserialize_body are dual, and obviously they should be written to match
 * each other.
 *
 * Deserialisation of Ref instances is handled through special casing in Ref::_deserialize.  The
 * address is read from the stream, and either the local address, a remote proxy, or null is
 * returned as appropriate.
 */


namespace x10 {
    namespace lang {
        class Object;
    }
}


namespace x10aux {

    class SERIALIZATION_MARKER { };

    // Allows runtime detection of Value cycles
    class addr_map {
        int _size;
        const void** _ptrs;
        int _top;
        void _grow() {
            _ptrs = (const void**) ::memcpy(new (x10aux::alloc<const void*>((_size<<1)*sizeof(const void*))) const void*[_size<<1], _ptrs, _size*sizeof(const void*));
            _size <<= 1;
        }
        void _add(const void* ptr) {
            if (_top == _size) _grow();
            _ptrs[_top++] = ptr;
        }
        bool _find(const void* ptr) {
            for (int i = 0; i < _top; i++)
                if (_ptrs[i] == ptr) return true;
            return false;
        }
    public:
        addr_map(int init_size = 4) : _size(init_size), _ptrs(new (x10aux::alloc<const void*>((init_size)*sizeof(const void*)))const void*[init_size]), _top(0) {
            assert (_ptrs != NULL);
        }
        template<class T> bool ensure_unique(const ref<T>& r) {
            return ensure_unique((void*) r.get());
        }
        bool ensure_unique(const void* p) {
            if (_find(p)) return false;
            _add(p);
            return true;
        }
        void reset() { _top = 0; assert (false); }
        ~addr_map() { x10aux::dealloc(_ptrs); }
    };



    // A growable buffer for serialising into and out of
    class serialization_buffer {
    private:
        static const int GROW_PERCENT = 200; // can't use float but don't want extra .cc file
        static const size_t INITIAL_SIZE = 16;
        char* buffer;
        char* limit;
        char* cursor;
        static char* alloc(size_t size) { return x10aux::alloc<char>(size); }
        static void dealloc(char* buf) { x10aux::dealloc<char>(buf); }
        char* grow() {
            assert (limit != NULL);
            char* saved_buf = buffer;
            size_t length = cursor - buffer;
            size_t allocated = limit - buffer;
            size_t new_size = (size_t) (allocated * GROW_PERCENT/100.0);
            buffer = alloc(new_size);
            ::memcpy(buffer, saved_buf, length);
            limit = buffer + new_size;
            cursor = buffer + length;
            dealloc(saved_buf);
            return buffer;
        }
    public:

        serialization_buffer()
            : buffer(alloc(INITIAL_SIZE)), limit(buffer + INITIAL_SIZE), cursor(buffer)
        { }

        ~serialization_buffer() {
            if (limit!=NULL)
                dealloc(buffer);
        }

        const char *get() const { return buffer; }

        // we use the same buffers for serializing and deserializing so the
        // const cast is necessary
        void set(const char* buf) { set(const_cast<char*>(buf)); }
        void set(char* buf) {
            buffer = cursor = buf;
            limit = NULL;
        }

        size_t length() { return cursor - buffer; }


        // default case for primitives and other things that never contain pointers
        template<class T> struct Write {
            static void _(serialization_buffer &buf, const T &val, addr_map &) {
                // FIXME: assumes all places are same endian
                _S_("Serializing a "ANSI_SER<<TYPENAME(T)<<ANSI_RESET": "<<val<<" into buf: "<<&buf);
                *(T*) buf.cursor = val;
                buf.cursor += sizeof(T);
            }
        };

        // case for references e.g. ref<Object>, 
        template<class T> struct Write<ref<T> > {
            static void _(serialization_buffer &buf, ref<T> val, addr_map &m) {
                _S_("Serializing a "ANSI_SER ANSI_BOLD<<TYPENAME(T)<<ANSI_RESET
                    " into buf: "<<&buf);
                //depends what T is (interface/Ref/Value/FinalValue/Closure)
                T::_serialize(val,buf,m);
            }
        };

        template<typename T> void write(const T &val, addr_map &m) {
            if (cursor + sizeof(T) >= limit) grow();
            Write<T>::_(*this,val,m);
        }


        // default case for primitives and other things that never contain pointers
        template<class T> struct Read {
            static T &_(serialization_buffer &buf) {
                // FIXME: assumes all places are same endian
                T &val = *(T*) buf.cursor;
                buf.cursor += sizeof(T);
                _S_("Deserializing a "ANSI_SER<<TYPENAME(T)<<ANSI_RESET": "<<val<<" into buf: "<<&buf);
                return val;
            }
        };

        // case for references e.g. ref<Object>, 
        template<class T> struct Read<ref<T> > {
            static ref<T> _(serialization_buffer &buf) {
                //dispatch because we don't know what it is
                _S_("Deserializing a "ANSI_SER ANSI_BOLD<<TYPENAME(T)<<ANSI_RESET" from buf: "<<&buf);
                return T::template _deserialize<T>(buf);
            }
        };

        template<typename T> T read() {
            return Read<T>::_(*this);
        }

/*
        void clean() {
            dealloc(buffer);
            buffer = alloc(INITIAL_SIZE);
            limit = buffer + INITIAL_SIZE;
            cursor = buffer;
        }
*/

    };



}

#endif
// vim:tabstop=4:shiftwidth=4:expandtab:textwidth=100

