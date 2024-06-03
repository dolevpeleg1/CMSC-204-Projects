/**
 * Test class for MorseCodeTree
 * @author Dolev Peleg
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MorseCodeTreeTestStudent 
{
	MorseCodeTree tree;
	@BeforeEach
	public void setUp() throws Exception 
	{
		tree = new MorseCodeTree();
	}
	
	@AfterEach
	public void tearDown()
	{
		tree = null;
	}
	@Test
	/**
	 * This tests all the methods of MorseCodeTree besides fetch and fetchNode
	 */
	void testToArrayList() 
	{
		assertEquals("[h, s, v, i, f, u, e, l, r, a, p, w, j, , b, d, x, n, c, k, y, t, z, g, q, m, o]", tree.toArrayList().toString());
	}

	/**
	 * This method test both fetch methods of MorseCodeTree
	 */
	@Test
	void testFetch()
	{
		assertEquals("d", tree.fetch("-.."));
		assertEquals("o", tree.fetch("---"));
		assertEquals("l", tree.fetch(".-.."));
		assertEquals("e", tree.fetch("."));
		assertEquals("v", tree.fetch("...-"));
	}
}
