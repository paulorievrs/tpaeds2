import java.util.*;

public class Lab03 {

    public static boolean isFim(String frase) {
        boolean isFim = false;
 
            if (frase.length() == 3) {
 
                    if (frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M') {
                         
                        isFim = true;
                    }
                }
             
            return isFim;
    }


    public static void main(String[] args) {

        String[] num1 = new String[1000];
        String[] num2 = new String[1000];
        int num3 = 0;
        int num4 = 0;
        String resultado = "";

        int quantidadeEntrada = 0;
 
       do {
 
            num1[quantidadeEntrada] = MyIO.readString();
            if(isFim(num1[quantidadeEntrada]) == false) {
                num2[quantidadeEntrada] = MyIO.readString();
            }
 
        } while (isFim(num1[quantidadeEntrada++]) == false);
 
        quantidadeEntrada--;
        

 
        for (int i = 0; i < quantidadeEntrada; i++) {
             
            num3 = Integer.parseInt(num1[i]);
            num4 = Integer.parseInt(num2[i]);

            resultado = formarString(num3, num4);
            MyIO.println(resultado);
        }

    }


    public static String formarString(int num1, int num2) {
        String nova = "";
        for (int i = num1; i <= num2; i++) {
            nova += Integer.toString(i);

        }

        for (int i = nova.length() - 1; i >= 0; i--) {
            nova += nova.charAt(i);
            

        }

        return nova;

    }

    
}