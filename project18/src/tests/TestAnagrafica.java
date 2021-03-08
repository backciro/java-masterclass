package tests;
import java.util.*;
import ordini.*;
import junit.framework.TestCase;
import org.junit.*;

public class TestAnagrafica extends TestCase {
	Ordini ordini = new Ordini();
	@Before
	public void setUp() throws Exception {
		try{
			ordini.readAnagrafica("anagrafica.txt");
		}catch(Exception e){
			fail("Non devono essere lanciate eccezioni");
		}
		//System.out.println(ordini.getCodiciProdotti());
		//System.out.println(ordini.getCodiciClienti());
		//System.out.println(ordini.getCodiciFornitori());
	}

	public void testGetCodiciProdotti() {
		ArrayList<String> codiciProdotti = ordini.getCodiciProdotti();
		assertEquals(codiciProdotti.size(),7);
		assertEquals(codiciProdotti.get(0),"divano2pP");
		assertEquals(codiciProdotti.get(6),"scaffale");
	}
	public void testGetCodiciClienti() {
		ArrayList<String> codiciClienti = ordini.getCodiciClienti();
		assertEquals(codiciClienti.size(),3);
		assertEquals(codiciClienti.get(0),"Bianchi");
		assertEquals(codiciClienti.get(2),"Verdi");
	}
	public void testGetCodiciFornitori() {
		ArrayList<String> codiciFornitori = ordini.getCodiciFornitori();
		assertEquals(codiciFornitori.size(),3);
		assertEquals(codiciFornitori.get(0),"AlfaMobili");
		assertEquals(codiciFornitori.get(2),"TuttoMobili");
	}
	public void testAnagraficaErrata() {
		try{
			Ordini ordiniE = new Ordini();
			ordiniE.readAnagrafica("anagraficaE.txt");
			ArrayList<String> codiciFornitori = ordiniE.getCodiciFornitori();
			assertEquals(codiciFornitori.size(),2);
			assertEquals(codiciFornitori.get(0),"AlfaMobili");
			assertEquals(codiciFornitori.get(1),"BetaMobili");
		}catch(Exception e){
			fail("Non devono essere lanciate eccezioni" + e.getMessage());
		}
	}
	public void testGetCodiciProdottiFornitore() {
		try{
			ArrayList<String> codiciProdotti = ordini.getCodiciProdottiFornitore("AlfaMobili");
			assertEquals(codiciProdotti.size(),3);
			assertEquals(codiciProdotti.get(0),"divano2pT");
			assertEquals(codiciProdotti.get(2),"poltronaT");
		}catch(Exception e){
			fail("Non devono essere lanciate eccezioni" + e.getMessage());
		}
	}
	
}
