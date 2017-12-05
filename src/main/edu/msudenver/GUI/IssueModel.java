package edu.msudenver.GUI;

import edu.msudenver.Issue;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Issue Model creates a model of an issue using StringPoperties to represent the issue's data
 * in order to allow the issue to be easily rendered by JavaFX components.
 * @author Artisan Software
 * @version 0.5.0
 */

public class IssueModel {
  
  private StringProperty description;
  private StringProperty title;
  private StringProperty severity;
  private StringProperty source;
  private StringProperty lineNumber;
  private StringProperty columnNumber;
  
  /**
   * Constructs a Model based on Properties from from an Issue to allow the issue to be easily
   * rendered by JavaFX components
   * @param inputIssue the issue to convert.
   */
  public IssueModel(Issue inputIssue) {

    severity = new SimpleStringProperty(inputIssue.getSeverity().toString()); 
    title = new SimpleStringProperty(inputIssue.getTitle());
    description = new SimpleStringProperty(inputIssue.getDescription());
    source = new SimpleStringProperty();
    lineNumber = new SimpleStringProperty();
    columnNumber = new SimpleStringProperty();
    String[] locationData = inputIssue.getSource().toString().split(",");
    
    setSource(locationData[0]);
    setLineNumber(locationData[1]);
    setColumnNumber(locationData[2]);
  }
  
  /**
   * Creates a new severity property with the default value of "severity".
   * @return a generic severity property
   */
  
  public StringProperty severityProperty() {
    if (severity == null) {
      severity = new SimpleStringProperty(this, "severity");
    }
    
    return severity;
  }
  
  public void setSeverity(String value) {
    severityProperty().set(value);
  }
  
  public String getSeverity() {
    return severityProperty().get();
  }
  
  /**
   * Creates a new title property with the default value of "title".
   * @return a generic title property
   */
  public StringProperty titleProperty() {
    if (title == null) {
      title = new SimpleStringProperty(this, "title");
    }
    
    return title;
  }
  
  public void setTitle(String value) {
    titleProperty().set(value);
  }
  
  public String getTitle() {
    return titleProperty().get();
  }
  
  /**
   * Creates a new description property with the default value of "description".
   * @return a generic title property
   */
  public StringProperty descriptionProperty() {
    if (description == null) {
      description = new SimpleStringProperty(this, "description");
    }
    
    return description;
  }
  
  public void setDescription(String value) {
    descriptionProperty().set(value);
  }
  
  public String getDescription() {
    return descriptionProperty().get();
  }

  /**
   * Creates a new source property with the default value of "source".
   * @return a generic source property
   */
  public StringProperty sourceProperty() {
    if (source == null) {
      source = new SimpleStringProperty(this, "Source");
    }
    
    return source;
  }
  
  public void setSource(String value) {
    sourceProperty().set(value);
  }
  
  public String getSource() {
    return sourceProperty().get();
  }
  
  /**
   * Creates a new line number property with the default value of "line number".
   * @return a generic line number property
   */
  public StringProperty lineNumberProperty() {
    if (lineNumber == null) {
      lineNumber = new SimpleStringProperty(this, "line number");
    }
    return lineNumber;
  }
  
  public void setLineNumber(String value) {
    lineNumberProperty().set(value);
  }
  
  public String getLineNumber() {
    return lineNumberProperty().get();
  }
  
  /**
   * Creates a new column number property with the default value of "column number".
   * @return a generic column number property
   */
  public StringProperty columnNumberProperty() {
    if (columnNumber == null) {
      columnNumber = new SimpleStringProperty(this, "column number");
    }
    return columnNumber;
  }
  
  public void setColumnNumber(String value) {
    columnNumberProperty().set(value);
  }
  
  public String getColumnNumber() {
    return columnNumberProperty().get();
  }
}