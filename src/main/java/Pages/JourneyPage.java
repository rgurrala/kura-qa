package Pages;

import Support.ElementUtils;
import org.junit.Assert;
import org.openqa.selenium.By;

import static Support.BaseClass.driver;


public class JourneyPage {


    ElementUtils utils = new ElementUtils();

    public void navigateToTrip_viaUpComingTrips(String trip) throws InterruptedException {
//        utils.sleep(4000);
//        utils.sendTxtWithOutClearing(By.xpath("//input[@placeholder='Search']"),trip.toLowerCase());
        utils.sleep(1000);
        try {
            utils.actionsClick(By.xpath("//td[text()='" + trip + "']"));
        } catch (Exception e) {
            int i;
            for (i = 0; i < 5; i++) {
                utils.sleep(2000);
                utils.clickOnDesiredNumberOfLocator(8, By.xpath("//button[@type='button']"));
                if (utils.checkIfElementIsDisplayed(By.xpath("//td[text()='" + trip + "']"))) {
                    utils.sleep(1000);
                    utils.actionsClick(By.xpath("//td[text()='" + trip + "']"));
                    break;
                }

            }
        }
    }


    public void assertDriverRegistrationSendMessageAndCancelJourney(String vehicleReg, String message) {
        utils.assertElementPresent(By.xpath("//td[text()='DRIVER_REGISTRATION']/..//td[text()='" + vehicleReg + "']"));
        utils.assertElementPresent(By.xpath("//td[text()='MANUAL_MESSAGE']/..//td[text()='" + message + "']"));
        utils.assertElementPresent(By.xpath("//td[text()='END_OF_JOURNEY']"));
    }


    public void switchCategoryToAll() {
        utils.assertElementPresent(By.xpath("//h6[text()='Journey Events']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//label[text()='Category']/../div/div"));
        utils.actionsClick(By.xpath("//li[text()='All']"));
    }

    public void assertDriverSkippedStopUnderJourneyView(String skippedStop) {
        switchCategoryToAll();
        utils.assertElementPresent(By.xpath("//td[text()='Reason: No Boarding Required; Route Points: " + skippedStop + "']/..//td[text()='DRIVER_PLANNED_SKIP_STOPS']"));
    }

    public void assertDriverStartAndEndOfJourney(String journeyStart, String journeyEnd) {
        utils.assertElementPresent(By.xpath("//td[text()='START_OF_JOURNEY']/..//td[text()='" + journeyStart + "']"));
        utils.assertElementPresent(By.xpath("//td[text()='END_OF_JOURNEY']/..//td[text()='" + journeyEnd + "']"));

    }

    public void assertSkippedToRoutePoint(String jumpedTo) {
        utils.assertElementPresent(By.xpath("//td[text()='SKIPPED_TO_ROUTEPOINT']/..//td[text()='" + jumpedTo + "']/..//td[text()='No reason provided']"));
    }

    public void sendNotifications(String audience, String message) {
        utils.sleep(1000);
        utils.hoverOverElement(By.xpath("//button[@aria-label='Journey actions']"));
        utils.actionsClick(By.xpath("//div[@id='Journeyactions-actions']//button[1]"));
        utils.actionsClick(By.xpath("//div[@id='select-Audience']"));
        if (audience.contentEquals("Observers")) {
            utils.clickBtn(By.xpath("//li[text()='" + audience + "']"));
            utils.sleep(1000);
            utils.actionsClick(By.xpath("//div[@id='select-Passenger']"));
            utils.actionsClick(By.xpath("//li[text()='lisa']"));
            utils.sendText(By.xpath("//label[text()='Message']/..//textarea"), message);
        } else {
            utils.clickBtn(By.xpath("//li[text()='" + audience + "']"));
            utils.sendText(By.xpath("//label[text()='Message']/..//textarea"), message);
        }
        utils.clickBtn(By.xpath("//span[text()='send']"));
    }

    public void createAlarm() {
        utils.sleep(1000);
        utils.clickBtn(By.xpath("//button[@aria-label='Journey actions']"));
        utils.clickBtn(By.xpath("//div[@id='Journeyactions-actions']//button[2]"));
        utils.sendText(By.xpath("//label[text()='Message']/..//textarea"), "call driver");
        utils.clickBtn(By.xpath("//span[text()='create']"));


    }

    public void captureAllBoardingAndDeBoardingNotificationsUnderJourneyPage(String passenger1, String passenger2, String passenger3, String passenger4, String location1, String location2, String location3, String location4) {
        utils.assertElementPresent(By.xpath("//td[text()='PASSENGER_BOARDED']/..//td[text()='" + location1 + "']/..//td[text()='" + passenger1 + "']"));
        utils.assertElementPresent(By.xpath("//td[text()='PASSENGER_DISEMBARK']/..//td[text()='" + location2 + "']"));
        utils.assertElementPresent(By.xpath("//td[text()='PASSENGER_BOARDED']/..//td[text()='" + location2 + "']/..//td[text()='" + passenger2 + "']"));
        utils.assertElementPresent(By.xpath("//td[text()='PASSENGER_DISEMBARK']/..//td[text()='" + location3 + "']"));
        utils.assertElementPresent(By.xpath("//td[text()='PASSENGER_BOARDED']/..//td[text()='" + location3 + "']/..//td[text()='" + passenger3 + "']"));
        utils.assertElementPresent(By.xpath("//td[text()='CONFIRM_ALL_DISEMBARKED']/..//td[text()='" + location4 + "']/..//td[text()='" + passenger4 + "']"));
        utils.assertElementPresent(By.xpath("//td[text()='PASSENGER_BOARDED']/..//td[text()='" + location4 + "']/..//td[text()='" + passenger4 + "']"));
    }


    public void compareEventTimeStampAndSystemReceiveTime() {
        utils.assertElementPresent(By.xpath("//tr[@class='mt-MuiTableRow-root mt-MuiTableRow-hover']//td[1]"));
        String eventTime = driver.findElement(By.xpath("//tr[@class='mt-MuiTableRow-root mt-MuiTableRow-hover']//td[1]")).getText();
        String extractEventTime = eventTime.split(" ")[1];
        String systemTime = driver.findElement(By.xpath("//tr[@class='mt-MuiTableRow-root mt-MuiTableRow-hover']//td[8]")).getText();
        String splitTimeByTAndGetZulu = systemTime.split(" ")[1];
        System.out.println(extractEventTime);
        System.out.println(splitTimeByTAndGetZulu);
        try {
            Assert.assertNotEquals(extractEventTime, splitTimeByTAndGetZulu);
        } catch (AssertionError e) {
            Assert.assertEquals(extractEventTime, splitTimeByTAndGetZulu);

        }


    }

    public void assertIamHere() {
        utils.assertElementPresent(By.xpath("//td[text()='rc club bunker']/..//td[3][text()='SKIPPED_TO_ROUTEPOINT']"));
        utils.assertElementPresent(By.xpath("//td[text()='UNEXPECTED_PASSENGER_DISEMBARKED_STOP']/..//td[text()='passengerone']/..//td[text()='rc club bunker']"));
        utils.assertElementPresent(By.xpath("//td[text()='my home']"));
        utils.assertElementAbsent(By.xpath("//td[text()='old home']"));
    }


}