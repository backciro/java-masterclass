package gestioneTaxi;

import java.util.*;
import java.util.stream.Collectors;

public class TaxiCompany {
	
	private int chiamatePerse = 0;
	
	Collection<Taxi> Flotta = new ArrayList<Taxi>();
	Queue<Taxi> Disponibili = new LinkedList<Taxi>();

	List<InfoI> StatisticheTaxi = new  ArrayList<InfoI>();
	List<InfoI> StatisticheQuartieri = new  ArrayList<InfoI>();
	
	
	public void addTaxi(String id) 
			throws InvalidTaxiName {
		boolean valid = true;
		
		Taxi T1 = new Taxi(id);
		T1.setPR(this);
		
		for (Taxi t : this.Flotta)
			if (t.getID().equals(id))
				valid = false;
		
		if (valid) {
			this.Flotta.add(T1);
			this.Disponibili.add(T1);
		}
		
		else 
			throw new InvalidTaxiName();
	}
	
	public void enqueueTaxi(Taxi T) {
		this.Disponibili.offer(T);
	}
	
	public Queue<Taxi> getLiberi() {
		return this.Disponibili;
	}

	public Taxi chiamataTaxi(Passeggero p) {
		
		Taxi T1 = null;
		
		if (this.Disponibili.size() == 0) {
			System.out.println("Chiamata persa...");
			this.chiamatePerse++;
			return null;
		}
		
		else {
			T1 = this.Disponibili.poll();
			T1.setCliente(p);
			
			this.StatisticheTaxi.add(T1);
		}
			
		return T1;
	}
	
	public List<Corsa> getCorse(String id) throws InvalidTaxiName{
		
		List<Taxi> retval = new ArrayList<Taxi>(1);
		
		retval = this.Flotta.stream()
				.filter(a -> a.getId().equals(id))
				.collect(Collectors.toList());
		
		if (retval.isEmpty())
			throw new InvalidTaxiName();
		
		Taxi T1 = retval.get(0);
		return T1.getCorse();
	}
	
	public int getCorsePerse(){
		return this.chiamatePerse;
	}
	
	public ArrayList<InfoI> statisticheTaxi() {
		return (ArrayList<InfoI>) this.StatisticheTaxi.stream()
				.sorted((a, b) -> b.compareTo(a))
				.distinct().collect(Collectors.toList());
	}
	
	public ArrayList<InfoI> statisticheQuartieri() {
		return (ArrayList<InfoI>) this.StatisticheQuartieri.stream()
				.sorted((a, b) -> b.compareTo(a))
				.collect(Collectors.toList());
	}
}
