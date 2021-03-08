package it.polito.po.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for it.polito.po.test");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestR1_Ateneo.class);
		suite.addTestSuite(TestR2_Studenti.class);
		suite.addTestSuite(TestR3_Insegnamenti.class);
		suite.addTestSuite(TestR4_Iscritti.class);
		//$JUnit-END$
		return suite;
	}

}
