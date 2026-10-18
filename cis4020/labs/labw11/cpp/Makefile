# Lab W11: 2-3 tree insertion
# Run "make" to build, or "make run" to build and run.
#
# Layout:
#   include/   header files (.h)
#   src/       source files (.cpp)

CXX      = g++
CXXFLAGS = -std=c++17 -Wall -Wextra -g -Iinclude

SRC = src/main.cpp src/TwoThreeTree.cpp
HDR = include/TwoThreeNode.h include/TwoThreeTree.h

labw11: $(SRC) $(HDR)
	$(CXX) $(CXXFLAGS) $(SRC) -o labw11

run: labw11
	./labw11

clean:
	rm -f labw11

.PHONY: run clean
