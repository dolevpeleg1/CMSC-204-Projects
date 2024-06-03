/**
 * Utility class that has all the password requirement related methods
 * @author Dolev Peleg
 */
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility 
{
	/**
	 * Check if both passwords match
	 * @param password
	 * @param passwordConfirm
	 * @throws UnmatchedException
	 */
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException
	{
		// Check if passwords match, throw UnmatchedException if not
		if(!comparePasswordsWithReturn(password, passwordConfirm))
		{
			throw new UnmatchedException();
		}
		
	} // end comparePasswords
	
	/**
	 * Check if both passwords match, return a boolean to indicate it
	 * @param password
	 * @param passwordConfirm
	 * @return boolean indicating if both passwords match
	 */
	
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm)
	{
		boolean results = false; // Default return value
		
		// Check if both passwords string match
		if(password.equals(passwordConfirm))
		{
			results = true;
		}
		
		return results;
	} // end comparePasswordsWithReturn
	
	/**
	 * Check if password is at least 6 characters long
	 * @param password
	 * @return boolean indicating if password is shorter than 6 characters
	 * @throws LengthException
	 */
	public static boolean isValidLength(String password) throws LengthException
	{
		boolean result = false; // Default return value
		
		// If the password is shorter than 6 characters, throws LengthExcpetion
		if(password.length() < 6)
		{
			throw new LengthException();
		}
		
		result = true;
		return result;
	} // End isValidLength
	
	/**
	 * Check if password contains an upper case letter
	 * @param password to be checked
	 * @return boolean indicating if password contains an upper case letter
	 * @throws NoUpperAlphaException
	 */
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException
	{
		boolean results = false; // Default return value
		
		// For loop to check if any of the characters of the password are upper case 
		for(int counter = 0; counter < password.length(); counter++)
		{
			if(Character.isUpperCase(password.charAt(counter)))
			{
				// If any character in the password is upper case, set results to true, break the loop
				results = true;
				break;
			}
		}
		
		// If the password does not contain an upper case letter, throws NoUpperAlphaException
		if(results == false)
		{
			throw new NoUpperAlphaException();
		}
		
		return results;
	} // End hasUpperAlpha
	
	/**
	 * Check if password contains an lower case letter
	 * @param password to be checked
	 * @return boolean indicating if password contains an lower case letter
	 * @throws NoLowerAlphaException
	 */
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException
	{
		boolean results = false; // Default return value
		
		// For loop to check if any of the characters of the password are lower case 
		for(int counter = 0; counter < password.length(); counter++)
		{
			if(Character.isLowerCase(password.charAt(counter)))
			{
				// If any character in the password is lower case, set results to true, break the loop
				results = true;
				break;
			}
		}
		
		// If the password does not contain an lower case letter, throws NoLowerAlphaException
		if(results == false)
		{
			throw new NoLowerAlphaException();
		}
		
		return results;
	} // End hasLowerAlpha
	
	/**
	 * Check if password contains a digit
	 * @param password to be checked
	 * @return boolean indicating if password contains a digit
	 * @throws NoDigitException
	 */
	public static boolean hasDigit(String password) throws NoDigitException
	{
		boolean results = false; // Default return value
		
		// For loop to check if any of the characters of the password are digits
		for(int counter = 0; counter < password.length(); counter++)
		{
			if(Character.isDigit(password.charAt(counter)))
			{
				// If any character in the password is lower case, set results to true, break the loop
				results = true;
				break;
			}
		}
		
		// If the password does not contain a digit, throws NoDigitException
		if(results == false)
		{
			throw new NoDigitException();
		}
		
		return results;
	} // End hasDigit
	
	/**
	 * Check if password contains a special character
	 * @param password to be checked
	 * @return boolean indicating if password contains a special character
	 * @throws NoSpecialCharException
	 */
	public static boolean hasSpecialChar(String password) throws NoSpecialCharException
	{
		boolean results = false; // Default return value
		
		// Check for a special symbol using the hint from the write up provided
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(password);
		results = (!matcher.matches());

		
		// If the password does not contain a special character, throws NoSpecialCharException
		if(results == false)
		{
			throw new NoSpecialCharException();
		}
		
		return results;
	} // End hasSpecialChar
	
	/**
	 * Check if the password does not contain 2 of the same characters in sequence. Return true if they do not
	 * @param password to be checked
	 * @return boolean indication if the password does not contain 2 of the same characters in sequence. THIS METHOD RETURNS TRUE IF THERE ARE NO 2 CHARS IN SEQUENCE. I FOLLOWED THE JDOC - Dolev
	 * @throws InvalidSequenceException
	 */
	public static boolean noSameCharInSquence(String password) throws InvalidSequenceException
	{
		boolean results = true; // Default return value
		for(int counter = 0; counter < password.length() - 1; counter++)
		{
			if (password.charAt(counter) == password.charAt(counter + 1))
			{
				results = false;
				throw new InvalidSequenceException();
			}
		}
		
		return results;
	} // End noSameCharInSquence
	
	/**
	 * Check if the password meets all the requirements
	 * @param password to be checked
	 * @return boolean indicating if the password meets all the requirements
	 * @throws LengthException
	 * @throws NoUpperAlphaException
	 * @throws NoLowerAlphaException
	 * @throws NoDigitException
	 * @throws NoSpecialCharException
	 * @throws InvalidSequenceException
	 */
	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException,
	NoDigitException, NoSpecialCharException, InvalidSequenceException
	{
		boolean results = false; // Default return value
		
		// Calling all requirements checking methods, if none of them throw an exception, set the return value to true
		isValidLength(password);
		hasUpperAlpha(password);
		hasLowerAlpha(password);
		hasDigit(password);
		hasSpecialChar(password);
		noSameCharInSquence(password);
		results = true;
		
		return results;
	} // End isValidPassword
	
	/**
	 * Check if password is between 6 to 9 characters
	 * @param password
	 * @return boolean indicating if password is 6 to 9 characters
	 */
	public static boolean hasBetweenSixAndNineChars(String password)
	{
		boolean results = false; // Default return value
		
		// If the password is between 6 characters and 9 characters, set the return value to true
		if(password.length() >= 6 && password.length() <= 9)
		{
			results = true;
		}
		
		return results;
	} // End hasBetweenSixAndNineChars
	
	/**
	 * Check if password is weak
	 * @param password to be checked
	 * @return boolean indicating if the password is weak
	 * @throws WeakPasswordException
	 */
	public static boolean isWeakPassword(String password) throws WeakPasswordException
	{
		boolean results = true; // Default return value
		
		// Call hasBetweenSixAndNineChars, throw weakPasswordException if it returns true
		if(hasBetweenSixAndNineChars(password))
		{
			throw new WeakPasswordException();
		}
		results = false;
		
		return results;
	} // End isWeakPassword
	
	/**
	 * Create and return an ArrayList of the messages of the invalid passwords
	 * @param passwords to be checked
	 * @return an ArrayList with the messages of the invalid passwords
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords)
	{
		ArrayList<String> results = new ArrayList<>(); // Create an ArrayList to return
		
		// Create a for loop to go through all the passwords, adding their exception messages to the results ArrayList
		for(int counter = 0; counter < passwords.size(); counter++)
		{
			try
			{
				isValidPassword(passwords.get(counter));
			}
			catch(Exception e)
			{
				results.add(passwords.get(counter) + " -> " + e.getMessage());
			}
		}
		
		return results;
	} // End getInvalidPasswords
	
} // End PasswordCheckerUtility
