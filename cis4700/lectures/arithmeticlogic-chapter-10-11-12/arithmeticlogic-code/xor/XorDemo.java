/* XorDemo.java  --  the XOR round trip from the concept primer.
 *
 * Encrypt the letter 'H' by XORing it with a key byte, then decrypt by XORing
 * the cipher with the SAME key. The key cancels itself, so you land back on 'H'.
 *
 * Compile & run:
 *   javac XorDemo.java
 *   java XorDemo
 */
public class XorDemo {
 
    /* print one byte as 8 bits, most significant bit first */
    static void printBits(String label, int b) {
        /*  below is using bitwise shift and bitwise & to go through each bit
            The expression (b >> i) & 1 reads the single bit at position i
            b       is the character, in this example, H (1001000)
            >>      shifts it by an amount
            & 1     pulls the right-most bit from the shifted value, masking the others

            i=7:  b>>7 = 00000000,  & 1 = 0   -> '0' shifted by 7, pulls off the 0
            i=6:  b>>6 = 00000001,  & 1 = 1   -> '1' shifted by 6, pulls off the 1
            i=5:  b>>5 = 00000010,  & 1 = 0   -> '0' shifted by 5, pulls off the 0
            i=4:  b>>4 = 00000100,  & 1 = 0   -> '0' shifted by 4, pulls off the 0
            i=3:  b>>3 = 00001001,  & 1 = 1   -> '1' shifted by 3, pulls off the 1
            i=2:  b>>2 = 00010010,  & 1 = 0   -> '0' shifted by 2, pulls off the 0
            i=1:  b>>1 = 00100100,  & 1 = 0   -> '0' shifted by 1, pulls off the 0
            i=0:  b>>0 = 01001000,  & 1 = 0   -> '0' no shift,     pulls off the 0

            if the bitwise & is a logical operator, anything non-zero is true,
            in the case of 1, it is interpreted as true, thus prints 1
            in the case of 0, it is interpreted as false, thus prints 0

            Do not confuse this with bitwise xor; this has nothing to do with
            anything but printing the character's bits

            Note: because the int is 32 bytes, we use caution to "zero" out
                the first 24 bits. Java uses a signed integer and the signed
                bit gets copied when widening from a smaller to a larger data
                type. In this specific example, that never happens because
                all the calls to this method use an int. Imagine if we passed
                in a byte of 255  (11111111) and it is widened to
                (11111111 11111111 11111111 11111111)

        */
        b &= 0xFF;  // zero out the first 24 for pure precaution
                   
        System.out.printf("%-8s", label);        

        for (int i = 7; i >= 0; i--)
            System.out.printf("%s", (b >> i) & 1);
 
        System.out.printf("   (%3d, 0x%02X ", b, b);

        if (b >= 32 && b < 127)
            System.out.printf(", '%c'", b);   /* show it as text if printable */

        System.out.printf(")\n");
    }
 
    public static void main(String[] args) {
        // Note: unlike C, Java's character is two bytes instead of one.
        // Java does have a byte datatype, but to simplify this demo, particularly
        // with the printing and casting issues. Trying to use the java char has
        // issues as well. Semantically, Java forces casting when narrowing
        // and XOR produces an int, so using ints here is easier overall and
        // we can just ignore the first 24 bits

        ing message = 'H';     // 72 = 01001000
        int key     = 42;      // 42 = 00101010
 
        int cipher = message ^ key;    // encrypt
        int back   = cipher  ^ key;    // decrypt with the same key
 
        System.out.println("Encrypting:");
        printBits("message", message);
        printBits("key",     key);
        printBits("cipher",  cipher);
 
        System.out.println("\nDecrypting (XOR the cipher with the same key):");
        printBits("cipher",  cipher);
        printBits("key",     key);
        printBits("back",    back);
 
        System.out.println("\nRecovered the original? " + (back == message ? "yes" : "no"));
    }
}
