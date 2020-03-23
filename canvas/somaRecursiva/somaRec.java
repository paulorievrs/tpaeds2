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
		" ## "+this.getCorDosOlhos()+" ## "+this.getAnoNascimento()+" ## "+this.getGenero()+ " ## "+this.getHomeworld()+" ## ");
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


class Celula {
	public int elemento; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.


	/**
	 * Construtor da classe.
	 */
	public Celula() {
		this(0);
	}

	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
	public Celula(int elemento) {
      		this.elemento = elemento;
      		this.prox = null;
	}
}


public class Pilha {
	private Celula topo;

	/**
	 * Construtor da classe que cria uma fila sem elementos.
	 */
	public Pilha() {
		topo = null;
	}


	/**
	 * Insere elemento na pilha (politica FILO).
	 * @param x int elemento a inserir.
	 */
	public void inserir(int x) {
		Celula tmp = new Celula(x);
		tmp.prox = topo;
		topo = tmp;
      		tmp = null;
	}


	/**
	 * Remove elemento da pilha (politica FILO).
	 * @return Elemento removido.
	 * @trhows Exception Se a sequencia nao contiver elementos.
	 */
	public int remover() throws Exception {
		if (topo == null) {
			throw new Exception("Erro ao remover!");
		}

		int resp = topo.elemento;
	        Celula tmp = topo;
		topo = topo.prox;
      		tmp.prox = null;
      		tmp = null;
		return resp;
	}


	/**
	 * Mostra os elementos separados por espacos, comecando do topo.
	 */
	public void mostrar() {
		System.out.print("[ ");
		for(Celula i = topo; i != null; i = i.prox){
			System.out.print(i.elemento + " ");
     		 }
		System.out.println("] ");
	}
}

public class SomaRecursiva {
	public static void montarPilha() {
		
	}


	public static void main(String[] args) {

		

	}
}
