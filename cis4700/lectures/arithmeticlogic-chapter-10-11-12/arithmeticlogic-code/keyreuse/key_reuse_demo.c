/* key_reuse_demo.c  --  the "reusing a key" example from the concept primer.
 *
 * Two different letters are encrypted with the SAME key. XORing the two
 * ciphertexts together cancels the shared key, leaving the two plaintexts
 * XORed with each other. So c1 XOR c2 equals m1 XOR m2, with no key involved.
 *
 * Build & run:
 *   gcc -O0 -o key_reuse_demo key_reuse_demo.c
 *   ./key_reuse_demo
 */
#include <stdio.h>

/* print one byte as 8 bits (MSB first) plus its hex and decimal value */
static void show(const char *label, unsigned char b) {
    printf("%-10s", label);
   
    for (int i = 7; i >= 0; i--)
        putchar((b >> i) & 1 ? '1' : '0');
    
    printf("   (%3d, 0x%02X ", b, b);
    
    if (b >= 32 && b < 127) 
        printf(", '%c'", b);
    
    printf(")\n");
}

int main(void) {
    unsigned char m1  = 'A';    /* first message  */
    unsigned char m2  = 'C';    /* second message */
    unsigned char key = 0x0F;   /* the SAME key used for both */

    unsigned char c1 = m1 ^ key;
    unsigned char c2 = m2 ^ key;

    printf("Two messages, one shared key:\n");
    show("message 1", m1);
    show("message 2", m2);
    show("key",       key);

    printf("\nEach encrypted with that same key:\n");
    show("cipher 1",  c1);
    show("cipher 2",  c2);

    printf("\nXOR the two ciphertexts, and XOR the two messages:\n");
    show("c1 XOR c2", c1 ^ c2);
    show("m1 XOR m2", m1 ^ m2);

    printf("\nSame result, so the key cancelled out? %s\n",
           (c1 ^ c2) == (m1 ^ m2) ? "yes" : "no");

    return 0;
}
