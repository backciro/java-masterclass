import java.util.*;

public class Diagonali {
	
	public static void recursionPrint(int start, int N) {
		
		int whiteSpaces = (N - start) / 2;
		
		if (start == N){
			for (int i = 0; i < start; i++)
				System.out.print('*');
			System.out.println();
			return;
		}
			
		for (int w = 0; w < whiteSpaces; w++) // stampo spazi bianchi per centramento
			System.out.print(' ');
		
		for (int i = 0; i < start; i++)
			System.out.print('*');
		System.out.println(); // newLine
		
		recursionPrint(start + 2, N);
		
		for (int w = 0; w < whiteSpaces; w++) // stampo spazi bianchi per centramento
			System.out.print(' ');
		
		for (int i = 0; i < start; i++)
			System.out.print('*');
		System.out.println(); // newLine
		
		return;
		
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		int N; 
		System.out.println("Inserisci un numero intero");
		N = new Scanner(System.in).nextInt();
		
		recursionPrint(1, (2 * N + 1));
	
	}
	
	
}