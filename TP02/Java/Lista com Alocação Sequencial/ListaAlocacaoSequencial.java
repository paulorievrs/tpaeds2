import java.text.*;
import java.io.*;
import java.util.*;
/*
* Classe Personagem TP02 Q01 - Java
* @author Paulo Rievrs Oliveira
* @version 1 02/2020
*/



class Personagem{
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
	public void print(int i) throws Exception{
		DecimalFormat df = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.US));
		    
		String print = ("["+i+"] "+" ## "+this.getNome()+" ## "+df.format(this.getAltura())+" ## "+df.format(this.getPeso())+" ## "+this.getCorDoCabelo()+" ## "+this.getCorDaPele()+
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
import java.io.*;

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


class MetodosLista {
      private Personagem[] personagemA;
      private int n;

      public MetodosLista() {
		this(1000);
	}

	public MetodosLista(int tam) {
		personagemA = new Personagem[tam];
		n = 0;
	}

	public void setN(int n) {
		this.n = n;
	}
	public int getN() {
		return this.n;
	}
	public void setPersonagensA(int posicao, Personagem[] personagemA) {
		this.personagemA = personagemA.clone();
	}
	public Personagem[] getPersonagemA() {
		return this.personagemA;
	}

	

      public void inserirInicio(Personagem personagem) throws Exception {
            if (n >= personagemA.length) {
                  throw new Exception("Erro ao inserirInicio coom:" + personagem);
            }

            for(int i = n; i > 0; i--) {
                  personagemA[i] = personagemA[i-1].clone();
            }
            personagemA[0] = personagem.clone();
            n++;
      }

      public void inserir(Personagem personagem, int posicao) throws Exception{
            if (n >= personagemA.length ||posicao < 0 || posicao > n) {
                  throw new Exception("Erro ao inserir com:" + personagem);
            }
            for(int i = n; i > posicao; i--) {
                  personagemA[i] = personagemA[i - 1].clone();
            }
            personagemA[posicao] = personagem.clone();
            n++;
      }

      public void inserirFim(Personagem personagem) throws Exception {
            if (n >= personagemA.length) {
                  throw new Exception("Erro ao inserirFim com: " + personagem);
            }
            personagemA[n] = personagem.clone();
            n++;
      }
      
      public Personagem removerInicio()throws Exception {
            if (n == 0) {
                  throw new Exception("Erro ao removerInicio ");
            }
            Personagem personagemRetorno = personagemA[0].clone();
            n--;
            for(int i = 0; i < n; i++) {
                  personagemA[i] = personagemA[i+1].clone();
            }

            return personagemRetorno;

      }

      public Personagem remover(int posicao) throws Exception{
            if (n == 0 || posicao < 0 || posicao >= n) {

                  throw new Exception("Erro ao remover, n = " + n + " posicao = " + posicao);
		}
		n--;
		
            Personagem personagemRetorno = personagemA[posicao].clone();
            for (int i = posicao; i < n; i++) {
                  personagemA[i] = personagemA[i + 1].clone();
            }
            return personagemRetorno;
      }

      public Personagem removerFim() throws Exception {
            if (n == 0) {
                  throw new Exception("Erro removerFim");
            }
            return personagemA[--n];
	}
	
	
}


public class ListaAlocacaoSequencial {
	static MetodosLista lista = new MetodosLista(1000);


      public static boolean isFim(String frase) {
		boolean isFim = true;
		if (frase.length() == 3) {
				if (frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M') {
				isFim = false;
				}
			}
		return isFim;
	}
      

      public static String getComandos(String s) {
            String[] comandos = s.split(" ");
            return comandos[0];
      }
      public static String getArquivos(String s){
            String[] arquivos = s.split(" ");
            String retorno = new String();
            if(arquivos.length == 2) {
                  retorno = arquivos[1];
            } else {
                  retorno = arquivos[2];
            }
            return retorno;
      }

      public static int getPosicao(String s) {
            String[] posicao = s.split(" ");
            return Integer.parseInt(posicao[1]);
      
      }

      public static Personagem setPersonagem(String strArquivo, Personagem personagem) throws Exception{
            personagem.acharNome(strArquivo);
            personagem.altura(strArquivo);
            personagem.setPeso(0);
            personagem.corDoCabelo(strArquivo);
            personagem.corDaPele(strArquivo);
            personagem.corDosOlhos(strArquivo);
            personagem.nascimento(strArquivo);
            personagem.genero(strArquivo);
            personagem.homeworld(strArquivo);
            aux.inserirInicio(personagem);
            return personagem;
      }
      
      public static Personagem[] lista(String[] pubin, int n) throws Exception {
        String comando = new String();
		String arquivo = new String();
		Personagem personagens[] = new Personagem[n];
		int posicao = 0;
		Personagem p = new Personagem();
            for(int i = 0; i < n; i++) {
			comando = getComandos(pubin[i]);
			personagens[i] = new Personagem();
                  switch (comando) {
                        case "II":
                              pubin[i] = new String(pubin[i].getBytes("ISO-8859-1"), "UTF-8");
                              arquivo = getArquivos(pubin[i]);
                              arquivo = personagens[i].lerArquivo(arquivo);
                              setPersonagem(arquivo, personagens[i]);
                              lista.inserirInicio(personagens[i]);
                        break;
                        case "IF": 
                              pubin[i] = new String(pubin[i].getBytes("ISO-8859-1"), "UTF-8");
                              arquivo = getArquivos(pubin[i]);
                              arquivo = personagens[i].lerArquivo(arquivo);
                              setPersonagem(arquivo, personagens[i]);
                              lista.inserirFim(personagens[i]);
                        break;
                        case "R*":
                          	posicao = getPosicao(pubin[i]);
							p = lista.remover(posicao);
							MyIO.println("(R) " + p.getNome());
                        break;
                        case "RI":
							p = lista.removerInicio();
							MyIO.println("(R) " + p.getNome());
                        break;
		                case "RF":
							p = lista.removerFim();
							MyIO.println("(R) " + p.getNome());
                        break;
                        case "I*":
                              pubin[i] = new String(pubin[i].getBytes("ISO-8859-1"), "UTF-8");
                              arquivo = getArquivos(pubin[i]);
                              arquivo = personagens[i].lerArquivo(arquivo);
                              posicao = getPosicao(pubin[i]);
                              setPersonagem(arquivo, personagens[i]);
                              lista.inserir(personagens[i], posicao);
                        break;
                        default:
                              MyIO.println("faltou o comando: " + getComandos(pubin[i]));
                  }

            }

            return personagens;
      }


      public static void main(String args[]) throws Exception {
            MyIO.setCharset("ISO-8859-1");
			String[] entrada = new String[1000];
			int quantidadeEntrada = 0;
			String strArquivo = "";
            String[] pubin = new String[1000];

            //inicio leitura de dados
		do {

			entrada[quantidadeEntrada] = MyIO.readLine();
		} while (isFim(entrada[quantidadeEntrada++]) == true);
            int n = MyIO.readInt();                  
			quantidadeEntrada--;
            for (int i = 0; i < n; i++) {
                  pubin[i] = MyIO.readLine();
            }
		//fim leitura de dados

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
		
		lista(pubin, n);
		Personagem print[] = lista.getPersonagemA();
		for(int i = 0; i < lista.getN(); i++) {
			print[i].print(i);
		}
		
		

      }
}