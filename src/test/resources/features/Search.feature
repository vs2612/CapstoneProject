Feature: Search Functionality

  Scenario: Search for MacBook, filter by category, and sort results
    Given User is on the home page
    When User performs a product search for "MacBook"
    Then User filters results by first category
    Then User sorts results by highest price to lowest
    Then User sorts results by highest rating to lowest
