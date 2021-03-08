package trees;

public class TreeNode {
	// package access members
	TreeNode leftNode, rightNode;   
	int data; int count;

	// initialize data and make this a leaf node
	public TreeNode( int nodeData ) { 
		data = nodeData;              
		leftNode = rightNode = null;  // node has no children
		count = 0; // useful for get depth of a node
	}
	
	// insert a new node in the node subtree; ignore duplicate values
	public void insert( int insertValue ) {
		// insert in left subtree
		if ( insertValue < data ) {
			// insert new TreeNode
			if ( leftNode == null )
				leftNode = new TreeNode( insertValue );
			else // continue traversing left subtree
				leftNode.insert( insertValue ); 
		}
		// insert in right subtree
		else if ( insertValue > data ) {
			// insert new TreeNode
			if ( rightNode == null )
				rightNode = new TreeNode( insertValue );
			else // continue traversing right subtree
				rightNode.insert( insertValue ); 
		}    
	}

	public int depth(TreeNode node, int d) {
		
		int depth = d;
		
		if (this.data == node.data)
			return depth + 1;
		
		if (this.data > node.data ) {
			depth++;
			
			if ( node.rightNode != null )
				depth = depth(node.rightNode, depth);
			else if (node.leftNode != null)
				depth = depth(node.leftNode, depth);
		}
		
		if (this.data < node.data) {
			depth++;
			
			if ( node.leftNode != null )
				depth = depth(node.leftNode, depth);
			else if (node.rightNode != null)
				depth = depth(node.rightNode, depth);
		}
		
		return depth;
	} 
}
