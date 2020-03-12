import java.io.*;
import java.net.*;


public class Html {

    public static boolean isFim(String frase) {

        boolean isFim = true;

            if (frase.length() == 3) {

                    if (frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M') {
                        
                        isFim = false;
                    }
                }
            return isFim;
    }

    public static boolean ehVogal(char s) {
        if(s == 'a' || s == 'e' || s == 'i' || s == 'o' || s == 'u') {
            return true;
        } else {
            return false;
        }

    }
//metodo para verificar se é letra
    public static boolean ehLetra(char s) {
        boolean letra = true;
        if (s >= 97 && s <= 122) {
            if (s >= 65 && s <= 90) {
            letra = true;
            }
        } else {
            letra = false;
        }
        return letra;
    }

//adicionar os dados da página a resp nova
    public static String getUrl(String endereco){
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;
   
        try {
           url = new URL(endereco);
           is = url.openStream();
           br = new BufferedReader(new InputStreamReader(is));
   
           while ((line = br.readLine()) != null) {
              resp += line + "\n";
           }
        } catch (MalformedURLException mue) {
           mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
         } 
    
         try {
            is.close();
         } catch (IOException ioe) {
    
         }
    

        return resp;
    }
    //verificando se é uma URL a STRING
    public static boolean ehURL(String s) {

        boolean ehURL = false;

        if (s.charAt(0) == 'h' && s.charAt(1) == 't' && s.charAt(2) == 't') {
            ehURL = true;
        }
        return ehURL;
    } //fim ehURL();

