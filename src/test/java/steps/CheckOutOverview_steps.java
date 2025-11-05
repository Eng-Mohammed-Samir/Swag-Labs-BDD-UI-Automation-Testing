package steps;

import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckOutOverview_steps {

    @When("user clicks finish button")
    public void user_clicks_finish_button() {
        Hooks.checkOutOverviewPage.clickFinishButton();
    }

    @Then("User should be redirected to the checkout overview page")
    public void user_should_be_redirected_to_the_checkout_overview_page() {
        Assert.assertTrue(Hooks.checkOutOverviewPage.areWeInCheckOutOverViewPage());
    }

    @Then("checkout overview page should display the correct 3 products")
    public void checkout_overview_page_should_display_the_correct_3_products() {
        Hooks.softAssert.assertTrue(Hooks.checkOutOverviewPage.isItemExist(Hooks.product1));
        Hooks.softAssert.assertTrue(Hooks.checkOutOverviewPage.isItemExist(Hooks.product2));
        Hooks.softAssert.assertTrue(Hooks.checkOutOverviewPage.isItemExist(Hooks.product3));
        Hooks.softAssert.assertAll();
    }

    @Then("payment information should be displayed")
    public void payment_information_should_be_displayed() {
        Assert.assertTrue(Hooks.checkOutOverviewPage.isPaymentInformationDisplayed());
    }

    @Then("shipping information should be displayed")
    public void shipping_information_should_be_displayed() {
        Assert.assertTrue(Hooks.checkOutOverviewPage.isShippingInformationDisplayed());
    }

    @Then("the total price should be calculated correctly")
    public void the_total_price_should_be_calculated_correctly() {
        float itemsTotalAmount = Hooks.checkOutOverviewPage.getItemsTotalAmount();
        float taxAmount = Hooks.checkOutOverviewPage.getTaxAmount();
        Assert.assertEquals(Hooks.checkOutOverviewPage.getTotalAmount(), itemsTotalAmount + taxAmount);
    }
}
