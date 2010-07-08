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

package x10.rtt;

import x10.core.fun.Fun_0_1;
import x10.core.fun.Fun_0_2;


public class ByteType extends RuntimeType<Byte> {
    public ByteType() {
        super(byte.class);
    }
    
    @Override
    public String typeName() {
        return "x10.lang.Byte";
    }

    @Override
    public boolean instanceof$(Object o) {
        return o instanceof java.lang.Byte;
    }

    @Override
    public Object makeArray(int length) {
        return new byte[length];
    }
    
    @Override
    public Object makeArray(Object... elem) {
        byte[] arr = new byte[elem.length];
        for (int i = 0; i < elem.length; i++) {
            arr[i] = ((Number)elem[i]).byteValue();
        }
        return arr;
    }
    
    @Override
    public Byte getArray(Object array, int i) {
        return ((byte[]) array)[i];
    }
    
    @Override
    public Byte setArray(Object array, int i, Byte v) {
        return ((byte[]) array)[i] = v;
    }
    
    @Override
    public int arrayLength(Object array) {
    	return ((byte[]) array).length;
    }

    @Override
    public Fun_0_1<Byte, Byte> absOperator() {
        return new Fun_0_1<Byte, Byte>() {
            public Byte apply$G(Byte x) {
                return (byte) (x > 0 ? x : -x);
            }
            public RuntimeType<?> getRTT() { return _RTT; }
            public Type<?> getParam(int i) {if (i == 0 || i == 1 ) return Types.BYTE; return null;}
        };
    }
    @Override
    public Fun_0_1<Byte, Byte> scaleOperator(final int k) {
        return new Fun_0_1<Byte, Byte>() {
            public Byte apply$G(Byte x) {
                return (byte) (x * k);
            }
            public RuntimeType<?> getRTT() { return _RTT; }
            public Type<?> getParam(int i) {if (i == 0 || i == 1) return Types.BYTE; return null;}
        };
    }
    
    @Override
    public Fun_0_2<Byte, Byte, Byte> addOperator() {
        return new Fun_0_2<Byte, Byte, Byte>() {
            public Byte apply$G(Byte x, Byte y) {
                return (byte) (x + y);
            }
            public RuntimeType<?> getRTT() { return _RTT; }
            public Type<?> getParam(int i) {if (i == 0 || i == 1 || i == 2) return Types.BYTE; return null;}
        };
    }
    @Override
    public Fun_0_2<Byte, Byte, Byte> subOperator() {
        return new Fun_0_2<Byte, Byte, Byte>() {
            public Byte apply$G(Byte x, Byte y) {
                return (byte) (x - y);
            }
            public RuntimeType<?> getRTT() { return _RTT; }
            public Type<?> getParam(int i) {if (i == 0 || i == 1 || i == 2) return Types.BYTE; return null;}
        };
    }
    @Override
    public Fun_0_2<Byte, Byte, Byte> mulOperator() {
        return new Fun_0_2<Byte, Byte, Byte>() {
            public Byte apply$G(Byte x, Byte y) {
                return (byte) (x * y);
            }         
            public RuntimeType<?> getRTT() { return _RTT; }
            public Type<?> getParam(int i) {if (i == 0 || i == 1 || i == 2) return Types.BYTE; return null;}
        };
    }
    @Override
    public Fun_0_2<Byte, Byte, Byte> divOperator() {
        return new Fun_0_2<Byte, Byte, Byte>() {
            public Byte apply$G(Byte x, Byte y) {
                return (byte) (x / y);
            }
            public RuntimeType<?> getRTT() { return _RTT; }
            public Type<?> getParam(int i) {if (i == 0 || i == 1 || i == 2) return Types.BYTE; return null;}
        };
    }
    @Override
    public Fun_0_2<Byte, Byte, Byte> modOperator() {
        return new Fun_0_2<Byte, Byte, Byte>() {
            public Byte apply$G(Byte x, Byte y) {
                return (byte) (x % y);
            }
            public RuntimeType<?> getRTT() { return _RTT; }
            public Type<?> getParam(int i) {if (i == 0 || i == 1 || i == 2) return Types.BYTE; return null;}
        };
    }
    @Override
    public Fun_0_2<Byte, Byte, Byte> maxOperator() {
        return new Fun_0_2<Byte, Byte, Byte>() {
            public Byte apply$G(Byte x, Byte y) {
                return (x > y ? x : y);
            }
            public RuntimeType<?> getRTT() { return _RTT; }
            public Type<?> getParam(int i) {if (i == 0 || i == 1 || i == 2) return Types.BYTE; return null;}
        };
    }
    @Override
    public Fun_0_2<Byte, Byte, Byte> minOperator() {
        return new Fun_0_2<Byte, Byte, Byte>() {
            public Byte apply$G(Byte x, Byte y) {
                return (x < y ? x : y);
            }
            public RuntimeType<?> getRTT() { return _RTT; }
            public Type<?> getParam(int i) {if (i == 0 || i == 1 || i == 2) return Types.BYTE; return null;}
        };
    }
    
