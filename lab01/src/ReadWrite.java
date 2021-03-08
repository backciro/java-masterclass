import java.util.Scanner; 

public class ReadWrite { 
	
	public static void main(String[] args) { 
		
		Scanner input = new Scanner(System.in); // associa la tastiera alla variabile “input” 
		String s; 
		int n;

		System.out.print("Enter a string: "); 
		s = input.nextLine(); // leggi una stringa 
		System.out.println(s);

		System.out.print("Enter an integer number: "); 
		n = input.nextInt(); // leggi un intero 
		System.out.println(n);
		input.close();
	}
}