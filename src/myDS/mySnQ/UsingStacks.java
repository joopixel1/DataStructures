package myDS.mySnQ;

import java.util.Arrays;

import sortingNsearching.*;

public class UsingStacks {

	public static void main(String[] args) {
		// Stacks can be used for many things including:
		
		//1. for jreversing a list
		Integer[] tac = ArrayUtil.randomIntArray(10, 100);
		System.out.println(Arrays.toString(tac));
		reverse(tac);
		System.out.println(Arrays.toString(tac));
		System.out.println();
		
		
		//2. mathcing paranthesis and html tags
		String cor = "{[()]()}";
		String wro = "{}{}{{}{}()[][-0]00";
		System.out.println(isMatched(cor));
		System.out.println(isMatched(wro));
		System.out.println();
		
		

	}
	
	private static boolean isMatched(String word) {
		String left = "{[(";
		String right = ")]}";
		LinkedStack<Character> me = new LinkedStack<Character>();
		for (char a: word.toCharArray()) {
			if(left.indexOf(a)>-1) me.add(a);
			if(right.indexOf(a)>-1) {
				if((a == '}' && me.top()=='{') || (a == ']' && me.top()=='[') || (a == ')' && me.top()=='(')) me.remove();
				else return false;
			}
		}
		return me.isEmpty();
	}

	public static <E> void reverse(E[] arr) {
		ArrayStack<E> me = new ArrayStack<E>();
		for(int i=0; i<arr.length; i++) me.add(arr[i]);
		for(int i=0; i<arr.length; i++) arr[i] = me.remove();
	}

}
