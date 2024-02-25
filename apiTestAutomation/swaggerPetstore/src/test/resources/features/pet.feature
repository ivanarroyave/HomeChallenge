Feature: Everything about your Pets.

  Scenario: Add a new pet to the store.
    When the pet is enrolled
    Then the pet will be available in the platform

  Scenario Outline: Finds pets by existing status.
    When the employee search by "<status>"
    Then he will see a list of pets relate with the status "<status>"
    Examples:
      | status    |
      | available |
      | pending   |
      | sold      |

  Scenario Outline: Finds pets by unexpected status.
    When the employee search by "<status>"
    Then he will not see any result
    Examples:
      | status |
      | other  |

  Scenario: Delete a pet
    Given a pet already exists in the system
    When the employee execute the delete process
    Then the platform will delete the pet from the system

  Scenario: Update an existing pet by Id
    Given a pet already exists in the system
    When the personal support change the name to "Lucky"
    Then the platform will taken the change

  Scenario: Update an existing pet by Id - Non-existing Id
    Given a pet already exists in the system
    When the personal support change the name to "Lucky", and someone had deleted the record before update
    Then the platform will indicate an alert related to pet not found

  Scenario: Update an existing pet by Id - Unexpected Id format
    Given a pet already exists in the system
    When the personal support change the name to "Lucky", and some invalid format id is captured by the system
    Then the platform will indicate an alert related to the invalid id supplied
