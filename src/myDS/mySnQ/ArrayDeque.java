package myDS.mySnQ;

public class ArrayDeque<E> implements myDeque<E> {

	private final E[] stac;
	private int first;
	private int size;
	
	public ArrayDeque () {
		this(100);
	}
	
	public ArrayDeque (int capacity) {
		stac = (E[]) new Object[capacity];
		first = 0;
		size = 0;
	}
	

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public E removeFirst() {
		if(size == 0) throw new IllegalStateException("Deque is Empty");
		E t = stac[first];
		stac[first] = null;
		first = (first+1)%stac.length;
		size--;
		return t;
	}

	@Override
	public E removeLast() {
		if(size == 0) throw new IllegalStateException("Deque is Empty");
		E t = stac[(first+size-1)%stac.length];
		stac[(first+size-1)%stac.length] = null;
		size--;
		return t;
	}

	@Override
	public void addFirst(E e) {
		if(size==stac.length) throw new IllegalStateException("Deque is Full");
		first = (first-1+stac.length)%stac.length;
		stac[first] = e;
		size++;			
	}

	@Override
	public void addLast(E e) {
		if(size==stac.length) throw new IllegalStateException("Deque is Full");
		stac[(size+first)%stac.length] = e;
		size++;		
	}
	
	@Override
	public void add(E e) {
		throw new UnsupportedOperationException("add");	
	}

	@Override
	public E remove() {
		throw new UnsupportedOperationException("remove");	
	}

}
