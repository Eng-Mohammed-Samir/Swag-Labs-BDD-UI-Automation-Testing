package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckOutOverviewPage extends BasePage {
    // Constructor
    public CheckOutOverviewPage(WebDriver driver) {
        super(driver);
    }

    // Page locators
    private final By checkoutOverview_label = By.className("title");
    private final By cartList = By.className("cart_item");
    private final By cartItemName = By.className("inventory_item_name");
    private final By cartItemPrice = By.className("inventory_item_price");
    private final By cartItemDescription = By.className("inventory_item_desc");
    private final By paymentInformtion_txt = By.xpath("//div[@class = 'summary_value_label'][1]");
    private final By shippingInformtion_txt = By.xpath("//div[@class = 'summary_value_label'][2]");
    private final By tax_label = By.className("summary_tax_label");
    private final By itemsTotal_label = By.className("summary_subtotal_label");
    private final By total_label = By.className("summary_total_label");
    private final By cancel_btn = By.cssSelector("button.cart_cancel_link.btn_secondary");
    private final By finish_btn = By.cssSelector("button.btn_action.cart_button");

    // Page methods
    @Step("Check if we are in Checkout Overview Page")
    public boolean areWeInCheckOutOverViewPage(){
        return super.waitForPageToLoad(checkoutOverview_label, 5);
    }

    @Step("Check if item '{item_Name}' exists in Checkout Overview")
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

    @Step("Navigate to {item_Name} Product's Page from Checkout Overview page")
    public void navigateToProductPageFromOverview(String item_Name) {
        if(item_Name.length() >= 4){
            List<WebElement> cartItems = driver.findElements(cartList);
            for (WebElement item : cartItems) {
                String name = item.findElement(cartItemName).getText();
                if (name.toLowerCase().contains(item_Name.toLowerCase())) {
                    item.findElement(cartItemName).click();
                    break;
                }
            }
        }
    }

    @Step("Get price of Checkout Overview item: {itemName}")
    public String getCheckOutOverviewItemPriceByName(String itemName) {
        String price = null;
        if (itemName.length() >= 4) {
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

    @Step("Get description of Checkout Overview item: {itemName}")
    public String getCheckOutOverviewItemDescriptionByName(String itemName) {
        String description = null;
        if (itemName.length() >= 4) {
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

    @Step("Click Cancel button")
    public void clickCancelButton() {
        super.moveToElementAndClick(cancel_btn);
    }

    @Step("Click Finish button")
    public void clickFinishButton() {
        super.moveToElementAndClick(finish_btn);
    }

    @Step("Get Payment Information text")
    public String getPaymentInformation(){
        return driver.findElement(paymentInformtion_txt).getText();
    }

    @Step("Check if Payment Information is displayed")
    public boolean isPaymentInformationDisplayed() {
        return driver.findElement(paymentInformtion_txt).isDisplayed();
    }

    @Step("Get Shipping Information text")
    public String getShippingInformation() {
        return driver.findElement(shippingInformtion_txt).getText();
    }

    @Step("Check if Shipping Information is displayed")
    public boolean isShippingInformationDisplayed() {
        return driver.findElement(shippingInformtion_txt).isDisplayed();
    }

    @Step("Get Items Total amount")
    public float getItemsTotalAmount() {
        String itemsTotalValue = driver.findElement(itemsTotal_label).getText().split("\\$")[1];
        return Float.parseFloat(itemsTotalValue);
    }

    @Step("Get Tax amount")
    public float getTaxAmount() {
        String taxValue = driver.findElement(tax_label).getText().split("\\$")[1];
        return Float.parseFloat(taxValue);
    }

    @Step("Get Total amount")
    public float getTotalAmount() {
        String totalValue = driver.findElement(total_label).getText().split("\\$")[1];
        return Float.parseFloat(totalValue);
    }
}
