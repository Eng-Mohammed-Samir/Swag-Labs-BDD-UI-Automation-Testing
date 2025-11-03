Feature: Authentication Flow steps

  Scenario: Verify that user can login, add items to cart, and complete purchase
    When user enters valid credentials
    And User clicks on the login button
    Then User should be redirected to the inventory page

    When User adds 3 items to the cart
    Then The cart badge should show the correct count

    When user clicks on shopping cart icon
    Then user should be redirected to the cart page
    And cart page should display the correct 3 products

    When User clicks checkout button
    Then User should be redirected to the checkout information page

    When User fills in the checkout information form with valid data
    And User clicks continue button
    Then User should be redirected to the checkout overview page
    And checkout overview page should display the correct 3 products
    And payment information should be displayed
    And shipping information should be displayed
    And the total price should be calculated correctly

    When user clicks finish button
    Then User should be redirected to the checkout complete page
    And A thank you message should be displayed

    When user clicks back home button
    Then user should be redirected to inventory page

