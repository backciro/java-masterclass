package gestioneTaxi;

public class Luogo implements InfoI{
	
	String Indirizzo, Quartiere;
	
	private int nArrivi;
	
	public Luogo(String ind, String quart) {
		this.Indirizzo = ind;
		this.Quartiere = quart;
		
		this.nArrivi = 0;
	}
	
	public String toString(){
		return this.Indirizzo;
	}

	@Override
	public int compareTo(InfoI o) {
		if (this.nArrivi < o.getValore())
			return -1;
		else if (this.nArrivi > o.getValore())
			return 1;
		else 
			return o.getId().compareTo(this.Quartiere);
	}
	
	@Override
	public String getId() {
		return this.Quartiere;
	}

	@Override
	public int getValore() {
		return this.nArrivi;
	}

	@Override
	public void increment() {
		this.nArrivi++;
	}

}
