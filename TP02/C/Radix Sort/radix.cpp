#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define TAM 1000

#define SZ 1000

char* lerArquivo(char *nome);

typedef struct Personagem {
   char *nome;
   int altura;
   double peso;
   char *corDoCabelo;
   char *corDaPele;
   char *corDosOlhos;
   char *anoNascimento;
   char *genero;
   char *homeworld;

} Personagem;

void setPersonagem(Personagem personagem, char *arquivo);
char *acharNome(char *s);
int altura(char *s);
double massa(char *s);
char *corDoCabelo(char *s);
char *corDaPele(char *s);
char *nascimento(char *s);
char *corDosOlhos(char *s);
char *genero(char *s);
char *homeworld(char *s);

Personagem personagemA[TAM];
int n = 0;


void inserirFim(Personagem personagem) {
      if (n >= TAM) {
            printf("Erro ao inserirFim com");
            exit(1);
      }
      personagemA[n] = personagem;
      n++;
}

char *lerArquivo(char *nome) {
      FILE *arq;
      char *arquivo = (char*) malloc(SZ * sizeof(char));
      arq = fopen(nome, "r");
      fgets(arquivo, 1000, arq);
      fclose(arq);
      return arquivo; 
}

bool isFim(char *s){
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

char *acharNome(char *s) {
      char *nome = (char*) malloc(SZ * sizeof(char));
      int j = 0;
      int p = 0;
      char aspas = '\'';

      for (int i = 0; i < strlen(s); i++) {
            if (s[i] == 'n' && s[i+1] == 'a' && s[i+2] == 'm' && s[i+3] == 'e') {
                  j = i+8;

                  for(int k = j; k < strlen(s); k++) {
                        if(s[k] == aspas) {
                              nome[p] = '\0';
                              k = strlen(s);
                        } else {
                              nome[p] = s[k];
                              p++;
                        }
                  }
            }
      }

      return nome;

}

int altura(char *s) {
      char *altura = (char*) malloc(SZ * sizeof(char));
      int j = 0;
      int p = 0;
      char aspas = '\'';
      for (int i = 0; i < strlen(s); i++) {
            if (s[i] == 'h' && s[i+1] == 'e' && s[i+2] == 'i' && s[i+3] == 'g' && s[i+4] == 'h' && s[i+5] == 't') {
                  j = i+10;
                  while (s[j] != aspas) {
                        altura[p] = s[j];
                        p++;
                        j++;
                  }
            }     
      }
      return atoi(altura);
}

double massa(char *s) {
      char *massa = (char*) malloc(SZ * sizeof(char));
      int j = 0;
      int p = 0;
      char aspas = '\'';
      for (int i = 0; i < strlen(s); i++) {
            if (s[i] == 'm' && s[(i+1)] == 'a' && s[(i+2)] == 's' && s[(i+3)] == 's') {
                  j = i+8;
                  while (s[(j)] != aspas) {
                        if(s[j] != ',') {
                              massa[p] = s[j];
                              p++;
                        }
                        j++;
                  }
            }
      }
      return atof(massa);
}

char *corDoCabelo(char *s) {
      char *cor = (char*) malloc (SZ * sizeof(char));
      int j = 0;
      int p = 0;
      char aspas = '\'';
      for (int i = 0; i < strlen(s); i++) {
            if (s[(i)] == 'h' && s[(i+1)] == 'a' && s[(i+2)] == 'i' && s[(i+3)] == 'r') {
                  j = i+14;
                  while (s[j] != aspas) {
                        cor[p] = s[j];
                        p++;
                        j++;
                  }
            }
      }
      return cor;
}


char *corDaPele(char *s) {
      char *cor = (char*) malloc (SZ * sizeof(char));
      int j = 0;
      int p = 0;
      char aspas = '\'';
      for (int i = 0; i < strlen(s); i++) {
            if (s[i] == 's' && s[i+1] == 'k' && s[(i+2)] == 'i' && s[(i+3)] == 'n') {
                  j = i+14;
                  while (s[(j)] != aspas) {
                        cor[p] += s[(j)];
                        p++;
                        j++;
                  }
            }
      }
      return cor;
}

char *corDosOlhos(char *s) {
      char *cor = (char*) malloc(SZ * sizeof(char));
      int j = 0;
      int p = 0;
      char aspas = '\'';
      for (int i = 0; i < strlen(s); i++) {
            if (s[(i)] == 'e' && s[(i+1)] == 'y' && s[(i+2)] == 'e') {
                  j = i+13;
                  while (s[(j)] != aspas) {
                        cor[p] += s[(j)];
                        p++;
                        j++;
                  }
            }
      }
      return cor;
}

char *nascimento(char *s) {
      char *nascimento = (char*) malloc(SZ * sizeof(char));
      int j = 0;
      int p = 0;
      char aspas = '\'';
      for (int i = 0; i < strlen(s); i++) {
            if (s[(i)] == 'b' && s[(i+1)] == 'i' && s[(i+2)] == 'r' && s[(i+3)] == 't' && s[(i+4)] == 'h') {
                  j = i+14;
                  while (s[(j)] != aspas) {
                        nascimento[p] += s[(j)];
                        p++;
                        j++;
                  }
            }
      }
      return nascimento;
}

char *genero (char *s) {
      char *genero = (char*) malloc(SZ * sizeof(char));
      int j = 0;
      int p = 0;
      char aspas = '\'';
      for (int i = 0; i < strlen(s); i++) {
            if (s[(i)] == 'g' && s[(i+1)] == 'e' && s[(i+2)] == 'n' && s[(i+3)] == 'd' && s[(i+4)] == 'e' && s[(i+5)] == 'r') {
                  j = i+10;
                  while (s[(j)] != aspas) {
                        genero[p] += s[(j)];
                        p++;
                        j++;
                  }
            }
      }
      return genero;
}

char *homeworld(char *s) {
      char *homeworld = (char*) malloc(SZ * sizeof(char));
      int j = 0;
      int p = 0;
      char aspas = '\'';
      for (int i = 0; i < strlen(s); i++) {
            if (s[(i)] == 'h' && s[(i+1)] == 'o' && s[(i+2)] == 'm' && s[(i+3)] == 'e' && s[(i+4)] == 'w' && s[(i+5)] == 'o' && s[(i+6)] == 'r' && s[(i+7)] == 'l' && s[(i+8)] == 'd') {
                  j = i+13;
                  while (s[(j)] != aspas) {
                        homeworld[p] += s[(j)];
                        p++;
                        j++;
                  }
            }
      }
      return homeworld;
}


void printPersonagem(Personagem personagem) {
      printf("\n ## %s ## %d ## %g ## %s ## %s ## %s ## %s ## %s ## %s ## \n", personagem.nome, personagem.altura, personagem.peso, personagem.corDoCabelo, personagem.corDaPele, personagem.corDosOlhos, personagem.anoNascimento, personagem.genero, personagem.homeworld);
}




//RADIX
// A utility function to get maximum value in arr[]

int getMax() { 
    int mx = personagemA[0].altura; 
    for (int i = 1; i < n; i++) 
        if (personagemA[i].altura > mx) 
            mx = personagemA[i].altura; 
    return mx; 
} 
  
// A function to do counting sort of arr[] according to 
// the digit represented by exp. 
void countSort(int exp) { 
    int output[n]; // output array 
    int i = 0;
    int count[9] = {0};
    int aux = 0;
  
    // Store count of occurrences in count[] 
    for (i = 0; i < n; i++) 
        aux = (personagemA[i].altura/exp)%9; 
        count[aux]++; 
  
    // Change count[i] so that count[i] now contains actual 
    //  position of this digit in output[] 
    for (i = 1; i < 9; i++) 
        count[i] += count[i - 1]; 
  
    // Build the output array 
    for (i = n - 1; i >= 0; i--) { 

        aux = (personagemA[i].altura/exp)%9;
        printf("\n aux = %d", aux);
        output[count[aux] - 1] = personagemA[i].altura; 

        count[(personagemA[i].altura/exp)%9]--; 
    } 
  
    // Copy the output array to arr[], so that arr[] now 
    // contains sorted numbers according to current digit 
    for (i = 0; i < n; i++) 
        personagemA[i].altura = output[i]; 
} 
  
// The main function to that sorts arr[] of size n using  
// Radix Sort 
void radixsort() 
{ 
    // Find the maximum number to know number of digits 
    int m = getMax(); 
  
    // Do counting sort for every digit. Note that instead 
    // of passing digit number, exp is passed. exp is 9^i 
    // where i is current digit number 
    for (int i = 1; m/i > 0; i *= 9) 
        countSort(i); 
} 

int main() {
      char entrada[SZ][SZ]; //Array de string (matriz de char)
      int quantidadeEntrada = 0;
      char *arquivo; //armazenar nome do arquivo
      
      //ler até aparecer fim na entrada padrão
      do {
            scanf(" %[^\n]", entrada[quantidadeEntrada]);
      } while(isFim(entrada[quantidadeEntrada++]) == false);
      quantidadeEntrada--;

      Personagem personagemEntradaPadrao[TAM]; //Array para armazenar os personagens da entrada padrão      
      //Armazenar personagens do pub in na lista
      for(int i = 0; i < quantidadeEntrada; i++) {

            arquivo = lerArquivo(entrada[i]); 
            personagemEntradaPadrao[i].nome = acharNome(arquivo);
            personagemEntradaPadrao[i].altura = altura(arquivo);
            personagemEntradaPadrao[i].peso = massa(arquivo);
            personagemEntradaPadrao[i].corDoCabelo = corDoCabelo(arquivo);
            personagemEntradaPadrao[i].corDaPele = corDaPele(arquivo);
            personagemEntradaPadrao[i].anoNascimento = nascimento(arquivo);
            personagemEntradaPadrao[i].corDosOlhos = corDosOlhos(arquivo);
            personagemEntradaPadrao[i].genero = genero(arquivo);
            personagemEntradaPadrao[i].homeworld = homeworld(arquivo);
            inserirFim(personagemEntradaPadrao[i]);
      }
      int k = 9;

      radixsort();


      for(int i = 0; i < quantidadeEntrada; i++) {
            printPersonagem(personagemA[i]);
      } 


      
}