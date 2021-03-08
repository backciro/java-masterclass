package orari;

import java.util.*;

class DateComparator implements Comparator<Treno> {

	@Override
	public int compare(Treno o1, Treno o2) {
		
		if ( o1.Data.year < o2.Data.year )
			return -1;
		else if ( o1.Data.year == o2.Data.year && o1.Data.month < o2.Data.month )
			return -1;
		else if ( o1.Data.year == o2.Data.year && o1.Data.month == o2.Data.month && o1.Data.day < o2.Data.day )
			return -1;
		else if ( o1.Data.year == o2.Data.year && o1.Data.month == o2.Data.month && o1.Data.day == o2.Data.day )
			return 0;
		else return 1;
	}
}

public class Treno {
	
	class Date { 
		int day; int month; int year; 
		
		public Date(int d, int m, int y) {
			this.year = y;
			this.month = m;
			this.day = d;
		}
	}
	
	private List<Passaggio> Stops = new ArrayList<Passaggio>();

	private Percorso Rotta;
	private String Capolinea;
	private Boolean Arrivato;
	
	private Integer MaxRetard;
	private Integer TotRetard;
	
	Date Data;

	public Treno(String cod, int dd, int mm, int yyyy) {
		
		this.Stops.clear();
		
		this.Data = new Date(dd, mm, yyyy);
		this.Arrivato = Boolean.FALSE;
		
		this.MaxRetard = 0;
		this.TotRetard = 0;
	}

  public Percorso getPercorso() {
    return this.Rotta;
  }

  public int getGiorno() {
	  return this.Data.day;
  }

  public int getMese() {
	  return this.Data.month;
  }

  public int getAnno() {
	  return this.Data.year;
  }
  
  public void setRotta(Percorso R) {
	  this.Rotta = R;
  }
  
  public void setCapolinea(String C) {
	  this.Capolinea = C;
  }

  public Passaggio registraPassaggio(String string, int h, int m) throws StazioneNonValida {

	 Boolean Valid = Boolean.FALSE;
	 Fermata F1 = null; Passaggio P1 = null;
	 int R;
	  
	  for (Fermata f : this.Rotta.getFermate())
		  if (f.getStazione().equals(string)) { 
			  Valid = Boolean.TRUE;
			  F1 = f;
		  }
	  
	  if (!Valid)
		  throw new StazioneNonValida();
		
	  else {
		  P1 = new Passaggio(string, h, m); 			//setting train real time
		  P1.setExpected(F1.getOre(), F1.getMinuti());	//setting train expected time
		
		  this.Stops.add(P1);
	  
		  R = P1.ritardo();
		
		  if (R > this.MaxRetard)
			  this.MaxRetard = R;
	  }
	  
	  if (F1.getStazione().equals(this.Capolinea)) {
		  this.Arrivato = Boolean.TRUE;
		  this.TotRetard = R;
	  }
	  
    return P1;
  }

  public boolean arrivato() {
	  return this.Arrivato;
  }

  public int ritardoMassimo() {
	  return this.MaxRetard;
  }

  public int ritardoFinale() {
	  return this.TotRetard;
  }
}
