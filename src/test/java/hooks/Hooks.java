package hooks;

import context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.JsonReader;
import utils.ScreenShotUtils;

import io.cucumber.java.Scenario;
import utils.configReader;

public class Hooks {
    private WebDriver driver;
    private ScenarioContext scenarioContext;

    // Constructor injection by PicoContainer
    public Hooks(ScenarioContext context) {
        this.scenarioContext = context;
    }

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(configReader.getProperty("base.url"));
        scenarioContext.setDriver(driver);
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
