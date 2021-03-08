package trees;

public class Tree {
	private TreeNode root;
  
	//construct an empty Tree of integers
	public Tree() { 
		root = null; 
	}
 
	// insert a new node in the binary search tree
	public void insertNode( int insertValue ) {
		if ( root == null )
			root = new TreeNode( insertValue ); // create the root node here
		else
			root.insert( insertValue ); // call the insert method
	}

	public void print() { 
		reverseInOrderTraversal();
	}

	private void reverseInOrderTraversal() {
		reverseInOrderHelper(this.root);
		
	}

	private void reverseInOrderHelper(TreeNode node) {
		
		if (node == null)
			return;
		
			reverseInOrderHelper(node.rightNode);
			
			for (int i = 1; i < node.depth(this.root, 0); i++) System.out.print("   "); 
			System.out.println(node.data);
			
			reverseInOrderHelper(node.leftNode);
	
	}
 
}
