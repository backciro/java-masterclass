package tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for it.polito.po.test");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestPrint.class);
		suite.addTestSuite(TestBase.class);
		suite.addTestSuite(TestTaxes.class);
		suite.addTestSuite(TestTotal.class);
		//$JUnit-END$
		return suite;
	}

}
