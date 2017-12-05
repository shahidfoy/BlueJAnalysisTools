package edu.msudenver.GUI;

import java.awt.Dimension;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * A Single Report Application provides a graphical user interface for viewing a code quality report
 * from a single tool, such as PMD
 *
 * @author Artisan Software
 * @version 1.1.0
 *
 */
public class SingleReportPane extends ReportPane {

  /**
   * The frame which will contain information reported by tools.
   */
  private static JFrame frame;
  private static JFXPanel fxPanel;
  
  /**
   * initializes the javaFX Panel using swing.
   */
  public static void initGui(String [] args) {
    // This method is invoked on the EDT thread
    String title = args[0];
    frame = new JFrame(title);
    fxPanel = new JFXPanel();
    frame.add(fxPanel);
    frame.setMinimumSize(new Dimension(400, 400));
    frame.setMaximumSize(new Dimension(1600, 1000));
    frame.setVisible(false);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    Platform.runLater(new Runnable() {
        @Override
        public void run() {
            initFx();
        }
    });
  }
  
  /**
   * Adds the scene to the JavaFX Panel.
   * @param fxPanel a JFXPanel which will contain the desired scene
   */
  private static void initFx() {
    // This method is invoked on the JavaFX thread
    BorderPane layout = new BorderPane();
    Scene scene = new Scene(layout);
    fxPanel.setScene(scene);
  }

  /**
   * Updates the frame with code quality information from a tool, then displays it to the user.
   * @param reportToShow  a JavaFX Pane containing information to be shown to the user.
   */
  public static void updateAndShowReport(Pane reportToShow) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Scene scene = new Scene(reportToShow);
        fxPanel.setScene(scene);
        double frameWidth = reportToShow.getPrefWidth();
        double frameHeight = reportToShow.getPrefHeight();
        Dimension preferredSize = new Dimension();
        preferredSize.setSize(frameWidth, frameHeight);
        frame.setMinimumSize(preferredSize);
        frame.setVisible(true);
      }
    });
  }
  
  /**
   * As GUI testing is currently inconvenient, main is included to allow a human to verify
   * correct behavior of the Single Report Panel.
   * @param args ignored.
   */
  
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          String[] params = {"Test Report"};
            initGui(params);
        }
    });
  }
}
