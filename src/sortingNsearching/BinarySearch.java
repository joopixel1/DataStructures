package sortingNsearching;

import java.util.Arrays;

public class BinarySearch {
	
	
	public static void main(String[] args)
	{ 
		
		Integer[] a = ArrayUtil.randomIntArray(10, 100);
		System.out.println(Arrays.toString(a));
		int val = a[0];

	
		QuickSort.sort(a);
		System.out.println(Arrays.toString(a));
		
		System.out.println("val " + val + ": " + binarySearch(a, val, 0, 9));
	
	}

	
	
	public static <T extends Comparable<T>> int binarySearch(T[] a, T val, int i, int j) {
		
		if(i >j) return -1;
		
		int mid = (i+j)/2;
		if(a[mid].compareTo(val) == 0) return mid;
		else if(a[mid].compareTo(val) > 0) return binarySearch(a, val, i, mid-1);
		else return binarySearch(a, val, mid+1, j);
		
	}
	
}