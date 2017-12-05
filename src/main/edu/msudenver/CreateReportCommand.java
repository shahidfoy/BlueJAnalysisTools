package edu.msudenver;

public class CreateReportCommand extends Command {
  
  private Tool receiver;
  
  /**
   * Constructs a Create Report Command associated with a particular tool.
   * @param inputReceiver the tool with the resources to execute the command.
   */
  public CreateReportCommand(Tool inputReceiver) {
    receiver = inputReceiver;
  }
  
  @Override
  public void execute() {
    receiver.createReport();
  }

}
