import it.polito.vm.VendingMachine;

public class MainClass {
	
	public static void main(String[] args) {
		
		VendingMachine vm = new VendingMachine();
		
		vm.addBeverage("Coca", 0.50);
		vm.addBeverage("Water", 0.30);
		vm.addBeverage("Beer", 1.00);
		
		vm.refillColumn(1, "Coca", 1);
		vm.refillColumn(2, "Beer", 10);
		vm.refillColumn(3, "Coca", 15);
		vm.refillColumn(4, "Water", 20);
		
		System.out.println("Coca: "+vm.availableCans("Coca"));
		System.out.println("Coca price: "+vm.getPrice("Coca"));
		
		System.out.println();
		
		vm.rechargeCard(12, 5.5);
		vm.rechargeCard(21, 10.0);
		vm.rechargeCard(99, 0.75);
		
		vm.sell("Water", 12);
		vm.sell("Coca", 12);
		vm.sell("Coca", 99);
		vm.sell("Beer", 21);
		vm.sell("Beer", 21);
		vm.sell("Beer", 21);
		vm.sell("Beer", 21);		
		vm.sell("Beer", 21);
		vm.sell("Beer", 21);		
		vm.sell("Beer", 21);
		vm.sell("Beer", 21);		
		vm.sell("Beer", 21);
		vm.sell("Beer", 21);
		vm.sell("Beer", 12); // bevanda esaurita
		vm.sell("Beer", 21); // credito insufficiente
		
		System.out.println("Coca: "+vm.availableCans("Coca"));
		System.out.println("Beer: "+vm.availableCans("Beer"));
		System.out.println("Water: "+vm.availableCans("Water"));
		
		System.out.println();
		
		System.out.println("Credit 12: "+vm.getCredit(12));
		System.out.println("Credit 99: "+vm.getCredit(99));
		System.out.println("Credit 21: "+vm.getCredit(21));
		
	}
}
