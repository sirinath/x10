#include <x10aux/system_utils.h>

#ifndef DISABLE_CLOCK_GETTIME
#  if !defined(_POSIX_TIMERS) || _POSIX_TIMERS <= 0
#    define DISABLE_CLOCK_GETTIME
#  endif
#endif
#ifndef DISABLE_CLOCK_GETTIME
#  include <time.h>  // for clock_gettime (optional POSIX)
#  if defined(_POSIX_MONOTONIC_CLOCK) && _POSIX_MONOTONIC_CLOCK >= 0
#    define CLOCK_X10 CLOCK_MONOTONIC
#  else
#    define CLOCK_X10 CLOCK_REALTIME
#  endif
#else
#  include <sys/time.h>  // for gettimeofday (POSIX)
#endif
#include <stdlib.h>

void x10aux::system_utils::exit(x10_int code) {
    // FIXME: The implementation in x10.backend/x10lang/x10lang.cc copied code
    //        into a static before throwing.
    //        Do we need to do the equivalent operation? If so, what is it?

    // Cannot do ::exit here, as we'll need to clean up.
    // We need not worry about any user code that looks like
    // catch(...), because --> We are generating code and will
    // never generate such code. Duh!
    throw code;
}

x10_long x10aux::system_utils::currentTimeMillis() {
#ifdef DISABLE_CLOCK_GETTIME
    struct ::timeval tv;
    gettimeofday(&tv, NULL);
    return (x10_long)(tv.tv_sec * 1000LL + tv.tv_usec / 1000);
#else
    struct ::timespec ts;
    ::clock_gettime(CLOCK_X10, &ts);
    return (x10_long)(ts.tv_sec * 1000LL + ts.tv_nsec / 1000000);
#endif
}

x10_long x10aux::system_utils::nanoTime() {
#ifdef DISABLE_CLOCK_GETTIME
    struct ::timeval tv;
    gettimeofday(&tv, NULL);
    return (x10_long)(tv.tv_sec * 1000000000LL + tv.tv_usec * 1000LL);
#else
    struct ::timespec ts;
    ::clock_gettime(CLOCK_X10, &ts);
    return (x10_long)(ts.tv_sec * 1000000000LL + ts.tv_nsec);
#endif
}

// vim:tabstop=4:shiftwidth=4:expandtab
