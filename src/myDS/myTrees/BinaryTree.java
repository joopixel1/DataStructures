package myDS.myTrees;

import myDS.myCollections.Position;

public interface BinaryTree<E> extends MyTree<E> {
	
	Position<E> left(Position<E> p);
	Position<E> right(Position<E> p);
	Position<E> sibling(Position<E> p);
	
	Position<E> addLeft(Position<E> p, E e);
	Position<E> addRight(Position<E> p, E e);
		
}
