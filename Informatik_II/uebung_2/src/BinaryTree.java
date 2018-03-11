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
		if(node == 0){
			return 0;
		}
		
		int father = node/2;
		if(node%2 == 0){
			father--;
		}
		return father;
	}
	
	/**
	 * Determines the index of the left child.
	 * 
	 * @param node index of the father
	 * @return index of the left child
	 */
	public static int leftChild(int node)
	{
		return node*2 + 1;
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
	private static void checkTree (char[] array) throws IllegalArgumentException{
		if(array.length == 0){
			throw new IllegalArgumentException("Array has length 0");
		}
		
		for(int i = 0; i < array.length; i++){
			if(array[father(i)] == ' '){
				throw new IllegalArgumentException("Node " + i + " has no valid father!");
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
	public String toString(){
		/*try{
			BinaryTree chk = self.tree;
			checkTree(chk);
		}catch(IllegalArgumentException e){
			String str = new String("");
			return str;
		}*/

		return this.printSubtree(0);
	}


	/**
	 * create string in indented form of given subtree.
	 *
	 * One line per node, depth-first. One space of indentation per depth.
	 */
	private String printSubtree(int start){
		if(start > (tree.length-1)){
			return "";
		}

		String str = new String();
		
		str = String.valueOf(tree[start]) + "\n ";
		str = str + this.printSubtree(start*2+1);
		str = str + this.printSubtree(start*2+2);
		return str;
	}
}
