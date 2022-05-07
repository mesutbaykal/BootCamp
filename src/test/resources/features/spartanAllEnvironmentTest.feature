Feature: Testing all UI, DB, API info matches
  Scenario: Getting information of one spartan from API
    When User sends a request to spartan API using id 8

  @wip @db
  Scenario: Verify all three environment data matches
    Given Commander is at the main page
    When Commander clicks on view button of the spartan with the ID number "8"
    And User gets info of a certain spartan from DB with ID 8
    And User sends a request to spartan API using id 8
    Then Info of Spartan should be same at all environments