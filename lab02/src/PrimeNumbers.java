import java.util.*;

public class PrimeNumbers {
    
    public static void main(String[] args) {
     
    	int max = 523, d, n;
    	int ind, offset;
    	
    	ArrayList<Integer> Prime = new ArrayList<Integer>();
        
        System.out.println("Numeri primi inferiori a " + max);
        Prime.add(0, 1);
        
        for (n = 2; n <= max; n++) {
          
        	d = 2;
            while ( (n % d != 0) && (d < n) )
                d++;
            
            if (d == n)
                Prime.add(n);
        }
        
       for (ind = 0; ind < Prime.size(); ++ind){
    	   for (offset = ind; offset < ind + 10; offset++){
    		   System.out.print(+ Prime.get(offset) +" ");
    	   } 
    	   System.out.println(); ind += 9;
       }
       System.out.println("Size of ArrayList: " + Prime.size());
        	
    }
    
}