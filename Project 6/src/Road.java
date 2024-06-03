/**
 * Data element class, represents an road as a node of a graph (edge)
 * @author Dolev Peleg
 */
public class Road implements Comparable<Road>
{
	// Fields
	private Town source;
	private Town destination;
	private int weight;
	private String name;
	
	// Parameterized constructor with specified weight
	public Road(Town source, Town destination,int degrees, String name)
	{
		this.source = source;
		this.destination = destination;
		this.weight = degrees;
		this.name = name;
	}
	
	// Parameterized constructor with weight preset at 1
	public Road(Town source, Town destination, String name)
	{
		this.source = source;
		this.destination = destination;
		this.weight = 1;
		this.name = name;
	}
	
	/**
	 * Returns true only if the edge contains the given town
	 * @param town
	 * @return boolean, true if the edge contain the town, false if otherwise
	 */
	public boolean contains(Town town)
	{
		// Return true if either the source or the destination are equal to the parameter
		if(source.equals(town) || destination.equals(town))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// toString method
	public String toString()
	{
		String firstTown;
		String secondTown;
		if(source.getName().compareTo(destination.getName()) > 0)
		{
			firstTown = source.getName();
			secondTown = destination.getName();
		}
		else
		{
			firstTown = destination.getName();
			secondTown = source.getName();
		}
		return "Road between " + firstTown + " and " + secondTown + ", weight: " + weight +", name: " + name;
	}
	
	// Getters
	public Town getSource() 
	{
		return source;
	}

	public Town getDestination() 
	{
		return destination;
	}

	public int getWeight() 
	{
		return weight;
	}

	public String getName() 
	{
		return name;
	}
	
	// compareTo method
	public int compareTo(Road o)
	{
		// If the names are the same, return 0, else return -1
		if(name.equals(o.getName()))
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}
	
	// equals method
	public boolean equals(Road otherRoad)
	{
		// Return true only if the road contains both the destination and the source, 
		// and has the same weight and name
		if(otherRoad.contains(source) && otherRoad.contains(destination) &&
			otherRoad.getWeight() == weight && otherRoad.getName().equals(name))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}