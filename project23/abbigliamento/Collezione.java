package abbigliamento;

import java.util.*;
import java.util.stream.Collectors;


public class Collezione {    
	
	static int IDcollection = 1;
	
	private String ID;
	Collection<Capo> Catalogo = new LinkedList<Capo>();
	
	public Collezione() {
		this.ID = "COLL" + Collezione.IDcollection++;
		this.Catalogo.clear();
	}
	
    public void add(Capo capo) {
    	this.Catalogo.add(capo);
	}
    
	public Collection<Capo> trova(Colore colore) {
		return this.Catalogo.stream().filter(c -> c.isColor(colore)).collect(Collectors.toList());
	}
        
	public Collection<Capo> trova(Materiale materiale) {	
		return this.Catalogo.stream().filter(c -> c.isMaterial(materiale)).collect(Collectors.toList());
	}
	    
	public Collection<Capo> trova(Modello modello) {
		return this.Catalogo.stream().filter(c -> c.isModel(modello)).collect(Collectors.toList());
	}
	
	public boolean isCollection(String nome) {
		return this.ID.equals(ID);
	}
	
	public String getID() {
		return this.ID;
	}
}

