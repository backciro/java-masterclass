package it.polito.po.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {


  public static Test suite() {
    TestSuite suite = new TestSuite("Test for test");
    //$JUnit-BEGIN$
    suite.addTest(new TestSuite(TestR1_MateriePrime.class));
    suite.addTest(new TestSuite(TestR2_Prodotti.class));
    suite.addTest(new TestSuite(TestR3_Ricette.class));
    suite.addTest(new TestSuite(TestR4_Menu.class));
    //$JUnit-END$
    return suite;
  }
}
