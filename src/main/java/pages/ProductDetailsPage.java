package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage{
    // Constructor
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    // Page locators
    private final By productName = By.className("inventory_details_name");
    private final By productDescription = By.className("inventory_details_desc");
    private final By productPrice = By.className("inventory_details_price");
    private final By productAddToCart_btn = By.id("add-to-cart");
    private final By removeFromCart_btn = By.id("remove");
    private final By backToProducts_btn = By.id("back-to-products");

    // Page methods
    @Step("Get product name")
    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    @Step("Get product description")
    public String getProductDescription() {
        return driver.findElement(productDescription).getText();
    }

    @Step("Get product price")
    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    @Step("Add product to cart")
    public void addProductToCart() {
        driver.findElement(productAddToCart_btn).click();
    }

    @Step("Remove product from cart")
    public void removeProductFromCart() {
        driver.findElement(removeFromCart_btn).click();
    }

    @Step("Navigate back to products page")
    public void navigateBackToProducts() {
        driver.findElement(backToProducts_btn).click();
    }
}
