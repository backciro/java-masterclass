package abbigliamento;

public class Colore {

	String nome;
	
	public Colore(String nome) {
		this.nome = nome;
	}

	public String getNome(){
		return this.nome;
	}
	
	public boolean isColor(String nome) {
		return this.nome.equals(nome);
	}
}
