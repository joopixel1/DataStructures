package sortingNsearching;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort {
	
	
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
		
		if(j-i < 1) return;
		
		int mid = (i+j)/2;
		sort(a, i, mid);
		sort(a, mid+1, j);
		merge(a, i, mid, mid+1, j);
		
	}
	
	public static <T extends Comparable<T>> void merge(T[] a, int arr1begin, int arr1end, int arr2begin, int arr2end) {
		
		int start = arr1begin;
		ArrayList<T> sol = new ArrayList<T>();
		int len = arr2end-arr1begin+1;
		
		int i=0;
		while (i < len) {
			if(arr1begin > arr1end) {
				sol.add(a[arr2begin]);
				arr2begin++;
			}
			else if(arr2begin > arr2end) {
				sol.add(a[arr1begin]);
				arr1begin++;
			}
			else if(a[arr1begin].compareTo(a[arr2begin]) < 0) {
				sol.add(a[arr1begin]);
				arr1begin++;
			}
			else {
				sol.add(a[arr2begin]);
				arr2begin++;
			}
			i++;
		}
		
		for(i=0; i<len; i++, start++) a[start] = sol.get(i);
		
	}

}
