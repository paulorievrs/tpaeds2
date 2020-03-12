public class PalinRec {


    public static boolean isFim(String frase) {

        boolean isFim = true;

            if (frase.length() == 3) {

                    if (frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M') {
                        
                        isFim = false;
                    }
                }
            
            return isFim;
    }

    public static void print(boolean ehPalin) {

        if (ehPalin == true) {
            MyIO.println("SIM");
        } else {
            MyIO.println("NAO");
        }
    }

    public static boolean ehPalin(String palin, int posicao) {

        boolean ehPalin = true;

        if (posicao >= palin.length()) {
            ehPalin = true;
        } else if (palin.charAt(posicao) == ((palin.charAt(palin.length() - posicao - 1)))) {
            ehPalin = ehPalin(palin, posicao + 1);

        } else {
            ehPalin = false;
        }

        return ehPalin;
    }

    public static boolean ehPalin(String s) {
        return ehPalin(s, 0);
    }

    public static void main(String[] args) {

        
        String[] palin = new String[1000];
        int quantidadeEntrada = 0;

        
        do {

            palin[quantidadeEntrada] = MyIO.readLine();

        } while (isFim(palin[quantidadeEntrada++]) == true);

        quantidadeEntrada--;

        for (int i = 0; i < quantidadeEntrada; i++) {
            
            print(ehPalin(palin[i]));
        
        }
        
    }

}