import java.util.*;

public class MaxInArray {
	
	private static int maxIte(ArrayList<Integer> vett, int N) {
		int i, max = -32768;
		
		for (i = 0; i < N; i++)
			if (vett.get(i) > max)
				max = vett.get(i);
		
		return max;
	}

	private static int maxRec(ArrayList<Integer> vett, int N, int i, int max) {
		
		if (i >= N)
			return max;
		
		if (vett.get(i) > max)
			max = vett.get(i);
		max = maxRec(vett, N, i + 1, max);
		
		return max;
	}

	public static int findMax(ArrayList<Integer> vett, int N) {
		//avro qua dentro due ulteriori metodi, uno iterativo e uno ricorsivo
		int recursive, iterative;
		
		recursive = maxRec(vett, N, 0, -32768);
		iterative = maxIte(vett, N);
		
		if (recursive == iterative)
			return iterative;
		else return -32768;
		
	}	

	public static void main(String[] args) {
		
		ArrayList<Integer> vett = new ArrayList<Integer>();
		
		System.out.println("Inserire un intero N, ed N interi in successione");
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		
		for (int i = 0; i < N; i++) {
			vett.add(input.nextInt());
		} input.close();
	
		System.out.println("il massimo nel vettore e\': " + findMax(vett, N));
	}
}