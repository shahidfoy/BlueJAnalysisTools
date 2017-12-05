Feature: Install Product
  As an end user of the code quality extension, I want to know where to find a stable version of the 
  	product that is ready to be installed in BlueJ.
  As a maintainer, I want to know that BlueJ recognizes that the extension is installed
  As a maintainer, I want to know that the correct version of the product is installed in BlueJ

  I don't want to:
   - have to download or update supported tools to use them with the extension.

  Background:
    Given the extension is installed in Bluej

  Scenario: Viewing extensions that are installed
    When I click the "Installed Extensions" menu item
    Then the pane should show "ArtisanCQ" as installed
    And should show the version that is installed
