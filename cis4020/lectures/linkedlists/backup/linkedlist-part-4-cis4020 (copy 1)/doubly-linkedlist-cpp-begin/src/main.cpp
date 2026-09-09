#include <iostream>

#include "Droid.h"
#include "LinkedList.h"

int main() {
	List<Droid> droids;

	droids.add(0, Droid("R2-D2"));
	droids.add(0, Droid("BB-8"));
	droids.add(0, Droid("C-3PO"));
	droids.add(0, Droid("R5-D4"));

	droids.display();

	for (int i = 0; i < droids.size(); i++) {
		// with a template, get(i) already returns a Droid; no cast needed
		std::cout << droids.get(i) << std::endl;
	}

	std::cout << std::endl;
	return 0;
}
