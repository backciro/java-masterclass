package it.polito.po.test;

import ateneo.Ateneo;
import junit.framework.TestCase;

public class TestR3_Insegnamenti extends TestCase {
//	private static final int MATRICOLA_INIZIALE = 10000;
//	private static final int MAX_STUDENTI = 1000;
	private static final int CODICE_INIZIALE = 10;
	private static final int MAX_CORSI = 50;
	String nome = "Politecnico di Torino";
	Ateneo a;
	
	public void setUp(){
		a = new Ateneo(nome);
		
	}

	public void testNuovoInsegnamento(){
		String titolo = "Ingegneria del Software";
		String titolare = "James Gosling";
		
		int codice = a.attiva(titolo, titolare);
		
		assertTrue("Codice corso non valido",
					codice >= CODICE_INIZIALE);
	}

	public void testPiuInsegnamenti(){
		String titolo = "Corso ";
		String titolare = "Docente ";
		
		int precedenteCodice = CODICE_INIZIALE - 1;
		for(int i=0; i<MAX_CORSI; ++i){
			int codice = a.attiva(titolo+i, titolare+i);
			assertTrue( precedenteCodice < codice);
			precedenteCodice = codice;
		}
				
	}
	
	public void testInfoInsegnamento(){
		String titolo = "Ingegneria del Software";
		String titolare = "James Gosling";
		
		int codice = a.attiva(titolo, titolare);
		
		String ins = a.insegnamento(codice);
		
		assertNotNull("Nessun insegnamento restituito", ins);
		
		assertTrue("Manca il titolo dell'insegnamento",
				ins.indexOf(""+codice) != -1);
		assertTrue("Manca il titolo dell'insegnamento",
					ins.indexOf(titolo) != -1);
		assertTrue("Manca il titolare dell'insegnamento",
				ins.indexOf(titolare) != -1);
	}


}
