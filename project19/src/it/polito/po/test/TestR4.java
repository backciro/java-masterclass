package it.polito.po.test;

import facebook.*;
import junit.framework.TestCase;

public class TestR4 extends TestCase {

	public TestR4(String arg0) {
		super(arg0);

	}

	public void testR41PersonWithLargestNumberOfFriends() {
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

			String s = m.personWithLargestNumberOfFriends();
			assertEquals(s, "GGG"); // gattuso 3

		} catch (PersonExistsException e) {
			fail();
		} catch (NoSuchCodeException e) {
			fail();
		} catch (Exception e) {
			fail();
		}
	}

	public void testR42PersonWithMostFriendsOfFriends() {
		Facebook m = new Facebook();
		//sheva<->Pato <-> kaka <-> gattuso <-> (Pirlo,maldini)
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
			m.addPerson("SSS", "Andrey", "Sheva");
			m.addFriendship("SSS", "XYZ");
			String s = m.personWithMostFriendsOfFriends();
			assertEquals(s, "ABCD"); //kaka = 3

		} catch (PersonExistsException e) {
			fail();
		} catch (NoSuchCodeException e) {
			fail();
		} catch (Exception e) {
			fail();
		}
	}

	public void testR43PopularGroup() {
		Facebook m = new Facebook();
		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			m.addPerson("XYZ", "Alex", "Pato");
			m.addPerson("GGG", "Gennaro", "Gattuso");

			m.addGroup("milan");
			m.addGroup("brasile");
			m.addGroup("poli");

			m.addPersonToGroup("XYZ", "brasile");
			m.addPersonToGroup("ABCD", "brasile");
			m.addPersonToGroup("GGG", "milan");
			String s = m.largestGroup();
			assertEquals(s, "brasile");
		} catch (PersonExistsException e) {
			fail();
		}
	}

	public void testR44PersonInLargestNumberOfGroups() {
		Facebook m = new Facebook();
		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			m.addPerson("XYZ", "Alex", "Pato");
			m.addPerson("GGG", "Gennaro", "Gattuso");
			m.addGroup("milan");
			m.addGroup("brasile");

			m.addPersonToGroup("XYZ", "brasile");
			m.addPersonToGroup("ABCD", "brasile");
			m.addPersonToGroup("GGG", "milan");
			m.addPersonToGroup("XYZ", "milan");
			String s = m.personInLargestNumberOfGroups();
			assertEquals(s, "XYZ"); //pato 2
		} catch (PersonExistsException e) {
			fail();
		}
	}

}