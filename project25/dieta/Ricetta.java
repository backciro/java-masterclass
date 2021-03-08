package dieta;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Ricetta implements ElementoNutritivo {
	
	private String Nome;
	private Map<String, Double> Ingredienti = new HashMap<String, Double>();
	
	private Alimenti ParentReference;
	
	private double GrassiFinal;
	private double CarboidratiFinal;
	private double ProteineFinal;
	private double KCalFinal;
	
	private double Grassi;
	private double Carboidrati;
	private double Proteine;
	private double KCal;

	private boolean isFor100G;
	private int pesoTOT;
	
	public Ricetta (String n, Alimenti a) {
		this.Nome = n;
		this.ParentReference = a; //che porcheria porco dio
		
		this.KCalFinal = 0;
		this.CarboidratiFinal = 0;
		this.CarboidratiFinal = 0;
		this.GrassiFinal = 0;
		
		this.isFor100G = true;
		
		this.pesoTOT = 0;
		this.ParentReference.addRicetta(this);
	}
	
	public void aggiungiIngrediente(String ingrediente, double quantita) {
		
		this.Ingredienti.put(ingrediente, quantita);
		this.pesoTOT += quantita;
		
		this.KCal = 0; this.Proteine = 0; this.Carboidrati = 0; this.Grassi = 0;
			
			for ( Entry<String, Double> s : Ingredienti.entrySet()) {
				this.KCal += this.ParentReference.materiePrime().stream()
						.filter((a) -> a.getNome().equals(s.getKey()))
						.collect(Collectors.summingDouble(ElementoNutritivo::getCalorie)) * (s.getValue()/100)  ;
			
				this.Proteine += this.ParentReference.materiePrime().stream()
						.filter((a) -> a.getNome().equals(s.getKey()))
						.collect(Collectors.summingDouble(ElementoNutritivo::getProteine)) * (s.getValue()/100)  ;
				
				this.Carboidrati += this.ParentReference.materiePrime().stream()
						.filter((a) -> a.getNome().equals(s.getKey()))
						.collect(Collectors.summingDouble(ElementoNutritivo::getCarboidrati)) * (s.getValue()/100)  ;
				
				this.Grassi += this.ParentReference.materiePrime().stream()
						.filter((a) -> a.getNome().equals(s.getKey()))
						.collect(Collectors.summingDouble(ElementoNutritivo::getGrassi)) * (s.getValue()/100)  ;
			
			}
			
			this.KCalFinal = ((double) 100.0 / this.pesoTOT) * this.KCal;
			this.ProteineFinal = ((double) 100.0 / this.pesoTOT) * this.Proteine;
			this.CarboidratiFinal = ((double) 100.0 / this.pesoTOT) * this.Carboidrati;
			this.GrassiFinal = ((double) 100.0 / this.pesoTOT) * this.Grassi;		
	}

	@Override
	public String getNome() {
		return this.Nome;
	}

	@Override
	public double getCalorie() {
		return this.KCalFinal;
	}

	@Override
	public double getProteine() {
		return this.ProteineFinal;
	}

	@Override
	public double getCarboidrati() {
		return this.CarboidratiFinal;
	}

	@Override
	public double getGrassi() {
		return this.GrassiFinal;
	}

	@Override
	public boolean per100G() {
		return this.isFor100G;
	}
}
