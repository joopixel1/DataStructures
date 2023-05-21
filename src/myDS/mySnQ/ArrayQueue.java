package myDS.mySnQ;

public class ArrayQueue<E> implements myQueue<E> {

	private final E[] stac;
	private int first;
	private int size;
	
	public ArrayQueue () {
		this(100);
	}
	
	public ArrayQueue (int capacity) {
		stac = (E[]) new Object[capacity];
		first = 0;
		size = 0;
	}

	@Override
	public void add(E e) {
		if(size==stac.length) throw new IllegalStateException("Queue is Full");
		stac[(size+first)%stac.length] = e;
		size++;
	}

	@Override
	public E remove() {
		if(size == 0) throw new IllegalStateException("Queue is Empty");
		E t = stac[first];
		stac[first] = null;
		first = (first+1)%stac.length;
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
	public E first() {
		if(size == 0) throw new IllegalStateException("Queue is Empty");
		return stac[first];
	}

}
