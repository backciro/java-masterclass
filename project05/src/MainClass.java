
public class MainClass {

	public static void main(String[] args) {
		
		TicketMachine tm1 = new TicketMachine(90);
		tm1.insertMoney(100);
		tm1.printTicket();
		
		System.out.println("Total amount collected so far: " + tm1.getTotal() + ",00 euros; from tm1");
		
		TicketMachine tm2 = new TicketMachine(120);
		tm2.insertMoney(100);
		tm2.printTicket();
		tm2.insertMoney(50);
		tm2.printTicket();
	
		System.out.println("Total amount collected so far: " + tm2.getTotal() +",00 euros; from tm2");
        
		tm2.insertMoney(140);
		tm2.printTicket();
	
		System.out.println("Total amount collected so far: " + tm2.getTotal() +",00 euros; from tm2");
	}
}
