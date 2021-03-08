package idraulica;

import java.util.*;
import hydraulic.Elemento;

public class Sistema {
	
	public ArrayList<Elemento> elementi = new ArrayList<Elemento>();
	
	public Sistema() {
		this.elementi.clear();	
	}
	
	public void aggiungiElemento(Elemento elem) {
		this.elementi.add(elem);
		
	}	
	
	public Elemento[] getElementi(){
		int i = 0;
		Elemento[] array = new Elemento[elementi.size() + 1];
		
		for (Elemento x : this.elementi)
			array[i++] = x;
		array[i] = null;
		
		return array;
	}
	
	private boolean isSysCorrect(Sistema s) 
	{
		return true;
	}
	
	public void simula() {
		
		if (isSysCorrect(this)) 
		{
			for (Elemento src : elementi)
				if (src instanceof Sorgente && src.portataQ > 0)
					src.simula(src.portataQ);
		}
		
		else {
			System.out.println("Sistema non corretto, impossibile ripartire le portate");
		}
	}
}
