package ticketing;

public class Utente {
	
	private String Nickname, Nome, eMail, Password;
	private int nTicket;
	
	public Utente(String Nn, String N, String eM, String Pass) {
		this.Nickname = Nn;
		this.Nome = N;
		this.eMail = eM;
		this.Password = Pass;
		
		this.nTicket = 0;
	}

    public String getNickname(){
        return this.Nickname;
    }

    public String getName(){
        return this.Nome;
    }
    
    public String getEmail(){
        return this.eMail;
    }
    
    public boolean authenticate(String pwd){
        return this.Password.equals(pwd);
    }
    
    public void newTick() {
    	this.nTicket++;
    }

    public long numeroTicket(){
        return this.nTicket; 
    }
    
}
