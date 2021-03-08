package Library;

public class Libro {
	
	private String Autore, Titolo, Editore, ISBN;
	
	private Utente Prestatario;
	private boolean inPrestito;
	
	public Libro (String autore, String titolo, String editore, String isbn) {
		this.Autore = autore;
		this.Titolo = titolo;
		this.Editore = editore;
		this.ISBN = isbn;
		
		this.inPrestito = false;
	}
	
	public String getAutore() {
		return this.Autore;
	}
	
	public String getTitolo() {
		return this.Titolo;
	}
	
	public String getEditore() {
		return this.Editore;
	}
	
	public String getISBN() {
		return this.ISBN;
	}
	
	public Utente getBorrower() {
		return this.Prestatario;
	}
	
	public boolean checkPrestito() {
		return this.inPrestito;
	}
	
	public void setPrestito(boolean t) {
		this.inPrestito = t;
	}
	
	public void setPrestatario(Utente t) {
		this.Prestatario = t;
	}
	
	public String toString() {
		return this.Autore + " " + this.Titolo + " " + this.Editore + " " + this.ISBN;
	}
}
