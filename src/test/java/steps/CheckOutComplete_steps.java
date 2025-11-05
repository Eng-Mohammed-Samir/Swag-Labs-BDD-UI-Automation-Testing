package steps;

import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckOutComplete_steps {

    @When("user clicks back home button")
    public void user_clicks_back_home_button() {
        Hooks.checkOutCompletePage.navigateToInventoryPage();
    }

    @Then("User should be redirected to the checkout complete page")
    public void user_should_be_redirected_to_the_checkout_complete_page() {
        Assert.assertTrue(Hooks.checkOutCompletePage.areWeInCheckOutCompletePage());
    }

    @Then("A thank you message should be displayed")
    public void a_thank_you_message_should_be_displayed() {
        Assert.assertTrue(Hooks.checkOutCompletePage.hasTheOrderBeenDispatchedSuccessfully());
    }
}
