package myDS.myTrees;


import java.util.Iterator;
import myDS.myCollections.Position;
import myDS.myLists.MyArrayList;
import myDS.myLists.MyList;
import myDS.mySnQ.LinkedQueue;


public class LinkedBinaryTree<E> implements BinaryTree<E> {
	
	private Node root;
	private int size;
	
	
	public LinkedBinaryTree(){
		size=0;
	}
	
	public LinkedBinaryTree(E e){
		root = new Node(null, e, null, null);
		size=1;
	}
	
	
	public LinkedBinaryTree(Position<E> p) {
		root = validate(p);
		size = 1;
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
		Node a = validate(p);
		MyArrayList<Position<E>> li = new MyArrayList<Position<E>>();
		if(a.left != null) li.add(a.left);
		if(a.right != null) li.add(a.right);
		return li;	
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
		return (validate(p).left == null && validate(p).right == null);
	}

	@Override
	public boolean isRoot(Position<E> p) {
		return p==root();
	}
	
	@Override
	public Position<E> left(Position<E> p) {
		return validate(p).left;
	}

	@Override
	public Position<E> right(Position<E> p) {
		return validate(p).right;
	}

	@Override
	public Position<E> sibling(Position<E> p) {
		Position<E> par  = parent(p);
		if(par == null) return null;
		return (left(par) == p) ? right(p) : left(p);
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
	
	public LinkedBinaryTree<E> subTree(Position<E> p) {
		LinkedBinaryTree<E> sub = new LinkedBinaryTree<E>(p);
		return sub;		
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
		if (root!=null) throw new IllegalStateException("root already exists");
		size++;
		return root = new Node(null, e, null, null);
	}

	@Override 
	public E set(Position<E> p, E e) {
		E val = validate(p).e;
		validate(p).e = e;
		return  val;
	}
	
	public Position<E> setRoot(Position<E> p) {
		Node n = validate(p);
		Node par  = validate(parent(p));
		n.parent = null;
		return root = n;
	}

	@Override
	public Position<E> addLeft(Position<E> p, E e) {
		Node n = validate(p);
		if (n.left!=null) throw new IllegalStateException("already has left element");
		size++;
		return n.left = new Node(n, e, null, null);
	}

	@Override
	public Position<E> addRight(Position<E> p, E e) {
		Node n = validate(p);
		if (n.right!=null) throw new IllegalStateException("already has left element");
		size++;
		return n.right = new Node(n, e, null, null);
	}
	
	public void addLeft(Position<E> p, Position<E> q) {
		Node n = validate(p);
		Node o = (Node)q;
		n.left = o;
		if(o != null) o.parent = n;
	}

	public void addRight(Position<E> p, Position<E> q) {
		Node n = validate(p);
		Node o = (Node)q;
		n.right = o;
		if(o != null) o.parent = n;
	}
	
	@Override
	public E remove(Position<E> p) {
		Node n = validate(p);
		if(isInternal(n)) throw new IllegalStateException("opereation can only be done on leaves");
		Node par  = validate(parent(p));
		if(par == null) root = null;
		if (left(par) == p) par.left = null ;
		else par.right = null;
		n.parent = n;
		size--;
		return p.getElement();

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
	
	public Position<E> attach(Position<E> p, BinaryTree<E> T1, BinaryTree<E> T2) {
		if(isInternal(p)) throw new IllegalStateException("opereation can only be done on leaves");
		Node n = validate(p);
		n.left = validate(T1.root());
		n.right = validate(T2.root());
		T1.addRoot(null);
		T2.addRoot(null);
		return n;
	}
	
	private class Node implements Position<E>{
		
		private E e;
		private Node parent;
		private Node left;
		private Node right;
		
		public Node (Node p, E elem, Node l, Node r) {
			parent = p;
			e = elem;
			left = l;
			right = r;
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
