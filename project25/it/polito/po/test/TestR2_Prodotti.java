package it.polito.po.test;
import java.util.Collection;
import java.util.Iterator;

import dieta.*;
import junit.framework.TestCase;


public class TestR2_Prodotti extends TestCase {

  public TestR2_Prodotti(String arg0) {
    super(arg0);
  }

  public void testDefinizione(){
  	Alimenti dieta = new Alimenti();
  	
  	int initialSize = dieta.prodotti().size();
  	dieta.definisciProdotto("Cracker", 111, 2.6, 17.2, 3.5);
	int finalSize = dieta.prodotti().size();
	
	assertEquals(0,initialSize);
	assertEquals(1,finalSize);
  }
  
  public void testCollezioneProdotti(){
	Alimenti dieta = new Alimenti();
  	
	dieta.definisciProdotto("Cracker", 111, 2.6, 17.2, 3.5);
	
	Collection<ElementoNutritivo> c = dieta.prodotti();
	
	ElementoNutritivo en = (ElementoNutritivo)c.iterator().next();
	
	assertEquals("Cracker",en.getNome());
	assertEquals(111,en.getCalorie(),0.001); 
	assertEquals(2.6,en.getProteine(),0.001); 
	assertEquals(17.2,en.getCarboidrati(),0.001); 
	assertEquals(3.5,en.getGrassi(),0.001); 
	assertFalse(en.per100G());
  }

  public void testProdotto(){
	Alimenti dieta = new Alimenti();
	dieta.definisciProdotto("Cracker", 111, 2.6, 17.2, 3.5);
	ElementoNutritivo en = dieta.getProdotto("Cracker");
	
	assertEquals("Cracker",en.getNome());
	assertEquals(111,en.getCalorie(),0.001); 
	assertEquals(2.6,en.getProteine(),0.001); 
	assertEquals(17.2,en.getCarboidrati(),0.001); 
	assertEquals(3.5,en.getGrassi(),0.001); 
	assertFalse(en.per100G());
  }
  
  public void testCollezioneProdottiOrdine(){
	Alimenti dieta = new Alimenti();
  	
	dieta.definisciProdotto("Zucchero", 400, 0, 100, 0);
	dieta.definisciProdotto("Mais", 70, 2.7, 12, 1.3);
	dieta.definisciProdotto("Pasta", 350, 12, 72.2, 1.5);
	
	Collection<ElementoNutritivo> c = dieta.prodotti();
	Iterator<ElementoNutritivo> it = c.iterator();
	ElementoNutritivo first = (ElementoNutritivo)it.next();
	ElementoNutritivo second = (ElementoNutritivo)it.next();
	ElementoNutritivo third = (ElementoNutritivo)it.next();
	
	assertEquals("Mais",first.getNome());
	assertEquals("Pasta",second.getNome());
	assertEquals("Zucchero",third.getNome());
  }

}
