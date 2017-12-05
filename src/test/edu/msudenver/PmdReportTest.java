package edu.msudenver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import edu.msudenver.Issue;
import edu.msudenver.MockToolOutput;
import edu.msudenver.PmdReport;

import java.util.ArrayList;

import org.junit.Test;

public class PmdReportTest {

  private PmdReport report;

  private PmdReport createReport() {
    try {
      report = new PmdReport(MockToolOutput.getPmdXml());
    } catch (Exception ex) {
      ex.printStackTrace();
      fail("Failed to create report");
    }
    return report;
  }

  @Test
  public void testGeneratedIssues() {
    report = createReport();
    ArrayList<Issue> issues = report.getIssues();
    assertEquals(14, issues.size());
    for (Issue i : issues) {
      assertNotSame(i.getTitle(), "");
      assertNotSame(i.getDescription(), "");
      assertNotNull(i.getSource());
      assertNotSame(i.getSource().toString(), "");
    }
  }

}
