/**
 * PONTIFÍCIA UNIVERSIDADE CATÓLICA DE MINAS GERAIS 
 * ALGORITMOS E ESTRUTURAS DE DADOS II
 * TP03Q04 - quickSort com Lista Dinâmica Duplamente Encadeada em C
 * @author Eduardo Henrique Souza Paraíso
 * @version 01/2020
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define TAM 1000

typedef struct Personagem {
    char *_nome;
    char *_corCabelo;
    char *_corPele;
    char *_corOlho;
    char *_anoNascimento;
    char *_genero;
    char *_terraNatal;

    int _altura;
    double _peso;

} Personagem;

/**
 * Aloca memória para um vetor de cha igual a TAM vezes o seu tipo
 * @param vetor [char*] vetor a ser alocado
 * @return [char*] vetor alocado
*/
char* alocaVetor(char *vetor){
	vetor = (char*) malloc(TAM * sizeof(char));
    return vetor;
}

/**
 * Lê um arquivo designado e retorna todo seu conteúdo
 * @param nomeArquivo [char*] nome do arquivo a ser aberto (local + nome + extensão)
 * @return [char*] todo o conteúdo do arquivo 
*/
char *leArquivo(char *nomeArquivo){
    FILE *arquivo;
    
    char *conteudo;
    conteudo = alocaVetor(conteudo);

    arquivo = fopen(nomeArquivo, "r");
    fgets(conteudo, 1000, arquivo);
   
    return conteudo;
}

/**
 * Função semelhante ao substring da linguagem Java
 * Retorna uma substring a partir de um ponto de interesse
 * @param padrao [char*] ponto de interesse
 * @param conteudo [char*] string principal a ser clivada
 * @return [char*] todo o conteúdo da string principal a partir da primeira ocorrência da string padrão
*/
char *substring(char *padrao, char *conteudo){
	char *substring = strstr(conteudo, padrao);

	return strdup(substring);
}

/**
 * Função semelhante a função substring, porém cliva a string principal entre um offset definido e a ocorrencia do primeiro limite
 * @param conteudo [char*] string principal a ser clivada
 * @param offset [int] valor de deslocamento a partir do inicio da string principal
*/ 
char *stringOffiset(char *conteudo, int offset, char limite){
    char *substring = alocaVetor(substring);

    for(int i = offset; conteudo[i] != limite; i++){
        substring[i-offset] = conteudo[i];
    }

    return substring;
}

/**
 * Realiza a busca da informação dentro de uma string
 * @param conteudo [char*] string principal que contém as informações buscadas
 * @param informacao [char*] conjunto especifico de strings a ser buscado:
 *                   [name] = nome da personagem
 *                   [height] = altura da personagem
 *                   [mass] = peso da personagem
 *                   [hair_color] = cor do cabelo da personagem
 *                   [skin_color] = cor da pele da personagem
 *                   [eye_color] = cor dos olhos da personagem
 *                   [birth_year] = ano de nascimento da personagem
 *                   [gender] = genêro da personagem
 *                   [homeworld] = terra natal da personagem
 * @param offset [int] valor de deslocamento conforme informação inserida
 *                   [name] = 8
 *                   [height] = 10
 *                   [mass] = 8
 *                   [hair_color] = 14
 *                   [skin_color] = 14
 *                   [eye_color] = 13
 *                   [birth_year] = 14
 *                   [gender] = 10
 *                   [homeworld] = 13
*/
char *buscaInfo(char *conteudo, char *informacao, int offset){    
    char *tmp = alocaVetor(tmp);
    char *resultado = alocaVetor(resultado);

    tmp = substring(informacao, conteudo);
    resultado = stringOffiset(tmp, offset,'\'');

    if(strcmp(informacao,"mass") == 0){
        memset(tmp, 0, sizeof(char*));
        for(int i = 0, j = 0; i < strlen(resultado); i++){
            if(resultado[i] != ',') {
                tmp[j] = resultado[i];
                j++;               
            }
        }
        strcpy(resultado, tmp);
    }

    free(tmp);
    return resultado;   
}

