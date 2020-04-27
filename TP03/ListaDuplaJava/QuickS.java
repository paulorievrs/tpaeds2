import java.text.*;
import java.io.*;
import java.util.*;
/*
* Classe Personagem TP02 Q01 - Java
* @author Paulo Rievrs Oliveira
* @version 1 02/2020
*/


class Personagem {
	//Atributos privados	
	private String nome = "";
	private int altura = 0;
	private double peso = 0;
	private String corDoCabelo = "";
	private String corDaPele = "";
	private String corDosOlhos = "";
	private String anoNascimento = "";
	private String genero = "";
	private String homeworld= "";

	//Construtor Vazio
	public Personagem() {
		this.nome = "";
		this.altura = 0;
		this.peso = 0;
		this.corDoCabelo = "";
		this.corDaPele = "";
		this.corDosOlhos = "";
		this.anoNascimento = "";
		this.genero = "";
		this.homeworld= "";
	}	

	//Construtor que recebe parametros
	public Personagem(String nome, int altura, double peso, String corDoCabelo, String corDaPele, String corDosOlhos, String anoNascimento, String genero, String homeworld) {
		
		setNome(nome);
		setAltura(altura);
		setPeso(peso);
		setCorDoCabelo(corDoCabelo);
		setCorDaPele(corDaPele);
		setCorDosOlhos(corDosOlhos);
		setAnoNascimento(anoNascimento);
		setGenero(genero);
		setHomeworld(homeworld);

	}

	//Inicio get's e set's dos atributos
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return this.nome;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public int getAltura(){
		return this.altura;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getPeso(){
		return this.peso;
	}
	public void setCorDoCabelo(String corDoCabelo) {
		this.corDoCabelo = corDoCabelo;
	}
	public String getCorDoCabelo(){
		return this.corDoCabelo;
	}
	public void setCorDaPele(String corDaPele) {
		this.corDaPele = corDaPele;
	}
	public String getCorDaPele(){
		return this.corDaPele;
	}
	public void setCorDosOlhos(String corDosOlhos) {
		this.corDosOlhos = corDosOlhos;
	}
	public String getCorDosOlhos(){
		return this.corDosOlhos;
	}
	public void setAnoNascimento(String anoNascimento) {
		this.anoNascimento = anoNascimento;
	}
	public String getAnoNascimento(){
		return this.anoNascimento;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getGenero(){
		return this.genero;
	}
	public void setHomeworld(String homeworld) {
		this.homeworld = homeworld;
	}
	public String getHomeworld(){
		return this.homeworld;
	}
	//fim set's e get's

	//metodo de printar
	public void print() throws Exception{
		DecimalFormat df = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.US));
		    
		String print = (" ## "+this.getNome()+" ## "+df.format(this.getAltura())+" ## "+df.format(this.getPeso())+" ## "+this.getCorDoCabelo()+" ## "+this.getCorDaPele()+
		" ## "+this.getCorDosOlhos()+" ## "+this.getAnoNascimento()+" ## "+this.getGenero()+ " ## "+this.getHomeworld()+" ##");
		String convertida = new String(print.getBytes(), "ISO-8859-1");
		MyIO.println(convertida);
	}

