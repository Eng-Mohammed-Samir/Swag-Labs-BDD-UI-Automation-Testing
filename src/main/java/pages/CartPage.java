package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

public class CartPage extends BasePage{
    // Constructor
    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Page locators
    private final By cart_label = By.className("title");
    private final By cartList = By.className("cart_item");
    private final By cartItemName = By.className("inventory_item_name");
    private final By cartItemPrice = By.className("inventory_item_price");
    private final By cartItemDescription = By.className("inventory_item_desc");
    private final By cartItemRemove_btn = By.xpath("//button[@class = 'btn btn_secondary btn_small cart_button']");
    private final By continueShopping_btn = By.xpath("//button[@class = 'btn btn_secondary back btn_medium']");
    private final By checkout_btn = By.xpath("//button[@class = 'btn btn_action btn_medium checkout_button ']");

    // Page methods
    @Step("Check if we are in Cart Page")
    public boolean areWeInCartPage(){
        return super.waitForPageToLoad(cart_label, 5);
    }

    @Step("Check if item '{item_Name}' exists in cart")
    public boolean isItemExist(String item_Name){
        boolean exists = false;
        if(item_Name.length() >= 4){
            List<WebElement> cartItems = driver.findElements(cartList);
            for (WebElement item : cartItems) {
                String name = item.findElement(cartItemName).getText();
                if (name.toLowerCase().contains(item_Name.toLowerCase())) {
                    exists = true;
                    break;
                }
            }
        }
        return exists;
    }
    @Step("Get price of cart item: {itemName}")
    public String getCartItemPriceByName(String itemName){
        String price = null;
        if(itemName.length() >= 4){
            List<WebElement> cartItems = driver.findElements(cartList);
            for (WebElement item : cartItems) {
                String name = item.findElement(cartItemName).getText();
                if (name.toLowerCase().contains(itemName.toLowerCase())) {
                    price = item.findElement(cartItemPrice).getText();
                    break;
                }
            }
        }
        return price;
    }

    @Step("Get description of cart item: {itemName}")
    public String getCartItemDescriptionByName(String itemName){
        String description = null;
        if(itemName.length() >= 4){
            List<WebElement> cartItems = driver.findElements(cartList);
            for (WebElement item : cartItems) {
                String name = item.findElement(cartItemName).getText();
                if (name.toLowerCase().contains(itemName.toLowerCase())) {
                    description = item.findElement(cartItemDescription).getText();
                    break;
                }
            }
        }
        return description;
    }

    @Step("Navigate to {item_Name} Product's Page from Cart page")
    public void navigateToProductPageFromCart(String item_Name) {
        if(item_Name.length() >= 4){
            List<WebElement> cartItemsNames = driver.findElements(cartItemName);
            for (WebElement item : cartItemsNames) {
                String name = item.getText();
                if (name.toLowerCase().contains(item_Name.toLowerCase())) {
                    item.click();
                    break;
                }
            }
        }
    }

    @Step("Remove cart item: {itemName}")
    public void removeCartItemByName(String itemName) {
        if (itemName.length() >= 4) {
            List<WebElement> cartItemsNames = driver.findElements(cartItemName);
            List<WebElement> itemRemoveButtons = driver.findElements(cartItemRemove_btn);
            for (int i = 0; i < cartItemsNames.size(); i++) {
                String name = cartItemsNames.get(i).getText();
                if (name.toLowerCase().contains(itemName.toLowerCase())) {
                    itemRemoveButtons.get(i).click();
                    break;
                }
            }
        }
    }

    @Step("Remove all items from cart")
    public void removeAllItemsFromCart() {
        List<WebElement> cartItemsRemove_btns = driver.findElements(cartItemRemove_btn);
        while (!cartItemsRemove_btns.isEmpty()) {
            cartItemsRemove_btns.get(0).findElement(cartItemRemove_btn).click();
            // re-fetch items list after DOM update
            cartItemsRemove_btns = driver.findElements(cartItemRemove_btn);
        }
    }

    @Step("Click on Continue Shopping button")
    public void clickContinueShopping() {
        super.moveToElementAndClick(continueShopping_btn);
    }

    @Step("Click on Checkout button")
    public void clickCheckoutButton() {
        super.moveToElementAndClick(checkout_btn);
    }
}
