/**
 * Exception class for when the password does not contain an upper case character
 * @author Dolev Peleg
 */
public class NoUpperAlphaException extends Exception
{
	/**
	 * Default constructor
	 */
	public NoUpperAlphaException() 
	{
		super("The password must contain at least one uppercase alphabetic character");
	} // End default constructor
	
	/**
	 * Constructor with string parameter
	 * @param String message
	 */
	public NoUpperAlphaException(String message)
	{
		super(message);
	} // End Constructor
	
} // End NoUpperAlphaException
