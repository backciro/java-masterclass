package abbigliamento;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Abbigliamento {		
	
	Collection<Collezione> Seasons = new LinkedList<Collezione>();
	
	private Collection<Capo> capi = new LinkedList<Capo>();
	
	private	Collection<Colore> colori = new HashSet<Colore>();
	private Collection<Modello> modelli = new HashSet<Modello>();
	private Collection<Materiale> materiali = new HashSet<Materiale>();
	
	public Abbigliamento(Collezione c) {
		this.Seasons.add(c);	
	}

	public void leggiFile(String fileName) {

		Boolean matchModel = false, matchColor = false, matchMaterial = false, matchCap = false;
		String[] bufferT;
		
		
		try {
			Scanner input = new Scanner(new File(fileName));
			
			while (input.hasNextLine()) {
				
				bufferT = input.nextLine().split(";");

				if (bufferT[0].equals("MOD")) {
					this.modelli.add( new Modello(bufferT[1], Double.parseDouble(bufferT[2]), Double.parseDouble(bufferT[3])) );
				}
				if (bufferT[0].equals("COL")) {
					this.colori.add( new Colore(bufferT[1]) );
				}
				if (bufferT[0].equals("MAT")) {
					this.materiali.add( new Materiale(bufferT[1], Double.parseDouble(bufferT[2])) );
				}
				
				if (bufferT[0].equals("CAP")) {
					
					Colore col = null;
					Modello mod = null;
					Materiale mat = null;
					
					for (Modello M : this.modelli)
						if (M.getNome().equals(bufferT[2])) {
							matchModel = Boolean.TRUE;
							mod = M;
						}
					
					for (Materiale M : this.materiali)
						if (M.getNome().equals(bufferT[3])) {
							matchMaterial = Boolean.TRUE;
							mat = M;
						}
					
					for (Colore C : this.colori)
						if(C.getNome().equals(bufferT[4])) {
							matchColor = Boolean.TRUE;
							col = C;
						}
					
					if (matchModel && matchColor && matchMaterial) {
						Capo C1 = new Capo(mod, mat, col);
						C1.setID(bufferT[1]);
						this.capi.add(C1);	
					}	
				}
				
				if (bufferT[0].equals("COLL")) {
					
					Collezione C1 = new Collezione();
					
					for (int i = 1; i < bufferT.length; i++) {
						matchCap = false;
						
						for (Capo P : this.capi) {
							if (P.getID().equals(bufferT[i])) {
								C1.add(P);
								matchCap = true;
							} 
						}
						if (!matchCap)
							System.out.println("Errore! Capo non presente in magazzino");
					}
					this.Seasons.add(C1);
				}	
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();	
		}	
	}
    	
	public Modello getModello(String name) {
		List<Modello> retval = new ArrayList<Modello>(1); 
		retval = this.modelli.stream().filter(a -> a.isModel(name)).limit(1).collect(Collectors.toList());

		return retval.get(0);
	}

	public Colore getColore(String name) {
		List<Colore> retval = new ArrayList<Colore>(1); 
		retval = this.colori.stream().filter(a -> a.isColor(name)).limit(1).collect(Collectors.toList());
		
		return retval.get(0);	
	}

	public Materiale getMateriale(String name) {		
		List<Materiale> retval = new ArrayList<Materiale>(1); 
		retval = this.materiali.stream().filter(a -> a.isMaterial(name)).limit(1).collect(Collectors.toList());

		return retval.get(0);	
	}

	public Capo getCapo(String name) {
		List<Capo> retval = new ArrayList<Capo>(1); 
		retval = this.capi.stream().filter(a -> a.isCape(name)).limit(1).collect(Collectors.toList());
		
		return retval.get(0);
	}

	public Collezione getCollezione(String name) {
		List<Collezione> retval = new ArrayList<Collezione>(1); 
		retval = this.Seasons.stream().filter(a -> a.isCollection(name)).limit(1).collect(Collectors.toList());
		
		return retval.get(0);
	}

}

