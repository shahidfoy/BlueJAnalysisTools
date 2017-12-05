package edu.msudenver;

import edu.msudenver.Issue;
import edu.msudenver.Report;
import edu.msudenver.Severity;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import net.sourceforge.pmd.PMD;
/**
 * PMDReport parses an XML file and builds the issue report from it.
 */
public class PmdReport extends Report {

  /**
   * Required for XML document navigating.
   */
  final DocumentBuilderFactory factory;
  final DocumentBuilder builder;
  final Document document;
  final Element root;

  /**
   * XML document tags for PMD.
   */
  final static String violationTag = "violation";
  final static String fileTag = "file";

  /**
   * XML tag attributes for PMD.
   */
  final static String filenameAttr = "name";
  final static String lineNumberAttr = "beginline";
  final static String columnNumberAttr = "begincolumn";
  final static String ruleAttr = "rule";
  final static String priorityAttr = "priority";

  /**
   * Parse a report from a PMD XML report and generate Issues.
   * @see Report
   * @see Issue
   */
  public PmdReport(InputStream xmlReport) throws Exception {
    super("PMD Issue Report");
    factory = DocumentBuilderFactory.newInstance();
    builder = factory.newDocumentBuilder();
    document = builder.parse(xmlReport);
    root = document.getDocumentElement();
    root.normalize();
    assert root.getTagName() == "pmd";
    // Build out all issues
    this.issues.addAll(createIssues());
  }

  /**
   * PMD violations in a list to iterate.
   */
  private NodeList getViolations() {
    return root.getElementsByTagName(violationTag);
  }

  /**
   * Create issues based on the current xml document.
   */
  private ArrayList<Issue> createIssues() {
    NodeList violations = getViolations();
    ArrayList<Issue> issues = new ArrayList<Issue>();
    for (int index = 0; index < violations.getLength(); index++) {
      issues.add(createIssue(violations.item(index)));
    }
    return issues;
  }

  /**
   * Given a fileName and XML violation node, generate an Issue.
   * @see Issue
   */
  private Issue createIssue(Node violation) {
    // Extract the file name for this violation
    String fileName = getAttribute(violation.getParentNode(), filenameAttr);
    int line = Integer.parseInt(getAttribute(violation, lineNumberAttr));
    int column = Integer.parseInt(getAttribute(violation, columnNumberAttr));
    int priority = Integer.parseInt(getAttribute(violation, priorityAttr));
    String title = getAttribute(violation, ruleAttr);
    String description = violation.getTextContent().trim();
    Severity severity = convertPriority(priority);
    Issue issue = new Issue(title, description, severity);
    issue.setSource(fileName, line, column);
    return issue;
  }

  /**
   * Get an attribute from a random node.
   * @return the attribute value or empty string if no attribute exists
   */
  private String getAttribute(Node n, String key) {
    if (n.hasAttributes()) {
      NamedNodeMap attributes = n.getAttributes();
      Node attribute = attributes.getNamedItem(key);
      if (attribute != null) {
        return attribute.getNodeValue();
      }
    }
    return "";
  }

  /**
   * PMD Priority goes from 1 to 5, 1 being the most severe and 5 being the least severe.
   * @return the severity a int value should be.
   */
  private Severity convertPriority(int priority) {
    assert priority > 0 && priority <= 5;
    if (priority == 1) {
      return Severity.ERROR;
    } else if (priority > 1 && priority < 4) {
      return Severity.WARNING;
    } else {
      return Severity.INFO;
    }
  }

}
