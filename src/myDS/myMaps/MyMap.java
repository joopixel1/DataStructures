package myDS.myMaps;

import myDS.myCollections.MyCollection;
import myDS.myCollections.MyEntry;

public interface MyMap<K, V> extends MyCollection<MyEntry<K, V>> {
	
	V get(K k);
	void put(K k, V v);
	V remove(K k);
	Iterable<K> keySet();
	Iterable<V> values();
	Iterable<MyEntry<K, V>> entrySet();
	
}
