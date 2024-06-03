/**
 * JUnit Test class
 * @author Dolev Peleg
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT 
{
	// Create an ArrayList to hold all invalid passwords
	ArrayList<String> invalidPasswords = new ArrayList<>();
	
	String validPassword;
	
	@Before
	public void setUp() throws Exception {
		validPassword = "NicePasword1!"; // Valid password
	}

	@After
	public void tearDown() throws Exception {
		invalidPasswords = null; // Set the ArrayList to null
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		String tooShortPassword = "N"; // Length invalid
		try
		{
			assertTrue(PasswordCheckerUtility.isValidLength(tooShortPassword));
		}
		catch(LengthException e)
		{
			assertTrue("Successfully threw a LengthException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw another excpetion besides LengthException", false);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		String noUpperPassword = "nicepasword1!"; // Upper case invalid
		invalidPasswords.add(noUpperPassword);
		
		try
		{
			assertTrue(PasswordCheckerUtility.hasUpperAlpha(noUpperPassword));
		}
		catch(NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a NoUpperAlphaException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw another excpetion besides NoUpperAlphaException", false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		String noLowerPassword = "NICEPASWORD1!"; // Lower case invalid
		invalidPasswords.add(noLowerPassword);
		
		try
		{
			assertTrue(PasswordCheckerUtility.hasLowerAlpha(noLowerPassword));
		}
		catch(NoLowerAlphaException e)
		{
			assertTrue("Successfully threw a NoLowerAlphaException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw another excpetion besides NoLowerAlphaException", false);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		String weakPassword = "NiceP1!"; // Weak password
		try
		{
			assertTrue(PasswordCheckerUtility.isWeakPassword(weakPassword));
		}
		catch(WeakPasswordException e)
		{
			assertTrue("Successfully threw a WeakPasswordException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw another excpetion besides WeakPasswordException", false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		String badSequencePassword = "NicePassword1!"; // Bad sequence invalid
		try
		{
			assertTrue(PasswordCheckerUtility.noSameCharInSquence(badSequencePassword));
		}
		catch(InvalidSequenceException e)
		{
			assertTrue("Successfully threw a InvalidSequenceException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw another excpetion besides InvalidSequenceException", false);
		}
	}
	
	/**
	 * Test if the two passwords match
	 * This test should throw an UnmatchedExcpeion for second case
	 */
	@Test
	public void TestComparePasswordsWithReturn()
	{
		String validPassword = "NicePassword1!"; // Bad sequence invalid
		try
		{
			PasswordCheckerUtility.comparePasswords(validPassword, "n");
		}
		catch(UnmatchedException e)
		{
			assertTrue("Successfully threw an UnmatchedExcpeion", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw another excpetion besides UnmatchedExcpeion", false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		String noDigitPassword = "NicePasword!"; // Digit invalid
		try
		{
			assertTrue(PasswordCheckerUtility.hasDigit(noDigitPassword));
		}
		catch(NoDigitException e)
		{
			assertTrue("Successfully threw a NoDigitException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw another excpetion besides NoDigitException", false);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.isValidPassword(validPassword));
		}
		catch(Exception e)
		{
			assertTrue("Threw an excpetion", false);
		}
	}
	
	/**
	 * Test if the password has at least one special character
	 * One test should throw a NoSpecialCharException
	 */
	@Test
	public void testHasSpecialChar()
	{

		String noSpecialPassword = "NicePasword1"; // Special char invalid
		try
		{
			assertTrue(PasswordCheckerUtility.hasSpecialChar(noSpecialPassword));
		}
		catch(NoSpecialCharException e)
		{
			assertTrue("Successfully threw a NoDigitException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw another excpetion besides NoDigitException", false);
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() 
	{
		String invalidPassword = "asdf";
		try
		{
			PasswordCheckerUtility.isValidPassword(invalidPassword);
		}
		catch(Exception e)
		{
			invalidPasswords.add(invalidPassword);
		}
		
		assertEquals(invalidPasswords.get(0), "asdf");
	}
	
}
