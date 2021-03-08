package it.polito.vm;

import java.util.*;

public class VendingMachine extends ControlPanel{
	
	public HashMap<String, Double> beverages = new HashMap<String, Double>();
	public HashMap<Integer, Double> IDcards = new HashMap<Integer, Double>();
	private HashMap<Integer, ControlPanel> board = new HashMap<Integer, ControlPanel>();
	public Integer Columns; 

	public VendingMachine() {

			this.IDcards.clear();
			this.beverages.clear();
								// a parameter could be received (or manual settled)
			this.Columns = 4; 	// for chose the number of columns
								// ...
			for (int i = 1; i <= this.Columns; i++)
				this.board.put(i, new ControlPanel());
	}

	public void addBeverage(String name, double price) {
		this.beverages.put(name, price);
	}

	public double getPrice(String beverageName) {
	
		if (this.beverages.containsKey(beverageName))
			return this.beverages.get(beverageName);
		else		
			return -1.0;
	}

	public void rechargeCard(int cardId, double credit) {
		if (this.IDcards.containsKey(cardId))
			this.IDcards.replace(cardId, (this.IDcards.get(cardId) + credit));

		else
			this.IDcards.put(cardId, credit);
	}

	public double getCredit(int cardId) {
		if (this.IDcards.containsKey(cardId))
			return this.IDcards.get(cardId) ;
		else 
			return -1.0;
	}

	public void refillColumn(int column, String beverageName, int cans) {
		this.board.get(column).refill(beverageName, cans);
	}

	public int availableCans(String beverageName) {
		
		int avl = -1; 
		boolean chckd = false;
		
		for (int i = 1; i <= this.Columns; i++)
			if (this.board.get(i).idCompare(beverageName)) {
				avl += this.board.get(i).availablity();
				chckd = true;
			} if (chckd) avl++;
		
		return avl ;
	}

	public int sell(String beverageName, int cardId) {
		
		if (this.getCredit(cardId) >= this.getPrice(beverageName)) 
		{ 
			for (int i = 1; i <= this.Columns; i++) 
			{	
				if (this.board.get(i).idCompare(beverageName) && this.board.get(i).authorize() )
				{
					this.rechargeCard( cardId, (- this.getPrice(beverageName)) );
					return i;
				}
			}
			
			System.out.println("Bevanda Esaurita\n");
		}
		
		else 
			System.out.println("Credito Insufficiente\n");
		
		return -1;
	}
}
