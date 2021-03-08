package gestioneTaxi;

public class Passeggero {
	
	Luogo Posizione;
	
	public Passeggero(Luogo pos) {
		this.Posizione = pos;
	}
	
	public void setPosizione(Luogo pos) {
		this.Posizione = pos;
	}
	
	public Luogo getPosizione() {
		return this.Posizione;
	}

}
