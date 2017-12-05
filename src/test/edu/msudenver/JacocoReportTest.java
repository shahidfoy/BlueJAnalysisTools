package edu.msudenver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import edu.msudenver.Issue;
import edu.msudenver.JacocoReport;
import edu.msudenver.Severity;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

public class JacocoReportTest {

  private JacocoReport report;

  private JacocoReport createReport() {
    try {
      report = new JacocoReport(readFile());
    } catch (Exception ex) {
      ex.printStackTrace();
      fail("Failed to create report");
    }
    return report;
  }

  private File readFile() {
    return new File("src/test/edu/msudenver/xml/jacoco.xml");
  }

  @Test
  public void testJacocoTest() {
    report = createReport();
    ArrayList<Issue> issues = report.getIssues();
    assertEquals(issues.size(), 6);
    for (Issue i : issues) {
      assertEquals(i.getSeverity(), Severity.INFO);
      assertNotSame(i.getTitle(), "");
      assertNotSame(i.getDescription(), "");
      assertNull(i.getSource());
    }
  }

}
