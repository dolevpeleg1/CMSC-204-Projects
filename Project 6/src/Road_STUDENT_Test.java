/**
 * Test class for Road
 * @author Dolev Peleg
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Road_STUDENT_Test 
{
	Road road;
	Town town1;
	Town town2;
	
	@BeforeEach
	public void setUp() throws Exception 
	{
		town1 = new Town("Silver Spring");
		town2 = new Town("Washington DC");
		road = new Road(town1, town2, 2, "Road_1");
		 
	}

	@AfterEach
	public void tearDown() throws Exception 
	{
		road = null;
		town1 = null;
		town2 = null;
	}
	
	@Test
	void testContains() 
	{
		assertTrue(road.contains(town1));
		assertTrue(road.contains(town2));
	}
	
	@Test
	void testToString() 
	{
		assertEquals("Road between Washington DC and Silver Spring, weight: 2, name: Road_1", road.toString());
	}
	
	@Test
	void testEquals()
	{
		assertTrue(road.equals(new Road(new Town("Silver Spring"), new Town("Washington DC"), 2, "Road_1")));
	}
	
	@Test
	void testCompareTo()
	{
		assertEquals(0, road.compareTo(road = new Road(town1, town2, 2, "Road_1")));
	}
}
