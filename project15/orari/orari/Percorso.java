package orari;

import java.util.*;

public class Percorso {
	
	private List<Fermata> Fermate = new ArrayList<Fermata>();
	private List<Treno> Treni = new ArrayList<Treno>();
	
	private String Codice, Categoria;
	private Boolean ExtraO;
	
	public Percorso(String cod, String cat) {
		this.Codice = cod;
		this.Categoria = cat;
		this.ExtraO = false;
	}

  public String getCodice() {
    return this.Codice;
  }

  public String getCategoria() {
    return this.Categoria;
  }

  public boolean isStraordinario() {
    return this.ExtraO;
  }

  public void setStraordinario(boolean b) {  
	  this.ExtraO = b;  
  }
  
  public void addTrain(Treno T) {
	  this.Treni.add(T);
  }

  public Fermata aggiungiFermata(String nomeStazione, int ore, int minuti) {
	  
	  Fermata F1 = new Fermata(nomeStazione, ore, minuti);
	  this.Fermate.add(F1);
	  Collections.sort(this.Fermate, new TimeComparator());
	  
	  return F1;
  }

  public List<Fermata> getFermate() {
    return this.Fermate;
  }
  
  public List<Treno> getTreni() {
	  return this.Treni;
  }
  
  public String getLast() {
	  return this.Fermate.get(this.Fermate.size() - 1).getStazione();
  }

  public int ritardoMassimo() {
	  Integer M_RIT, B_RIT = 0;
	  
	  for (Treno T : this.Treni) {
		  M_RIT = T.ritardoFinale();
		  
		  if (M_RIT > B_RIT)
			  B_RIT = M_RIT;
	  }
	  return B_RIT;
  }

  public int ritardoMedio() {
	  Integer CNT_TRENI = 0, A_RIT = 0;
	  
	  for (Treno T : this.Treni) {
		  CNT_TRENI++;
		  A_RIT += T.ritardoFinale();
	  }
	  
	  return (A_RIT / CNT_TRENI);
  }
}
