package it.polito.po.test;

import java.util.Collection;

import facebook.*;
import junit.framework.TestCase;

public class TestR3 extends TestCase {

	public TestR3(String arg0) {
		super(arg0);

	}

	public void testR31Group() {
		Facebook m = new Facebook();
		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");

			m.addGroup("milan");
			Collection<String> s = m.listOfGroups();
			assertTrue(s.contains("milan"));
			m.addGroup("juve");
			s = m.listOfGroups();
			assertTrue(s.contains("milan"));
			assertTrue(s.contains("juve"));
		} catch (PersonExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testR32EmptyGroup() {
		Facebook m = new Facebook();
		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");

			Collection<String> s = m.listOfGroups();
			assertTrue(s == null);

		} catch (PersonExistsException e) {
			e.printStackTrace();
		}
	}

	public void testR33GroupListing() {
		Facebook m = new Facebook();
		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			m.addPerson("XYZ", "Alex", "Pato");
			m.addPerson("GGG", "Gennaro", "Gattuso");
			m.addFriendship("ABCD", "XYZ");
			m.addFriendship("ABCD", "GGG");

			m.addGroup("milan");
			m.addGroup("brasile");
			m.addGroup("poli");

			m.addPersonToGroup("XYZ", "brasile");
			m.addPersonToGroup("ABCD", "brasile");
			m.addPersonToGroup("ABCD", "milan");
			m.addPersonToGroup("GGG", "milan");
			Collection<String> s = m.listOfPeopleInGroup("brasile");
			assertTrue(s.contains("XYZ"));
			assertTrue(s.contains("ABCD"));
			s = m.listOfPeopleInGroup("milan");
			assertTrue(s.contains("ABCD"));
			assertTrue(s.contains("GGG"));
		} catch (PersonExistsException e) {
			fail();
		} catch (NoSuchCodeException e) {
			fail();
		}
	}

}