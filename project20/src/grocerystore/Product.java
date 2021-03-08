package grocerystore;

public class Product {

	String code, descr;
	double price;
	
	boolean isDiscounted ;
	double discountPercent ;
	
	public Product(String c, String d, double p) {
		this.code = c;
		this.descr = d;
		this.price = p;
		
		this.isDiscounted = false;
	}
	
	public void setDiscount(double d) {
		this.isDiscounted = true;
		this.discountPercent = d;
	}
	
	public double getPrice() {
		if (this.isDiscounted)
			return (this.price - (this.discountPercent/100 * this.price));
		else 
			return this.price;
	}
	
	public double getNetPrice() {
		return this.price;
	}
	
	public double getGrossPrice() {
		return this.price * 1.2;
	}
	
	public String getDescription() {
		return this.descr;
	}
	
	public String getCode() {
		return this.code;
	}
	
	
	

} 

