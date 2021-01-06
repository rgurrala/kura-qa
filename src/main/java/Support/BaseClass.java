package Support;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.OutputType.BYTES;

public class BaseClass {
    WebModel webModel = new WebModel();

    public static WebDriver driver;

    @Before()
    public void startUp() throws IOException {
        driver = webModel.getUtils().browser();
        driver.get(webModel.getUtils().getProperty("env"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }


    @After
    public void tearDown(Scenario scenario) throws IOException {


        if (scenario.isFailed()) {
            TakesScreenshot camera = (TakesScreenshot) driver;
            File location = camera.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(location, new File("./screenShots/" + scenario.getName() + ".png"));
            byte[] screenShot = camera.getScreenshotAs(BYTES);
            scenario.embed(screenShot, "image/png");
            System.out.println("scenario failed");
        }

//
//        LogEntries logForParentApp = mobileParent.manage().logs().get("server");
//        for (LogEntry one : logForParentApp) {
//            try {
//                System.out.println(one);
//            } catch (Exception e) {
//
//            }
//
//        }
//        LogEntries logForDriverApp = mobileParent.manage().logs().get("server");
//        for (LogEntry one : logForDriverApp) {
//            try {
//                System.out.println(one);
//            } catch (Exception e) {
//            }

        // }
        driver.quit();
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("taskkill /F /IM node.exe");
            runtime.exec("taskkill /f /im cmd.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