	public Personagem clone() {
		Personagem clone = new Personagem(getNome(), 
			getAltura(), 
			getPeso(), 
			getCorDoCabelo(), 
			getCorDaPele(), 
			getCorDosOlhos(), 
			getAnoNascimento(), 
			getGenero(), 
			getHomeworld());

		return clone;
	}
	//ler dados do arquivo
	public String lerArquivo(String arquivo) {
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
	
	//manipular dados
	//metodo para achar nome
	public void acharNome(String s) {
		String nome = new String();
		int j = 0;
		char aspas = '\'';
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'n' && s.charAt(i+1) == 'a' && s.charAt(i+2) == 'm' && s.charAt(i+3) == 'e') {
				j = i+8;
				while (s.charAt(j) != aspas) {
					nome += s.charAt(j);
					j++;
				}
			}
		}
		this.setNome(nome);
	}

	//metodo para achar a altura
	public void altura(String s) {
		String altura = "";
		int j = 0;
		char aspas = '\'';
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'h' && s.charAt(i+1) == 'e' && s.charAt(i+2) == 'i' && s.charAt(i+3) == 'g' && s.charAt(i+4) == 'h' && s.charAt(i+5) == 't') {
				j = i+10;
				while (s.charAt(j) != aspas) {
					altura += s.charAt(j);
					j++;
				}
			}
			
		}

		int alt = 0;
		try {
			alt = Integer.parseInt(altura);
		} catch (NumberFormatException e) {
			alt = 0;
		}
		this.setAltura(alt);
	}
	//metodo para achar o peso
	public void peso(String s) {
		NumberFormat format = NumberFormat.getNumberInstance(Locale.ENGLISH);
		format.setMinimumFractionDigits(0);
		String p = "";
		int j = 0;
		char aspas = '\'';
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'm' && s.charAt(i+1) == 'a' && s.charAt(i+2) == 's' && s.charAt(i+3) == 's') {
				j = i+8;
				while (s.charAt(j) != aspas) {
					p += s.charAt(j);
					j++;
				}
			}
		}
		double peso = 0;
		p = removeDig(p, ",");	
		try {
			peso = Double.parseDouble(p);
		} catch (NumberFormatException e) {
			//nada
		}
		this.setPeso(peso);

	}
	//metodo para retirar virgula
	public String removeDig(String s, String dig) {
		int cont = 0;
		
		for(int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == dig.charAt(0)) {

				cont++;
			}
		}
		String nova = new String();
		String[] split = new String[cont];
		split = s.split(dig);
		for (int i = 0; i < split.length; i++) {
			nova+=split[i];
		}

		return nova;
	}
	//metodo para achar a cor do cabelo
	public void corDoCabelo(String s) {
		String cor = "";
		int j = 0;
		char aspas = '\'';
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'h' && s.charAt(i+1) == 'a' && s.charAt(i+2) == 'i' && s.charAt(i+3) == 'r') {
				j = i+14;
				while (s.charAt(j) != aspas) {
					cor += s.charAt(j);
					j++;
				}
			}
		}
		this.setCorDoCabelo(cor);
	}

	//metodo para achar a cor da pele
	public void corDaPele(String s) {
		String cor = "";
		int j = 0;
		char aspas = '\'';
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 's' && s.charAt(i+1) == 'k' && s.charAt(i+2) == 'i' && s.charAt(i+3) == 'n') {
				j = i+14;
				while (s.charAt(j) != aspas) {
					cor += s.charAt(j);
					j++;
				}
			}
		}
		this.setCorDaPele(cor);
	}

	//metodo para achar a cor dos olhos
	public void corDosOlhos(String s) {
		String cor = "";
		int j = 0;
		char aspas = '\'';
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'e' && s.charAt(i+1) == 'y' && s.charAt(i+2) == 'e') {
				j = i+13;
				while (s.charAt(j) != aspas) {
					cor += s.charAt(j);
					j++;
				}
			}
		}
		this.setCorDosOlhos(cor);
	}

	//metodo para achar o ano de nascimento
	public void nascimento(String s) {
		String nascimento = "";
		int j = 0;
		char aspas = '\'';
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'b' && s.charAt(i+1) == 'i' && s.charAt(i+2) == 'r' && s.charAt(i+3) == 't' && s.charAt(i+4) == 'h') {
				j = i+14;
				while (s.charAt(j) != aspas) {
					nascimento += s.charAt(j);
					j++;
				}
			}
		}
		this.setAnoNascimento(nascimento);
	}

	//metodo para achar o genero
	public void genero(String s) {
		String genero = "";
		int j = 0;
		char aspas = '\'';
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'g' && s.charAt(i+1) == 'e' && s.charAt(i+2) == 'n' && s.charAt(i+3) == 'd' && s.charAt(i+4) == 'e' && s.charAt(i+5) == 'r') {
				j = i+10;
				while (s.charAt(j) != aspas) {
					genero += s.charAt(j);
					j++;
				}
			}
		}
		this.setGenero(genero);
	}
	
	//metodo  para achar o homeworld
	public void homeworld(String s) {
		String homeworld = "";
		int j = 0;
		char aspas = '\'';
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'h' && s.charAt(i+1) == 'o' && s.charAt(i+2) == 'm' && s.charAt(i+3) == 'e' && s.charAt(i+4) == 'w' && s.charAt(i+5) == 'o' && s.charAt(i+6) == 'r' && s.charAt(i+7) == 'l' && s.charAt(i+8) == 'd') {
				j = i+13;
				while (s.charAt(j) != aspas) {
					homeworld += s.charAt(j);
					j++;
				}
			}
		}
		this.setHomeworld(homeworld);
	}
	
}


class CelulaDupla extends Personagem{
	public Personagem elemento;
	public CelulaDupla ant;
	public CelulaDupla prox;

