
/* OverflowDemo.java  --  the integer overflow example from the concept primer.
 *
 * A Java byte is an 8-bit signed integer that holds -128 through 127. Adding 1
 * to 127 does not give 128. It wraps around to -128, the most negative value.
 *
 * Compile & run:
 *   javac OverflowDemo.java
 *   java OverflowDemo
 */
public class Overflow {

    public static void main(String[] args) {
        // Note: like in the XOR examples, numeric promotions can be an issue.
        // I wanted to use a byte for simplicity, but that means adding int
        // 1 to it requires casting and the FF mask to fixe. Are there better
        // ways? Probably.

        byte before = 127;                 // the largest byte value
        byte after  = (byte) (before + 1); // one more wraps around
 
        System.out.printf("before = %4d   bits = ", before);
        for (int i = 7; i >= 0; i--)
            System.out.print(((before & 0xFF) >> i) & 1);
        System.out.println();
 
        System.out.printf("after  = %4d   bits = ", after);
        for (int i = 7; i >= 0; i--) 
            System.out.print(((after & 0xFF) >> i) & 1);
        System.out.println();

        for (int j = 0; j < 5; j++) {
            after = (byte) (after + 1);
            System.out.printf("after  = %4d   bits = ", after);
            for (int i = 7; i >= 0; i--)
                System.out.print(((after & 0xFF) >> i) & 1);
            System.out.println();
        }

    }
}

