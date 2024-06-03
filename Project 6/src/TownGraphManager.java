/**
 * Data manager class for the graph data structure
 * @author Dolev Peleg
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class TownGraphManager implements TownGraphManagerInterface
{
	// Field
	private Graph graph;
	
	// Default constructor
	public TownGraphManager()
	{
		graph = new Graph();
	}
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	public boolean addRoad(String town1, String town2, int weight, String roadName)
	{
		Town town1Object = new Town(town1);
		Town town2Object = new Town(town2);
		graph.addEdge(town1Object, town2Object, weight, roadName); // Adding the new road to the graph
		return graph.containsEdge(town1Object, town2Object); // Return true if the graph contains the edge between the towns, false otherwise
	}
	
	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	public String getRoad(String town1, String town2)
	{
		Town town1Object = new Town(town1);
		Town town2Object = new Town(town2);
		return graph.getEdge(town1Object, town2Object).getName(); // Returning the name of the road
	}
	
	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	public boolean addTown(String v)
	{
		graph.addVertex(new Town(v)); // Add the town to the graph
		return graph.containsVertex(new Town(v)); // Return true if the graph contains the town, false otherwise
	}
	
	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	public Town getTown(String name)
	{
		Town town = new Town(name); // Create a town with the given name
		// If the graph does not contain this town, return null
		if(!graph.containsVertex(town))
		{
			return null;
		}
		// Else return the town
		else
		{
			return town;
		}
	}
	
	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	public boolean containsTown(String v)
	{
		return graph.containsVertex(new Town(v));
	}
	
	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	public boolean containsRoadConnection(String town1, String town2)
	{
		return graph.containsEdge(new Town(town1), new Town(town2));
	}
	
	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	public ArrayList<String> allRoads()
	{
		ArrayList<String> list = new ArrayList<>();
		
		// Iterate through the set of edges, add all the names of all the roads
		Iterator<Road> iterator = graph.edgeSet().iterator();
		while(iterator.hasNext())
		{
			list.add(iterator.next().getName());
		}
		Collections.sort(list); // Sorting the list
		return list;
	}
	
	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	public boolean deleteRoadConnection(String town1, String town2, String road)
	{
		// If the graph contains a road between the two towns, with the sane road name
		if(graph.containsEdge(new Town(town1), new Town(town2)) && graph.getEdge(new Town(town1), new Town(town2)).getName().equals(road))
		{
			Road removedRoad = graph.getEdge(new Town(town1), new Town(town2));
			graph.removeEdge(removedRoad.getSource(), removedRoad.getDestination(), removedRoad.getWeight(), removedRoad.getName()); // Remove the road from the graph
			return true;
		}
		// Else return false
		else
		{
			return false;
		}
	}
	
	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	public boolean deleteTown(String v)
	{
		return graph.removeVertex(new Town(v));
	}
	
	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	public ArrayList<String> allTowns()
	{
		ArrayList<String> list = new ArrayList<>();

		// Iterate through the set of edges, add all the names of all the roads
		Iterator<Town> iterator = graph.vertexSet().iterator();
		while(iterator.hasNext())
		{
			list.add(iterator.next().getName());
		}
		Collections.sort(list); // Sorting the list
		return list;
	}
	
	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	public ArrayList<String> getPath(String town1, String town2)
	{
		return null;
	}
	
	
	public void populateTownGraph(File file) throws FileNotFoundException, IOException
	{
		Scanner input = new Scanner(file);
		String currentRoad;
		String[] words;
		String[] wordsExtanded = new String[4];
		String[] distanceAndName;
		// While input has next
		while(input.hasNextLine())
		{
			currentRoad = input.nextLine(); // The current word is the next line
			words = currentRoad.split(";"); // Split the current road string after each semicolon
			distanceAndName = words[0].split(",");
			wordsExtanded[0] = distanceAndName[0];
			wordsExtanded[1] = distanceAndName[1];
			wordsExtanded[2] = words[1];
			wordsExtanded[3] = words[2];

			Town sourceTown = new Town(wordsExtanded[3]); // The source town is the fourth element
			Town destinationTown = new Town(wordsExtanded[2]); // The destination town is the third element
			
			// Add the towns and the road to the graph
			graph.addVertex(sourceTown);
			graph.addVertex(destinationTown);
			graph.addEdge(sourceTown, destinationTown, Integer.valueOf(wordsExtanded[1]), wordsExtanded[0]);
		}
		input.close(); // Close the scanner
	}
}
