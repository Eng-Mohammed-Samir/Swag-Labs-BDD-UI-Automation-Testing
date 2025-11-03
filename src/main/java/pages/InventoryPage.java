package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InventoryPage extends BasePage{
    // Constructor
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    // Page locators
    private final By products_label = By.className("title");
    private final By inventoryList = By.className("inventory_item");
    private final By itemName = By.className("inventory_item_name");
    private final By itemPrice = By.className("inventory_item_price");
    private final By itemAddToCart_btn = By.xpath("//button[@class = 'btn btn_primary btn_small btn_inventory ']");
    private final By itemRemoveFromCart_btn = By.xpath("//button[@class = 'btn btn_secondary btn_small btn_inventory ']");
    private final By itemDescription = By.className("inventory_item_desc");
    private final By cartProductsCounter = By.cssSelector(".shopping_cart_container .shopping_cart_badge");
    private final By filters_dropdown = By.className("product_sort_container");

    private Select filters;

    // Page methods
    @Step("Check if we are in Inventory Page")
    public boolean areWeInInventoryPage(){
        return super.waitForPageToLoad(products_label, 5);
    }

    @Step("Check if item '{item_Name}' exists in inventory")
    public boolean isItemExist(String item_Name){
        boolean found = false;
        if(item_Name.length() >= 4){
            List<WebElement> items = driver.findElements(inventoryList);
            for (WebElement item : items) {
                String name = item.findElement(itemName).getText();
                if (name.toLowerCase().contains(item_Name.toLowerCase())) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    @Step("Get description of item '{item_Name}'")
    public String getItemDescription(String item_Name){
        String description = null;
        if(item_Name.length() >= 4){
            List<WebElement> items = driver.findElements(inventoryList);
            for (WebElement item : items) {
                String name = item.findElement(itemName).getText();
                if (name.toLowerCase().contains(item_Name.toLowerCase())) {
                    description = item.findElement(itemDescription).getText();
                    break;
                }
            }
        }
        else{
            description = "Item not found";
        }
        return description;
    }

    @Step("Get price of item '{item_Name}'")
    public String getItemPrice(String item_Name){
        String price = null;
        if(item_Name.length() >= 4){
            List<WebElement> items = driver.findElements(inventoryList);
            for (WebElement item : items) {
                String name = item.findElement(itemName).getText();
                if (name.toLowerCase().contains(item_Name.toLowerCase())) {
                    price = item.findElement(itemPrice).getText();
                    break;
                }
            }
        }
        else{
            price = "Item not found";
        }
        return price;
    }

    @Step("Add item '{item_Name}' to cart")
    public void addItemToCart(String item_Name){
        if(item_Name.length() >= 4){
            List<WebElement> items = driver.findElements(inventoryList);
            for (WebElement item : items) {
                String name = item.findElement(itemName).getText();
                if (name.toLowerCase().contains(item_Name.toLowerCase())
                            && item.findElement(itemAddToCart_btn).isDisplayed()) {
                    super.moveToElementAndClick(itemAddToCart_btn);
                    break;
                }
            }
        }
    }

    @Step("Remove item '{item_Name}' from cart")
    public void removeItemFromCart(String item_Name){
        if(item_Name.length() >= 4){
            List<WebElement> items = driver.findElements(inventoryList);
            for (WebElement item : items) {
                String name = item.findElement(itemName).getText();
                if (name.toLowerCase().contains(item_Name.toLowerCase())
                            && item.findElement(itemRemoveFromCart_btn).isDisplayed()) {
                    super.moveToElementAndClick(itemRemoveFromCart_btn);
                    break;
                }
            }
        }
    }

    @Step("Navigate to {item_Name} Item Page from Inventory page")
    public void navigateToItemPage(String item_Name){
        if(item_Name.length() >= 4){
            List<WebElement> items = driver.findElements(inventoryList);
            for (WebElement item : items) {
                String name = item.findElement(itemName).getText();
                if (name.toLowerCase().contains(item_Name.toLowerCase())) {
                    item.findElement(itemName).click();
                    break;
                }
            }
        }
    }

    @Step("Filter items by Name from A to Z")
    public void filterByNameFromAToZ(){
        filters = new Select(driver.findElement(filters_dropdown));
        filters.selectByValue("az");
    }

    @Step("Filter items by Name from Z to A")
    public void filterByNameFromZToA() {
        filters = new Select(driver.findElement(filters_dropdown));
        filters.selectByValue("za");
    }

    @Step("Filter items by Price from Low to High")
    public void filterByPriceFromLowToHigh() {
        filters = new Select(driver.findElement(filters_dropdown));
        filters.selectByValue("lohi");
    }

    @Step("Filter items by Price from High to Low")
    public void filterByPriceFromHighToLow() {
        filters = new Select(driver.findElement(filters_dropdown));
        filters.selectByValue("hilo");
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
}
