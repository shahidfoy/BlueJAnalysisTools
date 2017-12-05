package edu.msudenver;

import static org.junit.Assert.assertEquals;

import edu.msudenver.CodeQualityExtension;

import org.junit.Test;

public class CodeQualityExtensionTest {

  private CodeQualityExtension extension;

  @Test
  public void hasName() {
    extension = new CodeQualityExtension();
    assertEquals(extension.getName(), "ArtisanCQ");
  }

  @Test
  public void hasVersion() {
    extension = new CodeQualityExtension();
    assertEquals(extension.getVersion(), "0.5.0");
  }

}
