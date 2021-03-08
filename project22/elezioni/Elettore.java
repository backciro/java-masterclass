package elezioni;


public class Elettore implements Cittadino {
  private int numVoti;
  private String nome;
  private String cognome;
  private boolean votato;
  private boolean candidato;
  private boolean capolista;
  
  public Elettore(String n, String c) {
	  this.nome = n;
	  this.cognome = c;
	  this.votato = false;
	  this.candidato = false;
	  this.capolista = false;
	  this.numVoti = 0;
	  
  }
  
	public String getNome(){
		return this.nome;
	};
	public String getCognome(){
		return this.cognome;
	};
	public boolean haVotato(){
		return this.votato;
	};
	public boolean isCapolista(){
		return this.capolista;
	};
	public boolean isCandidato(){
		return this.candidato;
	};
	public long getNumeroVoti(){
		return this.numVoti;
	};
	

	public void setCapolista() {
		this.capolista = true;
	};

	public void setCandidato() {
		this.candidato = true;
	};
	
	public void getVoto() {
		this.numVoti++;
	}
	
	public void vota() {
		this.votato = true;
	};
}
