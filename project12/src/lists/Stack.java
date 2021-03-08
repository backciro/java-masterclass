package lists;

public class Stack {
	
	public String id;
	private List stackList;
   
	// construct stack
	public Stack (String str) { 
		id = str;
		stackList = new List( "stack" );
	}
    
	// add object to stack
	public void push( Object object ) { 
		stackList.insertAtFront( object );
	}
  
	// remove object from stack
	public Object pop() throws EmptyListException { 
		
		try {
		return stackList.removeFromFront();
		}
		
		catch (EmptyListException ELIST) {
			throw ELIST;
		}
	}
	
	// determine if stack is empty
	public boolean isEmpty() { 
		return stackList.isEmpty();
	}
   
	// output stack contents
	public void print()	{ 
		stackList.print();
	}
	
	public int size() {
		return stackList.count();
	}
}
