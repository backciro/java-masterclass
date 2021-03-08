package elezioni;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Lista {
	
	String nomeL, mottoL;
	long votiLista;
	
	Collection<Cittadino> Membri = new LinkedList<Cittadino>();
	
	Predicate<Cittadino> Capolista = C -> C.isCapolista();
	Predicate<Cittadino> Candidato = C -> C.isCandidato();
	

	public Lista(String nome, String motto){
		this.mottoL = motto;
		this.nomeL = nome;
		this.votiLista = 0;
	}
	
	public String getNome(){
		return this.nomeL;
	}

	public String getMotto(){
		return this.mottoL;
	}
	
	public void voto() {
		this.votiLista++;
	}
	
	public void assegnaCapolista(Cittadino capolista)
			throws CandidatoNonValido {
		
		if (this.Membri.contains(capolista) || capolista.isCapolista() || capolista.isCandidato())
			throw new CandidatoNonValido();
		
		capolista.setCapolista();
		this.Membri.add(capolista);
	}

	public void aggiungiCandidato(Cittadino candidato)
			throws CandidatoNonValido {
		
		if (this.Membri.contains(candidato) || candidato.isCandidato() || candidato.isCapolista())
				throw new CandidatoNonValido();
		
			candidato.setCandidato();
			this.Membri.add(candidato);
	}

	public Cittadino getCapolista(){

		for (Cittadino C : this.Membri)
			if (C.isCapolista())
				return C;
		return null;
	}

	public Collection<Cittadino> getCandidati(){
		return this.Membri.stream().filter(Candidato).collect(Collectors.toList());
	}
	
	
	public long getNumeroVoti(){
		return this.votiLista;
	}

	public double getPercentualeVoti(){

		return -1.1;
	}
}
