package edu.msudenver;

import edu.msudenver.Issue;
import edu.msudenver.Report;
import edu.msudenver.Severity;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class JacocoReport extends Report {

  /**
   * Required for XML document navigating.
   */
  final DocumentBuilderFactory factory;
  final DocumentBuilder builder;
  final Document document;
  final Element root;

  /**
   * XML tags for JaCoCo.
   */
  final static String counterTag = "counter";

  /**
   * XML attributes for JaCoCo.
   */
  final static String typeAttr = "type";
  final static String missedAttr = "missed";
  final static String coveredAttr = "covered";

  private final static String externalDtd = "http://apache.org/xml/features/nonvalidating/load-external-dtd";

  /**
   * Parse xml and create issues for the report.
   */
  public JacocoReport(File xmlReport) throws Exception {
    super("JaCoCo Coverage Report");
    // Ignore some defaults parameters
    // thank you, S.O. https://stackoverflow.com/a/155874/800917
    factory = DocumentBuilderFactory.newInstance();
    factory.setFeature(externalDtd, false);
    builder = factory.newDocumentBuilder();
    document = builder.parse(xmlReport);
    root = document.getDocumentElement();
    root.normalize();
    assert root.getTagName() == "report";
    // Parse document
    this.issues.addAll(createIssues());
  }

  /**
   * Create issues from root document.
   */
  private ArrayList<Issue> createIssues() {
    ArrayList<Node> counters = getTotalCounters();
    ArrayList<Issue> issues = new ArrayList<Issue>();
    for (Node n : counters) {
      NamedNodeMap attributes = n.getAttributes();
      String type = attributes.getNamedItem(typeAttr).getNodeValue();
      int missed = Integer.parseInt(attributes.getNamedItem(missedAttr).getNodeValue());
      int covered = Integer.parseInt(attributes.getNamedItem(coveredAttr).getNodeValue());
      String title = type + ": " + (int)((covered * 100.0f) / (covered + missed)) + "% covered";
      String description = "Missed: " + missed + " Covered: " + covered;
      issues.add(Issue.info(title, description));
    }
    return issues;
  }

  /**
   * Get top level counters elements.
   */
  private ArrayList<Node> getTotalCounters() {
    NodeList children = root.getChildNodes();
    ArrayList<Node> filtered = new ArrayList<Node>();
    for (int index = 0; index < children.getLength(); index++) {
      Node node = children.item(index);
      if (node.getNodeName() == counterTag) {
        filtered.add(node);
      }
    }
    return filtered;
  }

}