    public static int ehBR(String s) {
        int cont = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '<' && s.charAt(i+1) == 'b' && s.charAt(i+2) == 'r' && s.charAt(i+3) == '>') {
                cont++;
            }
        }

        return cont;
    }

    public static int ehTable (String s) {
        int cont = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '<' && s.charAt(i+1) == 't' && s.charAt(i+2) == 'a' && s.charAt(i+3) == 'b' && s.charAt(i+6) == '>') {
                cont++;
            }
        }

        return cont;
    }
    
    public static String toStr(String s) {
        String str = "";

        for (int i = 0; i < s.length(); i++) {
            str += s.charAt(i);
        }

        return str;
    }


    public static int ehCons(String s) {
        int ehCons = 0;
        for (int i = 0; i < s.length(); i++) {
            if (ehLetra(s.charAt(i)) && !ehVogal(s.charAt(i))) {
                ehCons++;
            }   
        }
        return ehCons;
    }

  
    public static void main(String[] args) throws Exception{
        
        
        String[] entrada = new String[1000];
        int quantidadeEntrada = 0;
        String s = "";
        String nome = "";
        String print = "";
        String utf8 = "";

        //inicio contadores da vogal a
        int contA = 0;
        int contÁ = 0;
        int contÀ = 0;
        int contã = 0;
        int contâ = 0;
        //fim contadores da vogal a
        //inicio contadores da vogal e
        int contE = 0;
        int contÉ = 0;
        int contÈ = 0;
        int contÊ = 0;
        //fim contadores da vogal e
        //inicio contadores da vogal i
        int contI = 0;
        int contÍ = 0;
        int contÌ = 0;
        int contÎ = 0;
        //fim contadores da vogal i
        //inicio contadores da vogal o
        int contO = 0;
        int contÓ = 0;
        int contÒ = 0;
        int contÕ = 0;
        int contÔ = 0;
        //fim contadores da vogal o
        //inicio contadores da vogal u
        int contU = 0;
        int contÚ = 0;
        int contÙ = 0;
        int contÛ = 0;
        //fim contadores da vogal u
        //<table> e <br>
        int contTable = 0;
        int contBr = 0;
        //consoante;
        int cons = 0;
        //entrada de dados
        String entra = "";




        do {
            entra = MyIO.readLine();
            entrada[quantidadeEntrada] = entra;

        } while (isFim(entrada[quantidadeEntrada++]) == true);

        quantidadeEntrada--;

       for (int i = 0; i < quantidadeEntrada; i++) {
            if (!ehURL(entrada[i])) {
                nome = entrada[i];
            }


            if(ehURL(entrada[i+1]) == true) {
                s = getUrl(entrada[i+1]);
                contBr = ehBR(s);
                contTable = ehTable(s);
                cons = ehCons(s);
                for (int j = 0; j < s.length(); j++) {
                    //Incio das verificações das vogais por else if
                    if (s.charAt(j) == 'a') {
                        contA++;
                        
                        }  if (s.charAt(j) == 'á') {
                            contÁ++;
                            } if (s.charAt(j) == 'à') {
                                contÀ++;
                                }  if (s.charAt(j) == 'ã') {
                                    contã++;
                                    }  if (s.charAt(j) == 'â') {
                                        contâ++;
                                        }  if (s.charAt(j) == 'e') {
                                            contE++;
                                            }  if (s.charAt(j) == 'é') {
                                                contÉ++;
                                                }  if (s.charAt(j) == 'è') {
                                                    contÈ++;
                                                    }  if (s.charAt(j) == 'ê') {
                                                        contÊ++;
                                                        }  if (s.charAt(j) == 'i') {
                                                            contI++;
                                                            }  if (s.charAt(j) == 'í') {
                                                                contÍ++;
                                                                }  if (s.charAt(j) == 'ì') {
                                                                    contÌ++;
                                                                    }  if (s.charAt(j) == 'î') {
                                                                        contÎ++;
                                                                        }  if (s.charAt(j) == 111) {
                                                                            contO++;
                                                                            }  if (s.charAt(j) == 'ó') {
                                                                                contÓ++;
                                                                                }  if (s.charAt(j) == 'ò') {
                                                                                    contÒ++;
                                                                                    }  if (s.charAt(j) == 'õ') {
                                                                                        contÕ++;
                                                                                        }  if (s.charAt(j) == 'ô') {
                                                                                            contÔ++;
                                                                                            }  if (s.charAt(j) == 'u') {
                                                                                                contU++;
                                                                                                }  if (s.charAt(j) == 'ú') {
                                                                                                    contÚ++;
                                                                                                    }  if (s.charAt(j) == 'ù') {
                                                                                                        contÙ++;
                                                                                                        }  if (s.charAt(j) == 'û') {
                                                                                                            contÛ++;
                                                                                                            }
                                                                                                        //fim das verificações das vogais por elseif.
                        
                    }

                
            }

           
            if(ehURL(entrada[i+1]) == true) {

                if(contBr > 0) { //remover do contador de consoantes as consoantes presentes em BR
                    int aux = contBr *2;
                    cons -= aux;
                }
    
                if (contTable > 0) { //remover do contador de consoantes / volgal as consoantes / vogal presentes em Table
                    int auxCons = contTable * 3;
                    int auxVogal = contTable * 2;
                    contA -= auxVogal;
                    contE -= auxVogal;
                    cons -= auxCons;
                    contA++;
                    contE++;
                
                }
                print = "a("+contA+") e("+contE+") i("+contI+") o("+contO+") u("+contU+") á("+contÁ+") é("+contÉ+") í("+contÍ+") ó("+contÓ+") ú("+contÚ+") à("+contÀ+") è("+contÈ+") ì("+contÌ+") ò("+contÒ+") ù("+contÙ+") ã("+contã+") õ("+contÕ+") â("+contâ+") ê("+contÊ+") î("+contÎ+") ô("+contÔ+") û("+contÛ+") consoante("+cons+") <br>("+contBr+") <table>("+contTable+") "+nome;
                utf8 = toStr(print);
                String convertida = new String(utf8.getBytes(), "ISO-8859-1");
                MyIO.println(convertida);
                contA = 0;
                contE = 0;
                contI = 0;
                contO = 0;
                contU = 0;
                contÁ = 0;
                contÉ = 0;
                contÍ = 0;
                contÓ = 0;
                contÚ = 0;
                contÀ = 0;
                contÈ = 0;
                contÌ = 0;
                contÒ = 0;
                contÙ = 0;
                contã = 0;
                contÕ = 0;
                contâ = 0;
                contÊ = 0;
                contÎ = 0;
                contÔ = 0;
                contÛ = 0;
                cons = 0;
                contBr = 0;
                contTable = 0;
            }
        } 
        
    }
}

