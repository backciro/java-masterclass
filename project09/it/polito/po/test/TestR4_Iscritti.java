package it.polito.po.test;

import ateneo.Ateneo;
import junit.framework.TestCase;

public class TestR4_Iscritti extends TestCase {

//	private static final int MATRICOLA_INIZIALE = 10000;
//	private static final int MAX_STUDENTI = 1000;
//	private static final int CODICE_INIZIALE = 10;
//	private static final int MAX_CORSI = 50;
	Ateneo a;
	int matricola;
	int codice;
	
	public void setUp(){
		a = new Ateneo("Politecnico di Torino");
		String nome = "Mario";
		String cognome = "Rossi";
		
		matricola = a.immatricola(nome, cognome);

		String titolo = "Ingegneria del Software";
		String titolare = "Kent Beck";
		
		codice = a.attiva(titolo, titolare);
	}

	public void testIscritti(){
		
		a.iscrivi(matricola, codice);
		
		String iscritti = a.elencoIscritti(codice);
		
		assertNotNull("Manca l'elenco degli iscritti",iscritti);
		
		assertTrue("Manca lo studente iscritto al corso",
					iscritti.contains(""+matricola));
	}

	public void testPiano(){
		
		a.iscrivi(matricola, codice);
		
		String piano = a.pianoStudi(matricola);
		
		assertNotNull("Manca il piano di studi", piano);
		
		assertTrue("Manca il corso a cui lo studente � iscritto",
					piano.contains(""+codice));
	}
}
