# Makefile for the use-after-free teaching demos.
#
# The flags are chosen so the demo is DETERMINISTIC and easy to read, not
# realistic. We deliberately remove defenses so the mechanism is visible:
#   -O0               do not optimize anything away (keep the bug intact)
#   -g                include debug info for gdb
#   -no-pie -fno-pie  fixed load address, so win()'s address never moves
#   -fno-stack-protector   no canary noise around our structs
#   -w                silence the (correct!) warnings about using freed memory
#
# Run each program with ASLR turned off for a clean, repeatable result:
#   setarch -R ./demo1_isadmin
#   setarch -R ./demo2_win

CXX      = g++
CXXFLAGS = -O0 -g -no-pie -fno-pie -fno-stack-protector -w

all: demo1_isadmin demo1_traced demo2_win demo2_traced

demo1_isadmin: demo1_isadmin.cpp
	$(CXX) $(CXXFLAGS) -o $@ $<

demo1_traced: demo1_traced.cpp
	$(CXX) $(CXXFLAGS) -o $@ $<

demo2_traced: demo2_traced.cpp
	$(CXX) $(CXXFLAGS) -o $@ $<

demo2_win: demo2_win.cpp
	$(CXX) $(CXXFLAGS) -o $@ $<

# Convenience targets that run with ASLR disabled.
run1: demo1_isadmin
	setarch -R ./demo1_isadmin

run2: demo2_win
	setarch -R ./demo2_win

clean:
	rm -f demo1_isadmin demo2_win
