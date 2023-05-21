package myDS.myPQ;

import java.util.Comparator;
import myDS.myCollections.MyCollection;
import myDS.myCollections.MyEntry;
import myDS.myCollections.Position;
import myDS.myLists.MyArrayList;
import myDS.myLists.PositionalList;
import myDS.myTrees.ArrayBinaryTree;

public class MyHeap<K, V> implements MyCollection<MyEntry<K, V>> {

	private ArrayBinaryTree<MyEntry<K, V>> list;
	private Comparator<K> compare;
	
	
	public MyHeap() {
		this(new Comparator<K> () {
			@Override
			public int compare(K o1, K o2) {
				return ((Comparable<K>) o1).compareTo(o2);	
			}
		});
	}
	
	public MyHeap(Comparator<K> comp) {
		list = new ArrayBinaryTree<MyEntry<K, V>>();
		compare = comp;		
	}
	
	public MyHeap(MyArrayList<MyEntry<K, V>> a) {
		this(a, new Comparator<K> () {
			@Override
			public int compare(K o1, K o2) {
				return ((Comparable<K>) o1).compareTo(o2);	
			}
		});		
	}
	
	public MyHeap(MyArrayList<MyEntry<K, V>> a, Comparator<K> comp) {
		list = new ArrayBinaryTree<MyEntry<K, V>>();
		compare = comp;	
		buttomUpHeapConstronction(a);
	}
	
	private void buttomUpHeapConstronction(MyArrayList<MyEntry<K, V>> a){
		for (int i=0; i<a.size(); i++) list.add(a.get(i));
		for (int i=list.size()-1; i>=0; i--) 
			bubbleDown(list.get(i));
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void add(MyEntry<K, V> e) {
		bubbleUp(list.addP(e));
	}

	private void bubbleUp(Position<MyEntry<K, V>> pos) {
		Position<MyEntry<K, V>> p = pos;
		Position<MyEntry<K, V>> par = list.parent(p);
		while(par != null && compare.compare(p.getElement().getKey(), par.getElement().getKey()) < 0) {
			swap(p, par);
			p = par;
			par = list.parent(par);
		}
	}

	private void swap(Position<MyEntry<K, V>> pos1, Position<MyEntry<K, V>> pos2) {
		MyEntry<K, V> mine = pos1.getElement();
		list.set(pos1, pos2.getElement());
		list.set(pos2, mine);
	}

	@Override
	public MyEntry<K, V> remove() {
		if(list.isEmpty()) return null;
		else if (list.size() == 1) return list.remove();
		else {
			MyEntry<K, V> rem = list.set(list.root(), list.remove());
			if(!list.isEmpty()) bubbleDown(list.root());
			return rem;
		}
	}

	private void bubbleDown(Position<MyEntry<K, V>> pos) {
		Position<MyEntry<K, V>> p = pos;
		Position<MyEntry<K, V>> left = list.left(p);
		Position<MyEntry<K, V>> right = list.right(p);
		Position<MyEntry<K, V>> chi;		
		if(left == null && right == null) chi = null;
		else if(left == null) chi = right;
		else if(right == null) chi = left;
		else chi = (compare.compare(left.getElement().getKey(), right.getElement().getKey()) <= 0) ? left : right;
		
		while(chi != null && compare.compare(p.getElement().getKey(), chi.getElement().getKey()) > 0) {
			swap(p, chi);
			p = chi;
			
			left = list.left(p);
			right = list.right(p);
			if(left == null && right == null) chi = null;
			else if(left == null) chi = right;
			else if(right == null) chi = left;
			else chi = (compare.compare(left.getElement().getKey(), right.getElement().getKey()) <= 0) ? left : right;	
		}
	}

	
	public MyEntry<K, V> top() {
		return list.root().getElement();
	}
	
	@Override
	public String toString() {
		//TODO not fully functional
		String real = "";
		for(int i=0; i<list.size(); i++) {
			real += (", " + list.get(i).getElement());
		}
		return real;
	}

}
