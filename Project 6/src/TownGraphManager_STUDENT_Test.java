/**
 * Test class for TownGraphManager
 * @author Dolev Peleg
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownGraphManager_STUDENT_Test 
{

	private TownGraphManagerInterface graph;
	  
	@BeforeEach
	public void setUp() throws Exception {
		  graph = new TownGraphManager();
		  graph.addTown("Silver Spring");
		  graph.addTown("Washington DC");
		  graph.addTown("Haifa");
		  graph.addTown("Tel Aviv");
		  
		  graph.addRoad("Silver Spring", "Washington DC", 2, "Road_1");
		  graph.addRoad("Haifa", "Tel Aviv", 2, "Road_2");
		 
	}

	@AfterEach
	public void tearDown() throws Exception 
	{
		graph = null;
	}

	@Test
	public void testAddAndRemoveRoad() 
	{
		assertFalse(graph.containsRoadConnection("Washington DC", "Haifa")); // Check if there is a road between Washington DC and Haifa
		graph.addRoad("Washington DC", "Haifa", 7000, "Airline_Road"); // Add the road
		assertTrue(graph.containsRoadConnection("Washington DC", "Haifa")); // Check if there is a road between Washington DC and Haifa
		graph.deleteRoadConnection("Washington DC", "Haifa", "Airline_Road"); // Remove the road
		assertFalse(graph.containsRoadConnection("Washington DC", "Haifa")); // Check again
	}

	@Test
	public void testGetRoad() 
	{
		assertEquals("Road_1", graph.getRoad("Silver Spring", "Washington DC"));
		assertEquals("Road_2", graph.getRoad("Haifa", "Tel Aviv"));
	}

	@Test
	public void testAddAndRemoveTown() 
	{
		assertEquals(false, graph.containsTown("NYC")); // Check if the graph contains the town
		graph.addTown("NYC"); // Add the town
		assertEquals(true, graph.containsTown("NYC")); // Check again
		graph.deleteTown("NYC"); // Remove the town
		assertEquals(false, graph.containsTown("NYC")); // Check again
	}
	
	

	@Test
	public void testAllRoads() 
	{
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
	}

	
	@Test
	public void testAllTowns() 
	{
		ArrayList<String> roads = graph.allTowns();
		assertEquals("Haifa", roads.get(0));
		assertEquals("Silver Spring", roads.get(1));
		assertEquals("Tel Aviv", roads.get(2));
		assertEquals("Washington DC", roads.get(3));
	}

	@Test
	public void testGetPath() {
		// This method was not implemented by Dolev Peleg
	}
	
	@Test
	public void testFile()
	{
		
		try
		{
			File file = new File("Dolev_Test"); // Create a new file
			
			// Add the road betwen Silver Spring and Washington DC to the file
			PrintWriter output = new PrintWriter(file);
			output.println("Road_1,2;Washington DC;Silver Spring");
			output.close(); // Close the output
			
			// Create a new graph, populate it using the populateTownGraph method
			TownGraphManager testGraph = new TownGraphManager();
			testGraph.populateTownGraph(file);
			
			assertEquals(testGraph.getRoad("Washington DC", "Silver Spring"), graph.getRoad("Washington DC", "Silver Spring")); // Assert if both roads are equal
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
