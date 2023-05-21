package myDS.myPQ;

import java.util.Comparator;
import java.util.NoSuchElementException;

import myDS.myCollections.MyEntry;
import myDS.myLists.PositionalList;

public class HeapPQ<K, V> implements MyPriorityQueue<K, V> {

	private MyHeap<K, V> list;
	
	public HeapPQ() {
		list = new MyHeap<K, V>();
	}
	
	public HeapPQ(Comparator<K> comp) {
		list = new MyHeap<K, V>(comp);
	}
	
	
	@Override
	public void enqueue(K k, V v) {
		list.add(new MyEntry<K, V>(k, v));		
	}

	@Override
	public MyEntry<K, V> peek() {
		if(isEmpty())throw new NoSuchElementException("empty");
		return list.top();
	}

	@Override
	public MyEntry<K, V> dequeue() {
		if(isEmpty())throw new NoSuchElementException("empty");
		return list.remove();
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