/**Exibe na tela as informações da personagem*/
void exibeInfo(Personagem starWars){
    fprintf(stdout, " ## ");
    fprintf(stdout, "%s ## ", starWars._nome);
    fprintf(stdout, "%d ## ", starWars._altura);
    fprintf(stdout, "%g ## ", starWars._peso);
    fprintf(stdout, "%s ## ", starWars._corCabelo);
    fprintf(stdout, "%s ## ", starWars._corPele);
    fprintf(stdout, "%s ## ", starWars._corOlho);
    fprintf(stdout, "%s ## ", starWars._anoNascimento);
    fprintf(stdout, "%s ## ", starWars._genero);
    fprintf(stdout, "%s ## ", starWars._terraNatal);
    fprintf(stdout, "\n");
}

//========================================================================================
typedef struct Celula_Dupla{
    Personagem personagem;
    struct Celula_Dupla *proximo;
    struct Celula_Dupla *anterior;
} Celula_Dupla;

Celula_Dupla *novaCelula_Dupla(Personagem elemento){
    Celula_Dupla *nova = (Celula_Dupla*) malloc(sizeof(Celula_Dupla));
    (*nova).personagem = elemento;
    (*nova).anterior = (*nova).anterior = NULL;

    return nova;
}

Celula_Dupla *start_Celula(){
    Celula_Dupla *nova = (Celula_Dupla*) malloc(sizeof(Celula_Dupla));
    (*nova).anterior = (*nova).anterior = NULL;

    return nova;
}
//========================================================================================
Celula_Dupla *primeiro;
Celula_Dupla * ultimo;
int nMovimentos;
int nComparacoes;
int nElementos;

void start(){
    primeiro = start_Celula();
    ultimo = primeiro;
    nMovimentos = 0;
    nComparacoes = 0;
    nElementos = 0;
}


void inserirFim(Personagem personagem){
    (*ultimo).proximo = novaCelula_Dupla(personagem);
    (*ultimo).proximo->anterior = ultimo;
    ultimo = (*ultimo).proximo;
    nElementos++;
}

/** Exibe lista na tela */
void exibeLista(){
    Celula_Dupla *i;
    for (i = (*primeiro).proximo; i != NULL; i = (*i).proximo) {
        exibeInfo((*i).personagem);
    }
}

//=================================================================================================================
void swap(Celula_Dupla *i, Celula_Dupla *j){
    Personagem tmp = (*i).personagem;
    (*i).personagem = (*j).personagem;
    (*j).personagem = tmp;
    
    nMovimentos += 3;
}

int mostraPosicao(Celula_Dupla *celula){
    int posicao = 0;
    for(Celula_Dupla *i = (*primeiro).proximo; i != NULL && i != celula; i = (*i).proximo, posicao++);

    return posicao;
}

Celula_Dupla *buscaPivo(int inicio, int fim){
    Celula_Dupla *pivo = (*primeiro).proximo;
    int posicao = (inicio + fim)/2;

    for(int i = 0; pivo != NULL && i < posicao; pivo = (*pivo).proximo, i++);
    return pivo;
}

