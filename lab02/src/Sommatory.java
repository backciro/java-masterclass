import java.util.*;

public class Sommatory {
	
	public static int iterativeSommatory(int N) {
		int res = 0;
		
		for (int i = 1; i <= N; i++)
			res += i;
		
		return res;
	}
	
	public static int recursiveSommatory(int ind, int res, int N) {
		
		if (ind == N) {
			res += N;
			return res; 
		}
			
		res += ind;
		res = recursiveSommatory(ind + 1, res, N);
		
		return res;
	}
	
	public static void main(String[] args) {
		
		System.out.println("Inserisci un numero intero N");
		
		Scanner input = new Scanner(System.in); // warning silencer
		int N = input.nextInt();
		input.close(); // warning silencer
		
		int IS, RS;
		
		IS = iterativeSommatory(N);
		RS = recursiveSommatory(1, 0, N);
		
		System.out.println("Sommatoria iterativa: " + IS + "\nSommatoria Ricorsiva: "+ RS);	
	}
	
}