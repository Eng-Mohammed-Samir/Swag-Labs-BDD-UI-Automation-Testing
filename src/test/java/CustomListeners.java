import org.testng.*;
import utils.FileUtils;


import java.io.File;
import java.io.IOException;

public class CustomListeners implements IExecutionListener {
    File allureResultsDir = new File("test-outputs/allure-results");
    File screenShotsDir = new File("test-outputs/Screenshots");


    @Override
    public void onExecutionStart() {
        try {
            FileUtils.deleteFilesInDirectory(allureResultsDir);
            FileUtils.deleteFilesInDirectory(screenShotsDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
