public class CesarRec {

/*metdo de ver se aparace o fim da String*/
    public static boolean isFim(String frase) {

        boolean isFim = true;

            if (frase.length() == 3) {

                    if (frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M') {
                        
                        isFim = false;
                    }
                }
            
            return isFim;

    } //fim isFim();

/*metodo para cifrar o codigo*/
    public static String cifra(String frase) {

        String alterado = "";

        for (int i = 0; i < frase.length(); i++) {
            
            alterado = alterado +(char)(frase.charAt(i) + 3);
            
        }


        return alterado;

    } //fim cifra()

    public static String recCifra(String frase, int posicao) {

        String alt = "";

        if (posicao >= frase.length()) {
            alt = "";
        } else {
            alt = alt + (char)(frase.charAt(posicao) + 3) + recCifra(frase, posicao + 1);
        }

        return alt;
    }

    public static String recCifra(String frase) {
        return recCifra(frase, 0);
    }

    public static void main(String[] args) {

        
        String[] frase = new String[1000];
        int quantidadeEntrada = 0;

        
        do {

            frase[quantidadeEntrada] = MyIO.readLine(); //receber entrada

        } while (isFim(frase[quantidadeEntrada++]) == true);

        quantidadeEntrada--;
        for (int i = 0; i < quantidadeEntrada; i++) {            
            MyIO.println(recCifra(frase[i])); //printar entrada
        }
        
    }

}