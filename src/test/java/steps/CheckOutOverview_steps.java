package steps;

import context.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CheckOutOverview_steps {
    private final ScenarioContext scenarioContext;

    // Constructor injection by PicoContainer
    public CheckOutOverview_steps(ScenarioContext context) {
        this.scenarioContext = context;
    }

    @When("User clicks finish button")
    public void User_clicks_finish_button() {
        scenarioContext.getCheckOutOverviewPage().clickFinishButton();
    }

    @Then("User should be redirected to the checkout overview page")
    public void User_should_be_redirected_to_the_checkout_overview_page() {
        Assert.assertTrue(scenarioContext.getCheckOutOverviewPage().areWeInCheckOutOverViewPage());
    }

    @Then("{string} item should be displayed in the checkout overview page")
    public void checkout_overview_page_should_display_the_correct_product(String itemName) {
        Assert.assertTrue(scenarioContext.getCheckOutOverviewPage().isItemExist(itemName));
    }

    @Then("payment information should be displayed")
    public void payment_information_should_be_displayed() {
        Assert.assertTrue(scenarioContext.getCheckOutOverviewPage().isPaymentInformationDisplayed());
    }

    @Then("shipping information should be displayed")
    public void shipping_information_should_be_displayed() {
        Assert.assertTrue(scenarioContext.getCheckOutOverviewPage().isShippingInformationDisplayed());
    }

    @Then("the total price should be calculated correctly")
    public void the_total_price_should_be_calculated_correctly() {
        float itemsTotalAmount = scenarioContext.getCheckOutOverviewPage().getItemsTotalAmount();
        float taxAmount = scenarioContext.getCheckOutOverviewPage().getTaxAmount();
        double value = itemsTotalAmount + taxAmount;
        BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals(scenarioContext.getCheckOutOverviewPage().getTotalAmount(), bd.doubleValue());
    }
}
