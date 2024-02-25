Feature: Everything about searching Pets.
  As a pet store employee
  I need to search some enrolled pets
  to verify some information about customers.

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

  Scenario Outline: Find pets by tag.
    Given exists a pet with the "<tag>"
    When the employee search by tag "<tag>"
    Then he will see a list of pets related with the tag "<tag>"
    Examples:
      | tag  |
      | sick |

  Scenario: Find pets by multiple tags.
    Given exists some pets with this tags
      | brave    |
      | friendly |
      | charming |
    And we know that tag "crazy" doesn't exist
    When the employee search by all mentioned tags before
    Then he will see a list of pets related only with existing tags