package steps;

import context.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Cart_steps {
    private final ScenarioContext scenarioContext;

    // Constructor injection by PicoContainer
    public Cart_steps(ScenarioContext context) {
        this.scenarioContext = context;
    }

    // Step Definitions
    @When("User clicks checkout button")
    public void User_clicks_checkout_button() {
        scenarioContext.getCartPage().clickCheckoutButton();
    }

    @When("User removes {string} item from the cart")
    public void User_removes_item_from_the_cart(String itemName) {
        scenarioContext.getCartPage().removeCartItemByName(itemName);
    }

    @When(("User clicks on {string} item's name from the cart page"))
    public void user_clicks_on_item_name(String itemName) {
        scenarioContext.getCartPage().navigateToProductPageFromCart(itemName);
    }

    @Then("User should be redirected to the cart page")
    public void User_should_be_redirected_to_the_cart_page() {
        Assert.assertTrue(scenarioContext.getCartPage().areWeInCartPage());
    }

    @Then("{string} item should be displayed in the cart")
    public void cart_page_should_display_the_correct_product(String itemName) {
        Assert.assertTrue(scenarioContext.getCartPage().isItemExist(itemName));
    }

    @Then("{string} item should no longer be displayed in the cart")
    public void item_should_no_longer_be_displayed_in_the_cart(String itemName) {
        Assert.assertFalse(scenarioContext.getCartPage().isItemExist(itemName));
    }
}
