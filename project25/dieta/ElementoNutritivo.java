package dieta;

/**
 * Interfaccia che rappresenta un generico elemento nutritivo
 * puo' essere una materia prima, un prodotto lavorato, oppure
 * una ricetta.
 * 
 */
public interface ElementoNutritivo {
	public String getNome();
	public double getCalorie();
	public double getProteine();
	public double getCarboidrati();
	public double getGrassi();
	public boolean per100G();
}
