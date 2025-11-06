package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
    @Step("Check if we are on the Login Page")
    public boolean areWeOnLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean isLoaded = false;
        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(login_btn));
            isLoaded = element.isDisplayed();
        }
        catch (TimeoutException e){
            isLoaded = false;
        }
        return isLoaded;
    }

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
