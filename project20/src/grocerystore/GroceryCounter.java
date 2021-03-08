package grocerystore;

import java.util.*;

public class GroceryCounter implements Counter {
	
	Collection<Product> stock = new LinkedList<Product>();
	
	List<Product> cart = new LinkedList<Product>();
	
	Comparator<Product> pComp = (a, b) -> a.getDescription().compareTo(b.getDescription());

	public void addProduct(String code, String desc, double price) {
		this.stock.add(new Product(code, desc, price));
	}

	public void promo(String code, int discount) {
		
		for (Product P : this.stock)
			if (P.getCode().equals(code))
				P.setDiscount(discount);
		
	}

	public void read(String code, int pieces) throws NoSuchProduct{
		
		boolean exist = false;

		for (Product P : this.stock)
			if (code.equals(P.getCode())) {
				for (int i = 0 ; i < pieces; i++)	
					this.cart.add(P);
				exist = true;
			}
		
		if (!exist)
			throw new NoSuchProduct();
		
	}
	
	public double total() {
		
		double total = 0;
		
		for (Product L : this.cart)
			total += L.getPrice();
		
		return total;
	}

	public double gross() {
		
		double gross = 0;
		
		for (Product L : this.cart)
			gross += L.getGrossPrice();
		
		return gross;
	}
	
	public double toDiscount() {
		
		double toDiscount = 0;
		
		for (Product L : this.cart)
			toDiscount += L.getNetPrice();
		
		return toDiscount;
		
	}

	public double taxes() {
	
		double taxes = 0;
		
		for (Product L : this.cart)
			taxes += L.getNetPrice();
		
		return (taxes/100 * 20); //20 as IVA on 20%
	}

	public void print() {
		
		System.out.println("**** MINI MARKET ****");

		Collections.sort(this.cart, pComp);
		
		for (Product P : this.cart) {
			System.out.println(P.getDescription() + "\tprice : " + P.getPrice());
		}
		System.out.println();
		System.out.printf("total : %.2f \nyou have saved : %.2f :)", this.total() , (this.toDiscount() - this.total()) );		
	}
	
	public void close() {
		this.cart.clear();
	}
}
