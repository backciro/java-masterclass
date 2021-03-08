package ateneo;

import java.util.*;

public class Insegnamento {
	
	String nome; String docente; int id;
	
	ArrayList<Studente> studenti_iscritti = new ArrayList<Studente>();
//	Studente[] frequenters = new Studente[100];
	
	private static int addCourse = 10;
	public int stud_is = 0;
	
	public Insegnamento() {
		this.nome = "" ;
		this.docente = "" ;	
		this.id = -1 ;
	}
	
	public Insegnamento(String idCorso, String nomeDoc) {
		this.nome = idCorso;
		this.docente = nomeDoc;	
		this.id = Insegnamento.addCourse++ ;
	}

	public void aggiungiStudente(Studente s) {
		if (this.stud_is <= 100) {
			this.studenti_iscritti.add(s);
			this.stud_is++;
		}
		else 
			System.out.println("LIMITE STUDENTI / CORSO RAGGIUNTO");
	}
}