package it.polito.po.test;

import facebook.*;

import junit.framework.TestCase;

public class TestR1 extends TestCase {

	public TestR1(String arg0) {
		super(arg0);

	}

	public void testR11AddPerson() {
		Facebook m = new Facebook();

		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			String s = m.getPerson("ABCD");
			assertEquals("ABCD Ricardo Kaka", s);
		} catch (NoSuchCodeException e) {
			fail();
		} catch (PersonExistsException e) {
			fail();
		} catch (Exception ex) {
			fail();
		}
	}

	public void testR12AddPerson2() {
		Facebook m = new Facebook();

		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			String s = m.getPerson("ABCD");
			assertTrue(s.contains("Ricardo") && s.contains("Kaka"));
		} catch (NoSuchCodeException e) {
			fail();
		} catch (Exception ex) {
			fail();
		}
	}

	public void testR13PersonDoesNotExist() {
		Facebook m = new Facebook();

		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			m.getPerson("ZZ");
			fail();
		} catch (NoSuchCodeException e) {
			assert (true);
		} catch (PersonExistsException e) {
			fail();
		} catch (Exception ex) {
			fail();
		}

	}

	public void testR14PersonExists() {
		Facebook m = new Facebook();
		try {
			m.addPerson("ABCD", "Ricardo", "Kaka");
			m.addPerson("ABCD", "Alex", "Pato");
			fail();
		} catch (PersonExistsException e) {
			assert (true);
		} catch (Exception ex) {
			fail();
		}

	}

}
