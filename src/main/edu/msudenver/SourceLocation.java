package edu.msudenver;


/**
 * A location in a source code file.
 */
public class SourceLocation {

  /**
   * The fileincluding path. could be used to read the actual file from disk.
   */
  private final String fileName;

  /**
   * A line number in the file.
   */
  private final Number line;

  /**
   * A column position in the file.
   */
  private final Number column;

  /**
   * Create a source code location, pointing to a line and column of the file.
   * @param fileName the file, including file path and extension
   * @param line the line in the file
   * @param column the column in the file
   */
  public SourceLocation(String fileName, Number line, Number column) {
    this.fileName = fileName;
    this.line = line;
    this.column = column;
  }

  /**
   * A common text format for line/column positioning (path/file.ext:[line]:[column]).
   */
  @Override
  public String toString() {
    final String DELIMITER = ",";
    return this.fileName + DELIMITER + this.line.toString() + DELIMITER + this.column.toString();
  }

}
