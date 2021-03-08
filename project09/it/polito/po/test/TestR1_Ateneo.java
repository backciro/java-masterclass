package it.polito.po.test;

import ateneo.Ateneo;
import junit.framework.TestCase;

public class TestR1_Ateneo extends TestCase {

	
	public void testAteneo(){
		
		String nome = "Politecnico di Torino";
		Ateneo a = new Ateneo(nome);
		
		
		assertEquals(nome,a.getNome());	
	}
	
	public void testRettore(){

		String nome = "Politecnico di Torino";
		String nomeRettore = "Francesco";
		String cognomeRettore = "Profumo";
		Ateneo a = new Ateneo(nome);
		
		a.setRettore(nomeRettore, cognomeRettore);
		
		String rettore = a.getRettore();
		
		assertNotNull("Non viene restituito alcun rettore.",
					rettore);
		assertTrue("Manca il nome del rettore",
					rettore.indexOf(nomeRettore)!=-1);
		assertTrue("Manca il cognome del rettore",
				rettore.indexOf(cognomeRettore)!=-1);
	}
}
