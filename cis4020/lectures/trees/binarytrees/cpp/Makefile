CXX = g++
CXXFLAGS = -std=c++17 -Wall -Wextra -pedantic -Iinclude

treeapp: src/TreeApp.cpp include/*.h
	$(CXX) $(CXXFLAGS) src/TreeApp.cpp -o treeapp

run: treeapp
	./treeapp

clean:
	rm -f treeapp

.PHONY: run clean
