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
    public void User_adds_item_to_the_cart(String itemName) {
        scenarioContext.getInventoryPage().addItemToCart(itemName);
    }

    @When("User removes {string} item from the inventory page")
    public void User_removes_item_from_the_inventory_page(String itemName) {
        scenarioContext.getInventoryPage().removeItemFromCart(itemName);
    }

    @Then("User should be redirected to the inventory page")
    public void User_should_be_redirected_to_the_inventory_page() {
        Assert.assertTrue(scenarioContext.getInventoryPage().areWeInInventoryPage());
    }

    @When(("User clicks on {string} item's name from the inventory page"))
    public void user_clicks_on_item_name(String itemName) {
        scenarioContext.getInventoryPage().navigateToItemPage(itemName);
    }
}
