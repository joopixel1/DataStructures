package myDS.myTrees;

import java.util.Iterator;
import myDS.myCollections.MyCollection;
import myDS.myCollections.Position;
import myDS.myLists.MyList;

public interface MyTree<E> extends MyCollection<E> {
	
	Position<E> root();
	Position<E> parent(Position<E> p);
	MyList<Position<E>> children(Position<E> p);
	int numChildren(Position<E> p);
	
	boolean isInternal(Position<E> p);
	boolean isExternal(Position<E> p);
	boolean isRoot(Position<E> p);
	
	Iterator<E> iterator();
	Iterator<Position<E>> positions();
	Iterator<E> reverseIterator();
	Iterator<Position<E>> reversePositions();
	Iterator<E> breadthFirstIterator();
	Iterator<Position<E>> breadthFirstPositions();
	
	
	int depth(Position<E> p);
	int height(Position<E> p);
	
	Position<E> addRoot(E e);
	E set(Position<E> p, E e);
	E remove(Position<E> p);
	
}
