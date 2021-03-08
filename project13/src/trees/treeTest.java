package trees;

public class treeTest {
	public static void main( String args[] )
	{
		Tree tree = new Tree();
		int[] values = {49,28,83,18,40,71,97,11,19,32,44,69,72,92,99};

		System.out.println( "Inserting the following values: " );

		for ( int i = 0; i < values.length; i++ ) {
			System.out.print( values[i] + " " );
			tree.insertNode( values[i] );
		}
		System.out.println ( "\n\nTree print:" );
		tree.print(); // perform inorder traversal of tree
	}
}