    @Override
    public Fun_0_2<Byte, Byte, Byte> andOperator() {
        return new Fun_0_2<Byte, Byte, Byte>() {
            public Byte apply$G(Byte x, Byte y) {
                return (byte) (x & y);
            }
            public RuntimeType<?> getRTT() { return _RTT; }
            public Type<?> getParam(int i) {if (i == 0 || i == 1 || i == 2) return Types.BYTE; return null;}
        };
    }

    @Override
    public Fun_0_2<Byte, Byte, Byte> orOperator() {
        return new Fun_0_2<Byte, Byte, Byte>() {
            public Byte apply$G(Byte x, Byte y) {
                return (byte) (x | y);
            }
            public RuntimeType<?> getRTT() { return _RTT; }
            public Type<?> getParam(int i) {if (i == 0 || i == 1 || i == 2) return Types.BYTE; return null;}
        };
    }
    
    @Override
    public Fun_0_2<Byte, Byte, Byte> xorOperator() {
        return new Fun_0_2<Byte, Byte, Byte>() {
            public Byte apply$G(Byte x, Byte y) {
                return (byte) (x ^ y);
            }
            public RuntimeType<?> getRTT() { return _RTT; }
            public Type<?> getParam(int i) {if (i == 0 || i == 1 || i == 2) return Types.BYTE; return null;}
        };
    }

    @Override
    public Fun_0_1<Byte, Byte> negOperator() {
        return new Fun_0_1<Byte, Byte>() {
            public Byte apply$G(Byte x) {
                return (byte) -x;
            }
            public RuntimeType<?> getRTT() { return _RTT; }
            public Type<?> getParam(int i) {if (i == 0 || i == 1) return Types.BYTE; return null;}
        };
    }
    
    @Override
    public Fun_0_1<Byte, Byte> posOperator() {
        return new Fun_0_1<Byte, Byte>() {
            public Byte apply$G(Byte x) {
                return (byte) +x;
            }
            public RuntimeType<?> getRTT() { return _RTT; }
            public Type<?> getParam(int i) {if (i == 0 || i == 1) return Types.BYTE; return null;}
        };
    }
    
    @Override
    public Fun_0_1<Byte, Byte> invOperator() {
        return new Fun_0_1<Byte, Byte>() {
            public Byte apply$G(Byte x) {
                return (byte) ~x;
            }
            public RuntimeType<?> getRTT() { return _RTT; }
            public Type<?> getParam(int i) {if (i == 0 || i == 1) return Types.BYTE; return null;}
        };
    }
    
    @Override
    public Byte minValue() {
        return Byte.MIN_VALUE;
    }

    @Override
    public Byte maxValue() {
        return Byte.MAX_VALUE;
    }
    
    @Override
    public Byte zeroValue() {
        return (byte) 0;
    }
    
    @Override
    public Byte unitValue() {
        return (byte) 1;
    }
}
