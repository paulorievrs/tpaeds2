#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool isFim(char entrada[]);
void leArq(char entrada[], char conteudo[]);
void separa(char conteudo[], char relevante[]);
void criaPersonagem(char relevante[]);

typedef struct{

    char nome[100];
    int altura = 0;
    double peso = 0.0;
    char corDoCabelo[100];
    char corDaPele[100];
    char corDosOlhos[100];
    char anoNascimento[100];
    char genero[100];
    char homeworld[100];


}Personagem;

int main(){
    char entrada[1000];
    char conteudo[1000];
    char relevante[1000];
    do{
        scanf("%s", entrada);
        if(!isFim(entrada)){
            leArq(entrada, conteudo);
            separa(conteudo, relevante);
            //criaPersonagem(relevante);
            puts(conteudo);

            //printf("%s", conteudo);
            //printf("%s", relevante);
        }

    }while(!isFim(entrada));


}

bool isFim(char entrada[]){
        return (entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M');

}

void leArq(char entrada[], char conteudo[]){

    FILE *arq = fopen(entrada, "r");

    fgets(conteudo, 1000, arq);
    fclose(arq);

}

void separa(char conteudo[], char relevante[]){
        bool done = false;
        int i = 1;
        do{
            if(conteudo[i] == ',' && conteudo[(i+3)] == 'f' && conteudo[(i+4)] == 'i' && conteudo[(i+5)] == 'l' && conteudo[(i+6)] == 'm' && conteudo[(i+7)] == 's'){
                done = true;
            }
            if(!done){
                relevante[i-1] = conteudo[i];
            }
            i++;
        }while(!done);

}

void criaPersonagem(char relevante[]){

    char nome[50];
    int p = 0;

    for(int i = 0; i < strlen(relevante)-1; i++){
        if(relevante[i] == 'n' && relevante[i+1] == 'a' && relevante[i+2] == 'm' && relevante[i+3] == 'e'){
            i += (3+5);
            do{
                nome[p] += relevante[i];
                i++;
                p++;
            }while(relevante[i] != '\'');
        }

    }
    printf("%s\n", nome);
}

