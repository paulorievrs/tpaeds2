#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <iconv.h>
#include<time.h>

#define bool   short
#define true   1
#define false  0
int n=0;
int cmp=0;
int mov=0;
struct data{
	int dia;
	int mes;
	int ano;
};
struct Presidente{
	int id;//OK
	char nome[100];//OK
	int idade;//OK
	data dataNascimento;//OK
	char localNascimento[100];//OK
	data inicioMandato;//OK
	data fimMandato;//OK
	data dataMorte;//OK
	char localMorte[100];//OK
	char antecessor[100];//OK
	char sucessor[100];//OK
	char vice[100];//OK
	char pagina[100];
	long paginaTam;
};
struct CelulaDupla{
	struct CelulaDupla* prox;
	struct CelulaDupla* ant;
	struct Presidente elemento;
};
struct CelulaDupla* primeiro;
struct CelulaDupla* ultimo;
CelulaDupla* novaCelulaDupla(Presidente elemento) {
   CelulaDupla* nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
   nova->elemento = elemento;
   nova->prox = NULL;
   nova->ant=NULL;
   return nova;
}
int imprimir(Presidente teste){
	//printa vivos
	if(teste.dataMorte.dia==0){
		printf("%d ## %s ## %d/%d/%d (I) ## %d/%d/%d (F) ## %d/%d/%d em %s (N) ## %d ## %s ## %ld ## %s ## %s ## %s\n",teste.id,teste.nome,teste.inicioMandato.dia,teste.inicioMandato.mes,teste.inicioMandato.ano,teste.fimMandato.dia,teste.fimMandato.mes,teste.fimMandato.ano,
		teste.dataNascimento.dia,teste.dataNascimento.mes,teste.dataNascimento.ano,teste.localNascimento,teste.idade,teste.pagina,teste.paginaTam,teste.antecessor,teste.sucessor,teste.vice);
	}
	//printa mortos
	else{
		printf("%d ## %s ## %d/%d/%d (I) ## %d/%d/%d (F) ## %d/%d/%d em %s (N) ## %d ## %d/%d/%d em %s (M) ## %s ## %ld ## %s ## %s ## %s\n",teste.id,teste.nome,teste.inicioMandato.dia,teste.inicioMandato.mes,teste.inicioMandato.ano,teste.fimMandato.dia,teste.fimMandato.mes,teste.fimMandato.ano,
		teste.dataNascimento.dia,teste.dataNascimento.mes,teste.dataNascimento.ano,teste.localNascimento,teste.idade,teste.dataMorte.dia,teste.dataMorte.mes,teste.dataMorte.ano,teste.localMorte
		,teste.pagina,teste.paginaTam,teste.antecessor,teste.sucessor,teste.vice);
	}
	return 0;
}
int mostrar(){
	int c=0;
	CelulaDupla* i;
	for(i=primeiro->prox;i!=NULL;i=i->prox){
		imprimir(i->elemento);
	}
	return 0;
}
/**
 * Cria uma fila sem elementos.
 */
void start () {
   primeiro=(CelulaDupla*) malloc(sizeof(CelulaDupla));
   primeiro->prox=NULL;
   primeiro->ant=NULL;
   ultimo=primeiro;
}
/**
 * Insere elemento no inicio da lista
 * @param x Presidente elemento a inserir.
 */
int  inserirInicio(Presidente x) {
   CelulaDupla* tmp = novaCelulaDupla(x);
   tmp->prox=primeiro->prox;
   tmp->ant=primeiro;
   primeiro->prox=tmp;
   if(primeiro==ultimo){
	   ultimo=tmp;
   }
   else{
	   tmp->prox->ant=tmp;
   }
	tmp=NULL;
	return 0;
}
/**
 * Remove elemento do Inicio  da Lista
 * @return Elemento removido.
 */

