#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

char **split(char *s, char *caracter) {
      int n = 0;      
      //Separa em tokens separados por espaço
      char *token;
      token = strtok(s, " ");

      while(token != NULL) {
            split[n] = strdup(token);
            token = strtok(NULL," ");
      n++;
      }
      return split;
}
//fim split

char *getComando(char *s) {
      char **x;
      x = split(s, " ");
      return x[0];
}

int main() {
   char *t = "R1 23 TESTE";
   char *x = getComando(t);
   printf("%s", x);
   
   return(0);
}