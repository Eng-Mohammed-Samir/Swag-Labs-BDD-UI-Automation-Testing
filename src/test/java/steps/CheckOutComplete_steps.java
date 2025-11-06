package steps;

import context.ScenarioContext;
import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.Hook;
import org.testng.Assert;
import pages.CheckOutCompletePage;

public class CheckOutComplete_steps {
    private final ScenarioContext scenarioContext;

    // Constructor injection by PicoContainer
    public CheckOutComplete_steps(ScenarioContext context) {
        this.scenarioContext = context;
    }

    @When("User clicks back home button")
    public void User_clicks_back_home_button() {
        scenarioContext.getCheckOutCompletePage().navigateToInventoryPage();
    }

    @Then("User should be redirected to the checkout complete page")
    public void User_should_be_redirected_to_the_checkout_complete_page() {
        Assert.assertTrue(scenarioContext.getCheckOutCompletePage().areWeInCheckOutCompletePage());
    }

    @Then("A thank you message should be displayed")
    public void a_thank_you_message_should_be_displayed() {
        Assert.assertTrue(scenarioContext.getCheckOutCompletePage().hasTheOrderBeenDispatchedSuccessfully());
    }
}
