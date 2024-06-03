/**
 * Exception class, thrown when a dequeue method is called on an empty queue.
 * @author Dolev Peleg
 */
public class QueueUnderflowException extends Exception
{
	/**
	 * Default constructor
	 */
	public QueueUnderflowException()
	{
		super("A dequeue method was called on an empty queue");
	}
	
	/**
	 * Parameterized constructor
	 * @param message
	 */
	public QueueUnderflowException(String message)
	{
		super(message);
	}
}
