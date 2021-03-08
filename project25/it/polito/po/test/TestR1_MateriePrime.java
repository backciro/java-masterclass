package it.polito.po.test;
import java.util.Collection;
import java.util.Iterator;

import dieta.*;
import junit.framework.TestCase;


public class TestR1_MateriePrime extends TestCase {

  public TestR1_MateriePrime(String arg0) {
    super(arg0);
  }

  public void testDefinizione(){
  	Alimenti dieta = new Alimenti();
  	
  	int initialSize = dieta.materiePrime().size();
  	dieta.definisciMateriaPrima("Pasta", 350, 12, 72.2, 1.5);
	int finalSize = dieta.materiePrime().size();
	
	assertEquals(0,initialSize);
	assertEquals(1,finalSize);
  }
  
  public void testCollezioneMateriePrime(){
	Alimenti dieta = new Alimenti();
  	
	dieta.definisciMateriaPrima("Pasta", 350, 12, 72.2, 1.5);
	
	Collection<ElementoNutritivo> c = dieta.materiePrime();
	
	ElementoNutritivo en = (ElementoNutritivo)c.iterator().next();
	
	assertEquals("Pasta",en.getNome());
	assertEquals(350,en.getCalorie(),0.001); 
	assertEquals(12,en.getProteine(),0.001); 
	assertEquals(72.2,en.getCarboidrati(),0.001); 
	assertEquals(1.5,en.getGrassi(),0.001); 
	assertTrue(en.per100G());
  }

  public void testMateriaPrima(){
	Alimenti dieta = new Alimenti();
	dieta.definisciMateriaPrima("Pasta", 350, 12, 72.2, 1.5);
	ElementoNutritivo en = dieta.getMateriaPrima("Pasta");
	
	assertEquals("Pasta",en.getNome());
	assertEquals(350,en.getCalorie(),0.001); 
	assertEquals(12,en.getProteine(),0.001); 
	assertEquals(72.2,en.getCarboidrati(),0.001); 
	assertEquals(1.5,en.getGrassi(),0.001); 
	assertTrue(en.per100G());
  }
  
  public void testCollezioneMateriePrimeOrdine(){
	Alimenti dieta = new Alimenti();
  	
	dieta.definisciMateriaPrima("Zucchero", 400, 0, 100, 0);
	dieta.definisciMateriaPrima("Mais", 70, 2.7, 12, 1.3);
	dieta.definisciMateriaPrima("Pasta", 350, 12, 72.2, 1.5);
	
	Collection<ElementoNutritivo> c = dieta.materiePrime();
	Iterator<ElementoNutritivo> it = c.iterator();
	ElementoNutritivo first = it.next();
	ElementoNutritivo second = it.next();
	ElementoNutritivo third = it.next();
	
	assertEquals("Mais",first.getNome());
	assertEquals("Pasta",second.getNome());
	assertEquals("Zucchero",third.getNome());
  }

}
