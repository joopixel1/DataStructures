package myDS.myMaps;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import myDS.myCollections.MyCollection;
import myDS.myCollections.MyEntry;
import myDS.myLists.MyArrayList;

public class HashBucketMap<K, V> implements MyMap<K, V> {

	
	int SIZE = 100;
	Object[] list = new Object[SIZE];
	int s;
	
	
	@Override
	public int size() {
		return s;
	}

	@Override
	public boolean isEmpty() {
		return s==0;
	}

	
	@Override
	public void add(MyEntry<K, V> e) {
		put(e.getKey(), e.getValue());
	}
	
	@Override
	public void put(K k, V v) {
		int h = Math.abs(k.hashCode()%SIZE);
		int i = findIndex(k, h);
		if(i == -1) {
			if(list[h] == null) list[h] = new MyArrayList<MyEntry<K,V>>();
			((MyArrayList<MyEntry<K,V>>) list[h]).add(new MyEntry<K, V>(k, v));
			s++;
		}
		else ((MyArrayList<MyEntry<K,V>>) list[h]).get(i).setValue(v);
	}


	@Override
	public V get(K k) {
		int h = Math.abs(k.hashCode()%SIZE);
		int i = findIndex(k, h);
		if(i == -1) return null;
		else return ((MyArrayList<MyEntry<K,V>>) list[h]).get(i).getValue();
	}

	private int findIndex(K key, int h) {
		MyArrayList<MyEntry<K, V>> m = (MyArrayList<MyEntry<K, V>>) list[h];
		if(m == null) return -1;
		for(int i =0; i<m.size(); i++) {
			if(m.get(i).getKey().equals(key)) return i;
		}
		return -1;
	}
	
	
	@Override
	public V remove(K k) {
		int h = Math.abs(k.hashCode()%SIZE);
		int i = findIndex(k, h);
		if(i != -1) {
			V ans = ((MyArrayList<MyEntry<K,V>>) list[h]).get(i).getValue();
			((MyArrayList<MyEntry<K,V>>) list[h]).remove(i);
			s--;
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
				return new EntryIterator();
			}			
		};
	}

	
	private class EntryIterator implements Iterator<MyEntry<K, V>>{

		Iterator<Object> entry = Arrays.asList(list).iterator();
		Iterator<MyEntry<K, V>> en;

		@Override
		public boolean hasNext() {
			//one of the reasons why java should just be replaced by kotlin. This is one of d problem that kotlin solvees. Problems os null crying emoji
			while((en == null) ? (en == null  && entry.hasNext()) : (!en.hasNext() && entry.hasNext())) {
				MyArrayList<MyEntry<K,V>> li = (MyArrayList<MyEntry<K, V>>) entry.next();
				en = (li == null) ? null : li.iterator();
			}
			return (en == null) ? false : en.hasNext();
		}

		@Override
		public MyEntry<K, V> next() {
			if(!hasNext()) throw new NoSuchElementException();
			return en.next();
		}

	}

	
}
