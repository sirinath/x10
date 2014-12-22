type t1 = void;

private type t2 = void;

type t3[T] = T;

type t4[T1, T2, T3, T5] = T1;

type t5{} = void;

type t6[T]{} = void;

type t7{ true } = void;

type t8{ true, true } = void;

type t9{ true, true, 1 == 1 } = void;

// FUTUR: type t10{ x:Long; y: Long x == y } = void;

type t11 (Long) = void;

type t12 (Long) { true, true, 1 == 1 } = void;

type t13 [T1, T2, T3, T5] (Long) { true, true, 1 == 1 } = void;