void quickSort_DP(Celula_Dupla *inicio, Celula_Dupla *fim){
    Celula_Dupla *i = inicio;
    Celula_Dupla *j = fim;

    int pos_inicio = mostraPosicao(inicio);
    int pos_fim = mostraPosicao(fim);

    Celula_Dupla *pivo = buscaPivo(pos_inicio, pos_fim);

    int pos_i = pos_inicio;
    int pos_j = pos_fim;

    while((*pivo).anterior != j && (*pivo).proximo != i){
        while(i != ultimo && (strcmp((*i).personagem._corCabelo, (*pivo).personagem._corCabelo) < 0) || i != ultimo && (strcmp((*i).personagem._corCabelo, (*pivo).personagem._corCabelo) == 0 && strcmp((*i).personagem._nome, (*pivo).personagem._nome) < 0)){
            if((*i).proximo != NULL){
                i = (*i).proximo;
                pos_i++;
            }
            nComparacoes += 1;
        }

        while((*j).anterior != primeiro && (strcmp((*j).personagem._corCabelo, (*pivo).personagem._corCabelo) > 0) || (*j).anterior != primeiro && (strcmp((*j).personagem._corCabelo, (*pivo).personagem._corCabelo) == 0 && strcmp((*j).personagem._nome, (*pivo).personagem._nome) > 0)){
            if((*j).anterior != NULL){
                j = (*j).anterior;
                pos_j--;
            }
            nComparacoes += 1;
        }

        if(pos_i <= pos_j){
            swap(i,j);
            i = (*i).proximo;
            j = (*j).anterior;

            pos_i++;
            pos_j--;
        }
    }

    if(pos_inicio < pos_j){
        quickSort_DP(inicio, j);
    }

    if(pos_i < pos_fim){
        quickSort_DP(i, fim);
    }

}

//========================================================================================
/**
 * Caso encontre uma linha com a palavra FIM, retornará TRUE e FALSE caso contrário
 * @param entrada é a string a ser avaliada
 * @return TRUE para FIM e FALSO para qualquer outra string
*/
bool procuraFIM(char *entrada){
    return (strlen(entrada) >= 3 && entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M');
}

void clean_stdin(){
    int c;
    do {
        c = getchar();
    } while (c != '\n' && c != EOF);
}

/**
 * Seta das as informações obtidas
 * @param card [Personagem] struct personagem
 * @param conteudo [char*] string com o conteudo do arquivo
 * @return [Personagem] struct toda setada
*/
Personagem setaDados(Personagem card, char *conteudo){
    card._nome = buscaInfo(leArquivo(conteudo), "name", 8);
    card._altura = atoi(buscaInfo(leArquivo(conteudo), "height", 10));
    card._peso = atof(buscaInfo(leArquivo(conteudo), "mass", 8));
    card._corCabelo = buscaInfo(leArquivo(conteudo), "hair_color", 14);
    card._corPele = buscaInfo(leArquivo(conteudo), "skin_color", 14);
    card._corOlho = buscaInfo(leArquivo(conteudo), "eye_color", 13);
    card._anoNascimento = buscaInfo(leArquivo(conteudo), "birth_year", 14);;
    card._genero = buscaInfo(leArquivo(conteudo), "gender", 10);
    card._terraNatal = buscaInfo(leArquivo(conteudo), "homeworld", 13);

    return card;
}

clock_t cronometro(){
    return clock();
}

/**
* Gera arquivo .txt com informações da ordenação, tais como tempo gasto, numero de comparações e numero de movimentações
* @param cronometroInicial
* @param cronometroFinal
*/
void logSort(clock_t inicio, clock_t final){
    FILE *log = fopen("matricula_quickSort2.txt", "w");
    double tempo = ((final - inicio) *1000.0)/CLOCKS_PER_SEC;
    fprintf(log, "Matricula: 00683400 \t Tempo(ns): %g \t Movimentos: %d \t Comparacoes: %d", tempo, nMovimentos, nComparacoes);
    fclose(log);
}

int main(){    
    char entrada[TAM][TAM];
    int numEntradas = 0;

    do{
        fscanf(stdin, "%s", entrada[numEntradas]);
    }while(procuraFIM(entrada[numEntradas++]) == false);
    numEntradas--;

    start();
    Personagem starWars;
    for(int i = 0; i < numEntradas; i++){   
        starWars = setaDados(starWars, entrada[i]);   
        inserirFim(starWars);
    }
    
  
    clock_t inicio = cronometro();
    quickSort_DP((*primeiro).proximo, ultimo);
    clock_t final = cronometro();
    exibeLista();   

    logSort(inicio, final);

    return 0;
}
