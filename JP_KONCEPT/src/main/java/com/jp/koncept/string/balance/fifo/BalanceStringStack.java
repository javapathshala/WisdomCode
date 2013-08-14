/**
 * 
 */
package com.jp.koncept.string.balance.fifo;

import java.util.Stack;

/**
 * @author dchadha
 */
public class BalanceStringStack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BalanceStringStack bal = new BalanceStringStack();
		String input="[(2+3]]";
		if(bal.IsStringBalance(input)){
			System.out.println("String - "+input + " is balanced");
		}else{
			System.out.println("String - "+input + " is not balanced");
		}

	}

	private boolean IsStringBalance(String input) {
		Stack<Character> stack = new Stack<Character>();
		int len = input.length();
		for (int i = 0; i < len; i++) {
			char c = input.charAt(i);
			switch (c) {

				case '{':
				case '(':
				case '[':
					stack.push(c);
					break;

				case ']':
					if (stack.isEmpty() || stack.pop() != '[')
						return false;
					break;
				case ')':
					if (stack.isEmpty() || stack.pop() != '(')
						return false;
					break;
				case '}':
					if (stack.isEmpty() || stack.pop() != '{')
						return false;
					break;
			}
		}
		return stack.isEmpty();

	}

}
