/* xor_demo.c  --  the XOR round trip from the concept primer.
 *
 * Encrypt the letter 'H' by XORing it with a key byte, then decrypt by XORing
 * the cipher with the SAME key. The key cancels itself, so you land back on 'H'.
 *
 * Build & run:
 *   gcc -O0 -o xor_demo xor_demo.c
 *   ./xor_demo
 */
#include <stdio.h>
 
/* print one byte as 8 bits, most significant bit first */
static void print_bits(const char *label, unsigned char b) {
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
    */
    printf("%-8s", label);

    for (int i = 7; i >= 0; i--) 
        putchar((b >> i) & 1 ? '1' : '0'); // 
    
    printf("   (%3d, 0x%02X ", b, b);
    
    if (b >= 32 && b < 127) 
        printf(", '%c'", b);   /* show it as text if printable */
    
    printf(")\n");
}
 
int main(void) {
    unsigned char message = 'H';    /* 72  = 01001000 */
    unsigned char key     = 42;     /* 42  = 00101010 (the asterisk) */
 
    unsigned char cipher = message ^ key;   /* encrypt with bitwise XOR) */
    unsigned char back   = cipher  ^ key;   /* decrypt with the same key */
 
    printf("Encrypting:\n");
    print_bits("message", message);
    print_bits("key",     key);
    print_bits("cipher",  cipher);
 
    printf("\nDecrypting (XOR the cipher with the same key):\n");
    print_bits("cipher",  cipher);
    print_bits("key",     key);
    print_bits("back",    back);
 
    printf("\nRecovered the original? %s\n", back == message ? "yes" : "no");

    return 0;
}

