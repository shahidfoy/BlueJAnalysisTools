package edu.msudenver;


/**
 * Show Report Commands allow source code to be analyzed by tools
 * @author Artisan Software
 * @version 0.1.0
 */
public class ShowReportCommand extends Command {

  private Tool receiver;
  
  /**
   * Create a Show Report Command associated with a particular tool.
   * @param inputReceiver the tool with the resources to execute the command.
   */
  public ShowReportCommand(Tool inputReceiver) {
    receiver = inputReceiver;
  }
  
  @Override
  public void execute() {
    receiver.showReport();
  }

}
