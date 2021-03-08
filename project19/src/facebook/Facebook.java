package facebook;

import java.util.stream.Collectors;
import java.util.*;
import java.util.Map.Entry;

public class Facebook {
	
	private Map<String, Person> people = new HashMap<String, Person>();
	private List<Group> groups = new LinkedList<Group>();
	
	// person comparator
	
	final Comparator<Entry<String, Person>> pComp = (p1, p2) -> Long.compare(p1.getValue().getNumFriends(), p2.getValue().getNumFriends());
	final Comparator<Entry<String, Person>> fOf_Comp = (p1, p2) -> Long.compare(p1.getValue().getNfOf(), p2.getValue().getNfOf());
	
	// group comparator
	final Comparator<Group> gComp = (g1, g2) -> Integer.compare(g1.getSubs().size(), g2.getSubs().size());
	final Comparator<Entry<String, Person>> gSubsComp = (gS1, gS2) -> Integer.compare(gS1.getValue().getNumGr(), gS2.getValue().getNumGr());
	
	public void addPerson(String code, String name, String surname)
			throws PersonExistsException {
		
		if(this.people.containsKey(code))
			throw new PersonExistsException();
		
		else 
			this.people.put(code, new Person(code, name, surname));
	}

	public String getPerson(String code) throws NoSuchCodeException {

		if(!this.people.containsKey(code))
			throw new NoSuchCodeException();
		
		else
			return this.people.get(code).toString();
	}

	public void addFriendship(String codePerson1, String codePerson2)
			throws NoSuchCodeException {
		
		if (!this.people.containsKey(codePerson1) || !this.people.containsKey(codePerson2))
			throw new NoSuchCodeException();
		
		else {
			try {
				this.people.get(codePerson1).addFriend(this.people.get(codePerson2));
				this.people.get(codePerson2).addFriend(this.people.get(codePerson1));
			}
			catch (NoSuchCodeException NSCe) {
				NSCe.printStackTrace();
			}
		}		
	}

	public Collection<String> listOfFriends(String codePerson)
			throws NoSuchCodeException {
		
		Collection<String> retval = null;
		
		if (!this.people.containsKey(codePerson))
			throw new NoSuchCodeException();
		else 
			if (! this.people.get(codePerson).getFriends().isEmpty())
				retval = this.people.get(codePerson).getFriends().stream().map(Person::getCode).collect(Collectors.toCollection(TreeSet::new));
			
		return retval;
	}

	public Collection<String> friendsOfFriends(String codePerson)
			throws NoSuchCodeException {

		Collection<String> fOf = new ArrayList<String>();
		
		if (!this.people.containsKey(codePerson))
			throw new NoSuchCodeException();
		
		else {
			for ( Person P : this.people.get(codePerson).getFriends() )
				for ( Person secLvl : P.getFriends() )
					if (!secLvl.getCode().equals(codePerson) && !fOf.contains(secLvl.toString()))
						fOf.add(secLvl.getCode());
			
			if (!fOf.isEmpty())
				return fOf;
			else return null;
		}	
	}

	public Collection<String> friendsOfFriendsNoRepitition(String codePerson)
			throws NoSuchCodeException {
		
		if (!this.people.containsKey(codePerson))
			throw new NoSuchCodeException();
		
		else {
			return this.friendsOfFriends(codePerson).stream().distinct().sorted((a, b) -> a.compareTo(b)).collect(Collectors.toCollection(TreeSet::new));
		}
	}

	public void addGroup(String groupName) {
		
		Boolean isIn = false;
		
		for (Group G : this.groups)
			if (G.getID().equals(groupName))
				isIn = true;
		
		if (!isIn)
			this.groups.add(new Group(groupName));
		else 
			System.out.println("Group is already in the system!"
					+ "Use method 'addPersonToGroup()'");		
	}

	public Collection<String> listOfGroups() {
		if (!this.groups.isEmpty())
			return this.groups.stream().map(Group::getID).sorted((a, b) -> a.compareTo(b)).collect(Collectors.toCollection(TreeSet::new));
		else 
			return null;
	}

	public void addPersonToGroup(String codePerson, String groupName) {
		
		for (Group G : this.groups)
			if (G.getID().equals(groupName))
				G.subscibe(codePerson);
		
		try {
			if (this.people.containsKey(codePerson))
				this.people.get(codePerson).addGroup(groupName);
			} 
		catch (NoSuchCodeException e) {
				e.printStackTrace();
			}
	}

	public Collection<String> listOfPeopleInGroup(String groupName) {
		for (Group G : this.groups)
			if (G.getID().equals(groupName))
				return G.getSubs();
		
		return null;
	}

	public String personWithLargestNumberOfFriends() {		
		return this.people.entrySet().stream().max(pComp).get().getValue().getCode();
		
	}

	public String personWithMostFriendsOfFriends() {
		return this.people.entrySet().stream().max(fOf_Comp).get().getValue().getCode();
	}

	public String largestGroup() {
		return this.groups.stream().max(gComp).get().getID();
	}

	public String personInLargestNumberOfGroups() {
		return this.people.entrySet().stream().max(gSubsComp).get().getValue().getCode();
	}
}