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

Personagem personagemA[TAM];
int n;

//INICIO FUNCOES DE LISTA
void start() {
      n = 0;
}
void inserirInicio(Personagem personagem) {
      if (n >= TAM) {
               printf("Erro em inserirInicio");
            exit(1);
      }

      for(int i = n; i > 0; i--) {
            personagemA[i] = personagemA[i-1];
      }
      personagemA[0] = personagem;
      n++;
}

void inserir(Personagem personagem, int posicao) {
      if (n >= TAM ||posicao < 0 || posicao > n) {
            printf("Erro ao inserir");
            exit(1);

      }
      for(int i = n; i > posicao; i--) {
            personagemA[i] = personagemA[i - 1];
      }
      personagemA[posicao] = personagem;
      n++;
}

void inserirFim(Personagem personagem) {
      if (n >= TAM) {
            printf("Erro ao inserirFim com");
            exit(1);
      }
      personagemA[n] = personagem;
      n++;
}

Personagem removerInicio() {
      if (n == 0) {
            printf("\nErro ao removerInicio\n");
      }
      Personagem personagemRetorno = personagemA[0];
      n--;
      for(int i = 0; i < n; i++) {
            personagemA[i] = personagemA[i+1];
      }

      return personagemRetorno;

}

Personagem remover(int posicao) {
      if (n == 0 || posicao < 0 || posicao >= n) {

            printf("Erro ao remover");
	   }

      Personagem personagemRetorno = personagemA[posicao];
      n--;
      for (int i = posicao; i < n; i++) {
            personagemA[i] = personagemA[i + 1];
      }
      return personagemRetorno;
}

Personagem removerFim() {
      if (n == 0) {
            printf("Erro removerFim");
            exit(1);
      }

      return personagemA[--n];
}
//FIM FUNÇÕES DE LISTA }


char* getComando(char *s) {
    char *retorno = (char*) malloc(2);
    if(s[0] == 'I' && s[1] == 'I') {
        retorno[0] = 'I';
        retorno[1] = 'I';
    }
    if(s[0] == 'I' && s[1] == 'F') {
        retorno[0] = 'I';
        retorno[1] = 'F';
    }
    if(s[0] == 'R' && s[1] == '*') {
        retorno[0] = 'R';
        retorno[1] = '*';
    }
    if(s[0] == 'R' && s[1] == 'I') {
        retorno[0] = 'R';
        retorno[1] = 'I';
    }
    if(s[0] == 'R' && s[1] == 'F') {
        retorno[0] = 'R';
        retorno[1] = 'F';
    }
    if(s[0] == 'I' && s[1] == '*') {
        retorno[0] = 'I';
        retorno[1] = '*';
    }

    return retorno;
}
char* getArquivosSemPos(char *s) {

    char *retorno = (char*) malloc(1000);
    int j = 0;
    for(int i = 3; i < strlen(s); i++) {
        retorno[j] = s[i];
        j++;
    }


    return retorno;
      
}
char* getArquivosComPos(char *s) {
      char *retorno = (char*) malloc(1000);
      int j = 0;
      for(int i = 6; i < strlen(s); i++) {
        retorno[j] = s[i];
        j++;
    }


    return retorno;
}


int getPosicao(char *s) {
    char retorno[5];
    int j = 0;
    for(int i = 3; i < strlen(s); i++) {
        if(s[i] == ' ') {
            i = strlen(s);
        }
        retorno[j] = s[i];
        j++;
    }

    int x;
    x = atoi(retorno);

    return x;
}

void comandoII(char *pubin, Personagem personagem, char *arquivo) {
  arquivo = getArquivosSemPos(pubin);
  arquivo = lerArquivo(arquivo);
  personagem.nome = acharNome(arquivo);
  personagem.altura = altura(arquivo);
  personagem.peso = 0;
  personagem.corDoCabelo = corDoCabelo(arquivo);
  personagem.corDaPele = corDaPele(arquivo);
  personagem.anoNascimento = nascimento(arquivo);
  personagem.corDosOlhos = corDosOlhos(arquivo);
  personagem.genero = genero(arquivo);
  personagem.homeworld = homeworld(arquivo);
  inserirInicio(personagem);
}

