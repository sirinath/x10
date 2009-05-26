#include <x10aux/config.h>

#include <x10aux/alloc.h>
#include <x10aux/class_cast.h>
#include <x10aux/serialization.h>
#include <x10aux/basic_functions.h>
#include <x10aux/throw.h>
#include <x10aux/hash.h>

#include <x10/lang/String.h>
#include <x10/lang/Rail.h>

#include <cstdarg>
#include <sstream>

using namespace x10::lang;
using namespace x10aux;

x10_int String::hashCode() {
    return x10aux::hash(reinterpret_cast<const unsigned char*>(FMGL(content)), length());
}

x10_int String::indexOf(ref<String> str, x10_int i) {
    const char *needle = str->FMGL(content);
    // TODO: bounds check
    const char *haystack = &FMGL(content)[i];
    const char *pos = strstr(haystack, needle);
    if (pos == NULL)
        return (x10_int) -1;
    return (x10_int) (pos - haystack);
}

x10_int String::indexOf(x10_char c, x10_int i) {
    int needle = (int)c;
    // TODO: bounds check
    const char *haystack = &FMGL(content)[i];
    const char *pos = strchr(haystack, needle);
    if (pos == NULL)
        return (x10_int) -1;
    return (x10_int) (pos - haystack);
}


static const char *my_strrstr(const char *haystack, const char *needle, int give_up) {
    const char *last_find = NULL;
    for (int i=0 ; i<=give_up && haystack[i]!='\0' ; ++i) {
        for (int j=0 ; needle[j]!='\0' ; ++j) {
            if (haystack[i+j] != needle[j]) goto nonmatch;
        }
        last_find = &haystack[i];
        nonmatch: {}
    }
    return last_find;
}

x10_int String::lastIndexOf(ref<String> str, x10_int i) {
    const char *needle = str->FMGL(content);
    const char *haystack = FMGL(content);
    // TODO: bounds check
    const char *pos = my_strrstr(haystack, needle, i);
    if (pos == NULL)
        return (x10_int) -1;
    return (x10_int) (pos - haystack);
}


static const char *my_strrchr(const char *haystack, int needle, int give_up) {
    const char *last_find = NULL;
    for (int i=0 ; i<=give_up && haystack[i]!='\0' ; ++i) {
        if (haystack[i] == needle) last_find = &haystack[i];
    }
    return last_find;
}

x10_int String::lastIndexOf(x10_char c, x10_int i) {
    int needle = (int)c;
    const char *haystack = FMGL(content);
    // TODO: bounds check
    const char *pos = my_strrchr(haystack, needle, i);
    if (pos == NULL)
        return (x10_int) -1;
    return (x10_int) (pos - haystack);
}

ref<String> String::substring(x10_int start, x10_int end) {
    assert(end>=start); // TODO: proper bounds check
    std::size_t sz = end - start;
    char *str = x10aux::alloc<char>(sz+1);
    for (std::size_t i=0 ; i<sz ; ++i)
        str[i] = FMGL(content)[start+i];
    str[sz] = '\0';
    return String::Steal(str);
}

x10_char String::charAt(x10_int i) {
    // TODO: bounds check
    return (x10_char) FMGL(content)[i];
}


ref<ValRail<x10_char> > String::chars() {
    x10_int sz = length();
    ValRail<x10_char> *rail = alloc_rail<x10_char,ValRail<x10_char> > (sz);
    for (int i=0 ; i<sz ; i++)
        rail->raw()[i] = (x10_char) FMGL(content)[i]; // avoid bounds check
    return rail;
}

ref<ValRail<x10_byte> > String::bytes() {
    x10_int sz = length();
    ValRail<x10_byte> *rail = alloc_rail<x10_byte,ValRail<x10_byte> > (sz);
    for (int i=0 ; i<sz ; i++)
        rail->raw()[i] = (x10_char) FMGL(content)[i]; // avoid bounds check
    return rail;
}

