package myDS.myMaps;


import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import myDS.myCollections.Position;
import myDS.myLists.MyArrayList;


public class SkipListMultiSet<E> implements MyMultiSet<E> {


	private MyArrayList<Node> header;
	private MyArrayList<Node> trailer;
	private int size;
	private int HEIGHT = 10;
	private Comparator<E> compare;
	private Random random;
	
	
	public SkipListMultiSet() {
		this(new Comparator<E> () {
			@Override
			public int compare(E o1, E o2) {
				return ((Comparable<E>) o1).compareTo(o2);	
			}
		});
	}
	
	public SkipListMultiSet(Comparator<E> comp) {
		compare = comp;	
		random = new Random();
		header = new MyArrayList<Node>();
		trailer = new MyArrayList<Node>();
		
		header.add(new Node(null, null, null, null, null));
		for(int i=1; i<HEIGHT; i++) header.add(new Node(null, null, null, null, header.get(i-1)));
		for(int i=0; i<HEIGHT-1; i++) header.get(i).above = header.get(i+1);
		
		trailer.add(new Node(header.get(0), null, null, null, null));
		for(int i=1; i<HEIGHT; i++) trailer.add(new Node(header.get(i), null, null, null, header.get(i-1)));
		for(int i=0; i<HEIGHT-1; i++) trailer.get(i).above = trailer.get(i+1);
		
		for(int i=0; i<HEIGHT; i++) header.get(i).next = trailer.get(i);
	}
	
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}
	
	
	private Node validate(Position<E> p){
		if(!(p instanceof Node)) throw new IllegalArgumentException("argument not valid");
		Node a = (Node)p;
		if(a.next == a || a.prev== a) throw new IllegalStateException("Position is no longer valid");
		return a;
	}
	
	private Position<E> next (Position<E> p){
		Node a = validate(p);
		return (a.next.next == null) ? null : a.next;
	}
	
	private Position<E> prev (Position<E>p){
		Node a = validate(p);
		return (a.prev.prev == null) ? null : a.prev;
	}
	
	private Position<E> above (Position<E>p){
		Node a = validate(p);
		return a.above;
	}
	
	private Position<E> below (Position<E>p){
		Node a = validate(p);
		return a.below;
	}
		
	@Override
	public boolean contains(E e) {
		if(isEmpty()) return false;
		E a = search(e).getElement();
		return (compare.compare(e, a) == 0);
	}
	
	private Position<E> search(E e){
		Node ans = header.get(9);
		while (next(ans) != null && compare.compare(e, next(ans).getElement()) >=0) 
			ans = (Node) next(ans);
		
		while (below(ans) != null) {
			ans = (Node) below(ans);
			while (next(ans) != null && compare.compare(e, next(ans).getElement()) >=0) 
				ans = (Node) next(ans);
		}
		
		return ans;
	}
	
	
	@Override
	public int count(E e) {
		if(isEmpty()) return 0;
		
		Node a = (Node) search(e);
		if (compare.compare(e, a.getElement()) == 0) {
			return 1 + countleft(a) + countright(a);
		}
		else {
			return 0;
		}
	}
	
	private int countleft(Node a) {		
		if (a.prev.e != a.e || a.prev == null) return 0; 
		return countleft(a.prev) + 1;
	}
	
	private int countright (Node a) {		
		if (a.next.e != a.e || a.next == null) return 0; 
		return countright(a.next) + 1;
	}

	
	@Override
	public void add(E e) {
		if(isEmpty()) {
			this.addRoot(e);
			return;
		}
		
		Position<E> a = search(e);
		if(a == header.get(0))
			this.addAfter(a, e);
		else if(a == trailer.get(0))
			this.addBefore(a, e);
		else if (compare.compare(e, a.getElement()) <= 0)
			this.addBefore(a, e);
		else
			this.addAfter(a, e);
		
	}
	
	public void set(Position<E> p, E e) {
		for(Node a = validate(p); a != null; a = a.above) a.e = e;
	}

	public Position<E> addRoot(E e) {
		if(	!isEmpty()) throw new IllegalStateException();
		
		Node ans = header.get(0).next = new Node(header.get(0), e, trailer.get(0), null, null);
		trailer.get(0).prev = header.get(0).next;
		
		int h = random.nextInt(10)+1;
		for(int i =1; i<h; i++) {
			ans = header.get(i).next = new Node(header.get(i), e, trailer.get(i), null, ans);
			trailer.get(i).prev = header.get(i).next;
		}
		
		
		while(ans.below != null) {
			ans.below.above = ans;
			ans = ans.below;
		}
		
		size++;
		return ans;
	}
	
	public Position<E> addAfter(Position<E> p, E e) {
		if(isEmpty()) throw new IllegalStateException();
		Node a = validate(p);
		while(a.below != null) a = a.below;
		
		if(a.next == trailer.get(0)) {			
			Node ans = a.next = new Node(a, e, trailer.get(0), null, null);
			trailer.get(0).prev = a.next;
			
			int h = random.nextInt(10)+1;
			for(int i =1; i<h; i++) {
				ans = ans.above = new Node(trailer.get(i).prev, e, trailer.get(i), null, ans);
				trailer.get(i).prev.next = ans; 
				trailer.get(i).prev = ans;
			}
			
			while(ans.below != null) {
				ans.below.above = ans;
				ans = ans.below;
			}
			
			size++;
			return ans;
		}
		else {
			Node l = a.next;			
			Node ans = a.next = new Node(a, e, l, null, null);
			l.prev = a.next;
			
			int h = random.nextInt(10)+1;
			for(int i =1; i<h; i++) {
				while (a.above == null) a = a.prev;
				a = a.above;
				while (l.above == null) l = l.next;
				l = l.above;
				ans = ans.above = new Node(a, e, l, null, ans);
				a.next = ans;
				l.prev = a.next;
			}
			
			while(ans.below != null) {
				ans.below.above = ans;
				ans = ans.below;
			}
			
			size++;
			return ans;
		}
	}

	public Position<E> addBefore(Position<E> p, E e) {
		if(isEmpty()) throw new IllegalStateException();
		Node a = validate(p);
		while(a.below != null) a = a.below;
		
		if(a.prev == header.get(0)) {			
			Node ans = a.prev = new Node(header.get(0), e, a, null, null);
			header.get(0).next = a.prev;
			
			int h = random.nextInt(10)+1;
			for(int i =1; i<h; i++) {
				ans = ans.above = new Node(header.get(i), e, header.get(i).next, null, ans);
				header.get(i).next.prev = ans; 
				header.get(i).next = ans;
			}
			
			while(ans.below != null) {
				ans.below.above = ans;
				ans = ans.below;
			}
			
			size++;
			return ans;
		}
		else {
			Node l = a.prev;			
			Node ans = a.prev = new Node(l, e, a, null, null);
			l.next = a.prev;
			
			int h = random.nextInt(10)+1;
			for(int i =1; i<h; i++) {
				while (a.above == null) a = a.next;
				a = a.above;
				while (l.above == null) l = l.prev;
				l = l.above;
				ans = ans.above = new Node(l, e, a, null, ans);
				a.prev = ans;
				l.next = a.prev;
			}
			
			while(ans.below != null) {
				ans.below.above = ans;
				ans = ans.below;
			}
			
			size++;
			return ans;
		}
	}
	

	@Override
	public E remove(E e) {
		Position<E> a = search(e);
		if (compare.compare(e, a.getElement()) == 0) {
			return remover(a);
		}
		else return null;
	}
	
	@Override
	public void remove(E e, int n) {
		for(int i=0; i<n; i++) remove(e);
	}
	
	@Override
	public void removeAll(E e) {
		while(remove(e) != null);		
	}
	
	private E remover(Position<E> p) {
		Node a = validate(p);
		while(a.below != null) a = a.below;
		Node ans = a;
		
		while(a != null) {
			a.next.prev = a.prev;
			a.prev.next = a.next;
			a.next=null;            //for gc
			a.prev = null;          //for gc
		
			a = a.above;
		}
		
		size--;
		return ans.e;
	}
	

	
	@Override
	public void union(MySet<E> T) {
		for (E e: T) add(e);		
	}

	@Override
	public void intersection(MySet<E> T) {
		for (E e: T) {
			if(!contains(e)) remove(e);
		}
	}

	@Override
	public void subtract(MySet<E> T) {
		for (E e: T) {
			if(contains(e)) remove(e);
		}
	}
	
	
	@Override
	public String toString() {
		String real = "";
		Node walkA = this.header.get(0).next;
		while(walkA != trailer.get(0)) {
			real += (", " + walkA.e);
			walkA = walkA.next;
		}
		return real;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private PositionIterator iter  = new PositionIterator();

			@Override
			public boolean hasNext() {return iter.hasNext();}

			@Override
			public E next() {
				return iter.next().getElement();
			}
		};
	}

	
	private class Node implements Position<E>{
		
		private E e;
		private Node next;
		private Node prev;
		private Node above;
		private Node below;
		
		public Node (Node p, E elem, Node n, Node a, Node b) {
			prev = p;
			e = elem;
			next = n;
			above = a;
			below = b;
		}

		@Override
		public E getElement() {
			return e;
		}
		
	}

	
	private class PositionIterator implements Iterator<Position<E>>{
		
		private Node walk = header.get(0);

		@Override
		public boolean hasNext() {return walk.next != trailer.get(0);}

		@Override
		public Position<E> next() {
			if(!hasNext()) throw new NoSuchElementException();
			return walk = walk.next;
		}

	}

	
}
