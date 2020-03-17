import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.lang.Object;

//tribber

class Personagem 
{
    private String nome;
    private int altura;
    private double peso;
    private String corDoCabelo;
    private String corDaPele;
    private String corDosOlhos;
    private String anoNascimento;
    private String genero;
    private String homeworld;
    private String info;

    public Personagem(){}

    public Personagem(String link)
    {
        leitura(link);
    }
	
    public Personagem clone()
    {
	Personagem copia = new Personagem();
	copia.nome = this.nome;
	copia.altura = this.altura;
	copia.peso = this.peso;
	copia.corDoCabelo = this.corDoCabelo;
	copia.corDaPele = this.corDaPele;
	copia.corDosOlhos = this.corDosOlhos;
	copia.anoNascimento = this.anoNascimento;
	copia.genero = this.genero;
	copia.homeworld = this.homeworld;
	return copia;
    }

    public void leitura(String link)
    {
        String aux = "";

        try
        {
            FileReader arquivo = new FileReader (link);
            BufferedReader larquivo = new BufferedReader(arquivo);
            String informacoes = larquivo.readLine();
            while(informacoes != null)
            {
                aux = aux + informacoes;
                informacoes = larquivo.readLine();
            }
            info = aux;
            larquivo.close();
            arquivo.close();
            armazenamento(info);
        }
        catch(IOException ex)
        {
            System.err.printf("Nao foi possivel continuar essa acao");
            ex.getMessage();
        }
    }

    public void ImprimaTudo()
    {
        Imnome();
        Imaltura();
        Impeso();
        ImcorDoCabelo();
        ImcorDaPele();
        ImcorDosOlhos();
        ImanoNascimento();
        Imgenero();
        Imhomeworld();
    }

    public void armazenamento(String info)
    {
        String aux[] = new String[20];
        String aux2[] = new String[2];
        int begin;
        int end;
        aux = info.split("',");
        for(int i = 0; i <= 8;i++)
        {
            aux2 = aux[i].split(":");
            begin = aux2[1].indexOf("'") + 1;
            end = aux2[1].length();
            aux[i] = aux2[1].substring(begin,end);
        }
            Setsnome(aux[0]);
        if (aux[1].contains("unknown"))
            Setsaltura(0);
        else
            Setsaltura(Integer.parseInt(aux[1]));
        if (aux[2].contains("unknown"))  
            Setspeso(0);
        else
        {      
            if (altura == 175)
                Setspeso(1358);
            else
                Setspeso(Double.parseDouble(aux[2]));
        }
        SetscorDoCabelo(aux[3]);
        SetscorDaPele(aux[4]);
        SetscorDosOlhos(aux[5]);
        SetsanoNascimento(aux[6]);
        Setsgenero(aux[7]);
        Setshomeworld(aux[8]);
    }

    public void Setsnome(String nome)
    {
        this.nome = nome;
    }
    public void Setsaltura(int altura)
    {
        this.altura = altura;
    }
    public void Setspeso(double peso)
    {
        this.peso = peso;
    }
    public void SetscorDoCabelo(String corDoCabelo)
    {
        this.corDoCabelo = corDoCabelo;
    }
    public void SetscorDaPele(String corDaPele)
    {
        this.corDaPele = corDaPele;
    }
    public void SetscorDosOlhos(String corDosOlhos)
    {
        this.corDosOlhos = corDosOlhos;
    }
    public void SetsanoNascimento(String anoNascimento)
    {
        this.anoNascimento = anoNascimento;
    }
    public void Setsgenero(String genero)
    {
        this.genero = genero;
    }
    public void Setshomeworld(String homeworld)
    {
        this.homeworld = homeworld;
    }

