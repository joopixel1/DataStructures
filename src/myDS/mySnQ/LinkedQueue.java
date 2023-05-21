package myDS.mySnQ;

import myDS.fundamentalDS.MySingleLinkedSequence;

public class LinkedQueue<E> implements myQueue<E> {

	private MySingleLinkedSequence<E> mes = new MySingleLinkedSequence<E>();
	
	@Override
	public void add(E e) {
		mes.addLast(e);	
	}

	@Override
	public E remove() {
		return mes.removeFirst();
	}

	@Override
	public int size() {
		return mes.size();
	}

	@Override
	public boolean isEmpty() {
		return mes.isEmpty();
	}

	@Override
	public E first() {
		return mes.first();
	}

}
