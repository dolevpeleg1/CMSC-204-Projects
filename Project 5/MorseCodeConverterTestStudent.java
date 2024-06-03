 /**
  * Test class for MorseCodeConverter
  * @author Dolev Peleg
  */
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MorseCodeConverterTestStudent {
	
	@Test
	public void testConvertToEnglishString() {	
		String converter1 = MorseCodeConverter.convertToEnglish(".... . .-.. .-.. --- / .-- --- .-. .-.. -.. ");
		assertEquals("hello world",converter1);
	}
	
	/**
	 * Testing for correct implementation of tree and traversal
	 */
	
	@Test
	public void testPrintTree() {	
		
		assertEquals("h s v i f u e l r a p w j  b d x n c k y t z g q m o", MorseCodeConverter.printTree());
	}
	
	/**
	 * Testing for correct conversion of all characters using key phrase to hit all letters
	 */
	
	@Test
	public void testConvertMorseStringToEnglishString() {	
		
		String converter1 = MorseCodeConverter.convertToEnglish(".. / .- -- / -.. --- .-.. . ...- / .- -. -.. / - .... .. ... / .. ... / -- -.-- / - . ... -");
		assertEquals("i am dolev and this is my test", converter1);

	}
	@Test
	public void testConvertMorseFileToEnglishString() {	
		File file = new File("src/DolevIsHungry.txt"); 
		try {
			assertEquals("my name is dolev and i did not eat lunch yet therefore i am hungry", MorseCodeConverter.convertToEnglish(file));
		} catch (FileNotFoundException e) {
			assertTrue("An unwanted exception was caught", false);
		}
	}
	

}
