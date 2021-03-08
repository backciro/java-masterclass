package lists;

public class InvalidExpression extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidExpression()
	{
		this( "InvExpress" );   // call other EmptyListException constructor
	}  

	public InvalidExpression( String name )
	{
		super( name );  // call superclass constructor
		System.out.println("\nWARNING! SYNTAX EXPRESSION NOT RECOGNISED\nInvalidExpression Detected!");
	}

}
