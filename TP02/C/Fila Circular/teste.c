#include <stdio.h>
#include <stdlib.h>

int x = 1;


int rounad(double number)
{
    return (number >= 0) ? (int)(number + 0.5) : (int)(number - 0.5);
}


int main() {
	double k = 5.6;
	int j = rounad(k);

	printf("%d", j);

}