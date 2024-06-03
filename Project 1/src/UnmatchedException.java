/**
 * Exception class, throws when the passwords do not match
 * @author Dolev Peleg
 */
public class UnmatchedException extends Exception
{
	/**
	 * Default constructor
	 */
	public UnmatchedException() 
	{
		super("Passwords do not match");
	} // End default constructor
	
	/**
	 * Constructor with string parameter
	 * @param String message
	 */
	public UnmatchedException(String message)
	{
		super(message);
	} // End Constructor
	
} // End UnmatchedException
