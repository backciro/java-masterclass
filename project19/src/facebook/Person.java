package facebook;

import java.util.*;
import java.util.stream.Collectors;

public class Person {
	
	private String ID, firstName, lastName;
	
	private List<Person> allFriends = new LinkedList<Person>();
	private List<String> myGroups = new LinkedList<String>();

	public Person(String iD, String firstN, String lastN) {
		this.ID = iD;
		this.firstName = firstN;
		this.lastName = lastN;
	}
	
	public String getCode() {
		return this.ID;
	}
	
	public String getFirst() {
		return this.firstName;
	}
	
	public String getLast() {
		return this.lastName;
	}
	
	public void addFriend(Person P) throws NoSuchCodeException {
	
		if (this.allFriends.contains(P))
			throw new NoSuchCodeException();
		else
			this.allFriends.add(P);	
	}
	
	public void addGroup(String arg) throws NoSuchCodeException {
		
		if (this.myGroups.contains(arg))
			throw new NoSuchCodeException();
		else
			this.myGroups.add(arg);
	}
	
	public List<Person> getFriends() {
			return this.allFriends.stream().sorted((a, b) -> a.getCode().compareTo(b.getCode())).collect(Collectors.toList());
	}
	
	public long getNumFriends() {
		return this.allFriends.size();
	}
	
	public int getNumGr() {
		return this.myGroups.size();
	}
	
	public long getNfOf() {
		
		long fOf = 0;
		
		for ( Person secLvl : this.allFriends )
			fOf += secLvl.getFriends().size();
		
		return fOf;
	}
	
	public String toString() {
		return this.ID + " " + this.firstName + " " + this.lastName;
	}
}
