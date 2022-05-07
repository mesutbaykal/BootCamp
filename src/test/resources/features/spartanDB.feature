Feature: Connecting and getting info from Spartan DB
  @db
  Scenario: Getting info of a specific spartan with ID number
    When User gets info of a certain spartan from DB with ID 8
    Then Spartan name should be "Rodolfo"

@db @2P
  Scenario: Verify UI and DB info of Spartans
    Given Commander is at the main page
    When Commander clicks on view button of the spartan with the ID number "8"
    And User gets info of a certain spartan from DB with ID 8
    Then Spartan names from UI and DB should match
