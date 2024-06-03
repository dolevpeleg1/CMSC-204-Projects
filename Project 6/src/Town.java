/**
 * Data element class, represents an town as a node of a graph (vertex)
 * @author Dolev Peleg
 */
public class Town implements Comparable<Town>
{
	// Field
	private String name;
	
	// Constructor that has a name parameter
	public Town(String name)
	{
		this.name = name;
	}
	
	// Copy constructor
	public Town(Town templateTown)
	{
		this(templateTown.getName());
	}
	
	// Getter
	public String getName() 
	{
		return name;
	}
	
	// compareTo method
	public int compareTo(Town o)
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

	// toString method
	public String toString()
	{
		return "Town name: " + name;
	}
	
	// hashCode method
	public int hashCode()
	{
		return 0;
	}
	
	// equals method
	public boolean equals(Town otherTown)
	{
		// If the names are the same, return true, else return false
		if(name.equals(otherTown.getName()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}