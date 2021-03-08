package Library;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Biblioteca {

	public static <T> Collector<T, List<T>, T> singletonStreamCollector() {
		return Collector.of(ArrayList::new, 
				List::add, 
				(left, right) -> { left.addAll(right); return left; }, 
				list -> {
					if (list.size() != 1)
						throw new IllegalStateException();
					return list.get(0);
				});
	}
	
	String Nome;

	Collection<Libro> Libri = new ArrayList<Libro>();
	Collection<Utente> Utenti = new ArrayList<Utente>();
	
	Queue<Utente> Richieste = new LinkedList<Utente>();
	List<Libro> Prestiti = new LinkedList<Libro>();
	List<Libro> Richieste_Libri = new LinkedList<Libro>();
	
	
	public Biblioteca (String n) {
		this.Nome = n;	
	}
	
	public void addLibro(Libro lib) throws InvalidIsbn {
		
		if ( this.Libri.stream().map(Libro::getISBN)
			.filter(a -> a.equals(lib.getISBN()))
			.collect(Collectors.toList()).size() > 0 )
			throw new InvalidIsbn();
		
		this.Libri.add(lib);
	}
	
	public Libro getLibro(String isbn){	    
	    return this.Libri.stream().filter(a -> a.getISBN().equals(isbn)).collect(singletonStreamCollector());
	}

	
	public List<Libro> libriPerAutore(){
		return this.Libri.stream().sorted((a, b) -> a.getAutore().compareTo(b.getAutore())).collect(Collectors.toList());
	}
	
	public void addUtente(Utente ut) throws InvalidCode {
	
		if ( this.Utenti.stream().map(Utente::getCodice)
				.filter(a -> a.equals(ut.getCodice()))
				.collect(Collectors.toList()).size() > 0 )
				throw new InvalidCode();
		
		this.Utenti.add(ut);
	}
	
	public List<Utente> utenti(){
		return this.Utenti.stream().sorted((a, b) -> Integer.compare(a.getCodice(), b.getCodice())).collect(Collectors.toList()) ;
	}
	
	public Libro prestito(int cu, String isbn) throws InvalidCode, InvalidIsbn {
		
		Libro L1 = this.Libri.stream().filter(a -> a.getISBN().equals(isbn)).limit(1).collect(singletonStreamCollector());
		Utente U1 = this.Utenti.stream().filter(a -> a.getCodice() == cu).limit(1).collect(singletonStreamCollector());
		
		if (L1 == null)
			throw new InvalidIsbn();
		if (U1 == null)
			throw new InvalidCode();
		
		if ( L1.checkPrestito() && L1.getBorrower().getCodice() == cu)
			return null;
		
		else if ( L1.checkPrestito() ) {
			U1.addRichieste(L1);
			this.Richieste.add(U1);
			this.Richieste_Libri.add(L1);
			return null;
		}	
		
		L1.setPrestito(true);
		L1.setPrestatario(U1);
		U1.addPrestito(L1);
		this.Prestiti.add(L1);
		
		return L1;
	}
	
	public Libro restituzione(int cu, String isbn) throws InvalidCode, InvalidIsbn {
		
		Libro L1 = this.Libri.stream().filter(a -> a.getISBN().equals(isbn)).limit(1).collect(singletonStreamCollector());
		Utente U1 = this.Utenti.stream().filter(a -> a.getCodice() == cu).limit(1).collect(singletonStreamCollector());
		
		if (L1 == null)
			throw new InvalidIsbn();
		if (U1 == null)
			throw new InvalidCode();
		
		if ( !L1.checkPrestito() || !L1.getBorrower().equals(U1) )
			return null;
		
		L1.setPrestito(false);
		U1.removePrestito(L1);
		this.Prestiti.remove(L1);
		
		Utente U2 = this.getRichieste(L1).poll();
		
		if (U2 != null) {
			U2.removeRichieste(L1);
			this.Richieste_Libri.remove(L1);
			this.prestito(U2.getCodice(), L1.getISBN());	
		}
		
		return L1;
	}
	
	public Queue<Utente> getRichieste(Libro l) {
		
		Queue<Utente> retval = new LinkedList<Utente>();
		
		for (Utente R : this.Richieste)
			for(Libro B : R.getRichieste())
				if (B.equals(l))
					retval.offer(R);
		
		return retval;
	}
	
	public List<Libro> elencoPrestiti() {
		return this.Prestiti.stream().distinct().sorted((a, b) -> a.getAutore().compareTo(b.getAutore())).collect(Collectors.toList()) ;
	}
	
	public List<Libro> elencoRichieste() {
		return this.Richieste_Libri.stream().distinct().sorted((a, b) -> a.getAutore().compareTo(b.getAutore())).collect(Collectors.toList()) ;
	}
}
