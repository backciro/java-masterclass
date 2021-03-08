package lists;

public class EmptyListException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyListException()
	{
		this( "List" );   // call other EmptyListException constructor
	}  

	public EmptyListException( String name )
	{
		super( name + " is empty" );  // call superclass constructor
		System.out.println("\nWARNING! STACK IS EMPTY\nEmptyListExeption Detected!");
	}
}
