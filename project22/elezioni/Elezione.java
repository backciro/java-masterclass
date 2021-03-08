package elezioni;

import java.util.*;
import java.util.stream.Collectors;


public class Elezione {

	Collection<Cittadino> Elettorato = new LinkedList<Cittadino>();
	Collection<Lista> Liste = new LinkedList<Lista>();
	
	
	int voti;
	
	public Elezione(){
		this.Elettorato.clear();
		this.voti = 0;
	}
	
	public Cittadino aggiungiElettore(String nome, String cognome) {
		
		Elettore E1 = new Elettore(nome, cognome);
		
		this.Elettorato.add(E1);
		
		return E1;
	}
	
	public Collection<Cittadino> getElettori(){
		return this.Elettorato;
	}
	
	public Cittadino getElettore(String nome, String cognome){
		
		for (Cittadino C : this.Elettorato)
			if (C.getNome().equals(nome) && C.getCognome().equals(cognome))
				return C;
		
		return null;
	}
	
	public void registraLista(Lista lista){
		
		this.Liste.add(lista);
		
	}

    /**
     * Il cittadino votante esprime un voto per la lista ed 
     * un voto di preferenza per il candidato identificato
     * da nome e cognome
     * @throws TentatoDoppioVoto se il cittadino ha gi� votato
     * @throws TaglioNonPermesso se il candidato per cui si esprime
     * 							la preferenza non appartiene alla lista
     */	
	public void vota(Cittadino votante, String lista, String nome, String cognome)
		throws TentatoDoppioVoto, TaglioNonPermesso {
		
		boolean Taglio = true;
		
		for (Cittadino C1 : this.Elettorato)
			if (C1.equals(votante)) {
				if (C1.haVotato())
					throw new TentatoDoppioVoto(); //ARRESTO!
				
				for (Lista L : this.Liste)
					if (L.getNome().equals(lista)){
						
						if (L.getCapolista().getNome().equals(nome) && L.getCapolista().getCognome().equals(cognome)) {
							L.getCapolista().getVoto();
							Taglio = false;
						}
						
						for (Cittadino C2 : L.getCandidati())
							if ( (C2.getNome().equals(nome) && C2.getCognome().equals(cognome)) ) {
								C2.getVoto();
								Taglio = false;
							}
						
						if (Taglio)
							throw new TaglioNonPermesso(); // NE CAPOLISTA NE CANDIDATO
						
						L.voto();
						C1.vota();	
						this.voti++;
						break;
					}	
			}	 
	}

	public void vota(Cittadino votante, String lista)
		throws TentatoDoppioVoto{
		
		for (Cittadino C : this.Elettorato)
			if (C.equals(votante)) {
				if (C.haVotato())
					throw new TentatoDoppioVoto(); //ARRESTO!
				
				for (Lista L : this.Liste)
					if (L.getNome().equals(lista)) {
						L.voto();
						L.getCapolista().getVoto();
						C.vota();
						this.voti++;
						break;
					}	
			}	 
	}
	
	public long getNumeroVotanti(){
		return this.voti;
	}
	
	public Collection<Lista> getRisultatiListe(){
		return this.Liste.stream().sorted((a, b) -> Long.compare(b.getNumeroVoti(), a.getNumeroVoti())).collect(Collectors.toList());
	}

	public Collection<Cittadino> getRisultatiCandidati(){
		return this.Elettorato.stream().filter(C -> C.getNumeroVoti() > 0).sorted((a, b) -> Long.compare(b.getNumeroVoti(), a.getNumeroVoti())).collect(Collectors.toList());
	}
	
	
}
