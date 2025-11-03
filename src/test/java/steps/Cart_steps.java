package steps;

import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Cart_steps {
    @When("User clicks checkout button")
    public void user_clicks_checkout_button() {
        Hooks.cartPage.clickCheckoutButton();
    }

    @Then("user should be redirected to the cart page")
    public void user_should_be_redirected_to_the_cart_page() {
        Assert.assertTrue(Hooks.cartPage.areWeInCartPage());
    }

    @Then("cart page should display the correct 3 products")
    public void cart_page_should_display_the_correct_3_products() {
        Hooks.softAssert.assertTrue(Hooks.cartPage.isItemExist(Hooks.product1));
        Hooks.softAssert.assertTrue(Hooks.cartPage.isItemExist(Hooks.product2));
        Hooks.softAssert.assertTrue(Hooks.cartPage.isItemExist(Hooks.product3));
        Hooks.softAssert.assertAll();
    }


}
