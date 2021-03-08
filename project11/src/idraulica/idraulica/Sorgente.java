package idraulica;

import hydraulic.Elemento;

public class Sorgente extends Elemento {
	
	Elemento dst;

	public Sorgente(String nome) {
		super(nome);

		this.dst = null;
		this.portataQ = 0.0;
	}
	
	public Elemento getSourceExit() {
		return this.dst;
	}
	
	public void setSourceExit(Elemento x) {
		this.dst = x;
	}

	public void setPortata(double portata){
		this.portataQ = portata;
	}
	
	@Override
	public void connetti(Elemento elem){
		if ( elem instanceof Sorgente )
			System.out.println("ERRORE DI COLLEGAMENTO");
		
		if ( elem instanceof Rubinetto ) {
			((Rubinetto) elem).setInR(this);
			this.dst = (Rubinetto) elem;
		}
		
		if ( elem instanceof Scarico ) {
			((Scarico) elem).setSinkSrc(this);
			this.dst = (Scarico) elem;
		}
		
		if ( elem instanceof Split ) {
			((Split) elem).setSplitIn(this);
			this.dst = (Split) elem;
		}
	}

	public void simula(double input) {
		
		System.out.println(this.id + " -> Portata in uscita: "+ this.portataQ);
		
		if (this.dst != null)
			this.dst.simula(this.portataQ);
		else 
			System.out.println("Problema in uscita nell elemento '" + this.id +"'");
		
	}
}
