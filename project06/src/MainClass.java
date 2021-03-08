
public class MainClass {

	public static void main(String[] args) {
	
		Heater h1 = new Heater(18,15,30);
		System.out.println("Temperatura attuale: " + h1.getTemperature());

		h1.warmer();
		System.out.println("Temperatura attuale: " + h1.getTemperature());

		h1.warmer();
		System.out.println("Temperatura attuale: " + h1.getTemperature());
		
		h1.warmer();
		System.out.println("Temperatura attuale: " + h1.getTemperature());
		
		h1.setIncrement(10);
		
		h1.cooler();
		System.out.println("Temperatura attuale: " + h1.getTemperature());

		h1.cooler();
		System.out.println("Temperatura attuale: " + h1.getTemperature());
		
		h1.cooler();
		System.out.println("Temperatura attuale: " + h1.getTemperature());
	}
}
