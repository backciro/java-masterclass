import java.util.Scanner;

public class Piramid {
	
	public static void main(String[] args) {
		
		int N;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Inserisci un intero positivo");
		N = input.nextInt();
		input.close();
		
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < x + 1; y++){
				System.out.print('*');
			} System.out.println();
		}
	
		
	}
}