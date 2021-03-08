import rubrica.Rubrica;

/**
 * Classe con un main di esempio.
 */
public class Principale {

    /**
     * Esempio di uso della classe Rubrica.
     * Puo essere utilizzato come semplice "test"
     * 
     */
	public static void main(String[] args) {
		
		// Quando si crea un oggetto Rubrica si deve fornire un titolo 
		Rubrica r = new Rubrica("Colleghi di lavoro");
		Rubrica r2 = new Rubrica("Amici");
		
		//...che e' poi accessibile tramite il metodo getTitolo().
		String titoloRubrica = r.getTitolo();
		String titoloRubrica2 = r2.getTitolo();
		
		System.out.println("Creata rubrica 1 con nome '" + titoloRubrica +"'");
		System.out.println("Creata rubrica 2 con nome '" + titoloRubrica2 +"'");
		
		// L'inserimento di una nuova voce nella rubrica avviene 
		// tramite il metodo aggiungi() che riceve tre parametri
		// stringa: nome, cognome, e telefono.
		r.aggiungi("Giovanni","Bianchi","123 45 67");
		r.aggiungi("Mario","Rossi","321 98 76");
		r.aggiungi("Giuseppe","Verdi","456 789 123");

		r2.aggiungi("Giovanni", "Buscetta", "333 33 33 333");
		r2.aggiungi("La", "Fogna", "344 43 34 343");
		r2.aggiungi("Carlotta", "Montalbano", "610 61TROIA 454");
		
		// Per accedere al primo elemento della rubrica si 
		// utilizza il metodo primo()
		String primaVoce = r.primo();
		String primaVoce2 = r2.primo();
		
		System.out.println("\nprimo() --> " + primaVoce);
		
		// Per accedere ad un elemento qualsiasi nella rubrica 
		// si utilizza il metodo voce() che riceve come parametro 
		// il numero d'ordine della voce (a partire da 1, 
		// percio primo() == voce(1));
		
		System.out.println("voce(1) --> " + r.voce(1));
		System.out.println("voce(2) --> " + r.voce(2));
		System.out.println("voce(3) --> " + r.voce(3));
		
		System.out.println();
		
		System.out.println("\nprimo() --> " + primaVoce2);
		System.out.println("voce(1) --> " + r2.voce(1));
		System.out.println("voce(2) --> " + r2.voce(2));
		System.out.println("voce(3) --> " + r2.voce(3));
		
		// Il metodo elenco() restituisce una stringa con 
		// l'elenco delle voci della rubrica separate da ", "; 
		// l'elenco inizia con "(" e termina con ")".
		System.out.println("\nElenco: "+ titoloRubrica +" " + r.elenco());
		System.out.println("\nElenco: "+ titoloRubrica2 +" " + r2.elenco());
		
		// il metodo ricerca() restituisce la stringa 
		// corrispondente alla prima voce che contiene il 
		// parametro come nome, cognome oppure telefono
		System.out.println("\nRicerca 'Verdi': " + r.ricerca("Verdi"));
		System.out.println("\nRicerca 'Fogna': " + r2.ricerca("Fogna"));
		
	}
}
