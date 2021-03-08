import hydraulic.Elemento;
import hydraulic.Scarico;
import hydraulic.Sistema;
import hydraulic.Sorgente;
import hydraulic.Split;
import junit.framework.TestCase;


public class TestR3_ElementiComplessi extends TestCase {

	public void testSplit(){
		Sistema s = new Sistema();
		Elemento src = new Sorgente("Src");		
		Split t = new Split("T");	
		Elemento sink1 = new Scarico("Sink 1");		
		Elemento sink2 = new Scarico("Sink 2");		
		s.aggiungiElemento(src);
		s.aggiungiElemento(t);
		s.aggiungiElemento(sink1);
		s.aggiungiElemento(sink2);
		
		src.connetti(t);
		t.connetti(sink1,0);
		t.connetti(sink2,1);
		
		Elemento[] u = t.getUscite();
		
		assertSame(sink1,u[0]);
		assertSame(sink2,u[1]);
	}


}
