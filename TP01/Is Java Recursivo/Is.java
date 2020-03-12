public class Is {

    /*metdo de ver se aparace o fim da String*/
        public static boolean isFim(  String frase) {

        boolean isFim = true;

        if (frase.length() == 3) {

            if (frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M') {

                isFim = false;
            }
        }

        return isFim;

    } // fim isFim();


    //inicio ehVogal
    public static boolean ehVogal(  String frase) {
        return ehVogal(frase, 0);
    }

    public static boolean ehVogal(  String frase,   int posicao) {

        boolean ehVogal = true;

        if (posicao >= frase.length()) {
            ehVogal = true;
        } else if (frase.charAt(posicao) == 'a' || frase.charAt(posicao) == 'e' || frase.charAt(posicao) == 'i'
                || frase.charAt(posicao) == 'o' || frase.charAt(posicao) == 'u') {
            ehVogal = ehVogal(frase, posicao + 1);

        } else {
            ehVogal = false;
        }

        return ehVogal;
    }
    //fim ehVogal;

    // inicio ehConsoante
    public static boolean ehConsoante(String frase) {
        return ehConsoante(frase, 0);
    }

    public static boolean ehConsoante(String frase, int posicao) {

        boolean ehConsoante = true;

        if (posicao >= frase.length()) {
            ehConsoante = true;
        } else if (!ehVogal(frase) && frase.charAt(posicao) < 48 && frase.charAt(posicao) > 57) {
            ehConsoante = ehConsoante(frase, posicao + 1);
        } else {
            ehConsoante = false;
        }
        return ehConsoante;
    }
    //fim ehConsoante;

    //inico ehInteiro
    public static boolean ehInteiro(  String frase) {
        return ehInteiro(frase, 0);
    }

    public static boolean ehInteiro(String frase, int posicao) {
        boolean ehInteiro = true;

        if (posicao >= frase.length()) {
            ehInteiro = true;
        } else if (frase.charAt(posicao) >= 48 && frase.charAt(posicao) <= 57) {
            ehInteiro = ehInteiro(frase, posicao + 1);
        } else {
            ehInteiro = false;
        }

        return ehInteiro;

    }
    //fim eh inteiro


    //metodo de printar
    public static void print(Boolean verificar) {
        
        if(verificar == true) {
            MyIO.print("SIM");
        } else {
            MyIO.print("NAO");
        }
    }
    //fim meteodo de printar

    //metodo de contar digitos de uma string

    public static int contarDigitos (String s) {

        int cont = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ',' || s.charAt(i) == '.') {
                cont++;
            }
        }
        return cont;
    }
    public static int digito(String s) {

        int aux = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ',' || s.charAt(i) == '.') {
                aux = i;
            }
        }
        return aux;
    }
    //fim metodo de contar digitos de uma string

    //inicio ehReal
    public static boolean ehReal(String frase, int posicao, int aux) {
        boolean ehReal = true;


        if (posicao >= frase.length()) {
            ehReal = true;
        } else if (aux != frase.charAt(posicao)) {
            if (frase.charAt(posicao) >= 48 && frase.charAt(posicao) <= 57) {
                ehReal = true;
            } else {
                ehReal = false;
            } 
        } else {
            ehReal = false;
        }
        return ehReal;
    }

    public static boolean ehReal(String frase) {
        int aux = digito(frase);
        if (aux == 0 || aux == 1) {
            return ehReal(frase, 0, aux);
        } else {
            return false;
        }
    }
    //fim eh real
    public static void main(  String[] args) {

          String[] frase = new String[1000];
    int quantidadeEntrada = 0;

    
    do {

        frase[quantidadeEntrada] = MyIO.readLine(); //receber entrada

    } while (isFim(frase[quantidadeEntrada++]) == true);

    quantidadeEntrada--;
    for (int i = 0; i < quantidadeEntrada; i++) {            
        print(ehVogal(frase[i]));
        MyIO.print(" ");
        print(ehConsoante(frase[i]));
        MyIO.print(" ");
        print(ehInteiro(frase[i]));
        MyIO.print(" ");
        print(ehReal(frase[i]));
        MyIO.print("\n");
        
    }
    
}

}