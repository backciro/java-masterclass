package abbigliamento;

public class Capo {
	
	String ID;
	
	Modello model;
	Materiale material;
	Colore color;
	
	double price;

	public Capo(Modello modello, Materiale materiale, Colore colore) {
		this.model = modello;
		this.material = materiale;
		this.color = colore;
		
		this.price = ( this.model.getCosto() + (this.model.getQuantita() * this.material.getCosto()) ) ;
	}
	
	public String getID() {
		return this.ID;
	}

	public double prezzo() {
		return this.price;
	}
	
	public Modello getModel() {
		return this.model;
	}
	
	public Materiale getMaterial() {
		return this.material;
	}
	
	public Colore getColor() {
		return this.color;
	}
	
	public boolean isColor(Colore c) {
		return this.color.equals(c);
	}
	
	public boolean isMaterial(Materiale m) {
		return this.material.equals(m);
	}
	
	public boolean isModel(Modello m) {
		return this.model.equals(m);
	}
	
	public void setID(String id) {
		this.ID = id;
	}
	
	public boolean isCape(String nome) {
		return this.ID.equals(nome);
	}
	
	public String toString() {
		return (this.model.getNome() + " " + this.color.getNome() + " di " + this.material.getNome());
	}
}