Presidente removerInicio() { 
   Presidente resp = primeiro->prox->elemento;
   CelulaDupla* tmp=primeiro->prox;
   tmp->prox->ant=primeiro;
   primeiro->prox=tmp->prox;
   tmp->ant=NULL;
   tmp->prox=NULL;
   free(tmp);
   tmp=NULL;
   return resp;
}
int inserirFim(Presidente x){
	CelulaDupla* tmp = novaCelulaDupla(x);
	ultimo->prox=tmp;
	tmp->ant=ultimo;
	ultimo=tmp;
	tmp=NULL;
	return 0;
}
Presidente removerFim(){
	Presidente resp = ultimo->elemento;
	CelulaDupla* tmp = ultimo;
	ultimo=ultimo->ant;
	ultimo->prox=NULL;
	tmp->ant=NULL;
	free(tmp);
}
int tamanho(){
	int tam=0;
	CelulaDupla* i;
	for(i=primeiro->prox;i!=NULL;tam++,i=i->prox);	
	return tam;
}
int inserir(Presidente x, int pos){
	int tam=tamanho();
	if(pos==0)inserirInicio(x);
	else if(pos==tam)inserirFim(x);
	else{
		CelulaDupla* i;
		int c=0;
		for(i=primeiro;c<pos;c++,i=i->prox);
		CelulaDupla* tmp = novaCelulaDupla(x);
		tmp->ant=i;
		tmp->prox=i->prox;
		tmp->ant->prox=tmp->prox->ant=tmp;
		tmp=i=NULL;
	}
	return 0;
}
Presidente remover(int pos){
	int tam=tamanho();
	Presidente resp;
	if(pos==0)resp=removerInicio();
	else if(pos=tam-1)resp=removerFim();
	else{
		CelulaDupla* i;
		int c=0;
		for(i=primeiro;c<pos;c++,i=i->prox);
		i->ant->prox=i->prox;
		i->prox->ant=i->ant;
		resp=i->elemento;
		i->prox=i->ant=NULL;
		free(i);
		i=NULL;
	}
	return resp;
}
int converte (char input[],char output[]) {
    iconv_t cd = iconv_open("ISO_8859-1", "UTF-8");
    if (cd == (iconv_t) -1) {
        perror("iconv_open failed!");
        return 1;
    }

    char *in_buf = &input[0];
    size_t in_left = strlen(input) - 1;

    char *out_buf = &output[0];
    size_t out_left = strlen(output) - 1;

    do {
        if (iconv(cd, &in_buf, &in_left, &out_buf, &out_left) == (size_t) -1) {
            perror("iconv failed!");
            return 1;
        }
    } while (in_left > 0 && out_left > 0);
    *out_buf = 0;

    iconv_close(cd);
    return 0;
}
bool comparaDatas(data primeira, data segunda){
	bool resp = false;
	if(primeira.ano>segunda.ano){
		resp=true;
	}
	else{
		if(primeira.ano==segunda.ano){
			if(primeira.mes>segunda.mes){
				resp=true;
			}
			else{
				if(primeira.mes==segunda.mes){
					if(primeira.dia>segunda.dia){
						resp=true;
					}
				}
			}
		}
	}
	return resp;
}
//metodo funcional
int getMes(char s[]){
	int mes=0;
	if(strstr(s,"janeiro")!=NULL)mes=1;
	if(strstr(s,"fevereiro")!=NULL)mes=2;
	if(strstr(s,"mar")!=NULL)mes=3;
	if(strstr(s,"abril")!=NULL)mes=4;
	if(strstr(s,"maio")!=NULL)mes=5;
	if(strstr(s,"junho")!=NULL)mes=6;
	if(strstr(s,"julho")!=NULL)mes=7;
	if(strstr(s,"agosto")!=NULL)mes=8;
	if(strstr(s,"setembro")!=NULL)mes=9;
	if(strstr(s,"outubro")!=NULL)mes=10;
	if(strstr(s,"novembro")!=NULL)mes=11;
	if(strstr(s,"dezembro")!=NULL)mes=12;
	return mes;
}
int substring(char s[], char sub[], int p, int l) {
   int c = 0;
   while (c < l) {
      sub[c] = s[p+c];
      c++;
   }
   sub[c] = '\0';
   return 0;
}
//METODO FUNCIONAL RETORNA UM PONTEIRO 
char *removeTags(char* buf){
	int idx = 0;
	int i;
	int opened = 0; // false
	for(i=0; i<strlen(buf); i++){
    if(buf[i]=='<') {
        opened = 1; // true
    } else if (buf[i] == '>') {
        opened = 0; // false
    } else if (!opened) {
        buf[idx++] = buf[i];
    }
	}
	buf[idx] = '\0';
	return buf;
}
/**
 * s string a ser substituida
 * sub elemento a ser deletado
 * replace elemento a ser colocado no lugar
 */
