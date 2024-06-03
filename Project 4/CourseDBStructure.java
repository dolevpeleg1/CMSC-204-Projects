import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Data Structure class, represent a CDS
 * @author Dolev Peleg
 */
public class CourseDBStructure implements CourseDBStructureInterface 
{
	// Fields
	LinkedList<CourseDBElement>[] dbTable;
	int totalCourses;
	
	// Constant
	final double LOAD_FACTOR = 1.5; // Load factor
	
	// Constructor that creates a new CourseDBSturcture with a specified capacity
	public CourseDBStructure(int arraySize)
	{
		dbTable = new LinkedList[arraySize];
		totalCourses = 0;
	}
	
	// Constructor that creates a new CourseDBSturcture with a specified capacity, for testing purposes
	public CourseDBStructure(String s, int capacity)
	{
		this(capacity);
	}
	
	/** 
	* Adds a CourseDBElement object to the CourseDBStructure using the hashcode
	* of the CourseDatabaseElemen object's crn value.
	* If the CourseDatabaseElement already exists, exit quietly
	* @param element the CourseDBElement to be added to CourseDBStructure
	*/
	public void add(CourseDBElement element) 
	{
		String stringCRN = "" + element.getCRN(); // Coerce the crn into a string
		int key = calculateHashCode(stringCRN); // Calculate the hash code
			
		// If the bucket is empty
		if(dbTable[key] == null)
		{
			LinkedList<CourseDBElement> list = new LinkedList<>(); // Create a new linked list
			list.add(element); // Add the element to the list
			dbTable[key] = list; // Add the list to the table
		}
		// Else, if the bucket is not empty
		else
		{
			// If the bucket already contains the element, exit quietly
			if(dbTable[key].contains(element))
			{
				return;
			}
			try
			{
				// If the bucket already contain a course with this crn
				if(dbTable[key].contains(get(element.getCRN())))
				{
					dbTable[key].remove(get(element.getCRN())); // Remove it from the bucket
					totalCourses--; // Decrement the total courses in the structure
					dbTable[key].add(element); // Add the updated version of it
					
				}
			}
			// Catch any exceptions
			catch(IOException e)
			{
				dbTable[key].add(element); // Add the element to the bucket if an IOException is thrown
			}
		}
		
		totalCourses++; // Increment the total courses in the structure
		
		// If the load factor exceeds 1.5, resize the array and rehash
		if((double)totalCourses / dbTable.length > LOAD_FACTOR)
		{
			int additionalSize = 0;
			int counter = 0;
			
			// Adding size of the next 4k + 3 prime to the length
			while(additionalSize < (dbTable.length / LOAD_FACTOR))
			{
				additionalSize = 3 + 4 * counter;
			}
			
			LinkedList<CourseDBElement>[] temp = new LinkedList[dbTable.length];
			
			// Copy the table to temp
			for(int i = 0; i < dbTable.length; i++)
			{
				temp[counter] = dbTable[counter];
			}
			
			// Adding size to dbTable
			dbTable = new LinkedList[temp.length + additionalSize];
			int tempNum = totalCourses;
			
			totalCourses = 0; // Reset total courses
			
			// Rehash
			for(int i = 0; i < temp.length; i++)
			{
				for(int j = 0; j < temp[i].size(); j++)
				{
					add(temp[i].get(j));
				}
			}
		}
	}

	/**
	 * Find a courseDatabaseElement based on the key (crn) of the
	 * courseDatabaseElement If the CourseDatabaseElement is found return it If not,
	 * throw an IOException
	 * @param crn crn (key) whose associated courseDatabaseElement is to be returned
	 * @return a CourseDBElement whose crn is mapped to the key
	 * @throws IOException if key is not found
	 */
	public CourseDBElement get(int crn) throws IOException 
	{
		CourseDBElement result = null;
		boolean found = false;
		String stringCRN = "" + crn; // Coerce the crn into a string
		int key = calculateHashCode(stringCRN); // Calculate the hash code
		
		// If the bucket is empty, throw an IOException
		if(dbTable[key] == null)
		{
			throw new IOException();
		}
		// If the bucket is not empty
		else
		{
			int counter = 0;
			// Loop that iterates until the crn is found or the bucket was searched completely
			while(!found && counter < dbTable[key].size())
			{
				// If the crn of the current DBElemet in the bucket equals to the searched crn
				if(dbTable[key].get(counter).getCRN() == crn)
				{
					found = true; // Set found to true
					result = dbTable[key].get(counter); // Set result to the current DBE element in the bucket
				}
				// Else, increment the index of the current DBElement in the bucket
				else
				{
					counter++;
				}
			}	
		}
		
		// If the crn is not found, return it
		if(!found)
		{
			throw new IOException();

		}
		return result;
	}

	/**
	 * @return an array list of string representation of each course in 
	 * the data structure separated by a new line. 
	 * Refer to the following example:
	 * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	 * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	 */
	public ArrayList<String> showAll() 
	{
		ArrayList<String> result = new ArrayList<>();
		// Outer loop that iterates through each bucket in the array
		for(int bucket = 0; bucket < dbTable.length; bucket++)
		{
			// If the bucket is not null
			if(dbTable[bucket] != null)
			{
				// Inner loop that adds all the data from each course in the bucket
				for(int currentCourse = 0; currentCourse < dbTable[bucket].size(); currentCourse++)
				{
					result.add("\nCourse:" + dbTable[bucket].get(currentCourse).getID() + " " + 
					"CRN:" + dbTable[bucket].get(currentCourse).getCRN() + " " + 
					"Credits:" + dbTable[bucket].get(currentCourse).getNumberOfCredits() + " " +
					"Instructor:" + dbTable[bucket].get(currentCourse).getInstructorName() + " " + 
					"Room:" + dbTable[bucket].get(currentCourse).getRoomNum());
				}
			}
		}
		return result;
	}

	/**
	 * Get the length of the array
	 * @return int, length of the array
	 */
	public int getTableSize() 
	{
		return dbTable.length;
	}
	
	/**
	 * Calculate a hash code for a string
	 * @param s
	 * @return integer, the hash code
	 */
	public int calculateHashCode(String s)
	{
		int key = 0; // Value of the key that will be calculated from the string
		final int G = 31; // Multiply by this integer to get unique hash code
		
		// Computing hash code for the string
		for(int counter = 0; counter < s.length(); counter++)
		{
			key = G * key + s.charAt(counter); // calculate hash code
		}
		return key = key % dbTable.length; // Set the key
	}
	
	
}
