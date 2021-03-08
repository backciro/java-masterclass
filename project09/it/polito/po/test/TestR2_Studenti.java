package it.polito.po.test;

import ateneo.Ateneo;
import junit.framework.TestCase;

public class TestR2_Studenti extends TestCase {

	private static final int MATRICOLA_INIZIALE = 10000;
	private static final int MAX_STUDENTI = 1000;
	String nome = "Politecnico di Torino";
	Ateneo a;
	
	public void setUp(){
		a = new Ateneo(nome);
	}

	public void testNuovoStudente(){
		String nome = "Mario";
		String cognome = "Rossi";
		
		int matricola = a.immatricola(nome, cognome);
		
		assertTrue("Le matricole dovrebbero partire da 10000",
					matricola>=MATRICOLA_INIZIALE);
		
	}

	public void testPiuStudenti(){
		String nome = "Mario";
		String cognome = "Rossi";
		
		int precMatricola=MATRICOLA_INIZIALE-1;
		for(int i=0; i<MAX_STUDENTI; ++i){
			int matricola = a.immatricola(nome+i, cognome+i);
			assertTrue("Le matricole devono essere assegnate progressivamente",
						matricola>precMatricola);
			precMatricola = matricola;
		}		
	}

	public void testInfoStudente(){
		String nome = "Mario";
		String cognome = "Rossi";
		
		int matricola = a.immatricola(nome, cognome);
		
		String studente = a.studente(matricola);
		
		assertNotNull("Non viene trovato alcuno studente",
					studente);
		
		System.out.println(studente);
		
		assertTrue("Manca la matricola",
				studente.indexOf(""+matricola) != -1);
		assertTrue("Manca il nome",
				studente.indexOf(nome) != -1);
		assertTrue("Manca il congome",
				studente.indexOf(cognome) != -1);
		
	}
	
	public void testInfoPiuStudenti(){
		String nome = "Mario";
		String cognome = "Rossi";
		
		int[] matricola= new int[MAX_STUDENTI];
		for(int i=0; i<MAX_STUDENTI; ++i){
			matricola[i] = a.immatricola(nome+i, cognome+i);
		}
		
		String primo = a.studente(matricola[0]);
		String mezzo = a.studente(matricola[MAX_STUDENTI/2]);
		String ultimo = a.studente(matricola[MAX_STUDENTI-1]);
		
		assertNotNull("Impossibile recuperare il primo studente iscritto",
						primo);
		assertTrue("Manca il nome del primo studente",
					primo.indexOf(nome+0) != -1);

		assertNotNull("Impossibile recuperare uno studente iscritto",
				mezzo);
		assertTrue("Manca il cognome di uno studente",
				mezzo.indexOf(nome+(MAX_STUDENTI/2)) != -1);

		assertNotNull("Impossibile recuperare l'ultimo studente iscritto",
				ultimo);
		assertTrue("Manca la matricola dell'ultimo studente",
				ultimo.indexOf(nome+(MAX_STUDENTI-1)) != -1);
	}

}
