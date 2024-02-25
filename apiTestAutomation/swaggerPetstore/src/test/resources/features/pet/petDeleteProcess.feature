Feature: Everything about deleting process of Pets.
  As a pet store employee
  I need to delete some enrolled pets
  to maintain updated the customers list.

  Scenario: Delete a pet
    Given a pet already exists in the system
    When the employee execute the delete process
    Then the platform will delete the pet from the system
