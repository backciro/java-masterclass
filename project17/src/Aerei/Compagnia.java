package Aerei;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Compagnia extends AeroportiMondo{

	public String CoName;
	
	private AeroportiMondo CoverSet;
	
	private List<Aereo> Airplanes = new LinkedList<Aereo>();
	private List<Aeroporto> Airports = new LinkedList<Aeroporto>();
	private List<Volo> Flights = new LinkedList<Volo>();

	// START Predicates DECLARATION //

	Predicate<Volo> bigRetardD = new Predicate<Volo>() {
		
		@Override
		public boolean test(Volo V) {
			return (V.getDepRet() > 15 ? true : false) ;
		}
	};	

	Predicate<Volo> bigRetardL = (Volo V) -> ( V.getLandRet() > 15 ? true : false ) ;
	
	Predicate<Volo> isCritical = (Volo V) -> ( V.percentFree() <= 30.0 ? false : true ) ;
	
	Predicate<Volo> isOnAir = (Volo V) -> ( V.Departed() ) ;

	// END Predicates DECLARATION //
	
	public Compagnia(String nome, AeroportiMondo am) {
		System.gc();
		
		this.CoName = nome;
		this.CoverSet = am;
		
		this.Airports = am.getAeroporti();
		this.Airplanes.clear();
		this.Flights.clear();
	}
	
	public void addAereo(String cod, int pass) 
			throws InvalidCode {
		
		Boolean isThere = Boolean.FALSE;
		
		for (Aereo A : this.Airplanes)
			if (A.getModel().equals(cod))
				isThere = Boolean.TRUE;
				
		if (isThere)
			throw new InvalidCode();
		else 
			this.Airplanes.add(new Aereo(cod, pass));
	}
	
	public List<String> getAerei() {
		return this.Airplanes.stream().map(Aereo::getModel).sorted((String a, String b) -> a.compareTo(b)).collect(Collectors.toList());
	}
	
	public void addVolo(String cod, String aer, String part, String arr, String giorno) 
			throws InvalidCode {
		
		Boolean d_AirportPresent = Boolean.FALSE;
		Boolean a_AirportPresent = Boolean.FALSE;
		Boolean PlanePresent = Boolean.FALSE;

		Aeroporto SRC = new Aeroporto(null);
		Aeroporto DST = new Aeroporto(null);
		
		Integer PlaneSeats = -1;;
		
		for (Aeroporto A : this.Airports) {
			if (A.getCode().equals(part)) {
				d_AirportPresent = Boolean.TRUE;
				SRC = A;
			}
			if (A.getCode().equals(arr)) {
				a_AirportPresent = Boolean.TRUE;
				DST = A;
			}
		}
		
		for (Aereo Ai : this.Airplanes)
			if (Ai.getModel().equals(aer)) {
				PlanePresent = Boolean.TRUE;
				PlaneSeats = Ai.getSeats();
			}
		
		if (d_AirportPresent && a_AirportPresent && PlanePresent) {
			Volo V1 = new Volo(cod, aer, part, arr, giorno);
			V1.setFreeSeats(PlaneSeats);
			V1.setTotalSeats(PlaneSeats);
			V1.brandFlight(this.CoName);
			
			this.Flights.add(V1);
			SRC.addPartenza(V1);
			DST.addArrivo(V1);
		}
		else
			throw new InvalidCode();
	}
	
	public void cancelVolo(String cod) 
			throws InvalidCode {
		
		String AirportS, AirportD;
		Boolean FlightPresent = Boolean.FALSE;
		
		for (Volo V : this.Flights)
			if (V.getFCode().equals(cod)) {
				FlightPresent = Boolean.TRUE;
				
				AirportS = V.getSource();
				AirportD = V.getDestination();
				
				this.CoverSet.getAeroporto(AirportS).deletePartenza(V);
				this.CoverSet.getAeroporto(AirportD).deleteArrivo(V);
				
				this.Flights.remove(V);
				V.finalize();
			}

		if (!FlightPresent)
			throw new InvalidCode();
	}
	
	public List<Volo> getVoli(){
		return this.Flights.stream().sorted((a, b) -> a.getFCode().compareTo(b.getFCode())).collect(Collectors.toList());
	}
	
	public boolean prenota(int num, String cod) 
			throws InvalidCode {
		
		Boolean ValidCode = Boolean.FALSE;
		
		for (Volo V : this.Flights)
			if (V.getFCode().equals(cod)) {
				if (this.postiLiberi(cod) >= num) 
					V.occupySeats(num);	
				else 
					return false;		
				
				ValidCode = Boolean.TRUE;
		}

		if (!ValidCode)
			throw new InvalidCode();
		
		return true;
	}
	
	public int postiLiberi(String cod) 
			throws InvalidCode {

		Boolean freeSpaceNotSet = Boolean.FALSE;
		Boolean flightPresent = Boolean.FALSE;
		
		Volo h = null;
		
		String planeCode = null;
		Integer totSpace = -1, freeSpace = -1;
		
		for (Volo V : this.Flights)
			if (V.getFCode().equals(cod)) {
				planeCode = V.getPCode();
				flightPresent = Boolean.TRUE;
				
				if (V.getFreeSeats() == -1) {
					freeSpaceNotSet = Boolean.TRUE;
					h = V;
				}
				
				else
					freeSpace = V.getFreeSeats();
			}
		
		for (Aereo A : this.Airplanes)
			if (A.getModel().equals(planeCode)) {
				totSpace = A.getSeats();
			
				if (freeSpaceNotSet) {
					h.setFreeSeats(totSpace);
				}
			}
		
		if (!flightPresent)
			throw new InvalidCode();
		
		if (freeSpaceNotSet)
			return totSpace;
		else
			return freeSpace;
	}
	
	public void partitoVolo(String cod, int ritardo) 
			throws InvalidCode {
		
		Boolean ValidCode = Boolean.FALSE;
		
		for (Volo V : this.Flights)
			if (V.getFCode().equals(cod)) {
				ValidCode = Boolean.TRUE;
				V.setDepRet(ritardo);
			}
		
		if (!ValidCode)
			throw new InvalidCode();	
	}
	
	public void arrivatoVolo(String cod, int ritardo) 
			throws InvalidCode {
		
		Boolean ValidCode = Boolean.FALSE;
		
		for (Volo V : this.Flights)
			if (V.getFCode().equals(cod)) {
				ValidCode = Boolean.TRUE;
				V.setLandRet(ritardo);
			}
		
		if (!ValidCode)
			throw new InvalidCode();
	}
		
	public List<String> ritardiPartenza() {
		return this.Flights.stream().filter(bigRetardD).map(Volo::getFCode).sorted((String a, String b) -> a.compareTo(b)).collect(Collectors.toList());
	}
	
	public List<String> ritardiArrivo() {
		return this.Flights.stream().filter(bigRetardL).map(Volo::getFCode).sorted((String a, String b) -> a.compareTo(b)).collect(Collectors.toList());
	}
	
	public List<String> voliCritici() {
		return this.Flights.stream().filter(isCritical).map(Volo::getFCode).sorted((String a, String b) -> a.compareTo(b)).collect(Collectors.toList());
	}
}
