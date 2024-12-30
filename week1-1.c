#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {
    char str[] = "Hello World";
    char str2[11]; 
    char str3[11]; 
    char decrypted[11]; 
    int i, len;

    len = strlen(str);

    printf("Entered text: %s\n", str);

    printf("AND with 127: ");
    for (i = 0; i < len; i++) {
        str2[i] = str[i] & 127;
        printf("%c", str2[i]);
    }
    printf("\n");

    printf("XOR with 127: ");
    for (i = 0; i < len; i++) {
        str3[i] = str[i] ^ 127;
        printf("%c", str3[i]);
    }
    printf("\n");

    printf("Decrypted text: ");
    for (i = 0; i < len; i++) {
        decrypted[i] = str3[i] ^ 127;
        printf("%c", decrypted[i]);
    }
    printf("\n");

    return 0;
}