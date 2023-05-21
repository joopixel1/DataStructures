package myDS.mySnQ;

//drawback limited capacity
public class ArrayStack<E> implements MyStack<E> {
	
	private final E[] stac;
	private int size;
	
	public ArrayStack () {
		this(100);
	}
	
	public ArrayStack (int capacity) {
		stac = (E[]) new Object[capacity];
		size = 0;
	}

	@Override
	public void add(E e) {
		if(size==stac.length) throw new IllegalStateException("Stack is Full");
		stac[size++] = e;
	}

	@Override
	public E remove() {
		if(size == 0) throw new IllegalStateException("Stack is Empty");
		E t = stac[size-1];
		stac[size-1] = null;
		size--;
		return t;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E top() {
		if(size == 0) throw new IllegalStateException("Stack is Empty");
		return stac[size-1];
	}

}
