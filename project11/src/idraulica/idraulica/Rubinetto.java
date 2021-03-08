package idraulica;

import hydraulic.Elemento;

public class Rubinetto extends Elemento {
	
	Elemento src, dst;
	Boolean isOpen = Boolean.FALSE;

	public Rubinetto(String nome) {
		super(nome);
		
		this.src = null;		
		this.dst = null;		
		this.portataQ = 0.0;
	}
	
	@Override
	public void connetti(Elemento elem){
		
		if ( elem instanceof Sorgente )
			System.out.println("ERRORE DI COLLEGAMENTO");
		
		if ( elem instanceof Scarico ) {
			((Scarico) elem).setSinkSrc(this);
			this.dst = (Scarico) elem;
		}
		
		if ( elem instanceof Split ) {
			((Split) elem).setSplitIn(this);
			this.dst = (Split) elem;
		}	
	}
	
	public void setOpen(boolean open){
		this.isOpen = open;
	}
	
	public Elemento getInR() {
		return this.src;
	}
	
	public Elemento getOutR() {
		return this.dst;
	}
	
	public void setInR(Elemento x) {
		this.src = x;
		
	}
	
	public void setOutR(Elemento x) {
		this.dst = x;
	}
	
	public Boolean isOpen() {
		return this.isOpen;
	}

	public void simula(double input) {
		
		this.portataQ = input;
		
		if (this.isOpen)
			System.out.println(this.id + " -> Portata in ingresso = portata in uscita: "+ this.portataQ);
		
		else {
			System.out.println(this.id + " -> Portata in ingresso: " + this.portataQ);
			System.out.println("ATTENZIONE RUBINETTO '" + this.id + "' CHIUSO");
		}
		
		if (this.dst != null && this.isOpen)
			this.dst.simula(this.portataQ);

		else
			System.out.println("Problema in uscita nell elemento '" + this.id +"'");
	}

}
