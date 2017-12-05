package edu.msudenver;


import java.io.File;
import java.util.ArrayList;

/**
 * Analyze Source Commands allow source code to be analyzed by tools
 * @author Artisan Software
 * @version 0.2.0
 */

public class AnalyzeSourceCommand extends Command {

  /**
   * The Tool which will perform the analysis.
   */
  private Tool receiver;
  
  /**
   * The collection of files to be analyzed.
   */
  private  ArrayList<File> sourceFiles;
  
  /**
   * Create an Analyze Source Command Command associated with a particular tool.
   * @param inputReceiver the tool with the resources to execute the command.
   */
  public AnalyzeSourceCommand(Tool inputReceiver) {
    receiver = inputReceiver;
    sourceFiles = new ArrayList<File>();
  }

  /**
   * Create an Analyze Source Command Command associated with a particular tool and 
   * a set of source files to be analyzed
   * @param inputReceiver the tool with the resources to execute the command.
   */
  public AnalyzeSourceCommand(Tool inputReceiver, ArrayList<File> inputFiles) {
    receiver = inputReceiver;
    sourceFiles = inputFiles;
  }
  
  @Override
  public void execute() {
    
    receiver.analyze(sourceFiles);

  }

}
