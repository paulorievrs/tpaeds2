import java.util.*;
import java.io.*;

public class Lista{
    public static boolean isFim(String s){ //Verifica se string é igual a FIM
            return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
        }

    public static String readArq(String nomeArq) throws Exception{
        String str = new String();
        Arq.openRead(nomeArq);

        str = Arq.readAll();

        Arq.close();

        return str;
    }

    public static String lerArquivo(String arquivo) {
        String linha = new String();
        try {
            FileReader arq = new FileReader(arquivo);
            BufferedReader lerArq = new BufferedReader(arq);
            linha = lerArq.readLine(); 
            arq.close();

            } catch (IOException e) {
              System.err.printf("Erro na abertura do arquivo: %s.\n",
                e.getMessage());
            }
        return linha;
    }

    public static void main(String[] args) throws Exception{
            MyIO.setCharset("UTF-8");
            String[] entrada = new String[300];
            String[] comandos = new String[300];
            Personagem[] personagem = new Personagem[1000];
            Personagem[] personagemComando = new Personagem[1000];
            int numEntrada = 0; 
            int tamComandos = 0;

            do{
                entrada[numEntrada] = MyIO.readLine();
            } while(isFim(entrada[numEntrada++]) == false);

            Listas lista = new Listas(300);

            for(int i = 0; i < numEntrada-1; i++){
                personagem[i] = new Personagem();
                String str = lerArquivo(entrada[i]);
                personagem[i].iniciaPersonagem(str);
                lista.inserirFim(personagem[i]);
            }

            tamComandos = MyIO.readInt();

            for(int j = 0; j < tamComandos; j++){
                personagemComando[j] = new Personagem();
                comandos[j] = MyIO.readLine();
                
                if(comandos[j].charAt(0) == 'I'){
                    String psr = getPersonagem(comandos[j]);
                    String str1 = readArq(psr);
                    personagemComando[j].iniciaPersonagem(str1);

                    if(comandos[j].charAt(1) == '*'){
                        int pos1 = getPosicao(comandos[j]);
                        lista.inserir(personagemComando[j], pos1);
                    } 
                    else if(comandos[j].charAt(1) == 'I'){
                        lista.inserirInicio(personagemComando[j]);
                    } 
                    else if(comandos[j].charAt(1) == 'F'){
                        lista.inserirFim(personagemComando[j]);
                    }
                } 
                else if(comandos[j].charAt(0) == 'R' && comandos[j].charAt(1) == '*'){
                    int pos = getPosicao(comandos[j]);
                    lista.remover(pos); 
                } 
                else if(comandos[j].charAt(0) == 'R' && comandos[j].charAt(1) == 'F'){
                    lista.removerFim();
                } 
                else if(comandos[j].charAt(0) == 'R' && comandos[j].charAt(1) == 'I'){
                    lista.removerInicio();
                }
            }
        }

        public static String getPersonagem(String str){
            int tam = str.length();
            String psr = "";

            for(int i = 2; i < tam; i++){
                if(str.charAt(i) == 'C'){
                    do{
                        psr += str.charAt(i);
                        i++;
                    } while(i < tam);
                }
            }

            return psr;
        }

