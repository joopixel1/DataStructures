package myDS.myMaps;

import java.util.Comparator;
import java.util.Iterator;
import myDS.myCollections.MyEntry;
import myDS.myLists.MyArrayList;

public class SortedArrayMap<K, V> implements MyMap<K, V> {
	
	
	private MyArrayList<MyEntry<K, V>> list;
	private Comparator<K> compare;
	
	
	public SortedArrayMap() {
		this(new Comparator<K> () {
			@Override
			public int compare(K o1, K o2) {
				return ((Comparable<K>) o1).compareTo(o2);	
			}
		});
	}
	
	public SortedArrayMap(Comparator<K> comp) {
		list = new MyArrayList<MyEntry<K, V>>();
		compare = comp;		
	}
	
	
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
		int i = binarySearch(e.getKey(), 0, list.size()-1);
		if(i == -1) addUsingInsertionSort(e);
		else list.set(i, e);
	}
	
	@Override
	public void put(K k, V v) {
		add(new MyEntry<K, V>(k, v));
	}
	
	private void addUsingInsertionSort(MyEntry<K, V> ent) {
		
		int i=list.size();
		list.add(ent);
		MyEntry<K, V> next = list.get(i);
		int j=i;
		while(j>0 && compare.compare(list.get(j-1).getKey(), next.getKey()) > 0) {	
				list.set(j, list.get(j-1));
				j--;
		}
			
		list.set(j, next); //insert element		
	}


	@Override
	public V get(K k) {
		int i = binarySearch(k, 0, list.size()-1);
		if(i == -1) return null;
		else return list.get(i).getValue();
	}
	
	public int binarySearch(K val, int i, int j) {
		
		if(i >j) return -1;
		
		int mid = (i+j)/2;
		if(compare.compare(list.get(mid).getKey(), val) == 0) return mid;
		else if(compare.compare(list.get(mid).getKey(), val) > 0) return binarySearch(val, i, mid-1);
		else return binarySearch(val, mid+1, j);
		
	}


	@Override
	public V remove(K k) {
		int i = binarySearch(k, 0, list.size()-1);
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
