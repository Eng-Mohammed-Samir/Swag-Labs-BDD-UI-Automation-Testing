package steps;

import context.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ProductDetails_steps {
    private final ScenarioContext scenarioContext;

    // Constructor injection by PicoContainer
    public ProductDetails_steps(ScenarioContext context) {
        this.scenarioContext = context;
    }

    @When("User clicks on \"Add to Cart\" button in the item details page")
    public void user_clicks_on_add_to_cart_button_in_the_item_details_page() {
        scenarioContext.getProductDetailsPage().addProductToCart();
    }

    @When("User clicks on \"Remove\" button in the item details page")
    public void user_clicks_on_remove_button() {
        scenarioContext.getProductDetailsPage().removeProductFromCart();
    }

    @When("User clicks on back to products button")
    public void user_clicks_on_back_to_products_button() {
        scenarioContext.getProductDetailsPage().navigateBackToProducts();
    }

    @Then("User should be redirected to {string} item's details page")
    public void user_should_be_redirected_to_item_s_details_page(String itemName) {
        Assert.assertTrue(scenarioContext.getProductDetailsPage().areWeOnProductDetailsPageOf(itemName));
    }
}
