package u7a3;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.util.ArrayList;

public class Tests {
	private IBinarySearchTreeUtils<String> utils;
	private ArrayList<BinarySearchTree<String>> trees;
	
	/** helper functions **/
	@Before public void setup()
	{
		final int numberofTrees = 7;
		utils = UtilsFactory.create();
		trees = new ArrayList<BinarySearchTree<String>>(numberofTrees);
		for (int i=0; i<numberofTrees; i++) {
			trees.add(new BinarySearchTree<String>(i, String.valueOf(i)));
		}
	}
	
	private BinarySearchTree<String> get(int index)
	{
		return trees.get(index);
	}
	
	private void linkLeft(int father, int child)
	{
		get(father).left = get(child);
	}

	private void linkRight(int father, int child)
	{
		get(father).right = get(child);
	}

	/*
	 *             3
	 *         1        5
	 *      0    2   4    6  
	 */
	private BinarySearchTree<String> balancedTree()
	{
		linkLeft(3 ,1);
		linkRight(3, 5);
		linkLeft(1, 0);
		linkRight(1, 2);
		linkLeft(5, 4);
		linkRight(5, 6);
		return get(3);
	}
	
	/*
	 *       6
	 *      5
	 *     4
	 *    3
	 *   2
	 *  1
	 * 0
	 */
	private BinarySearchTree<String> leftList()
	{
		for (int i=trees.size()-1; i>0; i--) {
			linkLeft(i, i-1);
		}
		return get(6);
	}

	/*
	 * 0
	 *  1
	 *   2
	 *    3
	 *     4
	 *      5
	 *       6
	 */
	private BinarySearchTree<String> rightList()
	{
		for (int i=0; i<trees.size() - 1; i++) {
			linkRight(i, i+1);
		}
		return get(0);
	}
	
	/*
	 *                        4
	 *                1               5
	 *          0         3                 6
	 *                  2
	 */
	private BinarySearchTree<String> genericTree()
	{
		linkRight(4, 5);
		linkRight(5, 6);
		linkLeft(4, 1);
		linkLeft(1, 0);
		linkRight(1, 3);
		linkLeft(3, 2);
		return get(4);
	}

	private void checkOrder(String expected, ArrayList<String> objects)
	{
		Assert.assertEquals(expected, objects.toString());
 	}
		
	/** tests for height **/
	@Test public void heightZero()
	{
		Assert.assertEquals(0, utils.height(null));
	}
	
	@Test public void heightOne()
	{
		Assert.assertEquals(1, utils.height(get(0)));
	}
	
	@Test public void heightLeftList()
	{
		Assert.assertEquals(7, utils.height(leftList()));
	}

	@Test public void heightRightList()
	{
		Assert.assertEquals(7, utils.height(rightList()));
	}

	@Test public void heightBalanced()
	{
		Assert.assertEquals(3, utils.height(balancedTree()));
	}
	
	@Test public void heightGeneric()
	{
		Assert.assertEquals(4, utils.height(genericTree()));
	}
	
	/** tests for isLeafe **/
	@Test  public void isLeafe()
	{
		Assert.assertTrue(utils.isLeaf(get(0)));
	}
	
	@Test public void isLeafChildLeft()
	{
		linkLeft(0, 1);
		Assert.assertFalse(utils.isLeaf(get(0)));
	}
	
	@Test public void isLeafChildRight()
	{
		linkRight(0, 1);
		Assert.assertFalse(utils.isLeaf(get(0)));
	}
	
	@Test public void isLeafeTwoChildren()
	{
		linkRight(0, 1);
		linkLeft(0, 2);
		Assert.assertFalse(utils.isLeaf(get(0)));
	}
	
	/** tests for hasOneChild **/
	@Test public void hasOneChildNoChildren()
	{
		Assert.assertFalse(utils.hasOneChild(get(0)));
	}
	
	@Test public void hasOneChildLeftChild()
	{
		linkLeft(0, 1);
		Assert.assertTrue(utils.hasOneChild(get(0)));
	}
	
	@Test public void hasOneChildRightChild()
	{
		linkRight(0, 1);
		Assert.assertTrue(utils.hasOneChild(get(0)));
	}
	
	@Test public void hasOneChildTwoChildren()
	{
		linkLeft(0, 1);
		linkRight(0, 2);
		Assert.assertFalse(utils.hasOneChild(get(0)));
	}
	
