Feature: Home Page Validation

  Scenario: Validate Search and Recommended For You
    Given User is on the home page
    Then Recommended For You section should be displayed
    Then MacBookAir should be displayed
    When User searches for "Macbook"
