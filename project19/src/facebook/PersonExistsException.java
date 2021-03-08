package facebook;

@SuppressWarnings("serial")
public class PersonExistsException extends Exception {
	public PersonExistsException() {
		System.out.println("Utente gia\' presente nel database!");
	}

}
