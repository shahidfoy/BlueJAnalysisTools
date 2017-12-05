package edu.msudenver;

import edu.msudenver.Issue;

import java.util.ArrayList;

/**
 * Common Report object that all tools generate for display/manipulation.
 */
public class Report {

  /**
   * The name of this report, typically the name of the tool that generated it.
   */
  protected String name;

  /**
   * The main data member of a report.
   */
  protected ArrayList<Issue> issues;

  /**
   * Create a new report with nothing in it.
   */
  public Report(String name) {
    this.name = name;
    this.issues = new ArrayList<Issue>();
  }

  /**
   * Getter for the name.
   */
  public String getName() {
    return name;
  }

  /**
   * Add a new issue to the report (does not dedupe).
   */
  public void addIssue(Issue issue) {
    issues.add(issue);
  }

  /**
   * Get the current list of issues (flat structure).
   */
  public ArrayList<Issue> getIssues() {
    return issues;
  }

}
