package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Page locators
    private final By userName_field = By.id("user-name");
    private final By password_field = By.id("password");
    private final By login_btn = By.id("login-button");

    // Page methods
    @Step("Enter user name: {userName}")
    public void enterUserName(String userName) {
        driver.findElement(userName_field).sendKeys(userName);
    }

    @Step("Enter password: {password}")
    public void enterPassword(String password) {
        driver.findElement(password_field).sendKeys(password);
    }

    @Step("Click Login button")
    public void clickLoginButton() {
        driver.findElement(login_btn).click();
    }

    public void login(String userName, String password) {
        enterUserName(userName);
        enterPassword(password);
        clickLoginButton();
    }
}
