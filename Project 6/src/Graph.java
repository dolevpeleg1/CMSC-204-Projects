/**
 * Data structure class, represents a graph with vertexes of towns, and edges of roads
 * @author Dolev Peleg
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Graph implements GraphInterface<Town,Road>
{
	// Fields
	private ArrayList<LinkedList<Town>> towns;
	private Set<Town> townsSet;
	private Set<Road> roadsSet;
	
	// Default constructor
	public Graph()
	{
		towns = new ArrayList<>(); // Create a new ArrayList of LinkedList<Town>
		townsSet = new HashSet<>(); // Create a new set of towns
		roadsSet = new HashSet<>(); // Create a new set of roads
	}
	
	/**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
    public Road getEdge(Town sourceTown, Town destinationTown)
    {
    	Road result = null;
    	// If either town is null, return null
    	if(sourceTown == null || destinationTown == null)
    	{
    		return null;
    	}
    	else
    	{
    		Iterator<Road> iterator = roadsSet.iterator();
        	Road checkedRoad;
        	
        	// Iterate through the roads set
        	while(iterator.hasNext())
        	{
        		checkedRoad = iterator.next();
        		// If the current road's source town equals to the source town, and its destination is equal to the destination town, return it
        		if(checkedRoad.contains(sourceTown) && checkedRoad.contains(destinationTown))
        		{
        			return checkedRoad;
        		}
        	}
    	}
    	
    	return result; // This will return null if the road does not exist
    }

    /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
    public Road addEdge(Town sourceTown, Town destinationTown, int weight, String description)
    {
    	// If road already exist, return null
    	if(containsEdge(sourceTown, destinationTown))
    	{
    		return null;
    	}
    	Road result;
    	int sourceTownIndex = 0;
    	int destinationTownIndex = 0;
    	// If one of the towns is null, throw a NullPointerException
    	if(sourceTown == null || destinationTown == null)
    	{
    		throw new NullPointerException("One of the towns is null");
    	}
    	
    	// Check if towns contain both towns
    	/*boolean sourceFound = false;
    	boolean destinationFound = false;
    	for(int i = 0; i < towns.size(); i++)
    	{
    		// If the array list contain a linked list with the source town as the first town, set the sourceFound to true
    		if(towns.get(i).getFirst().equals(sourceTown))
    		{
    			sourceFound = true;
    			sourceTownIndex = i; // Set the index of the source town to i
    		}
    		// Else if the array list contain a linked list with the destination town as the first town, set the destinationFound to true
    		else if(towns.get(i).getFirst().equals(destinationTown))
    		{
    			destinationFound = true;
    			destinationTownIndex = i; // Set the index of the destination town to i
    		}
    	}*/
    	// If one of the towns is not found, throw an IllegalArgumentException
    	if(!containsVertex(sourceTown) || !containsVertex(destinationTown))
    	{
    		throw new IllegalArgumentException("One of the towns is not in the graph");
    	}
    	// this block of code will execute only if the graph contains both towns, and they are not null
    	else
    	{
    		// Add the destination town to the linked list of the source town, and vice versa
    		towns.get(sourceTownIndex).add(destinationTown);
    		towns.get(destinationTownIndex).add(sourceTown);
    		
    		// Create a new road with the result and add it to the roads set
    		result = new Road(sourceTown, destinationTown, weight, description);
    		roadsSet.add(result);
    		
    		// Return the new road
    		return result;
    	}
    }
    
    /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
    public boolean addVertex(Town town) 
    {
    	// If the towns list contains the town, return false
    	if(townsSet.contains(town))
    	{
    		return false;
    	}
    	// If the towns list does not contain the town
    	else
    	{
    		// Create a new linked list with the town
    		LinkedList<Town> newTownList = new LinkedList<>();
    		newTownList.add(town);
    		
    		towns.add(newTownList); // Add the town's new list it to the array list
    		townsSet.add(town); // Add the town to the towns list
    		return true;
    	}
    }

    /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
    public boolean containsEdge(Town sourceTown, Town destinationTown)
    {
    	// If the towns set does not contain both of the towns, return false
    	if(!containsVertex(sourceTown) || !containsVertex(destinationTown))
    	{
    		return false;
    	}
    	// Else
    	else
    	{
    		Iterator<Road> roadIterator = roadsSet.iterator();
    		Road currentRoad;
    		boolean found = false;
    		while(roadIterator.hasNext() && !found)
    		{
    			currentRoad = roadIterator.next();
    			if(currentRoad.contains(sourceTown) && currentRoad.contains(destinationTown))
    			{
    				found = true;
    			}
    		}
    		return found;
    	}
    	//return false; // This line of code should never execute
    }

    /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
    public boolean containsVertex(Town town)
    {
		// For loop to get the linked list of the source town
		for(int i = 0; i < towns.size(); i++)
		{
			// When the linked list of the source town is found
			if(towns.get(i).getFirst().equals(town))
			{
				return true;
			}
		}
		return false;
    }

    /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
    public Set<Road> edgeSet()
    {
    	// Create a copy of the roadSet
    	Set<Road> result = new HashSet<>();
    	Iterator<Road> iterator = roadsSet.iterator();
    	while(iterator.hasNext())
    	{
    		result.add(iterator.next());
    	}
    	
    	return result; // Return the new set
    }

    /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
    public Set<Road> edgesOf(Town town)
    {
    	Set<Road> result = new HashSet<>();
    	
    	Iterator<Road> iterator = roadsSet.iterator();
    	Road checkedRoad;
    	
    	// Iterate through the roads set
    	while(iterator.hasNext())
    	{
    		checkedRoad = iterator.next();
    		// If the current road's source town equals to the town, add it to the set
    		if(checkedRoad.getSource().equals(town))
    		{
    			result.add(checkedRoad);
    		}
    	}
    	
    	return result; // Return the set
    }

    /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
    public Road removeEdge(Town sourceTown, Town destinationTown, int weight, String description)
    {
    	Road result = null;
    	Road removedRoad = new Road(sourceTown, destinationTown, weight, description);
    	
    	boolean roadExist = false;
    	
    	// Removing the road from the road set
    	Iterator<Road> iterator = roadsSet.iterator();
    	Road checkedRoad;
    	
    	// Iterate through the roads set while it has next and the road is not found
    	while(iterator.hasNext() && !roadExist)
    	{
    		checkedRoad = iterator.next();
    		// If the current road is equal to removedRoad
    		if(checkedRoad.equals(removedRoad))
    		{
    			roadExist = true;
    			roadsSet.remove(checkedRoad); // Remove the road from the road set
    			result = checkedRoad; // Set result to the checked road		
    		}
    	}
    	
    	// If the road does not exist, return null
    	if(!roadExist)
    	{
    		return null;
    	}
    	
    	// Removing the road from the linked list in the array list
    	// For loop to get the linked list of the source town
    	for(int i = 0; i < towns.size(); i++)
    	{
    		// If the linked list of the source town is found
    		if(towns.get(i).getFirst().equals(sourceTown))
    		{
    			// If the linked list contain the destination town, remove it from the linked list
    			if(towns.get(i).contains(destinationTown))
    			{
    				towns.get(i).remove(destinationTown);
    			}
    		}
    		// Else if the linked list of the destination town is found
    		else if(towns.get(i).getFirst().equals(destinationTown))
    		{
    			// If the linked list contain the source town, remove it from the linked list
    			if(towns.get(i).contains(sourceTown))
    			{
    				towns.get(i).remove(sourceTown);
    			}
    		}
    	}
    	
    	return result;
    }


    /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
    public boolean removeVertex(Town town)
    {
    	// If the townsSet does not contain the town, return false
    	if(!containsVertex(town))
    	{
    		return false;
    	}

    	townsSet.remove(town); // Remove the town from the townsSet
    	
    	// Removing any roads that contain the town
    	Iterator<Road> iterator = roadsSet.iterator();
    	Road checkedRoad;
    	
    	// Iterate through the roads set
    	while(iterator.hasNext())
    	{
    		checkedRoad = iterator.next();
    		// If the current road contains the town, remove it
    		if(checkedRoad.contains(town))
    		{
    			roadsSet.remove(checkedRoad);
    		}
    	}
    	
    	// Removing the town from the array list
    	// For loop to get the linked list of the source town
    	for(int i = 0; i < towns.size(); i++)
    	{
    		// When the linked list of the town is found, remove it
    		if(towns.get(i).getFirst().equals(town))
    		{
    			towns.remove(i);
    		}
    		// If the current index is not the linked list of the town, if it contains the town, remove it
    		else
    		{
    			if(towns.get(i).contains(town))
    			{
    				towns.get(i).remove(town);
    			}
    		}
    	}
    	
    	return true;
    }

    /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
    public Set<Town> vertexSet()
    {
    	Set<Town> result = new HashSet<>();
    	
    	// Iterate through the towns set and add all of it contents to the result
    	Iterator<Town> townIterator = townsSet.iterator();
    	while(townIterator.hasNext())
    	{
    		result.add(townIterator.next());
    	}
    	
    	return result;
    }
    
    
    /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */   
    public ArrayList<String> shortestPath(Town SourceTown, Town destinationTown)
    {
    	// ***** This method was not fully implemented, IT DOES NOT WORK *****
    	return null;
    }
    
    /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
    public void dijkstraShortestPath(Town sourceTown)
    {
    	// ***** This method was not fully implemented, IT DOES NOT WORK *****
    	DijkstraShortestPath shortestPath = new DijkstraShortestPath(towns.size());
    	
    	// Adding a linked list of nodes according to the amount of towns in towns
    	for(int i = 0; i < towns.size(); i++)
    	{
    		 List<Node> item = new ArrayList<Node>();
    		 shortestPath.adj.add(item);
    	}
    	
    	for(int i = 0; i < towns.size(); i++)
    	{
    		for(int j = 0; j < towns.get(i).size(); j++)
    		{
    			if(getEdge(towns.get(i).getFirst(), towns.get(0). get(j)) != null)
    			{
    				Road currentRoad = getEdge(towns.get(i).getFirst(), towns.get(0). get(j));
    				shortestPath.adj.get(i).add(new Node(j, currentRoad.getWeight()));
    			}
    		}
    	}
    	shortestPath.dijkstra(shortestPath.adj, 0);
    	
    }
}