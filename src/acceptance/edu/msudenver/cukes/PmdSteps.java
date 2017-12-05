package edu.msudenver.cukes;

import static org.junit.Assert.assertNotNull;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.PendingException;

import edu.msudenver.CodeQualityExtension;

public class PmdSteps {

  @Given("^that there are PMD issues in the source code$")
  public void that_there_are_PMD_issues_in_the_source_code() throws Exception {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^I run the \"([^\"]*)\" report$")
  public void i_run_the_report(String arg1) throws Exception {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Then("^a window appears titled \"([^\"]*)\"$")
  public void a_window_appears_titled(String arg1) throws Exception {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

}
