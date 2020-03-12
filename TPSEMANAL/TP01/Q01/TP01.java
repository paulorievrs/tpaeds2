
public class TP01 {
    
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

    public static int contarMaiusculas(String frase) {

        int quantidadeMaiusculas = 0;

            for (int i = 0; i < frase.length(); i++) {
                if (isMaiuscula(frase.charAt(i)) == true) {
                    quantidadeMaiusculas++;
                }
            }
        return quantidadeMaiusculas;
    }


    public static void main (String[] args){
        
        String[] frase = new String[1000];
        int quantidadeEntrada = 0;

        
        do {

            frase[quantidadeEntrada] = MyIO.readLine();

        } while (isFim(frase[quantidadeEntrada++]) == true);

        quantidadeEntrada--;
        
        for (int i = 0; i < quantidadeEntrada; i++) {
            MyIO.println(contarMaiusculas(frase[i]));
        }

    }
}