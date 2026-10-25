# Red-black tree examples
# Run "make" to build, or "make run" to build and run.

CXX      = g++
CXXFLAGS = -std=c++17 -Wall -Wextra -g -Iinclude

SRC = src/main.cpp src/RedBlackTree.cpp
HDR = include/RedBlackTree.h

redblack: $(SRC) $(HDR)
	$(CXX) $(CXXFLAGS) $(SRC) -o redblack

run: redblack
	./redblack

clean:
	rm -f redblack

.PHONY: run clean
