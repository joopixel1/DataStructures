package sortingNsearching;

import java.util.Arrays;

public class SelectionSorter {
	
	
	public static void main(String[] args)
	{ 
		
		Integer[] a = ArrayUtil.randomIntArray(10, 100);
		System.out.println(Arrays.toString(a));
	
		sort(a);
		System.out.println(Arrays.toString(a));
	
	}

	
	
	public static <T extends Comparable<T>> void sort(T[] a) {
		
		for(int i=0; i < a.length; i++) {
			int minPos = ArrayUtil.minim(a, i, a.length-1);
			ArrayUtil.swap(a, i, minPos);
		}
		
	}
	

}
