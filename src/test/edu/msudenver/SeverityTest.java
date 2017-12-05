package edu.msudenver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import edu.msudenver.Severity;

import org.junit.Test;

public class SeverityTest {

  @Test
  public void testAssumptions() {
    assertNotNull(Severity.INFO);
    assertNotNull(Severity.WARNING);
    assertNotNull(Severity.ERROR);
  }

  @Test
  public void testOrdering() {
    boolean less = Severity.INFO.compareTo(Severity.WARNING) > 0;
    assertTrue(less);
  }

}
