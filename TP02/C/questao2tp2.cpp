#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Personagem{
        char *nome;
        int altura;
        double peso;
        char *corDoCabelo;
        char corDaPele[100];
        char corDosOlhos[100];
        char anoNascimento[100];
        char genero[100];
        char homeworld[100];
}Personagem;

/*char *atributos(char *s){
	int j=0;
	int cont=0;
	char *t = (char*) malloc(1000 * sizeof(char));
	int aux = 0;
	for(int i=0;i<strlen(s);i++){
		if(s[i]=='n'&&s[i+1]=='a'&&s[i+2]=='m'&&s[i+3]=='e'){
			aux = i+8;
			while(s[aux]!=' '){
			t[j]=s[aux];
			
			j++;
			aux++;
			
			}
		}
	}
	puts(t);
	return t;
}*/

int main(){
	FILE *arq;
	char *nomeArq;
	char *dados;
	char *dada;
	char *t;
        dada = (char*) malloc(500 * sizeof(char));
	Personagem personagem;
	int cont=0;

	
	do{
		scanf(" %[^\n]", nomeArq);

		if(nomeArq[0]!='F'){
			cont++;
			arq=fopen(nomeArq,"r");
			fgets(dados, 1000, arq);
		//	printf("%s",dados);
			//personagem.nome=atributos(dados);
			puts(dados);
		//	printf("%s",personagem.nome);
			//printf("%s",personagem.nome);
			/*personagem[cont].altura=altura(dados);
			personagem[cont].peso=peso(dados);
			personagem[cont].corDoCabelo=corDoCabelo(dados);
			personagem[cont].corDaPele=corDaPele(dados);
			personagem[cont].corDosOlhos=corDosOlhos(dados);
			personagem[cont].anoNascimento=anoNascimento(dados);
			personagem[cont].genero=genero(dados);
			personagem[cont].homeworld(dados);
			printar(nome,altura,peso,corDoCabelo,corDaPele,corDosOlhos,anoNascimento,genero,homeworld);*/
		}
	}while(nomeArq[0]!='F');
	return 0;
}

