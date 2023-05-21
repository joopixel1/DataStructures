package myDS.searchTrees;

import java.util.Comparator;
import myDS.myCollections.MyEntry;
import myDS.myTrees.LinkedBinaryTree;

public abstract class AbstractSearchTree<K, V> extends LinkedBinaryTree<MyEntry<K, V>> implements searchTrees<K, V> {

	
	protected Comparator<K> compare;
	
	
	public AbstractSearchTree(Comparator<K> comp) {
		compare = comp;		
	}
	
	public AbstractSearchTree() {
		this(new Comparator<K> () {
			@Override
			public int compare(K o1, K o2) {
				return ((Comparable<K>) o1).compareTo(o2);	
			}
		});
	}
	
}
