package myDS.myMaps;

import java.util.Iterator;

public interface MySet<E> extends Iterable<E> {

	int size();
	boolean isEmpty();
	
	void add(E e);
	E remove(E e);
	boolean contains(E e);
	Iterator<E> iterator();	
	
	void union(MySet<E> T);
	void intersection(MySet<E> T);
	void subtract (MySet<E> T);

}
