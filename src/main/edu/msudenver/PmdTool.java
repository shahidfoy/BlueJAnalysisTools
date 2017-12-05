package edu.msudenver;

import edu.msudenver.GUI.IssueModel;
import edu.msudenver.GUI.SingleReportPane;
import edu.msudenver.GUI.SystemUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;

/**
 * PMD is a Tool that allows the Artisan Code Quality Extension to analyze source code and 
 * display violations found by PMD.
 * @author Artisan Software
 * @version 1.8.0
 */
public class PmdTool extends Tool {
  
  /**
   * Constructs a PMD tool containing no analysis or report.
   */
  public PmdTool() {
    analysis = null;
    report = null;
  }
  
  /**
   * Constructs a PMD tool containing an analysis file.
   * @param inputAnalysis an xml file containing PMD's analysis of some source file(s).
   */
  public PmdTool(File inputAnalysis) {
    analysis = inputAnalysis;
    report = null;
  }
  
  /**
   * Constructs a PMD tool containing an existing analysis file and report of issues found.
   * @param inputAnalysis an xml file containing PMD's analysis of some source file(s).
   * @param inputReport a Report of issues found by a PMD tool's analysis.
   */
  public PmdTool(File inputAnalysis, Report inputReport) {
    analysis = inputAnalysis;
    report = inputReport;
  }
  
