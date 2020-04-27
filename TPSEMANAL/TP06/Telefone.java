import java.io.*;
import java.util.*;

public class Telefone {
 
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int maior = 0;
        String anterior =new String();
        String atual = new String();

        while(s.nextLine() != null) {
            
            int qtd = s.nextInt();
            anterior = s.nextLine();
            int cont = 0;
            MyIO.println(anterior);
           /*for(int i = 0; i < qtd; i++) {
                atual = s.nextLine();
                for(int j = 0; j < atual.length(); j++) {

                    if(atual.charAt(j) == anterior.charAt(j)) {
                        cont++;
                    }
                anterior = atual;
                }
                if(cont > maior)
                maior = cont;
                cont = 0;
            }
            MyIO.println(maior);
            maior = 0;
            cont = 0;*/
        }

    }
}