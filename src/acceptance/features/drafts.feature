# Drafts: These are high level user stories that need
# to be turned into less vague features and scenarios
Feature: Maintainence
  In order to have a maintainable project
  As a developer
  I don't want to manually download dependencies
  I want to know when dependencies are out of date
  I want to upgrading bluej to be automatic
  I want to have confidence in upgrading dependencies
  I want the build to be reproducible

Feature: Using the extension
  In order to improve the code quality of my code
  As a student
  I want to configure which pmd rules that should be run on my files
  I want to suppress a specific pmd rule for a particular file
  I want go to the line referenced when I click the warning for a <tool>
  I want to see the number of errors|warnings that exist for each file
    and/or at what severity
    and/or for what tool
  I want to see the percentage of coverage for {lines,forks,branches} change when I run tests
  I want click the link for the reference for the pmd rule
  I want the line and column to be highlighted, showing the position of the warning
  I want to configure which tools ( out of the ones available ) to be enabled by default
    for reporting across files
  I don't want to have to configure the support files for each quality extension tool, I
    just want to work with sensible defaults
  I also want to be limited to sensible defaults, I want to expand or replace the default
    configurations for each tool
  I want a hard copy of the results, so I can export them other things
  I don't want separate windows for each tool

Feature: Global Configuration
  In order to monitor my student's code quality
  As a teacher
  I want to collect static analysis tool output to summarize quality across my students
  I want to set the default config all the tools for all my classes