	/** tests for preOrder **/
	@Test public void preOrderBalanced()
	{
		ArrayList<String> objects = utils.preOrder(balancedTree());
		checkOrder("[3, 1, 0, 2, 5, 4, 6]", objects);
	}

	@Test public void preOrderLeftList()
	{
		ArrayList<String> objects = utils.preOrder(leftList());
		checkOrder("[6, 5, 4, 3, 2, 1, 0]", objects);
	}
	
	@Test public void preOrderRightList()
	{
		ArrayList<String> objects = utils.preOrder(rightList());
		checkOrder("[0, 1, 2, 3, 4, 5, 6]", objects);		
	}

	@Test public void preOrderGeneric()
	{
		ArrayList<String> objects = utils.preOrder(genericTree());
		checkOrder("[4, 1, 0, 3, 2, 5, 6]", objects);				
	}

	/** tests for inOrder **/
	private void testOrder(BinarySearchTree<String> root)
	{
		checkOrder("[0, 1, 2, 3, 4, 5, 6]", utils.inOrder(root));
	}
	
	@Test public void inOrderBalanced() { testOrder(balancedTree()); }
	@Test public void inOrderLeftList() { testOrder(leftList()); }
	@Test public void inOrderRightList() { testOrder(rightList()); }
	@Test public void inOrderGeneric() { testOrder(genericTree()); }

	/** test for postOrder **/
	@Test public void postOrderBalanced()
	{
		ArrayList<String> objects = utils.postOrder(balancedTree());
		checkOrder("[0, 2, 1, 4, 6, 5, 3]", objects);
	}

	@Test public void postOrderLeftList()
	{
		ArrayList<String> objects = utils.postOrder(leftList());
		checkOrder("[0, 1, 2, 3, 4, 5, 6]", objects);
	}
	
	@Test public void postOrderRightList()
	{
		ArrayList<String> objects = utils.postOrder(rightList());
		checkOrder("[6, 5, 4, 3, 2, 1, 0]", objects);		
	}

	@Test public void postOrderGeneric()
	{
		ArrayList<String> objects = utils.postOrder(genericTree());
		checkOrder("[0, 2, 3, 1, 6, 5, 4]", objects);				
	}

	/** tests for insert **/
	private BinarySearchTree<String> testInsert(int... keys)
	{
		BinarySearchTree<String> tree = null;
		for (int key: keys) {
			tree = utils.insert(tree, key, String.valueOf(key));
		}
		checkOrder("[0, 1, 2, 3, 4, 5, 6]", utils.inOrder(tree));
		return tree;
	}	
	
	@Test public void insertRightList()
	{
		testInsert(0, 1, 2, 3, 4, 5, 6);
	}
	
	@Test public void insertLeftList()
	{
		testInsert(6, 5, 4, 3, 2, 1, 0);
	}
	
	@Test public void insertBalanced()
	{
		testInsert(3, 1, 0, 2, 5, 4, 6);
	}
	
	@Test public void insertGeneric()
	{
		testInsert(6, 3, 4, 0, 1, 2, 5);
	}

	@Test public void insertOverwrite()
	{
		BinarySearchTree<String> tree = testInsert(6, 3, 4, 0, 1, 2, 5);
		tree = utils.insert(tree, 3, "test");
		checkOrder("[0, 1, 2, test, 4, 5, 6]", utils.inOrder(tree));
	}
	
	/** tests for find **/
	@Test public void findEmpty()
	{
		Assert.assertEquals(null, utils.find(null, 1));
	}
	
	@Test public void findSingle()
	{
		Assert.assertEquals("0", utils.find(get(0), 0));
	}
	
	private void findAll(BinarySearchTree<String> root)
	{
		for (BinarySearchTree<String> tree: trees) {
			String result = (String) utils.find(root, tree.key);
			Assert.assertEquals((String)tree.thing, result);
		}
	}
	
	@Test public void findLeftList() {findAll(leftList());}
	@Test public void findRightList() { findAll(rightList()); }
	@Test public void findBalanced() {findAll(balancedTree());}
	@Test public void findGeneric() { findAll(genericTree()); }
	
