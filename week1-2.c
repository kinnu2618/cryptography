#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main()
{
char str[] = "Hello World";
char str1[12]; 
char str2[12]; 
char str3[12]; 
int i, len;
len = strlen(str);
for (i = 0; i < len; i++) {
str2[i] = str[i];
}
str2[len] = '\0';
for (i = 0; i < len; i++) {
str1[i] = str[i] & 127;
printf("%c", str1[i]);
}
str1[len] = '\0'; 
printf("\n");
for (i = 0; i < len; i++) {
str3[i] = str2[i] ^ 127;
printf("%c", str3[i]);
}
str3[len] = '\0';
printf("\n");
return 0;
}