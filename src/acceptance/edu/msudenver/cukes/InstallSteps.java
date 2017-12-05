package edu.msudenver.cukes;

import static org.junit.Assert.assertNotNull;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.PendingException;

import edu.msudenver.CodeQualityExtension;

public class InstallSteps {

    public static CodeQualityExtension extension;

    @Given("^the extension is installed in Bluej$")
    public void the_extension_is_installed_in_Bluej() throws Exception {
        extension = new CodeQualityExtension();
        assertNotNull(extension);
    }

    @When("^I click the \"([^\"]*)\" menu item$")
    public void i_click_the_menu_item(String arg1) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
    @Then("^the pane should show \"([^\"]*)\" as installed$")
    public void the_pane_should_show_as_installed(String name) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^should show the version that is installed$")
    public void should_show_the_version_that_is_installed() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
