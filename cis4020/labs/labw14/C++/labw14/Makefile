# Lab W14: open-addressing hash table with double hashing
# Run "make" to build, or "make run" to build and run.
#   make clean      removes the program and dump file
#
# Layout:
#   include/   header files (.h)
#   src/       source files (.cpp)
#   files/     input files (run from this folder so the paths work)

CXX      = g++
CXXFLAGS = -std=c++17 -Wall -Wextra -g -Iinclude

SRC = src/main.cpp src/HashTable.cpp
HDR = include/HashTable.h

hashing: $(SRC) $(HDR)
	$(CXX) $(CXXFLAGS) $(SRC) -o hashing

run: hashing
	./hashing

clean:
	rm -f hashing files/dump.txt

.PHONY: run clean
