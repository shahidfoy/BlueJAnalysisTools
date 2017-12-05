package edu.msudenver;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * A Tool is a utility that can analyze code to provide quality metrics
 * @author Artisan Software
 * @version 0.1.0
 */
public abstract class Tool {
  protected File analysis;
  protected Report report;
  protected static final String LINE_SEPARATOR = System.getProperty("line.separator");
  
  /**
   * analyze provides code quality analysis for a collection of source files.
   * @param inputFiles the source files to be analyzed
   * @return a file indicating problems found by the analysis tool
   */
  public abstract File analyze(ArrayList<File> inputFiles);
  
  /**
   * Show Report displays a human-readable summary of the analysis performed by the tool in a
   * Report Window.
   */
  public abstract void showReport();
  
  /**
   * create report creates a Report of violations found by the tool for interpretation and display
   * by the Code Quality Extension.
   * @return a Report of violations found by the tool.
   */
  public abstract Report createReport();
  
  /**
   * @return a file containing the results of a Tool's analysis.
   */
  public abstract File getAnalysis();


  protected String runExtension(String mycommand) throws IOException, InterruptedException {



    ProcessBuilder pb = new ProcessBuilder(mycommand.split(" +"));
    pb.redirectErrorStream(true);
    final Process p = pb.start();

    final StringBuilder output = new StringBuilder();
    Thread reader = new Thread(new Runnable() {
        public void run() {

          BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
          String s;
          try {
            while ((s = stdInput.readLine()) != null) {
              output.append(s);
              output.append(LINE_SEPARATOR);
            }
          } catch (IOException e) {
          output.append(e.toString());
          e.printStackTrace();
          } finally {
            try {
              stdInput.close(); 
              } catch (IOException e) { //quiet

              }
          }
      }
    });
    
    reader.setDaemon(true);
    reader.start();
    p.waitFor();
    return output.toString();

  }

}

