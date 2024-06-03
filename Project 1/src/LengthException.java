/**
 * Exception class, thrown when the password is too short
 * @author Dolev Peleg
 */
public class LengthException extends Exception
{
	/**
	 * Default constructor
	 */
	public LengthException() 
	{
		super("The password must be at least 6 characters long");
	} // End default constructor
	
	/**
	 * Constructor with string parameter
	 * @param String message
	 */
	public LengthException(String message)
	{
		super(message);
	} // End Constructor
} // End LengthException
