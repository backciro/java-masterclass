import hydraulic.Elemento;
import hydraulic.Sistema;
import hydraulic.Sorgente;
import junit.framework.TestCase;


public class TestR1_Elementi extends TestCase {

	public void testGetElementi(){
		Sistema s = new Sistema();
		Elemento el1 = new Sorgente("Prova");		
		Elemento el2 = new Sorgente("Prova1");		
		s.aggiungiElemento(el1);
		s.aggiungiElemento(el2);
		
		Elemento[] elementi = s.getElementi();
		
		assertEquals(null,elementi[2]);
		
		assertTrue(el1==elementi[0]
					|| el2==elementi[1]);
	}
}
