package orari;

import java.util.Comparator;

class TimeComparator implements Comparator<Fermata> {

	@Override
	public int compare(Fermata o1, Fermata o2) {
		
		if ( o1.Orario.ore < o2.Orario.ore ) 
			return -1;
		else if ( o1.Orario.ore == o2.Orario.ore && o1.Orario.minuti < o2.Orario.minuti )
			return -1;
		else if ( o1.Orario.ore == o2.Orario.ore && o1.Orario.minuti == o2.Orario.minuti )
			return 0;
		else 
			return 1;
	}
}

public class Fermata {
	
	class Time { 
		int ore, minuti; 
		
		public Time(int h, int m) {
			this.ore = h;
			this.minuti = m;
		}
	} 
	
	private String Stazione;
	Time Orario;
	
	public Fermata (String station, int h, int m) {
		
		this.Stazione = station;
		this.Orario = new Time(h, m);
	}
	
  public String getStazione() {
    return this.Stazione;
  }

  public int getOre() {
	return this.Orario.ore;
  }

  public int getMinuti() {
    return this.Orario.minuti;
  }
}
