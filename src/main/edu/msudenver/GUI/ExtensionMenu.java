package edu.msudenver.GUI;

import bluej.extensions.BClass;
import bluej.extensions.BPackage;
import bluej.extensions.MenuGenerator;
import bluej.extensions.PackageNotFoundException;
import bluej.extensions.ProjectNotOpenException;

import edu.msudenver.AnalyzeSourceCommand;
import edu.msudenver.CreateReportCommand;
import edu.msudenver.PmdTool;
import edu.msudenver.SpotbugTool;
import edu.msudenver.ShowReportCommand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import java.io.File;



/**
 * Manages the Checkstyle extension menu item.
 *
 * @author Artisan Software
 * @version 0.9.0
 */
public class ExtensionMenu extends MenuGenerator {
	
	
  private Frame frame;
  private File sourceFile;
  private String javaFileName;
  private String parentFolder;
  private String packageLocation;
  private AnalyzeSourceCommand analyzeSourceCommand;
  private CreateReportCommand createReportCommand;
  private ShowReportCommand showReportCommand;
  private final String EXTENSION_SUBMENU_NAME = "ArtansCQ";
  private final String PMD_MENU_ITEM_NAME = "PMD Analysis";
  
  private static final String LINE_SEPARATOR = System.getProperty("line.separator");

  @Override
  public JMenuItem getToolsMenuItem(BPackage inputPackage) {
    /**
     * The main Artisan Code Quality submenu.
     */
    JMenu artisancqToolsSubmenu = new JMenu(EXTENSION_SUBMENU_NAME);

    /**
     * the PMD analysis menu item for Artisan Code Quality: runs analysis using and presents a
     * report from PMD.
     */
    JMenuItem pmdReportToolsMenuItem = new JMenuItem(PMD_MENU_ITEM_NAME);
    
    /**
     * the SpotBugs analysis menu item for Artisan Code Quality: runs analysis using and presents a
     * report from PMD.
     */
    JMenuItem SpotBugsReportItem = new JMenuItem("SpotBugs Analysis");

    artisancqToolsSubmenu.add(pmdReportToolsMenuItem);
    artisancqToolsSubmenu.add(SpotBugsReportItem);

    artisancqToolsSubmenu.addActionListener(new MenuInaction());
    pmdReportToolsMenuItem.addActionListener(new PmdToolsMenuAction());
    SpotBugsReportItem.addActionListener(new SpotBugsClassMenuAction());

    try {
      packageLocation = inputPackage.getDir().getPath();
    } catch (ProjectNotOpenException e) {
      e.printStackTrace();
    } catch (PackageNotFoundException e) {
      e.printStackTrace();
    }

    
    return artisancqToolsSubmenu;
  }

  @Override
  public JMenuItem getClassMenuItem(BClass inputClass) {
    
    JMenu artisancqClassSubmenu = new JMenu(EXTENSION_SUBMENU_NAME);
    JMenuItem pmdReportClassMenuItem = new JMenuItem(new PmdClassMenuAction(PMD_MENU_ITEM_NAME));
    
    artisancqClassSubmenu.add(pmdReportClassMenuItem);
    artisancqClassSubmenu.addActionListener(new MenuInaction());
    
    //artisancqClassSubmenu.add(new JMenuItem(new FindBugsClassMenuAction("FindBugs Analysis")));

    try {
      sourceFile = inputClass.getJavaFile();
      javaFileName = sourceFile.getPath();
    } catch (ProjectNotOpenException e) {
      e.printStackTrace();
    } catch (PackageNotFoundException e) {
      e.printStackTrace();
    }

    getFileParent();

    return artisancqClassSubmenu;
  }

  /**
   * Get File Parent returns the path of the parent directory of a file.
   * @return the parent directory of the input file.
   */
  public String getFileParent() {
    parentFolder = javaFileName;
    int parentFolderLength = javaFileName.lastIndexOf(File.separator);
    parentFolder = parentFolder.substring(0, parentFolderLength);
    return parentFolder;
  }

  @Override
  public void notifyPostClassMenu(BClass bc, JMenuItem jmi) {
    try {
      javaFileName = bc.getJavaFile().getPath();
    } catch (ProjectNotOpenException e) {
      e.printStackTrace();
    } catch (PackageNotFoundException e) {
      e.printStackTrace();
    }
  }

  class MenuInaction implements ActionListener {
    public void actionPerformed(ActionEvent anEvent) {
      //Do Nothing
    }
  }

