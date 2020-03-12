#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>



bool isFim(char *s){
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}


char gerarAlt() {
    srand(4);
    return (char)('a' + (rand() % 26));
    
}

char* alterar(char *s, char s1, char s2, int posicao) {
    

    if (posicao < strlen(s)) {

         if (s[posicao] == s1) {

            s[posicao] = s2;

    }

    posicao++;
    alterar(s, s1, s2, posicao);
    
    
    return s;

    }
}



int main() {

    char entrada[1000][1000];
    int numEntradas = 0;

    do {
        fgets(entrada[numEntradas], 1000, stdin);
    } while (isFim(entrada[numEntradas++]) == false);

    numEntradas--;

    char c1,
         c2;
       
    for (int i = 0; i < numEntradas; i++) {
        c1 = gerarAlt();
        c2 = gerarAlt();
        printf("%s", alterar(entrada[i], c1, c2, 0));

    }

    return 0;
    
}