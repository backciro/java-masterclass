import java.util.*;

public class MMM {
	
	public static float media(ArrayList<Integer> vett, int N) {
		int cnt = 0;
		float media;
		
		for (int i = 0; i < N; i++)
			cnt += vett.get(i);
		
		media = ((float)cnt / (float) N);
		return media;
	}
	
	public static int moda(ArrayList<Integer> vett, int N) {
		
		int bestVal = 0, bestIndex = -1, Moda;
		int[] occorrenze = new int[N];
		
		for (int i = 0; i < N; i++) 
			occorrenze [i] = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (vett.get(i) == vett.get(j)) {
					occorrenze[i]++;
				}
			}
		}
			for (int x = 0 ; x < N; x++) {
				// qua viene semplicemente riportato il procedimento per avere tutte le mode,
				// nel programma sviluppato si e' scelto di ritornarne solo una, ovvero la prima trovata
				if (occorrenze[x] > bestVal) {
					bestIndex = x;
					bestVal = occorrenze[x];
				}
			}
		
		Moda = vett.get(bestIndex);
		return Moda;
	}
	
	public static int mediana(ArrayList<Integer> vett, int N) {
		int contaSup, contaInf,  bestVal = -1;
		
		for (int i = 0; i < N; i++) {
			
			contaSup = 0; 
			contaInf = 0;
			
			for (int j = 0; j < N; j++) {
				if (vett.get(i) >= vett.get(j))
					contaInf++;
				if (vett.get(i) <= vett.get(j))
					contaSup++;
			}
			if (contaInf == contaSup)
				bestVal = vett.get(i);
		}
		return bestVal;
	}
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		ArrayList<Integer> vett = new ArrayList<Integer>();
		int N = 0, s;

		while (N % 2 == 0) {
			System.out.println("Inserire un intero N DISPARI, ed N interi compresi [0 -> 100]");
			N = input.nextInt();	
		}
		for (int i = 0; i < N; i++) {
			s = input.nextInt();
		
			if(s >= 0 && s <= 100)
				vett.add(s);
			else {
				System.out.println("VALORE NON VALIDO ~~ [0 -> 100]");
				i--;
			}
		} input.close();
		
		 System.out.println("La media del vettore vale: " + media(vett, N));
		 System.out.println("La moda del vettore e\': "+ moda(vett, N));
		 System.out.println("La mediana del vettore e\': "+ mediana(vett, N));
	}
}