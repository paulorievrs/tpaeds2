public class Ls {

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

//metodo para verificar se so tem vogal
public static boolean ehVogal(String frase) {

    boolean ehVogal = true;
    for (int i = 0; i < frase.length(); i++) {
        if (frase.charAt(i) != 'a' && frase.charAt(i) != 'e' && frase.charAt(i) != 'i' && frase.charAt(i) != 'o' && frase.charAt(i) != 'u') {
            ehVogal = false;
            i = frase.length();
        }
    }
    return ehVogal;
} //fim ehVogal()

//metodo para verificar se so tem consoante
public static boolean ehConsoante(String frase) {

    boolean ehConsoante = false;
        for (int i = 0; i < frase.length(); i++) {
            if (!ehVogal(frase) || !ehInteiro(frase) || !ehReal(frase)) {
                ehConsoante = true;
                
            } else {
                ehConsoante = false;
                i = frase.length();
            }
        }
        
    return ehConsoante;
} //fim ehConsoante()

//metodo para verificar se é um numero inteiro
public static boolean ehInteiro (String frase) {
    boolean ehInteiro = false;

    for (int i = 0; i < frase.length(); i++) {
        if (frase.charAt(i) >= 48 && frase.charAt(i) <= 57) {
            ehInteiro = true;
            
        } else {
            ehInteiro = false;
            i = frase.length();
        }
    }
    return ehInteiro;
} //fim ehInteiro()

//metodo para verificar se é um numero real
public static boolean ehReal(String frase) {
    boolean ehReal = false;
    int cont = 0;
    int aux = 0;

    for (int i = 0; i < frase.length(); i++) {

        if(frase.charAt(i) == ',' || frase.charAt(i) == '.') {
            cont++;
            if (cont == 1) {
                aux = i;
            }
        }
    }
    for (int i = 0; i < frase.length(); i++) {
        if (frase.charAt(i) != frase.charAt(aux)) {

            if(cont == 1 || cont == 0) {
                if (frase.charAt(i) >= 48 && frase.charAt(i) <= 57) {
                    ehReal = true;
                } else {
                    ehReal = false;

                    i = frase.length();
                    } //fim else
                } //fim if(cont == 1 || cont == 0)
            } //fim if (frase.charAt(i) != frase.charAt(aux))
        } //fim for
    return ehReal;
} //fim ehReal()


public static void print(Boolean verificar) {
    
    if(verificar == true) {
        MyIO.print("SIM");
    } else {
        MyIO.print("NAO");
    }
}

public static void main(String[] args) {

    
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
