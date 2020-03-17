public class Outro {
	public static void main(String[] args) {

		for(int k = 0; k < 5; k++) {

			for(int i = 0; i < 3; i++) {
				System.out.println("Entrou i " + i);
				for(int j = 0; j < 3; j++) {
					System.out.println("entrou j " + j);
					if (j == 1) {
						i = 3;
						j = 3;
					}
				}
			}
		}

	}
}