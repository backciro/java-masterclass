package ordini;

public class Prodotto implements InfoI {
	
	private String ID;
	private Integer Price;
	
	private int selledItems;
	
	public Prodotto(String... args) {
		this.ID = args[1];
		this.Price = Integer.parseInt(args[2]);
		this.selledItems = 0;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public Integer getPrice() {
		return this.Price;
	}
	
	public String toString() {
		return this.ID + ", " + this.Price;
	}
	
	public void sellItem(int a) {
		this.selledItems += a;
	}

	@Override
	public String getCodice() {
		return this.ID;
	}

	@Override
	public int getValore() {
		return this.selledItems;
	}
}
