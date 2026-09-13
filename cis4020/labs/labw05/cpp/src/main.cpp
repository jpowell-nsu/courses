#include <iostream>
#include <fstream>
#include <string>

#include "Droid.h"
#include "LinkedList.h"

// Strips leading/trailing whitespace, including a trailing '\r' left behind
// by files with Windows-style line endings, which std::getline does not
// remove the way Java's readLine() does.
std::string trim(const std::string& s) {
	size_t start = s.find_first_not_of(" \t\r\n");
	if (start == std::string::npos) {
		return "";
	}
	size_t end = s.find_last_not_of(" \t\r\n");
	return s.substr(start, end - start + 1);
}

int main() {
	LinkedList<Droid> droids;

	std::string filename = "files/droids.txt";
	std::ifstream file(filename);
	if (!file.is_open()) {
		std::cout << "Could not open " << filename << std::endl;
		return 1;
	}

	std::string line;
	while (std::getline(file, line)) {
        line = trim(line);
		if (!line.empty()) {
			droids.add(droids.size(), Droid(line));
		}
	}
	file.close();

	std::cout << "Loaded " << droids.size() << " droids from " << filename << "." << std::endl;

    droids.display();
    // -----------------------------------------------------------------
	// Your testing code goes here. Use display(), the methods you add
	// in Parts 1 through 3, and your own small test cases (including an
	// empty list and a single-item list) to check your work.
	// -----------------------------------------------------------------

	return 0;
}
