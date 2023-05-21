package myDS.searchTrees;

public interface searchTrees<K, V> {
	
	V get(K k);
	void put(K k, V v);
	V remove(K k);

}
