import java.util.Scanner;

public class RomansNum {

	public static void main(String[] args){
		
		int N; 
		Scanner input = new Scanner(System.in);
		System.out.println("Inserisci un numero da 1 a 9\n");
		
		N = input.nextInt();
		input.close();
		
		if(N <= 3) {
			for(int a = 0; a < N; a++)
				System.out.print("I");
		}
		
		else if(N <= 5) {
			for(int a = 0; a < 5 - N; a++)
				System.out.print("I");
			System.out.println("V");
		}
		
		else if(N <= 8) {
			System.out.print("V");
			for(int a = 0; a < N - 5; a++)
				System.out.print("I");
		}
		
		else if (N == 9){
			System.out.println("IX");
		}
		
		else System.out.println("ERROR || ONLY NUMBERS 1 ~ 9");
	}	
}