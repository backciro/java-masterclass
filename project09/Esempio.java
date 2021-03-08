import ateneo.*;

/**
 * Classe con un main di esempio.
 */
public class Esempio {

	public static void main(String[] args) {
		
		String nomeAteneo = "Politecnico di Torino";
		String nomeAteneo2 = "Universita\' di Torino";
		
		Ateneo poli = new Ateneo(nomeAteneo);
		Ateneo unito = new Ateneo(nomeAteneo2);
		
		poli.setRettore("Marco", "Gilli");
		unito.setRettore("Gianni", "Mastrota");
		
		System.out.println("Rettore di " + poli.getNome() + " : " + poli.getRettore());
		System.out.println("Rettore di " + unito.getNome() + " : " + unito.getRettore());
		
		System.out.println();
		
		int s1 = poli.immatricola("Mario","Rossi");
		int s2 = poli.immatricola("Giuseppe","Verdi");
		int s3 = poli.immatricola("Matteo", "Minutti");
		int s4 = poli.immatricola("Gaetano", "Malta");
		
		int u1 = unito.immatricola("Martino", "Masu");
		int u2 = unito.immatricola("Luca", "Lutzu");
		int u3 = unito.immatricola("Filippo", "Minarelli");
		int u4 = unito.immatricola("Matteo", "Cossu");
		
		System.out.println("Inseriti studenti s" + s1 + ", s" + s2 + ", s" + s3 + ", s" + s4);
		System.out.println("Inseriti studenti u" + u1 + ", su" + u2 + ", su" + u3 + ", su" + u4);
		
		System.out.println("s4 = " + poli.studente(s4));
		System.out.println("u4 = " + unito.studente(u4));
		
		System.out.println();
		
		int macro = poli.attiva("Macro economia", "Paul Krugman");
		int apa = poli.attiva("Algoritmi e Programmazione", "Cabodi G., Camurati P.");
		int info = poli.attiva("Informatica I", "Stefano di Carlo");
		
		int sm = unito.attiva("Scienze delle merendino", "Carlo Cracco");
		int bocc = unito.attiva("Boccheggio Avanzato", "Marco Reus");

		System.out.println("Corsi disponibili: ");
		System.out.println("Apa = " + poli.insegnamento(apa));
		System.out.println("Informatica I = " + poli.insegnamento(info));
		System.out.println("MacroEconomia = " + poli.insegnamento(macro));
		
		System.out.println("\nCorsi disponibili (UniTO): ");
		System.out.println("SM = " + unito.insegnamento(sm));
		System.out.println("Boccheggio = " + unito.insegnamento(bocc));

		System.out.println();
		
		poli.iscrivi(s3, apa);
		poli.iscrivi(s3, info);
		poli.iscrivi(s2, macro);		
		poli.iscrivi(s1, macro);
		poli.iscrivi(s4, apa);
		poli.iscrivi(s3, macro);
		
		unito.iscrivi(u1, sm);
		unito.iscrivi(u2, sm);
		unito.iscrivi(u3, bocc);
		unito.iscrivi(u4, sm);
		
		System.out.println("Elenco iscritti corso APA: ");
		System.out.println(poli.elencoIscritti(apa));	
		
		System.out.println("\nElenco iscritti corso SM: ");
		System.out.println(unito.elencoIscritti(sm));
		
		System.out.println("Piano di studi di: " + poli.studente(s3));
		System.out.println(poli.pianoStudi(s3));
		
		System.out.println("\nPiano di studi di: " + unito.studente(u1));
		System.out.println(unito.pianoStudi(u1));
	}
}
