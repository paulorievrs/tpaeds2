import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Date;

/**
 * PONTIFÍCIA UNIVERSIDADE CATÓLICA DE MINAS GERAIS 
 * ALGORITMOS E ESTRUTURAS DE DADOS II
 * TP03Q07 - QuickSort com Lista Dinâmica Duplamente Encadeada em Java
 * @author Eduardo Henrique Souza Paraíso
 * @version 01/2020
*/

public class TP03Q07 {
    static Lista_DPEncadeada lista = new Lista_DPEncadeada();
    static int nComparacoes =  0;
    static int nMovimentacoes = 0;
    public static void main(String[] args) throws Exception{
        MyIO.setCharset("ISO-8859-1");
    
        String[] entradas = new String[1000];
        int numEntradas = 0;

        do{
            entradas[numEntradas] = MyIO.readLine();
        }while(procuraFIM(entradas[numEntradas++]) == false);
        numEntradas--;
       
        Card personagem;

        for(int i = 0; i < numEntradas; i++){
            personagem = new Card();                
            personagem.setaDados(personagem.leArquivo(entradas[i]));

            lista.insereFim(personagem);        
        }

        long inicio = cronometro();
        lista.quickSort_DP();   
        long termino = cronometro();
        log("00683400", inicio, termino);
        lista.imprimeLista();
    }

    /**
     * Caso encontre uma linha com a palavra FIM, retornará TRUE, FALSE caso contrário
     * @param entrada é a string a ser avaliada
     * @return TRUE para FIM e FALSO para qualquer outra string
    */
    public static boolean procuraFIM(String entrada){
        return (entrada.length() >= 3 && entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M');      
    }

    /**
     * Pega o horário do sistema, deve ser aplicado em dois momentos, inicio e fim.
     * @return
     */
    public static long cronometro(){
        return new Date().getTime();
    }

    /**
     * Gera arquivo .txt com informações da ordenação, tais como tempo gasto, numero de comparações e numero de movimentações
     * @param matricula
     * @param cronometroInicial
     * @param cronometroFinal
     * @param nComparacoes
     */
    public static void log(String matricula,long cronometroInicial, long cronometroFinal){
        Arq.openWrite("matricula_quickSort2.txt");
        Arq.println("Matricula: " + matricula + "\t"+ "Tempo(ns): " + (cronometroFinal - cronometroInicial) +"\t" + "Comparações: " + TP03Q07.nComparacoes + "\t" + "Movimentações: " + TP03Q07.nMovimentacoes);
        Arq.close();
    }
}

//Classe com atributos básicos das personagens
class Personagem{
    private String _nome;
    private int _altura;
    private double _peso;
    private String _corCabelo;
    private String _corPele;
    private String _corOlho;
    private String _anoNascimento;
    private String _genero;
    private String _terraNatal;

    /** Construtor vazio da classe */
    public Personagem(){
        this.set_nome("");
        this.set_altura(0);
        this.set_peso(0.0);
        this.set_corCabelo("");
        this.set_corPele("");
        this.set_corOlho("");
        this.set_anoNascimento("");
        this.set_genero("");
        this.set_terraNatal("");        
    }

    /**
     * Construtor personalizado da classe
     * @param nome [String] com o nome da personagem
     * @param altura [Int] com a altura da personagem
     * @param peso [double] com o peso da personagem
     * @param corCabelo [String] com a cor do cabelo da personagem
     * @param corPele [String] com a cor da pele da personagem
     * @param corOlho [String] com a cor dos olhos da personagem
     * @param anoNascimento [String] com a data de nascimento da personagem
     * @param genero [String] gênero
     * @param terraNatal [String] com a terra natal da personagem
     */
    public Personagem(String nome, int altura, double peso, String corCabelo, String corPele, String corOlho, String anoNascimento, String genero, String terraNatal){
        this.set_nome(nome);
        this.set_altura(altura);
        this.set_peso(peso);
        this.set_corCabelo(corCabelo);
        this.set_corPele(corPele);
        this.set_corOlho(corOlho);
        this.set_anoNascimento(anoNascimento);
        this.set_genero(genero);
        this.set_terraNatal(terraNatal);
    }

