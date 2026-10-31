# Priority Queues: a priority queue built on the heaps
# Run "make" to build, or "make run" to build and run.
#
# Layout:
#   include/   header files (.h)
#   src/       source files (.cpp)

CXX      = g++
CXXFLAGS = -std=c++17 -Wall -Wextra -g -Iinclude

SRC = src/PQApp.cpp
HDR = include/HeapBasics.h include/HeapArrayBased.h include/HeapReferenceBased.h \
      include/PriorityQueue.h include/Task.h

pqapp: $(SRC) $(HDR)
	$(CXX) $(CXXFLAGS) $(SRC) -o pqapp

run: pqapp
	./pqapp

clean:
	rm -f pqapp

.PHONY: run clean
