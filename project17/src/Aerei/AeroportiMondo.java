package Aerei;

import java.util.*;

public class AeroportiMondo {

	private List<Aeroporto> Avail_Airports = new LinkedList<Aeroporto>();
	
	public void addAeroporto(Aeroporto arpt) throws InvalidCode{
		
		if (this.Avail_Airports.contains(arpt))
			throw new InvalidCode();
		else
			this.Avail_Airports.add(arpt);
	}
	
	public void removeAeroporto(String cod) {
		for(Aeroporto AP : this.Avail_Airports)
			if (AP.getCode().equals(cod)) {
				this.Avail_Airports.remove(AP);
				return;
			}
	}
	
	public Aeroporto getAeroporto(String cod) {
		for(Aeroporto AP : this.Avail_Airports)
			if (AP.getCode().equals(cod))
				return AP;
		return null;
	}
	
	public List<Aeroporto> getAeroporti() {
		return this.Avail_Airports;
	}
	
	public void updateLists(List<Aeroporto> Airports) {
		this.Avail_Airports = Airports;
	}
}
