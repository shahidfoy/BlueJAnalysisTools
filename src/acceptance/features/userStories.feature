Feature: User Stories
As any Stakeholder, I want my interests represented in the project so that they are considered when
 decisions regarding the direction for development efforts are made.
As a Developer, I want user stories to allow me to systematically direct my development efforts
As a Developer or Maintainer, I want the product's requirements to be part of the project 
 repository.

 Scenario: user stories in the repository
 Given user stories have been written
 When I navigate to a "../src/acceptance/features" in the project repository
 Then There exists documentation detailing user stories