/**
 * Test class for Graph
 * @author Dolev Peleg
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Graph_STUDENT_Test 
{
	private Graph graph;
	  
	@BeforeEach
	public void setUp() throws Exception {
		  graph = new Graph();
		  graph.addVertex(new Town("Silver Spring"));
		  graph.addVertex(new Town("Washington DC"));
		  graph.addVertex(new Town("Haifa"));
		  graph.addVertex(new Town("Tel Aviv"));
		  
		  graph.addEdge(new Town("Silver Spring"), new Town("Washington DC"), 2, "Road_1");
		  graph.addEdge(new Town("Haifa"), new Town("Tel Aviv"), 2, "Road_2");
		 
	}

	@AfterEach
	public void tearDown() throws Exception 
	{
		graph = null;
	}

	@Test
	public void testAddAndRemoveRoad() 
	{
		assertFalse(graph.containsEdge(new Town("Washington DC"), new Town("Haifa"))); // Check if there is a road between Washington DC and Haifa
		graph.addEdge(new Town("Washington DC"), new Town("Haifa"), 7000, "Airline_Road"); // Add the road
		assertTrue(graph.containsEdge(new Town("Washington DC"), new Town("Haifa"))); // Check if there is a road between Washington DC and Haifa
		graph.removeEdge(new Town("Washington DC"), new Town("Haifa"), 7000, "Airline_Road"); // Remove the road
		assertFalse(graph.containsEdge(new Town("Washington DC"), new Town("Haifa")));; // Check again
	}

	@Test
	public void testGetRoad() 
	{
		assertEquals("Road between Washington DC and Silver Spring, weight: 2, name: Road_1", graph.getEdge(new Town("Silver Spring"), new Town("Washington DC")).toString());
		assertEquals("Road between Tel Aviv and Haifa, weight: 2, name: Road_2", graph.getEdge(new Town("Haifa"), new Town("Tel Aviv")).toString());
	}

	@Test
	public void testAddAndRemoveTown() 
	{
		assertEquals(false, graph.containsVertex(new Town("NYC"))); // Check if the graph contains the town
		graph.addVertex(new Town("NYC")); // Add the town
		assertEquals(true, graph.containsVertex(new Town("NYC"))); // Check again
		graph.removeVertex(new Town("NYC")); // Remove the town
		assertEquals(false, graph.containsVertex(new Town("NYC"))); // Check again
	}
	
	

	@Test
	public void testEdgesOf() 
	{
		// Create a new set of roads, add the road from Haifa to Tel Aviv to it
		Set<Road> set = new HashSet<>();
		set.add(graph.getEdge(new Town("Haifa"), new Town("Tel Aviv")));
		assertEquals(set.toString(), graph.edgesOf(new Town("Haifa")).toString()); // Check if the sets are equal
	}

	
	@Test
	public void testEdgeSet() 
	{
		// Create a new set of roads, add both roads to it
		Set<Road> set = new HashSet<>();
		set.add(graph.getEdge(new Town("Haifa"), new Town("Tel Aviv")));
		set.add(graph.getEdge(new Town("Washington DC"), new Town("Silver Spring")));
		assertEquals(set.toString(), graph.edgeSet().toString()); // Check if the sets are equal
	}
	
	@Test
	public void testVertexSet() 
	{
		// Create a new set of roads, add both roads to it
		Set<Town> set = new HashSet<>();
		set.add(new Town("Silver Spring"));
		set.add(new Town("Washington DC"));
		set.add(new Town("Haifa"));
		set.add(new Town("Tel Aviv"));
		assertEquals(set.toString(), graph.vertexSet().toString()); // Check if the sets are equal
	}

	@Test
	public void testDijkstraShortestPath() {
		// This method was not implemented by Dolev Peleg
	}

}
