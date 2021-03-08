package abbigliamento;

public class Modello {
	
	String nome; 
	double costoFisso; 
	double quantitaTessuto;
	
	public Modello(String nome, double costoFisso, double quantitaTessuto) {
		this.nome = nome;
		this.costoFisso = costoFisso;
		this.quantitaTessuto = quantitaTessuto;
	}

	public String getNome() {
		return this.nome;
	}
	
	public double getCosto() {
		return this.costoFisso;
	}
	
	public double getQuantita() {
		return this.quantitaTessuto;
	}
	
	public boolean isModel(String nome) {
		return this.nome.equals(nome);
	}

}
