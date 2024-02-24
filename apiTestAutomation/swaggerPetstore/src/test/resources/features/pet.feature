Feature: Everything about your Pets.

  Scenario: Add a new pet to the store.
    Given there is a cute pet in the reception
    When the pet is enrolled
    Then the pet will be available in the platform

  Scenario Outline: Finds pets by existing status.
    When the pet store employee search by "<status>"
    Then he will see a list of pets relate with the status "<status>"
    Examples:
      | status    |
      | available |
      | pending   |
      | sold      |

  Scenario Outline: Finds pets by unexpected status.
    When the pet store employee search by "<status>"
    Then he will not see any result
    Examples:
      | status |
      | other  |

    Scenario: Update an existing pet by Id
      Given a pet already exists in the system
      When the personal support change the name to "Lucky"
      Then the platform will taken the change

  Scenario: Update an existing pet by Id - Non-existing Id
    Given a pet already exists in the system
    When the personal support change the name to "Lucky", and someone had deleted the record before update
    Then the platform will indicate an alert related to pet not found