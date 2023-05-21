package myDS.myPQ;

import myDS.myCollections.MyCollection;
import myDS.myCollections.MyEntry;

public interface MyPriorityQueue<K, V> {
	
	void enqueue(K k, V v);
	MyEntry<K, V> peek();
	MyEntry<K, V> dequeue();
	int size();
	boolean isEmpty();
	
}
