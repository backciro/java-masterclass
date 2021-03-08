package ticketing;

public class Commento {
	
	
	private Utente Author;
	private Ticket Problem;
	private String Comment;
	
	private long timeStamp;

	public Commento(String nick, String text, Ticket ticket, Utente u1) {
		
		this.Author = u1;
		this.Problem = ticket;
		this.Comment = text;
		
		this.timeStamp = System.currentTimeMillis();
	}
	
    public Utente getAutore() {
        return this.Author;
    }
    public Ticket getTicket() {
        return this.Problem;
    }
    public String getTesto() {
        return this.Comment;
    }
    public long getTimestamp() {
        return this.timeStamp;
    }
}
