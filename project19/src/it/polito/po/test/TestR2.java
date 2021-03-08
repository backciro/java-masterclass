package it.polito.po.test;

import java.util.Collection;

import facebook.*;
import junit.framework.TestCase;

public class TestR2 extends TestCase {

	public TestR2(String arg0) {
		super(arg0);

	}

	public void testR21Friendship() {
		Facebook m = new Facebook();
		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			m.addPerson("XYZ", "Alex", "Pato");
			m.addPerson("GGG", "Gennaro", "Gattuso");
			m.addFriendship("ABCD", "XYZ");
			m.addFriendship("ABCD", "GGG");
			Collection<String> friends = m.listOfFriends("ABCD");
			assertTrue(friends.contains("XYZ"));
			assertTrue(friends.contains("GGG"));

		} catch (PersonExistsException e) {
			fail();
		} catch (NoSuchCodeException e) {
			fail();
		}

	}

	public void testR22TwoWayFriendship() {
		Facebook m = new Facebook();
		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			m.addPerson("XYZ", "Alex", "Pato");
			m.addPerson("GGG", "Gennaro", "Gattuso");
			m.addFriendship("ABCD", "XYZ");
			m.addFriendship("ABCD", "GGG");
			Collection<String> friends = m.listOfFriends("ABCD");
			assertTrue(friends.contains("XYZ"));
			assertTrue(friends.contains("GGG"));
			Collection<String> friends2 = m.listOfFriends("GGG");
			assertTrue(friends2.contains("ABCD"));
			Collection<String> friends3 = m.listOfFriends("XYZ");
			assertTrue(friends3.contains("ABCD"));
		} catch (PersonExistsException e) {
			fail();
		} catch (NoSuchCodeException e) {
			fail();
		} catch (Exception e) {
			fail();
		}

	}

	public void testR23FriendshipNotExistingCode() {
		Facebook m = new Facebook();
		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			m.addPerson("XYZ", "Alex", "Pato");
			m.addPerson("GGG", "Gennaro", "Gattuso");
			m.addFriendship("ABCD", "XYZ");
			m.addFriendship("ABCD", "AAA");
			fail();
		} catch (PersonExistsException e) {
			fail();
		} catch (NoSuchCodeException e) {
			assert (true);
		}

	}

	public void testR24FriendshipNotExistingCode2() {
		Facebook m = new Facebook();
		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			m.addPerson("XYZ", "Alex", "Pato");
			m.addPerson("GGG", "Gennaro", "Gattuso");
			m.addFriendship("ABCD", "XYZ");
			m.addFriendship("ABCD", "GGG");
			m.listOfFriends("UUUU");
			fail();
		} catch (PersonExistsException e) {
			fail();
		} catch (NoSuchCodeException e) {
			assert (true);
		}

	}

	public void testR25FriendshipNull() {
		Facebook m = new Facebook();
		
		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			Collection<String> friends = m.listOfFriends("ABCD");
			assertEquals(friends, null);
		} catch (NoSuchCodeException e) {
			fail();
		} catch (PersonExistsException e) {
		   fail();
		}

	}

	public void testR26FriendshipSecondLevel() {
		Facebook m = new Facebook();
		//Pato <-> kaka <-> gattuso <-> (Pirlo,maldini)
		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			m.addPerson("XYZ", "Alex", "Pato");
			m.addPerson("GGG", "Gennaro", "Gattuso");
			m.addPerson("PPP", "Paolo", "Maldini");
			m.addPerson("AAA", "Andrea", "Pirlo");
			m.addFriendship("ABCD", "XYZ");
			m.addFriendship("ABCD", "GGG");
			m.addFriendship("PPP", "GGG");
			m.addFriendship("AAA", "GGG");
			Collection<String> friendsKaka = m.friendsOfFriends("ABCD");
			assertTrue(friendsKaka.contains("PPP"));
			assertTrue(friendsKaka.contains("AAA"));
			Collection<String> friendsPato = m.friendsOfFriends("XYZ");
			assertTrue(friendsPato.contains("GGG"));
			Collection<String> friendsPirlo = m.friendsOfFriends("AAA");
			assertTrue(friendsPirlo.contains("ABCD"));
			assertTrue(friendsPirlo.contains("PPP"));
		} catch (PersonExistsException e) {
			fail();
		} catch (NoSuchCodeException e) {
			fail();
		} catch (Exception e) {
			fail();
		}

	}

	public void testR27FriendshipNotExisting2Level() {
		Facebook m = new Facebook();
		//Pato <-> kaka <-> gattuso <-> (Pirlo,maldini)
		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			m.addPerson("XYZ", "Alex", "Pato");
			m.addPerson("GGG", "Gennaro", "Gattuso");
			m.addPerson("PPP", "Paolo", "Maldini");
			m.addPerson("AAA", "Andrea", "Pirlo");
			m.addFriendship("ABCD", "XYZ");
			m.addFriendship("ABCD", "GGG");
			m.addFriendship("PPP", "GGG");
			m.addFriendship("AAA", "GGG");
			m.friendsOfFriends("UXX");
			fail();
		} catch (PersonExistsException e) {
			fail();
		} catch (NoSuchCodeException e) {
			assert (true);
		} catch (Exception e) {
			fail();
		}

	}

	public void testR28Friendship2LevelNull() {
		Facebook m = new Facebook();
		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			Collection<String> friendsKaka = m.friendsOfFriends("ABCD");
			assertEquals(friendsKaka, null);
		} catch (NoSuchCodeException e) {
			fail();
		} catch (Exception e) {
			fail();
		}

	}

	public void testR29Friendship2LevelNoRepetitions() {
		Facebook m = new Facebook();
		
		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			m.addPerson("XYZ", "Alex", "Pato");
			m.addPerson("GGG", "Gennaro", "Gattuso");
			m.addPerson("PPP", "Paolo", "Maldini");
			m.addPerson("AAA", "Andrea", "Pirlo");
			m.addFriendship("ABCD", "XYZ");
			m.addFriendship("ABCD", "GGG");
			m.addFriendship("PPP", "GGG");
			m.addFriendship("AAA", "GGG");
			m.addFriendship("ABCD", "PPP");
			Collection<String> friendsKaka = m
					.friendsOfFriendsNoRepitition("ABCD");
			         // should contain AAA (if first level friend excluded) or AAA, GGG, PPP
			assertTrue(friendsKaka.contains("AAA"));
			//assertFalse(friendsKaka.contains("PPP")); 
			assertTrue((friendsKaka.size() == 1) || (friendsKaka.size() == 3)); 
			    
			 
		} catch (PersonExistsException e) {
			fail();
		} catch (NoSuchCodeException e) {
			fail();
		} catch (Exception e) {
			fail();
		}

	}

	//controllo che tra lista di miei amici di amici non ci sia anche io 
	public void testR291Friendship2LevelNoRepetitions2() {
		Facebook m = new Facebook();
		//(maldini,Pato) <-> kaka <-> gattuso <-> (Pirlo,maldini)
		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			m.addPerson("XYZ", "Alex", "Pato");
			m.addPerson("GGG", "Gennaro", "Gattuso");
			m.addPerson("PPP", "Paolo", "Maldini");
			m.addPerson("AAA", "Andrea", "Pirlo");
			m.addFriendship("ABCD", "XYZ");
			m.addFriendship("ABCD", "GGG");
			m.addFriendship("PPP", "GGG");
			m.addFriendship("AAA", "GGG");
			m.addFriendship("ABCD", "PPP"); //aggiungo amicizia kaka maldini
			Collection<String> friendsKaka = m
					.friendsOfFriendsNoRepitition("ABCD");
			assertFalse(friendsKaka.contains("ABCD")); //maldini � gia di primo livello
		} catch (PersonExistsException e) {
			fail();
		} catch (NoSuchCodeException e) {
			fail();
		} catch (Exception e) {
			fail();
		}
	}
}