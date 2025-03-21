Feature: User Registration

  Scenario Outline: Registering a new user
    Given User is on the registration page
    When User enters registration details from Excel <RowIndex>
    And User accepts the privacy policy and submits the form
    Then User validates the registration outcome

    Examples:
      | RowIndex |
      | 1       |  # New user - should register successfully
      | 2       |  # Duplicate user - should show error
