/*********************************************************
 * filename: TestTree.java
 *
 * A file that builds a binary tree, fills it with
 * random integers, adds up all the integers in the tree,
 * and prints out the result.
 *********************************************************/

import java.util.Random;


/** class TreeNode
 *
 * Represents a node in class Tree.
 * Stores a value, and references to zero, one, or two children.
 */
class TreeNode {
	int value;
	TreeNode left, right;
	TreeNode(int v) {
		value = v;
		left = null;
		right = null;
	}
}


/** class Tree
 *
 * A binary tree data structure that stores TreeNodes.
 * Can add new TreeNodes, add all values in the tree,
 * and find minimum value in the tree.
 */
class Tree {
	private Random myRand;
	private TreeNode root;
	
	Tree() {
		root = null;
		myRand = new Random();
	}

	void add(int v) {
		TreeNode n = new TreeNode(v);
		
		if (root == null) {
			root = n;
			return;
		}

		// Put the new node into the tree.
		// Choose left or right branch at random.
		TreeNode curr = root;
		while (curr.left != null && curr.right != null) {
			if (pickLeft()) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		if (curr.left == null) {
			curr.left = n;
		} else {
			curr.right = n;
		}
	}

	int TreeSum() {
		return TreeSum(root);
	}
	private int TreeSum(TreeNode n) {
		// STUDENT FILL IN CODE HERE
	}
	
	int TreeMin() {
		return TreeMin(root);
	}
	private int TreeMin(TreeNode n) {
		// STUDENT FILL IN CODE HERE
	}
	
	private boolean pickLeft() { return myRand.nextBoolean(); }
}


/** class TestTree
 *
 * This class holds main() function for testing class Tree
 */
public class TestTree {
	static public void main(String[] args) {
		// Member variables for main()
		Random myRand = new Random();
		Tree myTree = new Tree();

		// Add 1000 random values to the tree.
		for(int i = 0; i < 1000; i++) {
			myTree.add(myRand.nextInt(5000) - 2500);
		}

		// Print out sum of tree elements and minimum tree element.
		System.out.println(myTree.TreeSum());
		System.out.println(myTree.TreeMin());
	}
}
