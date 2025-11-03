package steps;

import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.*;

public class CompletePurchaseFlow_steps {
    private InventoryPage inventoryPage;
    private LoginPage loginPage;
    private CartPage cartPage;
    private CheckOutInfoPage checkOutInfoPage;
    private CheckOutOverviewPage checkOutOverviewPage;
    private CheckOutCompletePage checkOutCompletePage;

    // Steps
    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
        loginPage = new LoginPage(Hooks.driver);
        loginPage.enterUserName(Hooks.standardUsername);
        loginPage.enterPassword(Hooks.password);
    }

    @When("User clicks on the login button")
    public void user_clicks_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @When("User adds 3 items to the cart")
    public void user_adds_3_items_to_the_cart() {
        inventoryPage.addItemToCart(Hooks.product1);
        inventoryPage.addItemToCart(Hooks.product2);
        inventoryPage.addItemToCart(Hooks.product3);
    }

    @When("user clicks on shopping cart icon")
    public void user_clicks_on_shopping_cart_icon() {
        inventoryPage.clickOnCartIcon();
    }

    @When("User clicks checkout button")
    public void user_clicks_checkout_button() {
        cartPage.clickCheckoutButton();
    }

    @When("User fills in the checkout information form with valid data")
    public void user_fills_in_the_checkout_information_form_with_valid_data() {
        checkOutInfoPage.enterFirstName(Hooks.checkoutFirstName);
        checkOutInfoPage.enterLastName(Hooks.checkoutLastName);
        checkOutInfoPage.enterPostalCode(Hooks.checkoutPostalCode);
    }

    @When("User clicks continue button")
    public void user_clicks_continue_button() {
        checkOutInfoPage.clickContinueButton();
    }

    @When("user clicks finish button")
    public void user_clicks_finish_button() {
        checkOutOverviewPage.clickFinishButton();
    }

    @When("user clicks back home button")
    public void user_clicks_back_home_button() {
        checkOutCompletePage.navigateToInventoryPage();
    }

    @Then("User should be redirected to the inventory page")
    public void user_should_be_redirected_to_the_inventory_page() {
        inventoryPage = new InventoryPage(Hooks.driver);
        Assert.assertTrue(inventoryPage.areWeInInventoryPage());
    }

    @Then("The cart badge should show the correct count")
    public void the_cart_badge_should_show_the_correct_count() {
        Assert.assertEquals(inventoryPage.getNumberOfProductsInCart(), 3);
    }

    @Then("user should be redirected to the cart page")
    public void user_should_be_redirected_to_the_cart_page() {
        cartPage = new CartPage(Hooks.driver);
        Assert.assertTrue(cartPage.areWeInCartPage());
    }

    @Then("cart page should display the correct 3 products")
    public void cart_page_should_display_the_correct_3_products() {
        Hooks.softAssert.assertTrue(cartPage.isItemExist(Hooks.product1));
        Hooks.softAssert.assertTrue(cartPage.isItemExist(Hooks.product2));
        Hooks.softAssert.assertTrue(cartPage.isItemExist(Hooks.product3));
        Hooks.softAssert.assertAll();
    }

    @Then("User should be redirected to the checkout information page")
    public void user_should_be_redirected_to_the_checkout_information_page() {
        checkOutInfoPage = new CheckOutInfoPage(Hooks.driver);
        Assert.assertTrue(checkOutInfoPage.areWeInCheckOutInfoPage());
    }

    @Then("User should be redirected to the checkout overview page")
    public void user_should_be_redirected_to_the_checkout_overview_page() {
        checkOutOverviewPage = new CheckOutOverviewPage(Hooks.driver);
        Assert.assertTrue(checkOutOverviewPage.areWeInCheckOutOverViewPage());
    }

    @Then("checkout overview page should display the correct 3 products")
    public void checkout_overview_page_should_display_the_correct_3_products() {
        Hooks.softAssert.assertTrue(checkOutOverviewPage.isItemExist(Hooks.product1));
        Hooks.softAssert.assertTrue(checkOutOverviewPage.isItemExist(Hooks.product2));
        Hooks.softAssert.assertTrue(checkOutOverviewPage.isItemExist(Hooks.product3));
        Hooks.softAssert.assertAll();
    }

    @Then("payment information should be displayed")
    public void payment_information_should_be_displayed() {
        Assert.assertTrue(checkOutOverviewPage.isPaymentInformationDisplayed());
    }

    @Then("shipping information should be displayed")
    public void shipping_information_should_be_displayed() {
        Assert.assertTrue(checkOutOverviewPage.isShippingInformationDisplayed());
    }

    @Then("the total price should be calculated correctly")
    public void the_total_price_should_be_calculated_correctly() {
        float itemsTotalAmount = checkOutOverviewPage.getItemsTotalAmount();
        float taxAmount = checkOutOverviewPage.getTaxAmount();
        Assert.assertEquals(checkOutOverviewPage.getTotalAmount(), itemsTotalAmount + taxAmount);
    }

    @Then("User should be redirected to the checkout complete page")
    public void user_should_be_redirected_to_the_checkout_complete_page() {
        checkOutCompletePage = new CheckOutCompletePage(Hooks.driver);
        Assert.assertTrue(checkOutCompletePage.areWeInCheckOutCompletePage());
    }

    @Then("A thank you message should be displayed")
    public void a_thank_you_message_should_be_displayed() {
        Assert.assertTrue(checkOutCompletePage.hasTheOrderBeenDispatchedSuccessfully());
    }

    @Then("user should be redirected to inventory page")
    public void user_should_be_redirected_to_inventory_page() {
        Assert.assertTrue(inventoryPage.areWeInInventoryPage());
    }
}