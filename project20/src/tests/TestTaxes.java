package tests;

import grocerystore.GroceryCounter;
import grocerystore.NoSuchProduct;
import junit.framework.TestCase;

public class TestTaxes extends TestCase {

	
	private GroceryCounter gc;
	
	public void setUp(){
		gc = new GroceryCounter();
		gc.addProduct("P001","Coffee",3.0);
		gc.addProduct("P002","Tuna",1.5);
		gc.addProduct("P003","Olive oil",3.0);
	}

	public void testGross() throws NoSuchProduct{
		gc.read("P001",2);
		gc.read("P002",3);
		
		double expectedTotal = 3.0*2+1.5*3;
		double expectedGross = expectedTotal * 1.2;
		assertEquals(expectedGross,gc.gross(),0.001);
	}

	public void testTax() throws NoSuchProduct{
		gc.read("P001",2);
		gc.read("P002",3);
		
		double expectedTotal = 3.0*2+1.5*3;
		double expectedTax = expectedTotal * 0.2;
		assertEquals(expectedTax,gc.taxes(),0.001);
	}

}
