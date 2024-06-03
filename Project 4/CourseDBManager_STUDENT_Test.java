

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the test file for the CourseDBManager
 * @author Dolev Pelegs
 * 
 */
public class CourseDBManager_STUDENT_Test 
{
	private CourseDBManagerInterface dataMgr = new CourseDBManager();

	/**
	 * Create an instance of CourseDBManager
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		dataMgr = new CourseDBManager();
	}

	/**
	 * Set dataMgr reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception 
	{
		dataMgr = null;
	}

	/**
	 * Test for the add method
	 */
	@Test
	public void testAddToDB() 
	{
		try 
		{
			dataMgr.add("CMSC204",11111,3,"MyHouse","The guy from that show I like");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/**
	 * Test for the showAll method
	 */
	@Test
	public void testShowAll() {
		dataMgr.add("MATH182",42512,4,"FiveGuys","The sixth guy");
		dataMgr.add("HIST118",53251,9,"Chipotle","The seventh guy");
		dataMgr.add("CMSC999",99999,8,"Here","The first guy");
		ArrayList<String> list = dataMgr.showAll();
		assertEquals(list.get(0),"\nCourse:MATH182 CRN:42512 Credits:4 Instructor:The sixth guy Room:FiveGuys");
	 	assertEquals(list.get(2),"\nCourse:HIST118 CRN:53251 Credits:9 Instructor:The seventh guy Room:Chipotle");
		assertEquals(list.get(1),"\nCourse:CMSC999 CRN:99999 Credits:8 Instructor:The first guy Room:Here");
	}
	
	/**
	 * Test for the read method
	 */
	@Test
	public void testRead() {
		try {
			File inputFile = new File("DolevTest.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("MATH111 11111 1 SW111 MathPlace"); // Will be added
			inFile.println("#This is a Comment and will be Ignored"); // Comment, will not be added
			inFile.print("MATH113  1 MathPlace MyInstructor"); // Missing details, will not be added
			inFile.close();
			dataMgr.readFile(inputFile);
			ArrayList<String> list = dataMgr.showAll();
			assertEquals(list.get(0),"\nCourse:MATH111 CRN:11111 Credits:1 Instructor:MathPlace Room:SW111"); // Only this course will be added
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}