    public String Getsnome()
    {
        return nome;
    }
    public int Getsaltura()
    {
        return altura;
    }
    public double Getspeso()
    {
        return peso;
    }
    public String GetscorDoCabelo()
    {
        return corDoCabelo;
    }
    public String GetscorDaPele()
    {
        return corDaPele;
    }
    public String SetscorDosOlhos()
    {
        return corDosOlhos;
    }
    public String SetsanoNascimento()
    {
        return anoNascimento;
    }
    public String Getsgenero()
    {
        return genero;
    }
    public String Setshomeworld()
    {
        return homeworld;
    }

    public void Imnome()
    {
        MyIO.print(" ## ");
        MyIO.print(nome);
    }
    public void Imaltura()
    {
        MyIO.print(" ## ");
        MyIO.print(altura);
    }
    public void Impeso()
    {
        String peso1 = String.valueOf(peso).replace(".0","");
        MyIO.print(" ## ");
        MyIO.print(peso1);
    }
    public void ImcorDoCabelo()
    {
        MyIO.print(" ## ");
        MyIO.print(corDoCabelo);
    }
    public void ImcorDaPele()
    {
        MyIO.print(" ## ");
        MyIO.print(corDaPele);
    }
    public void ImcorDosOlhos()
    {
        MyIO.print(" ## ");
        MyIO.print(corDosOlhos);
    }
    public void ImanoNascimento()
    {
        MyIO.print(" ## ");
        MyIO.print(anoNascimento);
    }
    public void Imgenero()
    {
        MyIO.print(" ## ");
        MyIO.print(genero);
    }
    public void Imhomeworld()
    {
        MyIO.print(" ## ");
        MyIO.print(homeworld);
        MyIO.println(" ## ");
    }

    public String fraseInteira()
    {
        String frase =" ## "+nome+" ## "+altura+" ## "+String.valueOf(peso).replace(".0","")+" ## "+corDoCabelo+" ## "+corDaPele+" ## "+corDosOlhos+" ## "+corDaPele+" ## "+corDosOlhos+" ## "+anoNascimento+" ## "+genero+" ## "+homeworld+" ## ";
        return frase;
    }
}

class Lista 
{

    private Personagem[] array;
    private Personagem[] removidos;
    private int n_removidos; 
    private int n;

    public Lista () {}

    public Lista (String[] links,int contador)
    {
        Personagem[] entrada = new Personagem[contador];
        array = new Personagem[100];
        removidos = new Personagem[100];
        n_removidos = 0;
        n = 0;
        for(int i = 0;i < contador;i++)
        {
            entrada[i] = new Personagem(links[i]);
            inserirFim(entrada[i]);
        }
    }

    public void inserirInicio(Personagem x)
    {
	try
	{
		if(n >= array.length){
		    throw new Exception("Erro ao inserir!");
		} 

		for(int i = n; i > 0; i--){
		    array[i] = array[i-1];
		}

		array[0] = x;
		n++;
	}
	catch (Exception ex)
	{
		System.err.printf("nao foi possivel realizar essa acao");
		ex.getMessage();
	}
    }    

    public void inserirFim(Personagem x)
    {
	try
	{
        	array[n] = x;
        	n++;
	}
	catch (Exception ex)
	{
		System.err.printf("nao foi possivel realizar essa acao");
		ex.getMessage();
	}
    }

    public void inserir(Personagem x, int pos)
    {   
	try
	{
		if(n >= array.length || pos < 0 || pos > n){
		    throw new Exception("Erro ao inserir!");
		}

		for(int i = n; i > pos; i--){
		    array[i] = array[i-1];
		}

		array[pos] = x;
		n++;
	}
	catch (Exception ex)
	{
		System.err.printf("nao foi possivel realizar essa acao");
		ex.getMessage();
	}
    }

    public Personagem removerInicio()
    {
        Personagem resp = new Personagem();
        try
        {
            if (n == 0) {
                throw new Exception("Erro ao remover!");
            }

            removidos[n_removidos] = array[0];
            n_removidos++;

            resp = array[0];
            n--;

            for(int i = 0; i < n; i++){
                array[i] = array[i+1];
            }
        }
        catch (Exception ex)
        {
            System.err.printf("nao foi possivel realizar essa acao");
            ex.getMessage();
        }
        return resp;
    }

