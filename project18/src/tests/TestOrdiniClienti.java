package tests;
import java.util.*;

import ordini.*;
import junit.framework.TestCase;
import org.junit.*;

public class TestOrdiniClienti extends TestCase{
	Ordini ordini = new Ordini();
	@Before
	public void setUp() throws Exception {
		try{
			ordini.readAnagrafica("anagrafica.txt");
		}catch(Exception e){
			fail("Non devono essere lanciate eccezioni");
		}
	}

	public void testNewOrdineCliente() {
		String cliente = "Bianchi"; String prodotto = "divano2pT"; int n = 2;
		try {
			String o = ordini.newOrdineCliente(cliente, prodotto, n);
			assertEquals(o,"oc1");
			OrdineClienteI ordine = ordini.getOrdineCliente(o);
			assertEquals(ordine.getCodiceCliente(),cliente);
			assertEquals(ordine.getCodiceProdotto(),prodotto);
			assertEquals(ordine.getImporto(),400);
			assertEquals(ordine.getStato(),"inserito");
		}catch(CodiceInesistente e){
			fail("Non devono essere lanciate eccezioni");
		}		
	}
	public void testNewOrdineClienteE() { // prodotto inesistente
		String cliente = "Bianchi"; String prodotto = "sedia"; int n = 1;
		try {
			ordini.newOrdineCliente(cliente, prodotto ,n);
			fail("deve lanciare l'eccezione codice inesistente");
		}catch(CodiceInesistente e){
			//ok
		}	
	}
	
	public void testNewOrdineClienteE2() { // cliente inesistente
		String cliente = "Neri"; String prodotto = "sedia"; int n = 1;
		try {
			ordini.newOrdineCliente(cliente, prodotto ,n);
			fail("deve lanciare l'eccezione codice inesistente");
		}catch(CodiceInesistente e){
			//ok
		}	
	}
	
	public void testOrdiniCliente() {
		try {
			ordini.newOrdineCliente("Verdi", "divano2pT",1);
			ordini.newOrdineCliente("Verdi", "poltronaT",2);
			ArrayList<OrdineClienteI> ordiniC = ordini.getOrdiniCliente("Verdi");
			// l'ordine dipende dall'importo quindi prima il secondo inserito
			assertEquals(ordiniC.size(),2);
			assertEquals(ordiniC.get(0).getCodiceOrdine(),"oc2");
			assertEquals(ordiniC.get(1).getCodiceOrdine(),"oc1");
		}catch(CodiceInesistente e){
			fail("Non devono essere lanciate eccezioni");
		}	
	}
	
	public void testOrdiniClienteE() {
		try {
			ordini.newOrdineCliente("Verdi", "divano2pT",1);
			ordini.newOrdineCliente("Verdi", "poltronaT",2);
			ordini.getOrdiniCliente("Neri");
			fail("deve lanciare l'eccezione codice inesistente");
		}catch(CodiceInesistente e){
			//ok
		}	
	}
	
	public void testGetOrdineClienteE() {
		try {
			ordini.newOrdineCliente("Bianchi", "divano2pT",1);
			ordini.newOrdineCliente("Bianchi", "poltronaT",2);
			ordini.getOrdineCliente("oc3"); // oc3 non esiste
			fail("deve lanciare l'eccezione codice inesistente");
			} catch(CodiceInesistente e){
				//ok
			}	
	}

}
