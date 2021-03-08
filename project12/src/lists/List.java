package lists;

public class List {
	private ListNode firstNode;
	private ListNode lastNode;
	private String name;  // string like "list" used in printing
  
	// construct an empty List with "list" as the name
	public List() { 
		this( "list" ); 
	} 
	
	// construct an empty List with a name
	public List( String listName ) {
		name = listName;
		firstNode = lastNode = null;
	}
  
	// insert Object at front of List
	public void insertAtFront( Object insertItem ) {
		if ( isEmpty() ) // firstNode and lastNode refer to same object
			firstNode = lastNode = new ListNode( insertItem );
		else // firstNode refers to new node
			firstNode = new ListNode( insertItem, firstNode );
	}
   
	// insert Object at end of List
	public void insertAtBack( Object insertItem ) {
		if ( isEmpty() ) // firstNode and lastNode refer to same Object
			firstNode = lastNode = new ListNode( insertItem );  
		else // lastNode's nextNode refers to new node
			lastNode = lastNode.nextNode = new ListNode( insertItem );
	}

	// remove first node from List
	public Object removeFromFront() throws EmptyListException {
		if ( isEmpty() ) // throw exception if List is empty
			throw new EmptyListException( name );   
		
		Object removedItem = firstNode.data; // retrieve data being removed 
		// update references firstNode and lastNode 
		if ( firstNode == lastNode )
			firstNode = lastNode = null;
		else
			firstNode = firstNode.nextNode;   
		return removedItem; // return removed node data

	} 
	
    // remove last node from List
	public Object removeFromBack() throws EmptyListException {
		if ( isEmpty() ) // throw exception if List is empty
			throw new EmptyListException( name );
		Object removedItem = lastNode.data; // retrieve data being removed
		// update references firstNode and lastNode
		if ( firstNode == lastNode )
			firstNode = lastNode = null;
		else { // locate new last node
			ListNode current = firstNode;
			// loop while current node does not refer to lastNode
			while ( current.nextNode != lastNode )
				current = current.nextNode;
			lastNode = current; // current is new lastNode
			current.nextNode = null;
		}
		return removedItem; // return removed node data 
	} 
	
	// determine whether list is empty
	public boolean isEmpty() { 
		return firstNode == null; // return true if List is empty
	}

	// output List contents
	public void print()	{
		if ( isEmpty() ) {
			System.out.println( "Empty " + name );
			return;
		}
		System.out.print( "The " + name + " is: " );
		ListNode current = firstNode;
		// while not at end of list, output current node's data
		while ( current != null ) {
			System.out.print( current.data.toString() + " " );
			current = current.nextNode;
		}
		System.out.println( "\n" );
	}
	
	public int count() {
		if ( isEmpty() ) {
			System.out.println( "Empty " + name );
			return -1;
		}
		int n = 0;
		ListNode current = firstNode;
		// while not at end of list, output current node's data
		while ( current != null ) {
			current = current.nextNode;
			n++;
		}
		
		return n;
	}
}
