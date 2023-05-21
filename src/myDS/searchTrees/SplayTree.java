package myDS.searchTrees;

import myDS.myCollections.MyEntry;
import myDS.myCollections.Position;

public class SplayTree<K, V> extends BinarySearchTree<K, V> {

	@Override
	public V get(K k) {
		Position<MyEntry<K, V>> a = getter(k);
		splay(a);
		return (compare.compare(k, a.getElement().getKey()) == 0) ? a.getElement().getValue() : null;
	}

	@Override
	public void put(K k, V v) {
		Position<MyEntry<K, V>> a = putter(k, v);
		splay(a);		
	}

	@Override
	public V remove(K k) {
		Position<MyEntry<K, V>> a = getter(k);
		V ans =  null;
		Position<MyEntry<K, V>> b = a;
		if (compare.compare(k, a.getElement().getKey()) == 0) {
			ans = a.getElement().getValue();
			b = remover(a);
		}
		splay(b);
		return ans;
	}
	
	private void splay(Position<MyEntry<K, V>> p) {
		
		while(p != this.root()) {
			if(this.isRoot(this.parent(p))) {
				zig(p, this.parent(p));
				continue;
			}
		
			Position<MyEntry<K, V>> par = this.parent(p);
			Position<MyEntry<K, V>> grand = this.parent(par);
			if((this.left(grand) == par && this.left(par) == p) 
				|| (this.right(grand) == par && this.right(par) == p)) 
					zigzig(p, par, grand, this.parent(grand));
			else
				zigzag(p, par, grand, this.parent(grand));
			
		}
	}

	private void zig(Position<MyEntry<K, V>> p, Position<MyEntry<K, V>> par) {
		if(p == this.right(par)) {
			this.addRight(par, this.left(p));
			this.addLeft(p, par);
		}else {
			this.addLeft(par, this.right(p));
			this.addRight(p, par);
		}
		
		this.setRoot(p);
	}
	
	private void zigzig(Position<MyEntry<K, V>> p, Position<MyEntry<K, V>> par, Position<MyEntry<K, V>> grand, Position<MyEntry<K, V>> grangran) {
		if(p == this.right(par)) {
			this.addRight(grand, this.left(par));
			this.addLeft(par, grand);
			
			this.addRight(par, this.left(p));
			this.addLeft(p, par);
		}else {
			this.addLeft(grand, this.right(par));
			this.addRight(par, grand);
			
			this.addLeft(par, this.right(p));
			this.addRight(p, par);
		}
		
		if(grand == this.root()) this.setRoot(p);
		else {
			if(grand == this.right(grangran)) this.addRight(grangran, p);
			else this.addLeft(grangran, p);
		}
	}
	
	private void zigzag(Position<MyEntry<K, V>> p, Position<MyEntry<K, V>> par, Position<MyEntry<K, V>> grand, Position<MyEntry<K, V>> grangran) {
		if(p == this.left(par)) {
			this.addRight(grand, this.left(p));
			this.addLeft(p, grand);
			
			this.addLeft(par, this.right(p));
			this.addRight(p, par);
		}else {
			this.addLeft(grand, this.right(p));
			this.addRight(p, grand);
			
			this.addRight(par, this.left(p));
			this.addLeft(p, par);		
		}
		
		if(grand == this.root()) this.setRoot(p);
		else {
			if(grand == this.right(grangran)) this.addRight(grangran, p);
			else this.addLeft(grangran, p);
		}
	}
	
}
