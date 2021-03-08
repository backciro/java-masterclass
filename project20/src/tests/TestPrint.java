package tests;

import grocerystore.GroceryCounter;
import grocerystore.NoSuchProduct;

public class TestPrint extends StdOutTestCase {

	private GroceryCounter gc;
	
	public void setUp(){
		gc = new GroceryCounter();
		gc.addProduct("P001","Coffee",3.0);
		gc.addProduct("P002","Tuna",1.5);
		gc.addProduct("P003","Olive oil",3.0);
	}

	public void testOneProduct() throws NoSuchProduct{
		gc.read("P001",1);
		startCapture();
		gc.print();
		String output = stopCapture();
		// output must contain at least Description
		
		assertContains(output,"Coffee");
	}

	public void testThreeProducts() throws NoSuchProduct{
		gc.read("P001",1);
		gc.read("P002",1);
		gc.read("P003",1);
		startCapture();
		gc.print();
		String output = stopCapture();
		// output must contain ordered Descriptions
		
		assertContainsInOrder(output,new String[]{"Coffee","Olive oil","Tuna"});
	}

}
