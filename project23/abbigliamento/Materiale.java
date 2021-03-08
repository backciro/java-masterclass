package abbigliamento;

public class Materiale {
	
	String nome;
	double costo;
    
	public Materiale(String nome, double costo) {		

		this.nome = nome;
		this.costo = costo;
	}

	public String getNome() {
		return this.nome;
	}

	public double getCosto() {
		return this.costo;		
	}
	
	public boolean isMaterial(String nome) {
		return this.nome.equals(nome);
	}
}
