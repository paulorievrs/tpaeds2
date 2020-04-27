import java.io.*;
import java.util.*;

public class Telefone {

    public static String[] removerNulls(String[] s) {
        int cont = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s[i] != null) {
                cont++;
            }
        }

        String[] semNulls = new String[cont];
        for(int i = 0; i < cont; i++) {
            semNulls[i] = s[i];
        }

        return semNulls;
        

    }

    public static String[] lerEntrada() {

        Scanner s = new Scanner(System.in);

        int i = 0;
        String[] entrada = new String[1000];

        while(s.hasNext()) {

            entrada[i] = s.nextLine();
            i++;
        }

        return removerNulls(entrada);

    }

    public static String[] getNumeros(String[] s) {
        if(s[i].par)

    }

    public static void main(String args[]) {
        String[] entrada = lerEntrada();

    }
}