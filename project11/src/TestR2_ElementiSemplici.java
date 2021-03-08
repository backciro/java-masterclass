import hydraulic.Elemento;
import hydraulic.Rubinetto;
import hydraulic.Scarico;
import hydraulic.Sistema;
import hydraulic.Sorgente;
import junit.framework.TestCase;


public class TestR2_ElementiSemplici extends TestCase {

	public void testNomeElemento(){
		String nome="Prova";
		Elemento el = new Sorgente(nome);
		
		assertEquals(nome,el.getNome());
	}

	public void testElementi(){
		Sistema s = new Sistema();
		Elemento src = new Sorgente("Src");		
		Elemento tap = new Rubinetto("Tap");		
		Elemento sink = new Scarico("Sink");		
		s.aggiungiElemento(src);
		s.aggiungiElemento(tap);
		s.aggiungiElemento(sink);
		
		src.connetti(tap);
		tap.connetti(sink);
		
		assertSame(tap,src.getUscita());
		assertSame(sink,tap.getUscita());
	}
}
