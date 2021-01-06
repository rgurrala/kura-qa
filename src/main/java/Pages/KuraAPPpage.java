package Pages;

import Support.ElementUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;


import static Pages.OrganisationPage.*;
import static io.appium.java_client.touch.offset.PointOption.point;


public class KuraAPPpage {
    public static AppiumDriver mobileDriver;
    public static AppiumDriver mobileParent;
    public static String otp;
    ElementUtils utils = new ElementUtils();



    public void launchDriverApp() throws MalformedURLException {
        //starting appium driver
        utils.accessCMD("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
        utils.sleep(5000);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Driver");
        capabilities.setCapability("platformVersion", "9.0.0");
        capabilities.setCapability("platformName", "ANDROID");
        capabilities.setCapability("udid", "52002dd24392b5a1");
        //capabilities.setCapability("automationName", "UiAutomator1");
        capabilities.setCapability("noReset", "true");
        capabilities.setCapability("appPackage", "com.coachhire.kura");
        capabilities.setCapability("appActivity", "com.coachhire.kura.MainActivity");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability("adbExecTimeout", 120000);
        capabilities.setCapability("newCommandTimeout", 3000);
        capabilities.setCapability("noSign", "true");
        URL mobileURL = new URL("http://127.0.0.1:4723/wd/hub");
        try {
            mobileDriver = new AndroidDriver(mobileURL, capabilities);
        } catch (Exception e) {
            mobileDriver = new AndroidDriver(mobileURL, capabilities);
        }


    }

//browserStack
//
//       capabilities.setCapability("browserstack.appium_version","1.14.0");
//       capabilities.setCapability("deviceName","Samsung Galaxy S9 Plus");
//        capabilities.setCapability("deviceOrientation","portrait");
//       capabilities.setCapability("platformVersion","9.0");
//        capabilities.setCapability("platformName","ANDROID");
//        capabilities.setCapability("app_url","bs://781eefae9a01fe780bf37b458d7b2b4d08fbed4a");
//        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,true);
//        mobileDriver= new AppiumDriver(newURL("https://tharadorairaj1:QQx8zdRvFFgrij4PkuJ5@hub-cloud.browserstack.com/wd/hub"), capabilities);
//        loginToDriverApp("uatkuratesting+driver@gmail.com","Kura123!");


    @Test
    public void launchParentApp() throws MalformedURLException {
        //starting appium parent
        utils.accessCMD("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 5050\"");
        utils.sleep(5000);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Parent");
        capabilities.setCapability("platformVersion", "9.0.0");
        capabilities.setCapability("platformName", "ANDROID");
        capabilities.setCapability("udid", "5200472dec01a4a9");
        //capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("noReset", "true");
        capabilities.setCapability("appPackage", "com.coachhire.kura");
        capabilities.setCapability("appActivity", "com.coachhire.kura.MainActivity");
        capabilities.setCapability("adbExecTimeout", 120000);
        capabilities.setCapability("newCommandTimeout", 3000);
        capabilities.setCapability("noSign", "true");
        URL mobileURL = new URL("http://127.0.0.1:5050/wd/hub");
        try {
            mobileParent = new AndroidDriver(mobileURL, capabilities);
        } catch (Exception e) {
            mobileParent = new AndroidDriver(mobileURL, capabilities);
        }

    }

    public void loginToGoogleAppOnTab(String udid, boolean parentApp) throws MalformedURLException {
        utils.accessCMD("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
        utils.sleep(5000);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Driver");
        capabilities.setCapability("platformVersion", "9.0.0");
        capabilities.setCapability("platformName", "ANDROID");
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("noReset", "true");
        capabilities.setCapability("appPackage", "com.google.android.gm");
        capabilities.setCapability("appActivity", "ConversationListActivityGmail");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability("adbExecTimeout", 120000);
        capabilities.setCapability("newCommandTimeout", 3000);
        capabilities.setCapability("noSign", "true");
        URL mobileURL = new URL("http://127.0.0.1:4723/wd/hub");
        if (parentApp) {
            try {
                mobileParent = new AndroidDriver(mobileURL, capabilities);
            } catch (Exception e) {
                mobileParent = new AndroidDriver(mobileURL, capabilities);
            }
        } else {
            try {
                mobileDriver = new AndroidDriver(mobileURL, capabilities);
            } catch (Exception e) {
                mobileDriver = new AndroidDriver(mobileURL, capabilities);
            }
        }
    }


//boot strap 4734

//        local emulator
//        capabilities.setCapability("deviceName", "emulator-5554");
//        capabilities.setCapability("platformVersion", "8.0.0");
//        capabilities.setCapability("platformName", "ANDROID");
//        capabilities.setCapability("udid", "emulator-5554");
//        capabilities.setCapability("noReset", "true");
//        capabilities.setCapability("fullReset", "false");
//        capabilities.setCapability("appPackage", "com.coachhire.kura");
//        capabilities.setCapability("appActivity", "host.exp.exponent.LauncherActivity");
//        capabilities.setCapability("appiumVersion","1.16.0");
//        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
//     capabilities.setCapability("newCommandTimeout", 3000);
//        URL mobileURL = new URL("http://0.0.0.0:4723/wd/hub");
//        mobileDriver = new AndroidDriver(mobileURL, capabilities);

//
//        capabilities.setCapability("browserstack.appium_version", "1.16.0");
//        capabilities.setCapability("deviceName", "Samsung Galaxy S10 Plus");
//        capabilities.setCapability("deviceOrientation", "portrait");
//        capabilities.setCapability("platformVersion", "9.0");
//        capabilities.setCapability("platformName", "ANDROID");
//        capabilities.setCapability("app_url", "bs://781eefae9a01fe780bf37b458d7b2b4d08fbed4a");
//        capabilities.setCapability("browserstack.resignApp", "false");
//        capabilities.setCapability("newCommandTimeout", 3000);
//        mobileParent = new AppiumDriver(newURL("https://tharadorairaj1:QQx8zdRvFFgrij4PkuJ5@hub-cloud.browserstack.com/wd/hub"), capabilities);


    public void loginToDriverApp(String userName, String password) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 30);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Driver Schedule\")")));
            utils.sleep(3000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Driver Schedule']/following-sibling::android.view.ViewGroup"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Logout\")"))).click();
        } catch (Exception e) {
            System.out.println("app is already logged out");
        }
        mobileDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Enter your email\")")).sendKeys(userName);
        mobileDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Enter your password\")")).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"SIGN IN\")"))).click();

    }

    public void logOutOfParentApp() {
        WebDriverWait wait = new WebDriverWait(mobileParent, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Schedule\")")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Schedule']/following-sibling::android.view.ViewGroup"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Logout\")"))).click();
    }

    public void loginToParentApp(String userName, String password) {
        WebDriverWait wait = new WebDriverWait(mobileParent, 50);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Schedule\")")));
            utils.sleep(3000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Schedule']/following-sibling::android.view.ViewGroup"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Logout\")"))).click();
        } catch (Exception e) {
            System.out.println("app is already logged out");
        }
        mobileParent.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Enter your email\")")).sendKeys(userName);
        mobileParent.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Enter your password\")")).sendKeys(password);
        WebElement all = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"SIGN IN\")")));
        all.click();
    }

    public void assertLinkPage() {
        WebDriverWait wait = new WebDriverWait(mobileParent, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Schedule\")")));
    }

    public void assertMobileDashBoardPageForDriver() {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Driver Schedule']")));
    }

    public void assertMobileDashBoardPageForParent() {
        WebDriverWait wait = new WebDriverWait(mobileParent, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Schedule\")")));

    }

    public void navigateToTripOnDriverApp(String trp) throws Exception {
        updateDriverApp();
        WebDriverWait wait = new WebDriverWait(mobileDriver, 40);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + trp + "\")"))).click();
        } catch (Exception e) {
            utils.scrollOnDriverApp(trp);
            utils.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + trp + "\")"))).click();
        }

    }

    public void assertAndChooseVehiclesFromAvailableList(String reg1, String reg2) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + reg1 + "\")")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + reg2 + "\")")));
        WebElement vehicle = mobileDriver.findElement((MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + reg1 + "\")")));
        vehicle.click();
        WebElement confirmButton = mobileDriver.findElement((MobileBy.AndroidUIAutomator("new UiSelector().text(\"CONFIRM\")")));
        confirmButton.click();
    }

    public void skipStops(String stop1, String stop2, boolean donotskip) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + stop1 + "\")")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + stop2 + "\")")));
        if (donotskip) {
            WebElement dontSkipStops = mobileDriver.findElement((MobileBy.AndroidUIAutomator("new UiSelector().text(\"DON'T SKIP ANY STOPS\")")));
            dontSkipStops.click();
        } else {
            WebElement skipMaidenHeadRoad = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"old home\")")));
            skipMaidenHeadRoad.click();
            WebElement reason = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"No Boarding Required\")")));
            reason.click();
            WebElement skip1Stop = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"SKIP 1 STOP\")")));
            skip1Stop.click();
        }

    }

    public void clickIamOnSite( String jumpedTo) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Advance to stop: " + jumpedTo + "\")")));
        WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"SUBMIT\")")));
         submit.click();

    }

    public void assertStopIsSkipped(String currentStop) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 20);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + currentStop + "\")")));
    }

    public void sendMessage(String messageType) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 20);
        WebElement messages = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Messages\")")));
        messages.click();
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated((MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + messageType + "\")"))));
        try {
            message.click();
        } catch (Exception e) {
            messages.click();
            message.click();
        }

    }

    public void waitForPassengerScreen(String stop) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + stop + "\")")));

    }

    public void navigateToJourneyTab() {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 20);
        WebElement journey = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Journey\")")));
        journey.click();
    }

    public void endCurrentJourney() {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 20);
        navigateToJourneyTab();
        try {
            mobileDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"END JOURNEY\")")).click();
        } catch (Exception e) {
            navigateToJourneyTab();
            mobileDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"END JOURNEY\")")).click();
        }
        WebElement confirmButton = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"CONFIRM\")")));
        confirmButton.click();
        utils.sleep(3000);
        mobileDriver.quit();


    }

    public void boardPassengers(String pickUpPassenger, boolean passengerSearch, boolean unexpectedBoarding) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 60);
        if (passengerSearch) {
            WebElement sendText = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Filter passengers\")")));
            sendText.sendKeys("passengerS");
        }
        WebElement boardPassenger = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + pickUpPassenger + "\")")));
        boardPassenger.click();
        if (unexpectedBoarding) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"aad is not expected on this trip\")")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"BOARD ANYWAY\")"))).click();


        }
        navigatingFromPassengerPageToJourneyPage();
    }

    public void navigatingFromPassengerPageToJourneyPage() {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 50);
        WebElement clickBackViaJourneyButton = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Journey\")")));
        clickBackViaJourneyButton.click();
    }

    public void navigateToPassengersPageOnApp() {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Passengers\")"))).click();

    }

    public void unBoardPassenger(String currentStop, String unboardPassengerName) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + unboardPassengerName + "\")")));
        WebElement unboardPassenger = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + unboardPassengerName + "\")")));
        unboardPassenger.click();
    }

    public void advanceToStop() {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Advance to stop:\")")));
        // wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Why did you skip the previous stop(s): old home, rc club bunker\")")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"SUBMIT\")"))).click();
    }

    public void endJourney(String leftPassengers) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 20);
        WebElement endJourney = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"END JOURNEY\")")));
        endJourney.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + leftPassengers + "\")")));
        WebElement confirm = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"CONFIRM\")")));
        confirm.click();

    }

    public void removePassengerOnObserverApp() {
        WebDriverWait wait = new WebDriverWait(mobileParent, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"REMOVE\")"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"CONFIRM\")"))).click();
    }

    public void assertNotifications() {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"call coachhire\")")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"OK\")"))).click();

    }

    public void searchPassengers(String passenger) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"aad\")")));
        WebElement sendText = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Filter passengers\")")));
        sendText.sendKeys(passenger);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"aad\")")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"passengertwo\")")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"passengerthree\")")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"passengerfour\")")));
        mobileDriver.quit();
    }

    public void passengersOnDriverApp(String avaiPas1, String avaiPas2, String availPas3, String unAvailPas) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 50);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + unAvailPas + "\")")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + avaiPas1 + "\")")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + avaiPas2 + "\")")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + availPas3 + "\")")));
        mobileDriver.quit();
    }


    public void notificationMessageOnParentAppParentApp(String message, boolean onRoute) {
        WebDriverWait wait = new WebDriverWait(mobileParent, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + message + "\")")));
        if (onRoute) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"on route\")")));
            Assert.assertFalse(utils.checkIfElementIsDisplayed_ParentApp(new MobileBy.ByAndroidUIAutomator("new UiSelector().textContains(\"" + tripName + "\")")));
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"OK\")"))).click();
    }

    public void assertParentCannotDeleteTrip() {
        Assert.assertTrue(mobileParent.findElements(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Remove\")")).size() == 0);
    }


    public void asObserver_ClickTheTripYouWouldWantThePassengerToBeAddedOnTo(String trip) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(mobileParent, 40);
        TouchAction plusButton = new TouchAction(mobileParent);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Schedule\")")));
        utils.sleep(2000);
        plusButton.tap(point(739, 1218)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).perform();
        utils.sleep(2000);
        plusButton.tap(point(746, 1128)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).perform();
        utils.sleep(2000);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + trip + "\")"))).click();
        } catch (Exception e) {
            utils.scrollOnParentApp(trip);
            utils.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + trip + "\")"))).click();
        }
    }

    public void assertObserverCanORCannotBookTrip(String trip, boolean value) {
        WebDriverWait wait = new WebDriverWait(mobileParent, 40);
        TouchAction plusButton = new TouchAction(mobileParent);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Schedule\")")));
        utils.sleep(2000);
        plusButton.tap(point(739, 1218)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).perform();
        utils.sleep(2000);
        plusButton.tap(point(746, 1128)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).perform();
        utils.sleep(2000);
        try {
            scrollToTrip_ParentApp();
        } catch (Exception e) {
        }
        Assert.assertEquals(utils.isElementPresentOnParentApp(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + trip + "\")")), value);


    }

    public void observerBookingTrip_TwoRoutePoints(int firstStopX, int firstStopy, int secondStopX, int secondStopY, boolean multipleDays, boolean tandc_firstTime, String text) throws IOException {
        WebDriverWait wait = new WebDriverWait(mobileParent, 40);
        TouchAction plusButton = new TouchAction(mobileParent);
        utils.sleep(5000);
        plusButton.tap(point(firstStopX, firstStopy)).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"BOARD HERE\")"))).click();
        utils.sleep(5000);
        plusButton.tap(point(secondStopX, secondStopY)).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"DISEMBARK HERE\")"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"CONFIRM\")"))).click();
        if (multipleDays) {
            String firstDay = utils.getDayOfWeekBasedOnDaysAdded(1, "dd").toUpperCase();
            int first = Integer.parseInt(firstDay);
            try {
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + first + "\")"))).get(3).click();
            } catch (Exception e) {
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + first + "\")"))).get(1).click();
            }
            String lastDay = utils.getDayOfWeekBasedOnDaysAdded(2, "dd").toUpperCase();
            int last = Integer.parseInt(lastDay);
            try {
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + last + "\")"))).get(5).click();
            } catch (Exception e) {
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + last + "\")"))).get(2).click();
            }
            String firstDayOfWeek = utils.getDayOfWeekBasedOnDaysAdded(1, "EEE").toUpperCase();
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + firstDayOfWeek + "\")"))).click();
            String lastDayOfWeek = utils.getDayOfWeekBasedOnDaysAdded(2, "EEE").toUpperCase();
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + lastDayOfWeek + "\")"))).click();

        } else {
            String day = utils.getDayOfWeekBasedOnDaysAdded(0, "EEE").toUpperCase();
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + day + "\")"))).click();
        }
        if (tandc_firstTime) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"booking T&Cs\")"))).click();
            utils.sleep(7000);
            String pdftext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@class='android.view.ViewGroup']/android.view.ViewGroup[1]"))).getAttribute("content-desc");
            Assert.assertTrue(pdftext.contains(text));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='Navigate up']"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='OFF']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"BOOK\")"))).click();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"View accepted booking T&Cs\")")));
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"BOOK\")"))).click();
        }
    }

    public void successfulAdditionOfPassengerToTrip() {
        WebDriverWait wait = new WebDriverWait(mobileParent, 40);
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"OK\")"))).click();
    }

    public void updateParentApp() {
        TouchAction plusButton = new TouchAction(mobileParent);
        plusButton.press(point(397, 310)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(point(389, 669)).release().perform();
    }
    public void updateParentAppUntilTripIsVisible() throws Exception {
        boolean value = true;
        int i = 5;
        while (value) {
            try {
               updateParentApp();
               assertParentCanSeeTrip(tripName, true);
                value = false;
            } catch (Exception e) {
                i--;
               utils.sleep(5000);
                if (i == 0) {
                    throw new Exception("trip not viewable on parent app");
                }
            }
        }
    }

    public void updateDriverApp() {
        TouchAction plusButton = new TouchAction(mobileDriver);
        plusButton.press(point(397, 310)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(point(389, 669)).release().perform();

    }

    public void assertParentCanSeeTrip(String trip, boolean view) {
        WebDriverWait wait = new WebDriverWait(mobileParent, 40);
        if (view) {
            scrollToTrip_ParentApp();
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + trip + "\")")));
        } else {
            try {
                scrollToTrip_ParentApp();
            } catch (Exception e) {
            }
            Assert.assertEquals(utils.checkIfElementIsDisplayed(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + trip + "\")")), false);

        }


    }

    public void errorMessageOnParentApp(String errorMessage) {
        WebDriverWait wait = new WebDriverWait(mobileParent, 40);
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + errorMessage + "\")")));
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"OK\")"))).click();
    }

    public void scrollToTrip_ParentApp() {
        TouchAction plusButton = new TouchAction(mobileParent);

        for (int i = 0; i <= 15; i++) {
            utils.sleep(1000);
            plusButton.press(point(378, 1203)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(point(398, 544)).release().perform();
            if (utils.checkIfElementIsDisplayed_ParentApp(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + tripName + "\")"))) {
                break;
            }
        }
    }

    public void throwExceptionIfTripIsNotSeenOnParentApp() throws Exception {
        if (!utils.checkIfElementIsDisplayed_ParentApp(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + tripName + "\")"))) {
            throw new Exception();
        }
    }

    public void throwExcepionIfTripIsSeenOnParentApp() throws Exception {
        if (utils.checkIfElementIsDisplayed_ParentApp(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + tripName + "\")"))) {
            throw new Exception();
        }
    }

    public void chooseDayOnParentApp(int addDaysInRelationToCurrentDay) {
        WebDriverWait wait = new WebDriverWait(mobileParent, 40);
        try {
            String Day = utils.getDayOfWeekBasedOnDaysAdded(addDaysInRelationToCurrentDay, "dd").toUpperCase();
            List<WebElement> date1 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + Day + "\")")));
            if (date1.size() <= 1) {
                date1.get(0).click();
                return;
            }
            date1.get(1).click();

        } catch (Exception e) {
            String Day = utils.getDayOfWeekBasedOnDaysAdded(addDaysInRelationToCurrentDay, "d").toUpperCase();
            List<WebElement> date1 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + Day + "\")")));
            if (date1.size() <= 1) {
                date1.get(0).click();
                return;
            }
            date1.get(1).click();

        }
    }


    public void deleteMailAndUpdateScreen(int mailIndex) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 30);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("com.google.android.gm:id/senders"))).get(mailIndex).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.gm:id/delete"))).click();
        updateGmailApp(false);
    }

    public void loginToGoolgeAndDeletePreviousKuraMails() throws MalformedURLException {
        loginToGoogleAppOnTab("52002dd24392b5a1", false);
        WebDriverWait wait = new WebDriverWait(mobileDriver, 30);

        for (int i = 0; i < 5; i++) {

            if (wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("com.google.android.gm:id/senders"))).get(i).getText().contains("RideKura")) {
                deleteMailAndUpdateScreen(i);
            }
        }
    }


    public void loginToGoogleAppAndGetOTP() {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 30);

        updateGmailApp(false);
        utils.sleep(2000);
        updateGmailApp(false);

        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Kura Sign Up verification code\")"))).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Kura Sign Up verification code\")")));
        otp = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='android.view.View']"))).get(5).getText();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.gm:id/delete"))).click();
    }


    public void confirmReceivingKuraMailAndDelete() {
        updateGmailApp(false);
        WebDriverWait wait = new WebDriverWait(mobileDriver, 30);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"RideKura\")"))).get(0).click();
        String email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.gm:id/recipient_summary"))).getText();
        Assert.assertTrue(email.contains(randomObserverNameWithOutEmail));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.gm:id/delete"))).click();

    }

    public void updateGmailApp(boolean parent) {
        if (parent) {
            utils.sleep(3000);
            TouchAction plusButton = new TouchAction(mobileParent);
            plusButton.press(point(434, 134)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(point(437, 592)).release().perform();
        } else {
            TouchAction plusButton = new TouchAction(mobileDriver);
            utils.sleep(3000);
            plusButton.press(point(434, 134)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(point(437, 592)).release().perform();
        }
    }

    public void navigateToGmailAppAndDeleteDrafts() throws MalformedURLException {
        loginToGoogleAppOnTab("5200472dec01a4a9", true);
        updateGmailApp(true);
        WebDriverWait wait = new WebDriverWait(mobileParent, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']"))).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(MobileBy.xpath("//*[@class='android.widget.LinearLayout']"))).get(19).click();
        try {
            List<WebElement> allMails = mobileParent.findElements(By.id("com.google.android.gm:id/viewified_conversation_item_view"));
            for (WebElement each : allMails) {
                utils.sleep(2000);
                each.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.gm:id/discard_drafts"))).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1"))).click();
            }
        } catch (Exception e) {
        }

    }

    public void assertMailInDraftsAndDeleteDraft(String text) {
        WebDriverWait wait = new WebDriverWait(mobileParent, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.gm:id/viewified_conversation_item_view"))).click();
//        String email=mobileParent.findElement(By.xpath("com.google.android.gm:id/recipient_summary")).getText();
//        Assert.assertTrue(email.contains(text));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.gm:id/discard_drafts"))).click();
        utils.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1"))).click();

    }

    public void assertDriverCanSeePrivateWaypointsOnJourneyScreen() {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"first waypoint\")"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"second waypoint\")"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"third waypoint\")"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"fourth waypoint\")"))).click();


    }


}















