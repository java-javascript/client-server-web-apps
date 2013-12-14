Feature: Login
  As a user,
  I want to be able to log in with correct credentials

  Scenario: Success login

    Given correct credentials
    When I load the page
    Then I should be able to log in