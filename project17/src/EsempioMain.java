import java.util.Collection;
import Aerei.*;

public class EsempioMain {

	public static void main(String[] args) {
		AeroportiMondo amAZ = new AeroportiMondo();
		AeroportiMondo amLU = new AeroportiMondo();
		Aeroporto a1 = new Aeroporto("TorinoTRN");
		Aeroporto a2 = new Aeroporto("ParisCDG");
		Aeroporto a3 = new Aeroporto("LondonGTW");
		Compagnia al = new Compagnia("Alitalia", amAZ);
		Compagnia lu = new Compagnia("Lufthansa", amLU);
		
		try {
			al.addAereo("ZZ8", 85);
			al.addAereo("B738", 90);
			al.addAereo("MD80", 85);
		} catch (InvalidCode e) {
			e.printStackTrace();
		}

		for(String s : al.getAerei())
			System.out.println(s);
	
		try {
			amAZ.addAeroporto(a1);
			amAZ.addAeroporto(a2);
			amAZ.removeAeroporto("ParisCDG");
			amAZ.addAeroporto(a2);			
			amAZ.addAeroporto(a3);
			amLU.addAeroporto(a1);
			amLU.addAeroporto(a2);
			
			al.addAereo("L734", 120);
			al.addAereo("E95", 70);
			for(String s : al.getAerei())
				System.out.println(s);
			
			al.addVolo("AZ321", "L734", "TorinoTRN", "ParisCDG", "Martedi");
			al.addVolo("AZ123", "E95", "LondonGTW", "TorinoTRN", "Lunedi");
			al.cancelVolo("AZ123");
			
			if(al.prenota(110, "AZ321"))
				System.out.println("Prenotazione effettuata");
			else
				System.out.println("Posti richiesti non disponibili");
			System.out.println("Posti liberi: "+ al.postiLiberi("AZ321"));
			
			lu.addAereo("L734", 120);
			lu.addAereo("CR9", 60);

			lu.addVolo("LH274", "CR9", "ParisCDG", "TorinoTRN", "Giovedi");
			lu.addVolo("LH273", "L734", "ParisCDG", "TorinoTRN", "Venerdi");
		
			if(lu.prenota(60, "LH273")) //120
				System.out.println("Prenotazione effettuata");
			else
				System.out.println("Posti richiesti non disponibili");
			System.out.println("Posti liberi: "+ lu.postiLiberi("LH273"));
			
			lu.prenota(30, "LH274"); //60
			
			lu.partitoVolo("LH273", 20);
			lu.partitoVolo("LH274", 10);
			
			lu.arrivatoVolo("LH273", 30);
			lu.arrivatoVolo("LH274", 18);
		}
		catch (InvalidCode ic){
			ic.printStackTrace();
		}
		
		System.out.println("\nVoli Lufthansa:");
		for(Volo v : lu.getVoli())			
			System.out.println("   "+v);
		
		print("Partenze", "Torino", amAZ.getAeroporto("TorinoTRN").getPartenze());	
		print("Arrivi", "Torino", amAZ.getAeroporto("TorinoTRN").getArrivi());		
		print("Partenze", "Parigi", amAZ.getAeroporto("ParisCDG").getPartenze());		
		print("Arrivi", "Parigi", amAZ.getAeroporto("ParisCDG").getArrivi());
		print("Partenze", "Londra", amAZ.getAeroporto("LondonGTW").getPartenze());
		
		System.out.println("\nRitardi partenza:");
		for(String c : lu.ritardiPartenza())			
			System.out.println("   "+c);
		
		System.out.println("\nRitardi arrivo:");
		for(String c : lu.ritardiArrivo())			
			System.out.println("   "+c);
		
		System.out.println("\nVoli critici:");
		for(String c : lu.voliCritici())			
			System.out.println("   "+c);
	}
	
	public static void print(String ap, String cod, Collection<Volo> voli){
		System.out.println("\n"+ap+" Aeroporto di " + cod + ":");
		for(Volo v : voli)
			System.out.println("   "+v);
	}
}
