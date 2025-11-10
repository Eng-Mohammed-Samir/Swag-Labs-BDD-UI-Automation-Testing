Feature: Item details page from the inventory page

  Scenario: Verify that User can add/remove items from their details page successfully then complete the purchase flow
    When User enters valid credentials
    And User clicks on the login button
    Then User should be redirected to the inventory page

    When User clicks on "T-Shirt (Red)" item's name from the inventory page
    Then User should be redirected to "T-Shirt (Red)" item's details page

    When User clicks on "Add to Cart" button in the item details page
    Then The cart badge should show the correct count 1

    When User clicks on "Remove" button in the item details page
    Then The cart badge should show the correct count 0

    When User clicks on back to products button
    Then User should be redirected to the inventory page

    When User clicks on "Fleece Jacket" item's name from the inventory page
    Then User should be redirected to "Fleece Jacket" item's details page

    When User clicks on "Add to Cart" button in the item details page
    Then The cart badge should show the correct count 1

    When User clicks on back to products button
    Then User should be redirected to the inventory page

    When User clicks on "Backpack" item's name from the inventory page
    Then User should be redirected to "Backpack" item's details page

    When User clicks on "Add to Cart" button in the item details page
    Then The cart badge should show the correct count 2

    When User clicks on shopping cart icon
    Then User should be redirected to the cart page
    And "Backpack" item should be displayed in the cart
    And "Fleece Jacket" item should be displayed in the cart

    When User clicks checkout button
    Then User should be redirected to the checkout information page

    When User fills in the checkout information form with valid data
    And User clicks continue button
    Then User should be redirected to the checkout overview page

    And payment information should be displayed
    And shipping information should be displayed
    And the total price should be calculated correctly
    And "Fleece Jacket" item should be displayed in the checkout overview page
    And "Backpack" item should be displayed in the checkout overview page

    When User clicks finish button
    Then User should be redirected to the checkout complete page
    And A thank you message should be displayed

    When User clicks on the context menu
    And User clicks on logout button
    Then User should be logged out successfully