/* LfsrDemo.java  --  the 4-bit LFSR example from the concept primer.
 *
 * A 4-bit shift register whose feedback bit is the XOR of its two rightmost
 * bits. Each clock tick: compute the feedback bit, then shift the whole
 * register right by one and drop the feedback bit in on the left. The feedback
 * bit is also the output bit. The seed is 1000.
 *
 * Nothing random happens: the same seed always produces the same stream.
 *
 * Compile & run:
 *   javac LfsrDemo.java
 *   java LfsrDemo
 */
public class LFSR {
 
    /* print the low 4 bits of a value, most significant of the four first */
    static void print4(int v) {
        for (int i = 3; i >= 0; i--) 
            System.out.print((v >> i) & 1);
    }
 
    public static void main(String[] args) {
        // change the seed and get a different result
        int state = 0x8;   // 1000 in binary: the seed
 
        System.out.println("step   state   output");
        for (int step = 1; step <= 8; step++) {
            // feedback = bit0 XOR bit1 (the two rightmost bits)
            int feedback = (state ^ (state >> 1)) & 1;
 
            System.out.printf(" %d      ", step);
            print4(state);
            System.out.printf("      %d%n", feedback);
 
            // shift right, then place the feedback bit into bit 3 (the left end)
            state = ((state >> 1) | (feedback << 3)) & 0xF;
        }
    }
}

