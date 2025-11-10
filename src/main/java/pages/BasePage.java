package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected final WebDriver driver;
    protected Actions actions;
    protected Wait<WebDriver> wait;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

  // Shared locators
    // Header locators
    private final By sideNavigationMenu_btn = By.xpath("//div[@class='bm-burger-button']//button");
    private final By allItems_btn = By.id("inventory_sidebar_link");
    private final By about_btn = By.id("about_sidebar_link");
    private final By logOut_btn = By.id("logout_sidebar_link");
    private final By resetAppState_btn = By.id("reset_sidebar_link");
    private final By cart_icon = By.id("shopping_cart_container");
    private final By cartProductsCounter = By.cssSelector(".shopping_cart_container .shopping_cart_badge");

    // Footer locators
    private final By twitter_icon = By.cssSelector(".social_twitter a");
    private final By facebook_icon = By.cssSelector(".social_facebook a");
    private final By linkedin_icon = By.cssSelector(".social_linkedin a");

    // Custom methods
    public boolean waitForPageToLoad(By locator, int timeoutInSeconds){
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        boolean isLoaded = false;
        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            isLoaded = element.isDisplayed();
        }
        catch (TimeoutException e){
            isLoaded = false;
        }
        return isLoaded;
    }

    public void moveToElementAndClick(By locator){
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    // Shared methods
    @Step("Click on Side Navigation Menu")
    public void clickOnSideNavigationMenu(){
        driver.findElement(sideNavigationMenu_btn).click();
    }

    @Step("Navigate to All Items page")
    public void navigateToAllItems(){
        driver.findElement(allItems_btn).click();
    }

    @Step("Navigate to About page")
    public void navigateToAboutPage(){
        driver.findElement(about_btn).click();
    }

    @Step("Log out from the application")
    public void logOut(){
        driver.findElement(logOut_btn).click();
    }

    @Step("Reset App State")
    public void resetAppState() {
        driver.findElement(resetAppState_btn).click();
    }

    @Step("Go to Cart Page")
    public void clickOnCartIcon() {
        this.moveToElementAndClick(cart_icon);
    }

    @Step("Get number of products in cart")
    public int getNumberOfProductsInCart() {
        String counterText = null;
        try {
            counterText = driver.findElement(cartProductsCounter).getText();
        } catch (NoSuchElementException e) {
            counterText = "0";
        }
        return Integer.parseInt(counterText);
    }

    @Step("Navigate to Twitter Page")
    public void goToTwitterPage() {
        driver.findElement(twitter_icon).click();
    }

    @Step("Navigate to Facebook Page")
    public void goToFacebookPage() {
        driver.findElement(facebook_icon).click();
    }

    @Step("Navigate to LinkedIn Page")
    public void goToLinkedInPage() {
        driver.findElement(linkedin_icon).click();
    }
}