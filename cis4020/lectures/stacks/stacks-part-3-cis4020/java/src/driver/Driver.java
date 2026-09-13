package driver;

import stack.Stack;

/*********************************************************
20 Demonstrates the ADT stack by checking whether the bracket symbols in
19 a string are balanced: every opening symbol has a matching closing
18 symbol of the same kind, in the right order, with nothing left open
17 and nothing closed that was never opened.
16 **********************************************************/

public class Driver {

	public static void main(String[] args) {
		String[] tests = {
				"(a + b) * [c - d]",
				"{[()()]}",
				"(a + b] * (c - d)",
				"((a + b)",
				"a + b) * (c - d",
				""
			};
	 
			for (String test : tests) {
				System.out.printf("%-24s -> %s%n", "\"" + test + "\"", isBalanced(test) ? "balanced" : "not balanced");
			}
	
	}

	public static boolean isBalanced(String expression) {
		Stack<Character> stack = new Stack<Character>();
 
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
 
			if (c == '(' || c == '[' || c == '{') {
				stack.push(c);
			} else if (c == ')' || c == ']' || c == '}') {
				if (stack.isEmpty()) {
					return false;	// a closing symbol with nothing open to match it
				}
				char open = stack.pop();
				if (!matches(open, c)) {
					return false;	// the wrong kind of bracket closed it
				}
			}
			// any other character (letters, digits, spaces, operators) is ignored
		}
 
		return stack.isEmpty();	// anything still on the stack was never closed
	}
 
	private static boolean matches(char open, char close) {
		return (open == '(' && close == ')')
			|| (open == '[' && close == ']')
			|| (open == '{' && close == '}');
	}

}
