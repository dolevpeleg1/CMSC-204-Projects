 
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyStackTest {
	public MyStack<String> stringS;
	public String a="a", b="b", c="c", d="d", e="e", f="f";
	public ArrayList<String> fill = new ArrayList<String>();
	
	// STUDENT: student tests will use the doubleS
	public MyStack<Double> doubleS;
	
	
	// STUDENT: add variables as needed for your student tests
	double num1 = 1.1, num2 = 2.2, num3 = 3.3, num4 = 4.4;
	
	@Before
	public void setUp() throws Exception {
		stringS = new MyStack<String>(5);
		stringS.push(a);
		stringS.push(b);
		stringS.push(c);
		
		//STUDENT: add setup for doubleS for student tests
		doubleS = new MyStack<Double>(3);
		doubleS.push(num1);
		doubleS.push(num2);
	}

	@After
	public void tearDown() throws Exception {
		stringS = null;
		doubleS = null;
	}

	@Test
	public void testIsEmpty() { // I added a try-catch block because pop throws an exception as one of the project requirements - Dolev
		assertEquals(false,stringS.isEmpty());
		try
		{
			stringS.pop();
			stringS.pop();
			stringS.pop();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		assertEquals(true, stringS.isEmpty());
	}

	@Test
	public void testIsFull() { // I added a try-catch block because push throws an exception as one of the project requirements - Dolev
		assertEquals(false, stringS.isFull());
		try
		{
			stringS.push(d);
			stringS.push(e);	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		assertEquals(true, stringS.isFull());
	}

	@Test
	public void testPop() {
		try {
			assertEquals(c, stringS.pop());
			assertEquals(b, stringS.pop());
			assertEquals(a, stringS.pop());
			//Queue is empty, next statement should cause QueueUnderFlowException
			stringS.pop();
			assertTrue("This should have caused an StackUnderflowException", false);
		}
		catch (StackUnderflowException e){
			assertTrue("This should have caused an StackUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackUnderflowException", false);
		}
	}

	@Test
	public void testPopStudent() {
		//Use the doubleQ for student tests
		try {
			assertEquals(2, doubleS.size());
			assertEquals(num2, doubleS.pop(), 0.1);
			assertEquals(1, doubleS.size());
			assertEquals(num1, doubleS.pop(), 0.1);
			//Queue is empty, next statement should cause QueueUnderFlowException
			doubleS.pop();
			assertTrue("This should have caused an StackUnderflowException", false);
		}
		catch (StackUnderflowException e){
			assertTrue("This should have caused an StackUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackUnderflowException", false);
		}
	}
	
	@Test
	public void testTop() { // I added a try-catch block because top,push, and pop throw an exception as one of the project requirements - Dolev
		try
		{
			assertEquals(c, stringS.top());
			stringS.push(d);
			assertEquals(d, stringS.top());
			stringS.pop();
			stringS.pop();
			assertEquals(b, stringS.top());	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
			
	}

	@Test
	public void testSize() { // I added a try-catch block because push and pop throw an exception as one of the project requirements - Dolev
		try
		{
			assertEquals(3, stringS.size());
			stringS.push(d);
			assertEquals(4, stringS.size());
			stringS.pop();
			stringS.pop();
			assertEquals(2, stringS.size());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Test
	public void testPush() {
		try {
			assertEquals(3, stringS.size());
			assertEquals(true, stringS.push(d));
			assertEquals(4, stringS.size());
			assertEquals(true, stringS.push(e));
			assertEquals(5, stringS.size());
			//Queue is full, next statement should cause QueueOverFlowException
			stringS.push(f);
			assertTrue("This should have caused an StackOverflowException", false);
		}
		catch (StackOverflowException e){
			assertTrue("This should have caused an StackOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackOverflowException", false);
		}
	}

	@Test
	public void testPushStudent() {
		//Use the doubleQ for student tests
		try
		{
			assertEquals(2, doubleS.size());
			assertEquals(true, doubleS.push(num3));
			assertEquals(3, doubleS.size());
			assertEquals(true, doubleS.push(num4));
			assertEquals(4, doubleS.size());
			//Queue is full, next statement should cause QueueOverFlowException
			doubleS.push(num3);
		}
		catch (StackOverflowException e){
			assertTrue("This should have caused an StackOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackOverflowException", false);
		}
	}
	
	@Test
	public void testToString() { // I added a try-catch block because push throws an exception as one of the project requirements - Dolev
		try
		{
			assertEquals("abc", stringS.toString());
			stringS.push(d);
			assertEquals("abcd", stringS.toString());
			stringS.push(e);
			assertEquals("abcde", stringS.toString());
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
			assertEquals("1.12.2", doubleS.toString());
			doubleS.push(num3);
			assertEquals("1.12.23.3", doubleS.toString());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testToStringDelimiter() { // I added a try-catch block because push throws an exception as one of the project requirements - Dolev
		try
		{
			assertEquals("a%b%c", stringS.toString("%"));
			stringS.push(d);
			assertEquals("a&b&c&d", stringS.toString("&"));
			stringS.push(e);
			assertEquals("a/b/c/d/e", stringS.toString("/"));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Test
	public void testFill() { // I added a try-catch block because pop throws an exception as one of the project requirements - Dolev
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		//start with an empty queue
		stringS = new MyStack<String>(5);
		//fill with an ArrayList
		stringS.fill(fill);
		assertEquals(3,stringS.size());
		try
		{
			assertEquals("carrot", stringS.pop());
			assertEquals("banana", stringS.pop());
			assertEquals("apple", stringS.pop());	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
