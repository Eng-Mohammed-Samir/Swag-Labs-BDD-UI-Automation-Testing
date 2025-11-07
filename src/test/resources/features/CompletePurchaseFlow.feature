Feature: Authentication Flow steps

  Scenario: Verify that user can login, add items to cart, and complete purchase
    When user enters valid credentials
    And User clicks on the login button
    Then User should be redirected to the inventory page

    When User adds "Backpack" item to the cart
    And User adds "Bike Light" item to the cart
    And User adds "Bolt T-Shirt" item to the cart
    Then The cart badge should show the correct count

    When user clicks on shopping cart icon
    Then user should be redirected to the cart page
    And "Backpack" item should be displayed in the cart
    And "Bike Light" item should be displayed in the cart
    And "Bolt T-Shirt" item should be displayed in the cart

    When User clicks checkout button
    Then User should be redirected to the checkout information page

    When User fills in the checkout information form with valid data
    And User clicks continue button
    Then User should be redirected to the checkout overview page

    And payment information should be displayed
    And shipping information should be displayed
    And the total price should be calculated correctly
    And "Backpack" item should be displayed in the checkout overview page
    And "Bike Light" item should be displayed in the checkout overview page
    And "Bolt T-Shirt" item should be displayed in the checkout overview page
    When user clicks finish button
    Then User should be redirected to the checkout complete page
    And A thank you message should be displayed

    When user clicks back home button
    Then User should be redirected to the inventory page

