package myDS.searchTrees;

import myDS.myCollections.MyEntry;
import myDS.myCollections.Position;

public class UsingSearchTrees {

	public static void main(String[] args) {
		// testing splay tree
		testSplayTree();
		
		// testing binary search tree
		testBinarySearchTree();

	}
	
	public static void testSplayTree() {
	    SplayTree<Integer, Integer> tree = new SplayTree<Integer, Integer>();

	    // Add nodes to the splay tree
	    tree.put(5, 5);
	    tree.put(3, 3);
	    tree.put(7, 7);
	    tree.put(2, 2);
	    tree.put(4, 4);
	    tree.put(6, 6);
	    tree.put(8, 8);

	    // Test tree traversal methods
	    System.out.println("In-order traversal:");
	    for(Position<MyEntry<Integer, Integer>> i: tree.inOrderTraversal(tree.root())) System.out.println(i.getElement());


	    System.out.println("Pre-order traversal:");
	    for(Position<MyEntry<Integer, Integer>> i: tree.preOrderTraversal(tree.root())) System.out.println(i.getElement());

	    System.out.println("Post-order traversal:");
	    for(Position<MyEntry<Integer, Integer>> i: tree.postOrderTraversal(tree.root())) System.out.println(i.getElement());


	 // Test tree search method
	    System.out.println("Search for value 4: " + tree.get(4));
	    for(Position<MyEntry<Integer, Integer>> i: tree.inOrderTraversal(tree.root())) System.out.println(i.getElement());
	    System.out.println("Search for value 9: " + tree.get(9));
	    for(Position<MyEntry<Integer, Integer>> i: tree.inOrderTraversal(tree.root())) System.out.println(i.getElement());


	    // Test tree removal method
	    System.out.println("Removing value 3");
	    tree.remove(3);
	    System.out.println("In-order traversal after removal:");
	    for(Position<MyEntry<Integer, Integer>> i: tree.inOrderTraversal(tree.root())) System.out.println(i.getElement());

	    
	    // Test tree minimum and maximum methods
	    System.out.println("Minimum value: " + tree.minimum());
	    System.out.println("Maximum value: " + tree.maximum());

	}


	public static void testBinarySearchTree() {
	    BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();

	    // Add nodes to the binary search tree
	    tree.put(5, 5);
	    tree.put(3, 3);
	    tree.put(7, 7);
	    tree.put(2, 2);
	    tree.put(4, 4);
	    tree.put(6, 6);
	    tree.put(8, 8);
	    


	    // Test tree traversal methods
	    System.out.println("In-order traversal:");
	    for(Position<MyEntry<Integer, Integer>> i: tree.inOrderTraversal(tree.root())) System.out.println(i.getElement());


	    System.out.println("Pre-order traversal:");
	    for(Position<MyEntry<Integer, Integer>> i: tree.preOrderTraversal(tree.root())) System.out.println(i.getElement());

	    System.out.println("Post-order traversal:");
	    for(Position<MyEntry<Integer, Integer>> i: tree.postOrderTraversal(tree.root())) System.out.println(i.getElement());

	    // Test tree search method
	    System.out.println("Search for value 4: " + tree.get(4));
	    System.out.println("Search for value 9: " + tree.get(9));

	    // Test tree removal method
	    System.out.println("Removing value 3");
	    tree.remove(3);
	    System.out.println("In-order traversal after removal:");
	    for(Position<MyEntry<Integer, Integer>> i: tree.inOrderTraversal(tree.root())) System.out.println(i.getElement());

	    
	    // Test tree minimum and maximum methods
	    System.out.println("Minimum value: " + tree.minimum());
	    System.out.println("Maximum value: " + tree.maximum());
	}

	
}
