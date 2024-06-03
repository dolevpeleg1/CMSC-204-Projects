/**
 * Test class for Town
 * @author Dolev Peleg
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Town_STUDENT_Test 
{
	Town town;
	
	@BeforeEach
	public void setUp() throws Exception 
	{
		town = new Town("Silver Spring");
	}

	@AfterEach
	public void tearDown() throws Exception 
	{
		town = null;
	}
	
	@Test
	void testToString() 
	{
		assertEquals("Town name: Silver Spring", town.toString());
	}
	
	@Test
	void testEquals()
	{
		assertTrue(town.equals(new Town("Silver Spring")));
	}

	@Test
	void testCompareTo()
	{
		assertEquals(0, town.compareTo(new Town("Silver Spring")));
	}
}
