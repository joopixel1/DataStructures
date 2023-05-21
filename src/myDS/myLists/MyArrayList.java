package myDS.myLists;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import myDS.myTrees.LinkedTree;

public class MyArrayList<E> implements MyList<E> {
	
	E[] arr;
	int size;
	
	public MyArrayList(){
		arr = (E[]) new Object[16];
		size=0;
	}
	
	@Override
	public void add(E e) {
		if(size== arr.length) expand();
		arr[size++] = e;	
	}
	
	@Override
	public void add(int i, E e) {
		adder(i, e);
	}
	private void adder(int i, E e) {
		if(i < 0 || i>size) throw new IndexOutOfBoundsException();
		if(size == arr.length) expand();
		int j = size;
		for(; j != i; j--) arr[j] = arr[j-1];
		arr[j] = e;
		size++;	
	}

	private void check(int i) {
		if(i < 0 || i>=size) throw new IndexOutOfBoundsException();
	}

	private void expand() {
		arr = Arrays.copyOf(arr, arr.length*2);	
	}

	@Override
	public E remove() {
		if(isEmpty()) return null;
		E a  = arr[--size];
		arr[size] = null;
		if(arr.length > 16 && size < arr.length/4) shrink();
		return a;		
	}
	
	@Override
	public E remove (int i) {
		return remover(i);		
	}
	
	private E remover(int i) {
		check(i);
		
		E a  = arr[i];
		int j = i+1;
		for(; j < size; j++) arr[j-1] = arr[j];
		
		arr[--size] = null;
		if(arr.length > 16 && size < arr.length/4) shrink();
		return a;	
	}
	

	public E remove(E e) {
		for(int i=0; i<size; i++) {
			if(arr[i] == e) return remover(i);
		}
		return null;
	}

	private void shrink() {
		arr = Arrays.copyOf(arr, size/2);
		
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
	public E get(int i) {
		check(i);
		return arr[i];
	}

	@Override
	public void set(int i, E e) {
		setter(i, e);
	}
	private void setter(int i, E e) {
		check(i);
		arr[i] = e;		
	}
	
	
	@Override
	public String toString() {
		String real = "";
		for(int i=0; i<size; i++) real += (", " + arr[i]);
		return real;
	}

	public E[] toArray(E[] lem) {
		lem = Arrays.copyOf(arr, lem.length);
		return lem;
	}

	@Override
	public ListIterator<E> iterator() {
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements ListIterator<E>{
		
		private int j=0;
		private int mySize=size;
		private boolean removable = false;

		@Override
		public boolean hasNext() {return j<size;}

		@Override
		public E next() {
			checkForComodification();
			if(!hasNext()) throw new NoSuchElementException();
			removable = true;
			return arr[j++];
		}

		@Override
		public boolean hasPrevious() {
			return j>0;
		}

		@Override
		public E previous() {
			checkForComodification();
			if(!hasPrevious()) throw new NoSuchElementException();
			return arr[--j];
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return j;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return j-1;
		}

		@Override
		public void remove() {
			checkForComodification();
			if(!removable) throw new IllegalStateException();
			removable = false;
			remover(j-1);
			mySize--;
		}

		@Override
		public void set(E e) {
			checkForComodification();
			if(!removable) throw new IllegalStateException();
			setter(j-1, e);
		}

		@Override
		public void add(E e) {
			checkForComodification();
			adder(j, e);
			mySize++;
		}
		
		private void checkForComodification() {
			if (mySize != size) throw new ConcurrentModificationException();			
		}
		
	}


}
