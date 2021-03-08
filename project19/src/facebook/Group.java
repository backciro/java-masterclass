package facebook;

import java.util.*;
import java.util.stream.Collectors;

public class Group {
	
	String idGroup;
	List<String> subscribers = new ArrayList<String>();
	
	public Group(String ID) {
		
		this.idGroup = ID;
	}
	
	public void subscibe(String code) {
		if (!this.subscribers.contains(code))
			this.subscribers.add(code);
		else
			System.out.println("User is already in the group!");
	}
	
	public String getID() {
		return this.idGroup;
	}
	
	public Collection<String> getSubs() {
		return this.subscribers.stream().sorted((a, b) ->a.compareTo(b)).collect(Collectors.toCollection(TreeSet::new));
	}	
	
	public String toString() {
		return this.idGroup + "   " + this.subscribers.size() + " subs";
	}
}
