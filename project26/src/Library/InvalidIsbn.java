package Library;

@SuppressWarnings("serial")
public class InvalidIsbn extends Exception {
	
	public InvalidIsbn() {
		System.out.println("Invalid ISBN Code");
	}
}
