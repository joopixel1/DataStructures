package myDS.mySnQ;

public class UsingQueues {

	public static void main(String[] args) {
		// Queues can be used for many things including:
		
		//1. for solving jophesus problem, using CIrcularQueues
		//which states dat if n children are in a circle and we go around eliminating every other K user.
		//who is left as the winner (last person)
		CircularQueue<Integer> myheart = new CircularQueue<Integer>();
		for(int i=1; i<=10; i++) myheart.add(i);
		System.out.println(josephus(myheart, 2));
		
		
		
	}

	private static <E> E josephus(CircularQueue<E> myheart, int k) {
		
		if(myheart.isEmpty()) return null;
		
		while(myheart.size() > 1) {
			myheart.remove();
			for(int i=0; i<k; i++)myheart.rotate();
			
		}
		
		return myheart.remove();		
		
	}

}
