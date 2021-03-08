package lists;

public class ListNode {
	// package access members;
	Object data;    
    ListNode nextNode;
    
    // create a ListNode that refers to object 
    ListNode( Object object ) { 
    	this( object, null ); 
	}
    
    // create a ListNode that refers to Object and to next ListNode
	ListNode( Object object, ListNode node ) {
		data = object;    
		nextNode = node;  
	}
	// return reference to data in node
	Object getObject() { 
		return data; // return Object in this node
	}
   
	// return reference to next node in list
	ListNode getNext() { 
		return nextNode; // get next node
	}
}