    /**
     * Seta o atributo nome da personagem
     * @param nome [String] nome da personagem
    */
    public void set_nome(String nome){
        this._nome = nome;
    }
    
    /**
     * Retorna o atributo nome da personagem
     * @return [String] nome da personagemm
    */
    public String get_nome(){
        return this._nome;
    }

    /**
     * Seta o atributo altura da personagem
     * @param altura [int] altura da personagem
     */
    public void set_altura(int altura){
        this._altura = altura;
    }   
    
    /**
     * Retorna o atributo altura da personagem
     * @return [int] altura da personagem
     */
    public int get_altura(){
        return this._altura;
    }

    /**
     * Seta o atributo peso da personagem
     * @param peso [double] peso da personagem
     */
    public void set_peso(double peso){
        this._peso = peso;
    }
    
    /**
     * Retorna a peso da personagem
     * @return [double] peso da personagem
     */
    public double get_peso(){
        return this._peso;
    }

    /**
     * Seta o atributo cor do cabelo da personagem
     * @param corCabelo [String] cor do cabelo da personagem
     */
    public void set_corCabelo(String corCabelo){
        this._corCabelo = corCabelo;
    }
    
    /**
     * Retorna a cor do cabelo da personagem
     * @return [String] cor do cabelo da personagem
     */
    public String get_corCabelo(){
        return this._corCabelo;
    }
 
    /**
     * Seta o atributo cor da pele da personagem
     * @param corPele [String] cor da pele da personagem
     */
    public void set_corPele(String corPele){
        this._corPele = corPele;
    }
    
    /**
     * Retorna a cor da pele da personagem
     * @return [String] cor da pele da personagem
     */
    public String get_corPele(){
        return this._corPele;
    }

    /**
     * Seta o atributo cor dos olhos da personagem
     * @param corOlho [String] cor dos olhos da personagem
     */
    public void set_corOlho(String corOlho){
        this._corOlho = corOlho;
    }
   
    /**
     * Retorna a cor dos olhos da personagem
     * @return [String] cor dos olhos da personagem
     */
    public String get_corOlho(){
        return this._corOlho;
    }

    /**
     * Seta o atributo ano de nascimento da personagem
     * @param anoNascimento [String] ano de nascimento da personagem
     */
    public void set_anoNascimento(String anoNascimento){
        this._anoNascimento = anoNascimento;
    }
   
    /**
     * Retorna o ano de nascimento da personagem
     * @return [int] ano de nascimento
     */ 
    public String get_anoNascimento(){
        return this._anoNascimento;
    }

    /**
     * Seta o atributo gênero da personagem
     * @param genero [String] gênero da personagem
     */
    public void set_genero(String genero){
        this._genero = genero;
    }
   
    /**
     * Retorna o genero da personagem
     * @return gênero da personagem
     */
    public String get_genero(){
        return this._genero;
    }

    /**
     * Seta o atributo terra natal da personagem
     * @param terraNatal [String] local de origem da personagem
     */
    public void set_terraNatal(String terraNatal){
        this._terraNatal = terraNatal;
    }
 
    /**
     * Retorna a terra natal da personagem
     * @return [String] terra natal
     */
    public String get_terraNatal(){
        return this._terraNatal;
    }  

    /**Exibe na tela as informações de cada personagem */
    public void exibeInfo(){
        NumberFormat NF = NumberFormat.getNumberInstance(Locale.ENGLISH);
        NF.setGroupingUsed(false);
        NF.setMinimumFractionDigits(0);

        MyIO.print(" ## ");
        MyIO.print(get_nome() + " ## ");
        MyIO.print(get_altura() + " ## ");
        System.out.print(NF.format(get_peso()) + " ## ");
        MyIO.print(get_corCabelo() + " ## ");
        MyIO.print(get_corPele() + " ## ");
        MyIO.print(get_corOlho() + " ## ");
        MyIO.print(get_anoNascimento() + " ## ");
        MyIO.print(get_genero() + " ## ");        
        MyIO.println(get_terraNatal() + " ## ");
    }
}

//Classe com métodos para construção dos cards de personagens
class Card extends Personagem{
    /**Construtor vazio herdado da classe Personagem */
    public Card(){
        super();
    }

