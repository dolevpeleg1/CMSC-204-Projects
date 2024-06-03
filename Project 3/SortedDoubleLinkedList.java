import java.util.Comparator;
import java.util.ListIterator;

/**
 * A generic Sorted double-linked list utility class that extends the generic BasicDoubleLinkedList class
 * @author Dolev Peleg
 */

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>
{
	// Comparator field
	Comparator<T> comparator;
	
	/**
	 * Parameterized constructor that creates an empty list that is associated with the specified comparator
	 * @param Comparator, the associated specified comparator
	 */
	public SortedDoubleLinkedList(Comparator<T> comparator)
	{
		this.comparator = comparator;
	}
	
	/**
	 * Inserts the specified element at the correct position in the sorted list
	 * @param data
	 */
	public void add(T data)
	{
		Node<T> currentDataNode;
		// Crate a new node and set its data to the new entry
		Node<T> newNode = new Node<T>();
		newNode.setData(data);
		
		// If the list is empty
		if(size == 0)
		{
			// Set the head and the tail to reference the new node
			head = newNode;
			tail = newNode;
			
			size++; // Increment the size
		}
		// If the list is not empty
		else
		{
			currentDataNode = head; // Start the comparison from the head node
			
			// For loop to iterate through the list
			for(int counter = 0; counter < size; counter++)
			{
				// If the compare method determines that the data is smaller than the current node's data, the new data will be inserted before the current node
				if(comparator.compare(data, currentDataNode.getData()) < 0)
				{
					// If this is the first iteration of the loop, this is the new head 
					if(counter == 0)
					{
						newNode.next = head; // Set the new node's next field reference the head node
						head.prev = newNode; // Set the head's prev field reference the new node
						head = newNode; // Set the head to reference the new node
					}
					// Else, this is a regular node in the list, 
					else
					{
						newNode.next = currentDataNode; // Set the next field of the new node to the current node
						newNode.prev = currentDataNode.prev; // Set the new node's prev field to the current nodes prev field
						currentDataNode.prev = newNode; // Set the current node's prev node's next field to reference the new node
						newNode.prev.next = newNode; // Set the new node's previous node's next field to reference the new node
					}
					
					// The data was added to the list
					size++; // Increment the size
					break; // Break the loop
					
				}
				// Else, the compare method determined that the data is greater than the current node's data
				else
				{
					// If this is the last iteration of the loop, this is the new tail 
					if(counter == size - 1)
					{
						// If there is only one node in the list
						if(size == 1)
						{
							tail = newNode; // Set the tail to reference the new node
							tail.prev = head; // Set the tail's prev field to the head
							head.next = tail; // Set the head's next field to the tail
						}
						// If there is more than one node in the list
						else
						{
							newNode.prev = tail; // Set the new node's prev field reference the tail node
							tail.next = newNode; // Set the tail's next field reference the new node
							tail = newNode; // Set the tail to reference the new node
						}
						
						// The data was added to the list
						size++; // Increment the size
						break; // Break the loop
					}
					// Else, set the current node the the current node's next field
					else
					{
						currentDataNode = currentDataNode.next; 
					}
				}
			}
		}
	}
	
	/**
	 * This operation is invalid for a sorted list. An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
	 * @param data
	 * @throws java.lang.UnsupportedOperationException
	 */
	public void addToEnd(T data) throws java.lang.UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * This operation is invalid for a sorted list. An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
	 * @param data
	 * @param data
	 * @throws java.lang.UnsupportedOperationException
	 */
	public void addToFront(T data) throws java.lang.UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	
	/**
	 *  Returns an object of the DoubleLinkedListIterator using the super class' iterator method
	 */
	public ListIterator<T> iterator()
	{
		return super.iterator();
	}
	
	/**
	 * Implements the remove operation by calling the super class remove method
	 * @param data
	 * @param comparator
	 * @return
	 */
	public Node remove(T data, Comparator<T> comparator)
	{
		return super.remove(data, comparator);
	}
	
}
