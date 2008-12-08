#ifndef X10AUX_MATH_H
#define X10AUX_MATH_H

// Include the system math.h and then undef all the crud
#include <math.h>

// expose some of the below as constants and functions in the x10aux::math namespace
#ifdef M_E
#  define SAVE_CONSTANTS
#endif
#ifdef M_LOG2_E
#  define SAVE_EXTRA_CONSTANTS
#endif
#ifdef signgam
#  define SAVE_SIGNGAM
#endif
#ifdef log2
#  define SAVE_LOG2
#endif
namespace x10aux {
    namespace math {
        #define SAVE_MATH_FUNC1(type, name) \
            inline type __m_##name(double v) { return name(v); } \
            inline type __m_##name(float v) { return name(v); } \
            extern void __m_()
        #define SAVE_MATH_FUNC2(type, name) \
            inline type __m_##name(double v1, double v2) { return name(v1, v2); } \
            inline type __m_##name(float v1, float v2) { return name(v1, v2); } \
            extern void __m_()
        #define SAVE_MATH_CONST(type, name) \
            static const type __m_##name = name
        SAVE_MATH_FUNC1(int, fpclassify);
        SAVE_MATH_FUNC1(bool, isfinite);
        SAVE_MATH_FUNC1(bool, isinf);
        SAVE_MATH_FUNC1(bool, isnan);
        SAVE_MATH_FUNC1(bool, isnormal);
        SAVE_MATH_FUNC1(int, signbit);
        SAVE_MATH_FUNC2(bool, isgreater);
        SAVE_MATH_FUNC2(bool, isgreaterequal);
        SAVE_MATH_FUNC2(bool, isless);
        SAVE_MATH_FUNC2(bool, islessequal);
        SAVE_MATH_FUNC2(bool, islessgreater);
        SAVE_MATH_FUNC2(bool, isunordered);
        #ifdef SAVE_CONSTANTS
        #ifdef SAVE_LOG2
        inline double __m_log2(double v) { return log2(v); }
        inline float __m_log2f(float v) { return log2f(v); }
        #endif
        #endif
        #ifdef SAVE_SIGNGAM
        SAVE_MATH_CONST(int, signgam);
        #endif
        #ifdef SAVE_CONSTANTS
        SAVE_MATH_CONST(int, DOMAIN);
        SAVE_MATH_CONST(int, SING);
        SAVE_MATH_CONST(int, OVERFLOW);
        SAVE_MATH_CONST(int, UNDERFLOW);
        SAVE_MATH_CONST(int, TLOSS);
        SAVE_MATH_CONST(int, PLOSS);
        SAVE_MATH_CONST(double, M_E);
        SAVE_MATH_CONST(double, M_LOG2E);
        SAVE_MATH_CONST(double, M_LOG10E);
        SAVE_MATH_CONST(double, M_LN2);
        SAVE_MATH_CONST(double, M_LN10);
        SAVE_MATH_CONST(double, M_PI);
        SAVE_MATH_CONST(double, M_PI_2);
        SAVE_MATH_CONST(double, M_PI_4);
        SAVE_MATH_CONST(double, M_1_PI);
        SAVE_MATH_CONST(double, M_2_PI);
        SAVE_MATH_CONST(double, M_2_SQRTPI);
        SAVE_MATH_CONST(double, M_SQRT2);
        SAVE_MATH_CONST(double, M_SQRT1_2);
        #ifdef SAVE_EXTRA_CONSTANTS
        SAVE_MATH_CONST(float, MAXFLOAT);
        SAVE_MATH_CONST(double, M_TWOPI);
        SAVE_MATH_CONST(double, M_3PI_4);
        SAVE_MATH_CONST(double, M_SQRTPI);
        SAVE_MATH_CONST(double, M_LN2LO);
        SAVE_MATH_CONST(double, M_LN2HI);
        SAVE_MATH_CONST(double, M_SQRT3);
        SAVE_MATH_CONST(double, M_IVLN10);
        SAVE_MATH_CONST(double, M_LOG2_E);
        SAVE_MATH_CONST(double, M_INVLN2);
        #endif
        #endif
        #undef SAVE_MATH_CONST
        #undef SAVE_MATH_FUNC2
        #undef SAVE_MATH_FUNC1

#undef HUGE_VAL
#undef HUGE_VALF
#undef HUGE_VALL
#undef FLT_EVAL_METHOD
#undef FP_NAN
#undef FP_INFINITE
#undef FP_ZERO
#undef FP_SUBNORMAL
#undef FP_NORMAL
#undef fpclassify
#undef isfinite
#undef isinf
#undef isnan
#undef isnormal
#undef signbit
#undef isgreater
#undef isgreaterequal
#undef isless
#undef islessequal
#undef islessgreater
#undef isunordered
#undef log2
#undef log2f
#undef signgam
//#undef __signgam_r // FIXME
#undef DOMAIN
#undef SING
#undef OVERFLOW
#undef UNDERFLOW
#undef TLOSS
#undef PLOSS
#undef MAXFLOAT
#undef M_E
#undef M_LOG2E
#undef M_LOG10E
#undef M_LN2
#undef M_LN10
#undef M_PI
#undef M_TWOPI
#undef M_PI_2
#undef M_PI_4
#undef M_3PI_4
#undef M_SQRTPI
#undef M_1_PI
#undef M_2_PI
#undef M_2_SQRTPI
#undef M_SQRT2
#undef M_SQRT1_2
#undef M_LN2LO
#undef M_LN2HI
#undef M_SQRT3
#undef M_IVLN10
#undef M_LOG2_E
#undef M_INVLN2

