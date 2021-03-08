package gestioneTaxi;

public class Corsa {

	Luogo Partenza, Arrivo;
	
	public Corsa(Luogo p, Luogo a) {
		this.Partenza = p;
		this.Arrivo = a;

	}
	
	public String toString() {
		return this.Partenza.toString() + "," + this.Arrivo.toString();
	}
	
}