// [IP] I'm sure I will hate this but it will do for now.
static ref<String> format_impl(ref<String> format, ref<AnyRail<ref<Object> > > parms) {
    std::ostringstream ss;
    char* fmt = const_cast<char*>(format->c_str());
    char* next = NULL;
    for (x10_int i = 0; fmt != NULL; i++, fmt = next) {
        next = strchr(fmt+1, '%');
        if (next != NULL)
            *next = '\0';
        if (*fmt != '%') {
            ss << fmt;
            if (next != NULL)
                *next = '%';
            i--;
            continue;
        }
        const ref<Object> p = parms->operator[](i);
        char* buf = NULL;
        if (p.isNull())
            ss << (buf = x10aux::alloc_printf(fmt, "null")); // FIXME: Ignore nulls for now
        else if (x10aux::instanceof<ref<String> >(p))
            ss << (buf = x10aux::alloc_printf(fmt, class_cast<ref<String> >(p)->c_str()));
        else if (x10aux::instanceof<ref<Box<x10_boolean> > >(p))
            ss << (buf = x10aux::alloc_printf(fmt, class_cast<x10_boolean>(p)));
        else if (x10aux::instanceof<ref<Box<x10_byte> > >(p))
            ss << (buf = x10aux::alloc_printf(fmt, class_cast<x10_byte>(p)));
        else if (x10aux::instanceof<ref<Box<x10_char> > >(p))
            ss << (buf = x10aux::alloc_printf(fmt, class_cast<x10_char>(p)));
        else if (x10aux::instanceof<ref<Box<x10_short> > >(p))
            ss << (buf = x10aux::alloc_printf(fmt, class_cast<x10_short>(p)));
        else if (x10aux::instanceof<ref<Box<x10_int> > >(p))
            ss << (buf = x10aux::alloc_printf(fmt, class_cast<x10_int>(p)));
        else if (x10aux::instanceof<ref<Box<x10_long> > >(p))
            ss << (buf = x10aux::alloc_printf(fmt, class_cast<x10_long>(p)));
        else if (x10aux::instanceof<ref<Box<x10_float> > >(p))
            ss << (buf = x10aux::alloc_printf(fmt, class_cast<x10_float>(p)));
        else if (x10aux::instanceof<ref<Box<x10_double> > >(p))
            ss << (buf = x10aux::alloc_printf(fmt, class_cast<x10_double>(p)));
        else
            ss << (buf = x10aux::alloc_printf(fmt, p->toString()->c_str()));
        if (buf != NULL)
            dealloc(buf);
        if (next != NULL)
            *next = '%';
    }
    return String::Lit(ss.str().c_str());
}

ref<String> String::valueOf(x10_boolean v) {
    if (v) {
        return String::Lit("true");
    }
    else {
        return String::Lit("false");
    }
}

ref<String> String::valueOf(x10_char v) {
    std::ostringstream ss;
    ss << x10aux::alloc_printf("%c", v);
    return String::Lit(ss.str().c_str());
}

ref<String> String::valueOf(x10_byte v) {
    std::ostringstream ss;
    ss << x10aux::alloc_printf("%d", v);
    return String::Lit(ss.str().c_str());
}

ref<String> String::valueOf(x10_short v) {
    std::ostringstream ss;
    ss << x10aux::alloc_printf("%d", v);
    return String::Lit(ss.str().c_str());
}

ref<String> String::valueOf(x10_int v) {
    std::ostringstream ss;
    ss << x10aux::alloc_printf("%d", v);
    return String::Lit(ss.str().c_str());
}

ref<String> String::valueOf(x10_long v) {
    std::ostringstream ss;
    ss << x10aux::alloc_printf("%lld", v);
    return String::Lit(ss.str().c_str());
}

ref<String> String::valueOf(x10_float v) {
    std::ostringstream ss;
    ss << x10aux::alloc_printf("%f", v);
    return String::Lit(ss.str().c_str());
}

ref<String> String::valueOf(x10_double v) {
    std::ostringstream ss;
    ss << x10aux::alloc_printf("%llf", v);
    return String::Lit(ss.str().c_str());
}

ref<String> String::valueOf(ref<Object> v) {
    if (v.isNull()) return String::Lit("null");
    return v->toString();
}

ref<String> String::format(ref<String> format, ref<ValRail<ref<Object> > > parms) {
    return format_impl(format, ref<AnyRail<ref<Object> > >(parms));
}

ref<String> String::format(ref<String> format, ref<Rail<ref<Object> > > parms) {
    return format_impl(format, ref<AnyRail<ref<Object> > >(parms));
}

x10_boolean String::_struct_equals(ref<Object> p0) {
    if (p0.get() == this) return true; // short-circuit trivial equality
    if (!this->Value::_struct_equals(p0))
        return false;
    ref<String> that = (ref<String>) p0;
    if (strcmp(this->FMGL(content), that->FMGL(content)))
        return false;
    return true;
}

const serialization_id_t String::_serialization_id =
    DeserializationDispatcher::addDeserializer(String::_deserialize<Object>);

DEFINE_RTT(String);
// vim:tabstop=4:shiftwidth=4:expandtab
