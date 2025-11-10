package steps;

import context.ScenarioContext;
import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CheckOutInfoPage;

public class CheckOutInfo_steps {
    private final ScenarioContext scenarioContext;

    // Constructor injection by PicoContainer
    public CheckOutInfo_steps(ScenarioContext context) {
        this.scenarioContext = context;
    }

    @When("User fills in the checkout information form with valid data")
    public void User_fills_in_the_checkout_information_form_with_valid_data() {
        scenarioContext.getCheckOutInfoPage().enterFirstName(scenarioContext.getCheckoutFirstName());
        scenarioContext.getCheckOutInfoPage().enterLastName(scenarioContext.getCheckoutLastName());
        scenarioContext.getCheckOutInfoPage().enterPostalCode(scenarioContext.getCheckoutPostalCode());
    }

    @When("User clicks continue button")
    public void User_clicks_continue_button() {
        scenarioContext.getCheckOutInfoPage().clickContinueButton();
    }

    @Then("User should be redirected to the checkout information page")
    public void User_should_be_redirected_to_the_checkout_information_page() {
        Assert.assertTrue(scenarioContext.getCheckOutInfoPage().areWeInCheckOutInfoPage());
    }
}
