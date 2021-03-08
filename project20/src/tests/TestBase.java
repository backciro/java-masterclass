package tests;

import grocerystore.GroceryCounter;
import grocerystore.NoSuchProduct;
import junit.framework.TestCase;

public class TestBase extends TestCase {

	private static final String CODE = "C1";

	public void testReadEmptyProduct() {
		GroceryCounter gc = new GroceryCounter();
		
		try {
			gc.read(CODE,1);
			fail("Product with code " + CODE + " is no present");
		} catch (NoSuchProduct e) {
			assertTrue(true); //Ok
		}
	}
	public void testReadWrongProduct() {
		GroceryCounter gc = new GroceryCounter();
		
		gc.addProduct(CODE,"Description",1.1);
		try {
			gc.read(CODE+"x",1);
			fail("Product with code " + CODE + "x is no present");
		} catch (NoSuchProduct e) {
			assertTrue(true); //Ok
		}
	}
}
