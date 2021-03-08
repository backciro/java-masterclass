import java.util.*;

public class Palindrome {

	private static boolean recPalindro(ArrayList<Integer> vett, int N, int i) {

		if (i > (N-1) / 2)
			return true;
		
		if (vett.get(i) != vett.get(N - 1 - i))
			return false;
		else if (recPalindro(vett, N, i + 1))
			return true;
			
		return false;
	}	
	
	private static boolean itePalindro(ArrayList<Integer> vett, int N) {
		
		int i, j;
		
		for(i = 0, j = N - 1; i >= j; i++, j--) {
			if(vett.get(i) != vett.get(j))
				return false;			
		}

		return true;
	}

	private static boolean testPalindrome(ArrayList<Integer> vett, int N) {
	
		boolean recursiveP, iterativeP;
		
		recursiveP = recPalindro(vett, N, 0);
		iterativeP = itePalindro(vett, N);
		
		return (recursiveP && iterativeP);
	}

	public static void main(String[] args) {
		
		System.out.println("Inserire un intero N, ed N interi in successione");
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		
		ArrayList<Integer> vett = new ArrayList<Integer>();
		
		for (int i = 0; i < N; i++) {
			vett.add(input.nextInt());
		} input.close();
		
		if (testPalindrome(vett, N))
			System.out.println("La stringa immessa e\' palindroma!");
		else
			System.out.println("La stringa immessa NON e\' palindroma!");	
	}
}