package StepDefinitions;

import Support.WebModel;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en_old.Ac;
import io.appium.java_client.MobileBy;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static Pages.HomePage.randomNumber;
import static Pages.KuraAPPpage.mobileDriver;
import static Pages.KuraAPPpage.mobileParent;
import static Pages.OrganisationPage.*;
import static Support.ElementUtils.randomVehicleRegGenerator;
import static Support.BaseClass.driver;
import static java.lang.Thread.*;


public class smokePack_StepDefs {
    WebModel webModel = new WebModel();

    public static String RandomBulkUpLoadPassengerFirstName;
    public static String RandomBulkUpLoadPassengerLastName;


    @Given("^I am logged in as an operator with userName \"([^\"]*)\"$")
    public void iAmLoggedInAsAnOperatorWithUserName(String userName) {
        webModel.getLoginPage().assertLoginPage();
        webModel.getLoginPage().login(userName, "Kura123!");
        webModel.getCommonMethodsPage().switchToAndCloseBluePopUp();
    }

    @When("^I click on the plus icon$")
    public void iClickOnThePlusIcon() {
        webModel.getHomePage().assertHomePage();

    }

    @Then("^I should be able to add an organisation and search and assert the creation organisation and search and assert the creation$")
    public void iShouldBeAbleToAddAnOrganisationAndSearchAndAssertTheCreationOrganisationAndSearchAndAssertTheCreation() throws InterruptedException {
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("1");
        webModel.getHomePage().randomNumber = webModel.getUtils().randomNumber();
        webModel.getHomePage().addOrganisation("newOrg" + randomNumber + "", "Organisation");
        webModel.getHomePage().searchAndAssertOrganisation(true, "newOrg" + randomNumber + "", "ORGANISATION");

    }

    @Then("^I should be able to edit the created organisation and assert the changes$")
    public void iShouldBeAbleToEditTheCreatedOrganisationAndAssertTheChanges() {
        webModel.getHomePage().editOrganisation(true, "editedOrg" + randomNumber + "");
        webModel.getHomePage().searchAndAssertOrganisation(true, "editedOrg" + randomNumber + "", "SUPPLIER");

    }


    @When("^i navigate to the magic search page and search & select a particular organization$")
    public void iNavigateToTheMagicSearchPageAndSearchSelectAParticularOrganization() {
        webModel.getHomePage().searchAndPickOrganisation("newOrg" + randomNumber + "");

    }

    @Then("^i should be able to add an auth group and assert its creation$")
    public void iShouldBeAbleToAddAnAuthGroupAndAssertItsCreation() throws InterruptedException {
        webModel.getOrganisationPage().assertOrganisationPage();
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("5");
        webModel.getOrganisationPage().assertAddAuthGroupPage();
        webModel.getOrganisationPage().addAuthGroup("AuthGroup" + randomNumber + "", "newOrg" + randomNumber + "", false);
        webModel.getOrganisationPage().assertAuthGroup("AuthGroup" + randomNumber);
    }

    @And("^I should be able to edit the created auth group and assert the changes$")
    public void iShouldBeAbleToEditTheCreatedAuthGroupAndAssertTheChanges() throws InterruptedException {
        webModel.getOrganisationPage().editAuthGroup("AuthGroup" + randomNumber + "");

    }

    @Then("^I should be able to add a person profile and assert its creation$")
    public void iShouldBeAbleToAddAPersonProfileAndAssertItsCreation() throws InterruptedException {
        webModel.getOrganisationPage().assertOrganisationPage();
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("5");
        webModel.getOrganisationPage().assertAddPersonProfilePage();
        webModel.getOrganisationPage().addPassengerProfile(false);
    }

    @And("^I should be able to edit the created person profile and assert the changes$")
    public void iShouldBeAbleToEditTheCreatedPersonProfileAndAssertTheChanges() {
        webModel.getOrganisationPage().assertCreatedPersonProfile();
        webModel.getOrganisationPage().editPersonProfile(randomPassengerName);
    }

    @Then("^I should be able to create an action plan and assert its creation$")
    public void iShouldBeAbleToCreateAnActionPlanAndAssertItsCreation() throws InterruptedException {
        webModel.getOrganisationPage().assertOrganisationPage();
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("3");
        webModel.getOrganisationPage().assertActionPlanCreationPage();
        webModel.getOrganisationPage().createNewActionPlan();
        webModel.getUtils().clickLocatorBasedOnIndex(1, By.xpath("//div[@class='MuiListItemIcon-root']"));
        webModel.getHomePage().searchAndPickOrganisation("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("Action Plans");
        webModel.getOrganisationPage().assertActionPlanCreation(actionPlan);
    }


    @Then("^I should be able to add a new vehicle and assert its creation$")
    public void iShouldBeAbleToAddANewVehicleAndAssertItsCreation() throws InterruptedException {
        webModel.getOrganisationPage().assertOrganisationPage();
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("2");
        webModel.getOrganisationPage().assertAddVehiclePage();
        webModel.getOrganisationPage().addNewVehicle();
        webModel.getOrganisationPage().assertVehicle(randomVehicleRegGenerator.toString(), "20", "Volvo", "7900", "Orange");

    }

    @And("^I should be able to edit the created vehicle and assert the changes$")
    public void iShouldBeAbleToEditTheCreatedVehicleAndAssertTheChanges() {
        webModel.getOrganisationPage().editVehicle(randomVehicleRegGenerator.toString());
    }

    @Then("^I should be able to use it to create a client booing$")
    public void iShouldBeAbleToUseItToCreateAClientBooing() throws InterruptedException {
        webModel.getUtils().clickLocatorBasedOnIndex(1, By.xpath("//div[@class='MuiListItemIcon-root']"));
        webModel.getHomePage().searchAndPickOrganisation("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("1");
        webModel.getOrganisationPage().createContracts("searchableByObservers", "None");
    }


    @When("^I navigate to the view trip I should be able to clone a trip and clone the trip in reverse$")
    public void iNavigateToTheViewTripIShouldBeAbleToCloneATripAndCloneTheTripInReverse() throws Exception {
        webModel.getUtils().sleep(2000);
        webModel.getOrganisationPage().cloneATrip();
        webModel.getOrganisationPage().assertClonedTrips();
        webModel.getOrganisationPage().assertTimingsInCloneAndInReverse();
        webModel.getOrganisationPage().ableToCloneTripInReverse(tripName);
        webModel.getOrganisationPage().assertCloneInReverseTrip(tripName);

    }

    @And("^add extra passengers from trip schedule for today$")
    public void addExtraPassengersFromTripScheduleForTomorrow() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("EDIT TRIP");
        webModel.getOrganisationPage().addExtraPassengerWhilstOnEditTrip("passengertwo");
    }

    @Then("^I should be able to asset added passengers for today$")
    public void iShouldBeAbleToAssetAddedPassengersForToday() throws InterruptedException {
        webModel.getOrganisationPage().assertPassengersAreAddedToTheTrip(tripName, false, 1, 2, "passengerone", "passengertwo");
    }


    @And("^I should be able to add extra way points for today$")
    public void iShouldBeAbleToAddExtraWayPointsForToday() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("EDIT TRIP");
        webModel.getOrganisationPage().adding_deletingExtraWayPointFromEditScreen(true, "London Road");

    }

