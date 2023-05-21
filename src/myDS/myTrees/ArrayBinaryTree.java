package myDS.myTrees;


import java.util.Arrays;
import java.util.Iterator;


import myDS.myCollections.Position;
import myDS.myLists.MyArrayList;
import myDS.myLists.MyList;
import myDS.mySnQ.LinkedQueue;


public class ArrayBinaryTree<E> implements BinaryTree<E> {

	int size;	
	private Object[] tree;
	
	
	public ArrayBinaryTree(){
		this(null);
	}
	
	public ArrayBinaryTree(E e){
		tree = new Object[127];
		size =0;
		if(e != null) tree[size++] = new Node(e, size);
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
	public boolean isInternal(Position<E> p) {
		return !isExternal(p);
	}

	@Override
	public boolean isExternal(Position<E> p) {
		return (left(p) == null && right(p) == null);
	}

	@Override
	public boolean isRoot(Position<E> p) {
		return p == tree[0];
	}
	
	@Override
	public Position<E> root() {
		return (Position<E>) tree[0];
	}

	@Override
	public Position<E> parent(Position<E> p) {
		int i = validate(p);
		return (i == 0) ? null : (Position<E>) tree[(i-1)/2];	
	}
	
	@Override
	public Position<E> left(Position<E> p) {
		int i = validate(p);
		return (i*2+1 >= tree.length) ? null : (Position<E>) tree[i*2+1];
	}

	@Override
	public Position<E> right(Position<E> p) {
		int i = validate(p);
		return (i*2+2 >= tree.length) ? null : (Position<E>) tree[i*2+2];
	}

	@Override
	public Position<E> sibling(Position<E> p) {
		Position<E> par  = parent(p);
		if(par == null) return null;
		return (left(par) == p) ? right(p) : left(p);
	}

	@Override
	public MyList<Position<E>> children(Position<E> p) {
		MyArrayList<Position<E>> li = new MyArrayList<Position<E>>();
		if(left(p) != null) li.add(left(p));
		if(right(p) != null) li.add(right(p));
		return li;	
	}
	
	@Override
	public int numChildren(Position<E> p) {
		return children(p).size();
	}

	@Override
	public int depth(Position<E> p) {
		if(p == root()) return 0;
		return 1+depth(parent(p));
	}

	@Override
	public int height(Position<E> p) {
		if(isExternal(p)) return 0;
		return Math.max(1+height(left(p)), 1+height(right(p)));
	}
	
	public Position<E> get(int i) {
		if(i<0 || i>=tree.length) throw new IndexOutOfBoundsException();
		return (Position<E>) tree[i];
	}
	
	@Override
	public Iterator<E> iterator() {
		return new MyElementIterator(positions());
	}
	
	@Override
	public Iterator<E> reverseIterator(){
		return new MyElementIterator(reversePositions());
	}
	
	@Override
	public Iterator<E> breadthFirstIterator() {
		return new MyElementIterator(breadthFirstPositions());
	}
	
	@Override
	public Iterator<Position<E>> positions() {
		return preOrderTraversal(root()).iterator();
	}
	
	@Override
	public Iterator<Position<E>> reversePositions() {
		return postOrderTraversal(root()).iterator();
	}

	@Override
	public Iterator<Position<E>> breadthFirstPositions() {
		return breadthFirstTraversal(root()).iterator();
	}
	
	public MyList<Position<E>> preOrderTraversal(Position<E> p) {
		MyArrayList<Position<E>> nam = new MyArrayList<Position<E>>();
		preOrderTraversal(p, nam);
		return nam;
	}
	
	private void preOrderTraversal(Position<E> p, MyList<Position<E>> snap) {
		snap.add(p);
		if(left(p) != null)preOrderTraversal(left(p), snap);
		if(right(p) != null)preOrderTraversal(right(p), snap);
	}
	
	public MyList<Position<E>> postOrderTraversal(Position<E> p) {
		MyArrayList<Position<E>> nam = new MyArrayList<Position<E>>();
		postOrderTraversal(p, nam);
		return nam;
	}
	
	private void postOrderTraversal(Position<E> p, MyList<Position<E>> snap) {
		if(left(p) != null)postOrderTraversal(left(p), snap);
		if(right(p) != null)postOrderTraversal(right(p), snap);
		snap.add(p);
	}
	
	public MyList<Position<E>> breadthFirstTraversal(Position<E> p) {
		MyArrayList<Position<E>> nam = new MyArrayList<Position<E>>();
		LinkedQueue<Position<E>> fr = new LinkedQueue<Position<E>>();
		fr.add(p);
		while(!fr.isEmpty()) {
			Position<E> n = fr.remove();
			nam.add(n);
			if(left(n) != null)fr.add(left(n));
			if(right(n) != null)fr.add(right(n));
		}
		return nam;
	}
	
	
	@Override
	public Position<E> addRoot(E e) {
		if (root() != null && size!=0) throw new IllegalStateException("root already exists");
		return (Position<E>) (tree[size++] = new Node(e, 0));
	}

	@Override 
	public E set(Position<E> p, E e) {
		E val = ((Node) tree[validate(p)]).e;
		((Node) tree[validate(p)]).e = e;
		return val;
	}

	@Override
	public Position<E> addLeft(Position<E> p, E e) {
		int i = validate(p)*2+1;
		if(i >= tree.length) expand();
		if (tree[i] !=null) throw new IllegalStateException("already has left element");
		size++;
		return (Position<E>) (tree[i] = new Node(e, i));
	}

	@Override
	public Position<E> addRight(Position<E> p, E e) {
		int i = validate(p)*2+2;
		if(i >= tree.length) expand();
		if (tree[i] !=null) throw new IllegalStateException("already has left element");
		size++;
		return (Position<E>) (tree[i] = new Node(e, i));
	}

	private void expand() {
		tree = Arrays.copyOf(tree, (tree.length+1)*4-1);	
	}
	
	@Override
	public E remove(Position<E> p) {
		int i = validate(p);
		if(isInternal(p)) throw new IllegalStateException("opereation can only be done on leaves");
		tree[i] = null;
		((Node) p).index = -1;
		return p.getElement();

	}

	public E[] toArray() {
		E[] lem = (E[]) new Object[tree.length];
		for(int i=0; i<tree.length; i++) lem[i] = ((Node) tree[i]).getElement();
;		return lem;
	}
	
	@Override
	public void add(E e) {
		//tree[size++] = new Node(e, size-1);  // wont work does the increment even before the expression is calculated
		tree[size] = new Node(e, size);
		size++;
	}
	
	public Position<E> addP(E e) {
		int n = size;
		size++;
		return (Position<E>) (tree[n] = new Node(e, n));
	}

	@Override
	public E remove() {
		size--;
		((Node)tree[size]).index = -1;
		E e = ((Node) tree[size]).getElement();
		tree[size] = null;
		return e;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(toArray());
	}
	
	private int validate(Position<E> p){
		if(!(p instanceof Node)) throw new IllegalArgumentException("argument not valid");
		Node a = (Node)p;
		// this is a method for a making a position invalid when it is removed by setting its index to -1
		if(a.index == -1) throw new IllegalStateException("Position is no longer valid");
		return a.index;
	}
	
	private class Node implements Position<E>{
		
		private E e;
		private int index;
		
		public Node (E elem, int i) {
			e = elem;
			index =i;
		}

		@Override
		public E getElement() {
			return e;
		}
	}
	
	private class MyElementIterator implements Iterator<E> {
		
		Iterator<Position<E>> posIter;
		
		private MyElementIterator(Iterator<Position<E>> iter)
		{
			posIter = iter;
		}
		
		@Override
		public boolean hasNext() {
			return posIter.hasNext();
		}

		@Override
		public E next() {
			return posIter.next().getElement();
		}
		
	}

	
}
