package sortingNsearching;


import java.util.Arrays;

public class QuickSort {
	
	
	public static void main(String[] args)
	{ 
		
		Integer[] a = ArrayUtil.randomIntArray(10, 100);
		System.out.println(Arrays.toString(a));
	
		sort(a);
		System.out.println(Arrays.toString(a));
	
	}
	
	
	public static <T extends Comparable<T>> void sort(T[] a) {
		sort(a, 0, a.length-1);
	}
	
	
	public static <T extends Comparable<T>> void sort(T[] a, int i, int j) {
		
		if(i >= j) return;
		
		int partition = partition(a, i, j);
		sort(a, i, partition);
		sort(a, partition+1, j);
		
	}
	
	public static <T extends Comparable<T>> int partition(T[] arr, int i, int j) {
		
		
		T pivot = arr[i];       // u choose first element as partition.
		int from = i-1;
		int to = j+1;
		while(from<to) {
			from++; while(arr[from].compareTo(pivot) < 0) {from++;}
			to--; while(arr[to].compareTo(pivot) > 0) {to--;}
			
			if(from < to) { ArrayUtil.swap(arr, from, to); }
		}
		
		return to;		
	}

}
