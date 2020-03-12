
public class Q02 {
    
    public static boolean isFim(String frase) {

        boolean isFim = true;

            if (frase.length() == 3) {

                    if (frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M') {
                        
                        isFim = false;
                    }
                }
            
            return isFim;
    }

    public static boolean isMaiuscula(char letra) {

        return (letra >= 'A' && letra <= 'Z');
    }


    public static int recContarMaiusculas(String frase, int posicao) {

        int resposta = 0;

        if (posicao >= frase.length()) {

            resposta = 0;

        } else if (isMaiuscula(frase.charAt(posicao)) == true) {
            
            resposta = 1 + recContarMaiusculas(frase, posicao + 1);
        
        } else {

            resposta = 0 + recContarMaiusculas(frase, posicao + 1);
        }

        return resposta;

    }

    public static int recContarMaiusculas(String frase) {
        return recContarMaiusculas(frase, 0);
    }

    public static void main (String[] args){
        
        String[] frase = new String[1000];
        int quantidadeEntrada = 0;

        
        do {

            frase[quantidadeEntrada] = MyIO.readLine();

        } while (isFim(frase[quantidadeEntrada++]) == true);

        quantidadeEntrada--;
        
        for (int i = 0; i < quantidadeEntrada; i++) {
            MyIO.println(recContarMaiusculas(frase[i]));
        }

    }
}


