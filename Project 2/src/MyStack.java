/**
 * Generic stack
 * @param <T> data type
 * @author Dolev Peleg
 */
import java.util.ArrayList;

public final class MyStack<T> implements StackInterface<T> 
{
	// Fields
	private Node topNode;
	private int numberOfEntries;
	private int maxCapacity; // Determine the maximum amount of entries a stack can hold
	private static final int DEFUALT_CAPACITY = 25; // Default capacity for default constructor
	
	/**
	 * Default constructor, creates an empty stack with default max capacity
	 */
	public MyStack()
	{
		this(DEFUALT_CAPACITY);
	}
	
	/**
	 * Parameterized constructor, creates an empty stack with specified max capacity
	 */
	public MyStack(int capacity)
	{
		topNode = null;
		numberOfEntries = 0;
		maxCapacity = capacity;
	}
	
	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	public boolean isEmpty()
	{
		boolean result = false;
		
		// If the top node is null, the stack is empty; return true
		if(topNode == null) 
		{
			result = true;
		}
		return result;
	}

	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull()
	{
		boolean result = false;
		
		// If the number of entries is equal to the max capacity of the stack, stack is full; return true
		if(numberOfEntries == maxCapacity)
		{
			result = true;
		}
		return result;
	}
	
	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T pop() throws StackUnderflowException
	{
		T result;
		
		// If the stack is empty, throw a StackUnderflowException
		if(isEmpty())
		{
			throw new StackUnderflowException();
		}
		// Else, return the top node's data field using top(), set the top node to its next node. Decrement the number of entries
		else
		{
			result = top();
			topNode = topNode.getNextNode();
			numberOfEntries--;
			
		}
		return result;
	}
	
	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T top() throws StackUnderflowException
	{
		T result;
		
		// If the stack is empty, throw a StackUnderflowException
		if(isEmpty())
		{
			throw new StackUnderflowException();
		}
		// Else, return the top node's data field
		else
		{
			result = topNode.getData();
		}
		return result;
	}

	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	public int size()
	{
		// Return the number of entries currently in the stack
		return numberOfEntries;
	}
	
	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */
	public boolean push(T e) throws StackOverflowException
	{
		boolean result = false;
		
		// If the stack is full, throw a StackOverflowException
		if(isFull())
		{
			throw new StackOverflowException();
		}
		// Else, create a new node with the parameter's object, set the top node to be the new node, increment the number of entries, and return true
		else
		{
			Node newNode = new Node(e, topNode);
			topNode = newNode; 
			numberOfEntries++;
			result = true;
		}
		return result;
	}
	
	
	/**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString()
	{
		String result = "";
		String temp = "";
		Node tempTopNode = new Node(null, topNode); // Create a temporary node that references the top node in its next field
		
		// Create a for loop that iterates once for each entry in the stack
		for(int counter = 0; counter < numberOfEntries; counter++)
		{
			tempTopNode = tempTopNode.getNextNode(); // Make the temporary node reference its next field
			
			// Add the toString() method of the temporary node's data field to the temp string in reverse order
			for(int index = tempTopNode.getData().toString().length() - 1; index >= 0 ; index--)
			{
				temp += tempTopNode.getData().toString().charAt(index); 
			}
		}
		
		// Switching the order of the characters in the temp string, assigning it to result
		for(int counter = temp.length() - 1; counter >= 0; counter--)
		{
			result += temp.charAt(counter);
		}
		return result; 
	}
	
	/**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	public String toString(String delimiter)
	{
		String result = "";
		String temp  = "";
		Node tempTopNode = new Node(null, topNode); // Create a temporary node that references the top node in its next field
		
		// Create a for loop that iterates once for each entry in the stack
		for(int counter = 0; counter < numberOfEntries; counter++)
		{
			tempTopNode = tempTopNode.getNextNode(); // Make the temporary node reference its next field
			
			// Add the toString() method of the temporary node's data field to the temp string in reverse order
			for(int index = tempTopNode.getData().toString().length() - 1; index >= 0 ; index--)
			{
				temp += tempTopNode.getData().toString().charAt(index); // Add the toString() method of the temporary node's data field to the temp string
			}
			// If this is not the last iteration of the loop, add the delimiter
			if(counter < numberOfEntries - 1)
			{
				temp += delimiter;
			}
		}
		
		// Switching the order of the characters in the temp string, assigning it to result
		for(int counter = temp.length() - 1; counter >= 0; counter--)
		{
			result += temp.charAt(counter);
		}
		return result;
	}
	
	 /**
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
	  * list reference within your Stack, you will be allowing direct access to the data of
	  * your Stack causing a possible security breech.
	  * @param list elements to be added to the Stack from bottom to top
	  * @throws StackOverflowException if stack gets full
	  */
	public void fill(ArrayList<T> list) // Does not throw StackOverflowException 
	{
		ArrayList<T> tempList = new ArrayList<>(); // Create a temporary array list
		// Create a for loop to create a copy of the list into the temporary list, and add its objects into the queue
		for(int counter = 0; counter < list.size(); counter++)
		{
			tempList.add(list.get(counter));
			try
			{
				push(tempList.get(counter));
			}
			// If the stack gets full, print out the StackOverflowException message
			catch(StackOverflowException e)
			{
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	// Create a Node utility class
	private class Node
	{
		// Fields
		private T data;
		private Node next;
		
		/**
		 * Parameterized constructor that creates a node with a null next field
		 * @param dataPortion
		 */
		private Node(T dataPortion)
		{
			this(dataPortion, null);
		}
		
		/**
		 * Parameterized constructor that creates a node and a next node
		 * @param dataPortion, @param nextNode
		 */
		private Node(T dataPortion, Node nextNode)
		{
			data = dataPortion;
			next = nextNode;
		}
		
		// Getters for the fields
		public T getData()
		{
			return data;
		}
		
		public Node getNextNode()
		{
			return next;
		}
	}
}
