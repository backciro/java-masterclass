package elezioni;


public interface Cittadino {
	public String getNome();
	public String getCognome();
	public boolean haVotato();
	public boolean isCapolista();
	public boolean isCandidato();
	public long getNumeroVoti();
	
	public void setCapolista();
	public void setCandidato();
	public void getVoto();
	public void vota();
}
