/**
 * Exception class for when the password has a character repeating at least twice
 * @author Dolev Peleg
 */
public class InvalidSequenceException extends Exception
{
	/**
	 * Default constructor
	 */
	public InvalidSequenceException() 
	{
		super("The password cannot contain more than two of the same character in sequence");
	} // End default constructor
	
	/**
	 * Constructor with string parameter
	 * @param String message
	 */
	public InvalidSequenceException(String message)
	{
		super(message);
	} // End Constructor
} // End InvalidSequenceException