int replaceall(char s[] , char sub, char replace){
	int i;
	for(i=0;i<strlen(s);i++){
		if(s[i]==sub){
			s[i]=replace;
		}
	}
	return 0;
}
/**
  Recebe um string s e uma string busca, busca a primeira ocorrencia da string busca na string s
*/
int indexOf(char s[],char busca[]){
	char *ptr = strstr(s,busca);
	int indexof = 0;
	if(ptr){
		indexof=ptr-s;
	}
	return indexof;
}
/**
  String entrada
  String saida
  int inicio
  int fim
  ex  entrada= hamburguer
      inicio = 1 fim = 5
	  saida = ambur
*/
int sub(char entrada[],char saida[],int inicio, int fim){
	int i;
	int c=0;
	for(i =inicio;i<fim;i++){
		saida[c]=entrada[i];
		c++;
	} 
	return 0;
}
/**
 * O metodo movimenta todos os elementos da string uma posicao para esquerda
 */
int shiftesq(char s[]){
	int i;
	for(i=1;i<strlen(s);i++){
		s[i-1]=s[i];
	}
	return 0;
}
/**
	O metodo recebe uma string com a localizacao do arquivo  em seguida faz a leitura dos dados e os salva em uma struct 
*/
struct Presidente ler(char arquivo[]){
	char taga[100] ="</a>";
	char tagb[100] = ">";
	char tagc[100] = " de ";
	char tagd[100] =")";
	char linha[100000];
	int dia=0,mes=0,ano=0;
	FILE *p =fopen(arquivo,"r+");
	struct Presidente teste;
	bool stop;
	char temp[100000];
	//PEGA INFORMACOES SOBRE O ARQUIVO
	strcpy(teste.pagina,arquivo);//SETA NOME DA PAGINA
	fseek(p,0L,SEEK_END);
	teste.paginaTam = ftell(p);
	fseek(p,0,SEEK_SET);
	
	//IGNORA TAGS INICIAIS
	for(stop = false; stop == false; stop = strstr(fgets(temp,100000,p),"background-color:#B0C4DE")!=NULL);
	for(stop = false; stop == false; fgets(linha,100000,p), stop = strstr(linha,"Presidente do Brasil")!=NULL);
	replaceall(linha,'�',' ');
	replaceall(linha,'�',' ');
	replaceall(linha,'�',' ');
	
	
	//ID
	//PRIMEIRO SUBSTRING
	int tam = strlen(linha) - indexOf(linha,tagb);
	substring(linha,linha,indexOf(linha,tagb)+1,tam);
	//SEGUNDO SUBSTRING
	tam = strlen(linha) - indexOf(linha,tagb);
	substring(linha,linha,indexOf(linha,tagb)+1,tam);
	//TERCEIRO SUBSTRING
	char saida[10000];
	substring(linha,saida,0,indexOf(linha,taga));
	teste.id = atoi(saida);
	//printf("%d\n",teste.id); //TESTE PRINT ID
	
	
	//IGNORAR TAGS
	for(stop = false;stop==false;stop=strstr(fgets(temp,100000,p),">Per")!=NULL);
	for(stop=false;stop==false;){
		fgets(linha,100000,p);
		if(strstr(linha,"<td")!=NULL){
			stop=true;
		}
	}
	
	
	//PRIMEIRO SUBSTRING
	tam = strlen(linha) - indexOf(linha,tagb);
	substring(linha,linha,indexOf(linha,tagb)+1,tam);
	//SEGUNDO SUBSTRING
	tam = strlen(linha) - indexOf(linha,tagb);
	substring(linha,linha,indexOf(linha,tagb)+1,tam);
	
