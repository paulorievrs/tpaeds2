
public class Lab02 {

    public static boolean isFim(String frase) {

        boolean isFim = true;
            if (frase.length() == 3) {

                    if (frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M') {
                        
                        isFim = false;
                    }
                }
            
            return isFim;
    }

    public static void print(String s) {

        MyIO.println(s);

    }

    public static String formaFrase(String s1) {

        String parte1 = "";
        String parte2 = "";
        String completa = "";
        int tam1 = 0;
        int tam2 = 0;
        int i = 0;
        String maior = "";
        while (s1.charAt(i) != ' ') {
            parte1 += s1.charAt(i);
            i++;
        }
        i++;
        while (i <  s1.length()) {
            parte2 += s1.charAt(i);
            i++;
        }

        if (parte1.length() > tam1) {
            tam1 = parte1.length();
            tam2 = parte2.length();
            maior = parte1;
        } else {
            tam1 = parte2.length();
            tam2 = parte1.length();
            maior = parte2;
        }

        for (int j = 0; j < tam2; j++) {

            completa = completa + parte1.charAt(j);
            completa += parte2.charAt(j);

        }

        for (int j = tam2; j < tam1; j++) {
            completa += maior.charAt(j);
        }


        return completa;
    }

    public static void main(String[] args) {
        String[] s1 = new String[1000];
        int quantidadeEntrada = 0;

        do {

            s1[quantidadeEntrada] = MyIO.readLine();
            
        } while (isFim(s1[quantidadeEntrada++]) == true);
        quantidadeEntrada--;
        


        for (int i = 0; i < quantidadeEntrada; i++) {
            print(formaFrase(s1[i]));        
            
        
        }
        
    }


}