	/**
	 * Construtor da classe.
	 */
	public CelulaDupla() {
		
	}


	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
	public CelulaDupla(Personagem elemento) {
		this.elemento = elemento;
		this.ant = this.prox = null;
	}
}

class ListaDupla extends CelulaDupla {
	protected CelulaDupla primeiro;
	protected CelulaDupla ultimo;
	protected int n;

	public ListaDupla() {
		primeiro = new CelulaDupla();
		ultimo = primeiro;
		n = 0;
	}
	public int getN() {
		return this.n;
	}
	/**
	 * Insere um elemento na primeira posicao da lista.
	 * @param x int elemento a ser inserido.
	 */
	public void inserirInicio(Personagem x) {
		CelulaDupla tmp = new CelulaDupla(x);

		tmp.ant = primeiro;
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if(primeiro == ultimo){
			ultimo = tmp;
		} else {
			tmp.prox.ant = tmp;
		}
		tmp = null;
		n++;
	}

	/**
	 * Insere um elemento na ultima posicao da lista.
	 * @param x int elemento a ser inserido.
	 */
	public void inserirFim(Personagem x) {
		ultimo.prox = new CelulaDupla(x);
		ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
		n++;
	}

	/**
	 * Remove um elemento da primeira posicao da lista.
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Personagem removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

		CelulaDupla tmp = primeiro;
		primeiro = primeiro.prox;
		Personagem resp = primeiro.elemento;
		tmp.prox = primeiro.ant = null;
		tmp = null;
		n--;
		return resp;
	}

	/**
	 * Remove um elemento da ultima posicao da lista.
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Personagem removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 
		Personagem resp = ultimo.elemento;
		ultimo = ultimo.ant;
		ultimo.prox.ant = null;
		ultimo.prox = null;
		n--;
		return resp;
	}

	/**
	 * Insere um elemento em uma posicao especifica considerando que o 
	 * primeiro elemento valido esta na posicao 0.
	 * @param x int elemento a ser inserido.
	 * @param pos int posicao da insercao.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public void inserir(Personagem x, int pos) throws Exception {

		int tamanho = n;

		if(pos < 0 || pos > tamanho){
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
		} else if (pos == 0){
			inserirInicio(x);
		} else if (pos == tamanho){
			inserirFim(x);
		} else {
			// Caminhar ate a posicao ant a insercao
			CelulaDupla i = primeiro;
			for(int j = 0; j < pos; j++, i = i.prox);

			CelulaDupla tmp = new CelulaDupla(x);
			tmp.ant = i;
			tmp.prox = i.prox;
			tmp.ant.prox = tmp.prox.ant = tmp;
			tmp = i = null;
			n++;
		}
	}

	/**
	 * Remove um elemento de uma posicao especifica da lista
	 * considerando que o primeiro elemento valido esta na posicao 0.
	 * @param posicao Meio da remocao.
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public Personagem remover(int pos) throws Exception {
		Personagem resp;
		int tamanho = n;

		if (primeiro == ultimo){
			throw new Exception("Erro ao remover (vazia)!");

		} else if(pos < 0 || pos >= tamanho){
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
		} else if (pos == 0){
			resp = removerInicio();
		} else if (pos == tamanho - 1){
			resp = removerFim();
		} else {
			// Caminhar ate a posicao anterior a insercao
			CelulaDupla i = primeiro.prox;
			for(int j = 0; j < pos; j++, i = i.prox);

			i.ant.prox = i.prox;
			i.prox.ant = i.ant;
			resp = i.elemento;
			i.prox = i.ant = null;
			i = null;
			n--;
		}

		return resp;
	}

	
    public void mostrar(CelulaDupla i, int cont) throws Exception {

		for(i = primeiro.prox; i != null; i = i.prox, cont++) {
			DecimalFormat df = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.US));
		    
			String print = (" ## "+i.elemento.getNome()+" ## "+df.format(i.elemento.getAltura())+" ## "+df.format(i.elemento.getPeso())+" ## "+i.elemento.getCorDoCabelo()+" ## "+i.elemento.getCorDaPele()+
			" ## "+i.elemento.getCorDosOlhos()+" ## "+i.elemento.getAnoNascimento()+" ## "+i.elemento.getGenero()+ " ## "+i.elemento.getHomeworld()+" ## ");
			String convertida = new String(print.getBytes(), "ISO-8859-1");
			MyIO.println(convertida);
		}

		
	}
}

class Quick extends ListaDupla {
	private int quantidadeDeMovimentacoes = 0;


	public Quick() {
		super();
	}

	public int getQuantidadeDeMovimentacoes() {
		return this.quantidadeDeMovimentacoes;
	}

	public int tamanho() {
		int tam = 0;
		for(CelulaDupla i = primeiro; i != null; i = i.prox, tam++);
		return tam;
	}

	public int getPosicao(CelulaDupla celula) throws Exception{
		int pos = 0;
        for (CelulaDupla i = primeiro.prox; i != null && i != celula; i = i.prox, pos++);

        return pos;
	}

	void swap(CelulaDupla i, CelulaDupla j){
		Personagem tmp = j.elemento.clone();
		j.elemento = i.elemento.clone();
		i.elemento = tmp.clone();
		
	}

	public CelulaDupla getPivo(int inicio, int fim){
		CelulaDupla pivo = primeiro.prox;
		int pos = ((inicio + fim)/2);
		for (int i = 0; pivo != null && i < pos; pivo = pivo.prox, i++);

		return pivo;
	}

	public void quicksort() throws Exception{
        quicksort(primeiro.prox, ultimo);
    }

	public void quicksort(CelulaDupla esq, CelulaDupla dir) throws Exception{
        CelulaDupla i = esq;
        CelulaDupla j = dir;  
        
        int Inicio = getPosicao(i);
        int Fim = getPosicao(j);

        CelulaDupla pivo = getPivo(Inicio, Fim);
        
        int posi = Inicio;
        int posj = Fim;

        
		while (pivo.ant != j && pivo.prox != i) {
			while ((i != ultimo && i.elemento.getCorDoCabelo().compareTo(pivo.elemento.getCorDoCabelo()) < 0) || (i != ultimo && i.elemento.getCorDoCabelo().compareTo(pivo.elemento.getCorDoCabelo()) == 0 && i.elemento.getNome().compareTo(pivo.elemento.getNome()) < 0)){
				if(i.prox != null){
                    i = i.prox;
                    posi++;
                }
			}
			while ((j.ant != primeiro && j.elemento.getCorDoCabelo().compareTo(pivo.elemento.getCorDoCabelo()) > 0) || (j.ant != primeiro && j.elemento.getCorDoCabelo().compareTo(pivo.elemento.getCorDoCabelo()) == 0 && j.elemento.getNome().compareTo(pivo.elemento.getNome()) > 0)){
				if(j.ant != null){
                    j = j.ant;
                    posj--;
                }
			}

			if (posi <= posj){
				swap(i, j);
				i = i.prox; posi++;
                j = j.ant; posj--;
			}
        }
        
		if (Inicio < posj){
            quicksort(esq, j);
        }
            
		if (posi < Fim){
            quicksort(i, dir);
        }
            
	}
}

	



public class QuickS {
    static Quick lista = new Quick();

	public static boolean isFim(String frase) {
		boolean isFim = true;
		if (frase.length() == 3) {
				if (frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M') {
				isFim = false;
				}
			}
		return isFim;
	}

	public static void setPersonagem(String strArquivo, Personagem personagem) throws Exception{
            personagem.acharNome(strArquivo);
            personagem.altura(strArquivo);
            personagem.peso(strArquivo);
            personagem.corDoCabelo(strArquivo);
            personagem.corDaPele(strArquivo);
            personagem.corDosOlhos(strArquivo);
            personagem.nascimento(strArquivo);
            personagem.genero(strArquivo);
            personagem.homeworld(strArquivo);
     }


	public static void main(String args[]) throws Exception{

		String[] entrada = new String[1000];
		String strArquivo = new String();		
		int quantidadeEntrada = 0;


		do {

			entrada[quantidadeEntrada] = MyIO.readLine();

		} while (isFim(entrada[quantidadeEntrada++]) == true);
		quantidadeEntrada--;

        Personagem[] arquivo = new Personagem[quantidadeEntrada];
		Personagem[] personagem = new Personagem[quantidadeEntrada];


		for(int i = 0; i < quantidadeEntrada; i++) {
			arquivo[i] = new Personagem();
			String convertida = new String(entrada[i].getBytes("ISO-8859-1"), "UTF-8");
	        strArquivo = arquivo[i].lerArquivo(convertida);
	        personagem[i] = new Personagem();
			setPersonagem(strArquivo, personagem[i]);
			lista.inserirFim(personagem[i]);
		}

		lista.quicksort();
		CelulaDupla i = new CelulaDupla();
		int j = 0;
		lista.mostrar(i, j);

	}
}