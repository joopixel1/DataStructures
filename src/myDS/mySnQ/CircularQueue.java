package myDS.mySnQ;

//change to linkedQueue later 
public class CircularQueue<E> extends LinkedQueue<E>{
	
	//adds extra function rotate that moves the first element to be the last element in o(1) time.
	void rotate() {
		this.add(this.remove());
	}

}
