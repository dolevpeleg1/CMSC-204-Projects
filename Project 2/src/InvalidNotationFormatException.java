/**
 * Exception class, thrown when a Notation format is incorrect.

 * @author Dolev Peleg
 */
public class InvalidNotationFormatException extends Exception
{
	/**
	 * Default constructor
	 */
	public InvalidNotationFormatException()
	{
		super("Invalid notation format");
	}
	
	/**
	 * Parameterized constructor
	 * @param message
	 */
	public InvalidNotationFormatException(String message)
	{
		super(message);
	}
}
