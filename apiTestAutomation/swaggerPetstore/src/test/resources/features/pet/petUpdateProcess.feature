Feature: Everything about updating process of Pets.
  As a pet store employee
  I need to update some enrolled pets
  to maintain updated the customers list.

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
