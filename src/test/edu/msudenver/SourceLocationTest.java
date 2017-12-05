package edu.msudenver;

import static org.junit.Assert.assertEquals;

import edu.msudenver.SourceLocation;

import org.junit.Test;

public class SourceLocationTest {

  @Test public void testSourceLocation() {
    SourceLocation source = new SourceLocation("edu/msudenver/SomeFile.java", 1, 1);
    assertEquals("edu/msudenver/SomeFile.java:1:1", source.toString());
  }

}
