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
    bool balanced = true;

    std::printf("Char:   ");
	stack.display();
    for (size_t i = 0; i < expression.length() && balanced; i++) {
	    char c = expression[i];
        std::printf("Char: %c ", c);
        if (c == '(' || c == '[' || c == '{') {
			stack.push(c);
		} else if (c == ')' || c == ']' || c == '}') {
			if (stack.isEmpty()) {
                balanced = false; // a closing symbol with nothing open to match it
            } else {
       			char open = stack.pop();
			    if (!matches(open, c)) {
                    balanced = false; // the wrong kind of bracket closed it
			    }
            }
		}
		// any other character (letters, digits, spaces, operators) is ignored
        stack.display();
	}
    return (balanced && stack.isEmpty());
}

int main() {
	std::string tests[] = {
		"(a+b)*[c-d]",
		"{[()()]}",
		"(a+b]*(c-d)",
		"((a+b)",
		"a+b)*(c-d",
		"([)(])(",
		"([)(])",
        ""
    };

	for (const std::string& test : tests) {
        std::printf("------------------------\n");
		std::string quoted = "\"" + test + "\"";
		std::printf("%-24s -> %s\n", quoted.c_str(), isBalanced(test) ? "balanced" : "not balanced");
	}

	return 0;
}

