package it.polito.po.test;
import dieta.*;
import junit.framework.TestCase;


public class TestR3_Ricette extends TestCase {

  public TestR3_Ricette(String arg0) {
    super(arg0);
  }

  public void testCreaRicetta() {
  	Alimenti dieta = new Alimenti();

  	/*Ricetta r = */ new Ricetta("Pasta col Mais",dieta);
  	
	assertTrue(dieta.getRicetta("Pasta col Mais")!=null);
	assertEquals(1,dieta.ricette().size());
  }

  public void testRicetta() {
	Alimenti dieta = new Alimenti();
	dieta.definisciMateriaPrima("Zucchero", 400, 0, 100, 0);
	dieta.definisciMateriaPrima("Mais", 70, 2.7, 12, 1.3);
	dieta.definisciMateriaPrima("Pasta", 350, 12, 72.2, 1.5);
	dieta.definisciMateriaPrima("Olio", 900, 0, 0, 100);
	dieta.definisciMateriaPrima("Nutella", 530, 6.8, 56, 31);

	Ricetta r = new Ricetta("Pasta e Nutella",dieta);
  	
	r.aggiungiIngrediente("Pasta",70);
	r.aggiungiIngrediente("Nutella",30);
	assertEquals(350 *0.7 + 530 *0.3, r.getCalorie(), 0.001);
	assertEquals(12 *0.7 + 6.8 *0.3 , r.getProteine(), 0.001);
	assertEquals(72.2 *0.7 + 56 *0.3, r.getCarboidrati(), 0.001);
	assertEquals(1.5 *0.7 + 31 *0.3 , r.getGrassi(), 0.001);
	assertTrue(r.per100G());
  }
	
  public void testRicetta2() {
	Alimenti dieta = new Alimenti();
	dieta.definisciMateriaPrima("Zucchero", 400, 0, 100, 0);
	dieta.definisciMateriaPrima("Mais", 70, 2.7, 12, 1.3);
	dieta.definisciMateriaPrima("Pasta", 350, 12, 72.2, 1.5);
	dieta.definisciMateriaPrima("Olio", 900, 0, 0, 100);

	Ricetta r = new Ricetta("Pasta col Mais",dieta);
  	
	r.aggiungiIngrediente("Pasta",70);
	r.aggiungiIngrediente("Mais",70);
	r.aggiungiIngrediente("Olio",13);
	
	assertEquals((350 *0.7 + 70 *0.7 + 900 *0.13)*100/153, r.getCalorie(), 0.001);
	assertEquals((12 *0.7 + 2.7 *0.7 + 0 *0.13)*100/153, r.getProteine(), 0.001);
	assertEquals((72.2 *0.7 + 12 *0.7 + 0 *0.13)*100/153, r.getCarboidrati(), 0.001);
	assertEquals((1.5 *0.7 + 1.3 *0.7 + 100 *0.13)*100/153, r.getGrassi(), 0.001);
  }

}
