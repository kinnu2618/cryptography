#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {
    char str[] = "Hello World";
    char str2[11]; // For AND with 127
    char str3[11]; 
    char decrypted[11]; // For decrypted XOR result
    int i, len;

    // Calculate the length of the input string
    len = strlen(str);

    // Print the entered text
    printf("Entered text: %s\n", str);

    // Perform AND operation with 127
    printf("AND with 127: ");
    for (i = 0; i < len; i++) {
        str2[i] = str[i] & 127;
        printf("%c", str2[i]);
    }
    printf("\n");

    // Perform XOR operation with 127
    printf("XOR with 127: ");
    for (i = 0; i < len; i++) {
        str3[i] = str[i] ^ 127;
        printf("%c", str3[i]);
    }
    printf("\n");

    // Decrypt the XOR result
    printf("Decrypted text: ");
    for (i = 0; i < len; i++) {
        decrypted[i] = str3[i] ^ 127;
        printf("%c", decrypted[i]);
    }
    printf("\n");

    return 0;
}