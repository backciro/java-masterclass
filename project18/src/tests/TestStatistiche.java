package tests;
import java.util.*;

import ordini.*;
import junit.framework.TestCase;
import org.junit.*;

public class TestStatistiche extends TestCase {

	Ordini ordini = new Ordini();
	@Before
	public void setUp() throws Exception {
		try{
			ordini.readAnagrafica("anagrafica.txt");
		}catch(Exception e){
			fail("Non devono essere lanciate eccezioni");
		}
	}
	
	public void testStatisticaProdotti() {
		try{
			ordini.newOrdineCliente("Bianchi", "libreria",1);
			ordini.newOrdineCliente("Bianchi", "divano2pT",1);
			ordini.newOrdineCliente("Bianchi", "poltronaT",2);
			ArrayList<InfoI> list = ordini.statisticaProdotti();
			//System.out.println("statistica Prodotti");
			//for (InfoI info:ordini.statisticaProdotti()) System.out.println("   " + info.getCodice() + ": " + info.getValore());
			assertEquals(list.size(),3);
			assertEquals(list.get(0).getCodice(), "poltronaT"); assertEquals(list.get(0).getValore(), 2); 
			assertEquals(list.get(1).getCodice(), "divano2pT"); assertEquals(list.get(1).getValore(), 1); 
			assertEquals(list.get(2).getCodice(), "libreria"); assertEquals(list.get(2).getValore(), 1); 
		}catch(Exception e){
			fail("Non devono essere lanciate eccezioni");
		}
	}

	public void testStatisticaClienti() {
		try{
			ordini.newOrdineCliente("Verdi", "libreria",1); // importo 300
			ordini.newOrdineCliente("Bianchi", "divano2pT",1); // importo 200
			ordini.newOrdineCliente("Rossi", "poltronaT",2); // importo 300
			ArrayList<InfoI> list = ordini.statisticaClienti();
			//System.out.println("statistica Clienti");
			//for (InfoI info:ordini.statisticaClienti()) System.out.println("   " + info.getCodice() + ": " + info.getValore());
			assertEquals(list.size(),3);
			assertEquals(list.get(0).getCodice(), "Rossi"); assertEquals(list.get(0).getValore(), 300); 
			assertEquals(list.get(1).getCodice(), "Verdi"); assertEquals(list.get(1).getValore(), 300); 
			assertEquals(list.get(2).getCodice(), "Bianchi"); assertEquals(list.get(2).getValore(), 200); 
		}catch(Exception e){
			fail("Non devono essere lanciate eccezioni");
		}
	}
	
	public void testStatisticaFornitori() {
		try{
			String oc1 = ordini.newOrdineCliente("Verdi", "libreria",1); // importo 300
			String oc2 = ordini.newOrdineCliente("Bianchi", "divano2pP",1); // importo 400
			String oc3 = ordini.newOrdineCliente("Rossi", "poltronaT",2); // importo 300
			String oc4 = ordini.newOrdineCliente("Rossi", "divano2pP",1); // importo 400
			ordini.newOrdineFornitore("AlfaMobili", oc1);
			ordini.newOrdineFornitore("BetaMobili", oc2);
			ordini.newOrdineFornitore("TuttoMobili", oc3);
			ordini.newOrdineFornitore("BetaMobili", oc4);
			ArrayList<InfoI> list = ordini.statisticaFornitori();
			System.out.println("statistica Fornitori");
			for (InfoI info : ordini.statisticaFornitori()) System.out.println("   " + info.getCodice() + ": " + info.getValore());
			assertEquals(list.size(),3);
			assertEquals(list.get(0).getCodice(), "BetaMobili"); assertEquals(list.get(0).getValore(), 800); 
			assertEquals(list.get(1).getCodice(), "AlfaMobili"); assertEquals(list.get(1).getValore(), 300); 
			assertEquals(list.get(2).getCodice(), "TuttoMobili"); assertEquals(list.get(2).getValore(), 300); 
		}catch(Exception e){
			fail("Non devono essere lanciate eccezioni");
		}
	}
}
