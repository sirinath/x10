#include <x10aux/math_utils.h>

#include <math.h>

x10_double x10aux::math_utils::pow(x10_double x, x10_double y) {
    return ::pow(x,y);
}

x10_double x10aux::math_utils::exp(x10_double x) {
    return ::exp(x);
}

x10_double x10aux::math_utils::cos(x10_double x) {
    return ::cos(x);
}

x10_double x10aux::math_utils::sin(x10_double x) {
    return ::sin(x);
}

x10_double x10aux::math_utils::log(x10_double x) {
    return ::log(x);
}

x10_double x10aux::math_utils::log10(x10_double x) {
    return ::log10(x);
}

x10_double x10aux::math_utils::log1p(x10_double x) {
    return ::log1p(x);
}

x10_double x10aux::math_utils::sqrt(x10_double x) {
    return ::sqrt(x);
}

x10_double x10aux::math_utils::ceil(x10_double x) {
    return ::ceil(x);
}

x10_double x10aux::math_utils::floor(x10_double x) {
    return ::floor(x);
}

x10_double x10aux::math_utils::round(x10_double x) {
    return ::round(x);
}

// vim:tabstop=4:shiftwidth=4:expandtab
