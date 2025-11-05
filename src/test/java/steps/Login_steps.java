package steps;

import hooks.Hooks;
import io.cucumber.java.en.When;

public class Login_steps {

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
        Hooks.loginPage.enterUserName(Hooks.standardUsername);
        Hooks.loginPage.enterPassword(Hooks.password);
    }

    @When("User clicks on the login button")
    public void user_clicks_on_the_login_button() {
        Hooks.loginPage.clickLoginButton();
    }
}
