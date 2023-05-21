package myDS.searchTrees;


import java.util.Iterator;

import myDS.myCollections.MyEntry;
import myDS.myCollections.Position;
import myDS.myLists.MyArrayList;
import myDS.myLists.MyList;


public class BinarySearchTree<K, V> extends AbstractSearchTree<K, V>{

	
	@Override
	public V get(K k) {
		MyEntry<K, V> a = getter(k).getElement();
		return(compare.compare(k, a.getKey()) == 0) ? a.getValue() : null;
	}
	
	protected Position<MyEntry<K, V>> getter(K k){
		return binaryTreeSearch(k, this.root());
	}

	@Override
	public void put(K k, V v) {
		putter(k, v);
	}
	
	protected Position<MyEntry<K, V>> putter(K k, V v){
		if(this.isEmpty()) this.addRoot(new MyEntry<K, V>(k, v));
		
		Position<MyEntry<K, V>> a = binaryTreeSearch(k, this.root());
		if (compare.compare(k, a.getElement().getKey()) == 0) {
			a.getElement().setValue(v);
			return a;
		}
		else if (compare.compare(k, a.getElement().getKey()) < 0)
			return this.addLeft(a, new MyEntry<K, V>(k, v));
		else
			return this.addRight(a, new MyEntry<K, V>(k, v));
	}

	@Override
	public V remove(K k) {
		Position<MyEntry<K, V>> a = getter(k);
		V ans =  null;
		if (compare.compare(k, a.getElement().getKey()) == 0) {
			ans = a.getElement().getValue();
			remover(a);
		}
		return ans;		
	}
	
	protected Position<MyEntry<K, V>> remover(Position<MyEntry<K, V>> a) {
		Position<MyEntry<K, V>> ans = this.parent(a);
			
		if(this.isExternal(a))
			this.remove(a);
		else if(this.numChildren(a) == 2) {
			Position<MyEntry<K, V>> b = predecessor(a);
			swap(a, b);
			remove(b);
		}
		else if(this.left(a) == null) {
			if(this.right(this.parent(a)) == a) this.addRight(this.parent(a), this.right(a));
			else this.addLeft(this.parent(a), this.right(a));
		}
		else {
			if(this.right(this.parent(a)) == a) this.addRight(this.parent(a), this.left(a));
			else this.addLeft(this.parent(a), this.left(a));
		}
		
		return ans;
	}
	
	public MyEntry<K, V> minimum() {
		Position<MyEntry<K, V>> ans = this.root();
		while (this.left(ans) != null) ans = this.left(ans);
		return ans.getElement();
	}
	
	public MyEntry<K, V> maximum() {
		Position<MyEntry<K, V>> ans = this.root();
		while (this.right(ans) != null) ans = this.right(ans);
		return ans.getElement();
	}
	
	
	private Position<MyEntry<K, V>> binaryTreeSearch(K k, Position<MyEntry<K, V>> r){
		if(this.isExternal(r))
			return r;
		else if (compare.compare(k, r.getElement().getKey()) == 0)
			return r;
		else if (compare.compare(k, r.getElement().getKey()) < 0)
			return (this.left(r) == null) ? r: binaryTreeSearch(k, this.left(r));
		else
			return (this.right(r) == null) ? r: binaryTreeSearch(k, this.right(r));
	}
	
	private Position<MyEntry<K, V>> predecessor(Position<MyEntry<K, V>> a) {
		Position<MyEntry<K, V>> ans = this.left(a);
		while(this.right(ans) != null) ans = this.right(ans); 
		return ans;
	}
	
	private void swap(Position<MyEntry<K, V>> pos1, Position<MyEntry<K, V>> pos2) {
		MyEntry<K, V> mine = pos1.getElement();
		this.set(pos1, pos2.getElement());
		this.set(pos2, mine);
	}
	
	

	public Iterator<MyEntry<K, V>> inorderIterator(){
		return new MyElementIterator(positions());
	}
	
	public Iterator<Position<MyEntry<K, V>>> inorderPositions() {
		return inOrderTraversal(root()).iterator();
	}
	
	public MyList<Position<MyEntry<K, V>>> inOrderTraversal(Position<MyEntry<K, V>> p) {
		MyArrayList<Position<MyEntry<K, V>>> nam = new MyArrayList<Position<MyEntry<K, V>>>();
		inOrderTraversal(p, nam);
		return nam;
	}
	
	private void inOrderTraversal(Position<MyEntry<K, V>> p, MyList<Position<MyEntry<K, V>>> snap) {	
		if(left(p) != null)inOrderTraversal(left(p), snap);
		snap.add(p);
		if(right(p) != null)inOrderTraversal(right(p), snap);
	}
	
	
	private class MyElementIterator implements Iterator<MyEntry<K, V>> {
		
		Iterator<Position<MyEntry<K, V>>> posIter;
		
		private MyElementIterator(Iterator<Position<MyEntry<K, V>>> iter)
		{
			posIter = iter;
		}
		
		@Override
		public boolean hasNext() {
			return posIter.hasNext();
		}

		@Override
		public MyEntry<K, V> next() {
			return posIter.next().getElement();
		}
		
	}
	

}