    /**
     * Construtor personalizado herdado da classe Personagem
     * @param nome [String] com o nome da personagem
     * @param altura [Int] com a altura da personagem
     * @param peso [double] com o peso da personagem
     * @param corCabelo [String] com a cor do cabelo da personagem
     * @param corPele [String] com a cor da pele da personagem
     * @param corOlho [String] com a cor dos olhos da personagem
     * @param anoNascimento [String] com a data de nascimento da personagem
     * @param terraNatal [String] com a terra natal da personagem
     */
    public Card(String nome, int altura, double peso, String corCabelo, String corPele, String corOlho, String anoNascimento, String genero, String terraNatal){
        super(nome, altura, peso, corCabelo, corPele, corOlho, anoNascimento, genero, terraNatal);
    }

    /**Clona todos os atributos de uma personagem para outro objeto da mesma entidade */
    public Card clone(){
        return new Card(this.get_nome(), this.get_altura(), this.get_peso(), this.get_corCabelo(), this.get_corPele(), this.get_corOlho(), this.get_anoNascimento(), this.get_genero(), this.get_terraNatal());
    }

    /**
     * Retorna todo o conteúdo do arquivo
     * @param nomeArquivo [String] enreço do arquivo + nome + extensão
     * @return [String] todo conteúdo presente no arquivo
     * @throws Exception para leitura do arquivo, porém não houve tratamento 
     */
    public String leArquivo(String nomeArquivo) throws Exception{
        BufferedReader arquivo = new BufferedReader(new InputStreamReader(new FileInputStream(ISO88591toUTF8(nomeArquivo)), "ISO-8859-1"));

        String conteudo = arquivo.readLine();
        while(arquivo.readLine() != null){
            conteudo += arquivo.readLine();
        }

        arquivo.close();
        return conteudo;              
    }

    public static String ISO88591toUTF8(String strISO) throws Exception {
		byte[] isoBytes = strISO.getBytes("ISO-8859-1");
		return new String(isoBytes, "UTF-8");
	}
    /**
     * Busca dados específicos da personagem
     * @param conteudo [String] com a informação do arquivo
     * @param informacao [String] com o dado específico:
     *                   name, height, mass, hair_color, skin_color, eye_color, birth_year, gender, homeWorld 
     * @param offset [int] valores de deslocamento abaixo:
     *               name = 8, height = 10, mass = 8, hair_color = 14, skin_color = 14, eye_color = 13, birth_year = 14, gender = 10, homeWorld = 13
     * @return [String] informação buscada
     */
    public String buscaDados(String conteudo, String informacao, int offset){
        String resultado = "";
        offset += conteudo.indexOf(informacao); 
        
        resultado = conteudo.substring((offset), conteudo.indexOf('\'', offset));        
    
        if(informacao.equals("mass") && resultado.contains(",")){
            resultado = resultado.replaceAll(",", "");
        }     

        return resultado;
    }

    /**
     * Unifica todos os sets para construção do card da personagem
     * @param conteudo [String] todo conteúdo do arquivo
     */
    public void setaDados(String conteudo){
        this.set_nome(this.buscaDados(conteudo, "name", 8));       
        this.set_corCabelo(this.buscaDados(conteudo, "hair_color", 14));
        this.set_corPele(this.buscaDados(conteudo, "skin_color", 14));
        this.set_corOlho(this.buscaDados(conteudo, "eye_color", 13));
        this.set_anoNascimento(buscaDados(conteudo, "birth_year", 14));
        this.set_genero(this.buscaDados(conteudo, "gender", 10));
        this.set_terraNatal(buscaDados(conteudo, "homeworld", 13));  

        try{
            this.set_altura(Integer.parseInt(this.buscaDados(conteudo, "height", 10)));
        }catch(NumberFormatException strNumber){
            this.set_altura(0);
        }

        try{
            this.set_peso(Double.parseDouble(this.buscaDados(conteudo, "mass", 8)));
        }catch(NumberFormatException strNumber){
            this.set_peso(0.0);
        }      
    } 
}

//Celula Duplamente Encadeada
class Celula_Dupla {
    public Card personagem;
    public Celula_Dupla anterior;
    public Celula_Dupla proximo;

