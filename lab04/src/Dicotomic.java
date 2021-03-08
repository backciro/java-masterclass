import java.util.*;

// 30 valori da 1 a 1000 ; ordino ; ricerco con succeesso e fallimento dicotomico ricorsivo ;

public class Dicotomic {
	
	public static int partition(int[] vett, int i, int j) { 
		int x,t;
		
		x = vett[j - 1]; // partition a la Lo Muto
		while (true) { 
			while ( vett[i] < x ) i++ ; 
			while ( vett[j] > x ) j-- ; 
			if ( i < j ) { 
				t = vett[i]; 
				vett[i] = vett[j];
				vett[j] = t; 
				i++; 
				j--; 
			} 
			else return j;
		}
	}
	
	public static void quickSort(int[] vett, int l, int h) { 
		int m;

		if ( l < h ) { 
			m = partition(vett, l, h); 
			
			quickSort(vett, l, m); 
			quickSort(vett, m+1, h); 
		}
	}
	
	public static int rbSearch(int[] v, int l, int h, int x) { 
		int m;

		if ( l > h )
			return -1; 
		
		else { 
			m = (l+h)/2; 
			
			if ( x == v[m] )
				return m; 
			
			if ( x < v[m] )
				return rbSearch(v, l, m-1, x); 
		
			else return rbSearch(v, m+1, h, x);
		}
	}
	
	public static void main(String[] args) {
		
		int s = 1, ret;
		Scanner input = new Scanner(System.in);
		
		int[] val = { 12, 34, 45, 56, 67, 78, 89, 90, 945, 342, 845, 188, 921, 999, 1, 
					 34, 666, 812, 246, 75, 96, 94, 133, 40, 11, 555, 231, 699, 777, 7 } ;
		
		quickSort(val, 0, 29);
		
		while (s != 0) {
			System.out.println("Inserisci un valore da ricercare nell\'array ~ ~ 0 per uscire");
			s = input.nextInt();
			
			ret = rbSearch(val, 0, 29, s);
			
			if (ret != -1)
				System.out.println("L\'elemento e\' presente nell\'array in posizione: " + ret);
			else
				System.out.println("L\'elemento NON e\' presente nell\'array!");
		}
		input.close();
	}
}