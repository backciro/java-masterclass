
public class TicketMachine {
	// A model of ticket machine that issues flat fare tickets

	int TC; //ticketCost
	int TA; //totalAmount
	int CA; //currentAmount
	
	int id; static int idify = 0; // system for tagging all the machines
		  
	public TicketMachine(int ticketCost) {
		this.TC = ticketCost; 	// cost of the ticket
		this.TA = 0;			// tatol amount collected so far
		this.CA = 0;			// current amount collected
		this.id = ++idify;		// temporal-increasing variable
	}
	
	// Return the total amount of money collected by this machine
	public int getTotal() {
		return this.TA;
	}
	
 	// Receive an amount of money from a customer. Check that it is sensible. 
	public void insertMoney(int amount) {
		
		if (amount <= 0)
			System.out.println("INSERT MONEY");
		else {
			this.CA += amount;
			System.out.println(); System.out.println();
			System.out.println("~~~ "+ amount +",00 EUROs INSERTED ~~~");
			System.out.print("~~~ ID TICKETMACHINE: tm"+ this.id +" ~~~");
			System.out.println(); System.out.println();
		}
    }

    /**
     * Print a ticket if enough money has been inserted, and
     * reduce the current balance by the ticket price. Print
     * an error message if more money is required.
     */
    public void printTicket()
    {
    	if (this.CA >= this.TC) {
    		
    		System.out.println();
    		for (int i = 0; i < 32; i++) 
    			System.out.print("/"); System.out.println();
    		for (int i = 0; i < 32; i++) 
    			System.out.print("*");
    		System.out.println(); System.out.println();
    		
    		System.out.println("~~~ VALID TICKET FOR TRANSIT ~~~");
    		System.out.println("~~~ TOTAL COST: EUROs "+ this.TC +",00 ~~~");
    		
    		System.out.println(); System.out.println();
    		
    		// print and returning the rest
    		System.out.println("~~~~~~ REST: EUROs "+ (this.CA - this.TC) + ",00 ~~~~~~~");
    		
    		this.CA = 0; // update current amount
    		this.TA += this.TC; // update total amount collected

    		System.out.println();
    		for (int i = 0; i < 32; i++) 
    			System.out.print("*"); System.out.println();
    		for (int i = 0; i < 32; i++) 
    			System.out.print("/");
    		System.out.println(); System.out.println();
    	}
    		
    	else {
    		System.out.println(); 
    		System.out.println("NOT ENOUGH MONEY FOR THE TRANSACTION!");
    		System.out.println((this.TC - this.CA) + ",00 EUROs LEFT FOR BUY TICKET");
    	}
    }
}