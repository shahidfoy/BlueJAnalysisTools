package edu.msudenver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import org.junit.Test;

import net.sourceforge.pmd.PMD;

/**
 * A test that proves that PMD can be executed
 * from withing the current classpath, without
 * needing to execute externally.
 */
public class PmdTest {

  @Test
  public void testPmd() throws Exception {
    String args[] = { "-d", "src/main/edu/msudenver/GUI/ReportPane.java", "-f", "xml", "-R", "java-basic,java-design", "-r", "pmd-test-output.xml" };
    int output = PMD.run(args);
    Scanner scanner = new Scanner(new File("pmd-test-output.xml"));
    String text = scanner.useDelimiter("\\A").next();
    scanner.close();
    System.out.println(text);
  }
}
