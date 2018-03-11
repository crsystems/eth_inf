/**
 * Main class of the Java program. 
 * 
 */

public class Main_02 {

    public static void main(String[] args) {
        
        // we print a heading and make it bigger using HTML formatting
        System.out.println("<h4>-- BinaryTree.java --</h4>");
	char[] tree = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O'};

	BinaryTree btree = new BinaryTree(tree);

	System.out.println(btree.toString());
    }
}