        public static int getPosicao(String str){
            String pos = "";

            for(int i = 3; i < 5; i++){
                pos += str.charAt(i);
            }

            int resp = Integer.parseInt(pos);
            
            return resp; 
        }
    }

    class Personagem{ 
        private int altura;
        private double peso;
        private String nome, corDoCabelo, corDaPele, corDosOlhos, anoNascimento, genero, homeworld; 

        public Personagem(){
            this.nome = this.corDaPele = this.corDoCabelo = this.corDosOlhos = this.anoNascimento = this.genero = this.homeworld = "";
            this.peso = 0.0;
            this.altura = 0;
        }

        public Personagem(int altura, double peso, String nome, String corDaPele, String corDoCabelo, String corDosOlhos, String anoNascimento, String genero, String homeworld){
            this.altura = altura; 
            this.peso = peso; 
            this.nome = nome; 
            this.corDaPele = corDaPele;
            this.corDoCabelo = corDoCabelo;
            this.corDosOlhos = corDosOlhos;
            this.anoNascimento = anoNascimento; 
            this.genero = genero; 
            this.homeworld = homeworld;
        }

        public int getAltura(){
            return this.altura;
        }

        public void setAltura(int altura){
            this.altura = altura; 
        }

        public double getPeso(){
            return this.peso; 
        }

        public void setPeso(double peso){
            this.peso = peso;
        }

        public String getNome(){
            return this.nome;
        }

        public void setNome(String nome){
            this.nome = nome;
        }

        public String getCorDaPele(){
            return this.corDaPele;
        }

        public void setCorDaPele(String corDaPele){
            this.corDaPele = corDaPele;
        }

        public String getCorDosOlhos(){
            return this.corDosOlhos;
        }

        public void setCorDosOlhos(String corDosOlhos){
            this.corDosOlhos = corDosOlhos;
        }

        public String getCorDoCabelo(){
            return this.corDoCabelo;
        }

        public void setCorDoCabelo(String corDoCabelo){
            this.corDoCabelo = corDoCabelo;
        }

        public String getGenero(){
            return this.genero;
        }

        public void setGenero(String genero){
            this.genero = genero;
        }

        public String getAnoNascimento(){
            return this.anoNascimento;
        }

        public void setAnoNascimento(String anoNascimento){
            this.anoNascimento = anoNascimento;
        }

        public String getHomeworld(){
            return this.homeworld;
        }

        public void setHomeworld(String homeworld){
            this.homeworld = homeworld;
        }

        public void lerNome(String str){
            String nome = "";
            boolean fim = false; 
            int i = 0;
            
            do{
                if(str.charAt(i) == 'n' && str.charAt(i+1) == 'a' && str.charAt(i+2) == 'm' && str.charAt(i+3) == 'e'){
                    i = i+8;
                    while(str.charAt(i) != 39){
                        nome += str.charAt(i);
                        i++;
                    }
                    fim = true; 
                } 

                i++;

            } while(i < str.length() && fim == false);

            this.nome = nome; 
        }

        public void lerPeso(String str){
            int i = 0;
            String peso = "";
            boolean fim = false;

            do{
                if(str.charAt(i) == 'm' && str.charAt(i+1) == 'a' && str.charAt(i+2) == 's' && str.charAt(i+3) == 's'){
                    i = i+8;
                    while(str.charAt(i) != 39){
                        peso += str.charAt(i);
                        i++;
                    } fim = true;
                }

                i++;

            } while(i < str.length() && fim == false);

            if(peso.charAt(0) >= '1' && peso.charAt(0) <= '9'){
                this.peso = Double.parseDouble(peso.replace(",", "."));
            } else {
                this.peso = 0;
            }
        }

        public void lerAltura(String str){
            int i = 0;
            boolean fim = false; 
            String altura = "";

            do{
                if(str.charAt(i) == 'h' && str.charAt(i+1) == 'e' && str.charAt(i+2) == 'i' && str.charAt(i+3) == 'g' && str.charAt(i+4) == 'h' && str.charAt(i+5) == 't'){
                    i=i+10;
                    while(str.charAt(i) != 39){
                        altura += str.charAt(i);
                        i++;
                    } fim = true;
                }

                i++;

            } while(i < str.length() && fim == false);

            int verifica = (int)altura.charAt(0);

            if(verifica >= 48 && verifica <= 57){
                this.altura = Integer.parseInt(altura);
            } else {
                this.altura = 0;
            }
        }

        public void lerHairColor(String str){
            int i = 0; 
            boolean fim = false; 
            String hairColor = "";

            do{
                if(str.charAt(i) == 'h' && str.charAt(i+1) == 'a' && str.charAt(i+2) == 'i' && str.charAt(i+3) == 'r' && str.charAt(i+4) == '_' && str.charAt(i+5) == 'c' && str.charAt(i+6) == 'o' && str.charAt(i+7) == 'l' && str.charAt(i+8) == 'o' && str.charAt(i+9) == 'r'){
                    i=i+14;
                    while(str.charAt(i) != 39){
                        hairColor += str.charAt(i);
                        i++;
                    } fim = true; 
                }

                i++;

            } while(i < str.length() && fim == false);

            this.corDoCabelo = hairColor;
        }

        public void lerSkinColor(String str){
            int i = 0; 
            boolean fim = false; 
            String skinColor = "";

            do{
                if(str.charAt(i) == 's' && str.charAt(i+1) == 'k' && str.charAt(i+2) == 'i' && str.charAt(i+3) == 'n' && str.charAt(i+4) == '_' &&  str.charAt(i+5) == 'c' && str.charAt(i+6) == 'o' && str.charAt(i+7) == 'l' && str.charAt(i+8) == 'o' && str.charAt(i+9) == 'r'){
                    i=i+14;
                    while(str.charAt(i) != 39){
                        skinColor += str.charAt(i);
                        i++;
                    } fim = true;
                }

                i++;

            } while(i < str.length() && fim == false);

            this.corDaPele = skinColor; 
        }

        public void lerEyeColor(String str){
            int i = 0; 
            boolean fim = false; 
            String eyeColor = "";

            do{
                if(str.charAt(i) == 'e' && str.charAt(i+1) == 'y' && str.charAt(i+2) == 'e' && str.charAt(i+3) == '_' && str.charAt(i+4) == 'c' && str.charAt(i+5) == 'o' && str.charAt(i+6) == 'l' && str.charAt(i+7) == 'o' && str.charAt(i+8) == 'r'){
                    i=i+13;
                    while(str.charAt(i) != 39){
                        eyeColor += str.charAt(i);
                        i++;
                    } fim = true; 
                }

                i++;
            } while(i < str.length() && fim == false);

            this.corDosOlhos = eyeColor;
        }

        public void lerBirth(String str){
            int i = 0; 
            boolean fim = false; 
            String birth = "";

            do{
                if(str.charAt(i) == 'b' && str.charAt(i+1) == 'i' && str.charAt(i+2) == 'r' && str.charAt(i+3) == 't' && str.charAt(i+4) == 'h' && str.charAt(i+5) == '_' && str.charAt(i+6) == 'y' && str.charAt(i+7) == 'e' && str.charAt(i+8) == 'a' && str.charAt(i+9) == 'r'){
                    i=i+14;
                    while(str.charAt(i) != 39){
                        birth += str.charAt(i);
                        i++;
                    } fim = true; 
                }

                i++;

            } while(i < str.length() && fim == false);

            this.anoNascimento = birth;
        }

        public void lerGender(String str){
            int i = 0; 
            boolean fim = false; 
            String gender = "";

            do{
                if(str.charAt(i) == 'g' && str.charAt(i+1) == 'e' && str.charAt(i+2) == 'n' && str.charAt(i+3) == 'd' && str.charAt(i+4) == 'e' && str.charAt(i+5) == 'r'){
                    i=i+10;
                    while(str.charAt(i) != 39){
                        gender += str.charAt(i);
                        i++;
                    } fim = true; 
                }

                i++;

            } while(i < str.length() && fim == false);

            this.genero = gender; 
        }

        public void lerHomeworld(String str){
            int i = 0; 
            boolean fim = false; 
            String homeworld = "";

            do{
                if(str.charAt(i) == 'h' && str.charAt(i+1) == 'o' && str.charAt(i+2) == 'm' && str.charAt(i+3) == 'e' && str.charAt(i+4) == 'w' && str.charAt(i+5) == 'o' && str.charAt(i+6) == 'r' && str.charAt(i+7) == 'l' && str.charAt(i+8) == 'd'){
                    i=i+13;
                    while(str.charAt(i) != 39){
                        homeworld += str.charAt(i);
                        i++;
                    } fim = true; 
                }

                i++;

            } while(i < str.length() && fim == false); 

            this.homeworld = homeworld; 
        }

        public void printaResp(){
            if( getPeso() %1 == 0 ) {
                MyIO.println(" ## " + this.nome + " ## " + this.altura + " ## " + ((int)this.peso) + " ## " + this.corDoCabelo + " ## " + this.corDaPele + " ## " + this.corDosOlhos + " ## " + this.anoNascimento + " ## " + this.genero + " ## " + this.homeworld + " ## ");
            } else {
                MyIO.println(" ## " + this.nome + " ## " + this.altura + " ## " + this.peso + " ## " + this.corDoCabelo + " ## " + this.corDaPele + " ## " + this.corDosOlhos + " ## " + this.anoNascimento + " ## " + this.genero + " ## " + this.homeworld + " ## ");
            }
        }

        public void iniciaPersonagem(String str){
            lerNome(str);
            lerAltura(str);
            lerPeso(str);
            lerHairColor(str);
            lerSkinColor(str);
            lerEyeColor(str);
            lerGender(str);
            lerBirth(str);
            lerHomeworld(str);
        }
    }

    class Listas{

        private Personagem[] array;
        private int n;

        Listas(int tam){
            this.array = new Personagem[tam];
            this.n = 0;
        }

        Listas(){
            this.array = new Personagem[300];
            this.n = 0; 
        }

        public void inserirFim(Personagem psr) throws Exception {
            if(n >= array.length){
                throw new Exception("Erro ao inserir!");
            }

            array[this.n] = psr;
            n++;
        }

        public void inserirInicio(Personagem psr) throws Exception{
            if(n >= array.length){
                throw new Exception("Erro ao inserir!");
            }
           
            for(int i = this.n; i > 0; i--){
                this.array[i] = this.array[i-1];
            }

            this.array[0] = psr;
            this.n++;

            //MyIO.println("inseriu inicio");
        }

        public void inserir(Personagem psr, int posicao) throws Exception {
            if(n >= array.length || posicao < 0 || posicao > this.n){
                throw new Exception("Erro ao inserir!");
            }

            for(int i = this.n; i > posicao; i--){
                this.array[i] = this.array[i-1];
            }

            this.array[posicao] = psr;
            this.n++;

            //MyIO.println("inseriu");
        }

        public Personagem removerInicio() throws Exception {
            if(n == 0){
                throw new Exception("Erro ao remover!");
            }

            Personagem resp = this.array[0];
            this.n--;

            for(int i = 0; i > this.n; i++){
                this.array[i] = this.array[i+1];
            }

            //MyIO.println("removeu inicio");

            return resp;
        }

        public Personagem removerFim() throws Exception {
            if(n == 0){
                throw new Exception("Erro ao remover!");
            }

            Personagem resp = this.array[n];
            this.n--;

            //MyIO.println("remover fim");

            return resp;
        }

        public Personagem remover(int posicao) throws Exception {
            if(posicao == 0 || posicao < 0 || posicao >= n){
                throw new Exception("Erro ao remover!");
            }

            Personagem resp = this.array[posicao];
            this.n--;

            for(int i = posicao; i < this.n; i++){
                this.array[i] = this.array[i+1];
            }

            //MyIO.println(resp.getNome());

            return resp;
        }

        public void imprimeLista(){
            for(int i = 0; i < this.n; i++){
                MyIO.print("[" + i + "]");
                this.array[i].printaResp();
            }
        }
    }