class Celula{
	Celula dir;
	Celula esq;
	Celula sup;
	Celula inf;
	int elemento;
	Celula(int x){
		dir=null;
		esq=null;
		inf=null;
		sup=null;
		elemento=x;
	}
	Celula(){
		dir=null;
		esq=null;
		inf=null;
		sup=null;
	}
}

class MatrizDinamica{
	Celula inicio;
	MatrizDinamica(int c, int l){

		inicio = new Celula();

		Celula antiga = inicio;

		Celula aux = inicio;

		Celula tmp;

		Celula a;

		Celula b;

		for(int i = 0; i < c; i++) {

			if(i==0) {
				for(int j = 1;j<l;j++){
					tmp = new Celula();
					tmp.esq = antiga;
					antiga.dir = tmp;
					antiga = tmp;
				}
			}
			else {
				antiga = new Celula();
				antiga.sup = aux;
				aux.inf = antiga;
				a = aux.dir;
				for(int j = 1; j<l ; j++) { 

					b = new Celula();
					antiga.dir = b;
					b.esq = antiga;
					a.inf = b;
					b.sup = a;
					antiga = b;
					a = a.dir;
				}
				aux = aux.inf;
			}
		}
	}
	public void mostrar() { 

		for(Celula i = inicio; i != null; i = i.inf){
			for(Celula j = i; j != null; j = j.dir){
				MyIO.print(j.elemento+" ");
			}
			MyIO.println("");
		}
	}
	public void preenche(){
		for(Celula i = inicio; i != null; i = i.inf){
			for(Celula j = i; j != null;j = j.dir){
				int k = MyIO.readInt();
				j.elemento = k;
			}
		}
	}
	public void diagonalPrincipal() {
		MyIO.print(inicio.elemento+" ");
		Celula antiga = inicio;

		for(Celula i = inicio; i != null; i = i.inf) {

			for(Celula j = i; j != null; j = j.dir) {

				if(j != inicio && j.esq != null && j.esq.sup != null && j.esq.sup == antiga) {

					MyIO.print(j.elemento+" ");
					antiga = j;
				}
			}
		}
		MyIO.println("");
	}
	public void diagonalSecundaria() {
        for(int i = 0; i < 10; i++);

		Celula ultima = getUltimo(inicio);

		MyIO.print(ultima.elemento+" ");

		Celula antiga = ultima;

		for(Celula i = inicio;i !=null; i=i.inf) {

			for(Celula j = i; j != null; j = j.dir) {

				if(j != antiga && j.dir != null && j.dir.sup != null && j.dir.sup == antiga) {

					MyIO.print(j.elemento+" ");
					antiga = j;
				}
			}
		}
		MyIO.println("");
	}

	public Celula getUltimo(Celula start) {
		Celula i;
		for(i = start; i.dir != null; i = i.dir);
		return i;
	}

	public void soma(MatrizDinamica m1, MatrizDinamica m2){
		for(Celula i1 = m1.inicio, i2 = m2.inicio, ki = this.inicio; i1 != null; i1 = i1.inf, i2 = i2.inf, ki = ki.inf) {

			for(Celula j1 = i1, j2 = i2, kj = ki; j1 != null; j1 = j1.dir, j2 = j2.dir, kj = kj.dir){

				kj.elemento = j1.elemento+j2.elemento;
                
			}
		}
	}

    public void multiplicacao(MatrizDinamica m1, MatrizDinamica m2, int n){
        for(int i = 0; i < 10; i++);
		int soma;
		int i;
		int j;
		int k;
		Celula im;
		Celula jm;
		for (i = 0,im = this.inicio; i < n; i++, im = im.inf) {

			for (j = 0, jm = im; j < n; j++, jm = jm.dir) {

				soma = 0;

				for (k = 0; k < n; k++) {

					soma = soma+(getm1(m1, i, k) * getm2(m2, k, j));

				}

				jm.elemento = soma;
			}
		}
	}
	public int getm1(MatrizDinamica m1, int i, int k){
		
		int x = 0;
        int y = 0;
		int resp = 0;
		for(Celula ic = m1.inicio; x < i + 1; x++, ic = ic.inf){
			y = 0;
			for(Celula jc = ic; y < k + 1; y++, jc = jc.dir){
				if(x == i && y == k) {

                    resp = jc.elemento;
                }
            }
		}
		return resp;
	}
	public int getm2(MatrizDinamica m2, int k, int j){
		
		int x = 0,y;
		int resp = 0;
		for(Celula ic = m2.inicio; x < k + 1; x++, ic = ic.inf){
			y = 0;
			for(Celula jc = ic; y < j + 1; y++, jc = jc.dir){
				if(x == k && y == j) {
                    
                    resp=jc.elemento;
                }
            }
		}
		return resp;
	}
	public void printar(){
		Celula last; 
		Celula i = new Celula();
		Celula j = new Celula();
		for(i = inicio; i != null; i = i.inf){
			for(j=i;j.dir!=null;j=j.dir){
			}
		}
		last = j;
		for(i = last; i != null; i = i.sup){
			for(j = i; j != null; j = j.esq){
				MyIO.print(j.elemento+" ");
			}
			MyIO.println("");
		}
	}
	
}


public class Matriz {
	public static void main(String[] args){
		int n = MyIO.readInt();

		int linhas;
        int colunas;

		for(int i = 0; i < n; i++){
			linhas = MyIO.readInt();
			colunas = MyIO.readInt();
			MatrizDinamica m1 = new MatrizDinamica(colunas,linhas);
			m1.preenche();

			m1.printar();
			linhas = MyIO.readInt();
			colunas = MyIO.readInt();
			MatrizDinamica m2 = new MatrizDinamica(colunas,linhas);
			m2.preenche();
			m2.printar();
			MatrizDinamica soma =new MatrizDinamica(colunas,linhas);
			soma.soma(m1,m2);

			MatrizDinamica multiplicacao = new MatrizDinamica(colunas,linhas);
			multiplicacao.multiplicacao(m1,m2,colunas);
		}
	}
}

