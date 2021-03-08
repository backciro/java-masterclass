package ticketing;

public class Prodotto {
	
	private String ID, Descrizione, Code;
	
	private int nTicks;
	
	private static int idify = 1;

	public Prodotto(String id, String descr) {
		this.ID = id;
		this.Descrizione = descr;
		this.Code = "P" + Prodotto.idify++;
		this.nTicks = 0;
	}
	
    public String getNome(){
        return this.ID;
    }

    public String getDescrizione(){
        return this.Descrizione;
    }

    public String getCodice(){
        return this.Code;
    }
    
    public void newTick() {
    	this.nTicks++;
    }
    
    public long numeroTicket(){
        return this.nTicks;
    }
    
}
