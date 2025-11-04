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
    public void user_clicks_checkout_button() {
        scenarioContext.getCartPage().clickCheckoutButton();
    }

    @Then("user should be redirected to the cart page")
    public void user_should_be_redirected_to_the_cart_page() {
        Assert.assertTrue(scenarioContext.getCartPage().areWeInCartPage());
    }

    @Then("{string} item should be displayed in the cart")
    public void cart_page_should_display_the_correct_product(String itemName) {
        Assert.assertTrue(scenarioContext.getCartPage().isItemExist(itemName));
    }


}
