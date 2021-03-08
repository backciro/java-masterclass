package tests;

import grocerystore.GroceryCounter;
import grocerystore.NoSuchProduct;
import junit.framework.TestCase;

public class TestTotal extends TestCase {

	private GroceryCounter gc;
	
	public void setUp(){
		gc = new GroceryCounter();
		gc.addProduct("P001","Coffee",3.0);
		gc.addProduct("P002","Tuna",1.5);
		gc.addProduct("P003","Olive oil",3.0);
	}
	
	public void testTotalOneProduct() throws NoSuchProduct{
		assertEquals(0.0,gc.total(),0.001);
		gc.read("P001",1);
		assertEquals(3.0,gc.total(),0.001);
	}
	
	public void testTotalTwoProduct() throws NoSuchProduct{
		gc.read("P001",1);
		gc.read("P002",1);
		assertEquals(3.0+1.5,gc.total(),0.001);
	}

	public void testTotalTwoProductQuantities() throws NoSuchProduct{
		gc.read("P001",2);
		gc.read("P002",3);
		assertEquals(3.0*2+1.5*3,gc.total(),0.001);
	}
	
	public void testClose() throws NoSuchProduct{
		gc.read("P001",2);
		gc.read("P002",3);
		assertTrue(gc.total()!=0.0);
		gc.close();
		assertEquals(gc.total(),0.0,0.001);
	}

}
