package myDS.myLists;

import myDS.myCollections.MyCollection;

public interface MyList<E> extends MyCollection<E>, Iterable<E> {
	void add(int i, E e);
	E remove(int i);
	E get(int i);
	void set(int i, E e);
}
