Feature: User Login

  Scenario Outline: Logging in with registered credentials
    Given User has registered and is on the login page
    When User enters login credentials from Excel <RowIndex>
    And User clicks on login button
    Then User should be logged in successfully

    Examples:
      | RowIndex |
      | 1       | # Correct credentials

  Scenario Outline: Attempt login with incorrect credentials and reset password
    Given User has registered and is on the login page
    When User enters login credentials from Excel <RowIndex>
    And User clicks on login button
    Then User should be redirected to reset password page and reset password

    Examples:
      | RowIndex |
      | 2       | # Incorrect credentials
      

