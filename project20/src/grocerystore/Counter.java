package grocerystore;

public interface Counter {
	
	// following attributes are public, static, and final, implicitly
	
	double VAT = 0.2; 
	
	
	// following methods are public and abstract, implicitly
	
	void read(String code, int pieces) throws NoSuchProduct;
	void print();
	void close();
	double total();
	double gross();
	double taxes();
}
