
package dieta;

import java.util.*;
import java.util.stream.*;


public class Alimenti {
	
	Collection<ElementoNutritivo> MateriaPrime = new ArrayList<ElementoNutritivo>();
	Collection<ElementoNutritivo> Prodotti = new ArrayList<ElementoNutritivo>();
	Collection<ElementoNutritivo> Ricette = new ArrayList<ElementoNutritivo>();
	
	
	public void definisciMateriaPrima(String nome, double calorie, double proteine, double carboidrati, double grassi) {
		this.MateriaPrime.add(new MateriaPrima(nome, calorie, proteine, carboidrati, grassi));
	}
	
	public Collection<ElementoNutritivo> materiePrime(){
		return this.MateriaPrime.stream().sorted((a, b) -> a.getNome().compareTo(b.getNome())).collect(Collectors.toList());
	}
	
	public ElementoNutritivo getMateriaPrima(String nome){
		List<ElementoNutritivo> retval = new ArrayList<ElementoNutritivo>();
		retval = this.MateriaPrime.stream().filter(a -> a.getNome().equals(nome)).limit(1).collect(Collectors.toList());
		
		return retval.get(0);
	}

	public void definisciProdotto(String nome, double calorie, double proteine, double carboidrati, double grassi) {
		this.Prodotti.add(new Prodotto(nome, calorie, proteine, carboidrati, grassi));
	}
	
	public Collection<ElementoNutritivo> prodotti(){
		return this.Prodotti.stream().sorted((a, b) -> a.getNome().compareTo(b.getNome())).collect(Collectors.toList());
	}
	
	public ElementoNutritivo getProdotto(String nome){
		List<ElementoNutritivo> retval = new ArrayList<ElementoNutritivo>();
		retval = this.Prodotti.stream().filter(a -> a.getNome().equals(nome)).limit(1).collect(Collectors.toList());
		
		return retval.get(0);
	}
	
	public void addRicetta(Ricetta T) {
		this.Ricette.add(T);
	}
	
	public Collection<ElementoNutritivo> ricette(){
		return this.Ricette.stream().sorted((a, b) -> a.getNome().compareTo(b.getNome())).collect(Collectors.toList());
	}
	
	public ElementoNutritivo getRicetta(String nome){
		List<ElementoNutritivo> retval = new ArrayList<ElementoNutritivo>();
		retval = this.Ricette.stream().filter(a -> a.getNome().equals(nome)).limit(1).collect(Collectors.toList());
		
		return retval.get(0);
	}
	
}
