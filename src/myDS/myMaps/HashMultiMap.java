package myDS.myMaps;

import java.util.Iterator;
import myDS.myCollections.MyEntry;
import myDS.myLists.MyArrayList;
import myDS.myLists.MyList;

public class HashMultiMap<K, V> implements MyMultiMap<K, V> {

	HashBucketMap<K, MyList<V>> map = new HashBucketMap<>();
	int size = 0;
	
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	
	@Override
	public Iterable<V> get(K k) {
		return map.get(k);
	}

	@Override
	public int count(K k) {
		MyList<V> g = map.get(k);
		return (g == null) ? 0 : g.size();
	}
	
	
	@Override
	public void put(K k, V v) {
		MyList<V> g = map.get(k);
		if(g != null) g.add(v);
		else {
			MyArrayList<V> arr = new MyArrayList<V>();
			arr.add(v);
			map.add(new MyEntry<K, MyList<V>>(k, arr));
		}
		size++;
	}
	
	@Override
	public void add(MyEntry<K, V> e) {
		put(e.getKey(), e.getValue());
	}

	
	@Override
	public MyEntry<K, V> remove() {
		throw new UnsupportedOperationException("remove");	
	}

	@Override
	public void removeAll(K k) {
		size -= map.get(k).size();
		map.remove(k);
	}

	@Override
	public MyEntry<K, V> remove(K k, V v) {
		MyList<V> g = map.get(k);
		if(g == null) return null;
		else {
			MyArrayList<V> arr = new MyArrayList<V>();
			arr.add(v);
			for (int i=0; i<g.size(); i++) {
				if(g.get(i) == v) {
					size--;
					return new MyEntry<K, V>(k, g.remove(i));
				}
			}return null;
		}
	}
	

	@Override
	public Iterable<K> keySet() {
		return new  Iterable<K>() {
			
			@Override
			public Iterator<K> iterator() {
				return new Iterator<K>() {
					
					Iterator<MyEntry<K, V>> entry = entrySet().iterator() ;
					
					@Override
					public boolean hasNext() {
						return entry.hasNext();
					}

					@Override
					public K next() {
						return entry.next().getKey();
					}
					
				};
			}			
		};
	}

	@Override
	public Iterable<V> values() {
		return new  Iterable<V>() {
			
			@Override
			public Iterator<V> iterator() {
				return new Iterator<V>() {
					
					Iterator<MyEntry<K, V>> entry = entrySet().iterator() ;
					
					@Override
					public boolean hasNext() {
						return entry.hasNext();
					}

					@Override
					public V next() {
						return entry.next().getValue();
					}
					
				};
			}			
		};
	}
	
	@Override
	public Iterable<MyEntry<K, V>> entrySet() {
		MyArrayList<MyEntry<K, V>> ans =  new MyArrayList<MyEntry<K, V>>();
		for(MyEntry<K, MyList<V>> i: map.entrySet()) {
			for(V value: i.getValue()) ans.add(new MyEntry<K, V>(i.getKey(), value));
		}
		return ans;
	}

	
}