    public Celula_Dupla(){
        this(new Card());
    }

    public Celula_Dupla(Card personagem){
        this.personagem = personagem;
        this.anterior = null;
        this.proximo = null;
    }
}

//Classe com implementação da lista sequêncial
class Lista_DPEncadeada {
    private Celula_Dupla primeiro;
    private Celula_Dupla ultimo;

    public Lista_DPEncadeada(){
        primeiro = new Celula_Dupla();
        ultimo = primeiro;
    }

    public Lista_DPEncadeada(Card personagem){
        Celula_Dupla tmp = new Celula_Dupla(personagem);

        tmp.anterior = primeiro;
        tmp.proximo = primeiro.proximo;
        primeiro.proximo = tmp;

        if(primeiro == ultimo){
            ultimo = tmp;
        }else{
            tmp.proximo.anterior = tmp;
        }
        
        tmp = null;
    }

    public void insereFim(Card personagem){
        ultimo.proximo = new Celula_Dupla(personagem);
        ultimo.proximo.anterior = ultimo;
        ultimo = ultimo.proximo;
    }

    public void swap(Celula_Dupla i, Celula_Dupla j){
        Card tmp = i.personagem.clone();
        i.personagem = j.personagem.clone();
        j.personagem = tmp.clone();
    }

    public int mostraPosicao(Celula_Dupla celula) throws Exception{
        int pos = 0;
        for (Celula_Dupla i = primeiro.proximo; i != null && i != celula; i = i.proximo, pos++);

        return pos;
    }

	public Celula_Dupla pivo(int inicio, int fim){
		Celula_Dupla pivo = primeiro.proximo;
		int pos = ((inicio + fim)/2);
		for (int i = 0; pivo != null && i < pos; pivo = pivo.proximo, i++);

		return pivo;
	}	

    public void quickSort_DP() throws Exception{
        quickSort_DP(primeiro.proximo, ultimo);
    }

    public void quickSort_DP(Celula_Dupla inicio, Celula_Dupla fim) throws Exception{
        Celula_Dupla i = inicio;
        Celula_Dupla j = fim;  
        
        int pos_inicio = mostraPosicao(inicio);
        int pos_fim = mostraPosicao(fim);

        Celula_Dupla pivo = pivo(pos_inicio, pos_fim);
        
        int pos_i = pos_inicio;
        int pos_j = pos_fim;

        
		while (pivo.anterior != j && pivo.proximo != i) {
			while ((i != ultimo && i.personagem.get_corCabelo().compareTo(pivo.personagem.get_corCabelo()) < 0) || (i != ultimo && i.personagem.get_corCabelo().compareTo(pivo.personagem.get_corCabelo()) == 0 && i.personagem.get_nome().compareTo(pivo.personagem.get_nome()) < 0)){
				if(i.proximo != null){
                    i = i.proximo;
                    pos_i++;
                }
				TP03Q07.nComparacoes += 1;
			}
			while ((j.anterior != primeiro && j.personagem.get_corCabelo().compareTo(pivo.personagem.get_corCabelo()) > 0) || (j.anterior != primeiro && j.personagem.get_corCabelo().compareTo(pivo.personagem.get_corCabelo()) == 0 && j.personagem.get_nome().compareTo(pivo.personagem.get_nome()) > 0)){
				if(j.anterior != null){
                    j = j.anterior;
                    pos_j--;
                }
				TP03Q07.nComparacoes += 1;
			}

			if (pos_i <= pos_j){
				swap(i, j);
				i = i.proximo; pos_i++;
                j = j.anterior; pos_j--;
                TP03Q07.nMovimentacoes += 3;
			}
        }
        
		if (pos_inicio < pos_j){
            quickSort_DP(inicio, j);
        }
            
		if (pos_i < pos_fim){
            quickSort_DP(i, fim);
        }
            
	}

    public void imprimeLista(){
        Celula_Dupla i = primeiro;
        for(i = i.proximo; i != null; i = i.proximo){
            i.personagem.exibeInfo();            
        }
    }


}