package gestioneTaxi;

import java.util.*;

public class Taxi implements InfoI{
	
	TaxiCompany CompanyReference;
	
	String ID;
	Passeggero Cliente;
	
	Luogo Partenza, Arrivo;
	Boolean occupato;
	
	int nCorse;
	
	List<Corsa> Corse = new LinkedList<Corsa>();

	public Taxi(String id) {
		this.ID = id;
		this.occupato = false;
		this.nCorse = 0;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public void setCliente(Passeggero p) {
		this.Cliente = p;
		this.occupato = true;
	}
	
	public void setPR(TaxiCompany t) {
		this.CompanyReference = t;
	}
	
	public List<Corsa> getCorse() {
		return this.Corse;
	}
	
	public String toString(){
		return this.ID;
	}
	
	public void inizioCorsa(Luogo destinazione) {
		this.Partenza = this.Cliente.getPosizione();
		this.Arrivo = destinazione;
	}
	
	public void fineCorsa() {
		this.occupato = false;
		this.Cliente.setPosizione(this.Arrivo);

		this.Arrivo.increment();
		this.increment();
		
		if (this.checkSQ()) 
			this.CompanyReference.StatisticheQuartieri.add(this.Arrivo);
		
		this.Corse.add(new Corsa(this.Partenza, this.Arrivo));
		
		this.Cliente =  null;
		this.Partenza = this.Arrivo = null;
		
		this.CompanyReference.enqueueTaxi(this);
	}

	private boolean checkSQ() {
		
		for (InfoI I : this.CompanyReference.StatisticheQuartieri)
			if (I.getId().equals(this.Arrivo.getId())) {
				I.increment();
				return false;
			}
		
		return true;
	}

	@Override
	public int compareTo(InfoI o) {
		if (this.nCorse < o.getValore())
			return -1;
		else if (this.nCorse > o.getValore())
			return 1;
		else 
			return o.getId().compareTo(this.ID);
	}

	@Override
	public String getId() {
		return this.ID;
	}

	@Override
	public int getValore() {
		return this.nCorse;
	}

	@Override
	public void increment() {
		this.nCorse++;
	}
}
