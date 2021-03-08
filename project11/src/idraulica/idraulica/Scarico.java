package idraulica;

import hydraulic.Elemento;

public class Scarico extends Elemento {

	private Elemento src;

	public Scarico(String nome) {
		super(nome);
		this.src = null;
	}

	@Override
	public void connetti(Elemento elem){
		System.out.println("ERRORE DI COLLEGAMENTO");
	}

	public Elemento getSinkSrc() {
		return this.src;
	}

	public void setSinkSrc(Elemento x) {
		this.src = x;
	}

	public void simula(double input) {

		this.portataQ = input;
		System.out.println(this.id + " -> Portata in ingresso: " + this.portataQ);

	}
}
