package facebook;

@SuppressWarnings("serial")
public class NoSuchCodeException extends Exception {
	public NoSuchCodeException() {
		System.out.println("Username non trovato!");
	}
}
