package myDS.myLists;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;

import java.util.NoSuchElementException;

import myDS.myCollections.MyCollection;
import myDS.myCollections.Position;

public class PositionalList<E> implements MyCollection<E>, Iterable<E> {
	
	private Node header;
	private Node trailer;
	private int size;
	
	public PositionalList(){
		header = new Node(null, null, null);
		trailer = new Node(header, null, null);
		header.next = trailer;
	}
	
	private Node validate(Position<E> p){
		if(!(p instanceof Node)) throw new IllegalArgumentException("argument not valid");
		Node a = (Node)p;
		if(a.next == null || a.prev== null) throw new IllegalStateException("Position is no longer valid");
		return a;
	}
	
	
	public Position<E> first() {return (isEmpty()) ? null : header.next;}
	
	public Position<E> last() {return (isEmpty()) ? null : trailer.prev;}
	
	public Position<E> before(Position<E> p) {
		Node a = validate(p);
		return (a.prev == header) ? null : a.prev;
	}
	
	public Position<E> after(Position<E> p) {
		Node a = validate(p);
		return (a.next == trailer) ? null : a.next;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}
	

	public Position<E> addFirst(E e) {
		if(header.next == trailer) {
			header.next = new Node(header, e, trailer);
			trailer.prev = header.next;
		}
		else {
			Node l = header.next;
			header.next = new Node(header, e, l);
			l.prev = header.next;
		}
		size++;
		return first();
	}
	
	public Position<E> addAfter(Position<E> p, E e) {
		Node a = validate(p);
		if(a.next == trailer) {
			a.next = new Node(a, e, trailer);
			trailer.prev = a.next;
		}
		else {
			Node l = a.next;
			a.next = new Node(a, e, l);
			l.prev = a.next;
		}
		size++;
		return after(p);
	}
	
	public Position<E> addLast(E e) {
		if(header.next == trailer) {
			header.next = new Node(header, e, trailer);
			trailer.prev = header.next;
		}
		else {
			Node l = trailer.prev;
			l.next = new Node(l, e, trailer);
			trailer.prev = l.next;
		}
		size++;
		return last();
	}
	
	public Position<E> addBefore(Position<E> p, E e) {
		Node a = validate(p);
		if(a.prev == header) {
			header.next = new Node(header, e, a);
		}
		else {
			Node l = a.prev;
			l.next = new Node(l, e, a);
			a.prev = l.next;
		}
		size++;
		return before(p);
	}
	
	public E removeFirst() {
		if(isEmpty()) return null;
		Node n = header.next;
		header.next = n.next;
		header.next.prev = header;
		size--;
		n.next=null;            //for gc
		n.prev = null;          //for gc
		return n.e;
	}
	
	public E removeLast() {
		if(isEmpty()) return null;
		Node n = trailer.prev;
		trailer.prev = n.prev;
		trailer.prev.next = trailer;
		size--;
		n.next=null;            //for gc
		n.prev = null;          //for gc
		return n.e;
	}
	
	public E remove(Position<E> p) {
		return remover(p);
	}
	
	private E remover(Position<E> p) {
		Node a = validate(p);
		a.next.prev = a.prev;
		a.prev.next = a.next;
		size--;
		a.next=null;            //for gc
		a.prev = null;          //for gc
		return a.e;
	}
	
	public void set(Position<E> p, E e) {
		Node a = validate(p);
		a.e = e;
	}
		
	
	@Override
	public void add(E e) {
		addLast(e);
	}

	@Override
	public E remove() {
		throw new UnsupportedOperationException("remove");	
	}
	
	@Override
	public String toString() {
		String real = "";
		Node walkA = this.header.next;
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
		
		PositionalList<E> m = (PositionalList<E>)o;
		
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
		try {
			PositionalList<E> m = (PositionalList<E>)super.clone();
			
			if(m.size > 0) {
				
				m.header = new Node(null, null, null);
				m.trailer = new Node(header, null, null);
				m.header.next = m.trailer;
				
				Node walkA = this.header.next;
				Node walkB = m.header;
		
				while(walkA != null) {
					walkB.next = new Node(walkB, walkA.e, trailer);					
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
	
	private class Node implements Position<E>{
		
		private E e;
		private Node next;
		private Node prev;
		
		public Node (Node p, E elem, Node n) {
			prev = p;
			e = elem;
			next = n;
		}

		@Override
		public E getElement() {
			if(next == null || prev== null) throw new IllegalStateException("Position is no longer valid");
			return e;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
	
	public Iterable<Position<E>> positions() {
		return new Iterable<Position<E>>() {

			@Override
			public Iterator<Position<E>> iterator() {
				return new PositionIterator();
			}
			
		};
	}
	
	
	private class PositionIterator implements Iterator<Position<E>>{
		
		private Node walk = header;
		private int mySize=size;
		private boolean removable = false;

		@Override
		public boolean hasNext() {return walk.next != trailer;}

		@Override
		public Position<E> next() {
			checkForComodification();
			if(!hasNext()) throw new NoSuchElementException();
			removable = true;
			return walk = walk.next;
		}

		@Override
		public void remove() {
			checkForComodification();
			if(!removable) throw new IllegalStateException();
			removable = false;
			walk = walk.prev;
			remover(walk.next);
			mySize--;
		}

		private void checkForComodification() {
			if (mySize != size) throw new ConcurrentModificationException();			
		}
		
	}
	
	
	private class ElementIterator implements Iterator<E>{
		
		private PositionIterator iter  = new PositionIterator();

		@Override
		public boolean hasNext() {return iter.hasNext();}

		@Override
		public E next() {
			return iter.next().getElement();
		}

		@Override
		public void remove() {
			iter.remove();
		}

	}

}
