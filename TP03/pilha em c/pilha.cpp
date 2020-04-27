/*
* Autor: Paulo Rievrs Oliveira
* Struct V-1.0
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>


#define TAM 6

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

//TIPO CELULA ===================================================================
typedef struct Celula {
	Personagem elemento;        // Elemento inserido na celula.
	struct Celula* prox; // Aponta a celula prox.
} Celula;

Celula* novaCelula(Personagem elemento) {
   Celula* nova = (Celula*) malloc(sizeof(Celula));
   nova->elemento = elemento;
   nova->prox = NULL;
   return nova;
}

//global
Personagem personagemA[TAM];
int qtdPersonagens = 0;

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
    
      printf("## %s ## %d ## %g ## %s ## %s ## %s ## %s ## %s ## %s ## \n", personagem.nome, personagem.altura, personagem.peso, personagem.corDoCabelo, personagem.corDaPele, personagem.corDosOlhos, personagem.anoNascimento, personagem.genero, personagem.homeworld);
}


//PILHA PROPRIAMENTE DITA =======================================================
Celula* topo = NULL; // Sem celula cabeca.


/**
 * Insere elemento na pilha (politica FILO).
 * @param x Personagem elemento a inserir.
 */
void inserir(Personagem x) {
   Celula* tmp = novaCelula(x);
   tmp->prox = topo;
   topo = tmp;
   tmp = NULL;
   qtdPersonagens++;


}

/**
 * Remove elemento da pilha (politica FILO).
 * @return Elemento removido.
 */
Personagem remover() {
   if (topo == NULL) {
      printf("Erro ao remover!");
   }

   Personagem resp = topo->elemento;
   Celula* tmp = topo;
   topo = topo->prox;
   tmp->prox = NULL;
   free(tmp);
   tmp = NULL;
   qtdPersonagens--;

   return resp;
}


/**
 * Mostra os elementos separados por espacos, comecando do topo.
 */
void mostrar(Celula *i, int cont) {
	if(i != NULL) {
		mostrar(i->prox, cont - 1);
		printf("[%d]  ## %s ## %d ## %g ## %s ## %s ## %s ## %s ## %s ## %s ## \n", cont, i->elemento.nome, i->elemento.altura, i->elemento.peso, i->elemento.corDoCabelo, i->elemento.corDaPele, i->elemento.corDosOlhos, i->elemento.anoNascimento, i->elemento.genero, i->elemento.homeworld);
	}
}



char* getArquivos(char *s) {
    char *retorno = (char*) malloc(1000);
    int j = 0;
    for(int i = 2; i < strlen(s); i++) {
        retorno[j] = s[i];
        j++;
    }

    return retorno;
}

char getComando(char *s) {
    return s[0];
}

void comandoR(Personagem personagem) {
  personagem = remover();
  printf("(R) %s\n", personagem.nome); 
}

void comandoI(char *pubin, Personagem personagem, char *arquivo) {
  arquivo = getArquivos(pubin);
  arquivo = lerArquivo(arquivo);
  personagem.nome = acharNome(arquivo);
  personagem.altura = altura(arquivo);
  personagem.peso = massa(arquivo);
  personagem.corDoCabelo = corDoCabelo(arquivo);
  personagem.corDaPele = corDaPele(arquivo);
  personagem.anoNascimento = nascimento(arquivo);
  personagem.corDosOlhos = corDosOlhos(arquivo);
  personagem.genero = genero(arquivo);
  personagem.homeworld = homeworld(arquivo);
  inserir(personagem);
}

void fazerComandosDaLista(char *pubin, int p, Personagem personagem) {
      char comando = getComando(pubin);
      char *arquivo = (char*) malloc(1000 * sizeof(char));
      int posicao = 0;

      if (comando == 'I') {

        comandoI(pubin, personagem, arquivo);
        }
      if (comando == 'R') {
            comandoR(personagem);
      }
}

int main() {

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

      scanf("%d", &valor); //ler um inteiro após o fim (sempre vai ter)



      Personagem personagemEntradaPadrao[TAM]; //Array para armazenar os personagens da entrada padrão      
      //Armazenar personagens do pub in na lista
      for(int i = 0; i < quantidadeEntrada; i++) {

            arquivo = lerArquivo(entrada[i]); 
            personagemEntradaPadrao[i].nome = acharNome(arquivo);
            personagemEntradaPadrao[i].altura = altura(arquivo);
            personagemEntradaPadrao[i].peso = 0;
            personagemEntradaPadrao[i].corDoCabelo = corDoCabelo(arquivo);
            personagemEntradaPadrao[i].corDaPele = corDaPele(arquivo);
            personagemEntradaPadrao[i].anoNascimento = nascimento(arquivo);
            personagemEntradaPadrao[i].corDosOlhos = corDosOlhos(arquivo);
            personagemEntradaPadrao[i].genero = genero(arquivo);
            personagemEntradaPadrao[i].homeworld = homeworld(arquivo);
            inserir(personagemEntradaPadrao[i]);
      }

    Personagem personagem[TAM];
    for(int i = 0; i < valor; i++) {
        scanf(" %[^\n]", pubin[i]);
        fazerComandosDaLista(pubin[i], valor, personagem[i]);
    }

	

	Celula *i = topo;
	mostrar(i, qtdPersonagens - 1);	
    
     
}