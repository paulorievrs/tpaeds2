import java.util.Random;

public class Alt {


    public static boolean isFim(String frase) {

        boolean isFim = true;

            if (frase.length() == 3) {

                    if (frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M') {
                        
                        isFim = false;
                    }
                }
            return isFim;
    }

    public static char gerarAlt(Random gerador) { //metodo para gerar e retornar char aleatorio

        return (char)('a' + (Math.abs(gerador.nextInt()) % 26));
    }

    public static String alterar(char c1, char c2, String s) { //metodo para alterar a String
        String s3 = "";

        for (int i = 0; i < s.length(); i++) {


            if (s.charAt(i) == c1) {

                s3 += c2;
            } else {
                s3 += s.charAt(i);
            }

        }

        return s3;

    }

    public static void main(String[] args) {

        String[] alterar = new String[1000];
        int quantidadeEntrada = 0;

        Random gerador = new Random();
        gerador.setSeed(4);

        char c1;
        char c2;

        do {

            alterar[quantidadeEntrada] = MyIO.readLine();

        } while (isFim(alterar[quantidadeEntrada++]) == true);

        quantidadeEntrada--;

        for (int i = 0; i < quantidadeEntrada; i++) {
            c1 = gerarAlt(gerador);
            c2 = gerarAlt(gerador);

            MyIO.println(alterar(c1, c2, alterar[i]));
        
        }
        
    } 

}