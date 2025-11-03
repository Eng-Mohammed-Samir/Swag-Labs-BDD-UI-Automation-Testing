package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ScreenShotUtils {
    public static final String ScreenShot_Path = "test-outputs/Screenshots/";

    public static void takeScreenshot(WebDriver driver, String screenShotName) {
        try {
            File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenShotFile = new File(ScreenShot_Path + screenShotName + ".png");
            FileUtils.copyFile(screenShot, screenShotFile);
            //AllureUtils.attachFileToAllureReport(screenShotName, screenShotFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void AttachScreenshotToAllureReport(String screenShotName) {
        try {
            AllureUtils.attachFileToAllureReport(screenShotName, ScreenShot_Path + screenShotName + ".png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

