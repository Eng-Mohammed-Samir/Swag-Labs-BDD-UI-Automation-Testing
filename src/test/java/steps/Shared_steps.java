package steps;

import context.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Shared_steps {
    private final ScenarioContext scenarioContext;

    // Constructor injection by PicoContainer
    public Shared_steps(ScenarioContext context) {
        this.scenarioContext = context;
    }

    @When("User clicks on the context menu")
    public void User_clicks_on_the_context_menu() {
        scenarioContext.getBasePage().clickOnSideNavigationMenu();
    }

    @When("User clicks on logout button")
    public void User_logs_out_from_the_application() {
        scenarioContext.getBasePage().logOut();
    }

    @When("User clicks on shopping cart icon")
    public void User_clicks_on_shopping_cart_icon() {
        scenarioContext.getBasePage().clickOnCartIcon();
    }

    @Then("The cart badge should show the correct count {int}")
    public void the_cart_badge_should_show_the_correct_count(int expectedCount) {
        Assert.assertEquals(scenarioContext.getBasePage().getNumberOfProductsInCart(), expectedCount);
    }
}
