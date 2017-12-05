package edu.msudenver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import edu.msudenver.Issue;
import edu.msudenver.Severity;

import edu.msudenver.SourceLocation;

import org.junit.Test;

public class IssueTest {

  /**
   * It should set it to warning by default.
   */
  @Test
  public void testCreateDefaultIssue() {
    Issue i = new Issue("Test", "There is an issue");
    assertEquals("[WARNING]: Test - There is an issue", i.toString());
  }

  @Test
  public void testCreateErrorIssue() {
    Issue i = new Issue("Test", "There is an error", Severity.ERROR);
    assertEquals("[ERROR]: Test - There is an error", i.toString());
  }

  @Test
  public void testCreateIssueWithSource() {
    SourceLocation source =  new SourceLocation("file", 1, 1);
    Issue i = new Issue("Test", "There is an error", Severity.ERROR, source);
    assertEquals("[ERROR]: Test - There is an error", i.toString());
    assertEquals(i.getSource().toString(), "file:1:1");
  }

  @Test
  public void testFactoryMethods() {
    Issue i = Issue.error("Oh no", "Some Error");
    assertEquals("[ERROR]: Oh no - Some Error", i.toString());
    Issue ii = Issue.warning("Yikes", "Some warning");
    assertEquals("[WARNING]: Yikes - Some warning", ii.toString());
    Issue iii = Issue.info("Hey", "Just so you know");
    assertEquals("[INFO]: Hey - Just so you know", iii.toString());
  }

  @Test
  public void testSetSeverity() {
    Issue i = new Issue("Yup", "Nope");
    assertEquals(Severity.WARNING, i.getSeverity());
    i.setSeverity(Severity.INFO);
    assertEquals(Severity.INFO, i.getSeverity());
  }

  @Test
  public void testSource() {
    Issue i = new Issue("Foo", "Bar");
    assertNull(i.getSource());
    assertFalse(i.hasSource());
    i.setSource("file", 1, 1);
    assertTrue(i.hasSource());
    assertNotNull(i.getSource());
  }

  @Test
  public void testAttributes() {
    Issue i = Issue.warning("wow", "so much");
    assertEquals(i.getAttributes().size(), 0);
    i.addAttribute("key", "value");
    assertEquals(i.getAttributes().size(), 1);
  }

}
