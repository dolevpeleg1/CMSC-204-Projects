/**
 * A generic double-linked list utility class that implements the Iterable interface and relies on a head (reference to first element of the list) and tail (reference to the last element of the list)
 * @author Dolev Peleg
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T>
{
	// Fields
	Node<T> head;
	Node<T> tail;
	int size;
	
	// Default constructor
	public BasicDoubleLinkedList()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * Returns the number of nodes in the list.
	 * @return int, size of the list
	 */
	public int getSize()
	{
		return size;
	}
	
	/**
	 * Adds an element to the end of the list and updated the size of the list
	 * @param data
	 */
	public void addToEnd(T data)
	{
		// Crate a new node and set its data to the new entry
		Node<T> newNode = new Node<T>();
		newNode.setData(data);
		
		// If this is the first node in the list, set the head and the tail to the new node
		if(size == 0)
		{
			head = newNode;
			tail = newNode;
		}
		// If this is not the first node in the list
		else
		{
			// // Set the nodes prev field to the tail node, the tails's next field to the node, then set the node to be the new tail
			newNode.setPrev(tail);
			tail.setNext(newNode);
			tail = newNode;
		}
		size++; // Increment size
	}
	
	/**
	 * Adds an element to the front of the list and updated the size of the list
	 * @param data
	 */
	public void addToFront(T data)
	{
		// Crate a new node and set its data to the new entry
		Node<T> newNode = new Node<T>();
		newNode.setData(data);
		
		// If this is the first node in the list, set the head and the tail to the new node
		if(size == 0)
		{
			head = newNode;
			tail = newNode;
		}
		// If this is not the first node in the list
		else
		{
			// Set the nodes next field to the head node, the head's prev field to the node, then set the node to be the new head
			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
		}
		size++; // Increment size
	}
	
	/**
	 * Returns, but does not remove the first element from the list
	 * @return T, the first element from the list
	 */
	public T getFirst()
	{
		T result;
		// If the head is null, set result to null
		if(head == null)
		{
			result = null;
		}
		// else, set result to the data of the tail
		else
		{
			return (T)head.getData();
		}
		return result;
	}
	
	/**
	 * Returns, but does not remove the last element from the list
	 * @return T, the last element from the list
	 */
	public T getLast()
	{
		T result;
		// If the tail is null, set result to null
		if(tail == null)
		{
			result = null;
		}
		// else, set result to the data of the tail
		else
		{
			return (T)tail.getData();
		}
		return result;
	}
	
	/**
	 * Returns an object of the DoubleLinkedListIterator
	 */
	public ListIterator<T> iterator() 
	{
		return new DoubleLinkedListIterator<>();
	}
	
	/**
	 * Remove the first occurrence of an item from the list based on larger data
	 * @param targetData
	 * @param comparator
	 * @return the removed item if found, null otherwise
	 */
	public Node<T> remove(T targetData, Comparator<T> comparator)
	{
		// If the list is empty, return null
		if(size == 0)
		{
			return null;
		}
		
		Node<T> result = null; // Return value, set to null by default
		Node<T> currentDataNode = head; // Node to be compared, start with the head
		
		// For loop to iterate through the list
		for(int counter = 0; counter < size; counter++)
		{
			// If there is only one object in the list
			if(size == 1)
			{
				result = head; // Set result to the head
				// Set both the head and the tail to null, decrement the size
				head = null;
				tail = null;
				size--;
			}
			else
			{
				// If current node's data compared to the target data is 0, this is the searched data
				if(comparator.compare(currentDataNode.data, targetData) == 0)
				{
					result = currentDataNode; // Set the result to the current node
					
					// If this is the first iteration of the loop, the data matched is the head node. Make the next node the head
					if(counter == 0)
					{
						head = currentDataNode.next;
					}
					// Else if this is the last iteration of the loop, the data matched is the tail node. Make the prev node the tail
					else if(counter == size - 1)
					{
						tail = currentDataNode.prev;
					}
					// Else, this is a regular node in the list, 
					else
					{
						currentDataNode.next.prev = currentDataNode.prev; // Set the next node's prev field to the prev field of the current 
						currentDataNode.prev.next = currentDataNode.next; // Set the prev node's next field to the next field of the current node
					}
					
					currentDataNode = null; // Nullify the current node
					size--; // Decrement the size
					break; // Break the loop
				}
				// Else, make the next node the current node
				else
				{
					currentDataNode = currentDataNode.next;
				}
			}
		}
		return result;
	}
	
	/**
	 * Remove and return the first element from the list, null if list is empty
	 * @return data element or null
	 */
	public T retrieveFirstElement()
	{
		T result; // Return value
		
		// If the list is not empty
		if(size > 0)
		{
			result = head.data; // Set result to the head's data
			head = head.next; // set the head's next field to be the new head, removing the previous head
			size--; // Decrement size
		}
		else
		{
			result = null;
		}
		return result;
	}
	
	/**
	 * Remove and return the first element from the list, null if list is empty
	 * @return data element or null
	 */
	public T retrieveLastElement()
	{
		T result; // Return value
		
		// If the list is not empty
		if(size > 0)
		{
			result = tail.data; // Set result to the head's data
			tail = tail.prev; // set the tail's prev field to be the new tail, removing the previous tail
			size--; // Decrement size
		}
		else
		{
			result = null;
		}
		return result;
	}
	
	public ArrayList<T> toArrayList()
	{
		ArrayList<T> result = new ArrayList<>(); // Array list to return
		Node<T> currentDataNode = head; // Current node to be added to the array list
		// For loop to iterate through the list
		for(int counter = 0; counter < size; counter++)
		{
			result.add(currentDataNode.data); // Add the current's node data to the list
			currentDataNode = currentDataNode.next; // Make the current node reference the next node
		}
		return result;
	}
	// Generic inner class Node
	public class Node<T>
	{
		// Fields
		private T data;
		Node<T> next;
		Node<T> prev;
		
		// Default constructor
		public Node()
		{
			data = null;
			next = null;
			prev = null;
		}
		// Getters and setters
		public Node<T> getNext() 
		{
			return next;
		}

		public void setNext(Node<T> next) 
		{
			this.next = next;
		}

		public Node<T> getPrev() 
		{
			return prev;
		}

		public void setPrev(Node<T> prev) 
		{
			this.prev = prev;
		}
		
		public T getData()
		{
			return data;
		}
		
		public void setData(T data) 
		{
			this.data = data;
		}
	} // End Node
	
	// Generic inner class DoubleLinkedListIterator
	private class DoubleLinkedListIterator<T> implements Iterator<T>, ListIterator<T>
	{
		// Fields
		private Node nextNode;
		int timesNextCalled = 0;

		// Default constructor
		private DoubleLinkedListIterator()
		{
			nextNode = head;	
		}

		/**
		 * Unsupported add method
		 * @throws UnsupportedOperationException
		 */
		public void add(T entry) throws UnsupportedOperationException
		{
			throw new UnsupportedOperationException();
		}

		/**
		 * Check if the list has a next node
		 * @return boolean, true if the list has a next node, false otherwise
		 */
		public boolean hasNext()
		{
			// If next was called less times than the size, the iterator has next
			return timesNextCalled < size;
		}

		/**
		 * Check if the list has a prev node
		 * @return boolean, true if the list has a prev node, false otherwise
		 */
		public boolean hasPrevious()
		{
			// If next was called more times than zero, the iterator has next (decremented every time previosu is called)
			return timesNextCalled > 0;
		}

		/**
		 * Return the next node's data field
		 * @throws NoSuchElementException
		 * @return T, the next node's data field
		 */
		public T next() throws NoSuchElementException
		{
			// If the list has a next node
			if(hasNext())
			{
				Node<T> tempNode = nextNode; // Set a temp node to the next node
				T result = tempNode.data; // Set the result to the temp node's data
				
				// If the next node is not the last node in the list
				if(nextNode.next != null)
				{
					nextNode = nextNode.next; // Set the next node to the next field of the node
				}
				timesNextCalled++; // Increment the times next was called
				return result;
			}
			// else, throw a NoSuchElementException
			else
			{
				throw new NoSuchElementException();
			}
		}

		/**
		 * Unsupported nextIndex method
		 * @throws UnsupportedOperationException
		 */
		public int nextIndex() throws UnsupportedOperationException
		{
			throw new UnsupportedOperationException();
		}

		/**
		 * Return the prev node's data field
		 * @throws NoSuchElementException
		 * @return T, the next node's data field
		 */
		public T previous() throws NoSuchElementException
		{
			// If the list has a next node
			if(hasPrevious())
			{
				Node<T> tempNode;
				T result; // Set the result to the temp node's data
				
				// If the entire list was already called by next()
				if(timesNextCalled == size)
				{
					tempNode = nextNode; // Set a temp node to the next node
					result = tempNode.data; // Set result to the data of the temp node
				}
				// If there are still more objects on the list that were not called by next()
				else
				{
					nextNode = nextNode.prev; // Set the next node to its prev field
					tempNode = nextNode; // Set the temp node to the new next field
					result = tempNode.data; // Set result to the data of the temp node
				}
				timesNextCalled--; // Decrement the times next was called
				return result;
			}
			// else, throw a NoSuchElementException
			else
			{
				throw new NoSuchElementException();
			}
		}

		/**
		 * Unsupported previousIndex method
		 * @throws UnsupportedOperationException
		 */
		public int previousIndex() throws UnsupportedOperationException
		{
			throw new UnsupportedOperationException();
		}

		/**
		 * Unsupported remove method
		 * @throws UnsupportedOperationException
		 */
		public void remove() throws UnsupportedOperationException
		{
			throw new UnsupportedOperationException();
		}

		/**
		 * Unsupported set method
		 * @throws UnsupportedOperationException
		 */
		public void set(T entry) throws UnsupportedOperationException
		{
			throw new UnsupportedOperationException();
		}
	} // End DoubleLinkedListIterator
} // End BasicDoubleLinkedList
