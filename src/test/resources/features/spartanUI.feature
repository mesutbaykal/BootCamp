Feature: Managing my Spartans Army
  Agile Story: As the Commander of Spartans army,
  I should be able to manage my army so that I can win the wars

  Background: navigate to main page
    Given Commander is at the main page

  Scenario: Viewing selected Spartans info
    When Commander clicks on view button of the spartan with the ID number "3"
    Then Verifies the name of the spartan is "Fidole"

  @spartans
  Scenario Outline:  Viewing selected Spartans info
    When Commander clicks on view button of the spartan with the ID number "<id>"
    Then Verifies the name of the spartan is "<name>"
    Examples:
      | id | name     |
      | 3  | Fidole   |
      | 35 | Gardiner |
      | 7  | Hershel  |