Feature: Checkout

  Scenario: User logs in, adds a MacBook to the cart, and proceeds to checkout
    Given User is on the home page and logs in
    When User visits home page
    When User adds MacBook to the cart
    When User navigates to the shopping cart
    When User proceeds to checkout
    When User fills in billing details with "John", "Doe", "TechCorp", "123 Street", "Suite 4", "London", "E12 3AB"
    When User agrees to the terms and conditions
    Then User attempts to proceed with payment
