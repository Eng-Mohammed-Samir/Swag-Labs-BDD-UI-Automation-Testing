package steps;

import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Inventory_stpes {

    @When("User adds 3 items to the cart")
    public void user_adds_3_items_to_the_cart() {
        Hooks.inventoryPage.addItemToCart(Hooks.product1);
        Hooks.inventoryPage.addItemToCart(Hooks.product2);
        Hooks.inventoryPage.addItemToCart(Hooks.product3);
    }

    @When("user clicks on shopping cart icon")
    public void user_clicks_on_shopping_cart_icon() {
        Hooks.inventoryPage.clickOnCartIcon();
    }

    @Then("User should be redirected to the inventory page")
    public void user_should_be_redirected_to_the_inventory_page() {
        Assert.assertTrue(Hooks.inventoryPage.areWeInInventoryPage());
    }

    @Then("The cart badge should show the correct count")
    public void the_cart_badge_should_show_the_correct_count() {
        Assert.assertEquals(Hooks.inventoryPage.getNumberOfProductsInCart(), 3);
    }


}
