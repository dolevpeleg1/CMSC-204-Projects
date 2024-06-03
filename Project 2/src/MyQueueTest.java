 
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyQueueTest {
	public MyQueue<String> stringQ;
	public String a="a", b="b", c="c", d="d", e="e", f="f";
	public ArrayList<String> fill = new ArrayList<String>();
	
	// STUDENT: student tests will use the doubleQ
	public MyQueue<Double> doubleQ;
	
	// STUDENT: add variables as needed for your student tests
	double num1 = 3.5, num2 = 4.2, num3 = 0.5, num4 = 5.0;
	@Before
	public void setUp() throws Exception {
		stringQ = new MyQueue<String>(5);
		stringQ.enqueue(a);
		stringQ.enqueue(b);
		stringQ.enqueue(c);
		
		//STUDENT: add setup for doubleQ for student tests
		doubleQ = new MyQueue<Double>(3);
		doubleQ.enqueue(num1);
		doubleQ.enqueue(num2);
	}

	@After
	public void tearDown() throws Exception {
		stringQ = null;
		doubleQ = null;
	}

	@Test
	public void testIsEmpty() { // I added a try-catch block because dequeue throws an exception as one of the project requirements - Dolev
		assertEquals(false,stringQ.isEmpty());
		try
		{
			stringQ.dequeue();
			stringQ.dequeue();
			stringQ.dequeue();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		assertEquals(true, stringQ.isEmpty());
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals(a, stringQ.dequeue());
			assertEquals(b, stringQ.dequeue());
			assertEquals(c, stringQ.dequeue());
			//Queue is empty, next statement should cause QueueUnderFlowException
			stringQ.dequeue();
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
		catch (QueueUnderflowException e){
			assertTrue("This should have caused an QueueUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
	}
	
	@Test
	public void testDequeueStudent() 
	{
		//Use the doubleQ for student tests
		try
		{
			assertEquals(num1, doubleQ.dequeue() , 0.1);
			assertEquals(num2, doubleQ.dequeue() , 0.1);
			//Queue is empty, next statement should cause QueueUnderFlowException
			doubleQ.dequeue();
		}
		catch (QueueUnderflowException e)
		{
			assertTrue("This should have caused an QueueUnderflowException", true);
		}
		catch (Exception e)
		{
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
		
	}

	@Test
	public void testSize() { // I added a try-catch block because enqueue and dequeue throw an exception as one of the project requirements - Dolev
		try
		{
			assertEquals(3, stringQ.size());
			stringQ.enqueue(d);
			assertEquals(4, stringQ.size());
			stringQ.dequeue();
			stringQ.dequeue();
			assertEquals(2, stringQ.size());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Test
	public void testEnqueue() {
		try {
			assertEquals(3, stringQ.size());
			assertEquals(true, stringQ.enqueue(d));
			assertEquals(4, stringQ.size());
			assertEquals(true, stringQ.enqueue(e));
			assertEquals(5, stringQ.size());
			//Queue is full, next statement should cause QueueOverFlowException
			stringQ.enqueue(f);
			assertTrue("This should have caused an QueueOverflowException", false);
		}
		catch (QueueOverflowException e){
			assertTrue("This should have caused an QueueOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueOverflowException", false);
		}
	}

	@Test
	public void testEnqueueStudent() {
		//Use the doubleQ for student tests
		try
		{
			assertEquals(2, doubleQ.size());
			assertEquals(true, doubleQ.enqueue(num3));
			assertEquals(3, doubleQ.size()); 
			doubleQ.enqueue(num4); // this should throw a QueueOverflowException
		}
		catch (QueueOverflowException e){
			assertTrue("This should have caused an QueueOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueOverflowException", false);
		}
	}

	@Test
	public void testIsFull() { // I added a try-catch block because enqueue throws an exception as one of the project requirements - Dolev
		try
		{
			assertEquals(false, stringQ.isFull());
			stringQ.enqueue(d);
			stringQ.enqueue(e);
			assertEquals(true, stringQ.isFull());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testToString() { // I added a try-catch block because enqueue throws an exception as one of the project requirements - Dolev
		try
		{
			assertEquals("abc", stringQ.toString());
			stringQ.enqueue(d);
			assertEquals("abcd", stringQ.toString());
			stringQ.enqueue(e);
			assertEquals("abcde", stringQ.toString());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void testToStringStudent() {
		//Use the doubleQ for student tests
		try
		{
			assertEquals("3.54.2", doubleQ.toString());
			doubleQ.dequeue();
			assertEquals("4.2", doubleQ.toString());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testToStringDelimiter() { // I added a try-catch block because enqueue throws an exception as one of the project requirements - Dolev
		try
		{
			assertEquals("a%b%c", stringQ.toString("%"));
			stringQ.enqueue(d);
			assertEquals("a&b&c&d", stringQ.toString("&"));
			stringQ.enqueue(e);
			assertEquals("a/b/c/d/e", stringQ.toString("/"));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testFill() { // I added a try-catch block because dequeue throws an exception as one of the project requirements - Dolev
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		//start with an empty queue
		stringQ = new MyQueue<String>(5);
		//fill with an ArrayList
		stringQ.fill(fill);
		assertEquals(3,stringQ.size());
		try
		{
			assertEquals("apple", stringQ.dequeue());
			assertEquals("banana", stringQ.dequeue());
			assertEquals("carrot", stringQ.dequeue());		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
