package ticketing;

import java.util.*;
import java.util.stream.Collectors;

public class Ticket {
	
	private Utente User;
	private Prodotto Product;
	private String Label;
	
	private Tracker Tracking;
	
	private Collection<Commento> Topics = new ArrayList<Commento>(); 

	private long Code;
	private long timeStamp;
	private static long idify = 1;
	
	public Ticket(Utente user, Prodotto prod, Tracker tracker, String label) {
		this.User = user;
		this.Product = prod;
		this.Label = label;
		
		this.Tracking = tracker;
		
		this.Code = Ticket.idify++;
		this.timeStamp = System.currentTimeMillis();
	}

    public long getCodice(){
        return this.Code;
    }
    public Utente getCreatore() {
        return this.User;
    }
    public Prodotto getProdotto() {
        return this.Product;
    }
    public String getEtichetta() {
        return this.Label;
    }
    public long getTimestamp() {
        return this.timeStamp;
    }
    
    public Commento nuovoCommento(String nick, String testo){
    	
    	List<Utente> UL = new ArrayList<Utente>(1);
    	UL = this.Tracking.Users.stream().filter(a -> a.getNickname().equals(nick)).limit(1).collect(Collectors.toList());
    	
    	Commento C1 = new Commento(nick, testo, this, UL.get(0));
    	this.Topics.add(C1);
    	
    	return C1;
    }

    public List<Commento> getCommenti(){
        return this.Topics.stream().sorted((a, b) -> Long.compare(b.getTimestamp() , a.getTimestamp())).collect(Collectors.toList());
    }
}
