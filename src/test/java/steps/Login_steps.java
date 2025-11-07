package steps;

import context.ScenarioContext;
import hooks.Hooks;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class Login_steps {
    private final ScenarioContext scenarioContext;

    // Constructor injection by PicoContainer
    public Login_steps(ScenarioContext context) {
        this.scenarioContext = context;
    }

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
        scenarioContext.getLoginPage().enterUserName(scenarioContext.getStandardUsername());
        scenarioContext.getLoginPage().enterPassword(scenarioContext.getPassword());
    }

    @When("User clicks on the login button")
    public void user_clicks_on_the_login_button() {
        scenarioContext.getLoginPage().clickLoginButton();
    }
}
