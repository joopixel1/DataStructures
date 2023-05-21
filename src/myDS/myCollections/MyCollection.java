package myDS.myCollections;

public interface MyCollection<E> {
	void add(E e);
	E remove();
	int size();
	boolean isEmpty();
}