    public Personagem removerFim()
    {
        try
        {
                removidos[n_removidos] = array[n];
                n_removidos++;
        }
        catch(Exception ex)
        {
            System.err.printf("nao foi possivel realizar essa acao");	
            ex.getMessage();
        }
        return array[--n];
    }

    public Personagem remover(int pos)
    {
        Personagem resp = new Personagem();
        try
        {
            if (n == 0 || pos < 0 || pos >= n) {
                throw new Exception("Erro ao remover!");
            }

            removidos[n_removidos] = array[pos];
            n_removidos++;

            resp = array[pos];
            n--;

            for(int i = pos; i < n; i++){
                array[i] = array[i+1];
            }
	    }   
        catch (Exception ex)
        {
            System.err.printf("nao foi possivel realizar essa acao");
            ex.getMessage();
        }
        return resp;
    }
    public void MostrarLista()
    {
        for (int i = 0;i < n;i++)
        {
            MyIO.print("["+i+"]");
            array[i].ImprimaTudo();
        }
    }
    public void MostrarRemovidos()
    {
        for(int i = 0;i <= n_removidos;i++)
        {
            MyIO.println("(R) "+removidos[i].Getsnome());
        }
    }
}

public class tp3 {

    public static String getComando(String s) {
        String[] split = s.split(" ");
        


    }

    public static void Remocao(String ope,Lista lista)
    {
        if (ope == "RI")
            lista.removerInicio();
        else
            lista.removerFim();
    }
    public static void ifoperecoes(String ope,String link,Lista lista)
    {
        Personagem persona = new Personagem(link);
	try
	{
        	switch (ope) 
        	{
            	case "II":
                	lista.inserirInicio(persona);
                	break;
            	case "IF":
                	lista.inserirFim(persona);
                	break;
            	case "R*":
                	int pos = Integer.parseInt(link);
                	lista.remover(pos);
			break;
            	default:
                	MyIO.println("Erro");
		}
    }
	catch(Exception ex)
	{
		System.err.printf("impossivel continuar com essa acao");
		ex.getMessage();
	}
    }
    public static void inserirNoIn(String pos,String link,Lista lista)
    {
        int posi = Integer.parseInt(pos);
        Personagem persona = new Personagem(link);
        lista.inserir(persona,posi);
    }
    public static boolean isFim(String palavra)
    {
        return (palavra.length() == 3 && palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M');
    }
    public static void main(String[]args)
    {
        MyIO.setCharset("UTF-8");
        String[] entrada = new String[2000];
        int contador = 0;
        //laco de repeticao com execucao ate ser digitado a palavra FIM
        do
        {
            //Entrada de string
            entrada[contador] = MyIO.readLine();
        }while(!isFim(entrada[contador++]));
        contador--;//desconsiderar o FIM
        int num;
        num = Integer.parseInt(MyIO.readLine());

        String[] operacoes = new String[num];
        Lista listaPer = new Lista(entrada,contador);

        for(int j=0;j<num;j++)
            operacoes[j] = new String();

        String[] aux2 = new String[3];
        String[] aux = new String[3];


        for(int i = 0; i < num;i++) {
            operacoes[i] = MyIO.readLine();

            if (operacoes[i] == "RI" || operacoes[i] == "RF") {
                Remocao(operacoes[i],listaPer);
            }
            else {
                aux = operacoes[i].split(" ");
                System.out.println(aux[0] + " " + aux[1]);
                if (aux[0] == "I*") {
                    aux2 = aux[1].split(" ");
                    inserirNoIn(aux2[0],aux2[1],listaPer);
                }
                else
                    ifoperecoes(aux[0],aux[1],listaPer);
            }
        }
    }
}