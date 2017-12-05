package edu.msudenver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import edu.msudenver.Issue;
import edu.msudenver.Report;

import org.junit.Test;

public class ReportTest {

  @Test
  public void testBasics() {
    Report r = new Report("Test");
    assertEquals("Test", r.getName());
    r.addIssue(new Issue("wow", "coverage"));
    assertNotNull(r.getIssues());
    assertEquals(1, r.getIssues().size());
  }

}
