package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;
import utils.JsonReader;
import utils.ScreenShotUtils;

import io.cucumber.java.Scenario;
import utils.configReader;

public class Hooks {
    public static WebDriver driver;
    public static SoftAssert softAssert;

    public static String sauceDemoUrl = configReader.getProperty("base.url");
    public static String standardUsername = JsonReader.getAttributeName("user_data", "standard_user");
    public static String password = JsonReader.getAttributeName("user_data", "password");
    public static String checkoutFirstName = JsonReader.getAttributeName("Checkout", "first_name");
    public static String checkoutLastName = JsonReader.getAttributeName("Checkout", "last_name");
    public static String checkoutPostalCode = JsonReader.getAttributeName("Checkout", "postal_code");
    public static String product1 = JsonReader.getAttributeName("Products", "product_1");
    public static String product2 = JsonReader.getAttributeName("Products", "product_2");
    public static String product3 = JsonReader.getAttributeName("Products", "product_3");
    public static String product4 = JsonReader.getAttributeName("Products", "product_4");
    public static String product5 = JsonReader.getAttributeName("Products", "product_5");
    public static String product6 = JsonReader.getAttributeName("Products", "product_6");

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(sauceDemoUrl);
        softAssert = new SoftAssert();
    }

    @After
    public void tearDown(Scenario scenario) {
        switch (scenario.getStatus()) {
            case FAILED -> {
                ScreenShotUtils.takeScreenshot(driver, scenario.getName() + "_ Failure Screenshot");
                ScreenShotUtils.AttachScreenshotToAllureReport(scenario.getName() + "_ Failure Screenshot");
            }
            case PASSED -> {
                ScreenShotUtils.takeScreenshot(driver, scenario.getName() + "_ Success Screenshot");
                ScreenShotUtils.AttachScreenshotToAllureReport(scenario.getName() + "_ Success Screenshot");
            }
            case SKIPPED -> {
                ScreenShotUtils.takeScreenshot(driver, scenario.getName() + "_ Skipped Screenshot");
                ScreenShotUtils.AttachScreenshotToAllureReport(scenario.getName() + "_ Skipped Screenshot");
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
