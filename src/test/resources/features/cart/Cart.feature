@cart @smoke
Feature: Shopping Cart
  As a logged-in user
  I want to manage my shopping cart
  So that I can add and remove products

  @requiresLogin @happy
  Scenario: Happy - Added product appears in cart with correct name, price, and quantity
    Given I navigate to the products page
    When  I capture and add the first product to the cart
    Then  the cart should contain the product with correct name, price, and quantity

  @requiresLogin @negative
  Scenario: Negative - Remove a product from the cart leaves it empty
    Given I navigate to the products page
    And   I capture and add the first product to the cart
    And   I go to the cart page
    When  I remove the product from the cart
    Then  the cart should be empty
