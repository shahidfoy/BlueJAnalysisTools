Feature: User initiated quality analysis
  In order to execute a code quality analysis
  As a user with source code
  I want to choose a suite of actions to run analysis

  - Full report: All tools and what they found #validate which options exist w/Dr.Paul
  - PMD analysis
  - Findbugs analysis
  - Checkstyle
  - JaCoCo test coverage

  Scenario: Run Full Report
    Given There is a BlueJ project with source code
    Given I have navigated to the "ArtisanCQ" sub menu
    When I click on "Full Report" menu item
    Then A window appears that says "Full Report"
    And each tool is represented in tabs
