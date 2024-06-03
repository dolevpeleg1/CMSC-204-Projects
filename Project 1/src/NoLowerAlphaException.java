/**
 * Exception class for when the password does not contain a lower case character
 * @author Dolev Peleg
 */
public class NoLowerAlphaException extends Exception
{
	/**
	 * Default constructor
	 */
	public NoLowerAlphaException() 
	{
		super("The password must contain at least one lowercase alphabetic character");
	} // End default constructor
	
	/**
	 * Constructor with string parameter
	 * @param String message
	 */
	public NoLowerAlphaException(String message)
	{
		super(message);
	} // End Constructor
	
} // End NoLowerAlphaException
