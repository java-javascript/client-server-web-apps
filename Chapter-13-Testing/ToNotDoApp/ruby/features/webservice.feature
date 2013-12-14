Feature: WebService
  As a user,
  I want to be able to log in, add and and list the feature through the API

  Scenario: Success add/list

    Given correct credentials
    When I add and list To Not Do items
    Then the returned list should contain the item I added