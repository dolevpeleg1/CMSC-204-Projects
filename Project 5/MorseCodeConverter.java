import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MorseCodeConverter 
{
	// Field
	static MorseCodeTree morseTree = new MorseCodeTree(); // Initialize the MorseCodeTree
	
	/**
	 * Return a string with all the data in the tree in LNR order with an space in between them
	 * @return String, the tree as a String in LNR order
	 */
	public static String printTree()
	{
		String result = morseTree.toArrayList().toString();
		result = result.replaceAll(",", ""); // Replace all the commas with spaces
		
		// Remove all the brackets
		result = result.substring(1,result.length() - 1);
		
		return result;
	}
	
	/**
	 * Convert Morse code into English. Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	 * @param code
	 * @return String, the code in English
	 */
	public static String convertToEnglish(String code)
	{
		String result = ""; // The returned String
		String currentWord = ""; // The current word being translated
		
		// For loop to iterate through all the words
		for(int i = 0; i < code.length(); i++)
		{
			// If the current character is a '/', add a space the to result
			if(code.charAt(i) == '/')
			{
				result += " ";
			}
			// Else if the current character is a space, or this is the last character in the code
			else if(code.charAt(i) == ' ' || i == code.length() - 1)
			{
				// If the character is not a space
				if(code.charAt(i) != ' ')
				{
					currentWord += code.charAt(i); // Add the current character to the current translated word
				}
				result += morseTree.fetch(currentWord); // Convert the current code to English, add it to the result
				currentWord = ""; // Set the current word to null
			}
			// Else, add the current character to the current word
			else
			{
				currentWord += code.charAt(i);
			}
		}
		return result;
	}
	
	/**
	 * Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	 * @param codeFile
	 * @return String, the code in English
	 * @throws FileNotFoundException
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException
	{
		String code = ""; // The code taken from the file
		String result = ""; // The returned String
		String currentWord = ""; // The current word being translated
		
		Scanner input = new Scanner(codeFile);
		// Add the code from the file to the code
		while(input.hasNextLine())
		{
			code += input.nextLine();
		}
		input.close(); // Close the scanner
		return convertToEnglish(code); // Call the other convertToEnglish method with the code as a parameter
	}
}