        #define RESTORE_MATH_FUNC1(type, name) \
            inline type name(double v) { return __m_##name(v); } \
            inline type name(float v) { return __m_##name(v); } \
            extern void __m_()
        #define RESTORE_MATH_FUNC2(type, name) \
            inline type name(double v1, double v2) { return __m_##name(v1, v2); } \
            inline type name(float v1, float v2) { return __m_##name(v1, v2); } \
            extern void __m_()
        #define RESTORE_MATH_CONST(type, name) \
            static const type name = __m_##name
        RESTORE_MATH_FUNC1(int, fpclassify);
        RESTORE_MATH_FUNC1(bool, isfinite);
        RESTORE_MATH_FUNC1(bool, isinf);
        RESTORE_MATH_FUNC1(bool, isnan);
        RESTORE_MATH_FUNC1(bool, isnormal);
        RESTORE_MATH_FUNC1(int, signbit);
        RESTORE_MATH_FUNC2(bool, isgreater);
        RESTORE_MATH_FUNC2(bool, isgreaterequal);
        RESTORE_MATH_FUNC2(bool, isless);
        RESTORE_MATH_FUNC2(bool, islessequal);
        RESTORE_MATH_FUNC2(bool, islessgreater);
        RESTORE_MATH_FUNC2(bool, isunordered);
        #ifdef SAVE_CONSTANTS
        #ifdef SAVE_LOG2
        inline double log2(double v) { return __m_log2(v); }
        inline float log2f(float v) { return __m_log2f(v); }
        #endif
        #endif
        #ifdef SAVE_SIGNGAM
        RESTORE_MATH_CONST(int, signgam);
        #endif
        #ifdef SAVE_CONSTANTS
        RESTORE_MATH_CONST(int, DOMAIN);
        RESTORE_MATH_CONST(int, SING);
        RESTORE_MATH_CONST(int, OVERFLOW);
        RESTORE_MATH_CONST(int, UNDERFLOW);
        RESTORE_MATH_CONST(int, TLOSS);
        RESTORE_MATH_CONST(int, PLOSS);
        RESTORE_MATH_CONST(float, MAXFLOAT);
        RESTORE_MATH_CONST(double, M_E);
        RESTORE_MATH_CONST(double, M_LOG2E);
        RESTORE_MATH_CONST(double, M_LOG10E);
        RESTORE_MATH_CONST(double, M_LN2);
        RESTORE_MATH_CONST(double, M_LN10);
        RESTORE_MATH_CONST(double, M_PI);
        RESTORE_MATH_CONST(double, M_TWOPI);
        RESTORE_MATH_CONST(double, M_PI_2);
        RESTORE_MATH_CONST(double, M_PI_4);
        RESTORE_MATH_CONST(double, M_3PI_4);
        RESTORE_MATH_CONST(double, M_SQRTPI);
        RESTORE_MATH_CONST(double, M_1_PI);
        RESTORE_MATH_CONST(double, M_2_PI);
        RESTORE_MATH_CONST(double, M_2_SQRTPI);
        RESTORE_MATH_CONST(double, M_SQRT2);
        RESTORE_MATH_CONST(double, M_SQRT1_2);
        RESTORE_MATH_CONST(double, M_LN2LO);
        RESTORE_MATH_CONST(double, M_LN2HI);
        RESTORE_MATH_CONST(double, M_SQRT3);
        RESTORE_MATH_CONST(double, M_IVLN10);
        RESTORE_MATH_CONST(double, M_LOG2_E);
        RESTORE_MATH_CONST(double, M_INVLN2);
        #endif
        #undef RESTORE_MATH_CONST
        #undef RESTORE_MATH_FUNC2
        #undef RESTORE_MATH_FUNC1
    }
}

#undef SAVE_CONSTANTS
#undef SAVE_SIGNGAM

#endif
// vim:tabstop=4:shiftwidth=4:expandtab
