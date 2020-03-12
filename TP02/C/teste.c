#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define SZ 10000

double massa(char *s) {
      char *massa = (char*) malloc(SZ * sizeof(char));
      int j = 0;
      int p = 0;
      char aspas = '\'';
      for (int i = 0; i < strlen(s); i++) {
            if (s[i] == 'm' && s[(i+1)] == 'a' && s[(i+2)] == 's' && s[(i+3)] == 's') {
                  j = i+8;
                  while (s[(j)] != aspas) {
                        massa[p] = s[j];
                        p++;
                        j++;
                  }
            }
      }
      return atof(massa);

}

int main() {
      char *entrada = (char*) malloc(SZ * sizeof(char));
      scanf(" %[^\n]", entrada);
      double saida = massa(entrada);
      printf("%g", saida);


}