  class PmdToolsMenuAction implements ActionListener {
        
    public void actionPerformed(ActionEvent anEvent) {
      String[] params = {"PMD Analysis"};
      SingleReportPane.initGui(params);
      PmdTool pmd = new PmdTool();
      ArrayList<File> sourceFiles = new ArrayList<File>();
      File packageDirectory = new File(packageLocation);
      
      
      sourceFiles.addAll(
          Arrays.asList(
              packageDirectory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File directory, String fileName) {
                  return fileName.endsWith(".java");
                }
              }
            )
          )
      );
      
      analyzeSourceCommand = new AnalyzeSourceCommand(pmd, sourceFiles);
      createReportCommand = new CreateReportCommand(pmd);
      showReportCommand = new ShowReportCommand(pmd);
      analyzeSourceCommand.execute();
      createReportCommand.execute();
      showReportCommand.execute();
    }
  }


  class SpotBugsClassMenuAction extends AbstractAction {
    /**
     * Serial ID for this action.
     */
    private static final long serialVersionUID = 1L;

    public SpotBugsClassMenuAction() {

    }

    public SpotBugsClassMenuAction(String menuName) {
      putValue(AbstractAction.NAME, menuName);
    }

    public void actionPerformed(ActionEvent anEvent) {

	  parentFolder = packageLocation;
	    try {

                JOptionPane.showMessageDialog(frame, "Running SpotBugs on selected Class (Click OK)");

                String command = "/spotbugs/bin/spotbugs -textui -jvmArgs -debug -medium " + parentFolder + "";

                if (SystemUtils.isWindows()) {
                    command = "C:\\spotbugs\\bin\\spotbugs.bat -textui -jvmArgs -debug -medium " + parentFolder + "";
                }
				
				String basepath = (new File(System.getProperty("running.jar"))).getParent();
				command = basepath + File.separator + "lib" + File.separator + "artisancq"
                 + File.separator + "spotbugs" + File.separator + "bin" + File.separator 
                 + "spotbugs.bat -textui -jvmArgs -debug -medium " + parentFolder + "";


                String output = runExtension(command);

                JOptionPane.showMessageDialog(frame, "Class Checked");

                StringBuilder msg = new StringBuilder("Any problems found are displayed below:");
                msg.append(LINE_SEPARATOR);
                msg.append(output);
                JOptionPane.showMessageDialog(frame, msg);

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Couldn't run SpotBugs: " + e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
            } catch (InterruptedException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Couldn't run SpotBugs: " + e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
            }

            JOptionPane.showMessageDialog(frame, "SpotBugs run completed");

    }
  }
  
     private String runExtension(String mycommand) throws IOException, InterruptedException {



            ProcessBuilder pb = new ProcessBuilder(mycommand.split(" +"));
            pb.redirectErrorStream(true);
            final Process p = pb.start();

            final StringBuilder output = new StringBuilder();
            Thread reader = new Thread(new Runnable() {
                public void run() {

                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String s;
                    try {
                        while ((s = stdInput.readLine()) != null ){
                            output.append(s);
                            output.append(LINE_SEPARATOR);
                        }
                    } catch (IOException e) {
                        output.append(e.toString());
                        e.printStackTrace();
                    } finally {
                        try { stdInput.close(); } catch (IOException e) { //quiet

                         }
                    }
                }
            });
            reader.setDaemon(true);
            reader.start();
            p.waitFor();
            return output.toString();

    }




  class PmdClassMenuAction extends AbstractAction {
    /**
     * Serial ID for this action.
     */
    private static final long serialVersionUID = 1L;

    public PmdClassMenuAction() {

    }
    
    public PmdClassMenuAction(String menuName) {
      putValue(AbstractAction.NAME, menuName);
    }

    public void actionPerformed(ActionEvent anEvent) {

      String[] params = {"PMD Analysis"};
      SingleReportPane.initGui(params);
      ArrayList<File> sourceFiles = new ArrayList<File>();
      System.out.println(sourceFile.getName());
      System.out.println(sourceFile.getPath());
      sourceFiles.add(sourceFile);
      System.out.println(javaFileName);
      PmdTool pmd = new PmdTool();
      
      analyzeSourceCommand = new AnalyzeSourceCommand(pmd, sourceFiles);
      createReportCommand = new CreateReportCommand(pmd);
      showReportCommand = new ShowReportCommand(pmd);
      analyzeSourceCommand.execute();
      createReportCommand.execute();
      showReportCommand.execute();
    }
  }
  
}
