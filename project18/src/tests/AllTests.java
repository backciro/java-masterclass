package tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(TestAnagrafica.class);
        suite.addTestSuite(TestOrdiniClienti.class);
        suite.addTestSuite(TestOrdiniFornitori.class);
        suite.addTestSuite(TestStatistiche.class);
        //$JUnit-END$
        return suite;
    }

}