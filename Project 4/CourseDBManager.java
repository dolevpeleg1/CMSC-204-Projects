import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Data Structure class, represent a CDM
 * @author Dolev Peleg
 */

public class CourseDBManager implements CourseDBManagerInterface
{
	// Data field
	CourseDBStructure courseDBS;
	
	// Default constructor
	public CourseDBManager()
	{
		courseDBS = new CourseDBStructure(99); // Create a new CourseDBStructure with a default size of 50
	}
	
	/**
	 * Adds a course (CourseDBElement) with the given information
	 * to CourseDBStructure.
	 * @param id course id 
	 * @param crn course crn
	 * @param credits number of credits
	 * @param roomNum course room number
	 * @param instructor name of the instructor
	 */
	public void add(String id, int crn, int credits, String roomNum, String instructor)
	{
		courseDBS.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
	}
	
	/**
	 * finds  CourseDBElement based on the crn key
	 * @param crn course crn (key)
	 * @return a CourseDBElement object, return null if it is not found
	 */
	public CourseDBElement get(int crn)
	{
		try
		{
			return courseDBS.get(crn);
		}
		//If the course was not found, print a message and return null
		catch(Exception e)
		{
			System.out.println("An exception was thrown");
			return null; // Returning null because it was not specified to throw the exception if the course is not found
		}
		
	}
	
	/**
	 * Reads the information of courses from a test file and adds them
	 * to the CourseDBStructure data structure
	 * @param input input file 
	 * @throws FileNotFoundException if file does not exists
	 */
	public void readFile(File input) throws FileNotFoundException
	{
		Scanner fileData = new Scanner(input); // Create a scanner
		// While there are more lines in the files
		while(fileData.hasNextLine())
		{
			String currentLine = fileData.nextLine();
			List<String> fields = new ArrayList<>(Arrays.asList(currentLine.split(" "))); // Create a list from the string, split the words by a space
			
			
			// If the current line is not empty or a comment
			if(currentLine != "" && currentLine.charAt(0) != '#')
			{
				if(
					// If there are at least 5 fields and
					fields.size() >= 5 && 
					// The first four characters of the first field are letters and
					Character.isAlphabetic(fields.get(0).charAt(0)) && Character.isAlphabetic(fields.get(0).charAt(1)) && Character.isAlphabetic(fields.get(0).charAt(2)) && Character.isAlphabetic(fields.get(0).charAt(3)) &&
					// The next three characters are numbers
					Character.isDigit(fields.get(0).charAt(4)) && Character.isDigit(fields.get(0).charAt(5)) && Character.isDigit(fields.get(0).charAt(6)) &&
					// All 5 characters of the second field are numbers
					fields.get(1).length() == 5 &&
					Character.isDigit(fields.get(1).charAt(0)) && Character.isDigit(fields.get(1).charAt(1)) && Character.isDigit(fields.get(1).charAt(2)) &&
					Character.isDigit(fields.get(1).charAt(3)) && Character.isDigit(fields.get(1).charAt(4)) &&
					// The third field is a single digit
					Character.isDigit(fields.get(2).charAt(0)) && fields.get(2).length() == 1 &&
					// The fourth field is either 2 letters and 3 numbers, or Distance-Learning or Distance Learning
					(Character.isAlphabetic(fields.get(3).charAt(0)) && Character.isAlphabetic(fields.get(3).charAt(1)) && Character.isDigit(fields.get(3).charAt(2)) && Character.isDigit(fields.get(3).charAt(3)) && Character.isDigit(fields.get(3).charAt(4))
					|| fields.get(3).equals("Distance-Learning") || fields.get(3).equals("Distance Learning"))
					) // End if
				{
					
					// Create a name field
					String name = "";
					// Add to the name every string after the fourth field
					for(int i = 4; i < fields.size(); i++)
					{
						if(i == 4 && fields.get(4).equals("Learning"))
						{
							fields.set(3, "Distance Learning");
							continue;
						}
						name += fields.get(i);
						if(i != fields.size() - 1)
						{
							name += " ";
						}
					}
					
					// Add all the fields from the line as a course
					add(fields.get(0), Integer.valueOf(fields.get(1)), Integer.valueOf(fields.get(2)), fields.get(3), name);
				}
			}
		}
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
		return courseDBS.showAll();
	}
}
