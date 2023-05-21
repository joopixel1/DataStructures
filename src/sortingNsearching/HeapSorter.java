package sortingNsearching;

import java.lang.reflect.Array;
import java.util.Arrays;

import myDS.myCollections.MyEntry;
import myDS.myLists.MyArrayList;
import myDS.myPQ.MyHeap;

public class HeapSorter {

	
	public static void main(String[] args)
	{ 
		
		Integer[] a = ArrayUtil.randomIntArray(10, 100);
		MyArrayList<MyEntry<Integer, Integer>> arr = new MyArrayList<MyEntry<Integer, Integer>>();
		for(int i=0; i < a.length; i++) arr.add(new MyEntry<Integer, Integer>(a[i], a[i]));
		System.out.println(arr);

		sort(arr);
		System.out.println(arr);
	
	}

	
	
	public static <K extends Comparable<K>, V> void sort(MyArrayList<MyEntry<K, V>> arr) {
		
		MyHeap<K, V> heap = new MyHeap<K, V>(arr);
		System.out.println(heap);
		for(int i=0; i < arr.size(); i++) arr.set(i, heap.remove());
		
	}

}
