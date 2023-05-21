package myDS.myCollections;

public class MyEntry<K, V> {
	
	
	K key;
	V value;
	
	
	public MyEntry(K k, V v) {
		key = k;
		value = v;
	}

	public V getValue() {
		return value;
	}
	
	public K getKey() {
		return key;		
	}
	
	public void setKey(K k) {
		key = k;
	}
	
	public void setValue(V v) {
		value = v;
	}
	
	@Override
	public String toString() {
		return "["+key+", "+value+"]";
	}
	
}
