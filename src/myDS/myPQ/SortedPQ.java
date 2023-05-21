package myDS.myPQ;

import java.util.Comparator;
import java.util.NoSuchElementException;

import myDS.myCollections.MyEntry;
import myDS.myCollections.Position;
import myDS.myLists.PositionalList;

public class SortedPQ<K,V> implements MyPriorityQueue<K, V> {

	private PositionalList<MyEntry<K, V>> list;
	private Comparator<K> compare;
	
	public SortedPQ() {
		this(new Comparator<K> () {
			@Override
			public int compare(K o1, K o2) {
				return ((Comparable<K>) o1).compareTo(o2);	
			}
		});
	}
	
	public SortedPQ(Comparator<K> comp) {
		list = new PositionalList<MyEntry<K, V>>();
		compare = comp;		
	}
	
	@Override
	public void enqueue(K k, V v) {
		addUsingInsertionSort(new MyEntry<K, V>(k, v));
	}

	@Override
	public MyEntry<K, V> peek() {
		if(isEmpty())throw new NoSuchElementException("empty");
		return list.first().getElement();
	}
	
	@Override
	public MyEntry<K, V> dequeue() {
		if(isEmpty())throw new NoSuchElementException("empty");
		return list.removeFirst();
	}
	
	private void addUsingInsertionSort(MyEntry<K, V> ent) {
		for(Position<MyEntry<K, V>> walk: list.positions()) {
			if(compare.compare(ent.getKey(), walk.getElement().getKey()) < 0) {
				list.addBefore(walk, ent);
				return;
			}
		}
		list.addLast(ent);
		
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	
}
