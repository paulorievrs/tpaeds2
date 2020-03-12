#include <stdio.h>
#include <stdlib.h>

int main() {
	/*M =  C*(1+i)^t
	
	//Rende 1,13% ao mes. ler valor aplicado e calcule o valor */

	float valorAplicado = 0.0;
	printf("Digite o valor aplicado: ");
	scanf("%f", &valorAplicado);
	printf("valorAplicado  = %f\n", valorAplicado);


	int tempo = 0;
	printf("\nDigite o tempo: ");
	scanf("%d", &tempo);
	printf("Tempo = %d\n", tempo);

	float base =  valorAplicado * (1+ 1.13);


	float montante = 1.0;
	for(int i = 1; i <= tempo; i++) {
		montante *= base;

	}

	printf("montante e de %f\n", montante);
}