/**
 * Exception class, thrown when top or pop method is called on an empty stack.
 * @author Dolev Peleg
 */
public class StackUnderflowException extends Exception
{
	/**
	 * Default constructor
	 */
	public StackUnderflowException()
	{
		super("top or pop method was called on an empty stack");
	}
	
	/**
	 * Parameterized constructor
	 * @param message
	 */
	public StackUnderflowException(String message)
	{
		super(message);
	}
}
