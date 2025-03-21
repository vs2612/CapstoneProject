Feature: Shopping Cart Functionality

  Scenario: Add, Update, and Remove Items in Cart
    Given User is on the home page to add items
    When User adds a product to the cart
    Then Product should be added to the cart
    When User updates product quantity to 3
    Then Product quantity should be updated

