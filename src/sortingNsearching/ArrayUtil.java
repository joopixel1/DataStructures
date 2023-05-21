package sortingNsearching;

import java.util.Random;

public class ArrayUtil {
	
	
	private static Random gen = new Random();
	
	
	/**
	 * Creates an array filled with random values.
	 * @param length the length of the array
	 * @param n the number of possible random values
	 * @return an array filled with length numbers between
	 * 0 and n - 1
	 */
	public static Integer[] randomIntArray(int length, int n)
	{ 
	
		Integer[] a = new Integer[length];
		for (int i = 0; i < a.length; i++) {
			a[i] = gen.nextInt(n);
		}
		return a;
		
	}
	
	
	/**
	 * Swaps two entries of an array.
	 * @param a the array
	 * @param i the first position to swap
	 * @param j the second position to swap
	 */
	public static <T> void swap(T[] a, int i, int j)
	{

		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	
	}

	/**
	 * gets d position minimum of an array from pos i to j included.
	 * @param a the array
	 * @param i the first position to swap
	 * @param j the second position to swap
	 */
	public static <T extends Comparable<T>> int minim(T[] a, int i, int j) {
		
		if(i >= a.length || j >= a.length) throw new IllegalArgumentException("i or j grater than length of an array"); 

		int ans = i;
		for(int k=i+1; k<=j; k++) {
			if(a[ans].compareTo(a[k]) > 0) ans = k;
		}
		return ans;
		
	}
	
	
}
