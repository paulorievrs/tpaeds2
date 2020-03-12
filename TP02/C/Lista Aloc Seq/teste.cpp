#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>


char* retornarComando(char *s) {
    char *retorno = (char*) malloc(1000);
    int j = 0;
    for(int i = 3; i < strlen(s); i++) {
        if(s[i] == ' ') {
            i = strlen(s);
        }
        retorno[j] = s[i];
        j++;
    }


    return retorno;

}


int main()
{
    char *algo = "I* 23 /tmp/personagens/Palpatine.txt";
    char *ret = retornarComando(algo);

    printf("%s", ret);
    

    return 0;
}