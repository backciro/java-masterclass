package Aerei;

public class Aereo {
	
	String model;
	Integer seats;
	
	public Aereo(String cod, int s) {
		this.model = cod;
		this.seats = s;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public Integer getSeats() {
		return this.seats;
	}
	
	public String toString() {
		return "Model: " + this.model + "  Seats: " + this.seats;
	}	
}