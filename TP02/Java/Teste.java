public class Teste {
	public static void main (String args[]) throws Exception {


		Personagem person = new Personagem("Jose", 10, 10.5, "vermelho", "branca", "verde", "21/01/2001", "masculino", "brasil");
		Personagem clone = new Personagem();
		clone = person.clone();
		clone.print();
	
	}
}