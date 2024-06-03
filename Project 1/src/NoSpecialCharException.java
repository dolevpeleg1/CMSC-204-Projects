/**
 * Exception class for when the password does not contain a special character
 * @author Dolev Peleg
 */
public class NoSpecialCharException extends Exception
{
	/**
	 * Default constructor
	 */
	public NoSpecialCharException() 
	{
		super("The password must contain at least one special character");
	} // End default constructor
	
	/**
	 * Constructor with string parameter
	 * @param String message
	 */
	public NoSpecialCharException(String message)
	{
		super(message);
	} // End Constructor
} // End noSpecialCharExpception
