package dieta;

public class Prodotto implements ElementoNutritivo {

	String Nome; 
	double KCal, Proteine, Carboidrati, Grassi;
	boolean isFor100G ;
	
	public Prodotto(String n, double KC, double P, double C, double G) {
		this.Nome = n;
		this.KCal = KC;
		this.Proteine = P;
		this.Carboidrati = C;
		this.Grassi = G;
		
		this.isFor100G = false;
	}
	
	@Override
	public String getNome() {
		return this.Nome;
	}

	@Override
	public double getCalorie() {
		return this.KCal;
	}

	@Override
	public double getProteine() {
		return this.Proteine;
	}

	@Override
	public double getCarboidrati() {
		return this.Carboidrati;
	}

	@Override
	public double getGrassi() {
		return this.Grassi;
	}

	@Override
	public boolean per100G() {
		return this.isFor100G;
	}


}
