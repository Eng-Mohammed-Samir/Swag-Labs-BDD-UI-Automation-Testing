package steps;

import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckOutInfo_steps {

    @When("User fills in the checkout information form with valid data")
    public void user_fills_in_the_checkout_information_form_with_valid_data() {
        Hooks.checkOutInfoPage.enterFirstName(Hooks.checkoutFirstName);
        Hooks.checkOutInfoPage.enterLastName(Hooks.checkoutLastName);
        Hooks.checkOutInfoPage.enterPostalCode(Hooks.checkoutPostalCode);
    }

    @When("User clicks continue button")
    public void user_clicks_continue_button() {
        Hooks.checkOutInfoPage.clickContinueButton();
    }

    @Then("User should be redirected to the checkout information page")
    public void user_should_be_redirected_to_the_checkout_information_page() {
        Assert.assertTrue(Hooks.checkOutInfoPage.areWeInCheckOutInfoPage());
    }
}
