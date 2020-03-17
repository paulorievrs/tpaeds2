public class Teste {

	public static boolean seTemMatrizDentroDaOutra(int[][] matriz1, int[][] matriz2) {
		boolean temMatrizDentroDaOutra = false;
		int iMaior = matriz1[0].length;
		int iMenor = matriz1[1].length;
		int jMaior = matriz2[0].length;
		int jMenor = matriz2[1].length;

		for(int i = 0; i <= (iMaior - iMenor); i++) {
			for(int j = 0; j <= jMaior - jMenor; j++) {

				for(int k = 0; k < iMenor; i++) {
					for(int l = 0; l < jMenor; j++) {

						if(matriz1[i+k][j+l] != matriz2[k][l]) {
							l = jMenor;
							k = iMenor;
						}
					}
				}
			}
		}


		return temMatrizDentroDaOutra;

	}


	public static int desempilha(int[] array, int n) {
		int x = array[--n];
		System.out.println("X = " + n);
		return array[--n];
	}

	public static int[] retirarInteiro(int[] array, int vaiSerRemovido, int n) throws Exception{
		if (array.length == 0) {
			throw new Exception("Array vazio");
		}

		

		int posicao = 0;
		int[] aux = new int[array.length];

		for(int i = 0; i < array.length; i++) {
			if(array[i] != vaiSerRemovido)
				
				aux[i] = desempilha(array, n);
				n--;
		}

		for(int i = 0; i < array.length; i++) {
			System.out.println("A = " +aux[i]);
		}


		return aux;

	}



	public static void main (String args[])  throws Exception{
		int[][] matriz = new int[3][3];

		int[] n = {1,2,3,4,5, 0};
		retirarInteiro(n, 3, 6);
	


	/*for(int i = 0; i < matriz.length; i++) {
		for(int j = 0; j < matriz.length; j++) {
			if(i + j == matriz.length - 1) {
				System.out.printf("(%d) ", matriz[i][j]); // mudança
   			} else {
   				System.out.printf("%d ", matriz[i][j]); 
   			}
		}	
		System.out.printf("\n");
	}*/

	}
}