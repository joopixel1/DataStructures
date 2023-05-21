package sortingNsearching;

import java.util.Arrays;
import java.util.Random;

public class LinearSearch {
	
	
	public static void main(String[] args)
	{ 
		
		Integer[] a = ArrayUtil.randomIntArray(10, 100);
		System.out.println(Arrays.toString(a));
		int val = a[(new Random()).nextInt(10)];
		
		System.out.println("val " + val + ": " + linearSearch(a, val, 0, 9));
	
	}

	
	
	public static <T extends Comparable<T>> int linearSearch(T[] a, T val, int i, int j) {
		
		for(int k=i; k<j; k++) {
			if(val.compareTo(a[k]) == 0) return k;
		}
		return -1;
		
	}
	
}
