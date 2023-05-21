package myDS.fundamentalDS;

import myDS.myCollections.MyCollection;

public class MySingleLinkedSequence<E> implements MyCollection<E>, Cloneable {

	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	
	public E first() {return (isEmpty()) ? null : head.e;}
	
	public E last() {return (isEmpty()) ? null : tail.e;}

	public void addFirst(E e) {
		if(head == null) {
			head = new Node<E>(e, null);
			tail = head;
		}
		else {
			head = new Node<E>(e, head);
		}
		size++;
	}
	
	public void addLast(E e) {
		if(head == null) {
			head = new Node<E>(e, null);
			tail = head;
		}
		else {
			tail.next = new Node<E>(e, null);
			tail = tail.next;
		}
		size++;
	}
	
	public E removeFirst() {
		if(isEmpty()) return null;
		Node<E> n = head;
		head = head.next;
		size--;
		if(size==0) tail = null; // remember dis.
		return n.e;
	}
	
	@Override
	public String toString() {
		String real = "";
		Node<E> walkA = this.head;
		while(walkA != null) {
			real += (", " + walkA.e);
			walkA = walkA.next;
		}
		return real;
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
	public void add(E e) {
		throw new UnsupportedOperationException("add");	
	}

	@Override
	public E remove() {
		throw new UnsupportedOperationException("remove");	
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(o==this) return true;
		
		if(o.getClass() != this.getClass() | o==null) return false;
		
		MySingleLinkedSequence<E> m = (MySingleLinkedSequence<E>)o;
		
		if(m.size() != this.size()) return false;
		Node walkA = this.head;
		Node walkB = m.head;
		
		while(walkA != null) {
			if(!walkA.e.equals(walkB.e)) return false;
			walkA = walkA.next;
			walkB = walkB.next;
		}
		
		return true;
	}
	
	@Override
	public Object clone() {
	
		MySingleLinkedSequence<E> m;
		try {
			m = (MySingleLinkedSequence<E>)super.clone();
			
			if(m.size > 0) {
				m.head = new Node<E>(head.e, null);
				Node<E> walkA = this.head.next;
				Node<E> walkB = m.head;
		
				while(walkA != null) {
					walkB.next = new Node<E>(walkA.e, null);					
					walkA = walkA.next;
					walkB = walkB.next;
				}
				walkB.next = tail;
			}
			
			return m;
		} 
		catch (CloneNotSupportedException e) {
			return null;
		}
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