void comandoIF(char *pubin, Personagem personagem, char *arquivo) {
  arquivo = getArquivosSemPos(pubin);
  arquivo = lerArquivo(arquivo);
  personagem.nome = acharNome(arquivo);
  personagem.altura = altura(arquivo);
  personagem.peso = 0;
  personagem.corDoCabelo = corDoCabelo(arquivo);
  personagem.corDaPele = corDaPele(arquivo);
  personagem.anoNascimento = nascimento(arquivo);
  personagem.corDosOlhos = corDosOlhos(arquivo);
  personagem.genero = genero(arquivo);
  personagem.homeworld = homeworld(arquivo);
  inserirFim(personagem);

}
void comandoRP(char *pubin, int posicao, Personagem personagem) {
  posicao = getPosicao(pubin);
  personagem = remover(posicao);
  printf("(R) %s\n", personagem.nome);

}
void comandoRI(Personagem personagem) {
   personagem = removerInicio();
   printf("(R) %s\n", personagem.nome);
    
}
void comandoRF(Personagem personagem) {
  personagem = removerFim();
  printf("(R) %s\n", personagem.nome);

  
}
void comandoIP(char *pubin, Personagem personagem, int posicao, char *arquivo) {
  arquivo = getArquivosComPos(pubin);
  arquivo = lerArquivo(arquivo);
  posicao = getPosicao(pubin);
  personagem.nome = acharNome(arquivo);
  personagem.altura = altura(arquivo);
  personagem.peso = 0;
  personagem.corDoCabelo = corDoCabelo(arquivo);
  personagem.corDaPele = corDaPele(arquivo);
  personagem.anoNascimento = nascimento(arquivo);
  personagem.corDosOlhos = corDosOlhos(arquivo);
  personagem.genero = genero(arquivo);
  personagem.homeworld = homeworld(arquivo);
  inserir(personagem, posicao);
}



void fazerComandosDaLista(char *pubin, int p, Personagem personagem) {
      char *comando = getComando(pubin);
      char *arquivo = (char*) malloc(1000 * sizeof(char));
      int posicao = 0;

      if (strcmp(comando, "II") == 0) {
            comandoII(pubin, personagem, arquivo);
      }
      if (strcmp(comando, "IF") == 0) {
            comandoIF(pubin, personagem, arquivo);
      }
      if (strcmp(comando, "R*") == 0)  {
            comandoRP(pubin, posicao, personagem);

      }
      if (strcmp(comando, "RI") == 0)  {
            comandoRI(personagem);
      }
      if (strcmp(comando, "RF") == 0)  {
        
          comandoRF(personagem);
      }
      if (strcmp(comando, "I*") == 0)  {
            comandoIP(pubin, personagem, posicao, arquivo);

      }
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
      int cont = 0;

      for (int i = 0; i < strlen(s); i++) {

            if (s[i] == 'n' && s[i+1] == 'a' && s[i+2] == 'm' && s[i+3] == 'e') {
                  j = i+8;
                  while (s[j] != '\'') {
                        nome[p] = s[j];
                        p++;
                        j++;
                        cont++;
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
            inserirFim(personagemEntradaPadrao[i]);
      }

      //For par fazer os comandos da lista
      Personagem personagens[TAM];
      for(int i = 0; i < valor; i++) {
            scanf(" %[^\n]", pubin[i]);
            fazerComandosDaLista(pubin[i], valor, personagens[i]);
      }
      for(int i = 0; i < n; i++) {
            printf("[%d]  ", i);
            printPersonagem(personagemA[i]);
      }


}