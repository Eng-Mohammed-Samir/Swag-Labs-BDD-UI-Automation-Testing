package context;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.JsonReader;

import java.util.List;

public class ScenarioContext {
    private WebDriver driver;
    private SoftAssert softAssert;

    // Page Objects - Lazy initialization
    private BasePage basePage;
    private CartPage cartPage;
    private CheckOutCompletePage checkOutCompletePage;
    private CheckOutInfoPage checkOutInfoPage;
    private CheckOutOverviewPage checkOutOverviewPage;
    private InventoryPage inventoryPage;
    private LoginPage loginPage;
    private ProductDetailsPage productDetailsPage;

    private String standardUsername;
    private String password;
    private String checkoutFirstName;
    private String checkoutLastName;
    private String checkoutPostalCode;
    private List<String> products;


    // ============== WebDriver Management ==============
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    // ============== SoftAssert Management ==============
    public SoftAssert getSoftAssert() {
        if (softAssert == null) {
            softAssert = new SoftAssert();
        }
        return softAssert;
    }

    // ============== Page Objects - Lazy Initialization ==============
    public BasePage getBasePage() {
        if (basePage == null) {
            basePage = new BasePage(driver);
        }
        return basePage;
    }

    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage(driver);
        }
        return cartPage;
    }

    public CheckOutCompletePage getCheckOutCompletePage() {
        if (checkOutCompletePage == null) {
            checkOutCompletePage = new CheckOutCompletePage(driver);
        }
        return checkOutCompletePage;
    }

    public CheckOutInfoPage getCheckOutInfoPage() {
        if (checkOutInfoPage == null) {
            checkOutInfoPage = new CheckOutInfoPage(driver);
        }
        return checkOutInfoPage;
    }

    public CheckOutOverviewPage getCheckOutOverviewPage() {
        if (checkOutOverviewPage == null) {
            checkOutOverviewPage = new CheckOutOverviewPage(driver);
        }
        return checkOutOverviewPage;
    }

    public InventoryPage getInventoryPage() {
        if (inventoryPage == null) {
            inventoryPage = new InventoryPage(driver);
        }
        return inventoryPage;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public ProductDetailsPage getProductDetailsPage() {
        if (productDetailsPage == null) {
            productDetailsPage = new ProductDetailsPage(driver);
        }
        return productDetailsPage;
    }

    // ============== Test Data - Lazy Initialization ==============
    public String getStandardUsername() {
        if (standardUsername == null) {
            standardUsername = JsonReader.getAttributeName("user_data", "standard_user");
        }
        return standardUsername;
    }

    public String getPassword() {
        if (password == null) {
            password = JsonReader.getAttributeName("user_data", "password");
        }
        return password;
    }

    public String getCheckoutFirstName() {
        if (checkoutFirstName == null) {
            checkoutFirstName = JsonReader.getAttributeName("Checkout", "first_name");
        }
        return checkoutFirstName;
    }

    public String getCheckoutLastName() {
        if (checkoutLastName == null) {
            checkoutLastName = JsonReader.getAttributeName("Checkout", "last_name");
        }
        return checkoutLastName;
    }

    public String getCheckoutPostalCode() {
        if (checkoutPostalCode == null) {
            checkoutPostalCode = JsonReader.getAttributeName("Checkout", "postal_code");
        }
        return checkoutPostalCode;
    }

    public List<String> getProductsNames(){
        if (products == null) {
            products = List.of(
                JsonReader.getAttributeName("Products", "product_1"),
                JsonReader.getAttributeName("Products", "product_2"),
                JsonReader.getAttributeName("Products", "product_3"),
                JsonReader.getAttributeName("Products", "product_4"),
                JsonReader.getAttributeName("Products", "product_5"),
                JsonReader.getAttributeName("Products", "product_6")
            );
        }
        return products;
    }
}
