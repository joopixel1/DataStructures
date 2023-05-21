package myDS.fundamentalDS;

public class UsingFundamentalList {

	public static void main(String[] args) {
		
		//test my single linked Sequence
		testSinglyLinkedList();
		
		//test my single linked Sequence
		testDoublyLinkedList();
		
		
	}
	
	public static void testDoublyLinkedList() {
	    MyDoubleLinkedSequence<Integer> list = new MyDoubleLinkedSequence<Integer>();

	    // Add some elements to the list
	    list.addFirst(1);
	    list.addLast(2);
	    list.addLast(3);
	    list.addFirst(4);

	    // Print the list
	    System.out.println("Original List:");
	    System.out.println(list);

	    // Remove the first and last elements from the list
	    list.removeFirst();
	    list.removeLast();

	    // Print the modified list
	    System.out.println("Modified List:");
	    System.out.println(list);

	    // Insert a new element at a specific position in the list
	    list.addLast(5);

	    // Print the modified list again
	    System.out.println("Modified List after Insertion:");
	    System.out.println(list);

	    // Remove an element at a specific position in the list
	    list.removeFirst();

	    // Print the final list
	    System.out.println("Final List:");
	    System.out.println(list);
	}

	
	public static void testSinglyLinkedList() {
	    // Test 1: Verify if an empty linked list is correctly created
	    MySingleLinkedSequence<Integer> list = new MySingleLinkedSequence<Integer>();
	    assert list.size() == 0;
	    assert list.isEmpty() == true;

	    // Test 2: Verify if an element can be correctly added to the linked list
	    list.addFirst(1);
	    assert list.size() == 1;
	    System.out.println(list);
	    assert list.isEmpty() == false;

	    // Test 3: Verify if multiple elements can be added to the linked list
	    list.addFirst(2);
	    list.addLast(3);
	    assert list.size() == 3;
	    System.out.println(list);
	    assert list.isEmpty() == false;

	    // Test 4: Verify if an element can be correctly removed from the linked list
	    list.removeFirst();
	    assert list.size() == 2;
	    System.out.println(list);
	    assert list.isEmpty() == false;

	    // Test 5: Verify if an exception is thrown when trying to remove an element from an empty list
	    MySingleLinkedSequence<Integer> emptyList = new MySingleLinkedSequence<Integer>();
	    assert emptyList.removeFirst() == null;

	    // Test 6: Verify if an exception is thrown when trying to access an element from an empty list
        assert emptyList.first() == null; // The line above should have thrown an exception, so we shouldn't get here
	

	    // Test 7: Verify if an element can be correctly retrieved from the linked list
	    Integer element = list.last();
	    assert element == 3;

	}

}
