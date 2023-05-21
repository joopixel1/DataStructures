package myDS.myMaps;

import java.util.Iterator;
import myDS.myCollections.MyEntry;
import myDS.myLists.MyArrayList;

public class UnSortedArrayMap<K, V> implements MyMap<K, V> {

	private MyArrayList<MyEntry<K, V>> list = new MyArrayList<MyEntry<K, V>>();
	
	
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
		int i = findIndex(e.getKey());
		if(i != -1) list.set(i, e);
		else list.add(e);
	}
	
	@Override
	public void put(K k, V v) {
		add(new MyEntry<K, V>(k, v));
	}

	
	private int findIndex(K key) {
		for(int i =0; i<list.size(); i++) {
			if(list.get(i).getKey().equals(key)) return i;
		}
		return -1;
	}

	@Override
	public V get(K k) {
		int i = findIndex(k);
		if(i == -1) return null;
		else return list.get(i).getValue();
	}


	@Override
	public V remove(K k) {
		int i = findIndex(k);
		if(i != -1) {
			V ans = list.get(i).getValue();
			list.remove(i);
			return ans;
		}
		else return null;
	}
	
	@Override
	public MyEntry<K, V> remove() {
		throw new UnsupportedOperationException("remove");	
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
		return new  Iterable<MyEntry<K, V>>() {
			
			@Override
			public Iterator<MyEntry<K, V>> iterator() {
				return list.iterator();
			}			
		};
	}

	
}