	/** tests for remove **/
	private void testRemove(BinarySearchTree<String> tree, int key)
	{
		ArrayList<Integer> keys = new ArrayList<Integer>(7);
		for (int i=0; i<7; i++) {
			keys.add(i);
		}
		keys.remove(key);
		BinarySearchTree<String> result = utils.remove(tree, key);
		checkOrder(keys.toString(), utils.inOrder(result));
	}
	
	
	@Test public void removeLeftList0() { testRemove(leftList(), 0); }
	@Test public void removeLeftList3() { testRemove(leftList(), 3); }
	@Test public void removeLeftList6() { testRemove(leftList(), 6); }

	@Test public void removeRightList0() { testRemove(rightList(), 0); }
	@Test public void removeRightList3() { testRemove(rightList(), 3); }
	@Test public void removeRightList6() { testRemove(rightList(), 6); }

	@Test public void removeBalanced0() { testRemove(balancedTree(), 0); }
	@Test public void removeBalanced3() { testRemove(balancedTree(), 3); }
	@Test public void removeBalanced6() { testRemove(balancedTree(), 6); }

	@Test public void removeGeneric0() { testRemove(genericTree(), 0); }
	@Test public void removeGeneric1() { testRemove(genericTree(), 1); }
	@Test public void removeGeneric3() { testRemove(genericTree(), 3); }
	@Test public void removeGeneric4() { testRemove(genericTree(), 4); }
	@Test public void removeGeneric6() { testRemove(genericTree(), 6); }
	
	@Test public void removeNotFound(){
		BinarySearchTree<String> result = utils.remove(genericTree(), 8);
		ArrayList<Integer> keys = new ArrayList<Integer>(7);
		for (int i=0; i<7; i++) {
			keys.add(i);
		}
		checkOrder(keys.toString(), utils.inOrder(result));
	}

	// Test on a more complex tree
	@Test public void removeComplex(){
		/*
		 * 				 -------40------
		 * 				/				\
		 * 			   20				70
		 * 			  /   \			  /    \
		 * 		     10	  30		60		80
		 * 			/		\	   /  \
		 * 			9	    31    50  61
		 * 							\	
		 * 							51
		 */
		BinarySearchTree<String> n40 = new BinarySearchTree<String>(40, String.valueOf(40));
		BinarySearchTree<String> n20 = new BinarySearchTree<String>(20, String.valueOf(20));
		BinarySearchTree<String> n10 = new BinarySearchTree<String>(10, String.valueOf(10));
		BinarySearchTree<String> n9 = new BinarySearchTree<String>(9, String.valueOf(9));
		BinarySearchTree<String> n30 = new BinarySearchTree<String>(30, String.valueOf(30));
		BinarySearchTree<String> n31 = new BinarySearchTree<String>(31, String.valueOf(31));
		BinarySearchTree<String> n70 = new BinarySearchTree<String>(70, String.valueOf(70));
		BinarySearchTree<String> n60 = new BinarySearchTree<String>(60, String.valueOf(60));
		BinarySearchTree<String> n50 = new BinarySearchTree<String>(50, String.valueOf(50));
		BinarySearchTree<String> n51 = new BinarySearchTree<String>(51, String.valueOf(51));
		BinarySearchTree<String> n61 = new BinarySearchTree<String>(61, String.valueOf(61));
		BinarySearchTree<String> n80 = new BinarySearchTree<String>(80, String.valueOf(80));
		
		// linking
		n40.left = n20;
		n40.right = n70;
		n20.left = n10;
		n20.right = n30;
		n10.left = n9;
		n30.right = n31;
		n70.left = n60;
		n70.right = n80;
		n60.left = n50;
		n60.right = n61;
		n50.right = n51;
		
		// Remove the root
		BinarySearchTree<String> result = utils.remove(n40, 40);
		// Node with key 50 should be the new root
		checkOrder("[9, 10, 20, 30, 31, 50, 51, 60, 61, 70, 80]", utils.inOrder(result));
		
		// Remove node with key 31
		result = utils.remove(result, 31);
		checkOrder("[9, 10, 20, 30, 50, 51, 60, 61, 70, 80]", utils.inOrder(result));
		
		// Remove node with key 20
		result = utils.remove(result, 20);
		checkOrder("[9, 10, 30, 50, 51, 60, 61, 70, 80]", utils.inOrder(result));
	}
}	
