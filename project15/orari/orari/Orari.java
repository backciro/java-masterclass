package orari;

import java.util.*;

public class Orari {

  private Collection<Percorso> Percorsi = new ArrayList<Percorso>();
  
  public Collection<Percorso> getPercorsi() {
	    return this.Percorsi;
	  }
	
  public Percorso creaPercorso(String codice, String categoria) {
    
	  Percorso P1 = new Percorso(codice, categoria);
	  this.Percorsi.add(P1);
	  return P1; 
  }

  public Percorso getPercorso(String codice) {
	  for (Percorso p : Percorsi)
		  if (codice.equals(p.getCodice()))
			  return p;
	  
	  System.out.println("Percorso non trovato");
	  return null;
  }
  
  public String getCapolinea(String cod) {
	  
	  String cap = null;
	  
	  for (Percorso P : this.Percorsi)
		  if (P.getCodice().equals(cod))
			  cap = P.getLast();
		  
		  
	  return cap;
  }

  public Treno nuovoTreno(String codice, int giorno, int mese, int anno) throws PercorsoNonValido {
	 
	  Boolean Valid = Boolean.FALSE;
	  Treno T1 = null; Percorso P1 = null;
	  
	  for (Percorso p : this.Percorsi)
		  if (p.getCodice().equals(codice)) {
			  Valid = Boolean.TRUE;
			  P1 = p;
		  }
	  
	  if (Valid) {
		 T1 = new Treno(codice, giorno, mese, anno);
		// al costruttore potrei passare il percorso P1 (invece del codice che neanche uso) e gestire internamente le assegnazioni di rotta e capolinea
		// ma io faccio il cazzo che mi pare!
		 
		 T1.setRotta(P1); 
		 T1.setCapolinea(P1.getLast());
		 
		 P1.addTrain(T1);
		 
		 Collections.sort(P1.getTreni(), new DateComparator());
	  }
	  else 
		  throw new PercorsoNonValido();
	  
    return T1;
  }
  
}
