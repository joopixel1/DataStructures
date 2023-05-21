package myDS.mySnQ;

import myDS.myCollections.MyCollection;

public interface myDeque<E> extends MyCollection<E> {
	E removeFirst();
	E removeLast();
	void addFirst(E e);
	void addLast(E e);
}
