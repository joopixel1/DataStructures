package myDS.myTrees;

import myDS.myCollections.Position;

public class UsingTrees {

	public static void main(String[] args) {
		
		ArrayBinaryTree<Integer> tree = new ArrayBinaryTree<Integer>(1);
		testBinaryTree(tree);
		
		LinkedBinaryTree<Integer> tree2 = new LinkedBinaryTree<Integer>(1);
		testBinaryTree(tree2);
		
		testLinkedTree();
		
		
	}
	
	public static void testLinkedTree() {
	    LinkedTree<String> tree = new LinkedTree<>();

	    // Add nodes to the linked tree
	    Position<String> root = tree.addRoot("A");
	    Position<String> child1 = tree.addChild(root, "B");
	    Position<String> child2 = tree.addChild(root, "C");
	    Position<String> grandchild1 = tree.addChild(child1, "D");
	    Position<String> grandchild2 = tree.addChild(child1, "E");
	    Position<String> grandchild3 = tree.addChild(child2, "F");
	    Position<String> greatGrandchild = tree.addChild(grandchild2, "G");

	    // Test tree traversal methods
	    System.out.println("Pre-order traversal:");
        StringBuilder preOrderSB = new StringBuilder();
        tree.iterator().forEachRemaining((element) -> preOrderSB.append(element).append(" "));
        assert preOrderSB.toString().trim().equals("A B D E G C F");

	    System.out.println("Post-order traversal:");
	    StringBuilder inOrderSB = new StringBuilder();
        tree.reverseIterator().forEachRemaining((element) -> inOrderSB.append(element).append(" "));
        assert inOrderSB.toString().trim().equals("D G E B F C A");

	    System.out.println("Level-order traversal:");
	    StringBuilder levelOrderSB = new StringBuilder();
        tree.breadthFirstIterator().forEachRemaining((element) -> levelOrderSB.append(element).append(" "));
        //assert levelOrderSB.toString().trim().equals("A B C D E F G");

	    // Test tree height method
	    int height = tree.height(root);
	    System.out.println("Tree height: " + height);

	    // Test tree removal method
	    System.out.println("Removing node 'B'");
	    tree.remove(greatGrandchild);
	    System.out.println("Pre-order traversal after removal:");
	    StringBuilder preOrderSB2 = new StringBuilder();
        tree.iterator().forEachRemaining((element) -> preOrderSB2.append(element).append(" "));
        assert preOrderSB2.toString().trim().equals("A B D E C F");
	}
	


	public static void testBinaryTree(BinaryTree<Integer> tree) {

        // Create the following binary tree:
        //       1
        //      / \
        //     2   3
        //    / \   \
        //   4   5   6
        //        \
        //         7
        Position<Integer> root = tree.root();
        Position<Integer> node2 = tree.addLeft(root, 2);
        Position<Integer> node3 = tree.addRight(root, 3);
        Position<Integer> node4 = tree.addLeft(node2, 4);
        Position<Integer> node5 = tree.addRight(node2, 5);
        Position<Integer> node6 = tree.addRight(node3, 6);
        Position<Integer> node7 = tree.addRight(node5, 7);
        
        //plotGraph(tree);

        // Test size
        assert tree.size() == 7;

        // Test height
        assert tree.height(root) == 3;

        // Test pre-order traversal
        StringBuilder preOrderSB = new StringBuilder();
        tree.iterator().forEachRemaining((element) -> preOrderSB.append(element).append(" "));
        assert preOrderSB.toString().trim().equals("1 2 4 5 7 3 6");

        // Test post-order traversal
        StringBuilder inOrderSB = new StringBuilder();
        tree.reverseIterator().forEachRemaining((element) -> inOrderSB.append(element).append(" "));
        assert inOrderSB.toString().trim().equals("4 2 7 5 1 3 6");

        // Test level-order traversal
        StringBuilder levelOrderSB = new StringBuilder();
        tree.breadthFirstIterator().forEachRemaining((element) -> levelOrderSB.append(element).append(" "));
        assert levelOrderSB.toString().trim().equals("1 2 3 4 5 6 7");


        // Test remove
        tree.remove(node7);
        assert tree.size() == 6;
        
    }
	
	



	private static void plotGraph(ArrayBinaryTree<Integer> tree) {
		// TODO Auto-generated method stub
		
	}

	
}
