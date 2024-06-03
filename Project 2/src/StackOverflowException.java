/**
 * Exception class, thrown when a push method is called on a full stack.
 * @author Dolev Peleg
 */
public class StackOverflowException extends Exception
{
	/**
	 * Default constructor
	 */
	public StackOverflowException()
	{
		super("push method was called on a full stack");
	}
	
	/**
	 * Parameterized constructor
	 * @param message
	 */
	public StackOverflowException(String message)
	{
		super(message);
	}
}
