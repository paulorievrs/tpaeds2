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
	
	//metodo leitura
	public void ler() {
		nome = MyIO.readString();
		altura = MyIO.readInt();
		peso = MyIO.readDouble();
		corDoCabelo = MyIO.readString();
		corDaPele = MyIO.readString();
		corDosOlhos = MyIO.readString();
		anoNascimento = MyIO.readString();
		genero = MyIO.readString();
		homeworld = MyIO.readString();
	}
	//metodo para transformar em string
	private String toStr(String s) {
		String str = "";
    
		for (int i = 0; i < s.length(); i++) {
		    str += s.charAt(i);
		}
    
		return str;
	  }
	//metodo de printar
	public void print() throws Exception{
		DecimalFormat df = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.US));
		    
		String print = (" ## "+this.getNome()+" ## "+df.format(this.getAltura())+" ## "+df.format(this.getPeso())+" ## "+this.getCorDoCabelo()+" ## "+this.getCorDaPele()+
		" ## "+this.getCorDosOlhos()+" ## "+this.getAnoNascimento()+" ## "+this.getGenero()+ " ## "+this.getHomeworld()+" ## ");
		String utf8 = toStr(print);
		String convertida = new String(utf8.getBytes(), "ISO-8859-1");
		MyIO.println(convertida);
	}

}


public class TP02 {
	//ler dados do arquivo
	public static String lerArquivo(String arquivo) {
		String ler = "";
		try {
			RandomAccessFile arq = new RandomAccessFile(arquivo, "r");
			ler = arq.readLine();
			arq.close();
		} catch (Exception e) {
			ler = "sem arquivo";
		} 
		return ler;
	}

	//verificar se a string é fim
	public static boolean isFim(String frase) {

		boolean isFim = true;

		if (frase.length() == 3) {

				if (frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M') {
				
				isFim = false;
				}
			}
		
		return isFim;
	}

	
	//manipular dados
	//metodo para achar nome
	public static void acharNome(String s, Personagem obj) {
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
		obj.setNome(nome);
	}

	//metodo para achar a altura
	public static void altura(String s, Personagem obj) {
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
		obj.setAltura(alt);
	}
	//metodo para achar o peso
	public static void peso(String s, Personagem obj) {
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
		obj.setPeso(peso);

	}
	//metodo para retirar virgula
	public static String removeDig(String s, String dig) {
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
	public static void corDoCabelo(String s, Personagem obj) {
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
		obj.setCorDoCabelo(cor);
	}

	//metodo para achar a cor da pele
	public static void corDaPele(String s, Personagem obj) {
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
		obj.setCorDaPele(cor);
	}

	//metodo para achar a cor dos olhos
	public static void corDosOlhos(String s, Personagem obj) {
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
		obj.setCorDosOlhos(cor);
	}

	//metodo para achar o ano de nascimento
	public static void nascimento(String s, Personagem obj) {
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
		obj.setAnoNascimento(nascimento);
	}

	//metodo para achar o genero
	public static void genero(String s, Personagem obj) {
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
		obj.setGenero(genero);
	}
	
	//metodo  para achar o homeworld
	public static void homeworld(String s, Personagem obj) {
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
		obj.setHomeworld(homeworld);
	}
	

	public static void main(String[] args) throws Exception {
		String[] entrada = new String[1000];
		int quantidadeEntrada = 0;
		String arquivo = "";

		
		do {

		entrada[quantidadeEntrada] = MyIO.readLine();
		} while (isFim(entrada[quantidadeEntrada++]) == true);
		quantidadeEntrada--;
		Personagem[] personagens = new Personagem[quantidadeEntrada];
		for (int i = 0; i < quantidadeEntrada; i++) {
			personagens[i] = new Personagem();
			arquivo = lerArquivo(entrada[i]);
			acharNome(arquivo, personagens[i]);
			altura(arquivo, personagens[i]);
			peso(arquivo, personagens[i]);
			corDoCabelo(arquivo, personagens[i]);
			corDaPele(arquivo, personagens[i]);
			corDosOlhos(arquivo, personagens[i]);
			nascimento(arquivo, personagens[i]);
			genero(arquivo, personagens[i]);
			homeworld(arquivo, personagens[i]);
			personagens[i].print();
			
		}
		
	}
}
