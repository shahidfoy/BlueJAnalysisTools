Feature: View errors created by Checkstlye
  In order to find possible bugs
  As a user with source code, I want:
   -to be able to invoke Checkstlye from the BlueJ interface
   -to be shown the description of violations found by Checkstlye, including:
      -where the warning is in my source code
      -the severity of the warning
      -the name of the rule that produced the warning
      -a description that explains the warning
   -A way to get a report of all violations found by Checkstlye for all the
   	 source files for the current project in my running BlueJ instance

  Scenario: Report Review - Table
    Given that there exists a file containing Checkstlye's analysis of some files
    When I run the "Checkstlye Analysis" report
    Then a window appears titled "Checkstlye Report"
    # TODO: validate there are errors in the window

  Scenario: Source Analysis - File
  	Given that there exist Checkstlye issues in the source code
  	When I run the "Checkstlye Analysis" report in the "Tools" menu in BlueJ
  	Then my project's source files are analyzed by Checkstlye
  	And a file containing the results of Checkstlye's analysis is created.