package edu.msudenver;

import java.net.URLDecoder;
import java.net.URL;

import bluej.extensions.BlueJ;
import bluej.extensions.Extension;
import bluej.extensions.event.PackageEvent;
import bluej.extensions.event.PackageListener;
import edu.msudenver.GUI.ExtensionMenu;

/**
 * Code Quality Extension allows users to analyze their source code using various static analysis
 * tools.
 *
 * @author Artisan Software
 * @version 0.5.0
 */

public class CodeQualityExtension extends Extension implements PackageListener {
  /**
   * The Artisan Code Quality Extension project's redmine site URL.
   */
  private static final String PROJECT_URL =
      "https://gouda.msudenver.edu/redmine/projects/artisan-software";
  /**
   * The current version of the Artisan Code Quality Extension.
   */
  private static final String VERSION_NUMBER = "0.5.0";
  /**
   * The display name of the Artisan Code Quality Extension within BlueJ.
   */
  private static final String EXTENSION_NAME = "ArtisanCQ";
  /**
   * The description of the Artisan Code Quality Extension within BlueJ.
   */
  private static final String EXTENSION_DESCRPTION =
      "An extension to provide static" + " quality analysis of source code";
  /**
   * The submenu item for Artisan Code Quality Extension tasks that will be added to the BlueJ tools
   * menu.
   */
  private ExtensionMenu artisancqToolsMenu;

  /**
   * Allows the extension to start running in BlueJ
   *
   * @param ibluej The BlueJ proxy object which will interface with this extension.
   */
  public void startup(BlueJ ibluej) {
    // add menu items to BlueJ.
    artisancqToolsMenu = new ExtensionMenu();
    ibluej.setMenuGenerator(artisancqToolsMenu);
    try {
      String path = CodeQualityExtension.class.getProtectionDomain().getCodeSource().getLocation().getPath();
      String filename = URLDecoder.decode(path, "UTF-8");
      System.out.println("Running JAR (running.jar) => " + filename);
      System.setProperty("running.jar", filename);
    } catch (Exception e) {
      System.out.println("Cannot resolve the running directory");
    }
  }

  /*
   * Returns the version number of this extension
   *
   * @return the current version number of the Artisan Code Quality Extension.
   */
  public String getVersion() {
    return VERSION_NUMBER;
  }

  /**
   * Returns the user-visible name of this extension.
   *
   * @return the name of the Artisan Code Quality Extension for display within BLueJ.
   */
  public String getName() {
    return EXTENSION_NAME;
  }

  /**
   * Returns a String description of this extension.
   *
   * @return the description of the extension for display within BlueJ.
   */
  public String getDescription() {
    return EXTENSION_DESCRPTION;
  }

  /**
   * Returns the URL to the project's redmine overview page.
   *
   * @return the project URL
   */
  @Override
  public URL getURL() {
    try {
      return new URL(PROJECT_URL);
    } catch (Exception e) {
      // The link is either dead or otherwise unreachable
      System.out.println("Code Quality Extension: getURL:Exception=" + e.getMessage());
      return null;
    }
  }

  @Override
  public boolean isCompatible() {
    // TODO Determine if extension is compatible with current version of BlueJ Extensions API
    return true;
  }

  /**
   * Closes the extension.
   */
  public void terminate() {
    System.out.println("Artisan Code Quality terminates.");
  }

  /**
   * Handles the event that a package is opening.
   */
  @Override
  public void packageOpened(PackageEvent ev) {
    // TODO Decide what, if anything, to do when a package opens
  }

  /*
   * Handles the event that a package is closing.
   */
  @Override
  public void packageClosing(PackageEvent ev) {
    // TODO Decide what to do, if anything, when a package closes.
  }
}
