package def;


import java.util.Collection;
import java.util.Iterator;

import elezioni.CandidatoNonValido;
import elezioni.Cittadino;
import elezioni.Elezione;
import elezioni.Lista;
import elezioni.TaglioNonPermesso;
import elezioni.TentatoDoppioVoto;

public class EsempioMain {

	public static void main(String[] args){
		Elezione e = new Elezione();
		e.aggiungiElettore("Giuseppe","Verdi");
		e.aggiungiElettore("Giovanni","Bianchi");
		e.aggiungiElettore("Mario","Rossi");
		
	  	String nome = "Lista 1";
	  	String motto = "Ad Maiora";
	  	Lista l = new Lista(nome,motto);
	  	
		Cittadino c = e.getElettore("Giuseppe","Verdi");
		try{
			l.assegnaCapolista(c);
		}
		catch(CandidatoNonValido cnv){
			System.out.println("Candidato non valido");
		}
		try{
			l.aggiungiCandidato(e.getElettore("Mario","Rossi"));
		}
		catch(CandidatoNonValido cnv){
			System.out.println("Candidato non valido");
		}

		e.registraLista(l);		
		Cittadino capo = l.getCapolista();
		System.out.println(l.getNome()+" - capolista: "+capo.getCognome());
		
		try{
			e.vota(c,"Lista 1");
			e.vota(c,"Lista 1","Mario","Rossi");
		}
		catch(TaglioNonPermesso ex){
			System.out.println("Taglio non permesso");
		}
		catch(TentatoDoppioVoto dv){
			System.out.println("Tentato doppio voto");
		}
		
		Collection<Lista> liste = e.getRisultatiListe();
		for (Iterator<Lista> iter = liste.iterator(); iter.hasNext();) {
      		Lista lista = (Lista) iter.next();
      		System.out.println(lista.getNome()+" - voti: "+lista.getNumeroVoti());
    	}
		
		Collection<Cittadino> candidati = e.getRisultatiCandidati();

		for (Iterator<Cittadino> iter = candidati.iterator(); iter.hasNext();) {
			Cittadino candidato = (Cittadino) iter.next();
			System.out.println(candidato.getCognome()+" - voti: "+candidato.getNumeroVoti());
		}
		
	}

}
