package ateneo;

import java.util.*;

public class Studente {
	
	String name, surname;
	int matricola;

	ArrayList<Insegnamento> pianoCarriera = new ArrayList<Insegnamento>();
//	Insegnamento[] carreerPlan = new Insegnamento[25];
	
	private static int addMatricola = 10000;
	public int iscrizioni = 0;
	
	public Studente() {
		this.name = null;
		this.surname = null; 
		this.matricola = -1 ;
	}
	
	public Studente(String nome, String cognome) {
		this.name = nome;
		this.surname = cognome; 
		this.matricola = Studente.addMatricola++ ;
	}
	
	public void aggiungiCorso(Insegnamento c) {
		if (this.iscrizioni <= 25) {
			this.pianoCarriera.add(c);
			this.iscrizioni++;
		}
		else 
			System.out.println("LIMITE CORSI / PIANO CARRIERA RAGGIUNTO");
	}
}