package edu.msudenver;

import edu.msudenver.Severity;
import edu.msudenver.SourceLocation;
import java.util.HashMap;

/**
 * An Issue is something that a tool will create when something needs attention.
 */
public class Issue {

  /**
   * Title of the issue, used as a way to identify the entire issue without knowing all the context.
   */
  private final String title;

  /**
   * Description should explain the issue with larger detail.
   */
  private final String description;

  /**
   * Severity of the issue.
   */
  private Severity severity;

  /**
   * A hash for metadata of the issue used for extra context for each tool.
   */
  private final HashMap<String, String> attributes;

  /**
   * Where the issue is located in a source file.
   */
  private SourceLocation source;

  /**
   * Create an issue without a source location, defaults to WARNING severity.
   */
  public Issue(String title, String description) {
    this.title = title;
    this.description = description;
    this.severity = Severity.WARNING;
    this.attributes = new HashMap<String, String>();
  }

  /**
   * Create an issue with severity, no source location.
   */
  public Issue(String title, String description, Severity severity) {
    this.title = title;
    this.description = description;
    this.severity = severity;
    this.attributes = new HashMap<String, String>();
  }

  /**
   * Create an issue with source information.
   */
  public Issue(String title, String description, Severity severity, SourceLocation source) {
    this.title = title;
    this.description = description;
    this.severity = severity;
    this.source = source;
    this.attributes = new HashMap<String, String>();
  }

  /**
   * Factory method to create an error severity Issue.
   * @return error issue with title, description
   */
  public static Issue error(String title, String description) {
    return new Issue(title, description, Severity.ERROR);
  }

  /**
   * Factory method to create a warning severity Issue.
   * @return warning issue with title, description
   */
  public static Issue warning(String title, String description) {
    return new Issue(title, description, Severity.WARNING);
  }

  /**
   * Factory method to create an info severity Issue.
   */
  public static Issue info(String title, String description) {
    return new Issue(title, description, Severity.INFO);
  }

  /**
   * Getter for the title of the issue.
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Getter for the description.
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Getter for the severity  of the issue.
   * @return the severity
   */
  public Severity getSeverity() {
    return this.severity;
  }

  /**
   * Set the severity level of the issue.
   * @param severity the severity
   */
  public void setSeverity(Severity severity) {
    this.severity = severity;
  }

  /**
   * Getter for source of the issue.
   * @return the source
   */
  public SourceLocation getSource() {
    return this.source;
  }

  /**
   * Factory constructor setter for the file, line, and column where this issue takes place.
   * @param fileName the file, including path and extension
   * @param line the line number in the file where the issue starts
   * @param column the column number in the file where the issue starts
   */
  public void setSource(String fileName, Number line, Number column) {
    this.source = new SourceLocation(fileName, line, column);
  }

  /**
   * Checks if the issue has a source.
   */
  public boolean hasSource() {
    return this.source != null;
  }

  /**
   * Add an attribute to this issue.
   */
  public void addAttribute(String key, String value) {
    this.attributes.put(key, value);
  }

  /**
   * Getter for the attributes of the issue.
   * @return the attributes
   */
  public HashMap<String, String> getAttributes() {
    return this.attributes;
  }

  /**
   * Get the representation of the issue as a string.
   */
  @Override
  public String toString() {
    return "[" + this.severity.name() + "]: " + this.title + " - " + this.description;
  }

}
