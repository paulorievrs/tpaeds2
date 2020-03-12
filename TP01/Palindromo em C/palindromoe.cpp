#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool isFim(char *s){
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}



bool ehPalindromo(char *palin, int posicao) {
    int ult = strlen(palin)-1;

    bool ehPalin = false;

    for(int i = 0; i < ult/2; i++) {
        if (palin[i] == palin[ult - i -1]) {
            ehPalin = true;
        } else {
            ehPalin = false;
            i = ult;
        }
    }

    return ehPalin;
    
}

void print(bool ehPalin){
    if(ehPalin == true){
        printf("SIM\n");
    }else{
        printf("NAO\n");
    }
}
    


int main() {

    char entrada[1000][1000];
    int numEntradas = 0;

    do {
        fgets(entrada[numEntradas], 1000, stdin);
    } while (isFim(entrada[numEntradas++]) == false);
    numEntradas--;

    for (int i = 0; i < numEntradas; i++) {
        print(ehPalindromo(entrada[i]));
    }

    return 0;
    
}