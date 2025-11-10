package steps;

import context.ScenarioContext;
import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class Login_steps {
    private final ScenarioContext scenarioContext;

    // Constructor injection by PicoContainer
    public Login_steps(ScenarioContext context) {
        this.scenarioContext = context;
    }

    @When("User enters valid credentials")
    public void User_enters_valid_credentials() {
        scenarioContext.getLoginPage().enterUserName(scenarioContext.getStandardUsername());
        scenarioContext.getLoginPage().enterPassword(scenarioContext.getPassword());
    }

    @When("User clicks on the login button")
    public void User_clicks_on_the_login_button() {
        scenarioContext.getLoginPage().clickLoginButton();
    }

    @Then("User should be logged out successfully")
    public void User_should_be_logged_out_successfully() {
        scenarioContext.getLoginPage().areWeOnLoginPage();
    }
}
