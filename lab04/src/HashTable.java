import java.util.*;

public class HashTable {
	
	static int[] HT = new int[40]; 				// hashing table
	static float alpha = (float)30/(float)40;	// alpha factor
	static int n = 30, N = 40;					// variables defined here
	
	private static int hashing (int x) {
		int base = 127;
		
		int i = ((x % base) % N); 		// mine hashing method

		return i;
	}
	
	private static boolean isFull(int x) {
		return (HT[x] != -1);			// method for check if the cell is empty or not
	}
	
	public static void HTcarica(int[] val) {
		int x;							// data update method
		
		for (int i = 0; i < N; i++) HT[i] = -1; // initialization 
		
		System.out.println("Caricamento dati in corso...");
		for (int i = 0; i < n; i++) { 
			
			x = hashing(val[i]);
			System.out.print("Tentativo di caricamento elemento: '"+ val[i] +"' in posizione: ["+ x +"] ");
			
			while (isFull(x)) {
				x++;			// linear probing hashing
				
				if (x >= n)
					x = x % n;
				System.out.print("-> COLLISIONE! Nuovo tentativo nella cella: [" + x + "] ");
			}
			
			HT[x] = val[i];
			System.out.println("\t~ Elemento: '"+ val[i] +"' caricato in posizione: ["+ x +"] ~");
		}
		System.out.println(); System.out.println();
	}
	
	public static void HTstampa() {
			System.out.println("Cella\tElemento");
		for (int i = 0 ; i < N ; i++) {
			System.out.println("["+i+"]\t'"+HT[i]+"' ");
		} System.out.println(); System.out.println();
	}
	
	public static void HTcerca() {
		
		int x, h, cnt = 0;
		System.out.println("Inserisci un valore da ricercare");
		Scanner input = new Scanner(System.in);
		x = input.nextInt(); input.close();
		
		h = hashing(x);
		
		while (true) {
			
			if (h >= N)
				h = h % N;
			
			if (HT[h] == x && cnt == 0) {
				System.out.println("Elemento: '"+ x +"' trovato in posizione: ["+ h +"] al primo sondaggio");
				break;
			}
			else if (HT[h] == x && cnt > 0) {
				System.out.println("Elemento: '"+ x +"' trovato in posizione: ["+ h +"] dopo "+ cnt +" sondaggi");
				break;
			}
			
			h++; cnt++;
		
			if (cnt >= N){
				System.out.println("Elemento: "+ x +" non trovato nella tabella!");
				break;
			}
		}
	}

	public static void main (String[] args) {
		
		// input values stored into int array
		int[] val = { 12, 34, 303, 56, 67, 78, 89, 90, 945, 342, 845, 188, 921, 979, 1, 
				 34, 676, 812, 246, 75, 96, 94, 133, 40, 11, 555, 231, 696, 777, 7 } ;
		
		HTcarica(val);
		HTstampa();
		HTcerca();		
	}
}