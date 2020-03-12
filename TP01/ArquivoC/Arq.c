#include <stdio.h>
#include <stdlib.h>


int main() {

	FILE *arq;
	arq = fopen("arq.txt", "wb");
	
	int n;
	
	scanf("%d", &n);

	double entrada = 0;
	
	for(int i = 0; i < n; i++) {
	   scanf("%lf", &entrada);
	   fwrite(&entrada, sizeof(double), 1, arq);  //funcao fwrite escrevendo em arquivo binario 
	}

	fclose(arq); 
	arq = fopen("arq.txt", "rb");



	fseek(arq, 0, SEEK_END); //fseek para direcionar o ponteiro ao fim do arquivo
	long tamArq = ftell(arq); //para aqui dizer o tamanho dele para o for
	double numeros = 0;

	for (int i = tamArq - 8; i >= 0; i-=8) {
	    fseek(arq, i, SEEK_SET); //atualizando aonde fica o ponteiro do arquivo para ler de tras para frente
	    fread(&numeros, sizeof(double), 1, arq);
	   
	    printf("%g\n", numeros); //%g para remover os .0 de numeros inteiros.

	    
	}

	fclose(arq);
	





}
