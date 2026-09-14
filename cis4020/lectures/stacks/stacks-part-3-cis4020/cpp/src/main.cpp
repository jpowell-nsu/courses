#include <cstdio>
#include <string>

#include "Stack.h"

/*********************************************************
Demonstrates the ADT stack by checking whether the bracket symbols in
a string are balanced: every opening symbol has a matching closing
symbol of the same kind, in the right order, with nothing left open
and nothing closed that was never opened.
**********************************************************/

bool matches(char open, char close) {
	return (open == '(' && close == ')')
		|| (open == '[' && close == ']')
		|| (open == '{' && close == '}');
}

bool isBalanced(const std::string& expression) {
	Stack<char> stack;
	stack.display();
	for (char c : expression) {
		if (c == '(' || c == '[' || c == '{') {
			stack.push(c);
			stack.display();
		} else if (c == ')' || c == ']' || c == '}') {
			if (stack.isEmpty()) {
				stack.display();
				return false;	// a closing symbol with nothing open to match it
			}
			char open = stack.pop();
			stack.display();
			if (!matches(open, c)) {
				stack.display();
				return false;	// the wrong kind of bracket closed it
			}
		}
		// any other character (letters, digits, spaces, operators) is ignored
	}

	return stack.isEmpty();	// anything still on the stack was never closed
}

int main() {
	std::string tests[] = {
		"(a + b) * [c - d]",
		"{[()()]}",
		"(a + b] * (c - d)",
		"((a + b)",
		"a + b) * (c - d",
		""
	};

	for (const std::string& test : tests) {
		std::string quoted = "\"" + test + "\"";
		std::printf("%-24s -> %s\n", quoted.c_str(), isBalanced(test) ? "balanced" : "not balanced");
	}

	return 0;
}

