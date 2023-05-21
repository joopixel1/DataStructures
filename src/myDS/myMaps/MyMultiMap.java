package myDS.myMaps;

import myDS.myCollections.MyCollection;
import myDS.myCollections.MyEntry;

public interface MyMultiMap<K, V> extends MyCollection<MyEntry<K, V>> {

	Iterable<V> get(K k);
	int count(K k);
	void put(K k, V v);
	MyEntry<K, V> remove(K k, V v);
	void removeAll(K k);
	
	Iterable<K> keySet();
	Iterable<V> values();
	Iterable<MyEntry<K, V>> entrySet();
	
	
}
