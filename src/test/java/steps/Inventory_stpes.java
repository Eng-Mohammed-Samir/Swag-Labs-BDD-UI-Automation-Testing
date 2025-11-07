package steps;

import context.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Inventory_stpes {
    private final ScenarioContext scenarioContext;

    // Constructor injection by PicoContainer
    public Inventory_stpes(ScenarioContext context) {
        this.scenarioContext = context;
    }

    @When("User adds {string} item to the cart")
    public void user_adds_item_to_the_cart(String itemName) {
        scenarioContext.getInventoryPage().addItemToCart(itemName);
    }

    @When("user clicks on shopping cart icon")
    public void user_clicks_on_shopping_cart_icon() {
        scenarioContext.getInventoryPage().clickOnCartIcon();
    }

    @Then("User should be redirected to the inventory page")
    public void user_should_be_redirected_to_the_inventory_page() {
        Assert.assertTrue(scenarioContext.getInventoryPage().areWeInInventoryPage());
    }

    @Then("The cart badge should show the correct count")
    public void the_cart_badge_should_show_the_correct_count() {
        Assert.assertEquals(scenarioContext.getInventoryPage().getNumberOfProductsInCart(), 3);
    }


}
