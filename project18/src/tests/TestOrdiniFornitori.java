package tests;
import java.util.*;

import ordini.*;
import junit.framework.TestCase;
import org.junit.*;

public class TestOrdiniFornitori extends TestCase{
	Ordini ordini = new Ordini();
	@Before
	public void setUp() throws Exception {
		try{
			ordini.readAnagrafica("anagrafica.txt");
		}catch(Exception e){
			fail("Non devono essere lanciate eccezioni");
		}
	}

	public void testNewOrdineFornitore() {
		try {
			String oc1 = ordini.newOrdineCliente("Bianchi", "divano2pT",1);
			ordini.newOrdineCliente("Bianchi", "poltronaT",2);
			String oc3 = ordini.newOrdineCliente("Rossi", "libreria",2);
			String o = ordini.newOrdineFornitore("AlfaMobili",oc3,oc1);
			assertEquals(o,"of1");
			OrdineFornitoreI ordine = ordini.getOrdineFornitore(o);
			assertEquals(ordine.getCodiceFornitore(),"AlfaMobili");
			assertEquals(ordine.getImporto(),800);
			assertEquals(ordine.getStato(),"inserito");
			ArrayList<String> codiciOrdiniCliente = ordine.getCodiciOrdiniCliente();
			assertEquals(codiciOrdiniCliente.size(),2);
			assertEquals(codiciOrdiniCliente.get(0),"oc1");
			assertEquals(codiciOrdiniCliente.get(1),"oc3");
			// occorre controllare lo stato = trattato
			OrdineClienteI ordineC = ordini.getOrdineCliente(oc1);
			assertEquals(ordineC.getStato(),"trattato");
			ordineC = ordini.getOrdineCliente(oc3);
			assertEquals(ordineC.getStato(),"trattato");
			} catch(CodiceInesistente e){
				fail("Non devono essere lanciate eccezioni");
			}catch(OrdineInaccettabile e){
				fail("Non devono essere lanciate eccezioni");
		}		
	}
	
// errori se gli ordini cliente non sono nello stato inserito e se i prodotti non sono forniti dal fornitore
	
	public void testNewOrdineFornitoreE1() {
		try {
			String oc1 = ordini.newOrdineCliente("Bianchi", "divano2pT",1);
			ordini.newOrdineFornitore("AlfaMobili",oc1);
			ordini.newOrdineFornitore("AlfaMobili",oc1);
			fail("Deve lanciare l'eccezione OrdineInaccettabile"); // lo stato di oc1 non � "inserito" ma "trattato"
		}catch(OrdineInaccettabile e){
			//ok
		}catch(Exception e) {
			fail("Non ci sono altre eccezioni");
		}
	}
	
	public void testNewOrdineFornitoreE2() {
		try {
			String oc1 = ordini.newOrdineCliente("Bianchi", "scaffale",1);
			ordini.newOrdineFornitore("AlfaMobili",oc1);
			fail("deve lanciare l'eccezione OrdineInaccettabile"); // AlfaMobili non fornisce scaffali
		}catch(OrdineInaccettabile e){
			//ok
		}catch(Exception e) {
			fail("Non ci sono altre eccezioni");
		}	
	}
	
	public void testConsegnaOrdineFornitore() {
		try {
			String oc1 = ordini.newOrdineCliente("Bianchi", "divano2pT",1);
			ordini.newOrdineCliente("Bianchi", "poltronaT",2);
			String oc3 = ordini.newOrdineCliente("Rossi", "libreria",1);
			String o = ordini.newOrdineFornitore("AlfaMobili",oc3,oc1);
			ordini.consegnaOrdineFornitore(o);
			// occorre controllare lo stato = consegnato per gli ordini cliente e l'ordine fornitore
			OrdineFornitoreI ordine = ordini.getOrdineFornitore(o);
			assertEquals(ordine.getStato(),"consegnato");
			OrdineClienteI ordineC = ordini.getOrdineCliente(oc1);
			assertEquals(ordineC.getStato(),"consegnato");
			ordineC = ordini.getOrdineCliente(oc3);
			assertEquals(ordineC.getStato(),"consegnato");
		}catch(Exception e){
			fail("Non devono essere lanciate eccezioni");
		}		
	}
	
	public void testConsegnaOrdineFornitoreE() {
		try {
			String oc1 = ordini.newOrdineCliente("Bianchi", "divano2pT",1);
			ordini.newOrdineCliente("Bianchi", "poltronaT",2);
			String oc3 = ordini.newOrdineCliente("Rossi", "libreria",1);
			ordini.newOrdineFornitore("AlfaMobili",oc3,oc1);
			ordini.consegnaOrdineFornitore("of2");
			fail("Deve lanciare l'eccezione CodiceInesistente");
		}catch(CodiceInesistente e){
			// ok;
		} catch(OrdineInaccettabile e){
			fail("non deve lanciare l'eccezione OrdineInaccettabile");
		} catch(ConsegnaInaccettabile e){
			fail("non deve lanciare l'eccezione ConsegnaInaccettabile");
		}
		
	}
	
	public void testGetOrdineFornitoreE() {
		try {
			String oc1 = ordini.newOrdineCliente("Verdi", "libreria",1); // importo 300
			String oc2 = ordini.newOrdineCliente("Bianchi", "divano2pP",1); // importo 400
			ordini.newOrdineFornitore("AlfaMobili", oc1);
			ordini.newOrdineFornitore("BetaMobili", oc2);
			ordini.getOrdineFornitore("of3"); // of3 non esiste
			fail("deve lanciare l'eccezione codice inesistente");
			} catch(CodiceInesistente e){
				//ok
			} catch(OrdineInaccettabile e){
				fail("non deve lanciare l'eccezione OrdineInaccettabile");
			}
	}
	
}
