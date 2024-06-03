/**
 * Data class representing a TreeNode. 
 * @author Dolev Peleg
 */
public class TreeNode<T> 
{
	// Fields
	private T data;
	TreeNode leftChild;
	TreeNode rightChild;
	
	// Constructors
	
	// Create a new TreeNode with left and right child set to null and data set to the dataNode
	public TreeNode(T dataNode)
	{
		data = dataNode;
		leftChild = null;
		rightChild = null;
	}
	
	// Constructor for making deep copies
	public TreeNode(TreeNode<T> node)
	{
		
	}
	
	/**
	 * Return the data within this TreeNode
	 * @return T, the data
	 */
	public T getData()
	{
		return data;
	}
	
	/**
	 * Set the data within this TreeNode
	 */
	public void setData(T data)
	{
		this.data = data;
	}
	
}