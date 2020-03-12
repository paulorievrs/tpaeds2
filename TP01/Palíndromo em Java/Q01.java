public class Q01 {


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

    public static boolean ehPalin(String palin) {

        boolean ehPalin = true;
        
        for (int i = 0; i < palin.length(); i++) {

            if(palin.charAt(i) != (palin.charAt(palin.length() - 1 - i ))) {
                ehPalin = false;
                i = palin.length();
            } 
        }

        return ehPalin;
    }

    public static void main(String[] args) {

        
        String[] palin = new String[1000];
        int quantidadeEntrada = 0;

        
        do {

            palin[quantidadeEntrada] = MyIO.readLine();

        } while (isFim(palin[quantidadeEntrada++]) == true);

        for (int i = 0; i < quantidadeEntrada; i++) {
            
            print(ehPalin(palin[i]));
        
        }
        
    }

}