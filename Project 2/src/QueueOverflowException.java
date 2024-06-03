/**
 * Exception class, thrown when a enqueue method is called on a full queue.
 * @author Dolev Peleg
 */
public class QueueOverflowException extends Exception
{
	/**
	 * Default constructor
	 */
	public QueueOverflowException()
	{
		super("A enqueue method was called on a full queue");
	}
	
	/**
	 * Parameterized constructor
	 * @param message
	 */
	public QueueOverflowException(String message)
	{
		super(message);
	}
}
