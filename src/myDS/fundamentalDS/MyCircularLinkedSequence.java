package myDS.fundamentalDS;

import myDS.myCollections.MyCollection;

public class MyCircularLinkedSequence<E> implements MyCollection<E> {

	private Node<E> tail;
	private int size;
	
	
	public E first() {return tail.next.e;}
	
	public E second() {return tail.e;}
	
	public boolean testFloydCycle() {
		Node slow = tail;
		Node fast = tail;
		while(slow!=null && fast!=null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast)return true;
		}
		return false;
	}
	
	public void rotate() {
		if(!isEmpty())tail = tail.next;
	}
	
	public void addFirst(E e) {
		if(tail == null) {
			tail = new Node<E>(e, null); 
			tail.next = tail;
		}
		else if (tail.next == tail) {
			tail.next = new Node<E>(e, tail); 
		}
		else {
			Node<E> n = new Node<E>(e, tail.next);
			tail.next = n;
		}
		size++;
	}
	
	public void addLast(E e) {
		if(tail == null) {
			tail = new Node<E>(e, null);
			tail.next = tail;
		}
		else if (tail.next == tail) {
			tail = new Node<E>(e, tail);
		}
		else {
			tail.next = new Node<E>(e, tail.next);
			tail = tail.next;
		}
		size++;
	}
	

	
	
	public E removeFirst() {
		if(isEmpty()) return null;
		else if (tail.next == tail) {
			Node<E> n = tail;
			tail = null;
			size--;
			return n.e;
		}
		else {
			Node<E> n = tail.next;
			tail.next = tail.next.next;
			size--;
			return n.e;
		}
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
	public String toString() {
		String real = "";
		Node<E> walkA = this.tail.next;
		while(walkA != null) {
			real += (", " + walkA.e);
			walkA = walkA.next;
		}
		if(this.tail != null) if(this.tail != null) System.out.println(walkA.e);
		return real;
	}
	
	
	
	@Override
	public void add(E e) {
		throw new UnsupportedOperationException("add");	
	}

	@Override
	public E remove() {
		throw new UnsupportedOperationException("remove");	
	}
	
	
	private class Node <E> {
		
		private E e;
		private Node<E> next;
		
		public Node (E elem, Node<E> n) {
			e = elem;
			next = n;
		}
	}
	

}
