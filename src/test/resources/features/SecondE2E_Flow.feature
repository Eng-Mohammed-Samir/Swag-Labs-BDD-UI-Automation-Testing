Feature: Adding and removing items from the inventory and cart

  Scenario: Verify that User can manage items from the inventory and cart successfully then complete the purchase flow
    When User enters valid credentials
    And User clicks on the login button
    Then User should be redirected to the inventory page

    When User adds "Backpack" item to the cart
    And User adds "Bolt T-Shirt" item to the cart
    And User adds "Fleece Jacket" item to the cart
    And User adds "T-Shirt (Red)" item to the cart
    Then The cart badge should show the correct count 4

    When User removes "Backpack" item from the inventory page
    Then The cart badge should show the correct count 3

    When User clicks on shopping cart icon
    Then User should be redirected to the cart page
    And "Bolt T-Shirt" item should be displayed in the cart
    And "Fleece Jacket" item should be displayed in the cart
    And "T-Shirt (Red)" item should be displayed in the cart

    When User removes "Fleece Jacket" item from the cart
    Then The cart badge should show the correct count 2
    And "Fleece Jacket" item should no longer be displayed in the cart

    When User clicks checkout button
    Then User should be redirected to the checkout information page

    When User fills in the checkout information form with valid data
    And User clicks continue button
    Then User should be redirected to the checkout overview page
    And payment information should be displayed
    And shipping information should be displayed
    And the total price should be calculated correctly
    And "T-Shirt (Red)" item should be displayed in the checkout overview page
    And "Bolt T-Shirt" item should be displayed in the checkout overview page

    When User clicks finish button
    Then User should be redirected to the checkout complete page
    And A thank you message should be displayed

    When User clicks on the context menu
    And User clicks on logout button
    Then User should be logged out successfully