import java.util.*;

public class TP6 {
  public static void main(String args[]) {
  		Scanner s = new Scanner(System.in);
		int[] print = new int[100];
		int conta = 0;
  		int entrada = s.nextInt();
  		int maior = 0;
 		int cont = 0;
  		String atual;
		try {
  			while ((s.nextLine().equals(""))) {
    		String anterior = s.next();                                                       
   			for (int i = 0; i < entrada - 1; i++) {
    			atual = s.next();
    			for (int j = 0; j < atual.length(); j++) {
     				if (atual.charAt(j) == anterior.charAt(j)) {
      					cont++;
     				}
    			}
    			anterior = atual;
    			if (cont > maior)
     				maior = cont;
     			cont = 0;
   			}
			print[conta] = maior;
  			maior = 0;
   			cont = 0;
			conta++;
   			entrada = s.nextInt();
  			}
		} catch (Exception e) {
			//
		}
		int aux;
		for(int i = 0; i < conta; i++) {
			for(int j = 0; j < conta -1; j++) {
				if(print[j] > print[j + 1]) {
					aux = print[j];
					print[j] = print[j+1];
					print[j+1] = aux;
				}
			}
		}

		for(int i = 0; i < conta; i++) {
			MyIO.println(print[i]);
		}
 	}
}
