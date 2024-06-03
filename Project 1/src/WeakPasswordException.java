/**
 * Exception class for when the password is between 6 and 10 characters
 * @author Dolev Peleg
 */
public class WeakPasswordException extends Exception
{
	/**
	 * Default constructor
	 */
	public WeakPasswordException() 
	{
		super("The password is OK but weak - it contains fewer than 10 characters.");
	} // End default constructor
	
	/**
	 * Constructor with string parameter
	 * @param String message
	 */
	public WeakPasswordException(String message)
	{
		super(message);
	} // End Constructor
	
} // End WeakPasswordException
