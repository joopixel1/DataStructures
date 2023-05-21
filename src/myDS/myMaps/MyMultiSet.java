package myDS.myMaps;

public interface MyMultiSet<E> extends MySet<E> {
	
	int count(E e);
	void remove(E e, int n);
	void removeAll(E e);

}
