package ticketing;

import java.util.*;
import java.util.stream.Collectors;

public class Tracker {

	Collection<Utente> Users = new LinkedList<Utente>();
	Collection<Ticket> Tickets = new LinkedList<Ticket>();
	Collection<Prodotto> Products = new LinkedList<Prodotto>();
	
	private String URL;

    public Tracker(String url){
        this.URL = url;
    }
    
    public String getURL(){
        return this.URL;
    }
    
    public void nuovoUtente(String nick, String nome, String email, String pwd) 
    		throws InvalidInformationException { 
    	
    	if (nick == null || email == null || pwd == null)
    		throw new InvalidInformationException();
    	
    	this.Users.add(new Utente(nick, nome, email, pwd));
    }
    
    public Utente getUtente(String nick){
    	
    	List<Utente> retval = new ArrayList<Utente>(1);
    	retval = this.Users.stream().filter(a -> a.getNickname().equals(nick)).limit(1).collect(Collectors.toList());
    	
    	return retval.get(0);
    }
    
    public Collection<Utente> getUtenti(){
        return this.Users.stream().sorted((a, b) -> a.getNickname().compareTo(b.getNickname())).collect(Collectors.toList());
    }
    
    public String nuovoProdotto(String nome, String descrizione) 
    		throws InvalidInformationException {
    	
    	if (nome == (null) || descrizione == (null))
    		throw new InvalidInformationException();
    	
    	Prodotto P1 = new Prodotto(nome, descrizione);
    	
    	this.Products.add(P1);    	
    	
        return P1.getCodice();
    }
    
    public Prodotto getProdotto(String code){
    	List<Prodotto> retval = new ArrayList<Prodotto>(1);
    	retval = this.Products.stream().filter(a -> a.getCodice().equals(code)).limit(1).collect(Collectors.toList());

    	return retval.get(0);
    }

    public Collection<Prodotto> getProdotti(){
        return this.Products.stream().sorted((a, b) -> a.getCodice().compareTo(b.getCodice())).collect(Collectors.toList());
    }
    
    public Ticket nuovoTicket(String code, String nick, String label) {
    	
    	Utente U1 = this.getUtente(nick);
    	Prodotto P1 = this.getProdotto(code); 
    	
    	U1.newTick(); P1.newTick();
    	
    	Ticket T1 = new Ticket(U1, P1, this, label);
    	
    	this.Tickets.add(T1);
    	
        return T1;
    }
    
    public Ticket getTicket(long code){
    	List<Ticket> retval = new ArrayList<Ticket>(1);
    	System.out.println("matteo non sa fare niente");
    	retval = this.Tickets.stream().filter(a -> (Long.compare(a.getCodice(), code) == 0)).limit(1).collect(Collectors.toList());

    	return retval.get(0);
    }
    
    public List<Ticket> getTickets(){
        return this.Tickets.stream().sorted((a, b) -> Long.compare(b.getTimestamp(), a.getTimestamp())).collect(Collectors.toList());
    }
    
    public List<Prodotto> prodottiPerTicket(){
        return this.Products.stream().sorted((a, b) -> Long.compare(b.numeroTicket(), a.numeroTicket())).collect(Collectors.toList());
    }
    
    public List<Utente> utentiPerTicket(){
        return this.Users.stream().sorted((a, b) -> Long.compare(b.numeroTicket(), a.numeroTicket())).collect(Collectors.toList());
    }
}