  @Override
  public File analyze(ArrayList<File> files) {
    
    analysis = null;
    
    System.out.println("Running PMD on selected Class");
    String basepath = (new File(System.getProperty("running.jar"))).getParent();
    System.out.println("basepath => " + basepath);
    // Build the PMD command
    String command;
    String output = "PMD Analysis Failed";
    String outputFileName = null;
    StringBuilder msg;
    final int NUMBER_OF_FILES = files.size();
    //    JOptionPane.showMessageDialog(frame, "File Selected: " + javaFileName);
    //
    //    if (javaFileName == null || javaFileName.trim().isEmpty()) {
    //JOptionPane.showMessageDialog(frame, "No file selected", "Error", JOptionPane.ERROR_MESSAGE);
    //      return;
    //  }
    
    if (NUMBER_OF_FILES > 1) {
      try {

        String sourceDirectoryName;
        sourceDirectoryName = files.get(0).getParent();
        outputFileName = sourceDirectoryName;
		/*
        command = basepath + File.separator + "lib" + File.separator + "artisancq"
            + File.separator + "pmd" + File.separator + "bin" + File.separator 
            + "pmd.bat -dir " + sourceDirectoryName + " -format xml -R "
            + "java-basic,java-design,java-unusedcode,java-imports";
			*/
			
		command = "/pmd/bin/run.sh pmd -d " + sourceDirectoryName + " -format xml -R "
            + "java-basic,java-design,java-unusedcode,java-imports";
		if (SystemUtils.isWindows()) {
	        command = "C:\\pmd\\bin\\pmd.bat -dir " + sourceDirectoryName + " -format xml -R "
            + "java-basic,java-design,java-unusedcode,java-imports";
        }
        
        output = runExtension(command);

        System.out.println("Directory Checked: " + sourceDirectoryName);
        
        msg = new StringBuilder("Any problems found are displayed below:");
        msg.append(LINE_SEPARATOR);
        msg.append(output);
        System.out.println(msg);
        

      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Couldn't run PMD: " + e.getMessage());
      } catch (InterruptedException e) {
        e.printStackTrace();
        System.out.println("Couldn't run PMD: " + e.getMessage());
      } 
        
    } else if (NUMBER_OF_FILES == 1) {
      
      try {

        String sourceFileName;
        sourceFileName = files.get(0).getPath();
        outputFileName = files.get(0).getParent();
		// "C:\\PMD\\bin\\pmd.bat -dir " + javaFileName + " -format text -R java-unusedcode,java-imports -version 1.7 -language java -debug";
		command = "/PMD/bin/run.sh pmd -d " + sourceFileName + " -format xml -R "
            + "java-basic,java-design,java-unusedcode,java-imports";
		if (SystemUtils.isWindows()) {
	        command = "C:\\PMD\\bin\\pmd.bat -dir " + sourceFileName + " -format xml -R "
            + "java-basic,java-design,java-unusedcode,java-imports";
        }
        /*
		command = basepath + File.separator + "lib" + File.separator + "artisancq"
            + File.separator + "pmd" + File.separator + "bin" + File.separator 
            + "pmd.bat -dir " + sourceFileName + " -format xml -R "
            + "java-basic,java-design,java-unusedcode,java-imports";
		*/
        
        output = runExtension(command);

        System.out.println("Class Checked: " + sourceFileName);
        
        msg = new StringBuilder("Any problems found are displayed below:");
        msg.append(LINE_SEPARATOR);
        msg.append(output);
        System.out.println(msg);
        

      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Couldn't run PMD: " + e.getMessage());
      } catch (InterruptedException e) {
        e.printStackTrace();
        System.out.println("Couldn't run PMD: " + e.getMessage());
      } 
      
    }
    
    System.out.println("PMD run completed");
    
    if (outputFileName != null) {
      System.out.println(outputFileName);
      outputFileName = outputFileName + File.separator + "pmd-analysis.xml";
      analysis = new File(outputFileName);
      try {
        FileWriter analysisWriter = new FileWriter(analysis);
        analysisWriter.write(output);
        analysisWriter.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.out.println(outputFileName);
    }
    return analysis;
  }
    

 

  @Override
  public Report createReport() {
    
    if (analysis != null) {
      try {
        report = new PmdReport(new FileInputStream(analysis));
      } catch (Exception e) {
        report = null;
      }
    }
    
    return report;
  }

  @Override
  public void showReport() {
    /*
     * Temporarily removed this logic as it is unnecessary to satisfy the current User Story.
     * Will reinsert and improve if and when it is appropriate, or remove permanently
    //If there is no report, create one
    if (report == null) {
      
    if (report == null) {
      report = new Report("PMD");
    }
    */
    if (report == null) {
      //TODO: replace this demo with:
      //    1) a check for an analyzed file from which to generate a report
      //    1.a) a process to generate a report if such a file exists
      //    1.b) something informative in the event that that such a file does not exist
      try {
        report = new PmdReport(MockToolOutput.getPmdXml());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    
    //The container which will hold the displayable version of the report.
    final FlowPane REPORT_LAYOUT;
    //The table of reported issues.
    TableView<IssueModel> issueTable = new TableView<IssueModel>();
    //An Observable ArrayList which will be the data source for the table of issues.
    ObservableList<IssueModel> issuesToDisplay = FXCollections.observableArrayList();
    
    //Convert issues into Issue Models composed of Properties an add them to the table's data source
    for (Issue issue : report.getIssues()) {
      issuesToDisplay.add(new IssueModel(issue));
    }
    issueTable.setItems(issuesToDisplay);
    
    //Create columns from the data
    TableColumn<IssueModel, String> severityCol = new TableColumn<IssueModel, String>("Severity");
    severityCol.setCellValueFactory(new PropertyValueFactory<IssueModel, String>("severity"));
    
    TableColumn<IssueModel, String> titleCol = new TableColumn<IssueModel, String>("Title");
    titleCol.setCellValueFactory(new PropertyValueFactory<IssueModel, String>("title"));
    
    TableColumn<IssueModel, String> descriptionCol =
          new TableColumn<IssueModel, String>("Description");
    descriptionCol.setCellValueFactory(new PropertyValueFactory<IssueModel, String>("description"));
    
    TableColumn<IssueModel, String> sourceCol = new TableColumn<IssueModel, String>("Source");
    sourceCol.setCellValueFactory(new PropertyValueFactory<IssueModel, String>("source"));
    
    TableColumn<IssueModel, String> lineNumberCol = 
        new TableColumn<IssueModel, String>("Line Number");
    lineNumberCol.setCellValueFactory(new PropertyValueFactory<IssueModel, String>("lineNumber"));
    
    TableColumn<IssueModel, String> columnNumberCol =
        new TableColumn<IssueModel, String>("Column Number");
    columnNumberCol.setCellValueFactory(
        new PropertyValueFactory<IssueModel, String>("columnNumber"));
    
    //group properties associated with the origin of an issue.
    TableColumn<IssueModel, String> originCol = new TableColumn<IssueModel, String>("Origin");
    originCol.getColumns().add(sourceCol);
    originCol.getColumns().add(lineNumberCol);
    originCol.getColumns().add(columnNumberCol);
    
    //add all columns to the report table
    issueTable.getColumns().add(originCol);
    issueTable.getColumns().add(severityCol);
    issueTable.getColumns().add(titleCol);
    issueTable.getColumns().add(descriptionCol);
    
    //add all visual components to the layout
    
    REPORT_LAYOUT = new FlowPane(issueTable);
    
    //Adjust size and/or position of components of the layout, as necessary
    issueTable.setPrefSize(1600, 1000);
    REPORT_LAYOUT.setPrefSize(1600, 1000);  
    
    //Display the report
    SingleReportPane.updateAndShowReport(REPORT_LAYOUT);
  }

  @Override
  public File getAnalysis() {
    return analysis;
  }

}
