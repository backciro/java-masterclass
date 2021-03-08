package tollroad;

@SuppressWarnings("serial")
public class CustomerNotPresent extends Exception {
       
	public CustomerNotPresent() {
		System.out.println("CUSTOMER NOT FOUND IN DATABASE");
	}
	
}
