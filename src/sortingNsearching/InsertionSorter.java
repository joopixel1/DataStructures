package sortingNsearching;

import java.util.Arrays;
import java.util.HashMap;

public class InsertionSorter {
	
	
	public static void main(String[] args)
	{ 
		
		Integer[] a = ArrayUtil.randomIntArray(10, 100);
		System.out.println(Arrays.toString(a));

	
		sort(a);
		System.out.println(Arrays.toString(a));
	
	}

	
	
	public static <T extends Comparable<T>> void sort(T[] a) {
		
		for(int i=0; i < a.length; i++) {
			
			T next = a[i];
			int j=i;
			while(j>0 && a[j-1].compareTo(next) > 0) {	
				a[j] = a[j-1];
				j--;
			}
			
			a[j] = next; //insert element		
		}
		
	}
	
}
