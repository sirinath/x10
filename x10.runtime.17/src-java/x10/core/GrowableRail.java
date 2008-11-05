package x10.core;


import x10.core.fun.Fun_0_1;
import x10.types.Type;
import x10.types.Types;
import x10.core.Iterable;
import x10.core.Iterator;

public class GrowableRail<T> extends Value implements Fun_0_1<Integer,T>, Settable<Integer, T>, Iterable<T> {
	private Type<T> elementType;
	private Object array;
	private int length;
	
	public GrowableRail(Type<T> t) {
		this(t, 16);
	}
	
	public GrowableRail(Type<T> t, int size) {
		this.elementType = t;
		this.array = t.makeArray(size);
		this.length = 0;
	}
	
	public T set(T v, Integer i) {
		grow(i+1);
		assert i < size();
		assert i <= length;
		return elementType.setArray(array, i, v);
	}

	public void add(T v) {
		grow(length+1);
		set(v, length);
		length++;
	}
	
	public void removeLast() {
		length--;
	}

	public Iterator<T> iterator() {
		return new RailIterator();
	}

	protected class RailIterator implements Iterator<T> {
		int i = 0;

		public boolean hasNext() {
			return i < length;
		}

		public T next() {
			return apply(i++);
		}
	}

	
	private void grow(int newSize) {
		if (newSize <= size())
			return;
		newSize = Math.max(newSize, size()*2);
		Object tmp = elementType.makeArray(newSize);
		System.arraycopy(array, 0, tmp, 0, length);
		array = tmp;
	}
	
	public T apply(Integer i) {
		return elementType.getArray(array, i);
	}
	
	public Integer length() {
		return length;
	}
	
    private int size() {
    	return elementType.arrayLength(array);
    }
	
	public Rail<T> toRail() {
		Object tmp = elementType.makeArray(length);
		System.arraycopy(array, 0, tmp, 0, length);
		return RailFactory.makeRailFromJavaArray(elementType, tmp);
	}

	public ValRail<T> toValRail() {
		Object tmp = elementType.makeArray(length);
		System.arraycopy(array, 0, tmp, 0, length);
		return RailFactory.makeValRailFromJavaArray(elementType, tmp);
	}

	public Type<?> rtt_x10$lang$Fun_0_1_U() {
		return Types.INT;
	}

	public Type<?> rtt_x10$lang$Fun_0_1_Z1() {
		return elementType;
	}

    @Override
    public Ref box$() {
    	return new BoxedGrowableRail(elementType, this);
    }
    
    public static class BoxedGrowableRail<T> extends Box<GrowableRail<T>> implements Indexable<Integer,T>, Fun_0_1<Integer,T>, Iterable<T>, Settable<Integer,T> {
    	public BoxedGrowableRail(Type<T> T, GrowableRail<T> v) {
    		super(new GrowableRail.RTT(T), v);
        }

		public T apply(Integer o) {
			return this.value.apply(o);
		}

		public Type<?> rtt_x10$lang$Fun_0_1_U() {
			throw new RuntimeException();
		}

		public Type<?> rtt_x10$lang$Fun_0_1_Z1() {
			throw new RuntimeException();
		}

		public Iterator<T> iterator() {
			return this.value.iterator();
		}

                public T set(T v, Integer i) {
                        return this.value.set(v, i);
                }

                public Integer length() {
                        return this.value.length();
                }

                public void add(T v) {
                        this.value.add(v);
                }

                public void removeLast() {
                        this.value.removeLast();
                }
    }

    
    //
    // Runtime type information
    //
    
    static public class RTT extends x10.types.RuntimeType<GrowableRail<?>> {
        Type<?> type;
        
        public RTT(Type<?> type) {
            super(GrowableRail.class);
            this.type = type;
        }

        public boolean instanceof$(java.lang.Object o) {
            if (!(o instanceof GrowableRail))
                return false;
            GrowableRail r = (GrowableRail) o;
            if (! r.elementType.equals(type)) // covariant
                return false;
            return true;
        }
        
        
        public boolean isSubtype(Type<?> type) {
            if (type instanceof GrowableRail.RTT) {
                GrowableRail.RTT r = (GrowableRail.RTT) type;
                return r.type.equals(this.type);
            }
//            if (type instanceof Fun_0_1.RTT) {
//                Fun_0_1.RTT r = (Fun_0_1.RTT) type;
//                return r.I.equals(Types.INT) && r.V.equals(this.type);
//            }
            return false;
        }
    }
}
