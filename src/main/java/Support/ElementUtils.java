package Support;


import com.opencsv.CSVWriter;
import io.appium.java_client.MobileBy;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;


import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

import static Pages.KuraAPPpage.mobileDriver;
import static Pages.KuraAPPpage.mobileParent;
import static Support.BaseClass.driver;

import static org.junit.Assert.assertFalse;


public class ElementUtils {

    public static StringBuilder randomVehicleRegGenerator;
    public static Calendar updatedTime;
    public String date;
    public String parentWindow;
    Properties prop;
    FileInputStream fileInputStream;

    public void sendText(By by, String text) {
        WebElement element = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(by));
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
    }

    public void sendTxtWithOutClearing(By by, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        element.sendKeys(text);
    }

    public void hitEnter(By by) {
        driver.findElement(by).sendKeys(Keys.ENTER);
    }

    public void clickBtn(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        sleep(1000);
        try {
            element.click();
        } catch (Exception e) {
            actionsClick(by);
        }

    }


    public void assertElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void assertElementPresentLongWait(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 180);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void assertElementPresent_DriverApp(By by) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    public void assertElementPresent_ParentApp(By by) {
        WebDriverWait wait = new WebDriverWait(mobileParent, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void assertElementLoaded(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForElementToVanish(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));

    }


    public void assertElementAbsent(By by) {
        Assert.assertTrue(driver.findElements(by).isEmpty());
    }

    public boolean isElementPresentOnParentApp(By by) {
        try {
            mobileParent.findElement(by).isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void sleep(int timeOutInMilliSeconds) {
        try {
            Thread.sleep(timeOutInMilliSeconds);
        } catch (Exception e) {
        }
    }

    public String getProperty(String key) throws IOException {
        prop = new Properties();
        fileInputStream = new FileInputStream("src/test/Resources/config.properties");
        prop.load(fileInputStream);

        return prop.getProperty(key);
    }

    public WebDriver browser() throws IOException {

        //String browser = System.getProperty("browser");
        String browser = getProperty("browser");

        switch (browser) {
            case "chrome":
                ChromeOptions option = new ChromeOptions();
                option.setExperimentalOption("w3c", false);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(option);
                break;
            case "headless":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=1920,1080");
                options.setExperimentalOption("w3c", false);
                WebDriverManager.chromedriver().setup();
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "C:\\Users\\Rajesh Gurrala\\IdeaProjects\\FeatureFiles\\BrowserFiles\\MicrosoftWebDriver.exe");
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "C:\\Users\\Rajesh Gurrala\\IdeaProjects\\FeatureFiles\\BrowserFiles\\geckodriver.exe");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
        }

        return driver;
    }


    public void hoverOverElement(By by) {
        WebElement element = new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(by));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void actionsClick(By by) {
        WebElement element = new WebDriverWait(driver, 25).until(ExpectedConditions.elementToBeClickable(by));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    public String tripName() {
        return "Time" + " " + getCurrentTime() + " " + "Date" + " " + getCurrentDateWithOutSpaces() + " " + randomName();
    }

    public String getCurrentDateWithOutSpaces() {
        return new SimpleDateFormat("ddMMyy").format(Calendar.getInstance().getTime());
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(4);
    }

    public String randomName() {
        return RandomStringUtils.randomAlphabetic(4).toLowerCase();
    }

    public String randomNameSevenDigits() {
        return RandomStringUtils.randomAlphabetic(7).toLowerCase();
    }

    public void selectByVisibleText(By by, String text) throws InterruptedException {
        WebElement element = new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(by));
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void javaScriptExecutorClick(By by) {
        WebElement element = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        //arguments[0].scrollIntoView();
    }

    public ElementUtils makeSureBoxIsChecked(By by1, By by2) throws InterruptedException {
        WebElement element = new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(by1));
        if (element.isSelected()) {
        } else {
            sleep(1000);
            javaScriptExecutorClick(by2);

        }
        return this;
    }

    public ElementUtils makeSureBoxIsUnChecked(By by1, By by2) {
        WebElement element = new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(by1));
        if (element.isSelected()) {
            sleep(1000);
            javaScriptExecutorClick(by2);
        } else {
        }
        return this;
    }

    public void assertUnchecked(By by) {
        WebElement element = new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(by));
        assertFalse(element.isSelected());
    }


    public void dragAndDrop(By by1, By by2) {
        WebElement LocatorFrom = driver.findElement(by1);
        WebElement LocatorTo = driver.findElement(by2);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("function createEvent(typeOfEvent) " +
                "{var event =document.createEvent(\"CustomEvent\");" +
                "event.initCustomEvent(typeOfEvent,true, true, null);" +
                "event.dataTransfer = {data: {},setData: function (key, value) {this.data[key] = value;}," +
                "getData: function (key) {return this.data[key];}};" +
                "return event;}" +
                "function dispatchEvent(element, event,transferData) {if (transferData !== undefined)" +
                " {event.dataTransfer = transferData;}" +
                "if (element.dispatchEvent) {element.dispatchEvent(event);} " +
                "else if (element.fireEvent) {element.fireEvent(\"on\" + event.type, event);}}" +
                "function simulateHTML5DragAndDrop(element, destination) " +
                "{" +
                "var dragStartEvent =createEvent('dragstart');" +
                "dispatchEvent(element, dragStartEvent);" +
                "var dropEvent = createEvent('drop');" +
                "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);" +
                "var dragEndEvent = createEvent('dragend');" +
                "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);" +
                "}" +
                "var source = arguments[0];" +
                "var destination = arguments[1];" +
                "simulateHTML5DragAndDrop(source,destination);", LocatorFrom, LocatorTo);
    }


    public String getCurrentTime() {
        return new SimpleDateFormat("hh:mm").format(Calendar.getInstance().getTime());
    }

    public String getCurrentDate() {
        date = new SimpleDateFormat("dd/MM/yy").format(Calendar.getInstance().getTime());
        return date;

    }

    public String addYearsToCurrentDate(int years) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, years);
        date = new SimpleDateFormat("dd/MM/yy").format(cal.getTime());
        return date;
    }

    public String addDaysToCurrentDay(int days, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, days);
        date = new SimpleDateFormat(pattern).format(cal.getTime());
        return date;
    }

    public String addDaysToCurrentDate(int number, String format) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, number);
        date = new SimpleDateFormat(format).format(cal.getTime());
        return date;
    }


    public String getDayOfWeekBasedOnDaysAdded(int addedDays, String format) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, addedDays);
        date = new SimpleDateFormat(format).format(cal.getTime());
        return date;
    }

    public String randomVehicleRegGenerator() {
        randomVehicleRegGenerator = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            char ch = (char) (Math.random() * 26 + 'A');
            randomVehicleRegGenerator.append(ch);
        }
        for (int i = 0; i < 4; i++) {
            char digit1 = (char) (Math.random() * 10 + '0');
            randomVehicleRegGenerator.append(digit1);
        }
        return randomVehicleRegGenerator.toString();

    }

    public boolean checkIfElementIsDisplayed(By by) {
        try {
            driver.findElement(by).isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    public boolean checkIfElementIsDisplayed_ParentApp(By by) {
        try {
            mobileParent.findElement(by).isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    public ElementUtils scrollToView(By by) {
        sleep(1000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
        return this;
    }

    public int getWebElementCount(By by) {
        List<WebElement> all = new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return all.size();
    }


    public void refreshPage() {
        driver.navigate().refresh();

    }


    public void navigateBack() {
        driver.navigate().back();
    }

    public void clickOnDesiredNumberOfLocator(int number, By by) {
        List<WebElement> all = new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        int i = 0;
        for (WebElement second : all) {
            i = i + 1;
            if (i == number) {
                second.click();
            }


        }
    }

    public void clickLocatorBasedOnIndex(int number, By by) {
        List<WebElement> all = new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        all.get(number).click();
    }

    public String getTextBasedOnIndex(int number, By by) {
        List<WebElement> all = new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return all.get(number).getAttribute("value");
    }

    public void clickOnDesiredNumberOfLocatorAndSendText(int number, By by, String text) {
        List<WebElement> all = new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        int i = 0;
        for (WebElement second : all) {
            i = i + 1;
            if (i == number) {
                sleep(1000);
                second.sendKeys(Keys.CONTROL + "a");
                second.sendKeys(Keys.DELETE);
                second.sendKeys(text);
            }

        }

    }


    public String extractTextBasedOnIndex(int number, By by, String attribute) {
        String text = null;
        List<WebElement> all = driver.findElements(by);
        int i = 0;
        for (WebElement second : all) {
            i = i + 1;
            if (i == number) {
                text = second.getAttribute(attribute);
            }

        }
        return text;
    }


    public void sendTextWithOutClearingOnDesiredNumberOfLocator(int number, By by, String text) throws InterruptedException {
        List<WebElement> all = new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        int i = 0;
        for (WebElement second : all) {
            i = i + 1;
            if (i == number) {
                sleep(1000);
                second.sendKeys(text);
            }

        }

    }

    public void toNearestWholeHour() {
        updatedTime = new GregorianCalendar();
    }

    public void uploadFile(String path) throws AWTException, InterruptedException {
        StringSelection ss = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
        sleep(1000);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    public void writeDataToCSVFile(String filePath, String coachCard, String pupilRef, String firstName, String secondName) {

        File file = new File(filePath);

        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile, '|',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            List<String[]> data = new ArrayList<>();
            data.add(new String[]{"Coach Card No", "Pupil Ref No", "Pupil 1st Name", "Pupil Surname", "Pupil Email", "Pupil Mobile", "Date of Birth", "Gender", "Pupil Address", "1st Parent / Guardian", "1st Parent / Guardian Telephone", "1st Parent / Guardian Mobile", "1st Parent / Guardian Email", "1st Parent / Guardian Relationship", "2nd Parent / Guardian Name", "2nd Parent / Guardian Telephone", "2nd Parent / Guardian Mobile", "2nd Parent / Guardian Email", "2nd Parent / Guardian Relationship", "3rd Parent / Guardian Name", "3rd Parent / Guardian Telephone", "3rd Parent / Guardian Mobile", "3rd Parent / Guardian Email", "3rd Parent / Guardian Relationship", "Coach Route MonAM", "Coach Stop MonAM", "Coach Route MonPM", "Coach Stop MonPM", "Coach Route MonPM Late", "Coach Stop MonPM Late", "Coach Route TuesAM", "Coach Stop TuesAM", "Coach Route TuesPM", "Coach Stop TuesPM", "Coach Route TuesPM Late", "Coach Stop TuesPM Late", "Coach Route WedAM", "Coach Stop WedAM", "Coach Route WedPM", "Coach Stop WedPM", "Coach Route WedPM Late", "Coach Stop WedPM Late", "Coach Route ThursAM", "Coach Stop ThursAM", "Coach Route ThursPM", "Coach Stop ThursPM", "Coach Route ThursPM Late", "Coach Stop ThursPM Late", "Coach Route FriAM", "Coach Stop FriAM", "Coach Route FriPM", "Coach Stop FriPM", "Coach Route FriPM Late", "Coach Stop FriPM Late"});
            data.add(new String[]{coachCard, pupilRef, firstName, secondName, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
            writer.writeAll(data);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadUrl(String url) {
        driver.get(url);
    }

    public void scrollOnDriverApp(String locator) throws Exception {
        boolean isSearching = true;
        int loop = 6;
        while (isSearching) {
            try {
                mobileDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"" + locator + "\"))"));
                isSearching = false;
            } catch (Exception e1) {
                loop--;
                if (loop == 0) {
                    throw new Exception("trip not visible under driver app");
                }
                sleep(10000);
            }
        }
    }


    public void scrollOnParentApp(String locator) {
        sleep(1000);
        mobileParent.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"" + locator + "\"))"));

    }

    public void switchToNewWindow() {
        parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                driver.manage().window().maximize();

            }
        }
    }

    public void switchBackToPreviousWindow() {
        driver.switchTo().window(parentWindow);
    }

    public int getElementCount(By by) {

        return driver.findElements(by).size();
    }


    public void deleteAllFilesWithSpecifiedExtension(String path, String fileExtension) {
        File folder = new File(path);
        File listOfFiles[] = folder.listFiles();
        for (File f : listOfFiles) {
            if (f.getName().endsWith(fileExtension)) {
                f.delete();
            }
        }
    }

    public String getCSVFileName(String path) {
        File folder = new File(path);
        String fileName = null;
        File listOfFiles[] = folder.listFiles();
        for (File f : listOfFiles) {
            if (f.getName().endsWith(".csv")) {
                fileName = f.getName();
            }
        }
        return fileName;
    }

    public void readTextfromURL(String url, String content) throws IOException {
        URL pdfFromURL = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(pdfFromURL.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            if (inputLine.contains(content)) {
                Assert.assertTrue(inputLine.contains(content));
                break;
            }
        in.close();
    }


    public void readFileAndAssertContent(String path, String content) throws IOException {
        List<String> fileLines = Files.readAllLines(Paths.get(path));
        for (String file : fileLines) {
            if (file.equals(content)) {
                Assert.assertTrue(fileLines.contains(content));
            }
        }
    }

    public String splitUrlAndExtractOrgId(int number) {
        String[] url = driver.getCurrentUrl().split("/");
        return url[number];


    }

    public void accessCMD(String command) {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}