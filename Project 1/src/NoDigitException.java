/**
 * Exception class for when the password does not contain a digit
 * @author Dolev Peleg
 */public class NoDigitException extends Exception
{
	/**
	 * Default constructor
	 */
	public NoDigitException() 
	{
		super("The password must contain at least one digit");
	} // End default constructor
	
	/**
	 * Constructor with string parameter
	 * @param String message
	 */
	public NoDigitException(String message)
	{
		super(message);
	} // End Constructor
	
} // End NoDigitException
