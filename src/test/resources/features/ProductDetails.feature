Feature: Product Details Validation

  Scenario: Validate Product Details After Search
    Given User searches product on the home page
    When User searches for "MacBook" and opens product page
    Then Product title should be displayed
    And Product description should be displayed
    And Product price should be displayed
    And Product images should be displayed
    And Product availability should be displayed
    When User adds the product to the wishlist
    Then Product should be added to the wishlist successfully
