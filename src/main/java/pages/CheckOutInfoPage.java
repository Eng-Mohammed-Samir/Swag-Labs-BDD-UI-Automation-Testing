package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutInfoPage extends BasePage{
    // Constructor
    public CheckOutInfoPage(WebDriver driver) {
        super(driver);
    }

    // Page locators
    private final By checkoutInfo_label = By.className("title");
    private final By firstName_field = By.id("first-name");
    private final By lastName_field = By.id("last-name");
    private final By postalCode_field = By.id("postal-code");
    private final By cancel_btn = By.xpath("//button[@class = 'btn btn_secondary back btn_medium cart_cancel_link']");
    private final By continue_btn = By.xpath("//input[@class = 'submit-button btn btn_primary cart_button btn_action']");
    private final By errorMessage_label = By.cssSelector("h3[data-test='error']");
    private final By errorClose_btn = By.className("error-button");

    // Page methods
    @Step("Check if we are in Checkout Info Page")
    public boolean areWeInCheckOutInfoPage(){
        return super.waitForPageToLoad(checkoutInfo_label, 5);
    }

    @Step("Enter first name: {firstName}")
    public void enterFirstName(String firstName){
        driver.findElement(firstName_field).sendKeys(firstName);
    }

    @Step("Enter last name: {lastName}")
    public void enterLastName(String lastName) {
        driver.findElement(lastName_field).sendKeys(lastName);
    }

    @Step("Enter postal code: {postalCode} ")
    public void enterPostalCode(String postalCode) {
        driver.findElement(postalCode_field).sendKeys(postalCode);
    }

    @Step("Click on Cancel button")
    public void clickCancelButton(){
        driver.findElement(cancel_btn).click();
    }

    @Step("Click on Continue button")
    public void clickContinueButton() {
        driver.findElement(continue_btn).click();
    }

    @Step("Get error message text")
    public String getErrorMessageText(){
        return driver.findElement(errorMessage_label).getText();
    }

    @Step("Ignore error message by clicking close button")
    public void closeErrorMessage(){
        driver.findElement(errorClose_btn).click();
    }
}
