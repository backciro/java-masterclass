package Library;

import java.util.*;
import java.util.stream.Collectors;

public class Utente {

	private Integer Codice;
	private String Nome, Cognome;
	
	Queue<Libro> Richieste = new LinkedList<Libro>();
	Collection<Libro> Prestiti = new ArrayList<Libro>();
	
	
	public Utente (int codice, String nome , String cognome ) {
		this.Codice = codice;
		this.Nome = nome;
		this.Cognome = cognome;
	}
		
	public void addPrestito(Libro L) {
		this.Prestiti.add(L);
	}
	
	public void addRichieste(Libro L) {
		this.Richieste.add(L);
	}
	
	public void removePrestito(Libro L) {
		this.Prestiti.remove(L);
	}
	
	public void removeRichieste(Libro L) {
		this.Richieste.remove(L);
	}
	
	public Queue<Libro> getRichieste() {
		return this.Richieste;
	}
	
	public List<Libro> prestiti() {
		return this.Prestiti.stream().sorted((a, b) -> a.getAutore().compareTo(b.getAutore())).collect(Collectors.toList());
	}
	
	public List<Libro> richieste() {
		return this.Richieste.stream().collect(Collectors.toList());
	}
	
	public int getCodice() {
		return this.Codice;
	}
	
	public String getNome() {
		return this.Nome;
	}
	
	public String getCognome() {
		return this.Cognome;
	}
	
	public String toString() {
		return this.Codice + " " + this.Nome + " " + this.Cognome;
	}
	
}
