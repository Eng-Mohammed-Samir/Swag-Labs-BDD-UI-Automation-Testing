Feature: Item details page from the cart page

  Scenario: Verify that user can remove item from product details page accessed via cart then complete the purchase flow
    When User enters valid credentials
    And User clicks on the login button
    Then User should be redirected to the inventory page

    When User adds "Onesie" item to the cart
    And User adds "Bike Light" item to the cart
    And User adds "Bolt T-Shirt" item to the cart
    And User adds "Backpack" item to the cart
    Then The cart badge should show the correct count 4

    When User clicks on shopping cart icon
    Then User should be redirected to the cart page
    And "Onesie" item should be displayed in the cart
    And "Bike Light" item should be displayed in the cart
    And "Bolt T-Shirt" item should be displayed in the cart
    And "Backpack" item should be displayed in the cart

    When User clicks on "Backpack" item's name from the cart page
    Then User should be redirected to "Backpack" item's details page

    When User clicks on "Remove" button in the item details page
    Then The cart badge should show the correct count 3

    When User clicks on shopping cart icon
    Then User should be redirected to the cart page
    And "Onesie" item should be displayed in the cart
    And "Bike Light" item should be displayed in the cart
    And "Bolt T-Shirt" item should be displayed in the cart
    And "Backpack" item should no longer be displayed in the cart

    When User clicks checkout button
    Then User should be redirected to the checkout information page

    When User fills in the checkout information form with valid data
    And User clicks continue button
    Then User should be redirected to the checkout overview page
    And payment information should be displayed
    And shipping information should be displayed
    And the total price should be calculated correctly
    And "Onesie" item should be displayed in the checkout overview page
    And "Bike Light" item should be displayed in the checkout overview page
    And "Bolt T-Shirt" item should be displayed in the checkout overview page

    When User clicks finish button
    Then User should be redirected to the checkout complete page
    And A thank you message should be displayed

    When User clicks on the context menu
    And User clicks on logout button
    Then User should be logged out successfully