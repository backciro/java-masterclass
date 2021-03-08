import grocerystore.*;

public class GroceryApplication {

	public static void main(String[] args) {
		
		GroceryCounter counter = new GroceryCounter();
		
		counter.addProduct("P001", "Coffee", 3.0);
		counter.addProduct("P002", "Tuna", 2.4);
		counter.addProduct("P003", "Oil", 2.0);
		
		counter.promo("P002", 20); // 20% discount on tuna
		
		try{			
			counter.read("P002",3);
			counter.read("P001",1);
			counter.read("P003",1);			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		counter.print();
		
		System.out.println(	counter.gross() );
		System.out.println(	counter.taxes() );
		System.out.println(	counter.total() );
		
		counter.close();		
	}
}
