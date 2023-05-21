package myDS.myMaps;


import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import myDS.myCollections.MyEntry;
import myDS.myCollections.Position;
import myDS.myLists.MyArrayList;

public class SkipListMap<K, V> implements MyMap<K, V> {
	
	private MyArrayList<Node> header;
	private MyArrayList<Node> trailer;
	private int size;
	private int HEIGHT = 10;
	private Comparator<K> compare;
	private Random random;
	
	
	public SkipListMap() {
		this(new Comparator<K> () {
			@Override
			public int compare(K o1, K o2) {
				return ((Comparable<K>) o1).compareTo(o2);	
			}
		});
	}
	
	public SkipListMap(Comparator<K> comp) {
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
	
	
	private Node validate(Position<MyEntry<K, V>> p){
		if(!(p instanceof Node)) throw new IllegalArgumentException("argument not valid");
		Node a = (Node)p;
		if(a.next == a || a.prev== a) throw new IllegalStateException("Position is no longer valid");
		return a;
	}
	
	private Position<MyEntry<K, V>> next (Position<MyEntry<K, V>> p){
		Node a = validate(p);
		return (a.next.next == null) ? null : a.next;
	}
	
	private Position<MyEntry<K, V>> prev (Position<MyEntry<K, V>>p){
		Node a = validate(p);
		return (a.prev.prev == null) ? null : a.prev;
	}
	
	private Position<MyEntry<K, V>> above (Position<MyEntry<K, V>>p){
		Node a = validate(p);
		return a.above;
	}
	
	private Position<MyEntry<K, V>> below (Position<MyEntry<K, V>>p){
		Node a = validate(p);
		return a.below;
	}
		
	@Override
	public V get(K k) {
		if(isEmpty()) return null;
		MyEntry<K, V> a = search(k).getElement();
		return(compare.compare(k, a.getKey()) == 0) ? a.getValue() : null;
	}
	
	private Position<MyEntry<K, V>> search(K k){
		Node ans = header.get(9);
		while (next(ans) != null && compare.compare(k, next(ans).getElement().getKey()) >=0) 
			ans = (Node) next(ans);
		
		while (below(ans) != null) {
			ans = (Node) below(ans);
			while (next(ans) != null && compare.compare(k, next(ans).getElement().getKey()) >=0) 
				ans = (Node) next(ans);
		}
		
		return ans;
	}
		
	
	@Override
	public void put(K k, V v) {
		if(isEmpty()) {
			this.addRoot(new MyEntry<K, V>(k, v));
			return;
		}
		
		Position<MyEntry<K, V>> a = search(k);
		if(a == header.get(0))
			this.addAfter(a, new MyEntry<K, V>(k, v));
		else if(a == trailer.get(0))
			this.addBefore(a, new MyEntry<K, V>(k, v));
		else if (compare.compare(k, a.getElement().getKey()) == 0)
			set(a, v);
		else if (compare.compare(k, a.getElement().getKey()) < 0)
			this.addBefore(a, new MyEntry<K, V>(k, v));
		else
			this.addAfter(a, new MyEntry<K, V>(k, v));
		
	}
	
	public void set(Position<MyEntry<K, V>> p, V v) {
		for(Node a = validate(p); a != null; a = a.above) a.getElement().setValue(v);
	}

	public Position<MyEntry<K, V>> addRoot(MyEntry<K, V> e) {
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
	
	public Position<MyEntry<K, V>> addAfter(Position<MyEntry<K, V>> p, MyEntry<K, V> e) {
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

	public Position<MyEntry<K, V>> addBefore(Position<MyEntry<K, V>> p, MyEntry<K, V> e) {
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
	public void add(MyEntry<K, V> e) {
		put(e.getKey(), e.getValue());	
	}

	
	@Override
	public V remove(K k) {
		Position<MyEntry<K, V>> a = search(k);
		if (compare.compare(k, a.getElement().getKey()) == 0) {
			return remover(a).getValue();
		}
		else return null;
	}
	
	private MyEntry<K, V> remover(Position<MyEntry<K, V>> p) {
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
	public MyEntry<K, V> remove() {
		throw new UnsupportedOperationException("remove");	
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
	public Iterable<K> keySet() {
		return new  Iterable<K>() {
			
			@Override
			public Iterator<K> iterator() {
				return new Iterator<K>() {
					
					Iterator<MyEntry<K, V>> entry = entrySet().iterator() ;
					
					@Override
					public boolean hasNext() {
						return entry.hasNext();
					}

					@Override
					public K next() {
						return entry.next().getKey();
					}
					
				};
			}			
		};
	}

	@Override
	public Iterable<V> values() {
		return new  Iterable<V>() {
			
			@Override
			public Iterator<V> iterator() {
				return new Iterator<V>() {
					
					Iterator<MyEntry<K, V>> entry = entrySet().iterator() ;
					
					@Override
					public boolean hasNext() {
						return entry.hasNext();
					}

					@Override
					public V next() {
						return entry.next().getValue();
					}
					
				};
			}			
		};
	}
	
	@Override
	public Iterable<MyEntry<K, V>> entrySet() {
		return new  Iterable<MyEntry<K, V>>() {
			
			@Override
			public Iterator<MyEntry<K, V>> iterator() {
				return new Iterator<MyEntry<K, V>>() {
					private PositionIterator iter  = new PositionIterator();

					@Override
					public boolean hasNext() {return iter.hasNext();}

					@Override
					public MyEntry<K, V> next() {
						return iter.next().getElement();
					}
				};
			}			
		};
	}

	
	
	private class Node implements Position<MyEntry<K, V>>{
		
		private MyEntry<K, V> e;
		private Node next;
		private Node prev;
		private Node above;
		private Node below;
		
		public Node (Node p, MyEntry<K, V> elem, Node n, Node a, Node b) {
			prev = p;
			e = elem;
			next = n;
			above = a;
			below = b;
		}

		@Override
		public MyEntry<K, V> getElement() {
			return e;
		}
		
	}

	private class PositionIterator implements Iterator<Position<MyEntry<K, V>>>{
		
		private Node walk = header.get(0);

		@Override
		public boolean hasNext() {return walk.next != trailer.get(0);}

		@Override
		public Position<MyEntry<K, V>> next() {
			if(!hasNext()) throw new NoSuchElementException();
			return walk = walk.next;
		}

	}
	

}
