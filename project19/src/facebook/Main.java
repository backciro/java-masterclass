package facebook;

import java.util.Collection;

public class Main {

	public static void main(String[] args) {

		Facebook f = new Facebook();
		
		try {
			f.addPerson("Mario99", "Mario", "Rossi");
			f.addPerson("Mario88", "Mario", "Verdi");
			f.addPerson("Elena66", "Elena", "Aresti");
			f.addPerson("BigLupo", "Lupo", "Bianchi");
			f.addPerson("FFA", "Franca", "Rosetti");
			f.addPerson("Sally", "Sandra", "Sandroni");
			
			String s = f.getPerson("Mario99"); // "Mario99 Mario Rossi"
			System.out.println(s);
			
			
			f.addFriendship("Mario99", "BigLupo");
			f.addFriendship("Mario99", "Elena66");
			f.addFriendship("Elena66", "FFA");
			f.addFriendship("Mario88", "Sally");
			f.addFriendship("BigLupo", "FFA");
			
			Collection<String> friends = f.listOfFriends("Mario99"); // "Elena66" "BigLupo"    
			System.out.println(friends);
			friends = f.listOfFriends("Elena66"); // "FFA" "Mario99" "Sally"			
			System.out.println(friends);
			friends = f.friendsOfFriends("Mario99"); // "FFA" "Sally"
			System.out.println(friends);

			String id = f.personWithMostFriendsOfFriends(); // "FFA"
		//	String id = f.personWithLargestNumberOfFriends(); // "Mario99"
			System.out.println(id);
			
		} 
		catch (PersonExistsException e) {
		} 
		catch (NoSuchCodeException e) {
		}
	}
}