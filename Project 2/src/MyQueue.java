/**
 * Generic queue
 * @param <T> data type
 * @author Dolev Peleg
 */
import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T> 
{
	// Fields
	private T[] queue;
	private int frontIndex;
	private int backIndex;
	private int numberOfEntries; // The number of entries that are in the queue
	private static final int DEFUALT_CAPACITY = 25; // Default capacity for default constructor
	
	/**
	 * Default constructor with default capacity
	 */
	public MyQueue()
	{
		this(DEFUALT_CAPACITY);
	}
	
	/**
	 * Parameterized constructor with specified capacity
	 * @param capacity
	 */
	public MyQueue(int capacity)
	{
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[capacity + 1];
		queue = temp;
		frontIndex = 0;
		backIndex = capacity;
		numberOfEntries = 0;
	}

	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	public boolean isEmpty()
	{
		return frontIndex == (backIndex + 1) % queue.length; // Return if the queue array is empty
	}

	/**
	 * Determines of the Queue is Full
	 * @return true if Queue is full, false if not
	 */
	public boolean isFull()
	{
		return frontIndex == (backIndex + 2) % queue.length; // Return if the queue array is full
	}

	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException if queue is empty
	 */
	public T dequeue() throws QueueUnderflowException
	{
		T result = null;
		
		// If queue is empty, throw a QueueUnderflowException Exception
		if(isEmpty())
		{
			throw new QueueUnderflowException();
		}
		// If queue is not empty, set result to the first entry in the queue, set that entry to null, increment the front index, decrement the number of entries, and return the result 
		else
		{
			result = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1) % queue.length;
			numberOfEntries--;
			return result;
		}
	}

	/**
	 * Returns number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	public int size()
	{
		return numberOfEntries; // Return the number of entries
	}

	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
	public boolean enqueue(T e) throws QueueOverflowException
	{
		// If queue is full, throw QueueOverflowException exception
		if(isFull())
		{
			throw new QueueOverflowException();
		}
		// If queue is not full, increment the back index, enqueue the new entry in the element of the back index, increment the number of entries, and return true
		else
		{
			backIndex = (backIndex + 1) % queue.length;
			queue[backIndex] = e;
			numberOfEntries++;
			return true;
		}
	}

	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	public String toString()
	{
		String result = ""; // Create an empty string
		int counter = frontIndex; // Create a counter that starts at the element number of the front index
		
		// Create a for loop to iterate the same amount of times as the number of entries in the queue
		for(int objectNumber = 0; objectNumber < numberOfEntries; objectNumber++)
		{
			result += queue[counter].toString(); // Add the toString method of the object to the result
			counter = (counter + 1) % queue.length; // increment the counter
		}
		return result;
	}

	/**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	public String toString(String delimiter)
	{
		String result = ""; // Create an empty string
		int counter = frontIndex; // Create a counter that starts at the element number of the front index
		
		// Create a for loop to iterate the same amount of times as the number of entries in the queue
		for(int objectNumber = 0; objectNumber < numberOfEntries; objectNumber++)
		{
			// If the object is not the last object in the queue, add the toString method of the object, and the delimiter to the result
			if(objectNumber != numberOfEntries - 1)
			{
				result += queue[counter].toString() + delimiter;
			}
			else // Else, add the toString method of the object, without the delimiter
			{
				result += queue[counter].toString();
			}
			counter = (counter + 1) % queue.length; // increment the counter
		}
		return result;
	}

	/**
	 * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	 * is the first element in the Queue
	 * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
	 * list reference within your Queue, you will be allowing direct access to the data of
	 * your Queue causing a possible security breech.
	 * @param list elements to be added to the Queue
	 * @throws QueueOverflowException if queue is full

	 */
	public void fill(ArrayList<T> list) // Does not throw a QueueOverflowException
	{
		ArrayList<T> tempList = new ArrayList<>(); // Create a temporary array list
		// Create a for loop to create a copy of the list into the temporary list, and add its objects into the queue
		for(int counter = 0; counter < list.size(); counter++)
		{
			tempList.add(list.get(counter));
			try
			{
				enqueue(tempList.get(counter));
			}
			// If the queue gets full, print out the QueueOverflowException message
			catch(QueueOverflowException e)
			{
				System.out.println(e.getMessage());
			}
			
		}
	}

}
