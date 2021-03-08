package dieta;

import java.util.*;
import java.util.stream.Collectors;

public class Menu implements ElementoNutritivo {
	
	private Alimenti ParentReference;

	private String Nome;
	
	Map<String, Double> Ricette = new HashMap<String, Double>();
	Collection<String> Prodotti = new ArrayList<String>();
 
	double KCal, Proteine, Carboidrati, Grassi;
	boolean isFor100G ;
	
	
	public Menu (String n, Alimenti a){
		this.Nome = n;
		this.ParentReference = a;
		
		this.KCal = 0;
		this.Proteine = 0;
		this.Carboidrati = 0;
		this.Grassi = 0;
		
		this.isFor100G = false;
	}

	
	public void aggiungiRicetta(String ricetta, double quantita) {
		
		this.Ricette.put(ricetta, quantita);
		
		this.KCal += ( this.ParentReference.ricette().stream()
				.filter(a -> a.getNome().equals(ricetta))
				.collect(Collectors.summingDouble(ElementoNutritivo::getCalorie)) / 100 ) * quantita;
		
		this.Proteine += ( this.ParentReference.ricette().stream()
				.filter(a -> a.getNome().equals(ricetta))
				.collect(Collectors.summingDouble(ElementoNutritivo::getProteine)) / 100 ) * quantita;
		
		this.Carboidrati += ( this.ParentReference.ricette().stream()
				.filter(a -> a.getNome().equals(ricetta))
				.collect(Collectors.summingDouble(ElementoNutritivo::getCarboidrati)) / 100 ) * quantita;
		
		this.Grassi += ( this.ParentReference.ricette().stream()
				.filter(a -> a.getNome().equals(ricetta))
				.collect(Collectors.summingDouble(ElementoNutritivo::getGrassi)) / 100 ) * quantita;
	}

	public void aggiungiProdotto(String prodotto) {
		this.Prodotti.add(prodotto);
		
		this.KCal += this.ParentReference.prodotti().stream()
				.filter(a -> a.getNome().equals(prodotto))
				.collect(Collectors.summingDouble(ElementoNutritivo::getCalorie)) ;
		
		this.Proteine += this.ParentReference.prodotti().stream()
				.filter(a -> a.getNome().equals(prodotto))
				.collect(Collectors.summingDouble(ElementoNutritivo::getProteine)) ;
		
		this.Carboidrati += this.ParentReference.prodotti().stream()
				.filter(a -> a.getNome().equals(prodotto))
				.collect(Collectors.summingDouble(ElementoNutritivo::getCarboidrati)) ;
		
		this.Grassi += this.ParentReference.prodotti().stream()
				.filter(a -> a.getNome().equals(prodotto))
				.collect(Collectors.summingDouble(ElementoNutritivo::getGrassi)) ;
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
