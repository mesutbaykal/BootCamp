Feature: Test Spartan API with complete CRUD operations

  Scenario: Read data from Spartan API
    Given User sends a request to Mock API for a mock Spartan Data
    When User uses Mock Data to create a Spartan
    And User sends a request to Spartan API with id 0
    Then Created Spartan has same information with Post Request
    And User Updates all the fields of created Spartan
    And User sends a request to Spartan API with id 0
    Then User deletes spartan 0