    @And("^assert that the way point is only added to today$")
    public void assertThatTheWayPointIsOnlyAddedToToday() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 2, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("VIEW TRIP");
        webModel.getOrganisationPage().assertAWayPoint(false, "London Road");
        webModel.getUtils().navigateBack();
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("VIEW TRIP");
        webModel.getOrganisationPage().assertAWayPoint(true, "London Road");
    }


    @When("^I view trip and click supplier$")
    public void iViewTripAndClickSupplier() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("Edit Suppliers");
    }


    @And("^login as the driver \"([^\"]*)\"$")
    public void loginAsTheDriver(String userName) {
        webModel.getKuraAPPpage().loginToDriverApp(userName, "Kura123!");
        webModel.getKuraAPPpage().assertMobileDashBoardPageForDriver();
    }

    @And("^navigate to the particular trip$")
    public void navigateToTheParticularTrip() throws Exception {
        webModel.getKuraAPPpage().navigateToTripOnDriverApp(tripName);
        webModel.getKuraAPPpage().assertAndChooseVehiclesFromAvailableList("AB12 QED", "yx63 ews");
        webModel.getKuraAPPpage().skipStops("my home", "old home", true);

    }

    @Then("^i should be able to send a message and cancel the trip,which should be captured under journey events on web kura$")
    public void iShouldBeAbleToSendAMessageAndCancelTheTripWhichShouldBeCapturedUnderJourneyEventsOnWebKura() throws InterruptedException {
        webModel.getKuraAPPpage().sendMessage("BREAKDOWN");
        webModel.getKuraAPPpage().navigateToJourneyTab();
        webModel.getKuraAPPpage().endCurrentJourney();
        webModel.getHomePage().chooseDesiredIconFromVerticalIndex(2);
        webModel.getJourneyPage().navigateToTrip_viaUpComingTrips(tripName);
        webModel.getJourneyPage().assertDriverRegistrationSendMessageAndCancelJourney("AB12 QED", "BREAKDOWN");
    }

    @And("^set geo location for maidenhead road$")
    public void setGeoLocationForMaidenheadRoad() {
        webModel.getUtils().sleep(3000);
        webModel.getCommonMethodsPage().setGeoLocation(51.52489, -0.64673, 0);

    }

    @When("^I launch kura mobile app$")
    public void iLaunchKuraMobileApp() throws MalformedURLException {
        webModel.getKuraAPPpage().launchDriverApp();
    }

    @And("^set geo location for maidenhead bridge$")
    public void setGeoLocationForMaidenheadBridge() {
        webModel.getCommonMethodsPage().setGeoLocation(51.52035, -0.62759, 0);
        webModel.getUtils().sleep(3000);
        webModel.getCommonMethodsPage().setGeoLocation(51.50904, -0.59625, 0);
    }

    @Then("^I should be able to skip the desired stop and complete my Journey$")
    public void iShouldBeAbleToSkipTheDesiredStopAndCompleteMyJourney() throws Exception {
        webModel.getKuraAPPpage().navigateToTripOnDriverApp(tripName);
        webModel.getKuraAPPpage().assertAndChooseVehiclesFromAvailableList("AB12 QED", "yx63 ews");
        webModel.getKuraAPPpage().skipStops("my home", "old home", false);
        webModel.getKuraAPPpage().boardPassengers("passengerone", false, false);
        webModel.getKuraAPPpage().assertStopIsSkipped("old home");
        webModel.getCommonMethodsPage().setGeoLocation(51.49717, -0.57433, 0);
        webModel.getKuraAPPpage().waitForPassengerScreen("Board");
        webModel.getKuraAPPpage().navigateToJourneyTab();
        webModel.getCommonMethodsPage().setGeoLocation(51.46393, -0.49304, 0);
        webModel.getKuraAPPpage().waitForPassengerScreen("Board");
        webModel.getKuraAPPpage().navigateToJourneyTab();
        webModel.getKuraAPPpage().endCurrentJourney();
    }


    @And("^I should be able to capture the skip notification under journey view$")
    public void iShouldBeAbleToCaptureTheSkipNotificationUnderJourneyView() throws InterruptedException {
        webModel.getHomePage().chooseDesiredIconFromVerticalIndex(2);
        webModel.getJourneyPage().navigateToTrip_viaUpComingTrips(tripName);
        webModel.getJourneyPage().assertDriverSkippedStopUnderJourneyView("old home");
    }


    @When("^I set geo location for croydon area to jump the maidenhead bridge$")
    public void iSetGeoLocationForCroydonAreaToJumpTheMaidenheadBridge() {
        webModel.getCommonMethodsPage().setGeoLocation(51.5199, -0.63386, 0);
        webModel.getUtils().sleep(5000);
        webModel.getCommonMethodsPage().setGeoLocation(51.49717, -0.57433, 0);
    }

    @Then("^i should be able confirm my location by clicking i am here button and complete the rest of the journey$")
    public void iShouldBeAbleConfirmMyLocationByClickingIAmHereButtonAndCompleteTheRestOfTheJourney() {
        webModel.getKuraAPPpage().clickIamOnSite("rc club bunker");
        webModel.getCommonMethodsPage().setGeoLocation(51.49544, -0.53729, 0);
        webModel.getCommonMethodsPage().setGeoLocation(51.46393, -0.49304, 0);
        webModel.getKuraAPPpage().unBoardPassenger("lonAir", "passengerone");
        webModel.getKuraAPPpage().navigatingFromPassengerPageToJourneyPage();
        webModel.getKuraAPPpage().endCurrentJourney();
    }

    @And("^capture i am here button actions under journey view$")
    public void captureIAmHereButtonActionsUnderJourneyView() throws InterruptedException {
        webModel.getHomePage().chooseDesiredIconFromVerticalIndex(3);
        webModel.getJourneyPage().navigateToTrip_viaUpComingTrips(tripName);
        webModel.getJourneyPage().assertSkippedToRoutePoint("rc club bunker");
    }

    @And("^board \"([^\"]*)\" at maidenhead road$")
    public void boardAtMaidenheadRoad(String passenger) {
        webModel.getKuraAPPpage().boardPassengers(passenger, false, false);

    }

    @Given("^I am logged in as an operator and create an organisation$")
    public void iAmLoggedInAsAnOperatorAndCreateAnOrganisation() throws InterruptedException {
        webModel.getLoginPage().login("rajeshgurrala@coachhire.com", "Kura123!");
        webModel.getCommonMethodsPage().switchToAndCloseBluePopUp();
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("1");
        randomNumber = webModel.getUtils().randomNumber();
        webModel.getHomePage().addOrganisation("newOrg" + randomNumber + "", "Organisation");

    }

    @And("^I should be able to update the created action plan$")
    public void iShouldBeAbleToUpdateTheCreatedActionPlan() throws InterruptedException {
        webModel.getOrganisationPage().editAnActionPlan();
        webModel.getOrganisationPage().assertActionPlanCreation("updatedActionPlan");

    }

    @And("^create an action plan$")
    public void createAnActionPlan() throws InterruptedException {
        webModel.getOrganisationPage().assertOrganisationPage();
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("3");
        webModel.getOrganisationPage().assertActionPlanCreationPage();
        webModel.getOrganisationPage().createNewActionPlan();
    }

    @And("^i pick organisation from org page$")
    public void iPickOrganisationFromOrgPage() {
        webModel.getHomePage().chooseDesiredIconFromVerticalIndex(4);
        webModel.getUtils().clickBtn(By.xpath("//td[text()='OrgCreatedForAutoTest2']"));
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");

    }

    @And("^i pick organisation from magic search or org page$")
    public void iPickOrganisationFromMagicSearchOrOrgPage() {
        webModel.getHomePage().searchAndPickOrganisation("OrgCreatedForAutoTest2");
    }

    public void closeAllPopups() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://booking.coachhire.com/admin/?page=logout#!login.php");
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("rajesh gurrala");
        driver.findElement(By.xpath("//input[@id='p']")).sendKeys("Sriya747");
        driver.findElement(By.xpath("//input[@id='button']")).click();
        sleep(1000);
        driver.findElement(By.xpath("//a[text()='Jobs']")).click();
        sleep(1000);
        driver.findElement(By.xpath("//a[text()='Latest Quotes ']"));
        List<WebElement> all = driver.findElements(By.xpath("//span[@aria-label='Close']"));
        for (WebElement each : all) {
            Actions actions = new Actions(driver);
            actions.moveToElement(each).click().perform();
        }
    }

    @And("^send notifications to the driver$")
    public void sendNotificationsToTheDriver() {
        webModel.getJourneyPage().sendNotifications("Driver", "call coachhire");
        webModel.getKuraAPPpage().assertNotifications();
    }

    @Then("^I should be able to add a \"([^\"]*)\" see if i can access the trip$")
    public void iShouldBeAbleToAddASeeIfICanAccessTheTrip(String trip) throws Exception {
        webModel.getOrganisationPage().assertOrganisationPage();
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        webModel.getOrganisationPage().assertAddTripPage();
        tripName = "trip_" + webModel.getUtils().tripName().toLowerCase();
        webModel.getUtils().sleep(3000);
        webModel.getOrganisationPage().addTripForASingleDay(false, tripName + trip + "", "my home", "old home", "passengerone", true, "searchableByObservers", false, 1, 0);
        webModel.getOrganisationPage().navigateToTripsPageAndSearch("OrgCreatedForAutoTest2", tripName + trip);
    }


    @And("^board \"([^\"]*)\" and deboard \"([^\"]*)\" at maidenhead bridge$")
    public void boardAndDeboardAtMaidenheadBridge(String passenger1, String passenger2) {
        webModel.getKuraAPPpage().unBoardPassenger("old home", passenger2);
        webModel.getKuraAPPpage().boardPassengers(passenger1, false, false);
    }

    @And("^set geo location for croydon arena$")
    public void setGeoLocationForCroydonArena() {
        webModel.getCommonMethodsPage().setGeoLocation(51.51141, -0.57737, 0);
        webModel.getUtils().sleep(3000);
        webModel.getCommonMethodsPage().setGeoLocation(51.49717, -0.57433, 0);
    }

    @And("^board \"([^\"]*)\" and deboard \"([^\"]*)\" at croydon arena$")
    public void boardAndDeboardAtCroydonArena(String arg0, String arg1) {
        webModel.getKuraAPPpage().unBoardPassenger("rc club bunker", arg1);
        webModel.getKuraAPPpage().boardPassengers(arg0, false, false);
    }

    @And("^set geo location for sevenoaks rail station$")
    public void setGeoLocationForSevenoaksRailStation() throws InterruptedException {
        webModel.getCommonMethodsPage().setGeoLocation(51.49504, -0.53729, 0);
        webModel.getUtils().sleep(3000);
        webModel.getCommonMethodsPage().setGeoLocation(51.46393, -0.49304, 0);
    }

    @And("^board and deboard passengers at sevenoaks rail station$")
    public void boardAndDeboardPassengersAtSevenoaksRailStation() {
        webModel.getKuraAPPpage().unBoardPassenger("lonAir", "passengerthree");
        webModel.getKuraAPPpage().boardPassengers("passengerfour", false, false);
        webModel.getKuraAPPpage().navigatingFromPassengerPageToJourneyPage();
        webModel.getKuraAPPpage().endCurrentJourney();
    }


    @Then("^I should be able to search and find the passenger$")
    public void iShouldBeAbleToSearchAndFindThePassenger() {
        webModel.getKuraAPPpage().searchPassengers("aad");
    }

    @Then("^I should be able to capture starting, ending points of journey as well as boardings, deboardings under the journey page\\.$")
    public void iShouldBeAbleToCaptureStartingEndingPointsOfJourneyAsWellAsBoardingsDeboardingsUnderTheJourneyPage() throws InterruptedException {
        webModel.getHomePage().chooseDesiredIconFromVerticalIndex(2);
        webModel.getJourneyPage().navigateToTrip_viaUpComingTrips(tripName);
        webModel.getJourneyPage().assertDriverStartAndEndOfJourney("my home", "lonAir");
        webModel.getJourneyPage().captureAllBoardingAndDeBoardingNotificationsUnderJourneyPage("passengerone", "passengertwo", "passengerthree", "passengerfour", "my home", "old home", "rc club bunker", "lonAir");

    }


    @And("^unboard the passengers at maidenhead bridge$")
    public void unboardThePassengersAtMaidenheadBridge() {
        webModel.getKuraAPPpage().unBoardPassenger("old home", "passengerfive");
    }

    @And("^end the journey$")
    public void endTheJourney() {
        webModel.getKuraAPPpage().navigatingFromPassengerPageToJourneyPage();
        webModel.getKuraAPPpage().endCurrentJourney();
    }


    @And("^unboard the \"([^\"]*)\" at maidenhead bridge$")
    public void unboardTheAtMaidenheadBridge(String passenger) {
        webModel.getKuraAPPpage().unBoardPassenger("old home", passenger);

    }

    @And("^I should be able to navigate to the trip journey page$")
    public void iShouldBeAbleToNavigateToTheTripJourneyPage() throws InterruptedException {
        webModel.getHomePage().chooseDesiredIconFromVerticalIndex(2);
        webModel.getJourneyPage().navigateToTrip_viaUpComingTrips(tripName);
    }

    @Then("^I should be able to mimic that i am actually at the pick up point and board passengers$")
    public void iShouldBeAbleToMimicThatIAmActuallyAtThePickUpPointAndBoardPassengers() {
        webModel.getKuraAPPpage().boardPassengers("passengerone", false, false);
    }

    @And("^when i end the journey$")
    public void whenIEndTheJourney() {
        webModel.getKuraAPPpage().endJourney("passengerone");
    }


    @Then("^I should be able to add passengers for the full trip schedule$")
    public void iShouldBeAbleToAddPassengersForTheFullTripSchedule() throws InterruptedException {
        webModel.getOrganisationPage().addOrDeletePassengersToTripViaTripSchedule("passengertwo", false, "Add", true, 1, 2, "my home", "old home");

    }

    @And("^assert that passengers have been added to the full trip$")
    public void assertThatPassengersHaveBeenAddedToTheFullTrip() {
        webModel.getOrganisationPage().assertPassengersAreAddedToTheTrip(tripName, true, 1, 2, "passengerone", "passengertwo");

    }

    @And("^delete the passengers$")
    public void deleteThePassengers() throws InterruptedException {
        webModel.getOrganisationPage().addOrDeletePassengersToTripViaTripSchedule("passengertwo", false, "Remove", true, 1, 2, "my home", "old home");

    }

    @Then("^the i should be able to assert passenger deleted$")
    public void theIShouldBeAbleToAssertPassengerDeleted() {
        webModel.getOrganisationPage().assertPassengersAreDeletedAcrossTrip(tripName, "passengertwo", "passengerone", true);

    }


    @Then("^I should be able to capture the boarding notification on the parent app$")
    public void iShouldBeAbleToCaptureTheBoardingNotificationOnTheParentApp() {
        webModel.getKuraAPPpage().notificationMessageOnParentAppParentApp("Passenger Boarded", false);
        try {
            webModel.getKuraAPPpage().notificationMessageOnParentAppParentApp("Service Ahead of Schedule", false);
        } catch (Exception e) {
            webModel.getUtils().sleep(10000);
            webModel.getKuraAPPpage().notificationMessageOnParentAppParentApp("Service Ahead of Schedule", false);
        }

    }

    @Then("^i should be able to capture deboarding notification on the parent app$")
    public void iShouldBeAbleToCaptureDeboardingNotificationOnTheParentApp() {
        webModel.getKuraAPPpage().notificationMessageOnParentAppParentApp("Passenger Disembarked", false);
        mobileParent.quit();
    }

    @And("^simultaneously login as \"([^\"]*)\"$")
    public void simultaneouslyLoginAsAs(String parentLogin) throws MalformedURLException {
        webModel.getKuraAPPpage().launchParentApp();
        webModel.getUtils().sleep(4000);
        webModel.getKuraAPPpage().loginToParentApp("uatkuratesting+" + parentLogin + "@gmail.com", "Kura123!");
        webModel.getKuraAPPpage().assertMobileDashBoardPageForParent();
    }

    @Then("^I should be able to trigger a manual alarm alert on the journey page$")
    public void iShouldBeAbleToTriggerAManualAlarmAlertOnTheJourneyPage() throws InterruptedException {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToJourneyFromOrgPage();
        webModel.getJourneyPage().createAlarm();

    }

    @And("^i should be able to capture manual triggered alarms, driver sent medical alerts and no boardings under alarms$")
    public void iShouldBeAbleToCaptureManualTriggeredAlarmsDriverSentMedicalAlertsAndNoBoardingsUnderAlarms() throws InterruptedException {
        webModel.getHomePage().chooseDesiredIconFromVerticalIndex(4);
        webModel.getAlarmsPage().checkMedical_FailedToBoard_CallDriverAlarms_closeAll(tripName);
    }

    @And("^send a medical alarm$")
    public void sendAMedicalAlarm() {
        webModel.getKuraAPPpage().sendMessage("MEDICAL");
    }


    @When("^i perform bulk upload$")
    public void iPerformBulkUpload() throws InterruptedException, AWTException {
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("6");
        webModel.getOrganisationPage().bulkUpload(System.getProperty("user.dir") + "\\Bulkupload\\bulkupload.csv", "bulkupload");
    }

    @Then("^the passengers and observers list should be populated$")
    public void thePassengersAndObserversListShouldBePopulated() throws InterruptedException {
        webModel.getOrganisationPage().checkProcessOfBulkUpLoadAndAssertDataUpload(true);
        webModel.getOrganisationPage().checkIfObserverIsAddedToAllPassengers();
    }

    @And("^when i update the list with a new updated upload then the list should be updated accordingly$")
    public void whenIUpdateTheListWithANewUpdatedUploadThenTheListShouldBeUpdatedAccordingly() throws InterruptedException, AWTException {
        webModel.getUtils().clickLocatorBasedOnIndex(1, By.xpath("//div[@class='MuiListItemIcon-root']"));
        webModel.getHomePage().searchAndPickOrganisation("newOrg" + randomNumber);
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("6");
        webModel.getOrganisationPage().bulkUpload(System.getProperty("user.dir") + "\\Bulkupload\\bulkupdate.csv", "bulkupdate");
        webModel.getOrganisationPage().checkProcessOfBulkUpLoadAndAssertDataUpload(false);
    }


    @When("^i wait for (\\d+) hour$")
    public void iWaitForHour(int arg0) throws InterruptedException {
        Thread.sleep(7700000);
    }

    @Then("^i should be able to capture the no logon and late alarms on the alarm page$")
    public void iShouldBeAbleToCaptureTheNoLogonAndLateAlarmsOnTheAlarmPage() throws InterruptedException {
        webModel.getUtils().refreshPage();
        webModel.getHomePage().chooseDesiredIconFromVerticalIndex(4);
        webModel.getAlarmsPage().checkLate_NoLogon_closeAll(tripName);
    }

    @Then("^I should receive a passenger not boarded notification on parent app$")
    public void iShouldReceiveAPassengerNotBoardedNotificationOnParentApp() {
        webModel.getKuraAPPpage().notificationMessageOnParentAppParentApp("Not Boarded", false);
        // webModel.getKuraAPPpage().deleteTripOnParentApp();
        mobileParent.quit();
    }

    @Then("^I should get unexpected boarding message on parent app$")
    public void iShouldGetUnexpectedBoardingMessageOnParentApp() {
        webModel.getKuraAPPpage().notificationMessageOnParentAppParentApp("Unexpected Boarding", false);
    }

    @And("^set geo location for rc club bunker$")
    public void setGeoLocationForRcClubBunker() {
        webModel.getCommonMethodsPage().setGeoLocation(51.49717, -0.57433, 0);

    }

    @Then("^I should get unexpected deboarding message on parent app$")
    public void iShouldGetUnexpectedDeboardingMessageOnParentApp() {
        webModel.getKuraAPPpage().notificationMessageOnParentAppParentApp("Unexpected Disembark", false);
    }


    @And("^I should be able to end the journey and delete the trip from parent app$")
    public void iShouldBeAbleToEndTheJourneyAndDeleteTheTripFromParentApp() {

    }

    @And("^I should be able to process the early alarms under the alarm section$")
    public void iShouldBeAbleToProcessTheEarlyAlarmsUnderTheAlarmSection() throws InterruptedException {
        webModel.getHomePage().chooseDesiredIconFromVerticalIndex(4);
        webModel.getAlarmsPage().captureEarlyScheduleAlarm(tripName);
    }

    @Then("^I should be able to capture delayed parent notification in oneSignal$")
    public void iShouldBeAbleToCaptureDelayedParentNotificationInOneSignal() {
        webModel.getOrganisationPage().loginToOneSignalAndCheckNotifications("passengerfive");

    }

    @Given("^I create a two hour head single day trip with \"([^\"]*)\"$")
    public void iCreateATwoHourHeadSingleDayTripWith(String passenger) throws Throwable {
        iAmLoggedInAsAnOperatorWithUserName("rajeshgurrala@coachhire.com");
        webModel.getCommonMethodsPage().switchToAndCloseBluePopUp();
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        tripName = "trip_" + webModel.getUtils().tripName().toLowerCase();
        webModel.getOrganisationPage().addTripForASingleDay(false, tripName, "my home", "old home", passenger, true, "notifications", true, 1, 0);
    }


    @Then("^I should be able to assert that the account is linked$")
    public void iShouldBeAbleToAssertThatTheAccountIsLinked() {
    }

    @When("^I create an org and add an observer profile$")
    public void iCreateAnOrgAndAddAnObserverProfile() throws InterruptedException {
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("1");
        webModel.getHomePage().randomNumber = webModel.getUtils().randomNumber();
        webModel.getHomePage().addOrganisation("newOrg" + randomNumber + "", "Organisation");
        webModel.getHomePage().pickOrganisationFromOrgsPage("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("5");
        webModel.getOrganisationPage().addObserverProfile();
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("5");
        webModel.getOrganisationPage().createObserverProfileWithOutEmail();

    }


    @And("^I should be able to sign up to the observerapp on the mobile device$")
    public void iShouldBeAbleToSignUpToTheObserverappOnTheMobileDevice() throws MalformedURLException {
        webModel.getKuraAPPpage().launchParentApp();
        webModel.getKuraAPPpage().loginToParentApp("uatkuratesting+observer" + randomObserverName + "@gmail.com", "Kura123!");
//        change here
//        webModel.getKuraAPPpage().assertMobileDashBoardPageForParent();
    }


    @And("^I should be able to use the action plan to create a contract and then assert that the contract is visible under create trip page$")
    public void iShouldBeAbleToUseTheActionPlanToCreateAContractAndThenAssertThatTheContractIsVisibleUnderCreateTripPage() throws InterruptedException {
        iShouldBeAbleToUseItToCreateAClientBooing();
        webModel.getUtils().clickLocatorBasedOnIndex(1, By.xpath("//div[@class='MuiListItemIcon-root']"));
        webModel.getHomePage().searchAndPickOrganisation("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("1");
        webModel.getOrganisationPage().assertCreatedContractIsAccessibleUnderCreateTrip("searchableByObservers");

    }

    @When("^I create an auth group and assign that to a new person profile$")
    public void iCreateAnAuthGroupAndAssignThatToANewPersonProfile() throws InterruptedException, IOException, AWTException {
        webModel.getUtils().clickLocatorBasedOnIndex(1, By.xpath("//div[@class='MuiListItemIcon-root']"));
        webModel.getHomePage().searchAndPickOrganisation("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("4");
        webModel.getOrganisationPage().assertAddAuthGroupPage();
        webModel.getOrganisationPage().addAuthGroup("AuthGroup" + randomNumber + "", "newOrg" + randomNumber + "", false);
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("5");
        webModel.getOrganisationPage().assertAddPersonProfilePage();
        webModel.getOrganisationPage().addPersonProfileWithAuthGroup("observer", "AuthGroup" + randomNumber);
        webModel.getKuraAPPpage().loginToGoolgeAndDeletePreviousKuraMails();
        webModel.getOrganisationPage().createNewAccountOnKura("observer" + randomObserverName + "");
        webModel.getKuraAPPpage().loginToGoogleAppAndGetOTP();
        webModel.getOrganisationPage().enterOTPandFinishAccountCreationOnKura();


    }

    @Then("^I should be able to login as that person and confirm that the permissions are enforced$")
    public void iShouldBeAbleToLoginAsThatPersonAndConfirmThatThePermissionsAreEnforced() throws MalformedURLException {
        webModel.getLoginPage().login("rajeshgurrala@coachhire.com", "Kura123!");
        webModel.getHomePage().pickOrganisationFromOrgsPage("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("People");
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Total", randomObserverName);
        webModel.getUtils().clickBtn(By.xpath("//span[text()='observer" + randomObserverName + "']"));
        webModel.getKuraAPPpage().loginToGoolgeAndDeletePreviousKuraMails();
        webModel.getOrganisationPage().linkPersonProfileToCognitiveAccountOnKura(true, "uatkuratesting+" + "observer" + randomObserverName + "@gmail.com", "Kura123!", false);
        webModel.getKuraAPPpage().loginToGoolgeAndDeletePreviousKuraMails();
        webModel.getUtils().sleep(2000);
        webModel.getOrganisationPage().assertAuthPermissions();

    }


    @When("^I break the link between the observer profile and cognitive account$")
    public void iBreakTheLinkBetweenTheObserverProfileAndCognitiveAccount() {
        webModel.getUtils().clickLocatorBasedOnIndex(1, By.xpath("//div[@class='MuiListItemIcon-root']"));
        webModel.getHomePage().searchAndPickOrganisation("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().breakLinkBetweenPersonProfileAndCognitiveAccount("observer" + randomObserverName);

    }

    @Then("^the link page should be shown when the observer logs in$")
    public void theLinkPageShouldBeShownWhenTheObserverLogsIn() throws MalformedURLException {
        webModel.getKuraAPPpage().launchParentApp();
        webModel.getKuraAPPpage().loginToParentApp("uatkuratesting+" + "observer" + randomObserverName + "@gmail.com", "Kura123!");
        webModel.getKuraAPPpage().assertLinkPage();


    }

    @And("^when i re-establish the link$")
    public void whenIReEstablishTheLink() throws MalformedURLException {
        webModel.getOrganisationPage().linkPersonProfileToCognitiveAccountOnKura(true, "uatkuratesting+" + "observer" + randomObserverName + "@gmail.com", "Kura123!", false);
        webModel.getKuraAPPpage().loginToGoolgeAndDeletePreviousKuraMails();
    }

    @Then("^the observer should be able to see the passenger again on the app$")
    public void theObserverShouldBeAbleToSeeThePassengerAgainOnTheApp() throws Exception {
        webModel.getKuraAPPpage().launchParentApp();
        webModel.getKuraAPPpage().loginToParentApp("uatkuratesting+observer" + randomObserverName + "@gmail.com", "Kura123!");
//        change here
//        webModel.getKuraAPPpage().assertMobileDashBoardPageForParent();
        webModel.getKuraAPPpage().assertParentCanSeeTrip(tripName, true);
        mobileParent.quit();
    }

    @When("^I create a supplier and add driver and vehicle$")
    public void iCreateASupplierAndAddDriverAndVehicle() throws InterruptedException, IOException {
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("1");
        webModel.getHomePage().randomNumber = webModel.getUtils().randomNumber();
        webModel.getHomePage().addOrganisation("newSupplier" + randomNumber + "", "Supplier");
        webModel.getHomePage().searchAndPickOrganisation("newSupplier" + randomNumber + "");
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("4");
        webModel.getOrganisationPage().assertAddAuthGroupPage();
        webModel.getOrganisationPage().createDriverAuthGroup();
        webModel.getHomePage().searchAndPickOrganisation("newSupplier" + randomNumber + "");
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("5");
        webModel.getOrganisationPage().assertAddPersonProfilePage();
        webModel.getOrganisationPage().addPersonProfileWithAuthGroup("driver", "driver");
        webModel.getHomePage().searchAndPickOrganisation("newSupplier" + randomNumber + "");
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("2");
        webModel.getOrganisationPage().assertAddVehiclePage();
        webModel.getOrganisationPage().addNewVehicle();
    }

    @Then("^I should be able to create a trip with the new driver and vehicle details$")
    public void iShouldBeAbleToCreateATripWithTheNewDriverAndVehicleDetails() throws Exception {
        webModel.getHomePage().searchAndPickOrganisation("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().assertOrganisationPage();
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        tripName = "trip_" + webModel.getUtils().tripName();
        webModel.getOrganisationPage().addTripForASingleDay(false, tripName, "my home", "old home", "passengerone", true, "searchableByObservers", false, 1, 0);
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("EDIT TRIP");
        webModel.getOrganisationPage().clickSupplierOnEditTripPage();
        webModel.getOrganisationPage().addSupplierForSingleDay("newSupplier" + randomNumber + "", "driver" + randomObserverName, vehicleReg, "10");

    }

    @And("^when logged as a driver, I should be able to see the trip$")
    public void whenLoggedAsADriverIShouldBeAbleToSeeTheTrip() throws Exception {
        webModel.getKuraAPPpage().loginToGoolgeAndDeletePreviousKuraMails();
        webModel.getOrganisationPage().createNewAccountOnKura("driver" + randomObserverName + "");
        webModel.getKuraAPPpage().loginToGoogleAppAndGetOTP();
        webModel.getOrganisationPage().enterOTPandFinishAccountCreationOnKura();
        webModel.getLoginPage().login("rajeshgurrala@coachhire.com", "Kura123!");
        webModel.getHomePage().pickOrganisationFromOrgsPage("newSupplier" + randomNumber + "");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("People");
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Total", randomObserverName);
        webModel.getUtils().clickBtn(By.xpath("//span[text()='driver" + randomObserverName + "']"));
        webModel.getOrganisationPage().linkPersonProfileToCognitiveAccountOnKura(true, "uatkuratesting+" + "driver" + randomObserverName + "@gmail.com", "Kura123!", false);
        webModel.getKuraAPPpage().loginToGoolgeAndDeletePreviousKuraMails();
        webModel.getKuraAPPpage().launchDriverApp();
        webModel.getKuraAPPpage().loginToDriverApp("uatkuratesting+driver" + randomObserverName + "@gmail.com", "Kura123!");
        webModel.getKuraAPPpage().navigateToTripOnDriverApp(tripName);
    }

    @And("^I should be able to export the person profiles as a csv file$")
    public void iShouldBeAbleToExportThePersonProfilesAsACsvFile() {
        webModel.getHomePage().logoutOfKura();
        webModel.getLoginPage().login("rajeshgurrala@coachhire.com", "Kura123!");
        webModel.getUtils().clickLocatorBasedOnIndex(1, By.xpath("//div[@class='MuiListItemIcon-root']"));
        webModel.getHomePage().searchAndPickOrganisation("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("People");
        //webModel.getOrganisationPage().exportData("Persons");
    }


    @When("^I navigate to the modify trip tab$")
    public void iNavigateToTheModifyTripTab() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
    }

    @Then("^I should be able to add supplier for the whole of the trip$")
    public void iShouldBeAbleToAddSupplierForTheWholeOfTheTrip() throws InterruptedException {
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("Edit Suppliers");
        webModel.getOrganisationPage().addSupplierDetailsForTripSchedule("SupplierForAutoTest", 1, 2, "10", false, false);


    }

    @And("^assert the supplier added to full trip$")
    public void assertTheSupplierAddedToFullTrip() throws Exception {
        iNavigateToTheModifyTripTab();
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("EDIT TRIP");
        webModel.getOrganisationPage().clickSupplierOnEditTripPage();
        webModel.getOrganisationPage().assertSavedSupplierDetails();
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 2, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("EDIT TRIP");
        webModel.getOrganisationPage().clickSupplierOnEditTripPage();
        webModel.getOrganisationPage().assertSavedSupplierDetails();
    }


    @Then("^I should be able to assert that new added rout points$")
    public void iShouldBeAbleToAssertThatNewAddedRoutPoints() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 2, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("VIEW TRIP");
        webModel.getUtils().assertElementPresent(By.xpath("//span[text()='rc club bunker']"));
        Assert.assertNotEquals(webModel.getUtils().getTextBasedOnIndex(3, By.xpath("//input[@class='MuiInputBase-input MuiInput-input MuiInputBase-inputMarginDense MuiInput-inputMarginDense']")), webModel.getUtils().getTextBasedOnIndex(6, By.xpath("//input[@class='MuiInputBase-input MuiInput-input MuiInputBase-inputMarginDense MuiInput-inputMarginDense']")));
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("VIEW TRIP");
        webModel.getUtils().assertElementPresent(By.xpath("//span[text()='rc club bunker']"));
        Assert.assertNotEquals(webModel.getUtils().getTextBasedOnIndex(3, By.xpath("//input[@class='MuiInputBase-input MuiInput-input MuiInputBase-inputMarginDense MuiInput-inputMarginDense']")), webModel.getUtils().getTextBasedOnIndex(6, By.xpath("//input[@class='MuiInputBase-input MuiInput-input MuiInputBase-inputMarginDense MuiInput-inputMarginDense']")));
    }

    @And("^when i delete the supplier for single day$")
    public void whenIDeleteTheSupplierForSingleDay() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 2, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("Edit Suppliers");
        webModel.getUtils().refreshPage();
        webModel.getUtils().sleep(1000);
        webModel.getUtils().assertElementPresent(By.xpath("//span[text()='REMOVE']"));
        webModel.getUtils().clickBtn(By.xpath("//span[text()='REMOVE']"));
        webModel.getCommonMethodsPage().clickSave();
        webModel.getUtils().assertElementPresent(By.xpath("//h2[text()='Task ran successfully']"));
        webModel.getUtils().clickBtn(By.xpath("//span[text()='Close']"));
        webModel.getUtils().refreshPage();
        webModel.getUtils().sleep(5000);
        webModel.getUtils().refreshPage();
        webModel.getUtils().sleep(2000);
        webModel.getUtils().refreshPage();
        webModel.getUtils().sleep(2000);
        webModel.getUtils().refreshPage();
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 2, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("Edit Suppliers");
        webModel.getOrganisationPage().addSupplierDetailsForTripSchedule("SupplierForAutoTest", 1, 1, "10", false, false);

    }

    @Then("^the supplier should be deleted for that particular day$")
    public void theSupplierShouldBeDeletedForThatParticularDay() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 2, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("EDIT TRIP");
        webModel.getOrganisationPage().clickSupplierOnEditTripPage();
        webModel.getOrganisationPage().assertSavedSupplierDeleted();
        iNavigateToTheModifyTripTab();
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("EDIT TRIP");
        webModel.getOrganisationPage().clickSupplierOnEditTripPage();
        webModel.getOrganisationPage().assertSavedSupplierDetails();
    }


    @Then("^the route should be deleted$")
    public void theRouteShouldBeDeleted() throws Exception {

        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 2, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("VIEW TRIP");
        try {
            webModel.getUtils().assertElementAbsent(By.xpath("//span[text()='rc club bunker']"));
        } catch (AssertionError e) {
            webModel.getUtils().refreshPage();
            webModel.getUtils().assertElementAbsent(By.xpath("//span[text()='rc club bunker']"));
        }
        Assert.assertNotEquals(webModel.getUtils().getTextBasedOnIndex(3, By.xpath("//input[@class='MuiInputBase-input MuiInput-input MuiInputBase-inputMarginDense MuiInput-inputMarginDense']")), webModel.getUtils().getTextBasedOnIndex(7, By.xpath("//input[@class='MuiInputBase-input MuiInput-input MuiInputBase-inputMarginDense MuiInput-inputMarginDense']")));

        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("VIEW TRIP");
        try {
            webModel.getUtils().assertElementAbsent(By.xpath("//span[text()='rc club bunker']"));
        } catch (AssertionError e) {
            webModel.getUtils().refreshPage();
            webModel.getUtils().assertElementAbsent(By.xpath("//span[text()='rc club bunker']"));

        }
        Assert.assertNotEquals(webModel.getUtils().getTextBasedOnIndex(3, By.xpath("//input[@class='MuiInputBase-input MuiInput-input MuiInputBase-inputMarginDense MuiInput-inputMarginDense']")), webModel.getUtils().getTextBasedOnIndex(7, By.xpath("//input[@class='MuiInputBase-input MuiInput-input MuiInputBase-inputMarginDense MuiInput-inputMarginDense']")));

    }


    @When("^I override the contract under particular client booking$")
    public void iOverrideTheContractUnderParticularClientBooking() throws InterruptedException {
        webModel.getOrganisationPage().navigateToEditRules_or_TandCs("OrgCreatedForAutoTest2", "notifications", false);
        webModel.getOrganisationPage().overrideClientBooking(tripName);
    }

    @Then("^i should be able to confirm that by performing the trip$")
    public void iShouldBeAbleToConfirmThatByPerformingTheTrip() {
        webModel.getUtils().sleep(3000);
        Assert.assertTrue(mobileParent.findElements(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Expected Boarding\")")).isEmpty());
        mobileParent.quit();
    }


    @When("^I try over-boarding the vehicle via edit trip$")
    public void iTryOverBoardingTheVehicleViaEditTrip() throws InterruptedException {
        webModel.getOrganisationPage().addExtraPassengerWhilstOnEditTrip("alex");


    }


    @Then("^I should see an error message stating \"([^\"]*)\"$")
    public void iShouldSeeAnErrorMessageStating(String message) {
        webModel.getUtils().assertElementPresent(By.xpath("//p[text()[contains(., '" + message + "')]]"));
    }

    @When("^i navigate to the search trip page$")
    public void iNavigateToTheSearchTripPage() throws Exception {
        webModel.getOrganisationPage().navigateToTripsPageAndSearch("OrgCreatedForAutoTest2", tripName);

    }

    @Then("^I should be able to change the trip name and assert the changes$")
    public void iShouldBeAbleToChangeTheTripNameAndAssertTheChanges() {
        webModel.getOrganisationPage().renameTripAndAssertChanges(tripName);
    }


    @When("^I login as the observer \"([^\"]*)\"$")
    public void iLoginAsTheObserver(String Observer) throws Throwable {
        webModel.getKuraAPPpage().launchParentApp();
        webModel.getKuraAPPpage().loginToParentApp("uatkuratesting+" + Observer + "@gmail.com", "Kura123!");
    }

    @Then("^I should be able to view the trip and book the passenger on to the trip$")
    public void iShouldBeAbleToViewTheTripAndBookThePassengerOnToTheTrip() throws InterruptedException, IOException {
        webModel.getKuraAPPpage().asObserver_ClickTheTripYouWouldWantThePassengerToBeAddedOnTo(tripName);
        webModel.getKuraAPPpage().observerBookingTrip_TwoRoutePoints(381, 510, 736, 765, false, false, "");
        webModel.getKuraAPPpage().successfulAdditionOfPassengerToTrip();
        webModel.getKuraAPPpage().updateParentApp();
    }

    @Then("^I should be able to see the trip under the observer app$")
    public void iShouldBeAbleToSeeTheTripUnderTheObserverApp() {
        webModel.getKuraAPPpage().assertParentCanSeeTrip(tripName, true);
    }

    @And("^once the passenger is removed off the trip$")
    public void onceThePassengerIsRemovedOffTheTrip() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("EDIT TRIP");
        webModel.getOrganisationPage().deletePassengerWhilstOnEditTrip();


    }

    @Then("^the observer \"([^\"]*)\" will not be able to see$")
    public void theObserverWillNotBeAbleToSee(String observer) throws Exception {
        mobileParent.quit();
        webModel.getKuraAPPpage().launchParentApp();
        webModel.getKuraAPPpage().loginToParentApp("uatkuratesting+" + observer + "@gmail.com", "Kura123!");
           webModel.getKuraAPPpage().updateParentApp();
        webModel.getKuraAPPpage().updateParentApp();
//      change here
//        webModel.getKuraAPPpage().assertMobileDashBoardPageForParent();
        webModel.getKuraAPPpage().assertParentCanSeeTrip(tripName, false);
        mobileParent.quit();
    }


    @And("^try adding a passenger to the trip$")
    public void tryAddingAPassengerToTheTrip() throws InterruptedException, IOException {
        webModel.getKuraAPPpage().asObserver_ClickTheTripYouWouldWantThePassengerToBeAddedOnTo(tripName);
        webModel.getKuraAPPpage().observerBookingTrip_TwoRoutePoints(381, 510, 736, 765, false, false, "");
        //new 381, 510, 736, 765
        //old 381, 510, 750, 928
    }


    @Then("^an error message stating \"([^\"]*)\" should be thrown$")
    public void anErrorMessageStatingShouldBeThrown(String string) {
        webModel.getKuraAPPpage().errorMessageOnParentApp(string);
        mobileParent.quit();
    }

    @When("^I navigate to view schedule trips under the respective passenger$")
    public void iNavigateToViewScheduleTripsUnderTheRespectivePassenger() {
        webModel.getUtils().sleep(3000);
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("People");
        webModel.getOrganisationPage().navigateToViewSchedule("akon");
        webModel.getOrganisationPage().assertTripUnderPassengerTripSchedule(tripName);

    }

    @And("^I should be able to view and remove the person from the trip$")
    public void iShouldBeAbleToViewAndRemoveThePersonFromTheTrip() throws Exception {
        webModel.getOrganisationPage().removePassengerFromTrip_viaPassengerSchedule(tripName);
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("Trips");
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("EDIT TRIP");
        webModel.getUtils().assertElementAbsent(By.xpath("//p[text()='akon']"));

    }


    @Then("^I should be able to view passenger addition under booking when i login as ops with in the org$")
    public void iShouldBeAbleToViewPassengerAdditionUnderBookingWhenILoginAsOpsWithInTheOrg() {
        webModel.getHomePage().logoutOfKura();
        webModel.getLoginPage().login("uatkuratesting+orgadmin@gmail.com", "Kura123!");
        webModel.getHomePage().searchAndPickOrganisation("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToBookingsPage();
        webModel.getOrganisationPage().assertPassengerAdd_OrDeleteEntryUnderBookingsPage(false);
        webModel.getHomePage().logoutOfKura();
        webModel.getLoginPage().login("rajeshgurrala@coachhire.com", "Kura123!");


    }

    @Then("^I should be able to view passenger deletion under booking when i login as ops with in the org$")
    public void iShouldBeAbleToViewPassengerDeletionUnderBookingWhenILoginAsOpsWithInTheOrg() {
        webModel.getHomePage().logoutOfKura();
        webModel.getLoginPage().login("uatkuratesting+orgadmin@gmail.com", "Kura123!");
        webModel.getHomePage().searchAndPickOrganisation("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToBookingsPage();
        webModel.getOrganisationPage().assertPassengerAdd_OrDeleteEntryUnderBookingsPage(true);
    }


    @Then("^I should be confrim that the trip has been created only for a single day$")
    public void iShouldBeConfrimThatTheTripHasBeenCreatedOnlyForASingleDay() throws Exception {
        webModel.getOrganisationPage().navigateToTripCalendar("OrgCreatedForAutoTest2", tripName);
        Assert.assertEquals(webModel.getUtils().getWebElementCount(By.xpath("//p[text()='" + tripName + "']")), 1);
    }

    @When("^I navigate to the edit trip schedule and add exclusion dates$")
    public void iNavigateToTheEditTripScheduleAndAddExclusionDates() throws Exception {
        webModel.getOrganisationPage().navigateToEditTripSchedule_AddExclusionDates();

    }


    @Then("^I should be able to delete all the trips from the trip calendar$")
    public void iShouldBeAbleToDeleteAllTheTripsFromTheTripCalendar() throws Exception {
        webModel.getOrganisationPage().navigateToTripCalendar("OrgCreatedForAutoTest2", tripName);

        boolean value = true;
        int numbers = 10;
        while (value) {
            try {
                webModel.getUtils().assertElementAbsent(By.xpath("//p[text()='" + tripName + "']"));
                value = false;
            } catch (AssertionError e) {
                webModel.getUtils().sleep(7000);
                webModel.getOrganisationPage().navigateToTripCalendar("OrgCreatedForAutoTest2", tripName);
                webModel.getUtils().refreshPage();
                numbers--;
                if (numbers == 0) {
                    throw new Exception("unable to delete trips");
                }
            }

        }

    }

    @And("^I should be able to confirm tht the trip is deleted under the passenger trip schedule$")
    public void iShouldBeAbleToConfirmThtTheTripIsDeletedUnderThePassengerTripSchedule() {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("People");
        webModel.getOrganisationPage().navigateToViewSchedule("rupa");
        webModel.getOrganisationPage().assertNoTripUnderPassengerTripSchedule(tripName);
    }


    @Then("^I should be able to successfully add a document$")
    public void iShouldBeAbleToSuccessfullyAddADocument() throws AWTException, InterruptedException {
        webModel.getOrganisationPage().findPersonProfileAndUploadDocument(randomObserverName);
        webModel.getUtils().assertElementPresent(By.xpath("//input[@value='sampleDocument']"));
    }

    @When("^I send a notification to \"([^\"]*)\"$")
    public void iSendANotificationTo(String allObservers) throws InterruptedException {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToJourneyFromOrgPage();
        webModel.getJourneyPage().sendNotifications(allObservers, "test");

    }

    @Then("^all the observers should receive the parent notification$")
    public void allTheObserversShouldReceiveTheParentNotification() {
        WebDriverWait wait = new WebDriverWait(mobileParent, 40);
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"test\")")));
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"OK\")"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"test\")")));
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"OK\")"))).click();
    }

    @And("^when I send notification to particular observer$")
    public void whenISendNotificationToParticularObserver() {
        webModel.getJourneyPage().sendNotifications("Observers", "test");


    }

    @Then("^only that observer should receive notification$")
    public void onlyThatObserverShouldReceiveNotification() {
        WebDriverWait wait = new WebDriverWait(mobileParent, 40);
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"test\")")));
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"OK\")"))).click();
        Assert.assertFalse(webModel.getUtils().checkIfElementIsDisplayed_ParentApp(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"test\")")));
    }

    @Then("^only the observer whose passenger is still onboard should receive notification$")
    public void onlyTheObserverWhosePassengerIsStillOnboardShouldReceiveNotification() {

        WebDriverWait wait = new WebDriverWait(mobileParent, 40);
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"test\")")));
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"OK\")"))).click();
        Assert.assertFalse(webModel.getUtils().checkIfElementIsDisplayed_ParentApp(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"test\")")));
    }

    @When("^I remove the passenger off the trip$")
    public void iRemoveThePassengerOffTheTrip() {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("People");
        webModel.getOrganisationPage().navigateToViewSchedule("ajith");
        webModel.getOrganisationPage().assertTripUnderPassengerTripSchedule(tripName);
        webModel.getOrganisationPage().removePassengerFromTrip_viaPassengerSchedule(tripName);
    }

    @Then("^The passenger should not appear under the obs app$")
    public void thePassengerShouldNotAppearUnderTheObsApp() {
        //confirming the trip is removed from app
        webModel.getKuraAPPpage().updateParentApp();
        webModel.getKuraAPPpage().assertParentCanSeeTrip(tripName, false);
    }


    @And("^close the parent app$")
    public void closeTheParentApp() {
        mobileParent.quit();
    }

    @And("^open parent app$")
    public void openParentApp() throws MalformedURLException {
        webModel.getKuraAPPpage().launchParentApp();

    }

    @And("^I should be able to confirm new client booking creation$")
    public void iShouldBeAbleToConfirmNewClientBookingCreation() {
        webModel.getUtils().clickLocatorBasedOnIndex(1, By.xpath("//div[@class='MuiListItemIcon-root']"));
        webModel.getHomePage().searchAndPickOrganisation("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("Contracts");
        webModel.getOrganisationPage().assertCreatedContract("Contract");
    }


    @And("^logout of the kura web$")
    public void logoutOfTheKuraWeb() {
        webModel.getHomePage().logoutOfKura();
    }

    @When("^I send a notification to \"([^\"]*)\" via trip page$")
    public void iSendANotificationToViaTripPage(String allObservers) throws InterruptedException {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToJourneyFromOrgPage();
        webModel.getJourneyPage().sendNotifications(allObservers, "test");


    }

    @Given("^as a coachhire user i issue permissions to org user$")
    public void asACoachhireUserIIssuePermissionsToOrgUser() throws InterruptedException {
        webModel.getLoginPage().login("rajeshgurrala@coachhire.com", "Kura123!");
        webModel.getCommonMethodsPage().switchToAndCloseBluePopUp();
        webModel.getOrganisationPage().navigateToEditAuthGroups("OrgCreatedForAutoTest2", "ops");
        webModel.getOrganisationPage().editJourneyReportingBookingPermissions(true);


    }

    @Then("^the org user should be able to access bookings, maps and reporting$")
    public void theOrgUserShouldBeAbleToAccessBookingsMapsAndReporting() {
        webModel.getHomePage().logoutOfKura();
        webModel.getLoginPage().login("uatkuratesting+adam@gmail.com", "Kura123!");
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().assertUserCanOrCannotViewAccessBookingMapsAndReporting(true);


    }

    @When("^I revoke permissions$")
    public void iRevokePermissions() throws InterruptedException {
        webModel.getHomePage().logoutOfKura();
        webModel.getLoginPage().login("rajeshgurrala@coachhire.com", "Kura123!");
        webModel.getOrganisationPage().navigateToEditAuthGroups("OrgCreatedForAutoTest2", "ops");
        webModel.getOrganisationPage().editJourneyReportingBookingPermissions(false);

    }

    @Then("^the org user should not be able to access bookings, maps and reporting$")
    public void theOrgUserShouldNotBeAbleToAccessBookingsMapsAndReporting() {
        webModel.getHomePage().logoutOfKura();
        webModel.getLoginPage().login("uatkuratesting+adam@gmail.com", "Kura123!");
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().assertUserCanOrCannotViewAccessBookingMapsAndReporting(false);

    }

    @And("^I should be able to compare event time and system time$")
    public void iShouldBeAbleToCompareEventTimeAndSystemTime() {
        webModel.getJourneyPage().compareEventTimeStampAndSystemReceiveTime();
    }

    @And("^close driver app$")
    public void closeDriverApp() {
        mobileDriver.quit();
    }


    @Then("^the passenger along with the route point is deleted from the full length of the trip$")
    public void thePassengerAlongWithTheRoutePointIsDeletedFromTheFullLengthOfTheTrip() throws Exception {
        webModel.getOrganisationPage().viewTripsAndAssertRoutePointAndPassengerDeleted(tripName, "old home", true);


    }

    @When("^I delete the added route point across trip schedule$")
    public void iDeleteTheAddedRoutePointAcrossTripSchedule() throws Exception {

        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("Edit Routepoints");
        webModel.getOrganisationPage().deleteRoutePointAcrossFullTrip(3, false);
    }

    @When("^the route point where the passenger is supposed to de-board is deleted across full length of the trip$")
    public void theRoutePointWhereThePassengerIsSupposedToDeBoardIsDeletedAcrossFullLengthOfTheTrip() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("Edit Routepoints");
        webModel.getOrganisationPage().deleteRoutePointAcrossFullTrip(2, false);
    }

    @When("^the route point where the passenger is supposed to de-board is deleted for single day only$")
    public void theRoutePointWhereThePassengerIsSupposedToDeBoardIsDeletedForSingleDayOnly() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("Edit Routepoints");
        webModel.getOrganisationPage().deleteRoutePointAcrossFullTrip(2, true);
    }

    @Then("^the passenger along with the route point is deleted for that particular day$")
    public void thePassengerAlongWithTheRoutePointIsDeletedForThatParticularDay() throws Exception {
        webModel.getOrganisationPage().viewTripsAndAssertRoutePointAndPassengerDeleted(tripName, "old home", false);
    }


    @And("^i create a four point trip with multiple passengers$")
    public void iCreateAFourPointTripWithMultiplePassengers() throws InterruptedException {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        tripName = "trip_" + webModel.getUtils().tripName();
        webModel.getOrganisationPage().fourPointTripWithMultiplePassengers(tripName, "searchableByObservers");
    }

    @When("^i try adding passenger at the stop where the slot is available$")
    public void iTryAddingPassengerAtTheStopWhereTheSlotIsAvailable() throws InterruptedException {
        webModel.getOrganisationPage().addOrDeletePassengersToTripViaTripSchedule("aamin", false, "Add", true, 1, 2, "rc club bunker", "lonAir");


    }

    @Then("^the passenger addition should be successful$")
    public void thePassengerAdditionShouldBeSuccessful() {
        webModel.getOrganisationPage().assertPassengersAreAddedToTheTrip(tripName, true, 1, 2, "passengerone", "aamin");

    }

    @And("^I set the trip to be observable by observers$")
    public void iSetTheTripToBeObservableByObservers() {
        webModel.getOrganisationPage().navigateToEditRules_or_TandCs("OrgCreatedForAutoTest2", "searchableByObservers", false);
        webModel.getOrganisationPage().setSearchableRuleOnAContract("OBSERVERS");
    }

    @And("^add my passenger to the stop where there are spaces available$")
    public void addMyPassengerToTheStopWhereThereAreSpacesAvailable() throws InterruptedException, IOException {
        webModel.getKuraAPPpage().asObserver_ClickTheTripYouWouldWantThePassengerToBeAddedOnTo(tripName);
        webModel.getKuraAPPpage().observerBookingTrip_TwoRoutePoints(283, 442, 493, 634, true, false, "");
    }

    @Then("^the addition should be successful$")
    public void theAdditionShouldBeSuccessful() throws Exception {
        webModel.getKuraAPPpage().successfulAdditionOfPassengerToTrip();
        webModel.getKuraAPPpage().chooseDayOnParentApp(1);
       webModel.getKuraAPPpage().updateParentAppUntilTripIsVisible();
        webModel.getKuraAPPpage().assertParentCanSeeTrip(tripName, true);
        webModel.getUtils().sleep(3000);
        webModel.getKuraAPPpage().removePassengerOnObserverApp();
        webModel.getKuraAPPpage().chooseDayOnParentApp(2);
        webModel.getKuraAPPpage().assertParentCanSeeTrip(tripName, true);
        webModel.getUtils().sleep(3000);
        webModel.getKuraAPPpage().removePassengerOnObserverApp();
    }

    @And("^board \"([^\"]*)\" at maidenhead road with passenger search set to \"([^\"]*)\" and unexpectedBoard set to \"([^\"]*)\"$")
    public void boardAtMaidenheadRoadWithPassengerSearchSetToAndUnexpectedBoardSetTo(String passenger, String search, String unexpectedBoard) {
        webModel.getKuraAPPpage().boardPassengers(passenger, Boolean.valueOf(search), Boolean.valueOf(unexpectedBoard));

    }

    @And("^confirm the stop name \"([^\"]*)\" and address split \"([^\"]*)\"$")
    public void confirmTheStopNameAndAddressSplit(String stopName, String address) {
        webModel.getUtils().assertElementPresent_DriverApp(By.xpath("//*[@text='Stop Name:']/following-sibling::android.widget.TextView[@text='" + stopName + "']"));
        webModel.getUtils().assertElementPresent_DriverApp(By.xpath("//*[@text='Address:']/following-sibling::android.widget.TextView[@text[contains(.,'" + address + "')]]"));
    }

    @And("^set geo location for right outside the boarding point of the geo fence$")
    public void setGeoLocationForRightOutsideTheBoardingPointOfTheGeoFence() {
        webModel.getUtils().sleep(10000);
//        Westgate Retail Park
        webModel.getCommonMethodsPage().setGeoLocation(51.52313, -0.62759, 0);
//        Salt Hill Three Tuns
        webModel.getUtils().sleep(10000);
        webModel.getCommonMethodsPage().setGeoLocation(51.51866, -0.61359, 0);
        webModel.getUtils().sleep(10000);


    }

    @And("^board \"([^\"]*)\" at out side the geofence$")
    public void boardAtOutSideTheGeofence(String passenger) {
        webModel.getKuraAPPpage().navigateToPassengersPageOnApp();
        webModel.getKuraAPPpage().boardPassengers(passenger, Boolean.valueOf(false), Boolean.valueOf(false));
    }


    @Then("^I should be able to change the route points and rebook the passenger on the same trip$")
    public void iShouldBeAbleToChangeTheRoutePointsAndRebookThePassengerOnTheSameTrip() throws InterruptedException, IOException {
        webModel.getUtils().sleep(3000);
        webModel.getKuraAPPpage().removePassengerOnObserverApp();
        webModel.getKuraAPPpage().asObserver_ClickTheTripYouWouldWantThePassengerToBeAddedOnTo(tripName);
        webModel.getKuraAPPpage().observerBookingTrip_TwoRoutePoints(381, 510, 736, 765, false, false, "");
        webModel.getKuraAPPpage().successfulAdditionOfPassengerToTrip();
        webModel.getKuraAPPpage().updateParentApp();
    }

    @And("^I should be able to confirm the changes under the edit trip$")
    public void iShouldBeAbleToConfirmTheChangesUnderTheEditTrip() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("VIEW TRIP");
        webModel.getUtils().assertElementPresent(By.xpath("//p[text()='ajith']/../..//div[1]//span[text()='rc club bunker']"));
        webModel.getUtils().assertElementPresent(By.xpath("//p[text()='ajith']/../..//div[1]//span[text()='lonAir']"));

    }


    @Then("^I should get unexpected boarding notification along with passenger not boarded notification$")
    public void iShouldGetUnexpectedBoardingNotificationAlongWithPassengerNotBoardedNotification() {
        WebDriverWait wait = new WebDriverWait(mobileParent, 40);
        webModel.getKuraAPPpage().notificationMessageOnParentAppParentApp("Unexpected Boarding", true);

        try {
            webModel.getKuraAPPpage().notificationMessageOnParentAppParentApp("Service Ahead of Schedule", false);
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"OK\")"))).click();
        } catch (Exception e) {
        }
        webModel.getKuraAPPpage().notificationMessageOnParentAppParentApp("Not Boarded", false);
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().text(\"OK\")"))).click();

    }

    @And("^I should be able to see that passenger has boarded on route under journey screen$")
    public void iShouldBeAbleToSeeThatPassengerHasBoardedOnRouteUnderJourneyScreen() throws InterruptedException {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToJourneyFromOrgPage();
        webModel.getUtils().assertElementPresent(By.xpath("//td[text()='passengerfive']/..//td[text()='UNEXPECTED_PASSENGER_BOARDED_STOP']/..//td[text()='ON ROUTE']"));
    }


    @And("^I create a single day trip with stops \"([^\"]*)\" and \"([^\"]*)\", \"([^\"]*)\" and contract \"([^\"]*)\"$")
    public void iCreateASingleDayTripWithStopsAndAndContract(String fromStop, String toStop, String passenger, String contract) throws Throwable {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        tripName = "trip_" + webModel.getUtils().tripName().toLowerCase();
        webModel.getOrganisationPage().addTripForASingleDay(false, tripName, fromStop, toStop, passenger, true, contract, false, 1, 0);
    }

    @Given("^I create a trip with four stops and passenger \"([^\"]*)\" addition is set to \"([^\"]*)\" and the trip is configured to be viewable by an observer$")
    public void iCreateATripWithFourStopsAndPassengerAdditionIsSetToAndTheTripIsConfiguredToBeViewableByAnObserver(String passenger, String passengerAddition) throws Throwable {
        iAmLoggedInAsAnOperatorWithUserName("rajeshgurrala@coachhire.com");
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        tripName = "trip_" + webModel.getUtils().tripName().toLowerCase();
        webModel.getOrganisationPage().FourPointTripWithSinglePassenger("searchableByObservers", 2, passenger, Boolean.valueOf(passengerAddition), false, "my home", "old home", "rc club bunker", "lonAir");
        webModel.getOrganisationPage().navigateToEditRules_or_TandCs("OrgCreatedForAutoTest2", "searchableByObservers", false);
        webModel.getOrganisationPage().setSearchableRuleOnAContract("OBSERVERS");
    }

    @And("^I create a three year long trip schedule$")
    public void iCreateAThreeYearLongTripSchedule() throws InterruptedException {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        tripName = "trip_ThreeYearLong" + " " + webModel.getUtils().tripName().toLowerCase();
        webModel.getOrganisationPage().threeYearLongTripSchedule("searchableByObservers");

    }

    @Then("^I should be able to add a supplier for the entire trip schedule$")
    public void iShouldBeAbleToAddASupplierForTheEntireTripSchedule() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("Edit Suppliers");
        webModel.getOrganisationPage().addSupplierDetailsForTripSchedule("SupplierForAutoTest", 1, 2, "10", true, false);

    }

    @Then("^I should assert that trips are visible for days specified$")
    public void iShouldAssertThatTripsAreVisibleForDaysSpecified() throws Exception {
        webModel.getUtils().sleep(300000);
        webModel.getOrganisationPage().navigateToTripsPageAndSearch("OrgCreatedForAutoTest2", tripName);
        webModel.getUtils().clickBtn(By.xpath("//td[text()='" + tripName + "']"));
        webModel.getOrganisationPage().checkTripsCreatedForDaysSpecified();


    }

    @And("^assert that the supplier has been added for days specified$")
    public void assertThatTheSupplierHasBeenAddedForDaysSpecified() throws InterruptedException {
        webModel.getOrganisationPage().checkStuffAddedForDaysSpecified(false, false);

    }


    @And("^only the non linked user gets sent the email$")
    public void onlyTheNonLinkedUserGetsSentTheEmail() {
        webModel.getKuraAPPpage().confirmReceivingKuraMailAndDelete();


    }

    @And("^i create cognito account and link the already created observer profile to the passenger whilst creating a passenger profile with linking multiple observers set to \"([^\"]*)\"$")
    public void iCreateCognitoAccountAndLinkTheAlreadyCreatedObserverProfileToThePassengerWhilstCreatingAPassengerProfileWithLinkingMultipleObserversSetTo(String arg0) throws InterruptedException, IOException {
        webModel.getHomePage().pickOrganisationFromOrgsPage("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("5");
        webModel.getOrganisationPage().addPassengerProfile(false);
        webModel.getOrganisationPage().linkUpObserverToPassengerProfile("passenger" + randomPassengerName, "observer" + randomObserverName, Boolean.valueOf(arg0));
        webModel.getKuraAPPpage().loginToGoolgeAndDeletePreviousKuraMails();
        webModel.getOrganisationPage().createNewAccountOnKura("observer" + randomObserverName + "");
        webModel.getKuraAPPpage().loginToGoogleAppAndGetOTP();
        webModel.getOrganisationPage().enterOTPandFinishAccountCreationOnKura();
    }


    @When("^I add hundred passengers to the trip schedule$")
    public void iAddHundredPassengersToTheTripSchedule() throws InterruptedException {
        webModel.getOrganisationPage().addOrDeletePassengersToTripViaTripSchedule("passengertwo", true, "Add", true, 1, 2, "my home", "old home");

    }


    @Then("^I should be able to assert that the passenengers are added to the three year trip schedule$")
    public void iShouldBeAbleToAssertThatThePassenengersAreAddedToTheThreeYearTripSchedule() throws InterruptedException {
        webModel.getOrganisationPage().checkStuffAddedForDaysSpecified(true, false);
    }

    @And("^navigate to org page$")
    public void navigateToOrgPage() {
        webModel.getUtils().sleep(300000);
        webModel.getUtils().refreshPage();
    }

    @And("^wait for \"([^\"]*)\"$")
    public void waitFor(String arg0) {
        webModel.getUtils().sleep(Integer.valueOf(arg0));
    }


    @And("^when I add route points to full trip wait \"([^\"]*)\"$")
    public void whenIAddRoutePointsToFullTripWait(String arg0) {
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("Edit Routepoints");
        webModel.getOrganisationPage().add_Remove_RoutePointAcrossFullLengthOfTrip("rc club bunker", false, Boolean.valueOf(arg0));
    }

    @Then("^I should be able to assert that the route point is added to three year trip schedule$")
    public void iShouldBeAbleToAssertThatTheRoutePointIsAddedToThreeYearTripSchedule() throws InterruptedException {
        webModel.getOrganisationPage().checkStuffAddedForDaysSpecified(false, true);
    }

    @And("^I create a trip schedule with multiple passenger transactions at stops$")
    public void iCreateATripScheduleWithMultiplePassengerTransactionsAtStops() throws InterruptedException {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        tripName = "trip_" + webModel.getUtils().tripName();
        webModel.getOrganisationPage().fourPointTrip_singleDay(tripName, "notifications", true, "passengerone", "passengertwo", "passengerthree", "passengerfour", Boolean.valueOf(false), 90, "my home", "old home", "rc club bunker", "lonAir");
    }

    @When("^I delete that particular stop$")
    public void iDeleteThatParticularStop() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("Edit Routepoints");
        webModel.getOrganisationPage().add_Remove_RoutePointAcrossFullLengthOfTrip("old home", true, false);

    }

    @Then("^the client booking table should update accordingly$")
    public void theClientBookingTableShouldUpdateAccordingly() {
        webModel.getUtils().navigateBack();
        webModel.getUtils().navigateBack();
        webModel.getOrganisationPage().navigateToBookingsPage();
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//td[text()='" + tripName + "']/..//td[text()='REMOVE']/..//td[text()='passa']")), 2);
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//td[text()='" + tripName + "']/..//td[text()='REMOVE']/..//td[text()='passb']")), 2);
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//td[text()='" + tripName + "']/..//td[text()='REMOVE']/..//td[text()='passc']")), 2);
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//td[text()='" + tripName + "']/..//td[text()='REMOVE']/..//td[text()='passd']")), 2);
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//td[text()='" + tripName + "']/..//td[text()='REMOVE']/..//td[text()='passengertwo']")), 2);
    }

    @And("^I create a single day trip with stops \"([^\"]*)\" and \"([^\"]*)\", \"([^\"]*)\" and contract \"([^\"]*)\" and an extra stop$")
    public void iCreateASingleDayTripWithStopsAndAndContractAndAnExtraStop(String fromStop, String toStop, String passenger, String contract) throws Throwable {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        tripName = "trip_" + webModel.getUtils().tripName().toLowerCase();
        webModel.getOrganisationPage().addTripForASingleDay(true, tripName, fromStop, toStop, passenger, true, contract, false, 1, 0);
    }

    @And("^I link up the first observer with the created cognito account and provide an email address to the second observer$")
    public void iLinkUpTheFirstObserverWithTheCreatedCognitoAccountAndProvideAnEmailAddressToTheSecondObserver() throws MalformedURLException {
        webModel.getLoginPage().login("rajeshgurrala@coachhire.com", "Kura123!");
        webModel.getHomePage().pickOrganisationFromOrgsPage("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("People");
        webModel.getOrganisationPage().addEmailAfterObserverProfileIsCreated(randomObserverNameWithOutEmail);
        webModel.getUtils().navigateBack();
        webModel.getOrganisationPage().addEmailAfterObserverProfileIsCreated(randomObserverName);
        webModel.getUtils().navigateBack();
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Total", randomObserverName);
        webModel.getUtils().clickBtn(By.xpath("//span[text()='observer" + randomObserverName + "']"));
        webModel.getOrganisationPage().linkPersonProfileToCognitiveAccountOnKura(false, "uatkuratesting+" + "observer" + randomObserverName + "@gmail.com", "Kura123!", false);
        webModel.getKuraAPPpage().loginToGoolgeAndDeletePreviousKuraMails();


    }

    @And("^I link up the first observer with the created cognito account$")
    public void iLinkUpTheFirstObserverWithTheCreatedCognitoAccount() throws MalformedURLException {
        webModel.getLoginPage().login("rajeshgurrala@coachhire.com", "Kura123!");
        webModel.getHomePage().pickOrganisationFromOrgsPage("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("People");
        webModel.getOrganisationPage().addEmailAfterObserverProfileIsCreated(randomObserverName);
        webModel.getUtils().navigateBack();
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Total", randomObserverName);
        webModel.getUtils().clickBtn(By.xpath("//span[text()='observer" + randomObserverName + "']"));
        webModel.getOrganisationPage().linkPersonProfileToCognitiveAccountOnKura(false, "uatkuratesting+" + "observer" + randomObserverName + "@gmail.com", "Kura123!", false);
        webModel.getKuraAPPpage().loginToGoolgeAndDeletePreviousKuraMails();
    }

    @And("^I should be able to capture the i am here button on the jurney event screen$")
    public void iShouldBeAbleToCaptureTheIAmHereButtonOnTheJurneyEventScreen() throws InterruptedException {
        webModel.getHomePage().chooseDesiredIconFromVerticalIndex(2);
        webModel.getJourneyPage().navigateToTrip_viaUpComingTrips(tripName);
        webModel.getJourneyPage().assertIamHere();

    }


    @And("^create a client booking for the trip with \"([^\"]*)\"$")
    public void createAClientBookingForTheTripWith(String passenger) throws Exception {
        webModel.getKuraAPPpage().launchDriverApp();
        webModel.getUtils().sleep(3000);
        webModel.getCommonMethodsPage().setGeoLocation(51.52489, -0.64673, 0);
        webModel.getKuraAPPpage().loginToDriverApp("uatkuratesting+driver@gmail.com", "Kura123!");
        webModel.getKuraAPPpage().assertMobileDashBoardPageForDriver();
        webModel.getKuraAPPpage().navigateToTripOnDriverApp(tripName);
        webModel.getKuraAPPpage().assertAndChooseVehiclesFromAvailableList("AB12 QED", "yx63 ews");
        webModel.getKuraAPPpage().skipStops("my home", "old home", true);
        webModel.getKuraAPPpage().passengersOnDriverApp("Bob Brown", "Claire Cole", "Daniel Danvers", passenger);


    }

    @And("^the passenger should be removed under the trips$")
    public void thePassengerShouldBeRemovedUnderTheTrips() throws Exception {
        webModel.getOrganisationPage().navigateToTripCalendar("newOrg" + randomNumber + "", tripName);
        webModel.getOrganisationPage().assertPassengersAreDeletedAcrossTrip(tripName, "Ali Anderson", "Bob Brown", false);
    }

    @And("^I perform bulk update to disable the passenger on the trip$")
    public void iPerformBulkUpdateToDisableThePassengerOnTheTrip() throws InterruptedException, AWTException {
        webModel.getHomePage().pickOrganisationFromOrgsPage("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("6");
        webModel.getOrganisationPage().bulkUpload(System.getProperty("user.dir") + "\\Bulkupload\\bulkupdate.csv", "bulkupdate");

    }

    @And("^I add a supplier \"([^\"]*)\" and driver \"([^\"]*)\" with capacity \"([^\"]*)\"$")
    public void iAddASupplierAndDriverWithCapacity(String supplier, String driver, String capacity) throws Throwable {
        webModel.getOrganisationPage().clickEditTripAndSupplier("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().addSupplierForSingleDay(supplier, driver, "AB12 QED", capacity);
    }

    @And("^I add a supplier \"([^\"]*)\" and driver \"([^\"]*)\" with capacity \"([^\"]*)\" for randomly created org$")
    public void iAddASupplierAndDriverWithCapacityForRandomlyCreatedOrg(String supplier, String driver, String capacity) throws Throwable {
        webModel.getOrganisationPage().clickEditTripAndSupplier("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().addSupplierForSingleDay(supplier, driver, "AB12 QED", capacity);
    }


    @And("^I should capture unexpected journey notification on the kura$")
    public void iShouldCaptureUnexpectedJourneyNotificationOnTheKura() throws InterruptedException {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToJourneyFromOrgPage();
        webModel.getUtils().assertElementPresent(By.xpath("//td[text()='aad']/..//td[text()='UNEXPECTED_PASSENGER_BOARDED_JOURNEY']/..//td[text()='ON ROUTE']"));
    }


    @And("^the booking table should show manifest the changes for passenger \"([^\"]*)\"$")
    public void theBookingTableShouldShowManifestTheChangesForPassenger(String passenger) {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("Bookings");
        webModel.getUtils().assertElementPresent(By.xpath("//h6[text()='Booking Summary']"));
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//td[text()='" + tripName + "']/../td[2][text()='ADD']/following-sibling::td[text()='" + passenger + "']")), 2);
        try {
            Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//td[text()='" + tripName + "']/../td[2][text()='REMOVE']/following-sibling::td[text()='" + passenger + "']")), 2);
        } catch (Exception e) {
            webModel.getUtils().sleep(3000);
            webModel.getUtils().refreshPage();
            webModel.getUtils().sleep(3000);
            webModel.getUtils().refreshPage();
            Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//td[text()='" + tripName + "']/../td[2][text()='REMOVE']/following-sibling::td[text()='" + passenger + "']")), 2);
        }
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//td[text()='" + tripName + "']/../td[2][text()='ADD']/..//td[7][text()='my home']/..//td[8][text()='old home']")), 2);
    }

    @When("^I change the route points of the passenger$")
    public void iChangeTheRoutePointsOfThePassenger() throws Exception {
        webModel.getOrganisationPage().changeRoutePointsViaEditTrip("passengerfive", tripName);

    }

    @Then("^the changes for the single trip passenger \"([^\"]*)\" edit should be manifested under booking summary$")
    public void theChangesForTheSingleTripPassengerEditShouldBeManifestedUnderBookingSummary(String passenger) {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("Bookings");
        webModel.getUtils().assertElementPresent(By.xpath("//h6[text()='Booking Summary']"));
        webModel.getUtils().assertElementPresent(By.xpath("//td[text()='" + passenger + "']/../td[2][text()='ADD']/..//td[7][text()='my home']/..//td[8][text()='old home']"));
        webModel.getUtils().assertElementPresent(By.xpath("//td[text()='" + passenger + "']/../td[2][text()='ADD']/..//td[7][text()='rc club bunker']/..//td[8][text()='lonAir']"));
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//td[text()='" + tripName + "']/../td[2][text()='REMOVE']/following-sibling::td[text()='" + passenger + "']")), 1);
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//td[text()='" + tripName + "']/../td[2][text()='ADD']/following-sibling::td[text()='" + passenger + "']")), 2);

    }


    @Given("^I create a trip with four stops and passenger \"([^\"]*)\" is bording at the first part of the journey$")
    public void iCreateATripWithFourStopsAndPassengerIsBordingAtTheFirstPartOfTheJourney(String passenger) throws Throwable {
        iAmLoggedInAsAnOperatorWithUserName("rajeshgurrala@coachhire.com");
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        tripName = "trip_" + webModel.getUtils().tripName().toLowerCase();
        webModel.getOrganisationPage().FourPointTripWithSinglePassenger("searchableByObservers", 3, passenger, false, true, "my home", "old home", "rc club bunker", "lonAir");
        webModel.getOrganisationPage().navigateToEditRules_or_TandCs("OrgCreatedForAutoTest2", "searchableByObservers", false);
        webModel.getOrganisationPage().setSearchableRuleOnAContract("OBSERVERS");
    }

    @And("^link the observer profile to the account$")
    public void linkTheObserverProfileToTheAccount() throws MalformedURLException {
        webModel.getKuraAPPpage().loginToGoolgeAndDeletePreviousKuraMails();
        webModel.getHomePage().pickOrganisationFromOrgsPage("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("People");
        webModel.getUtils().sendTxtWithOutClearing(By.xpath("//input[@placeholder='Search']"), "mr p green");
        webModel.getUtils().clickBtn(By.xpath("//span[text()='mr p green']"));
        webModel.getOrganisationPage().linkPersonProfileToCognitiveAccountOnKura(false, "uatkuratesting+green@gmail.com", "Kura123!", false);
        webModel.getKuraAPPpage().loginToGoolgeAndDeletePreviousKuraMails();


    }

    @When("^I login as the observer i should be able to confirm that i am linked$")
    public void iLoginAsTheObserverIShouldBeAbleToConfirmThatIAmLinked() throws MalformedURLException {
        webModel.getKuraAPPpage().launchParentApp();
        webModel.getUtils().sleep(4000);
        webModel.getKuraAPPpage().loginToParentApp("uatkuratesting+green@gmail.com", "Kura123!");
        webModel.getKuraAPPpage().assertMobileDashBoardPageForParent();
        webModel.getKuraAPPpage().assertParentCanSeeTrip(tripName, true);
    }


    @And("^when i perform the bulk update$")
    public void whenIPerformTheBulkUpdate() throws InterruptedException, AWTException {
        webModel.getHomePage().logoutOfKura();
        webModel.getLoginPage().login("rajeshgurrala@coachhire.com", "Kura123!");
        webModel.getHomePage().pickOrganisationFromOrgsPage("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("6");
        webModel.getOrganisationPage().bulkUpload(System.getProperty("user.dir") + "\\Bulkupload\\bulkupdate.csv", "bulkupdate");

    }


    @When("^I navigate to the profiles page$")
    public void iNavigateToTheProfilesPage() {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("People");

    }

    @Then("^I should be able to search by email, first and last names, phone numbers and tokens$")
    public void iShouldBeAbleToSearchByEmailFirstAndLastNamesPhoneNumbersAndTokens() {
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Total", "close");
        webModel.getUtils().sleep(2000);
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//span[contains(text(),'close')]")), 3);
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Passengers", "glen");
        webModel.getUtils().sleep(2000);
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//span[contains(text(),'glen')]")), 1);
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Observers", "close");
        webModel.getUtils().sleep(2000);
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//span[contains(text(),'close')]")), 1);
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Users", "close");
        webModel.getUtils().sleep(2000);
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//span[contains(text(),'close')]")), 1);
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Total", "uatkuratesting+glenclose@gmail.com");
        webModel.getUtils().sleep(2000);
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//div[text()[contains(.,'uatkuratesting+glenclose@gmail.com')]]")), 1);
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Total", "7654321987");
        webModel.getUtils().sleep(2000);
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//div[text()[contains(.,'7654321987')]]")), 1);
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Total", "598489520");
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//div[text()[contains(.,'beth')]]")), 1);


    }

    @Then("^I should be able to update the name and search for it$")
    public void iShouldBeAbleToUpdateTheNameAndSearchForIt() throws InterruptedException {
        webModel.getOrganisationPage().updateProfileName("Total", "beth rowley");
        webModel.getUtils().navigateBack();
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Total", randomPassengerName);
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//span[contains(text(),'" + randomPassengerName + "')]")), 1);
    }

    @And("^I should be able to check email and phone links$")
    public void iShouldBeAbleToCheckEmailAndPhoneLinks() throws MalformedURLException {
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Passengers", "34323456788");
        webModel.getKuraAPPpage().navigateToGmailAppAndDeleteDrafts();
        webModel.getOrganisationPage().checkEmailLinksOnProfileLabels();
        webModel.getKuraAPPpage().updateGmailApp(true);
        webModel.getKuraAPPpage().assertMailInDraftsAndDeleteDraft("beth");
        webModel.getOrganisationPage().checkPhoneLinkOnProfileLabels();
        Assert.assertEquals(driver.getWindowHandles().size(), 2);


    }

    @When("^change the trip schedule timings$")
    public void ChangeTheTripScheduleTimings() throws InterruptedException {
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("Edit Routepoints");
        webModel.getOrganisationPage().changeTripScheduleRoutePointTimings();


    }

    @Then("^I should be able to asset the newly created trip schedule across all the days$")
    public void iShouldBeAbleToAssetTheNewlyCreatedTripScheduleAcrossAllTheDays() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("Edit Routepoints");
        webModel.getUtils().assertElementPresent(By.xpath("//h5[text()='Edit Route Points']"));
        Assert.assertTrue(webModel.getUtils().extractTextBasedOnIndex(1, By.xpath("//input[@id='arrivalDateTime_input']"), "value").contains("11:00"));
        Assert.assertTrue(webModel.getUtils().extractTextBasedOnIndex(2, By.xpath("//input[@id='arrivalDateTime_input']"), "value").contains("12:00"));
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 2, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("Edit Routepoints");
        webModel.getUtils().assertElementPresent(By.xpath("//h5[text()='Edit Route Points']"));
        Assert.assertTrue(webModel.getUtils().extractTextBasedOnIndex(1, By.xpath("//input[@id='arrivalDateTime_input']"), "value").contains("11:00"));
        Assert.assertTrue(webModel.getUtils().extractTextBasedOnIndex(2, By.xpath("//input[@id='arrivalDateTime_input']"), "value").contains("12:00"));

    }

    @Then("^I should see one observers profile linked$")
    public void iShouldSeeOneObserversProfileLinked() {
        webModel.getOrganisationPage().logoutOfKura();
        webModel.getLoginPage().login("rajeshgurrala@coachhire.com", "Kura123!");
        webModel.getHomePage().pickOrganisationFromOrgsPage("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("People");
        webModel.getUtils().assertElementPresent(By.xpath("//p[text()='Observers']/following-sibling::p/div[text()='1']"));

    }

    @And("^when i try sending re-invite on emails should be sent$")
    public void whenITrySendingReInviteOnEmailsShouldBeSent() {
        webModel.getUtils().refreshPage();
        webModel.getUtils().sleep(4000);
        webModel.getUtils().refreshPage();
        webModel.getOrganisationPage().sendInvitationForLinkUp("Observers", "Re-Invite");
        webModel.getUtils().assertElementPresent(By.xpath("//span[text()[contains(., '0 emails sent')]]"));
    }

    @And("^when i send invite only email should be sent$")
    public void whenISendInviteOnlyEmailShouldBeSent() {
        webModel.getOrganisationPage().sendInvitationForLinkUp("Observers", "Invite");
        // webModel.getUtils().assertElementPresent(By.xpath("//span[text()[contains(., '1 emails sent')]]"));
    }

    @And("^I create a single day trip for tomorrow with stops \"([^\"]*)\" and \"([^\"]*)\", \"([^\"]*)\" and contract \"([^\"]*)\"$")
    public void iCreateASingleDayTripForTomorrowWithStopsAndAndContract(String fromStop, String toStop, String passenger, String contract) throws Throwable {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        tripName = "trip_" + webModel.getUtils().tripName().toLowerCase();
        webModel.getOrganisationPage().addTripForASingleDay(false, tripName, fromStop, toStop, passenger, true, contract, false, 1, 1);
    }


    @When("^I try adding a route point for trip that is scheduled for today$")
    public void iTryAddingARoutePointForTripThatIsScheduledForToday() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("EDIT TRIP");
        webModel.getOrganisationPage().adding_deletingExtraWayPointFromEditScreen(true, "London Road");
    }

    @Then("^I should get an error message stating \"([^\"]*)\"$")
    public void iShouldGetAnErrorMessageStating(String message) {
        webModel.getUtils().assertElementPresent(By.xpath("//p[text()[contains(.,'" + message + "')]]"));

    }

    @Given("^I am logged in as an operator and i have uploaded randomly generated passenger$")
    public void iAmLoggedInAsAnOperatorAndIHaveUploadedRandomlyGeneratedPassenger() throws InterruptedException, AWTException {
        webModel.getLoginPage().login("rajeshgurrala@coachhire.com", "Kura123!");
        webModel.getCommonMethodsPage().switchToAndCloseBluePopUp();
        webModel.getHomePage().searchAndPickOrganisation("OrgCreatedForAutoTest2");
        RandomBulkUpLoadPassengerFirstName = webModel.getUtils().randomNameSevenDigits().toLowerCase();
        RandomBulkUpLoadPassengerLastName = webModel.getUtils().randomNameSevenDigits().toLowerCase();
        webModel.getUtils().writeDataToCSVFile(System.getProperty("user.dir") + "\\Bulkupload\\randomBulkUpload.csv", webModel.getUtils().randomNumber(), webModel.getUtils().randomNumber(), RandomBulkUpLoadPassengerFirstName, RandomBulkUpLoadPassengerLastName);
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("6");
        webModel.getOrganisationPage().bulkUpload(System.getProperty("user.dir") + "\\Bulkupload\\randomBulkUpload.csv", "randomBulkUpload");
    }

    @When("^I disable the passenger via bulk update$")
    public void iDisableThePassengerViaBulkUpdate() throws InterruptedException, AWTException {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("6");
        webModel.getOrganisationPage().bulkUpload(System.getProperty("user.dir") + "\\Bulkupload\\randomBulkUpdate.csv", "randomBulkUpdate");
    }

    @Then("^that passenger should be un searchable under profiles$")
    public void thatPassengerShouldBeUnSearchableUnderProfiles() {
        webModel.getUtils().sleep(15000);
        webModel.getUtils().refreshPage();
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("People");
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Passengers", RandomBulkUpLoadPassengerFirstName);
        webModel.getUtils().assertElementAbsent(By.xpath("//span[text()='" + RandomBulkUpLoadPassengerFirstName + "" + " " + "" + RandomBulkUpLoadPassengerLastName + "']"));


    }


    @And("^I should be able to search for that passenger$")
    public void iShouldBeAbleToSearchForThatPassenger() {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("People");
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Passengers", RandomBulkUpLoadPassengerFirstName);
        webModel.getUtils().assertElementPresent(By.xpath("//span[text()='" + RandomBulkUpLoadPassengerFirstName + "" + " " + "" + RandomBulkUpLoadPassengerLastName + "']"));

    }

    @And("^the passenger should be searchable in magic search$")
    public void thePassengerShouldBeSearchableInMagicSearch() {
        webModel.getHomePage().magicSearch(RandomBulkUpLoadPassengerFirstName);
        webModel.getUtils().assertElementPresent(By.xpath("//span[text()='" + RandomBulkUpLoadPassengerFirstName + "" + " " + "" + RandomBulkUpLoadPassengerLastName + "']"));

    }

    @Given("^I am logged in as an org admin with userName \"([^\"]*)\"$")
    public void iAmLoggedInAsAnOrgAdminWithUserName(String userName) {
        webModel.getLoginPage().assertLoginPage();
        webModel.getLoginPage().login(userName, "Kura123!");
        webModel.getCommonMethodsPage().switchToAndCloseBluePopUp();
    }

    @And("^I should be able to download the summary in csv file$")
    public void iShouldBeAbleToDownloadTheSummaryInCsvFile() throws IOException {
        webModel.getUtils().deleteAllFilesWithSpecifiedExtension(System.getProperty("user.home") + "\\Downloads", ".csv");
        webModel.getOrganisationPage().downloadLatestBookingSummaryReport();
        webModel.getUtils().sleep(4000);
        webModel.getUtils().readFileAndAssertContent(System.getProperty("user.home") + "\\Downloads\\" + webModel.getUtils().getCSVFileName(System.getProperty("user.home") + "\\Downloads"), "passengerone");
        webModel.getUtils().deleteAllFilesWithSpecifiedExtension(System.getProperty("user.home") + "\\Downloads", ".csv");


    }


    @When("^i navigate to edit trip of any day and provide supplier details$")
    public void iNavigateToEditTripOfAnyDayAndProvideSupplierDetails() throws Exception {
        webModel.getOrganisationPage().clickEditTripAndSupplier("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().addSupplierForSingleDay("SupplierForAutoTest", "driverone", "AB12 QED", "5");

    }

    @Then("^the error message should say \"([^\"]*)\"$")
    public void theErrorMessageShouldSay(String message) throws Throwable {
        webModel.getOrganisationPage().errorMessageOnSupplierPage(message);

    }

    @And("^i provide a second supplier with over lapping dates$")
    public void iProvideASecondSupplierWithOverLappingDates() throws InterruptedException {
        webModel.getHomePage().searchAndPickOrganisation("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("Trips");
        webModel.getUtils().sleep(1000);
        webModel.getUtils().sendText(By.xpath("//input[@type='text']"), tripName);
        webModel.getUtils().clickBtn(By.xpath("//td[text()='" + tripName + "']"));
        webModel.getUtils().clickOnDesiredNumberOfLocator(1, By.xpath("//p[text()='" + tripName + "']"));
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("Edit Suppliers");
        webModel.getUtils().refreshPage();
        webModel.getOrganisationPage().addSecondSupplier(1, 2);
    }

    @When("^I create a passenger profile with a unique reference number$")
    public void iCreateAPassengerProfileWithAUniqueReferenceNumber() throws InterruptedException, AWTException {
        webModel.getHomePage().pickOrganisationFromOrgsPage("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("5");
        webModel.getOrganisationPage().addPassengerProfile(true);

    }

    @And("^i perform bulk upload with a passenger profile matching the earlier created reference number$")
    public void iPerformBulkUploadWithAPassengerProfileMatchingTheEarlierCreatedReferenceNumber() throws InterruptedException, AWTException {
        webModel.getHomePage().searchAndPickOrganisation("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("6");
        webModel.getOrganisationPage().bulkUpload(System.getProperty("user.dir") + "\\Bulkupload\\bulkupload.csv", "bulkupload");
    }

    @Then("^the passenger profile should not be duplicated but only updated$")
    public void thePassengerProfileShouldNotBeDuplicatedButOnlyUpdated() {
        webModel.getHomePage().pickOrganisationFromOrgsPage("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("People");
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Total", "Fred");
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//span[contains(text(),'Fred')]")), 0);
        webModel.getOrganisationPage().searchPersonProfileUnderDesiredRole("Total", "passenger" + randomPassengerName);
        webModel.getUtils().sleep(1000);
        Assert.assertEquals(webModel.getUtils().getElementCount(By.xpath("//span[contains(text(),'" + randomPassengerName + "')]")), 1);
        webModel.getUtils().clickBtn(By.xpath("//span[text()='passenger" + randomPassengerName + "']"));
        webModel.getOrganisationPage().assertPassengerProfileDetails("598693008", "5803", "mr o franks", "mrs c l franks");

    }


    @When("^I should only be able to add the passenger to the trip only after accepting T&Cs$")
    public void iShouldOnlyBeAbleToAddThePassengerToTheTripOnlyAfterAcceptingTCs() throws Exception {
        webModel.getKuraAPPpage().asObserver_ClickTheTripYouWouldWantThePassengerToBeAddedOnTo(tripName);
        webModel.getKuraAPPpage().observerBookingTrip_TwoRoutePoints(344, 506, 422, 635, false, true, "Dummy PDF file");
        webModel.getKuraAPPpage().successfulAdditionOfPassengerToTrip();
        webModel.getKuraAPPpage().updateParentAppUntilTripIsVisible();
        webModel.getKuraAPPpage().removePassengerOnObserverApp();
    }

    @And("^I logout and login with operator credentials$")
    public void iLogoutAndLoginWithOperatorCredentials() {
        webModel.getOrganisationPage().logoutOfKura();
        webModel.getLoginPage().login("rajeshgurrala@coachhire.com", "Kura123!");
    }

    @And("^create a client booking for the trip via setting the contract to be observable by \"([^\"]*)\"$")
    public void createAClientBookingForTheTripViaSettingTheContractToBeObservableBy(String observable) throws Throwable {
        webModel.getOrganisationPage().assertOrganisationPage();
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("3");
        webModel.getOrganisationPage().assertActionPlanCreationPage();
        webModel.getOrganisationPage().createNewActionPlan();
        webModel.getUtils().clickLocatorBasedOnIndex(1, By.xpath("//div[@class='MuiListItemIcon-root']"));
        webModel.getHomePage().searchAndPickOrganisation("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("1");
        webModel.getOrganisationPage().createContracts("searchableByObservers", observable);
    }

    @And("^once accepted the observer should not be asked to accept the TandC agreement again$")
    public void onceAcceptedTheObserverShouldNotBeAskedToAcceptTheTandCAgreementAgain() throws Exception {
        webModel.getKuraAPPpage().launchParentApp();
        webModel.getKuraAPPpage().loginToParentApp("uatkuratesting+observer" + randomObserverName + "@gmail.com", "Kura123!");
        webModel.getKuraAPPpage().asObserver_ClickTheTripYouWouldWantThePassengerToBeAddedOnTo(tripName);
        webModel.getKuraAPPpage().observerBookingTrip_TwoRoutePoints(344, 506, 422, 635, false, false, "");
        webModel.getKuraAPPpage().successfulAdditionOfPassengerToTrip();
        webModel.getKuraAPPpage().updateParentAppUntilTripIsVisible();
        webModel.getKuraAPPpage().removePassengerOnObserverApp();


    }

    @And("^add T&Cs for the contract with pdf \"([^\"]*)\"$")
    public void addTCsForTheContractWithPdf(String file) throws Throwable {
        webModel.getOrganisationPage().navigateToEditRules_or_TandCs("newOrg" + randomNumber + "", "searchableByObservers", true);
        webModel.getOrganisationPage().uploadTandCsAsAPDFAndAssertFileUpload(System.getProperty("user.dir") + "\\Bulkupload\\" + file + ".pdf", false, "Dummy PDF file");
    }


    @And("^once the passenger is removed from the trip and the T&Cs of the very contract used to create the trip earlier are updated with pdf \"([^\"]*)\"$")
    public void onceThePassengerIsRemovedFromTheTripAndTheTCsOfTheVeryContractUsedToCreateTheTripEarlierAreUpdatedWithPdf(String file) throws Exception {
        webModel.getOrganisationPage().navigateToEditRules_or_TandCs("newOrg" + randomNumber + "", "searchableByObservers", true);
        webModel.getOrganisationPage().uploadTandCsAsAPDFAndAssertFileUpload(System.getProperty("user.dir") + "\\Bulkupload\\" + file + ".pdf", true, "Dummy PDF download");

    }

    @Then("^the agreement kicks in again$")
    public void theAgreementKicksInAgain() throws Exception {
        webModel.getKuraAPPpage().launchParentApp();
        webModel.getKuraAPPpage().loginToParentApp("uatkuratesting+observer" + randomObserverName + "@gmail.com", "Kura123!");
        webModel.getKuraAPPpage().asObserver_ClickTheTripYouWouldWantThePassengerToBeAddedOnTo(tripName);
        webModel.getKuraAPPpage().observerBookingTrip_TwoRoutePoints(344, 506, 422, 635, false, true, "Dummy PDF download");
        webModel.getKuraAPPpage().successfulAdditionOfPassengerToTrip();
       webModel.getKuraAPPpage().updateParentAppUntilTripIsVisible();
        webModel.getKuraAPPpage().assertParentCanSeeTrip(tripName, true);
    }

    @And("^I should be able to add a trip with passenger addition set to \"([^\"]*)\"$")
    public void iShouldBeAbleToAddATripWithPassengerAdditionSetTo(String vale) throws Throwable {
        webModel.getHomePage().pickOrganisationFromOrgsPage("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        webModel.getOrganisationPage().createAPrivateWayPoint("SL1 6EP", "first waypoint");
        webModel.getOrganisationPage().createAPrivateWayPoint("SL1 1HQ", "second waypoint");
        webModel.getOrganisationPage().createAPrivateWayPoint("SL3 9AB", "third waypoint");
        webModel.getOrganisationPage().createAPrivateWayPoint("SL3 0HU", "fourth waypoint");
        webModel.getUtils().clickLocatorBasedOnIndex(1, By.xpath("//div[@class='MuiListItemIcon-root']"));
        webModel.getHomePage().searchAndPickOrganisation("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        tripName = "trip_" + webModel.getUtils().tripName().toLowerCase();
        webModel.getOrganisationPage().FourPointTripWithSinglePassenger("searchableByObservers", 2, "passenger" + randomPassengerName, Boolean.valueOf(vale), false, "first waypoint", "second waypoint", "third waypoint", "fourth waypoint");

    }

    @And("^open the passenger board page$")
    public void openThePassengerBoardPage() {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Passengers\")"))).click();
    }

    @And("^advance to stop$")
    public void advanceToStop() {
        webModel.getKuraAPPpage().advanceToStop();
    }


    @Given("^I am logged in as an operator and i navigate to the booking summary table of a particular organisation$")
    public void iAmLoggedInAsAnOperatorAndINavigateToTheBookingSummaryTableOfAParticularOrganisation() {
        webModel.getLoginPage().assertLoginPage();
        webModel.getLoginPage().login("Rajeshgurrala@coachhire.com", "Kura123!");
        webModel.getCommonMethodsPage().switchToAndCloseBluePopUp();
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("Bookings");
        webModel.getUtils().assertElementPresent(By.xpath("//h6[text()='Booking Summary']"));

    }

    @Then("^I should be export the data as per the week of my choice$")
    public void iShouldBeExportTheDataAsPerTheWeekOfMyChoice() throws IOException {
        webModel.getOrganisationPage().downloadLatestBookingSummaryReport();
        webModel.getUtils().sleep(2000);
        webModel.getUtils().readFileAndAssertContent(System.getProperty("user.home") + "\\Downloads\\" + webModel.getUtils().getCSVFileName(System.getProperty("user.home") + "\\Downloads"), "KURA OPS");
        webModel.getUtils().deleteAllFilesWithSpecifiedExtension(System.getProperty("user.home") + "\\Downloads", ".csv");
    }

    @And("^create a trip to be able to board the passenger before the geo fence$")
    public void createATripToBeAbleToBoardThePassengerBeforeTheGeoFence() throws InterruptedException {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        tripName = "trip_" + webModel.getUtils().tripName().toLowerCase();
        webModel.getOrganisationPage().addTripForASingleDay(true, tripName, "my home", "old home", "passengerfive", false, "notifications", false, 1, 0);
    }

    @And("^I should be able to capture the early scheduled notification on parent app$")
    public void iShouldBeAbleToCaptureTheEarlyScheduledNotificationOnParentApp() {
        webModel.getKuraAPPpage().notificationMessageOnParentAppParentApp("Service Ahead of Schedule", false);
    }

    @And("^I navigate from passengers page to journey page on the app$")
    public void iNavigateFromPassengersPageToJourneyPageOnTheApp() {
        webModel.getKuraAPPpage().navigatingFromPassengerPageToJourneyPage();
    }

    @And("^I should not receive passenger not boarded notification on parent app$")
    public void iShouldNotReceivePassengerNotBoardedNotificationOnParentApp() {
        Assert.assertFalse(webModel.getUtils().checkIfElementIsDisplayed_ParentApp(new MobileBy.ByAndroidUIAutomator("new UiSelector().textContains(\"Not Boarded\")")));
    }

    @Then("^I should be able to view the trip on the parent app$")
    public void iShouldBeAbleToViewTheTripOnTheParentApp() throws MalformedURLException {
        webModel.getKuraAPPpage().launchParentApp();
        webModel.getUtils().sleep(4000);
        webModel.getKuraAPPpage().loginToParentApp("uatkuratesting+furio@gmail.com", "Kura123!");
        webModel.getKuraAPPpage().assertMobileDashBoardPageForParent();
        webModel.getKuraAPPpage().assertObserverCanORCannotBookTrip(tripName, true);

    }

    @And("^when i flick the contract to be observable by none$")
    public void whenIFlickTheContractToBeObservableByNone() {
        webModel.getOrganisationPage().navigateToEditRules_or_TandCs("OrgCreatedForAutoTest2", "searchableByObservers", false);
        webModel.getOrganisationPage().setSearchableRuleOnAContract("NONE");

    }

    @Then("^the trip should vanish under parent app$")
    public void theTripShouldVanishUnderParentApp() throws MalformedURLException {
        webModel.getKuraAPPpage().launchParentApp();
        webModel.getKuraAPPpage().loginToParentApp("uatkuratesting+furio@gmail.com", "Kura123!");
        webModel.getKuraAPPpage().assertMobileDashBoardPageForParent();
        webModel.getKuraAPPpage().updateParentApp();
        webModel.getKuraAPPpage().assertObserverCanORCannotBookTrip(tripName, false);
    }

    @And("^if i were to create a trip whilst the contract is set to be observable by none and then flick it back to to observable by observers$")
    public void ifIWereToCreateATripWhilstTheContractIsSetToBeObservableByNoneAndThenFlickItBackToToObservableByObservers() throws InterruptedException {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        secondTripName = "trip_" + webModel.getUtils().tripName();
        webModel.getOrganisationPage().fourPointTripWithMultiplePassengers(secondTripName, "searchableByObservers");
        webModel.getOrganisationPage().navigateToEditRules_or_TandCs("OrgCreatedForAutoTest2", "searchableByObservers", false);
        webModel.getOrganisationPage().setSearchableRuleOnAContract("OBSERVERS");
    }

    @Then("^I should be able to see both the trips$")
    public void iShouldBeAbleToSeeBothTheTrips() throws MalformedURLException {
        webModel.getKuraAPPpage().launchParentApp();
        webModel.getUtils().sleep(5000);
        webModel.getKuraAPPpage().loginToParentApp("uatkuratesting+furio@gmail.com", "Kura123!");
        webModel.getKuraAPPpage().assertMobileDashBoardPageForParent();
        webModel.getKuraAPPpage().updateParentApp();
        webModel.getKuraAPPpage().assertObserverCanORCannotBookTrip(tripName, true);
        webModel.getKuraAPPpage().launchParentApp();
        webModel.getKuraAPPpage().assertMobileDashBoardPageForParent();
        webModel.getKuraAPPpage().assertObserverCanORCannotBookTrip(secondTripName, true);
    }


    @When("^i perform bulk upload and create a trip with passenger \"([^\"]*)\" and time (\\d+)$")
    public void iPerformBulkUploadAndCreateATripWithPassengerAndTime(String passenger, int time) throws Throwable {
        webModel.getHomePage().searchAndPickOrganisation("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().hoverOverPlusAndClickDesiredOption_OrganisationsPage("6");
        webModel.getOrganisationPage().bulkUpload(System.getProperty("user.dir") + "\\Bulkupload\\bulkupload.csv", "bulkupload");
        webModel.getHomePage().pickOrganisationFromOrgsPage("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        webModel.getOrganisationPage().createAPrivateWayPoint("SL1 6EP", "first waypoint");
        webModel.getOrganisationPage().createAPrivateWayPoint("SL1 1HQ", "second waypoint");
        webModel.getOrganisationPage().createAPrivateWayPoint("SL3 9AB", "third waypoint");
        webModel.getOrganisationPage().createAPrivateWayPoint("SL3 0HU", "fourth waypoint");
        webModel.getHomePage().pickOrganisationFromOrgsPage("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        tripName = "trip_" + webModel.getUtils().tripName();
        webModel.getOrganisationPage().fourPointTrip_singleDay(tripName, "searchableByObservers", false, passenger, "Bob Brown", "Claire Cole", "Daniel Danvers", false, time, "first waypoint", "second waypoint", "third waypoint", "fourth waypoint");

    }

    @Given("^a trip has already been created that is scheduled for less then (\\d+) hours from the time of creation$")
    public void aTripHasAlreadyBeenCreatedThatIsScheduledForLessThenHoursFromTheTimeOfCreation(int arg0) throws InterruptedException {
        iAmLoggedInAsAnOperatorWithUserName("rajeshgurrala@coachhire.com");
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        tripName = "trip_" + webModel.getUtils().tripName().toLowerCase();
        webModel.getOrganisationPage().FourPointTripWithSinglePassenger("searchableByObservers", 1, "ajith", false, false, "my home", "old home", "rc club bunker", "lonAir");
        webModel.getOrganisationPage().navigateToEditRules_or_TandCs("OrgCreatedForAutoTest2", "searchableByObservers", false);
        webModel.getOrganisationPage().setSearchableRuleOnAContract("OBSERVERS");
    }


    @Then("^an error message stating \"([^\"]*)\" would be displayed$")
    public void anErrorMessageStatingWouldBeDisplayed(String message) {
        webModel.getKuraAPPpage().errorMessageOnParentApp(message);
        mobileParent.quit();


    }

    @And("^I create a trip with four stops with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" on board with onlyOnePassengerOnBoardAtAnInstance set to \"([^\"]*)\" and time to trip (\\d+)$")
    public void iCreateATripWithFourStopsWithAndOnBoardWithOnlyOnePassengerOnBoardAtAnInstanceSetToAndTimeToTrip(String passenger1, String passenger2, String passenger3, String passenger4, String onlyOnePassengerOnBoardAtAnInstance, int time) throws Throwable {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        tripName = "trip_" + webModel.getUtils().tripName();
        webModel.getOrganisationPage().fourPointTrip_singleDay(tripName, "notifications", false, passenger1, passenger2, passenger3, passenger4, Boolean.valueOf(onlyOnePassengerOnBoardAtAnInstance), time, "my home", "old home", "rc club bunker", "lonAir");
    }

    @Given("^I am logged in as an operator and a trip skip dates set to \"([^\"]*)\" has been recently created with passenger \"([^\"]*)\"$")
    public void iAmLoggedInAsAnOperatorAndATripSkipDatesSetToHasBeenRecentlyCreatedWithPassenger(String skipDates, String passenger) throws Throwable {
        webModel.getLoginPage().assertLoginPage();
        webModel.getLoginPage().login("rajeshgurrala@coachhire.com", "Kura123!");
        webModel.getCommonMethodsPage().switchToAndCloseBluePopUp();
        webModel.getHomePage().searchAndPickOrganisation("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        webModel.getOrganisationPage().assertAddTripPage();
        tripName = "trip_" + webModel.getUtils().tripName().toLowerCase();
        webModel.getOrganisationPage().addTrip(tripName, "searchableByObservers", true, passenger, Boolean.valueOf(skipDates));
    }

    @Given("^I am logged in as an operator and a trip schedule starting \"([^\"]*)\" minutes ahead from today has been recently created$")
    public void iAmLoggedInAsAnOperatorAndATripScheduleStartingMinutesAheadFromTodayHasBeenRecentlyCreated(String time) throws Throwable {
        webModel.getLoginPage().assertLoginPage();
        webModel.getLoginPage().login("rajeshgurrala@coachhire.com", "Kura123!");
        webModel.getHomePage().searchAndPickOrganisation("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        webModel.getOrganisationPage().assertAddTripPage();
        tripName = "trip_" + webModel.getUtils().tripName();
        webModel.getOrganisationPage().tripScheduleStartingFromToday("searchableByObservers", tripName, "passengerone", time);
    }

    @When("^I navigate to the modify trip tab of tomorrow$")
    public void iNavigateToTheModifyTripTabOfTomorrow() throws Exception {
        webModel.getOrganisationPage().navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 2, tripName);
    }

    @And("^I try adding supplier details for entire duration of trip$")
    public void iTryAddingSupplierDetailsForEntireDurationOfTrip() throws InterruptedException {
        webModel.getOrganisationPage().clickViewOREditTripOnSummaryDropDown("Edit Suppliers");
        webModel.getOrganisationPage().addSupplierDetailsForTripSchedule("SupplierForAutoTest", 0, 1, "10", false, true);
    }

    @And("^I should be able to prove that the created way point is specific to this particular org only$")
    public void iShouldBeAbleToProveThatTheCreatedWayPointIsSpecificToThisParticularOrgOnly() throws InterruptedException {
        webModel.getHomePage().pickOrganisationFromOrgsPage("OrgCreatedForAutoTest2");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        webModel.getUtils().sendText(By.xpath("//p[text()='Search for a location']/..//input"), "first wayPoint");
        webModel.getUtils().assertElementAbsent(By.xpath("//p[text()='first wayPoint']"));
    }


    @Then("^I should be able to see way point \"([^\"]*)\"$")
    public void iShouldBeAbleToSeeWayPoint(String wayPoint) throws Throwable {
        webModel.getUtils().refreshPage();
        webModel.getUtils().sleep(1000);
        webModel.getUtils().clickBtn(By.xpath("//div[@type='text']"));
        webModel.getUtils().sendText(By.xpath("//p[text()='Search for a location']/..//input"), wayPoint);
        webModel.getUtils().clickBtn(By.xpath("//p[text()='" + wayPoint + "']"));
        webModel.getUtils().clickBtn(By.xpath("//span[text()='ADD']"));
        webModel.getUtils().assertElementPresent(By.xpath("//span[text()='" + wayPoint + "']"));
    }


    @When("^I create a private way point \"([^\"]*)\" under postcode \"([^\"]*)\"$")
    public void iCreateAPrivateWayPointUnderPostcode(String wayPoint, String postCode) throws Throwable {
        webModel.getHomePage().pickOrganisationFromOrgsPage("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().navigateToCreateTripsPageFromOrgPage();
        webModel.getOrganisationPage().createAPrivateWayPoint(postCode, wayPoint);
    }

    @Test
    public void testdatesOnApp() throws MalformedURLException {
        webModel.getKuraAPPpage().launchParentApp();
        webModel.getKuraAPPpage().loginToParentApp("uatkuratesting+furio@gmail.com", "Kura123!");
        webModel.getKuraAPPpage().chooseDayOnParentApp(1);
    }

    @Then("^the driver should be able to see the private way points and the removed passenger, should not appear under list of passengers on driver app$")
    public void theDriverShouldBeAbleToSeeThePrivateWayPointsAndTheRemovedPassengerShouldNotAppearUnderListOfPassengersOnDriverApp() throws Exception {
        webModel.getKuraAPPpage().launchDriverApp();
        webModel.getUtils().sleep(3000);
        webModel.getKuraAPPpage().loginToDriverApp("uatkuratesting+driver@gmail.com", "Kura123!");
        webModel.getKuraAPPpage().assertMobileDashBoardPageForDriver();
        webModel.getKuraAPPpage().navigateToTripOnDriverApp(tripName);
        webModel.getKuraAPPpage().assertAndChooseVehiclesFromAvailableList("AB12 QED", "yx63 ews");
        webModel.getKuraAPPpage().skipStops("first waypoint", "second waypoint", true);
        webModel.getKuraAPPpage().assertDriverCanSeePrivateWaypointsOnJourneyScreen();
        webModel.getKuraAPPpage().navigateToPassengersPageOnApp();
        webModel.getKuraAPPpage().passengersOnDriverApp("Bob Brown", "Claire Cole", "Daniel Danvers", "Ali Anderson");
    }

    @When("^I navigate to admin auth group and try editing it$")
    public void iNavigateToAdminAuthGroupAndTryEditingIt() {
        webModel.getHomePage().pickOrganisationFromOrgsPage("newOrg" + randomNumber + "");
        webModel.getOrganisationPage().chooseDesiredOption_OrganisationPage("Auth Groups");
        webModel.getOrganisationPage().failedToEditAutoGroup("Admins");
    }

    @Then("^I should get an error message on edit auth group page stating \"([^\"]*)\"$")
    public void iShouldGetAnErrorMessageOnEditAuthGroupPageStating(String message) throws Throwable {
        String text = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 MuiTypography-colorError MuiTypography-alignCenter']")).getText();
        Assert.assertTrue(text.contains(message));
    }


    @And("^I link up (\\d+) person profiles from (\\d+) different orgs via common cognito account$")
    public void iLinkUpPersonProfilesFromDifferentOrgsViaCommonCognitoAccount(int arg0, int arg1) throws MalformedURLException {
        webModel.getOrganisationPage().breakProfileLink("OrgCreatedForAutoTest", "eva");
        webModel.getOrganisationPage().breakProfileLink("OrgCreatedForAutoTest2", "eva");
        webModel.getOrganisationPage().navigateToPersonProfile("OrgCreatedForAutoTest", "eva");
        webModel.getOrganisationPage().linkPersonProfileToCognitiveAccountOnKura(true, "uatkuratesting+eva@gmail.com", "Kura123!", false);
        webModel.getHomePage().logoutOfKura();
        webModel.getLoginPage().login("rajeshgurrala@coachhire.com", "Kura123!");
        webModel.getOrganisationPage().navigateToPersonProfile("OrgCreatedForAutoTest2", "eva");
        webModel.getOrganisationPage().linkPersonProfileToCognitiveAccountOnKura(true, "uatkuratesting+eva@gmail.com", "Kura123!", true);
        webModel.getKuraAPPpage().loginToGoolgeAndDeletePreviousKuraMails();
        webModel.getHomePage().logoutOfKura();


    }

    @When("^I login as that person profile$")
    public void iLoginAsThatPersonProfile() {
        webModel.getLoginPage().login("uatkuratesting+eva@gmail.com", "Kura123!");
    }

    @Then("^I should be able to view the organisations the account is linked to$")
    public void iShouldBeAbleToViewTheOrganisationsTheAccountIsLinkedTo() {
        webModel.getHomePage().chooseDesiredIconFromVerticalIndex(3);
        webModel.getUtils().assertElementPresent(By.xpath("//a[text()='OrgCreatedForAutoTest2']"));
        webModel.getUtils().assertElementPresent(By.xpath("//a[text()='OrgCreatedForAutoTest']"));

    }

}