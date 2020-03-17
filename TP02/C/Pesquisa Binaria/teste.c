#include <stdio.h>
#include <stdlib.h>

int main() {

	int m[4][4];
	int di[4][4];

	for(int i = 0; i < 4; i++) {
		for(int j = 0; j < 4; j++) {
			m[i][j] = j+1;
		}
	}

	for(int j = 0; j<4; j++ ){
    di[j] = m[j][3-j];
}

//Repõe a diagonal na direção inversa

	for(int j = 0; j<4; j++ ){
    	m[j][3-j] = di[3-j];
	}  
	printf("%d", m);

}
