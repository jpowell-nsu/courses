/* overflow_demo.c  --  the integer overflow example from the concept primer.
 *
 * An 8-bit signed integer holds -128 through 127. Adding 1 to 127 does not
 * give 128. It wraps around to -128, the most negative value.
 *
 * Build & run:
 *   gcc -O0 -o overflow_demo overflow_demo.c
 *   ./overflow_demo
 */
#include <stdio.h>
#include <stdint.h>
 
int main(void) {
    int8_t before = 127;                   /* the largest 8-bit signed value */
    int8_t after  = (int8_t)(before + 1);  /* one more wraps around          */
 
    printf("before = %4d   bits = ", before);
    for (int i = 7; i >= 0; i--) 
        putchar(((uint8_t)before >> i) & 1 ? '1' : '0');
    printf("\n");
 
    printf("after  = %4d   bits = ", after);
    for (int i = 7; i >= 0; i--) 
        putchar(((uint8_t)after >> i) & 1 ? '1' : '0');
    printf("\n");


    for (int i = 0; i < 5; i++) {
        after = after + 1;
        printf("after  = %4d   bits = ", after);
        for (int i = 7; i >= 0; i--)
            putchar(((uint8_t)after >> i) & 1 ? '1' : '0');
        printf("\n");
    }
 
    return 0;
}

