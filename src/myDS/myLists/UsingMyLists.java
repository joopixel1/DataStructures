package myDS.myLists;

import java.util.Arrays;
import java.util.Collections;

import myDS.myCollections.Position;

public class UsingMyLists {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testMyArrayList();
		removeEvenNumber();
		
		//test my positionalList
		testPositionalList();

	}
	
	private static void testMyArrayList() {
		MyArrayList<String> list = new MyArrayList<String>();
		list.add("apple");
		list.add("banana");
		list.add("cherry");
		list.remove(1); // removes "banana" from the list
		String firstElement = list.get(0); // returns "apple"
		
		for (int i = 0; i < list.size(); i++) {
		    String element = list.get(i);
		    System.out.println(element);
		}
		
		//for lkater
		for (String element : list) {
		    System.out.println(element);
		}
		
		Object[] arr = list.toArray(new String[list.size()]);
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		


		
	}

	public static void removeEvenNumber() {
        // Create an ArrayList of integers
        MyArrayList<Integer> numbers = new MyArrayList<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        numbers.add(7);
        numbers.add(8);
        numbers.add(9);
        numbers.add(10);

        // Create a new ArrayList to store the odd numbers
        MyArrayList<Integer> oddNumbers = new MyArrayList<Integer>();

        // Iterate over the original ArrayList and add all the odd numbers to the new ArrayList
        for (int i = 0; i < numbers.size(); i++) {
            int number = numbers.get(i);
            if (number % 2 != 0) {
                oddNumbers.add(number);
            }
        }

        // Print the resulting list of odd numbers
        System.out.println(oddNumbers);
    }

	public static void testPositionalList() {
	    PositionalList<Integer> list = new PositionalList<Integer>();

	    // Add some elements to the list
	    Position<Integer> firstPosition = list.addFirst(1);
	    Position<Integer> lastPosition = list.addLast(2);
	    Position<Integer> middlePosition = list.addAfter(firstPosition, 3);

	    // Print the list
	    System.out.println("Original List:");
//	    for (Integer element : list) {
//	        System.out.print(element + " ");
//	    }
	    System.out.println(list);

	    // Replace an element at a specific position in the list
	    list.set(middlePosition, 4);
	    System.out.println(list);
	    //test my sorting methos using insertion sort
	    sortPositionalList(list);

	    // Print the modified list
	    System.out.println("Modified List:");
//	    for (Integer element : list) {
//	        System.out.print(element + " ");
//	    }
	    System.out.println(list);

	    // Remove an element at a specific position in the list
	    list.remove(lastPosition);

	    // Print the final list
	    System.out.println("Final List:");
//	    for (Integer element : list) {
//	        System.out.print(element + " ");
//	    }
	    System.out.println(list);
	    
	    
	}

	private static <E extends Comparable<E>> void sortPositionalList(PositionalList<E> list) {
		for(Position<E> i=list.first(); i!=list.last();) {
			i = list.after(i);
			E elem = i.getElement();
			Position<E> j = i;
			while(list.before(j).getElement().compareTo(elem) > 0 && j!=list.first()) {
				list.set(j, list.before(j).getElement());
				j = list.before(j);
			}
			list.set(j, elem);
		}
		
	}

}
