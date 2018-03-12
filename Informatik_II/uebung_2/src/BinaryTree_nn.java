public class BinaryTree {
	private char[] tree;
	
	/**
	 * Determines the index of the father.
	 * 
	 * The root is its own father.
	 * 
	 * @param node index of the children
	 * @return index of the father
	 */
	public static int father(int node)
	{
	    return (node-1)/2;
	}
	
	/**
	 * Determines the index of the left child.
	 * 
	 * @param node index of the father
	 * @return index of the left child
	 */
	public static int leftChild(int node)
	{
	    return node*2+1;
	}
	
	/**
	 * Determines the index of the right child.
	 * 
	 * @param node index of the father
	 * @return index of the right child
	 */
	public static int rightChild(int node)
	{
	    return node*2+2;
	}
	
	/**
	 * Check if the given array represents a valid binary tree.
	 * 
	 * @param array a binary tree encoded as char array
	 * @throws IllegalArgumentException if check fails
	 */
	private static void checkTree(char[] array)
	{
	    if(array.length==0){
	        throw new IllegalArgumentException("Array has length 0.");
	    }
	    
	    for(int i=1; i<array.length; i++){
	        if(array[i]!=' ' && array[(i-1)/2]==' '){
	            throw new IllegalArgumentException("Node "+i+" does not have a father node.");
	        }
	    }
	}
	
	/**
	 * Create a new binary tree from the given array representation.
	 * 
	 * The array stores the values of the binary tree in breadth-first order. 
	 * A space encodes a missing node. 
	 * 
	 * @param array the new tree
	 * @throws IllegalArgumentException if array does not represent a valid binary tree.
	 */
	public BinaryTree(char[] array)
	{
		checkTree(array);
		this.tree = array;
	}
	
	/**
	 * convert {@link BinaryTree#tree} into indented form.
	 * 
	 * One line per node, depth-first. One space of indentation per depth.
	 */
	public String toString()
	{
	    return this.print(0,0);
	}
	
	public String print(int start, int height){
	    
	    if(start > (tree.length-1) || tree[start]==' '){
			return "";
		}
		
	    String str_tree = new String();
	    String whitespace = new String();
	    for(int i=1; i<=height; i++){
	        whitespace = whitespace + " ";
	    }
	     str_tree=str_tree+whitespace+tree[start]+"\n";
	     
	     str_tree=str_tree+print(start*2+1,height+1);
	     str_tree=str_tree+print(start*2+2,height+1);
	     
	     return str_tree;
	}
}

