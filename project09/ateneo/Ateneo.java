package ateneo;

import java.util.*;

public class Ateneo {
	
	String id, Rett_Name, Rett_Surname;

	ArrayList<Studente> cStudents = new ArrayList<Studente>();
	ArrayList<Insegnamento> sCourses = new ArrayList<Insegnamento>();
	
	public Ateneo(String nome) {
		this.id = nome;
	}
	
	public String getNome() {
		return this.id;
	}
	
	public void setRettore(String nome, String cognome) {
		this.Rett_Name = nome; 
		this.Rett_Surname = cognome;
	}
	
	public String getRettore(){
		return String.format("%s %s", this.Rett_Name, this.Rett_Surname);
	}
	
	public int immatricola(String nome, String cognome){
		Studente s = new Studente(nome, cognome);
		
		if (this.cStudents.size() <= 1000) {
			this.cStudents.add(s);
			return s.matricola;
		}
		
		else {
			System.out.println("LIMITE STUDENTI / ATENEO RAGGIUNTO");
			return -1;
		}
	}
	
	public String studente(int matricola){

		for (Studente x : this.cStudents)
			if ( x.matricola ==  matricola )
				return String.format("%s %s s%d", x.name, x.surname, x.matricola);
				
		return "STUDENT NOT FOUND";
	}
	
	public int attiva(String titolo, String titolare) {
		Insegnamento c = new Insegnamento(titolo, titolare);
		
		if (this.sCourses.size() <= 50) {
			this.sCourses.add(c);
			return c.id; 
		}
		
		else {
			System.out.println("LIMITE CORSI / ATENEO RAGGIUNTO");
			return -1;
		}
	}
	
	public String insegnamento(int codice) {
		
		for (Insegnamento x : this.sCourses)
			if ( x.id == codice)
				return String.format("c%d - '%s'   %s", x.id, x.nome, x.docente);
		
		return "COURSE NOT FOUND";
	}
	
	public void iscrivi(int matricolaStudente, int codiceInsegnamento) 
	{
		Insegnamento course = new Insegnamento();
		Studente student = new Studente();
		
		for (Insegnamento x : this.sCourses)
			if (x.id == codiceInsegnamento)
				course = x;
		
		for (Studente x : this.cStudents)
			if (x.matricola == matricolaStudente)
				student = x;
		
		course.aggiungiStudente(student);
		student.aggiungiCorso(course);
	}

	public String elencoIscritti(int codiceInsegnamento) {
		Insegnamento course = new Insegnamento();
		String str = "";
		
		for (Insegnamento x : this.sCourses)
			if (x.id == codiceInsegnamento)
				course = x;
		
		for (Studente x : course.studenti_iscritti)
			str += "s"+ x.matricola + " - " + x.name + " " + x.surname + "\n";
		
		return str;
	}

	public String pianoStudi(int matricolaStudente) {
		Studente student = new Studente();
		String str = "";
		
		for (Studente x : this.cStudents)
			if (x.matricola == matricolaStudente)
				student = x;
		
		for (Insegnamento x : student.pianoCarriera)
			str += "c" + x.id + " - " + x.nome + " " + x.docente + "\n";
		
		return str;
	}
}
