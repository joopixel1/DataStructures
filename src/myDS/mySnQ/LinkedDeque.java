package myDS.mySnQ;

import myDS.fundamentalDS.MyDoubleLinkedSequence;

public class LinkedDeque<E> implements myDeque<E> {

	private MyDoubleLinkedSequence<E> mes = new MyDoubleLinkedSequence<E>();
	
	@Override
	public int size() {
		return mes.size();
	}

	@Override
	public boolean isEmpty() {
		return mes.isEmpty();
	}

	@Override
	public E removeFirst() {
		return mes.removeFirst();
	}

	@Override
	public E removeLast() {
		return mes.removeLast();
	}

	@Override
	public void addFirst(E e) {
		mes.addFirst(e);
	}

	@Override
	public void addLast(E e) {
		mes.addLast(e);
	}

	@Override
	public void add(E e) {
		mes.add(e);
	}

	@Override
	public E remove() {
		return mes.remove();
	}

}
