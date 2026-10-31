# Heaps: array-based and pointer-based heaps, plus heapsort
# Run "make" to build both programs.
#   make run        builds (if needed) and runs heaping, the interactive heap driver
#   make run-sort   builds (if needed) and runs heapsorting, the heapsort trace
#   make clean      removes both programs
#
# Layout:
#   include/   header files (.h)
#   src/       source files (.cpp)

CXX      = g++
CXXFLAGS = -std=c++17 -Wall -Wextra -g -Iinclude

HDR = include/HeapBasics.h include/HeapArrayBased.h include/HeapReferenceBased.h \
      include/HeapSort.h include/Item.h

all: heaping heapsorting

heaping: src/Heaping.cpp $(HDR)
	$(CXX) $(CXXFLAGS) src/Heaping.cpp -o heaping

heapsorting: src/HeapSorting.cpp $(HDR)
	$(CXX) $(CXXFLAGS) src/HeapSorting.cpp -o heapsorting

run: heaping
	./heaping

run-sort: heapsorting
	./heapsorting

clean:
	rm -f heaping heapsorting

.PHONY: all run run-sort clean
