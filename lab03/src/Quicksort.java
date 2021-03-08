import java.util.*;

public class Quicksort {

	public static int partition(ArrayList<Integer> vett, int i, int j) { 
		int x,t;
		
		x = vett.get(j - 1); // partition a la Lo Muto
		while (true) { 
			while ( vett.get(i) < x ) i++ ; 
			while ( vett.get(j) > x ) j-- ; 
			if ( i < j ) { 
				t = vett.get(i); 
				vett.set(i, vett.get(j));
				vett.set(j, t); 
				i++; 
				j--; 
			} 
			else return j;
		}
	}
	
	public static void quickSort(ArrayList<Integer> vett, int l, int h) { 
		int m;

		if ( l < h ) { 
			m = partition(vett, l, h); 
			
			for(int i:vett) 
				System.out.print(i +" ");
			System.out.println();
			
			quickSort(vett, l, m); 
			quickSort(vett, m+1, h); 
			}
	}
	
	public static void main(String[] args) {
	
		ArrayList<Integer> vett = new ArrayList<Integer>();
		
		System.out.println("Inserire un intero N, ed N interi in successione");
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		
		for (int i = 0; i < N; i++) {
			vett.add(input.nextInt());
		} input.close();
		 
		quickSort(vett, 0, N - 1); 	
	}
}