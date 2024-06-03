/**
 * Test class for BasicDoubleLinkedList
 * @author Dolev Peleg
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<String> linkedString;
	StringComparator comparator;
	
	@Before
	public void setUp() throws Exception 
	{
		// Create a List: First Second Third Fourth
		linkedString = new BasicDoubleLinkedList<String>();
		linkedString.addToEnd("Second");
		linkedString.addToFront("First");
		linkedString.addToEnd("Third");
		linkedString.addToEnd("Fourth");
		comparator = new StringComparator();
	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
		comparator = null;
	}

	@Test
	public void testGetSize() 
	{
		assertEquals(4,linkedString.getSize());
	}
	
	@Test
	public void testAddAndRetrieveEnd() 
	{
		linkedString.addToEnd("New End"); // Add a new end
		assertEquals("New End", linkedString.retrieveLastElement()); // Retrieving the new end
		assertEquals("Fourth", linkedString.getLast()); // getLast should return Fourth
	}
	
	@Test
	public void testAddAndRetrieveFront() 
	{
		linkedString.addToFront("New Front"); // Add a new front
		assertEquals("New Front", linkedString.retrieveFirstElement()); // Retrieving the new front
		assertEquals("First", linkedString.getFirst()); // getFirst should return First
	}
	
	@Test
	public void testToArrayList()
	{
		assertEquals("[First, Second, Third, Fourth]", linkedString.toArrayList().toString());
	}
	
	@Test
	public void testIterator() 
	{
		ListIterator<String> iterator = linkedString.iterator();
		assertTrue(iterator.hasNext()); // True, first element is next
		assertFalse(iterator.hasPrevious());
		
		// Next until the end of the list
		assertEquals("First", iterator.next()); // First
		assertEquals("Second", iterator.next()); // Second
		assertEquals("Third", iterator.next()); // Third
		assertEquals("Fourth", iterator.next()); // Fourth
		
		try
		{
			//no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
		assertFalse(iterator.hasNext()); // False, no more objects in the list
		assertTrue(iterator.hasPrevious());
		
		// Previous until the beginning of the list
		assertEquals("Fourth", iterator.previous()); // Fourth
		assertEquals("Third", iterator.previous()); // Third
		assertEquals("Second", iterator.previous()); // Second
		assertEquals("First", iterator.previous()); // First
		
		try
		{
			//no more elements in list
			iterator.previous();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
		assertTrue(iterator.hasNext()); // True, first element is next
		assertFalse(iterator.hasPrevious());
	}
	
	@Test
	public void testRemove() 
	{
		linkedString.addToEnd("dolev"); // Adding my name to the list
		assertEquals("dolev", linkedString.getLast()); // Testing if my name is the last object in the list
		linkedString.remove("dolev", comparator); // Removing my name from the list
		assertEquals("Fourth", linkedString.getLast()); // Testing if Fourth is the last object in the list
	}

	// Comparator made by my instructor
	private class StringComparator implements Comparator<String>
	{
		@Override
		public int compare(String arg0, String arg1) 
		{
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
	}
}
