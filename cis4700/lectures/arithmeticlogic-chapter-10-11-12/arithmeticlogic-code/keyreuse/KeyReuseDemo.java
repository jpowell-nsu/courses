/* KeyReuseDemo.java  --  the "reusing a key" example from the concept primer.
 *
 * Two different letters are encrypted with the SAME key. XORing the two
 * ciphertexts together cancels the shared key, leaving the two plaintexts
 * XORed with each other. So c1 XOR c2 equals m1 XOR m2, with no key involved.
 *
 * Compile & run:
 *   javac KeyReuseDemo.java
 *   java KeyReuseDemo
 */
public class KeyReuseDemo {

    /* print one byte as 8 bits (MSB first) plus its hex and decimal value */
    static void show(String label, int b) {
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

        System.out.printf("%-10s", label);

        for (int i = 7; i >= 0; i--)
            System.out.printf("%s", (b >> i) & 1);

        System.out.printf("   (%3d, 0x%02X ", b, b);
        if (b >= 32 && b < 127)
            System.out.printf(", '%c'", b);   /* show it as text if printable */

        System.out.printf(")\n");     

    }

    public static void main(String[] args) {
        // As before, we need use int to simpl

        int m1  = 'A';      // first message
        int m2  = 'C';      // second message
        int key = 0x0F;     // the SAME key used for both

        int c1 = m1 ^ key;
        int c2 = m2 ^ key;

        System.out.println("Two messages, one shared key:");
        show("message 1", m1);
        show("message 2", m2);
        show("key",       key);

        System.out.println("\nEach encrypted with that same key:");
        show("cipher 1",  c1);
        show("cipher 2",  c2);

        System.out.println("\nXOR the two ciphertexts, and XOR the two messages:");
        show("c1 XOR c2", c1 ^ c2);
        show("m1 XOR m2", m1 ^ m2);

        System.out.println("\nSame result, so the key cancelled out? "
                + (((c1 ^ c2) == (m1 ^ m2)) ? "yes" : "no"));
    }
}
