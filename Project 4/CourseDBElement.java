/**
 * Data element class, represent a course DBE, has CDE as data fields
 * @author Dolev Peleg
 */
public class CourseDBElement implements Comparable<CourseDBElement>
{
	// Data fields
	private String CourseID;
	private int crn;
	private int numberOfCredits;
	private String roomNumber;
	private String instructorName;
	
	// Default constructor
	public CourseDBElement()
	{
	}
	
	// Parameterized constructor
	public CourseDBElement(String CourseID, int CRN, int numberOfCredits, String roomNumber, String instructorName)
	{
		this.CourseID = CourseID;
		this.crn = CRN;
		this.numberOfCredits = numberOfCredits;
		this.roomNumber = roomNumber;
		this.instructorName = instructorName;
	}

	// Getters and setters
	public String getID() 
	{
		return CourseID;
	}

	public void setCourseID(String courseID) 
	{
		CourseID = courseID;
	}

	public int getCRN() 
	{
		return crn;
	}

	public void setCRN(int cRN) 
	{
		crn = cRN;
	}

	public int getNumberOfCredits() 
	{
		return numberOfCredits;
	}

	public void setNumberOfCredits(int numberOfCredits) 
	{
		this.numberOfCredits = numberOfCredits;
	}

	public String getRoomNum() 
	{
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) 
	{
		this.roomNumber = roomNumber;
	}

	public String getInstructorName() 
	{
		return instructorName;
	}

	public void setInstructorName(String instructorName) 
	{
		this.instructorName = instructorName;
	}

	/*
	 * private String CourseID;
	private int crn;
	private int numberOfCredits;
	private String roomNumber;
	private String instructorName;
	 */
	// Overridden compareTo method
	@Override
	public int compareTo(CourseDBElement anotherCourseDBElement) 
	{
		// If all the fields are equal, return 0
		if(CourseID.equals(anotherCourseDBElement.getID()) &&
				crn == anotherCourseDBElement.getCRN() &&
				numberOfCredits == anotherCourseDBElement.numberOfCredits &&
				roomNumber.equals(anotherCourseDBElement.getRoomNum()) &&
				instructorName.equals(anotherCourseDBElement.getInstructorName()))
		{
			return 0;
		}
		// Else return 1
		else
		{
			return 1;
		}
	}
	
	// toString method
	public String toString()
	{
		String result;
		return result = "Course: " + CourseID + " CRN: " + crn + " Credits: " + numberOfCredits + " Instructor: " + instructorName + " Room: " + roomNumber;
	}
}
