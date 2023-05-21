package myDS.myTrees;

import java.util.Iterator;
import myDS.myCollections.Position;
import myDS.myLists.MyArrayList;
import myDS.myLists.MyList;
import myDS.mySnQ.LinkedQueue;


public class LinkedTree<E> implements MyTree<E> {
	
	private Node root;
	private int size;
	
	
	public LinkedTree(){
		size=0;
	}
	
	public LinkedTree(E e){
		root = new Node(null, e);
		size=1;
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
	public Position<E> root() {
		return (isEmpty()) ? null : root;
	}

	@Override
	public Position<E> parent(Position<E> p) {
		return validate(p).parent;
	}
	
	@Override
	public MyList<Position<E>> children(Position<E> p) {
		return validate(p).children;
	}
	
	@Override
	public int numChildren(Position<E> p) {
		return children(p).size();
	}

	@Override
	public boolean isInternal(Position<E> p) {
		return !isExternal(p);
	}

	@Override
	public boolean isExternal(Position<E> p) {
		return validate(p).children.size() == 0;
	}

	@Override
	public boolean isRoot(Position<E> p) {
		return p==root();
	}
	
	@Override
	public Iterator<E> iterator(){
		return new MyElementIterator(positions());
	}
	
	@Override
	public Iterator<E> reverseIterator() {
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
		preOrderTraversal(root(), nam);
		return nam;
	}
	
	private void preOrderTraversal(Position<E> p, MyList<Position<E>> snap) {
		snap.add(p);
		for (Position<E> i: children(p)) preOrderTraversal(i, snap);
	}
	
	public MyList<Position<E>> postOrderTraversal(Position<E> p) {
		MyArrayList<Position<E>> nam = new MyArrayList<Position<E>>();
		postOrderTraversal(root(), nam);
		return nam;
	}
	
	private void postOrderTraversal(Position<E> p, MyList<Position<E>> snap) {
		for (Position<E> i: children(p)) postOrderTraversal(i, snap);
		snap.add(p);
	}
	
	public MyList<Position<E>> breadthFirstTraversal(Position<E> p) {
		MyArrayList<Position<E>> nam = new MyArrayList<Position<E>>();
		LinkedQueue<Position<E>> fr = new LinkedQueue<Position<E>>();
		fr.add(p);
		while(!fr.isEmpty()) {
			Position<E> n = fr.remove();
			nam.add(n);
			for (Position<E> i: children(n)) fr.add(i);
		}
		return nam;
	}
	
	@Override
	public int depth(Position<E> p) {
		if(p == root()) return 0;
		return 1+depth(parent(p));
	}

	@Override
	public int height(Position<E> p) {
		if(isExternal(p)) return 0;
		int h=0;
		for (Position<E> i: children(p)) h = Math.max(h, 1+height(i));
		return h;
	}

	@Override 
	public E set(Position<E> p, E e) {
		E val = validate(p).e;
		validate(p).e = e;
		return  val;
	}
	
	@Override
	public Position<E> addRoot(E e) {
		if (root!=null) throw new IllegalStateException("root already exists");
		size++;
		return root = new Node(null, e);
	}

	public Position<E> addChild(Position<E> p, E e) {
		Node n = validate(p);
		Node c = new Node(n, e);
		n.children.add(c);
		size++;
		return c;
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
	public E remove(Position<E> p) {
		Node n = validate(p);
		if(isInternal(n)) throw new IllegalStateException("opereation can only be done on leaves");
		Node par  = validate(parent(p));
		if(par == null) root = null;
		par.children.remove(n);
		n.parent = n;
		size--;
		return p.getElement();

	}
	
	@Override
	public String toString() {
		String real = "";
		Iterator<E> walkA = iterator();
		while(walkA.hasNext()) real += (", " + walkA.next());
		return real;
	}
		
	private Node validate(Position<E> p){
		if(!(p instanceof Node)) throw new IllegalArgumentException("argument not valid");
		Node a = (Node)p;
		// this is a method for a making a position invalid when it is removed by setting its parent to itself
		if(a.parent == a) throw new IllegalStateException("Position is no longer valid");
		return a;
	}
	
	
	private class Node implements Position<E>{
		
		private E e;
		private Node parent;
		private MyArrayList<Position<E>> children;
		
		public Node (Node p, E elem) {
			parent = p;
			e = elem;
			children = new MyArrayList<Position<E>>();
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
