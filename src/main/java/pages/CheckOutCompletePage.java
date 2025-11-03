package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutCompletePage extends BasePage{

    // Constructor
    public CheckOutCompletePage(WebDriver driver) {
        super(driver);
    }

    // Page locators
    private final By checkOutComplete_label = By.className("title");
    private final By completeSuccessMsg_header = By.className("complete-header");
    private final By completeInfo_txt = By.className("complete-text");
    private final By backHome_btn = By.id("back-to-products");

    // Page methods
    @Step("Check if we are in Checkout Complete Page")
    public boolean areWeInCheckOutCompletePage() {
        return super.waitForPageToLoad(checkOutComplete_label, 5);
    }

    @Step("Check if the order has been dispatched successfully")
    public boolean hasTheOrderBeenDispatchedSuccessfully() {
        return driver.findElement(completeSuccessMsg_header).isDisplayed();
    }

    @Step("Navigate to Inventory Page")
    public void navigateToInventoryPage() {
        driver.findElement(backHome_btn).click();
    }
}
