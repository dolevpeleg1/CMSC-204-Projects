/**
 * A data structure linked binary tree which inherits from the LinkedConverterTreeInterface
 * @author Dolev Peleg
 */
import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String>
{
	// Fields
	private TreeNode<String> rootNode;
	
	// Default constructor
	public MorseCodeTree()
	{
		rootNode = new TreeNode(""); // Creating a new root with an empty String as the root
		buildTree(); // Build the tree
	}

	/**
	 * Returns a reference to the root
	 * @return reference to root
	 */
	public TreeNode<String> getRoot()
	{
		return rootNode;
	}
	
	/**
	 * sets the root of the Tree
	 * @param newNode a TreeNode<T> that will be the new root
	 */
	public void setRoot(TreeNode<String> newNode)
	{
		rootNode = newNode;
	}

	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}
	
	/**
	 * Adds result to the correct position in the tree based on the code
	 * This method will call the recursive method addNode
	 * 
	 * @param code the code for the new node to be added
	 * 
	 */
	public void insert(String code, String letter)
	{
		addNode(rootNode, code, letter);
	}
	
	/**
	 * This is a recursive method that adds element to the correct position 
	 * in the tree based on the code.
	 * 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of addNode
	 * @param letter the data of the new TreeNode to be added
	 */
	public void addNode(TreeNode<String> root, String code, String letter)
	{
		// Base case, If the length of the code is 1
		if(code.length() == 1)
		{
			// If the character is "." store to the left of the current root
			if(code.equals("."))
			{
				// If the leftChild is not null, set its data to the letter
				if(root.leftChild != null)
				{
					root.leftChild.setData(letter);
				}
				// Else if the leftChild is null, initialize it with a new treeNode with the letter as its data
				else
				{
					root.leftChild = new TreeNode(letter);
				}
			}
			// Else if the character is "-" store to the right of the current root
			if(code.equals("-"))
			{
				
				// If the rightChild is not null, set its data to the letter
				if(root.rightChild != null)
				{
					root.rightChild.setData(letter);
				}
				// Else if the rightChild is null, initialize it with a new treeNode with the letter as its data
				else
				{
					root.rightChild = new TreeNode(letter);
				}
			}
		}
		// Else if there is more than one character
		else if(code.length() > 1)
		{
			// If the first character is "." new root becomes the left child
			if(code.charAt(0) == '.')
			{
				if(root.leftChild == null)
				{
					// If the left child is null, initialize it with a new node
					if(root.leftChild == null)
					{
						root.leftChild = new TreeNode(null);
					}
				}
				addNode(root.leftChild, code.substring(1), letter); // Calling addNode with the code starting from the next char
			}
			// Else if the first character is "-" new root becomes the right child
			else if(code.charAt(0) == '-')
			{
				// If the right child is null, initialize it with a new node
				if(root.rightChild == null)
				{
					root.rightChild = new TreeNode(null);
				}
				addNode(root.rightChild, code.substring(1), letter); // Calling addNode with the code starting from the next char
			}
		}
	}
	
	/**
	 * Fetch the data in the tree based on the code
	 * This method will call the recursive method fetchNode
	 * 
	 * @param code the code that describes the traversals within the tree
	 * @return the result that corresponds to the code
	 */
	public String fetch(String code)
	{
		return fetchNode(rootNode, code);
	}
	
	/**
	 * This is the recursive method that fetches the data of the TreeNode
	 * that corresponds with the code
	 * 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of fetchNode
	 * @return the data corresponding to the code
	 */
	public String fetchNode(TreeNode<String> root, String code)
	{
		// Base case, If the length of the code is 1
		if(code.length() == 1)
		{
			// If the character is "." fetch the data from the left child
			if(code.equals("."))
			{
				return (String)root.leftChild.getData();
			}
			// Else if the character is "-" fetch the data from right left child
			if(code.equals("-"))
			{
				return (String)root.rightChild.getData();
			}
		}
		// Else if there is more than one character
		else if(code.length() > 1)
		{
			// If the first character is "." new root becomes the left child
			if(code.charAt(0) == '.')
			{
				return fetchNode(root.leftChild, code.substring(1)); // Calling fetchNode with the code starting from the next char
			}
			// Else if the first character is "-" new root becomes the right child
			else if(code.charAt(0) == '-')
			{
				return fetchNode(root.rightChild, code.substring(1)); // Calling fetchNode with the code starting from the next char
			}
		}
		return ""; // This value should never be returned
	}
	
	/**
	 * This operation is not supported for a LinkedConverterTree
	 * @param data data of node to be deleted
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	public MorseCodeTree delete(String data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("This method is not supported in the MorseCodeTree");
	}
	
	/**
	 * This operation is not supported for a LinkedConverterTree
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	public MorseCodeTree update() throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("This method is not supported in the MorseCodeTree");
	}
	
	/**
	 * This method builds the LinkedConverterTree by inserting TreeNodes<T>
	 * into their proper locations
	 * 
	 */
	public void buildTree()
	{
		// Inserting all the letters into the tree with their codes
		insert(".-", "a");
		insert("-...", "b");
		insert("-.-.", "c");
		insert("-..", "d");
		insert(".", "e");
		insert("..-.", "f");
		insert("--.", "g");
		insert("....", "h");
		insert("..", "i");
		insert(".---", "j");
		insert("-.-", "k");
		insert(".-..", "l");
		insert("--", "m");
		insert("-.", "n");
		insert("---", "o");
		insert(".--.", "p");
		insert("--.-", "q");
		insert(".-.", "r");
		insert("...", "s");
		insert("-", "t");
		insert("..-", "u");
		insert("...-", "v");
		insert(".--", "w");
		insert("-..-", "x");
		insert("-.--", "y");
		insert("--..", "z");
	}
	
	/**
	 * Returns an ArrayList of the items in the linked converter Tree in LNR (Inorder) Traversal order
	 * Used for testing to make sure tree is built correctly
	 * @return an ArrayList of the items in the linked Tree
	 */
	public ArrayList<String> toArrayList()
	{
		ArrayList<String> result = new ArrayList<>(); // Create a new array list
		LNRoutputTraversal(rootNode, result); // Call the recursive method LNRoutputTraversal with the array list as a parameter
		return result; // Return the array list
	}
	
	
	/**
	 * The recursive method to put the contents of the linked converter tree in an ArrayList<T> 
	 * LNR (Inorder)
	 * @param root the root of the tree for this particular recursive instance
	 * @param list the ArrayList that will hold the contents of the tree in LNR order
	 */
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list)
	{
		// Base case: if the current node it null, return. 
		if(root == null)
		{
			return;
		}
		// Else
		else
		{
			LNRoutputTraversal(root.leftChild, list); // Call the method recursively with the left child as a parameter 
			list.add(root.getData()); // Add the current node's data to the list
			LNRoutputTraversal(root.rightChild, list); // Call the method recursively with the right child as a parameter 
		}
	}
}