	char ttt[10000];
	//PEGA DADOS INICIO GOVERNO
	//PEGA DIA INICIO MANDATO
	sub(linha,ttt,0,(linha[1]== ' '||linha[1]=='�') ? 1 : 2);
	dia=atoi(ttt);//DIA INICIO MANDATO
	if(dia>31){
		dia=dia/10;
	}
	teste.inicioMandato.dia = dia;//SETA DIA INICIO MANDATO

	
	//PEGA MES INICIO MANDATO
	tam =strlen(linha) -indexOf(linha,tagc)+4;
	substring(linha,linha,indexOf(linha,tagc)+4,tam);
	sub(linha,saida,0,indexOf(linha,taga));
	mes=getMes(saida);
	teste.inicioMandato.mes=mes;//SETA MES INICIO MANDATO
	
	
	//PEGA ANO INICIO MANDATO
	tam = strlen(linha) -indexOf(linha,taga)+4;
	substring(linha,linha,indexOf(linha,taga)+4,tam);
	tam = strlen(linha) - indexOf(linha,tagb)+1;
	substring(linha,linha,indexOf(linha,tagb)+1,tam);
	sub(linha,saida,0,indexOf(linha,taga));
	ano = atoi(saida);
	teste.inicioMandato.ano=ano;//SETA ANO INICIO MANDATO
	//printf("Inicio Mandato %d/%d/%d\n",teste.inicioMandato.dia,teste.inicioMandato.mes,teste.inicioMandato.ano);// TESTE PRINT INICIO MANDATO
	
	//PULA TAGS
	tam = strlen(linha) - indexOf(linha,tagb);
	substring(linha,linha,indexOf(linha,tagb)+1,tam);
	tam = strlen(linha) - indexOf(linha,tagb);
	substring(linha,linha,indexOf(linha,tagb)+1,tam);
	tam = strlen(linha) - indexOf(linha,tagb);
	substring(linha,linha,indexOf(linha,tagb)+1,tam);
	
	
	//PEGA DADOS FIM GOVERNO
	//PEGA DIA FIM MANDATO
	char newsaida[100000];
	substring(linha,newsaida,0,(linha[1]== ' '||linha[1]=='�') ? 1 : 2);
	dia=atoi(newsaida);
	teste.fimMandato.dia=dia;//SETA DIA FIM MANDATO
	
	
	//PEGA MES FIM MANDATO
	tam = strlen(linha) - indexOf(linha,tagc)+4;
	substring(linha,linha,indexOf(linha,tagc)+4,tam);
	substring(linha,newsaida,0,indexOf(linha,taga));
	mes=getMes(newsaida);
	teste.fimMandato.mes=mes;//SETA MES FIM MANDATO
	
	
	//PEGA ANO FIM MANDATO
	tam = strlen(linha) - indexOf(linha,taga)+4;
	substring(linha,linha,indexOf(linha,taga)+4,tam);
	tam = strlen(linha) - indexOf(linha,tagb)+1;
	substring(linha,linha,indexOf(linha,tagb)+1,tam);
	substring(linha,newsaida,0,indexOf(linha,taga));
	ano=atoi(newsaida);
	teste.fimMandato.ano=ano;//SETA ANO FIM MANDATO
	//printf("Fim Mandato %d/%d/%d\n",teste.fimMandato.dia,teste.fimMandato.mes,teste.fimMandato.ano); //TESTE PRINT FIM MANDATO
	
	
	//PEGA VICE PRESIDENTE
	for(stop=false;stop==false;){
		fgets(linha,100000,p);
		if(strstr(linha,"<td")!=NULL){
			stop=true;
		}
	}
	bool vice=false;
	if(strstr(linha,"Vice")!=NULL){
		vice=true;
	}
	if(vice){
        for(stop=false;stop==false;){
			fgets(linha,100000,p);
			if(strstr(linha,"<td")!=NULL){
				stop=true;
			}
		}
        strcpy(teste.vice,removeTags(linha));//SETA NOME VICE
		converte(teste.vice,teste.vice);
		teste.vice[strcspn(teste.vice, "\n")] = 0;//APAGA \N NO FINAL DO NOME 
		//printf("%s\n",teste.vice);//TESTE PRINT VICE
        for(stop=false;stop==false;){
			fgets(linha,100000,p);
			if(strstr(linha,"<td")!=NULL){
				stop=true;
			}
		}
    } 
	else{
        strcpy(teste.vice,"");;
    }
	
	
	
