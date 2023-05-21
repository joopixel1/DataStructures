package myDS.fundamentalDS;

import myDS.myCollections.MyCollection;

public class MyDoubleLinkedSequence<E> implements MyCollection<E>, Cloneable {

	private Node<E> header;
	private Node<E> trailer;
	private int size;
	
	public MyDoubleLinkedSequence(){
		header = new Node<E>(null, null, null);
		trailer = new Node<E>(header, null, null);
		header.next = trailer;
	}
	
	
	public E first() {return (isEmpty()) ? null : header.next.e;}
	
	public E last() {return (isEmpty()) ? null : trailer.prev.e;}

	public void addFirst(E e) {
		if(header.next == trailer) {
			header.next = new Node<E>(header, e, trailer);
			trailer.prev = header.next;
		}
		else {
			Node<E> l = header.next;
			header.next = new Node<E>(header, e, l);
			l.prev = header.next;
		}
		size++;
	}
	
	public void addLast(E e) {
		if(header.next == trailer) {
			header.next = new Node<E>(header, e, trailer);
			trailer.prev = header.next;
		}
		else {
			Node<E> l = trailer.prev;
			l.next = new Node<E>(l, e, trailer);
			trailer.prev = l.next;
		}
		size++;
	}
	
	public E removeFirst() {
		if(isEmpty()) return null;
		Node<E> n = header.next;
		header.next = n.next;
		header.next.prev = header;
		size--;
		n.next=null;            //for gc
		n.prev = null;          //for gc
		return n.e;
	}
	
	public E removeLast() {
		if(isEmpty()) return null;
		Node<E> n = trailer.prev;
		trailer.prev = n.prev;
		trailer.prev.next = trailer;
		size--;
		n.next=null;            //for gc
		n.prev = null;          //for gc
		return n.e;
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
	public String toString() {
		String real = "";
		Node<E> walkA = this.header.next;
		while(walkA != trailer) {
			real += (", " + walkA.e);
			walkA = walkA.next;
		}
		return real;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(o==this) return true;
		
		if(o.getClass() != this.getClass() | o==null) return false;
		
		MyDoubleLinkedSequence<E> m = (MyDoubleLinkedSequence<E>)o;
		
		if(m.size() != this.size()) return false;
		Node walkA = this.header;
		Node walkB = m.header;
		
		while(walkA != null) {
			if(!walkA.e.equals(walkB.e)) return false;
			walkA = walkA.next;
			walkB = walkB.next;
		}
		
		return true;
	}
	
	@Override
	public Object clone() {
	
		MyDoubleLinkedSequence<E> m;
		try {
			m = (MyDoubleLinkedSequence<E>)super.clone();
			
			if(m.size > 0) {
				
				m.header = new Node<E>(null, null, null);
				m.trailer = new Node<E>(header, null, null);
				m.header.next = m.trailer;
				
				Node<E> walkA = this.header.next;
				Node<E> walkB = m.header;
		
				while(walkA != null) {
					walkB.next = new Node<E>(walkB, walkA.e, trailer);					
					walkA = walkA.next;
					walkB = walkB.next;
				}
				trailer.prev = walkB;
			}
		
			return m;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	private class Node <E> {
		
		private E e;
		private Node<E> next;
		private Node<E> prev;
		
		public Node (Node<E> p, E elem, Node<E> n) {
			prev = p;
			e = elem;
			next = n;
		}
	}
	
	
}
