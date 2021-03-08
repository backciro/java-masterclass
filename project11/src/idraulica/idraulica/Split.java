package idraulica;

import hydraulic.Elemento;

public class Split extends Elemento {

	Elemento src;
	Elemento dst0, dst1;

	public Split(String nome) {

		super(nome);
		this.dst0 = null;
		this.dst1 = null;
	}

	public Elemento getSplitSrc() {
		return this.src;
	}

	public void setSplitIn(Elemento x) {
		this.src = x;
	}

	public Elemento getSplitDst(int o) {
		if (o == 0)
			return this.dst0;
		else if (o == 1)
			return this.dst1;
		else return null;
	}

    public Elemento[] getUscite(){

    	Elemento[] array = new Elemento[2];
    	array[0] = this.dst0;
    	array[1] = this.dst1;

    	return array;
    }

	public void connetti(Elemento elem, int uscita){

		if (uscita == 0) {
			if ( elem instanceof Rubinetto ) {
				((Rubinetto) elem).setInR(this);
				this.dst0 = (Rubinetto) elem;
			}

			if ( elem instanceof Scarico ) {
				((Scarico) elem).setSinkSrc(this);
				this.dst0 = (Scarico) elem;
			}

			if ( elem instanceof Split ) {
				((Split) elem).setSplitIn(this);
				this.dst0 = (Split) elem;
			}
		}

		else if (uscita == 1) {
			if ( elem instanceof Rubinetto ) {
				((Rubinetto) elem).setInR(this);
				this.dst1 = (Rubinetto) elem;
			}

			if ( elem instanceof Scarico ) {
				((Scarico) elem).setSinkSrc(this);
				this.dst1 = (Scarico) elem;
			}

			if ( elem instanceof Split ) {
				((Split) elem).setSplitIn(this);
				this.dst1 = (Split) elem;
			}
		}

		else
			System.out.println("Errore parametri funzione");
	}

	public void simula(double input) {

		this.portataQ = input;
		System.out.println(this.id + " -> Portata in ingresso: " + this.portataQ);

		if (this.dst0 != null && this.dst1 != null)
			System.out.println(this.id + " -> Portate in uscita:  0: " +this.portataQ/2 + "  1: "+ this.portataQ/2 );

		if (this.dst0 != null && this.dst1 != null) {
			this.dst0.simula( (this.portataQ/2) );
			this.dst1.simula( (this.portataQ/2) );
		}

		else
			System.out.println("Perdite in uscita nell elemento '" + this.id+"'");

	}

}