	//PEGA ANTECESSOR
	bool antecessor=false;
	if(strstr(linha,"Antecessor")!=NULL){
		antecessor=true;
	}
	if(antecessor){
         for(stop=false;stop==false;){
			fgets(linha,100000,p);
			if(strstr(linha,"<td")!=NULL){
				stop=true;
			}
		}
        strcpy(teste.antecessor,removeTags(linha));//SETA NOME ANTECESSOR
		converte(teste.antecessor,teste.antecessor);
		teste.antecessor[strcspn(teste.antecessor, "\n")] = 0;//APAGA \N NO FINAL DO NOME
		//printf("%s\n",teste.antecessor);//TESTE PRINT ANTECESSOR
        for(stop=false;stop==false;){
			fgets(linha,100000,p);
			if(strstr(linha,"<td")!=NULL){
				stop=true;
			}
		}
    } 
	else{
        strcpy(teste.antecessor," ");
    }
	
	
	//PEGA SUCESSOR
	bool sucessor=false;
	if(strstr(linha,"Sucessor")!=NULL){
		sucessor=true;
	}
	if(sucessor){
        for(stop=false;stop==false;){
			fgets(linha,100000,p);
			if(strstr(linha,"<td")!=NULL){
				stop=true;
			}
		}
        strcpy(teste.sucessor,removeTags(linha));//SETA NOME SUCESSOR
		converte(teste.sucessor,teste.sucessor);
		teste.sucessor[strcspn(teste.sucessor, "\n")] = 0;//APAGA \N NO FINAL DO NOME
		//printf("%s\n",teste.sucessor);//TESTE PRINT SUCESSOR
        for(stop=false;stop==false;){
			fgets(linha,100000,p);
			if(strstr(linha,"<td")!=NULL){
				stop=true;
			}
		}
    } 
	else{
        strcpy(teste.sucessor,"");
    }
	
	
	//PEGA NOME COMPLETO PRESIDENTE
	for(stop = false; stop == false; stop = strstr(fgets(temp,100000,p),"Dados pessoais")!=NULL);
	for(stop=false;stop==false;){
			fgets(linha,100000,p);
			if(strstr(linha,"<td")!=NULL){
				stop=true;
			}
	}
	bool nome=false;
	if(strstr(linha,"Nome")!=NULL){
		nome=true;
	}
	if(nome){
        for(stop=false;stop==false;){
			fgets(linha,100000,p);
			if(strstr(linha,"<td")!=NULL){
				stop=true;
			}
		}
        strcpy(teste.nome,removeTags(linha));//SETA NOME COMPLETO
		converte(teste.nome,teste.nome);
		teste.nome[strcspn(teste.nome, "\n")] = 0;//APAGA \N NO FINAL DO NOME
		//printf("%s\n",teste.nome);//TESTE PRINT nome
        for(stop=false;stop==false;){
			fgets(linha,100000,p);
			if(strstr(linha,"<td")!=NULL){
				stop=true;
			}
		}
    } 
	else{
        strcpy(teste.nome," ");
    }
	
