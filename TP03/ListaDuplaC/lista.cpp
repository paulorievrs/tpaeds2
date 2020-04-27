/*
* Autor: Paulo Rievrs Oliveira
* Struct V-1.0
*/

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
void limparEspacoDaCadeia(char *s);

///TIPO CELULA ===================================================================
typedef struct CelulaDupla {
      Personagem elemento;        // Elemento inserido na celula.
      struct CelulaDupla* prox; // Aponta a celula prox.
      struct CelulaDupla* ant;  // Aponta a celula ant.
} CelulaDupla;

CelulaDupla* novaCelulaDupla(Personagem elemento) {
   CelulaDupla* nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
   nova->elemento = elemento;
   nova->ant = NULL;
   nova->prox = NULL;
   return nova;
}

//LISTA PROPRIAMENTE DITA =======================================================
CelulaDupla* primeiro;
CelulaDupla* ultimo;


/**
 * Cria uma lista dupla sem elementos (somente no cabeca).
 */
void start () {
   Personagem x;
   primeiro = novaCelulaDupla(x);
   ultimo = primeiro;
}

void inserirFim(Personagem x) {
   
   ultimo->prox = novaCelulaDupla(x);

   ultimo->prox->ant = ultimo;
   ultimo = ultimo->prox;
}

void swap(CelulaDupla *i, CelulaDupla *j){
    Personagem tmp = i->elemento;
    i->elemento = j->elemento;
    j->elemento = tmp;
    
}

int getPosticao(CelulaDupla *pos){
    int posicao = 0;
    for(CelulaDupla *i = primeiro->prox; i != NULL && i != pos; i = i->prox, posicao++);

    return posicao;
}

CelulaDupla *getPivo(int inicio, int fim){
    CelulaDupla *pivo = primeiro->prox;
    int pos = (inicio + fim)/2;

    for(int i = 0; pivo != NULL && i < pos; pivo = pivo->prox, i++);
    return pivo;
}


void quicksort(CelulaDupla *esq, CelulaDupla *dir){
    CelulaDupla *i = esq;
    CelulaDupla *j = dir;

    int inicio = getPosticao(esq);
    int fim = getPosticao(dir);

    CelulaDupla *pivo = getPivo(inicio, fim);

    int posi = inicio;
    int posj = fim;

    while(pivo->ant != j && pivo->prox != i){
        while(i != ultimo && (strcmp(i->elemento.corDoCabelo, (*pivo).elemento.corDoCabelo) < 0) || i != ultimo && (strcmp(i->elemento.corDoCabelo, (*pivo).elemento.corDoCabelo) == 0 && strcmp(i->elemento.nome, (*pivo).elemento.nome) < 0)){
            if(i->prox != NULL){
                i = i->prox;
                posi++;
            }
        }

        while(j->ant != primeiro && (strcmp(j->elemento.corDoCabelo, (*pivo).elemento.corDoCabelo) > 0) || j->ant != primeiro && (strcmp(j->elemento.corDoCabelo, (*pivo).elemento.corDoCabelo) == 0 && strcmp(j->elemento.nome, (*pivo).elemento.nome) > 0)){
            if(j->ant != NULL){
                j = j->ant;
                posj--;
            }
        }

        if(posi <= posj){
            swap(i,j);
            i = i->prox;
            j = j->ant;

            posi++;
            posj--;
        }
    }

    if(inicio < posj){
        quicksort(esq, j);
    }

    if(posi < fim){
        quicksort(i, dir);
    }

}

int tamanho() {
   int tamanho = 0; 
   CelulaDupla* i;
   for(i = primeiro; i != ultimo; i = i->prox, tamanho++);
   return tamanho;
}

void printPersonagem(CelulaDupla *personagem) {
      printf(" ## %s ## %d ## %g ## %s ## %s ## %s ## %s ## %s ## %s ## \n", personagem->elemento.nome, personagem->elemento.altura, personagem->elemento.peso, personagem->elemento.corDoCabelo, personagem->elemento.corDaPele, personagem->elemento.corDosOlhos, personagem->elemento.anoNascimento, personagem->elemento.genero, personagem->elemento.homeworld);
}
void mostrar() {
   CelulaDupla* i;
   
   for (i = primeiro->prox; i != NULL; i = i->prox) {
            printPersonagem(i);
   }
   
}





///FIM LISTA ============================================================================================
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



int main() {
      start();

      char entrada[SZ][SZ]; //Array de string (matriz de char)
      int quantidadeEntrada = 0;
      char *arquivo; //armazenar nome do arquivo
      int valor = 0; //armazenar valor do inteiro
      char pubin[SZ][SZ];
      

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
      quicksort(primeiro->prox, ultimo);
    
      mostrar();
      
}