package myDS.myPQ;

import java.util.NoSuchElementException;

public class UsingPQ {

	public static void main(String[] args) {
		UnSortedPQ<Integer, Integer> pq = new UnSortedPQ<Integer, Integer>();
		testPriorityQueue(pq);
		
		SortedPQ<Integer, Integer> pq2 = new SortedPQ<Integer, Integer>();
		testPriorityQueue(pq2);
		
		HeapPQ<Integer, Integer> pq3 = new HeapPQ<Integer, Integer>();
		testPriorityQueue(pq3);		

	}
	
	public static void testPriorityQueue(MyPriorityQueue<Integer, Integer> pq) {

        // Test enqueue
        pq.enqueue(20, 20);
        pq.enqueue(10, 10);
        pq.enqueue(30, 30);
        pq.enqueue(40, 40);
        pq.enqueue(5, 5);

        // Test size
        assert pq.size() == 5;

        // Test peek
        assert pq.peek().getKey() == 5;

        // Test dequeue
        assert pq.dequeue().getKey() == 5;
        assert pq.dequeue().getValue() == 10;

        // Test size after dequeue
        assert pq.size() == 3;

        // Test clear
        while(!pq.isEmpty()) pq.dequeue();
        assert pq.isEmpty();
        assert pq.size() == 0;

        // Test exceptions
        try {
            pq.dequeue();
            assert false; // Should not reach this line
        } catch (NoSuchElementException e) {
            // Expected exception, do nothing
        }

        try {
            pq.peek();
            assert false; // Should not reach this line
        } catch (NoSuchElementException e) {
            // Expected exception, do nothing
        }
    }

}