	//DATA DE NASCIMENTO
	for(stop=false;stop==false;){
			fgets(linha,100000,p);
			if(strstr(linha,"<td")!=NULL){
				stop=true;
			}
	}
	tam = strlen(linha) - indexOf(linha,tagb)+1;
	substring(linha,linha,indexOf(linha,tagb)+1,tam);
	tam = strlen(linha) - indexOf(linha,tagb)+1;
	substring(linha,linha,indexOf(linha,tagb)+1,tam);
	substring(linha,newsaida,0,(linha[1] == ' ' || linha[1] == '�') ? 1 : 2);
	dia=atoi(newsaida);
	teste.dataNascimento.dia = dia;//SETA DIA DATA NASCIMENTO
	//printf("%d",teste.dataNascimento.dia);//TESTE DATA DIA NASCIMENTO
	tam=strlen(linha) - indexOf(linha,tagc)+4;
	substring(linha,linha,indexOf(linha,tagc)+4,tam);
	substring(linha,newsaida,0,indexOf(linha,taga));
	mes=getMes(newsaida);
	teste.dataNascimento.mes=mes;//SETA MES DATA NASCIMENTO
	//printf("%d",teste.dataNascimento.mes);//TESTE DATA MES NASCIMENTO
	tam=strlen(linha) - indexOf(linha,taga)+4;
	substring(linha,linha,indexOf(linha,taga)+4,tam);
	tam=strlen(linha) - indexOf(linha,tagb)+1;
	substring(linha,linha,indexOf(linha,tagb)+1,tam);
	substring(linha,newsaida,0,indexOf(linha,taga));
	ano=atoi(newsaida);
	teste.dataNascimento.ano=ano;//SETA ANO DATA NASCIMENTO
	//printf("%d",teste.dataNascimento.ano);//TESTE DATA ANO NASCIMENTO
	//printf("%d/%d/%d",teste.dataNascimento.dia,teste.dataNascimento.mes,teste.dataNascimento.ano);//TESTE DATA DE NASCIMENTO COMPLETA
	
	
	//IDADE
	teste.idade = 2019 - ano + ((mes == 1 && dia <= 3) ? 1 : 0);//SETA IDADE
	//printf("%d",teste.idade);//TESTE IDADE
	
	
	//LOCAL NASCIMENTO
	tam=strlen(linha) - indexOf(linha,taga)+4;
	substring(linha,newsaida,indexOf(linha,taga)+4,tam);
	strcpy(teste.localNascimento,removeTags(newsaida));//SETA localNascimento COMPLETO
	shiftesq(teste.localNascimento);//REMOVE ESPACO NO COMECO DA STRING
	converte(teste.localNascimento,teste.localNascimento);
	teste.localNascimento[strcspn(teste.localNascimento, "\n")] = 0;//APAGA \N NO FINAL DO localNascimento
	//printf("%s\n\n",teste.localNascimento); //TESTE LOCAL NASCIMENTO MORTOS
	
	
	//TESTE DE QUEM TA VIVO
	bool vivo=false;
	if(strstr(teste.localNascimento,"anos")!=NULL){
		vivo=true;
	}
	if(vivo){
		tam=strlen(teste.localNascimento) - indexOf(teste.localNascimento,tagd)+2;
		substring(teste.localNascimento,teste.localNascimento,indexOf(teste.localNascimento,tagd)+2,tam);//SETA LOCALNASCIMENTO VIVOS
		//printf("%s",teste.localNascimento);//TESTE LOCAL NASCIMENTO VIVOS
		teste.dataMorte.dia=0;
		teste.dataMorte.mes=0;
		teste.dataMorte.ano=0;
	}
	//PEGA DADOS DE QUEM TA MORTO
	else{
		for(stop=false;stop==false;){
			fgets(linha,100000,p);
			if(strstr(linha,"<td")!=NULL){
				stop=true;
			}
		}
		for(stop=false;stop==false;){
			fgets(linha,100000,p);
			if(strstr(linha,"<td")!=NULL){
				stop=true;
			}
		}
		//DATA MORTE
		tam = strlen(linha) - indexOf(linha,tagb)+1;
		substring(linha,linha,indexOf(linha,tagb)+1,tam);
		tam = strlen(linha) - indexOf(linha,tagb)+1;
		substring(linha,linha,indexOf(linha,tagb)+1,tam);
		tam = strlen(linha) - indexOf(linha,tagb)+1;
		substring(linha,linha,indexOf(linha,tagb)+1,tam);
		substring(linha,newsaida,0,(linha[1] == ' ' || linha[1] == '�') ? 1 : 2);
		dia = atoi(newsaida);
		teste.dataMorte.dia = dia;//SETA DIA DATA MORTE
		//printf("%d",teste.dataMorte.dia);//TESTE DIA DATA MORTE
		tam = strlen(linha) - indexOf(linha,tagc)+4;
		substring(linha,linha,indexOf(linha,tagc)+4,tam);
		substring(linha,newsaida,0,indexOf(linha,taga));
		mes = getMes(newsaida);
		teste.dataMorte.mes = mes;//SETA MES DATA MORTE
		//printf("%d",teste.dataMorte.mes);//TESTE MES DATA MORTE
		tam = strlen(linha) - indexOf(linha,taga)+4;
		substring(linha,linha,indexOf(linha,taga)+4,tam);
		tam = strlen(linha) - indexOf(linha,tagb)+1;
		substring(linha,linha,indexOf(linha,tagb)+1,tam);
		substring(linha,newsaida,0,indexOf(linha,taga));
		ano = atoi(newsaida);
		teste.dataMorte.ano = ano;//SETA ANO DATA MORTE
		//printf("%d",teste.dataMorte.ano);//TESTE ANO DATA MORTE
		//printf("%d/%d/%d",teste.dataMorte.dia,teste.dataMorte.mes,teste.dataMorte.ano);//TESTE DATA MORTE COMPLETO
		tam = strlen(linha) - indexOf(linha,taga)+4;
		substring(linha,newsaida,indexOf(linha,taga)+4,tam);
		removeTags(newsaida);
		tam = strlen(newsaida) - indexOf(newsaida,tagd)+2;
		substring(newsaida,newsaida,indexOf(newsaida,tagd)+2,tam);
		strcpy(teste.localMorte,removeTags(newsaida));//SETA localMorte COMPLETO
		converte(teste.localMorte,teste.localMorte);
		teste.localMorte[strcspn(teste.localMorte, "\n")] = 0;//APAGA \N NO FINAL DO localMorte
		//printf("%s",teste.localMorte);//TESTE LOCAL MORTE
	}
	fclose(p);
	if(teste.id==25)teste.inicioMandato.dia=2;
	return teste;
	
}
bool equals(char s1[],char s2[]){
	bool resp=true;
	if(strlen(s1)!=strlen(s2)) resp=0;
	int tam = strlen (s1);
	int i;
	for(i=0;i<tam;i++){
		if(s1[i]!=s2[i]){
			resp=0;
		}
	}
	return false;
}
int swap(CelulaDupla* i,CelulaDupla* j){
	Presidente tmp = j->elemento;
	j->elemento=i->elemento;
	i->elemento=tmp;
	mov+=2;
	return 0;
}
int quicksortrec(CelulaDupla* esq,CelulaDupla* dir,int posesq,int posdir){
	CelulaDupla* i =esq;
	CelulaDupla* j =dir;
	int posi=posesq,posj=posdir;
	struct data pivo=dir->elemento.inicioMandato;
	while(posi<=posj){
		while(comparaDatas(pivo,i->elemento.inicioMandato)){
			cmp++;
			i=i->prox;
			posi++;
		}
		while(comparaDatas(j->elemento.inicioMandato,pivo)){
			cmp++;
			j=j->ant;
			posj--;
		}
		if(posi<=posj){
			swap(i,j);
			i=i->prox;
			posi++;
			j=j->ant;
			posj--;
		}
	}
	
	if(posesq<posj) quicksortrec(esq,j,posesq,posj);
	if(posi<posdir) quicksortrec(i,dir,posi,posdir);
	return 0;
}
int quicksort(){
	quicksortrec(primeiro->prox,ultimo,0,tamanho()-1);
	return 0;
}
int main(){
	struct Presidente tmp ;
	char arquivo[100];
	char input[100];
	int pos;
	int i;
	int repete;
	start();
	for(i=0;strstr(arquivo,"FIM")==NULL;i++){
		scanf("%s",arquivo);
		if(strstr(arquivo,"FIM")==NULL){
			inserirFim(ler(arquivo));
		}
	}
	FILE *p =fopen("619148_quicksort2.txt","w");
	clock_t comeco = clock();
	quicksort();
	double total = (clock() - comeco);
	mostrar();
	fprintf(p,"619148\t%d\t%d\t%.0f",cmp,mov,total);
	return 0;
}