package Pages;

import Support.BaseClass;
import Support.ElementUtils;


import org.junit.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static Pages.HomePage.randomNumber;
import static Pages.KuraAPPpage.otp;
import static Support.BaseClass.driver;
import static Support.ElementUtils.updatedTime;


public class OrganisationPage {
    public static String tripName;
    public static String secondTripName;
    public static String randomPassengerName;
    public static String randomObserverName;
    public static String randomObserverNameWithOutEmail;
    public static String linkCode;
    public static String actionPlan;
    public static String[] OTPpin;
    public static String vehicleReg;
    ElementUtils utils = new ElementUtils();
    CommonMethodsPage commonMethodsPage = new CommonMethodsPage();
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    public void assertOrganisationPage() {
        utils.assertElementPresent(By.xpath("//h5[text()='View Organisation']"));
    }

    public void hoverOverPlusAndClickDesiredOption_OrganisationsPage(String option) throws InterruptedException {
        utils.sleep(2000);
        utils.hoverOverElement(By.xpath("//span[@class='MuiSpeedDialIcon-root']"));
        utils.sleep(1000);
        utils.actionsClick(By.xpath("//div[@id='Addanentity-actions']//button[" + option + "]"));
    }


    public void assertAddAuthGroupPage() {
        utils.assertElementPresent(By.xpath("//h5[text()='Please enter the details of the Authorisation Group']"));
    }

    public void addAuthGroup(String authGroupName, String orgName, boolean editTrip) throws InterruptedException {
        commonMethodsPage.clickAdd();
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), authGroupName);
        utils.clickBtn(By.xpath("//div[@id='select-Organisation']"));
        utils.clickBtn(By.xpath("//li[text()='newOrg" + randomNumber + "']"));
        utils.clickOnDesiredNumberOfLocator(3, By.xpath("//span[@class='MuiIconButton-label']"));
        utils.makeSureBoxIsChecked(By.xpath("//input[@value='MAGIC_SEARCH']"), By.xpath("//input[@value='MAGIC_SEARCH']"));
        utils.makeSureBoxIsChecked(By.xpath("//input[@value='VIEW_ORGANISATION']"), By.xpath("//input[@value='VIEW_ORGANISATION']"));
        utils.makeSureBoxIsChecked(By.xpath("//input[@value='VIEW_AUTHGROUP']"), By.xpath("//input[@value='VIEW_AUTHGROUP']"));
        utils.makeSureBoxIsChecked(By.xpath("//input[@value='VIEW_PERSONPROFILE']"), By.xpath("//input[@value='VIEW_PERSONPROFILE']"));
        utils.makeSureBoxIsChecked(By.xpath("//input[@value='VIEW_CLIENTBOOKING']"), By.xpath("//input[@value='VIEW_CLIENTBOOKING']"));
        utils.makeSureBoxIsChecked(By.xpath("//input[@value='VIEW_VEHICLE']"), By.xpath("//input[@value='VIEW_VEHICLE']"));
        utils.makeSureBoxIsChecked(By.xpath("//input[@value='VIEW_ACTIONPLAN']"), By.xpath("//input[@value='VIEW_ACTIONPLAN']"));
        utils.makeSureBoxIsChecked(By.xpath("//input[@value='VIEW_TRIP']"), By.xpath("//input[@value='VIEW_TRIP']"));
        if (editTrip) {
            utils.makeSureBoxIsChecked(By.xpath("//input[@value='EDIT_TRIP']"), By.xpath("//input[@value='EDIT_TRIP']"));
        }
        utils.makeSureBoxIsChecked(By.xpath("//input[@value='VIEW_PERSONPROFILE']"), By.xpath("//input[@value='VIEW_PERSONPROFILE']"));
        utils.clickBtn(By.xpath("//div[@id='select-Organisation']"));
        utils.sleep(1000);
        utils.clickBtn(By.xpath("//li[text()='" + orgName + "']"));
        commonMethodsPage.clickSubmit();
    }

    public void createDriverAuthGroup() throws InterruptedException {
        commonMethodsPage.clickAdd();
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), "driver");
        utils.clickBtn(By.xpath("//div[@id='select-Organisation']"));
        utils.clickBtn(By.xpath("//li[text()='newSupplier" + randomNumber + "']"));
        utils.clickOnDesiredNumberOfLocator(3, By.xpath("//span[@class='MuiIconButton-label']"));
        utils.makeSureBoxIsChecked(By.xpath("//input[@value='MAGIC_SEARCH']"), By.xpath("//input[@value='VIEW_TRIP']"));
        utils.makeSureBoxIsChecked(By.xpath("//input[@value='VIEW_ORGANISATION']"), By.xpath("//input[@value='VIEW_VEHICLE']"));
        utils.makeSureBoxIsChecked(By.xpath("//input[@value='VIEW_AUTHGROUP']"), By.xpath("//input[@value='VIEW_DRIVER_TRIP']"));
        utils.makeSureBoxIsChecked(By.xpath("//input[@value='VIEW_PERSONPROFILE']"), By.xpath("//input[@value='VIEW_JOURNEY_MAP_SUB']"));
        utils.makeSureBoxIsChecked(By.xpath("//input[@value='VIEW_CLIENTBOOKING']"), By.xpath("//input[@value='VIEW_JOURNEY_REPORTING_MAP']"));
        utils.makeSureBoxIsChecked(By.xpath("//input[@value='VIEW_VEHICLE']"), By.xpath("//input[@value='VIEW_BOOKING_SUMMARY']"));
        commonMethodsPage.clickSubmit();

    }


    public void assertAuthGroup(String authGroupName) {
        chooseDesiredOption_OrganisationPage("Auth Groups");
        utils.assertElementPresent(By.xpath("//a[text()='" + authGroupName + "']"));
    }

    public void editAuthGroup(String authGroupName) throws InterruptedException {
        utils.clickBtn(By.xpath("//a[text()='" + authGroupName + "']"));
        commonMethodsPage.clickEdit(false);
        utils.makeSureBoxIsUnChecked(By.xpath("//input[@value='MAGIC_SEARCH']"), By.xpath("//input[@value='MAGIC_SEARCH']"));
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), "updated" + authGroupName);
        commonMethodsPage.clickSubmit();
        utils.assertUnchecked(By.xpath("//input[@value='MAGIC_SEARCH']"));
        utils.assertElementPresent(By.xpath("//input[@value='updated" + authGroupName + "']"));
    }

    public void failedToEditAutoGroup(String autoGroup) {
        utils.clickBtn(By.xpath("//a[text()='" + autoGroup + "']"));
        commonMethodsPage.clickEdit(false);
        utils.sleep(1000);
        utils.clickOnDesiredNumberOfLocator(3, By.xpath("//span[@class='MuiIconButton-label']"));
        utils.makeSureBoxIsUnChecked(By.xpath("//input[@value='MAGIC_SEARCH']"), By.xpath("//input[@value='MAGIC_SEARCH']"));
        commonMethodsPage.clickSubmit();

    }

    public void assertAddPersonProfilePage() {
        utils.assertElementPresent(By.xpath("//h5[text()='Please enter the details of the person'] "));
    }

    public void addPassengerProfile(boolean uniqueReferenceAndToken) throws InterruptedException {
        randomPassengerName = utils.randomName().toLowerCase();
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), "passenger" + randomPassengerName);
        utils.makeSureBoxIsChecked(By.xpath("//input[@type='checkbox']"), By.xpath("//input[@type='checkbox']"));
        if (uniqueReferenceAndToken) {
            utils.sendText(By.xpath("//input[@id='importId']"), "5803");
            utils.sendText(By.xpath("//input[@id='tokens']"), "9876543");
            utils.sleep(1000);
            utils.hitEnter(By.xpath("//input[@id='tokens']"));
            utils.sleep(1000);
        }
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        commonMethodsPage.clickSubmit();
    }

    public void assertPassengerProfileDetails(String token, String externalID, String father, String mother) {
        utils.assertElementPresent(By.xpath("//input[@value='" + token + "']"));
        utils.assertElementPresent(By.xpath("//input[@value='" + externalID + "']"));
        utils.assertElementPresent(By.xpath("//span[text()='" + father + "']"));
        utils.assertElementPresent(By.xpath("//span[text()='" + mother + "']"));
    }

    public void linkUpObserverToPassengerProfile(String passengerProfile, String observerProfile, boolean linkSecondObserver) throws InterruptedException {
        chooseDesiredOption_OrganisationPage("People");
        searchPersonProfileUnderDesiredRole("Total", passengerProfile);
        utils.sleep(1000);
        utils.clickBtn(By.xpath("//span[text()='" + passengerProfile + "']"));
        utils.assertElementLoaded(By.xpath("//span[text()='EDIT ']"));
        utils.sleep(2000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='EDIT ']"));
        utils.sendText(By.xpath("//label[text()='Observed by']/..//input"), observerProfile);
        utils.sleep(1000);
        utils.clickBtn(By.xpath("//span[text()='" + observerProfile + "']/../../..//span[text()='ADD']"));


        if (linkSecondObserver) {
            utils.sendText(By.xpath("//label[text()='Observed by']/..//input"), "observer" + randomObserverNameWithOutEmail + "");
            utils.sleep(1000);
            utils.clickBtn(By.xpath("//span[text()='observer" + randomObserverNameWithOutEmail + "']/../../..//span[text()='ADD']"));
        }
        utils.assertElementPresent(By.xpath("//span[text()='" + observerProfile + "']/../../..//span[text()='REMOVE']"));
        commonMethodsPage.clickSubmit();
    }

    public void logoutOfKura() {
        utils.clickBtn(By.xpath("//*[@id=\"root\"]/div/div/header/div/button[2]/span[1]"));
        utils.sleep(1000);
        utils.clickBtn(By.xpath("//li[text()='Logout']"));
    }

    public void createNewAccountOnKura(String name) {
        logoutOfKura();
        utils.clickBtn(By.xpath("//a[text()='Create account']"));
        utils.sendText(By.xpath("//input[@name='email']"), "uatkuratesting+" + name + "@gmail.com");
        utils.sendText(By.xpath("//input[@name='password']"), "Kura123!");
        utils.sendText(By.xpath("//input[@name='phone_line_number']"), "07515394230");
        utils.clickBtn(By.xpath("//button[text()='Create Account']"));
    }

    public void enterOTPandFinishAccountCreationOnKura() {
        utils.sendText(By.xpath("//input[@placeholder='Enter your code']"), otp);
        utils.clickBtn(By.xpath("//button[text()='Confirm']"));

    }


    public void breakLinkBetweenPersonProfileAndCognitiveAccount(String personProfile) {
        chooseDesiredOption_OrganisationPage("People");
        searchPersonProfileUnderDesiredRole("Total", personProfile);
        utils.clickBtn(By.xpath("//span[text()='" + personProfile + "']"));
        utils.assertElementPresent(By.xpath("//td[text()='uatkuratesting+" + personProfile + "@gmail.com']"));
        utils.sleep(1000);
        utils.scrollToView(By.xpath("//h6[text()='Linked User']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='Unlink']"));
    }

    public void linkPersonProfileToCognitiveAccountOnKura(boolean relink, String email, String password, boolean secondLink) {
        utils.assertElementPresent(By.xpath("//span[text()='PersonProfile']"));
        if (relink) {
            utils.actionsClick(By.xpath("//span[text()='Re-Invite']"));
        } else {
            utils.sleep(1000);
            utils.scrollToView(By.xpath("//span[text()='Invite']"));
            utils.sleep(1000);
            utils.javaScriptExecutorClick(By.xpath("//span[text()='Invite']"));
            utils.sleep(1000);
        }
        linkCode = driver.findElement(By.xpath("//span[text()='Re-Invite']/../following-sibling::div")).getText();
        homePage.logoutOfKura();
        loginPage.login(email, password);
        if (secondLink) {
            utils.sleep(2000);
            utils.clickOnDesiredNumberOfLocator(2, By.xpath("//span[@class='MuiIconButton-label']"));
            utils.clickBtn(By.xpath("//a[text()='Create Link']"));
        }
        if (!secondLink) {
            utils.assertElementPresent(By.xpath("//p[text()='enter your code below']"));
        }
        utils.clickBtn(By.xpath("//label[text()='Enter Code']/..//input"));
        utils.sendText(By.xpath("//label[text()='Enter Code']/..//input"), linkCode);
        utils.clickBtn(By.xpath("//span[text()='LINK']"));
    }


    public void addObserverProfile() throws InterruptedException {
        randomObserverName = utils.randomName().toLowerCase();
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), "observer" + randomObserverName);
        //utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("email")), "uatkuratesting+observer" + randomObserverName + "@gmail.com");
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        commonMethodsPage.clickSubmit();
    }


    public void addEmailAfterObserverProfileIsCreated(String observer) {
        searchPersonProfileUnderDesiredRole("Total", observer);
        utils.clickBtn(By.xpath("//span[text()='observer" + observer + "']"));
        utils.assertElementLoaded(By.xpath("//span[text()='EDIT ']"));
        utils.sleep(2000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='EDIT ']"));
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("email")), "uatkuratesting+observer" + observer + "@gmail.com");
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        commonMethodsPage.clickSubmit();
    }

    public void createObserverProfileWithOutEmail() {
        randomObserverNameWithOutEmail = utils.randomName().toLowerCase();
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), "observer" + randomObserverNameWithOutEmail);
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        commonMethodsPage.clickSubmit();


    }


    public void addPersonProfileWithAuthGroup(String personProfile, String authGroup) {
        randomObserverName = utils.randomName().toLowerCase();
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), personProfile + randomObserverName);
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("email")), "uatkuratesting+" + personProfile + "" + randomObserverName + "@gmail.com");
        utils.clickBtn(By.xpath("//span[text()='" + authGroup + "']/../../..//span[text()='ADD']"));
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        commonMethodsPage.clickSubmit();
    }


    public void chooseDesiredOption_OrganisationPage(String option) {
        utils.assertElementPresent(By.xpath("//h5[text()='View Organisation']"));
        utils.sleep(2000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='" + option + "']"));
    }

    public void assertCreatedPersonProfile() {
        chooseDesiredOption_OrganisationPage("People");
        searchPersonProfileUnderDesiredRole("Total", randomPassengerName);
        utils.assertElementPresent(By.xpath("//span[text()='passenger" + randomPassengerName + "']"));
    }

    public void editPersonProfile(String personProfile) {
        utils.clickBtn(By.xpath("//td[text()='passenger" + personProfile + "']"));
        commonMethodsPage.clickEdit(false);
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), "updatedpassenger" + personProfile);
        commonMethodsPage.clickSubmit();
        utils.assertElementPresent(By.xpath("//input[@value='updatedpassenger" + personProfile + "']"));
    }

    public void assertActionPlanCreationPage() {
        utils.assertElementPresent(By.xpath("//h6[text()='Action Plan Builder']"));
    }

    public void createNewActionPlan() throws InterruptedException {
        utils.sleep(1000);
        actionPlan = "actionPlan";
        utils.sendText(By.xpath("//input[@type='text']"), actionPlan);
        utils.dragAndDrop(By.xpath("//h5[text()='Heading']"), By.xpath("//p[text()='Drag components from the left into this panel']"));
        utils.clickOnDesiredNumberOfLocator(2, By.xpath("//h5[text()='Heading']"));
        utils.sendText(By.xpath("//input[@value='Heading']"), "driver late");
        utils.dragAndDrop(By.xpath("//label[text()='Checkbox']"), By.xpath("//h5[text()='driver late']"));
        utils.clickOnDesiredNumberOfLocator(2, By.xpath("//label[text()='Checkbox']"));
        utils.sendText(By.xpath("//input[@value='Checkbox']"), "has the parent and teachers been informed");
        commonMethodsPage.clickSave();
        utils.navigateBack();
        utils.sleep(10000);
        utils.refreshPage();
        chooseDesiredOption_OrganisationPage("Action Plans");
        try {
            utils.assertElementPresent(By.xpath("//span[text()='" + actionPlan + "']"));
        } catch (Exception e) {
            utils.refreshPage();
            utils.refreshPage();
            utils.sleep(5000);
            utils.refreshPage();
            utils.refreshPage();
            utils.sleep(5000);
            utils.refreshPage();
            utils.refreshPage();
            utils.sleep(5000);
            utils.refreshPage();
            utils.assertElementPresent(By.xpath("//span[text()='" + actionPlan + "']"));
        }

    }

    public void assertAddVehiclePage() {
        utils.assertElementPresent(By.xpath("//h5[text()='Please enter the details of the vehicle']"));
    }

    public void addNewVehicle() {
        vehicleReg = utils.randomVehicleRegGenerator();
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("registration")), vehicleReg);
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("capacity")), "20");
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("make")), "Volvo");
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("model")), "7900");
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("color")), "Orange");
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("insuranceStartDate_input")), utils.getCurrentDate());
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("insuranceEndDate_input")), utils.addYearsToCurrentDate(1));
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("dVSAStartDate_input")), utils.getCurrentDate());
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("dVSAEndDate_input")), utils.addYearsToCurrentDate(1));
        commonMethodsPage.clickSubmit();
    }

    public void assertVehicle(String regNumber, String capacity, String make, String model, String colour) {
        chooseDesiredOption_OrganisationPage("Vehicles");
        utils.assertElementPresent(By.xpath("//a[text()='" + regNumber + "']"));
        utils.assertElementPresent(By.xpath("//a[text()='" + capacity + "']"));
        //utils.assertElementPresent(By.xpath("//td[text()='"+make+"']"));
        utils.assertElementPresent(By.xpath("//a[text()='" + model + "']"));
        utils.assertElementPresent(By.xpath("//a[text()='" + colour + "']"));
    }

    public void editVehicle(String vehicleRegNumber) {
        utils.clickBtn(By.xpath("//a[text()='" + vehicleRegNumber + "']"));
        commonMethodsPage.clickEdit(false);
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("capacity")), "20");
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("make")), "Man");
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("model")), "001");
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("color")), "White");
        commonMethodsPage.clickSubmit();
        utils.assertElementPresent(By.xpath("//input[@value='White']"));
        utils.assertElementPresent(By.xpath("//input[@value='Man']"));
        utils.assertElementPresent(By.xpath("//input[@value='001']"));
    }

    public void assertAddTripPage() {
        utils.assertElementPresent(By.xpath("//h5[text()='Please enter the details of the trip']"));
    }

    //fixed time
    public void addTrip(String tripName, String contract, boolean multipleDays, String passenger1, boolean skipDates) throws InterruptedException {
        utils.clickBtn(By.id("select-Contract"));
        utils.clickBtn(By.xpath("//li[text()='" + contract + "']"));
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), tripName);
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("startDateTime_input")), utils.addDaysToCurrentDate(1, "dd/MM/yy"));
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("endDate_input")), utils.addDaysToCurrentDate(2, "dd/MM/yy"));
        utils.clickBtn(By.xpath("//span[@class='rw-i rw-i-clock-o']"));
        utils.actionsClick(By.xpath("//li[text()='09:00:00']"));
        utils.sleep(1000);
        utils.actionsClick(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(1, "EEE") + "']"));
        if (multipleDays) {
            utils.sleep(1000);
            utils.actionsClick(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(2, "EEE") + "']"));
        }

        utils.actionsClick(By.xpath("//span[@class='rw-i rw-i-clock-o']"));
        utils.clickBtn(By.xpath("//li[text()='09:00:00']"));
        if (skipDates) {

            utils.sleep(1000);
            utils.sleep(1000);
            utils.javaScriptExecutorClick(By.xpath("//button[@aria-label='Open calendar']"));
            try {
                utils.actionsClick(By.xpath("//td[@class='rw-cell rw-now rw-state-focus rw-state-selected']/following-sibling::td[1]"));
            } catch (Exception e) {
                utils.actionsClick(By.xpath("//td[@class='rw-cell rw-now rw-state-focus rw-state-selected']/../following-sibling::tr/td[1]"));
            }
        }
        if (utils.checkIfElementIsDisplayed(By.xpath("//p[text()='Search for a location']/..//input"))) {
            utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), "my home");
        }
        utils.clickBtn(By.xpath("//p[text()='my home']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        utils.sendText(By.xpath("//label[text()='Boarding passengers']/..//input"), passenger1);
        utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passenger1 + "']"));
        utils.clickBtn(By.xpath("//p[text()='my home']"));
        utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), "old home");
        utils.sleep(2000);
        utils.clickBtn(By.xpath("//p[text()='old home']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        utils.sendText(By.xpath("//span[text()='old home']/../..//input[@id='arrivalDateTime_input']"), utils.addDaysToCurrentDate(1, "dd/MM/yy") + ", " + "11:00");
        utils.actionsClick(By.xpath("//span[text()='ADD REMAINING']"));
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        commonMethodsPage.clickSubmit();
        propogatingTimeForTaskCreation();
    }

    public void tripScheduleStartingFromToday(String contract, String tripName, String passenger1, String time) {
        utils.clickBtn(By.id("select-Contract"));
        utils.clickBtn(By.xpath("//li[text()='" + contract + "']"));
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), tripName);
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("startDateTime_input")), utils.addDaysToCurrentDate(0, "dd/MM/yy"));
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("endDate_input")), utils.addDaysToCurrentDate(1, "dd/MM/yy"));
        utils.toNearestWholeHour();
        updatedTime.add(Calendar.MINUTE, Integer.parseInt(time));
        utils.sendText(By.xpath("//input[@id='startDateTime_input']"), new SimpleDateFormat("dd/MM/yy").format(updatedTime.getTime()) + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
        utils.sleep(1000);
        utils.actionsClick(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(0, "EEE") + "']"));
        utils.sleep(1000);
        utils.actionsClick(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(1, "EEE") + "']"));

        if (utils.checkIfElementIsDisplayed(By.xpath("//p[text()='Search for a location']/..//input"))) {
            utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), "my home");
        }
        utils.clickBtn(By.xpath("//p[text()='my home']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        utils.sendText(By.xpath("//label[text()='Boarding passengers']/..//input"), passenger1);
        utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passenger1 + "']"));
        utils.clickBtn(By.xpath("//p[text()='my home']"));
        utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), "old home");
        utils.sleep(2000);
        utils.clickBtn(By.xpath("//p[text()='old home']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        utils.actionsClick(By.xpath("//span[text()='ADD REMAINING']"));
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        commonMethodsPage.clickSubmit();
        propogatingTimeForTaskCreation();
    }

    public void addTripForASingleDay(boolean extraStop, String tripName, String stop1, String stop2, String passenger, boolean addPassengerAtFirstStop, String contract, boolean add2Hours, int startTime, int daysToBeAdded) throws InterruptedException {
        utils.clickBtn(By.id("select-Contract"));
        utils.clickBtn(By.xpath("//li[text()='" + contract + "']"));
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), tripName);
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("startDateTime_input")), utils.addDaysToCurrentDate(daysToBeAdded, "dd/MM/yy"));
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("endDate_input")), utils.addDaysToCurrentDate(daysToBeAdded, "dd/MM/yy"));
        utils.toNearestWholeHour();
        if (add2Hours) {
            updatedTime.add(Calendar.HOUR, 2);
            utils.sendText(By.xpath("//input[@id='startDateTime_input']"), new SimpleDateFormat("dd/MM/yy").format(updatedTime.getTime()) + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
            utils.clickBtn(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(0, "EEE") + "']"));
        } else if (daysToBeAdded == 0 && !add2Hours) {
            updatedTime.add(Calendar.HOUR, startTime);
            utils.sendText(By.xpath("//input[@id='startDateTime_input']"), new SimpleDateFormat("dd/MM/yy").format(updatedTime.getTime()) + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
            utils.clickBtn(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(0, "EEE") + "']"));
        } else if (daysToBeAdded == 1 && !add2Hours) {
            updatedTime.add(Calendar.DAY_OF_WEEK, startTime);
            utils.sendText(By.xpath("//input[@id='startDateTime_input']"), new SimpleDateFormat("dd/MM/yy").format(updatedTime.getTime()) + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
            utils.clickBtn(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(1, "EEE") + "']"));
        }

        utils.clickBtn(By.xpath("//span[text()='Driver can skip stops']"));
        utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), stop1);
        utils.clickBtn(By.xpath("//p[text()='" + stop1 + "']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        if (addPassengerAtFirstStop) {
            utils.sendText(By.xpath("//label[text()='Boarding passengers']/..//input"), passenger);
            utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passenger + "']"));
        }

        if (extraStop) {
            utils.clickBtn(By.xpath("//p[text()='" + stop1 + "']"));
            utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), "old home");
            utils.sleep(2000);
            utils.clickBtn(By.xpath("//p[text()='old home']"));
            utils.sleep(1000);
            utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
            utils.clickBtn(By.xpath("//p[text()='old home']"));
            utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), "rc club bunker");
            utils.sleep(2000);
            utils.clickBtn(By.xpath("//p[text()='rc club bunker']"));
            utils.sleep(1000);
            utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
            if (!addPassengerAtFirstStop) {
                utils.clickOnDesiredNumberOfLocatorAndSendText(2, By.xpath("//label[text()='Boarding passengers']/..//input"), passenger);
                utils.clickOnDesiredNumberOfLocator(1, By.xpath("//div[@role='option'][text()='" + passenger + "']"));
                utils.actionsClick(By.xpath("//span[text()='ADD REMAINING']"));
            }
        } else {
            utils.clickBtn(By.xpath("//p[text()='" + stop1 + "']"));
            utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), stop2);
            utils.sleep(2000);
            utils.clickBtn(By.xpath("//p[text()='" + stop2 + "']"));
            utils.sleep(1000);
            utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        }
        if (addPassengerAtFirstStop) {
            utils.actionsClick(By.xpath("//span[text()='ADD REMAINING']"));
        }

        if (add2Hours) {
            updatedTime.add(Calendar.HOUR, 2);
            utils.clickOnDesiredNumberOfLocatorAndSendText(2, By.xpath("//input[@id='arrivalDateTime_input']"), new SimpleDateFormat("dd/MM/yy").format(updatedTime.getTime()) + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
            utils.clickOnDesiredNumberOfLocatorAndSendText(2, By.xpath("//input[@id='departureDateTime_input']"), new SimpleDateFormat("dd/MM/yy").format(updatedTime.getTime()) + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
        } else if (!extraStop) {
            updatedTime.add(Calendar.MINUTE, startTime + 2);
            utils.clickOnDesiredNumberOfLocatorAndSendText(2, By.xpath("//input[@id='arrivalDateTime_input']"), new SimpleDateFormat("dd/MM/yy").format(updatedTime.getTime()) + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
            utils.clickOnDesiredNumberOfLocatorAndSendText(2, By.xpath("//input[@id='departureDateTime_input']"), new SimpleDateFormat("dd/MM/yy").format(updatedTime.getTime()) + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
        }
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        commonMethodsPage.clickSubmit();
        propogatingTimeForTaskCreation();
    }

    public void propogatingTimeForTaskCreation() {
        utils.assertElementPresentLongWait(By.xpath("//h2[text()='Task ran successfully']"));
        utils.assertElementPresentLongWait(By.xpath("//span[text()='Close']"));
        utils.clickBtn(By.xpath("//span[text()='Close']"));

    }


    public void assertCreatedContractIsAccessibleUnderCreateTrip(String contract) {
        utils.clickBtn(By.id("select-Contract"));
        utils.assertElementPresent(By.xpath("//li[text()='" + contract + "']"));
    }

    public void fourPointTrip_singleDay(String tripName, String contract, boolean multipleboardAndDeboardAtStops, String passengerA, String passengerB, String passengerC, String passengerD, boolean onlyOnePassengerOnBoardAtAnInstance, int time, String waypoint1, String waypoint2, String waypoint3, String waypoint4) throws InterruptedException {
        utils.clickBtn(By.id("select-Contract"));
        try {
            utils.clickBtn(By.xpath("//li[text()='" + contract + "']"));
        } catch (Exception e) {
            utils.refreshPage();
            utils.sleep(100000);
            utils.refreshPage();
            utils.clickBtn(By.id("select-Contract"));
            utils.clickBtn(By.xpath("//li[text()='" + contract + "']"));
        }
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), tripName);

        if (multipleboardAndDeboardAtStops) {
            utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("startDateTime_input")), utils.addDaysToCurrentDate(1, "dd/MM/yy"));
            utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("endDate_input")), utils.addDaysToCurrentDate(2, "dd/MM/yy"));
        } else {
            utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("startDateTime_input")), utils.addDaysToCurrentDate(0, "dd/MM/yy"));
            utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("endDate_input")), utils.addDaysToCurrentDate(0, "dd/MM/yy"));

        }
        utils.toNearestWholeHour();
        updatedTime.add(Calendar.MINUTE, time);
        utils.sendText(By.xpath("//input[@id='startDateTime_input']"), new SimpleDateFormat("dd/MM/yy").format(updatedTime.getTime()) + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));

        if (multipleboardAndDeboardAtStops) {
            utils.clickBtn(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(1, "EEE") + "']"));
            utils.clickBtn(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(2, "EEE") + "']"));
        } else {
            utils.clickBtn(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(0, "EEE") + "']"));
        }


        utils.clickBtn(By.xpath("//span[text()='Driver can skip stops']"));
        utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), waypoint1);
        utils.clickBtn(By.xpath("//p[text()='" + waypoint1 + "']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));

        utils.sendText(By.xpath("//label[text()='Boarding passengers']/..//input"), passengerA);
        utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passengerA + "']"));
        if (multipleboardAndDeboardAtStops) {
            utils.sendTxtWithOutClearing(By.xpath("//label[text()='Boarding passengers']/..//input"), "passa");
            utils.clickBtn(By.xpath("//div[@role='option'][text()='passa']"));

            utils.sendTxtWithOutClearing(By.xpath("//label[text()='Boarding passengers']/..//input"), "passb");
            utils.clickBtn(By.xpath("//div[@role='option'][text()='passb']"));
        }


        utils.clickBtn(By.xpath("//p[text()='" + waypoint1 + "']"));
        utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), waypoint2);
        utils.sleep(2000);
        utils.clickBtn(By.xpath("//p[text()='" + waypoint2 + "']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));

        utils.sendTextWithOutClearingOnDesiredNumberOfLocator(2, By.xpath("//label[text()='Boarding passengers']/..//input"), passengerB);
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passengerB + "']"));
        if (onlyOnePassengerOnBoardAtAnInstance) {
            utils.sendTextWithOutClearingOnDesiredNumberOfLocator(2, By.xpath("//label[text()='Disembarking passengers']/..//input"), passengerA);
            utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passengerA + "']"));
        }

        if (multipleboardAndDeboardAtStops) {
            utils.sendTextWithOutClearingOnDesiredNumberOfLocator(2, By.xpath("//label[text()='Disembarking passengers']/..//input"), "passa");
            utils.clickBtn(By.xpath("//div[@role='option'][text()='passa']"));
            utils.sendTextWithOutClearingOnDesiredNumberOfLocator(2, By.xpath("//label[text()='Disembarking passengers']/..//input"), "passb");
            utils.clickBtn(By.xpath("//div[@role='option'][text()='passb']"));
            utils.sendTextWithOutClearingOnDesiredNumberOfLocator(2, By.xpath("//label[text()='Boarding passengers']/..//input"), "passc");
            utils.clickBtn(By.xpath("//div[@role='option'][text()='passc']"));
            utils.sendTextWithOutClearingOnDesiredNumberOfLocator(2, By.xpath("//label[text()='Boarding passengers']/..//input"), "passd");
            utils.clickBtn(By.xpath("//div[@role='option'][text()='passd']"));
        }
        utils.scrollToView(By.xpath("//span[text()='Driver can skip stops']"));
        utils.clickBtn(By.xpath("//p[text()='" + waypoint2 + "']"));
        utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), waypoint3);
        utils.sleep(2000);
        utils.clickBtn(By.xpath("//p[text()='" + waypoint3 + "']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        utils.sendTextWithOutClearingOnDesiredNumberOfLocator(3, By.xpath("//label[text()='Boarding passengers']/..//input"), passengerC);
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passengerC + "']"));
        if (onlyOnePassengerOnBoardAtAnInstance) {
            utils.sendTextWithOutClearingOnDesiredNumberOfLocator(3, By.xpath("//label[text()='Disembarking passengers']/..//input"), passengerB);
            utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passengerB + "']"));
        }

        utils.scrollToView(By.xpath("//span[text()='Driver can skip stops']"));
        utils.clickBtn(By.xpath("//p[text()='" + waypoint3 + "']"));
        utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), waypoint4);
        utils.sleep(2000);
        utils.clickBtn(By.xpath("//p[text()='" + waypoint4 + "']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        utils.sendTextWithOutClearingOnDesiredNumberOfLocator(4, By.xpath("//label[text()='Boarding passengers']/..//input"), passengerD);
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passengerD + "']"));
        if (onlyOnePassengerOnBoardAtAnInstance) {
            utils.sendTextWithOutClearingOnDesiredNumberOfLocator(4, By.xpath("//label[text()='Disembarking passengers']/..//input"), passengerC);
            utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passengerC + "']"));
        }

        utils.actionsClick(By.xpath("//span[text()='ADD REMAINING']"));
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        commonMethodsPage.clickSubmit();
        propogatingTimeForTaskCreation();

    }

    public void FourPointTripWithSinglePassenger(String contract, int timeToBeAdded, String passenger, boolean addPassenger, boolean first2Stops, String stop1, String stop2, String stop3, String stop4) throws InterruptedException {
        utils.clickBtn(By.id("select-Contract"));
        utils.clickBtn(By.xpath("//li[text()='" + contract + "']"));
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), tripName);
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("startDateTime_input")), utils.addDaysToCurrentDate(0, "dd/MM/yy"));
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("endDate_input")), utils.addDaysToCurrentDate(0, "dd/MM/yy"));
        utils.toNearestWholeHour();
        updatedTime.add(Calendar.HOUR, timeToBeAdded);
        utils.sendText(By.xpath("//input[@id='startDateTime_input']"), new SimpleDateFormat("dd/MM/yy").format(updatedTime.getTime()) + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
        utils.clickBtn(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(0, "EEE") + "']"));
        utils.clickBtn(By.xpath("//span[text()='Driver can skip stops']"));
        utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), stop1);
        utils.clickBtn(By.xpath("//p[text()='" + stop1 + "']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        //utils.toNearestWholeHour();
        updatedTime.add(Calendar.MINUTE, 15);
        utils.clickOnDesiredNumberOfLocatorAndSendText(1, By.xpath("//input[@id='arrivalDateTime_input']"), utils.addDaysToCurrentDate(0, "dd/MM/yy") + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
        utils.clickOnDesiredNumberOfLocatorAndSendText(1, By.xpath("//input[@id='departureDateTime_input']"), utils.addDaysToCurrentDate(0, "dd/MM/yy") + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
//         if(addPassenger)  { utils.sendText(By.xpath("//label[text()='Boarding passengers']/..//input"), passenger);
//            utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passenger + "']"));}
        utils.clickBtn(By.xpath("//p[text()='" + stop1 + "']"));
        utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), stop2);
        utils.sleep(2000);
        utils.clickBtn(By.xpath("//p[text()='" + stop2 + "']"));
        utils.sleep(1000);
        updatedTime.add(Calendar.MINUTE, 5);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        utils.clickOnDesiredNumberOfLocatorAndSendText(2, By.xpath("//input[@id='arrivalDateTime_input']"), utils.addDaysToCurrentDate(0, "dd/MM/yy") + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
        utils.sleep(1000);
        utils.clickOnDesiredNumberOfLocatorAndSendText(2, By.xpath("//input[@id='departureDateTime_input']"), utils.addDaysToCurrentDate(0, "dd/MM/yy") + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
//       if(addPassenger){ utils.sendTextOnDesiredNumberOfLocator(2, By.xpath("//label[text()='Disembarking passengers']/..//input"), passenger);
//        utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passenger + "']"));}
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        utils.scrollToView(By.xpath("//span[text()='Driver can skip stops']"));
        utils.clickBtn(By.xpath("//p[text()='" + stop2 + "']"));
        utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), stop3);
        utils.sleep(2000);
        utils.clickBtn(By.xpath("//p[text()='" + stop3 + "']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        updatedTime.add(Calendar.MINUTE, 10);
        utils.clickOnDesiredNumberOfLocatorAndSendText(3, By.xpath("//input[@id='arrivalDateTime_input']"), utils.addDaysToCurrentDate(0, "dd/MM/yy") + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
        utils.clickOnDesiredNumberOfLocatorAndSendText(3, By.xpath("//input[@id='departureDateTime_input']"), utils.addDaysToCurrentDate(0, "dd/MM/yy") + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        utils.scrollToView(By.xpath("//span[text()='Driver can skip stops']"));
        utils.clickBtn(By.xpath("//p[text()='" + stop3 + "']"));
        utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), stop4);
        utils.sleep(2000);
        utils.clickBtn(By.xpath("//p[text()='" + stop4 + "']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        updatedTime.add(Calendar.MINUTE, 15);
        utils.clickOnDesiredNumberOfLocatorAndSendText(4, By.xpath("//input[@id='arrivalDateTime_input']"), utils.addDaysToCurrentDate(0, "dd/MM/yy") + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
        utils.sleep(1000);
        utils.clickOnDesiredNumberOfLocatorAndSendText(4, By.xpath("//input[@id='departureDateTime_input']"), utils.addDaysToCurrentDate(0, "dd/MM/yy") + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));

        if (first2Stops) {

            utils.sendTextWithOutClearingOnDesiredNumberOfLocator(1, By.xpath("//label[text()='Boarding passengers']/..//input"), passenger);
            utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passenger + "']"));
            utils.sendTextWithOutClearingOnDesiredNumberOfLocator(2, By.xpath("//label[text()='Disembarking passengers']/..//input"), passenger);
            utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passenger + "']"));

        }


        if (addPassenger) {
            utils.sendTextWithOutClearingOnDesiredNumberOfLocator(3, By.xpath("//label[text()='Boarding passengers']/..//input"), passenger);
            utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passenger + "']"));
        }


        if (addPassenger) {
            utils.sendTextWithOutClearingOnDesiredNumberOfLocator(4, By.xpath("//label[text()='Disembarking passengers']/..//input"), passenger);
            utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passenger + "']"));
        }

        utils.scrollToView(By.xpath("//button[@type='submit']"));
        commonMethodsPage.clickSubmit();
        propogatingTimeForTaskCreation();

    }


    public void fourPointTripWithMultiplePassengers(String tripName, String contract) throws InterruptedException {
        utils.clickBtn(By.id("select-Contract"));
        utils.clickBtn(By.xpath("//li[text()='" + contract + "']"));
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), tripName);
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("startDateTime_input")), utils.addDaysToCurrentDate(1, "dd/MM/yy"));
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("endDate_input")), utils.addDaysToCurrentDate(2, "dd/MM/yy"));
        utils.toNearestWholeHour();
        //updatedTime.add(Calendar.HOUR, 1);
        utils.sendText(By.xpath("//input[@id='startDateTime_input']"), utils.addDaysToCurrentDate(1, "dd/MM/yy") + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
        utils.clickBtn(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(1, "EEE") + "']"));
        utils.clickBtn(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(2, "EEE") + "']"));
        utils.clickBtn(By.xpath("//span[text()='Driver can skip stops']"));
        utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), "my home");
        utils.clickBtn(By.xpath("//p[text()='my home']"));
        utils.sleep(1000);

        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        utils.sendTxtWithOutClearing(By.xpath("//label[text()='Boarding passengers']/..//input"), "passengerone");
        utils.clickBtn(By.xpath("//div[@role='option'][text()='passengerone']"));

        utils.sendTxtWithOutClearing(By.xpath("//label[text()='Boarding passengers']/..//input"), "passengertwo");
        utils.clickBtn(By.xpath("//div[@role='option'][text()='passengertwo']"));

        utils.sendTxtWithOutClearing(By.xpath("//label[text()='Boarding passengers']/..//input"), "passengerthree");
        utils.clickBtn(By.xpath("//div[@role='option'][text()='passengerthree']"));

        utils.sendTxtWithOutClearing(By.xpath("//label[text()='Boarding passengers']/..//input"), "passengerfour");
        utils.clickBtn(By.xpath("//div[@role='option'][text()='passengerfour']"));

        utils.sendTxtWithOutClearing(By.xpath("//label[text()='Boarding passengers']/..//input"), "abbas");
        utils.clickBtn(By.xpath("//div[@role='option'][text()='abbas']"));

        utils.sendTxtWithOutClearing(By.xpath("//label[text()='Boarding passengers']/..//input"), "passengersix");
        utils.clickBtn(By.xpath("//div[@role='option'][text()='passengersix']"));

        utils.sendTxtWithOutClearing(By.xpath("//label[text()='Boarding passengers']/..//input"), "stefanie");
        utils.clickBtn(By.xpath("//div[@role='option'][text()='stefanie']"));

        utils.sendTxtWithOutClearing(By.xpath("//label[text()='Boarding passengers']/..//input"), "rupa");
        utils.clickBtn(By.xpath("//div[@role='option'][text()='rupa']"));

        utils.sendTxtWithOutClearing(By.xpath("//label[text()='Boarding passengers']/..//input"), "zoe");
        utils.clickBtn(By.xpath("//div[@role='option'][text()='zoe']"));

        utils.sendTxtWithOutClearing(By.xpath("//label[text()='Boarding passengers']/..//input"), "abba");
        utils.clickBtn(By.xpath("//div[@role='option'][text()='abba']"));

        utils.clickBtn(By.xpath("//p[text()='my home']"));
        utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), "old home");
        utils.sleep(2000);
        utils.clickBtn(By.xpath("//p[text()='old home']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        utils.toNearestWholeHour();
        updatedTime.add(Calendar.MINUTE, 5);
        utils.clickOnDesiredNumberOfLocatorAndSendText(2, By.xpath("//input[@id='arrivalDateTime_input']"), utils.addDaysToCurrentDate(1, "dd/MM/yy") + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
        utils.clickOnDesiredNumberOfLocator(2, By.xpath("//input[@id='departureDateTime_input']"));
        utils.sendTextWithOutClearingOnDesiredNumberOfLocator(2, By.xpath("//label[text()='Disembarking passengers']/..//input"), "abbas");
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        utils.clickBtn(By.xpath("//div[@role='option'][text()='abbas']"));
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        utils.scrollToView(By.xpath("//span[text()='Driver can skip stops']"));
        utils.clickBtn(By.xpath("//p[text()='old home']"));
        utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), "rc club bunker");
        utils.sleep(2000);
        utils.clickBtn(By.xpath("//p[text()='rc club bunker']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        updatedTime.add(Calendar.MINUTE, 5);
        utils.clickOnDesiredNumberOfLocatorAndSendText(3, By.xpath("//input[@id='arrivalDateTime_input']"), utils.addDaysToCurrentDate(1, "dd/MM/yy") + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
        utils.clickOnDesiredNumberOfLocator(3, By.xpath("//input[@id='departureDateTime_input']"));
        utils.scrollToView(By.xpath("//span[text()='Driver can skip stops']"));
        utils.clickBtn(By.xpath("//p[text()='rc club bunker']"));
        utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), "lonAir");
        utils.sleep(2000);
        utils.clickBtn(By.xpath("//p[text()='lonAir']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        updatedTime.add(Calendar.MINUTE, 5);
        utils.clickOnDesiredNumberOfLocatorAndSendText(4, By.xpath("//input[@id='arrivalDateTime_input']"), utils.addDaysToCurrentDate(1, "dd/MM/yy") + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
        utils.clickOnDesiredNumberOfLocator(4, By.xpath("//input[@id='departureDateTime_input']"));
        utils.actionsClick(By.xpath("//span[text()='ADD REMAINING']"));
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        commonMethodsPage.clickSubmit();
        propogatingTimeForTaskCreation();
    }

    public void threeYearLongTripSchedule(String contract) {
        utils.clickBtn(By.id("select-Contract"));
        utils.clickBtn(By.xpath("//li[text()='" + contract + "']"));
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), tripName);
        utils.sleep(1000);
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("endDate_input")), utils.addYearsToCurrentDate(3));
        utils.toNearestWholeHour();
        updatedTime.add(Calendar.MINUTE, 30);
        utils.sendText(By.xpath("//input[@id='startDateTime_input']"), utils.addDaysToCurrentDate(1, "dd/MM/yy") + "," + " " + new SimpleDateFormat("HH:mm").format(updatedTime.getTime()));
        utils.clickBtn(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")));
        utils.sleep(1000);
        utils.actionsClick(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(0, "EEE") + "']"));
        utils.sleep(1000);
        utils.actionsClick(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(1, "EEE") + "']"));
        utils.sleep(1000);
        utils.actionsClick(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(2, "EEE") + "']"));
        utils.sleep(1000);
        utils.actionsClick(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(3, "EEE") + "']"));
        utils.sleep(1000);
        utils.actionsClick(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(4, "EEE") + "']"));
        utils.sleep(1000);
        utils.actionsClick(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(5, "EEE") + "']"));
        utils.sleep(1000);
        utils.actionsClick(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(6, "EEE") + "']"));
        if (utils.checkIfElementIsDisplayed(By.xpath("//p[text()='Search for a location']/..//input"))) {
            utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), "my home");
        }
        utils.clickBtn(By.xpath("//p[text()='my home']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        utils.clickBtn(By.xpath("//p[text()='my home']"));
        utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), "old home");
        utils.sleep(2000);
        utils.clickBtn(By.xpath("//p[text()='old home']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        commonMethodsPage.clickSubmit();
        propogatingTimeForTaskCreation();
    }


    public void addExtraPassengerWhilstOnEditTrip(String passenger) throws InterruptedException {
        utils.sendTxtWithOutClearing(By.xpath("//label[text()='Boarding passengers']/..//input"), passenger);
        utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passenger + "']"));
        utils.actionsClick(By.xpath("//span[text()='ADD REMAINING']"));
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        commonMethodsPage.clickSubmit();
    }

    public void deletePassengerWhilstOnEditTrip() {
        utils.clickBtn(By.cssSelector("svg[class='MuiSvgIcon-root MuiChip-deleteIcon']"));
        utils.clickBtn(By.cssSelector("svg[class='MuiSvgIcon-root MuiChip-deleteIcon']"));
        utils.clickBtn(By.xpath("//span[text()='SAVE']"));


    }


    public void navigateToTripSummaryDropDown(String org, int whi1chDay, String trip) throws Exception {
        navigateToTripsPageAndSearch(org, trip);
        utils.clickBtn(By.xpath("//td[text()='" + trip + "']"));
        try {
            boolean value = true;
            int number = 1;
            while (value) {
                utils.sleep(2000);
                try {
                    utils.clickOnDesiredNumberOfLocator(whi1chDay, By.xpath("//p[text()='" + trip + "']"));
                    value = false;
                } catch (Exception e1) {
                    utils.sleep(2000);
                    utils.refreshPage();
                    number--;
                    if (number == 0) {
                        throw new Exception("unable to click");
                    }

                }
            }
        } catch (Exception e) {
            utils.sleep(3000);
            utils.clickOnDesiredNumberOfLocator(4, By.xpath("//span[@class='MuiIconButton-label']"));
            utils.sleep(2000);
            utils.clickOnDesiredNumberOfLocator(whi1chDay, By.xpath("//p[text()='" + trip + "']"));
        }


    }

    public void navigateToTripCalendar(String org, String trip) throws Exception {
        navigateToTripsPageAndSearch(org, trip);
        utils.clickBtn(By.xpath("//td[text()='" + trip + "']"));
    }

    public void navigateToTripsPageAndSearch(String org, String trip) throws Exception {
        homePage.searchAndPickOrganisation(org);
        chooseDesiredOption_OrganisationPage("Trips");
        boolean value = true;
        int loop = 4;
        while (value) {
            try {
                utils.sleep(2000);
                utils.sendTxtWithOutClearing(By.xpath("//input[@type='text']"), trip);
                utils.assertElementPresent(By.xpath("//td[text()='" + trip + "']"));
                value = false;
            } catch (Exception e) {
                loop--;
                utils.sleep(5000);
                utils.refreshPage();
                if (loop == 0) {
                    try {
                        throw new Exception("trip not appearing under search");
                    } catch (Exception e1) {
                    }
                }
            }

        }
//        utils.refreshPage();
//        utils.sleep(10000);
//        utils.refreshPage();
//        utils.sleep(2000);
//        utils.sendText(By.xpath("//input[@type='text']"), trip);
//        try {
//            utils.assertElementPresent(By.xpath("//td[text()='" + trip + "']"));
//        } catch (Exception e) {
//            utils.refreshPage();
//            utils.sleep(5000);
//            utils.refreshPage();
//            utils.sleep(2000);
//            utils.sendText(By.xpath("//input[@type='text']"), trip);
//            utils.assertElementPresent(By.xpath("//td[text()='" + trip + "']"));
//        }
    }


    public void renameTripAndAssertChanges(String trip) {
        utils.clickBtn(By.xpath("//button[@title='Edit']"));
        utils.sendText(By.xpath("//input[@placeholder='Trip Name']"), "updated" + trip + "");
        utils.sleep(1000);
        utils.clickOnDesiredNumberOfLocator(2, By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorInherit']"));
        utils.sleep(250000);
        utils.refreshPage();
        utils.sleep(3000);
        utils.sendText(By.xpath("//input[@type='text']"), "updated" + trip + "");
        utils.assertElementPresent(By.xpath("//td[text()='updated" + trip + "']"));
        utils.sendText(By.xpath("//input[@type='text']"), "" + trip + "");
        utils.assertElementAbsent(By.xpath("//td[text()='" + trip + "']"));

    }

    public void accessTripFromOrgPage(String trip) {
        utils.navigateBack();
        chooseDesiredOption_OrganisationPage("Trips");
        utils.sleep(1000);
        utils.sendText(By.xpath("//input[@type='text']"), trip);
        try {
            utils.clickBtn(By.xpath("//td[text()='" + trip + "']"));
        } catch (Exception e) {
            utils.sleep(7000);
            utils.refreshPage();
            utils.sendText(By.xpath("//input[@type='text']"), trip);

            utils.clickBtn(By.xpath("//td[text()='" + trip + "']"));
        }
    }

    public boolean checkIfYouAccessTrip(String trip) {
        try {
            utils.assertElementPresent(By.xpath("//p[text()='" + trip + "']"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickViewOREditTripOnSummaryDropDown(String option) {
        utils.assertElementPresent(By.xpath("//span[text()='" + option + "']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='" + option + "']"));
    }

    public void changeTripScheduleRoutePointTimings() throws InterruptedException {
        utils.sendText(By.xpath("//input[@id='fromDate_input']"), utils.addDaysToCurrentDate(1, "dd/MM/yy"));
        utils.clickOnDesiredNumberOfLocatorAndSendText(1, By.xpath("//input[@id='arrivalDateTime_input']"), utils.addDaysToCurrentDate(1, "dd/MM/yy, ") + "11:00");
        utils.clickOnDesiredNumberOfLocatorAndSendText(1, By.xpath("//input[@id='departureDateTime_input']"), utils.addDaysToCurrentDate(1, "dd/MM/yy, ") + "11:00");
        utils.clickOnDesiredNumberOfLocatorAndSendText(2, By.xpath("//input[@id='arrivalDateTime_input']"), utils.addDaysToCurrentDate(1, "dd/MM/yy, ") + "12:00");
        utils.clickOnDesiredNumberOfLocatorAndSendText(2, By.xpath("//input[@id='departureDateTime_input']"), utils.addDaysToCurrentDate(1, "dd/MM/yy, ") + "12:00");
        commonMethodsPage.clickSave();
        utils.assertElementPresent(By.xpath("//h2[text()='Are you sure you want to make these changes?']"));
        utils.clickBtn(By.xpath("//span[text()='SAVE ANYWAY']"));
        utils.sleep(3000);
        utils.clickBtn(By.xpath("//*[text()='Close']"));
    }


    public void ableToCloneTripInReverse(String trip) throws Exception {
        navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 2, trip);
        clickViewOREditTripOnSummaryDropDown("VIEW TRIP");
        utils.javaScriptExecutorClick(By.xpath("//span[text()='CLONE IN REVERSE']"));
        utils.clickBtn(By.id("select-Contract"));
        utils.clickBtn(By.xpath("//li[text()='contract']"));
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), "reverse " + tripName);
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("endDate_input")), utils.addDaysToCurrentDate(1, "dd/MM/yy"));
        utils.hitEnter(By.xpath(commonMethodsPage.xpathGenerator_inputTag("endDate_input")));
        utils.clickBtn(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(1, "EEE") + "']"));
        utils.sleep(1000);
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        commonMethodsPage.clickSubmit();
        propogatingTimeForTaskCreation();

    }

    public void assertCloneInReverseTrip(String trip) throws Exception {
        navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, "reverse " + trip);
        clickViewOREditTripOnSummaryDropDown("VIEW TRIP");
        utils.sleep(2000);
        Assert.assertEquals(driver.findElements(By.xpath("//div[@class='MuiListItemText-root']/span")).get(4).getText(), "old home");
        assertTimingsInCloneAndInReverse();
    }

    public void cloneATrip() throws Exception {
        navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        clickViewOREditTripOnSummaryDropDown("VIEW TRIP");
        utils.clickBtn(By.xpath("//div[@id='root']//span[text()='CLONE']"));
        utils.assertElementPresent(By.xpath("//h5[text()='Please enter the details of the trip']"));
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("startDateTime_input")), utils.addDaysToCurrentDate(1, "dd/MM/yy") + ", 23:00");
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("endDate_input")), utils.addDaysToCurrentDate(1, "dd/MM/yy"));
        utils.sleep(2000);
        utils.clickBtn(By.xpath("//h5[text()='Please enter the details of the trip']"));
        utils.sleep(1000);
        utils.actionsClick(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(1, "EEE") + "']"));
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        commonMethodsPage.clickSubmit();

    }

    public void assertTimingsInCloneAndInReverse() {
        List<WebElement> allDates = driver.findElements(By.xpath("//input[@id='arrivalDateTime_input']"));
        for (WebElement firstDate : allDates) {
            utils.sleep(1000);
            Assert.assertTrue(firstDate.getAttribute("value").contains("23:00"));
            break;
        }
        utils.assertElementPresent(By.xpath("//span[text()='old home']/../..//input[contains(@value,'23')]"));

    }

    public void assertClonedTrips() throws Exception {
        utils.sleep(30000);
        utils.refreshPage();
        navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 2, tripName);
        clickViewOREditTripOnSummaryDropDown("EDIT TRIP");
        utils.sleep(1000);
        List<WebElement> allWayPoints = driver.findElements(By.xpath("//div[@style='display: initial;']/..//ul//li[1]//span"));
        for (WebElement firstWayPoint : allWayPoints) {
            utils.sleep(1000);
            Assert.assertTrue(firstWayPoint.getText().contentEquals("my home"));
            break;
        }

    }


    public void addOrDeletePassengersToTripViaTripSchedule(String passenger, boolean multiplePassengers, String addOrDelete, boolean acrossFullTrip, int from, int to, String boardingStop, String disembarkStop) throws InterruptedException {
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='Edit Passengers']"));

        if (multiplePassengers) {
            String[] passengersNames = {"passa", "passb", "passc", "passd", "passe", "passf", "passg", "passh", "passi", "passj", "passk", "passl", "passm", "passn", "passo", "passp", "passq", "passr", "passs", "passt", "passu", "passv", "passw", "passx", "passy", "passz", "apass", "bpass", "cpass", "dpass", "epass", "fpass", "gpass", "hpass", "ipass", "jpass", "kpass", "lpass", "mpass", "npass", "opass", "ppass", "qpass", "rpass", "spass", "tpass", "upass", "vpass", "wpass", "xpass", "ypass", "zpass", "onepass", "twopass", "threepass", "fourpass", "fivepass", "sixpass", "sevenpass", "eightpass", "ninepass", "tenpass", "elevenpass", "twelvepass", "thirteenpass", "fourteenpass", "fifteenpass", "sixteenpass", "seventeenpass", "eighteenpass", "ninteenpass", "twentypass", "twentyonepass", "twentytwopass", "twentythreepass", "twentyfourpass", "twentyfivepass", "twentysixpass", "twentysevenpass", "twentyeightpass", "twentyninepass", "thirtypass", "thirtyonepass", "thirtytwopass", "thirtythreepass", "thirtyfourpass", "thirtyfivepass", "thirtysixpass", "thirtysevenpass", "tirtyeightpass", "thirtyninepass", "fourtypass", "fourtyonepass", "fourtytwopass", "fourtythreepass", "fourtyfourpass", "fourtyfivepass", "fourtysixpass", "fourtysevenpass", "fourtyeightpass", "fourtyninepass"};

            for (int i = 0; i < 101; i++) {
                utils.sleep(1000);
                utils.sendTxtWithOutClearing(By.xpath("//label[text()='Passenger']/..//input"), passengersNames[i]);
                utils.sleep(1000);
                utils.clickBtn(By.xpath("//div[@role='option'][contains(text(),'" + passengersNames[i] + "')]"));
            }

        } else {


            utils.sleep(1000);
            utils.sendText(By.xpath("//label[text()='Passenger']/..//input"), passenger);
            utils.sleep(1000);
            utils.clickBtn(By.xpath("//div[@role='option'][contains(text(),'" + passenger + "')]"));

        }

        utils.clickBtn(By.xpath("//div[@id='select-Action']"));
        utils.clickBtn(By.xpath("//li[text()='" + addOrDelete + "']"));

        if (multiplePassengers) {

            utils.sendText(By.xpath("//input[@id='fromDate_input']"), utils.addDaysToCurrentDate(1, "dd/MM/yy"));
            utils.sendText(By.xpath("//input[@id='toDate_input']"), utils.addYearsToCurrentDate(3));
            utils.clickLocatorBasedOnIndex(1, By.xpath("//button [@aria-label='Select date']"));
            String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

            for (int i = 0; i < 7; i++) {
                utils.sleep(1000);
                utils.actionsClick(By.xpath("//span[text()='" + daysOfWeek[i] + "']"));
            }

        } else {

            utils.sendText(By.xpath("//input[@id='fromDate_input']"), utils.addDaysToCurrentDate(from, "dd/MM/yy"));
            utils.sendText(By.xpath("//input[@id='toDate_input']"), utils.addDaysToCurrentDate(from, "dd/MM/yy"));
            utils.clickLocatorBasedOnIndex(1, By.xpath("//button [@aria-label='Select date']"));
            utils.sleep(1000);
            utils.actionsClick(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(from, "EEE") + "']"));
            if (acrossFullTrip) {
                utils.sendText(By.xpath("//input[@id='toDate_input']"), utils.addDaysToCurrentDate(to, "dd/MM/yy"));
                utils.actionsClick(By.xpath("//span[text()='" + utils.getDayOfWeekBasedOnDaysAdded(to, "EEE") + "']"));
            }
        }

        utils.selectByVisibleText(By.xpath("//select[@id='EmbarkRoutepoint']"), boardingStop);
        utils.selectByVisibleText(By.xpath("//select[@id='DisembarkRoutepoint']"), disembarkStop);
        commonMethodsPage.clickSave();
        if (multiplePassengers) {
            utils.sleep(300000);
        }
        utils.assertElementPresent(By.xpath("//h2[text()='Task ran successfully']"));
        utils.clickBtn(By.xpath("//span[text()='Close']"));
    }


    public void assertPassengersAreAddedToTheTrip(String trip, boolean acrossFullTrip, int day1, int day2, String existingPassenger, String addedPassenger) {
        utils.sleep(1000);
        utils.refreshPage();
        try {
            utils.sleep(1000);
            utils.clickOnDesiredNumberOfLocator(day1, By.xpath("//p[text()='" + trip + "']"));
        } catch (Exception e) {
            utils.sleep(2000);
            utils.clickOnDesiredNumberOfLocator(4, By.xpath("//span[@class='MuiIconButton-label']"));
            utils.sleep(1000);
            utils.clickOnDesiredNumberOfLocator(day1, By.xpath("//p[text()='" + trip + "']"));
        }
        utils.sleep(1000);
        utils.clickBtn(By.xpath("//span[text()='VIEW TRIP']"));
        utils.assertElementPresent(By.xpath("//label[text()='Boarding passengers']/..//p[contains(text(),'" + existingPassenger + "')]"));
        utils.assertElementPresent(By.xpath("//label[text()='Boarding passengers']/..//p[contains(text(),'" + addedPassenger + "')]"));
        utils.assertElementPresent(By.xpath("//label[text()='Disembarking passengers']/..//p[contains(text(),'" + existingPassenger + "')]"));
        utils.assertElementPresent(By.xpath("//label[text()='Disembarking passengers']/..//p[contains(text(),'" + addedPassenger + "')]"));
        //asserting that the passenger is not added to the second day of the trip
        utils.navigateBack();
        utils.sleep(2000);
        try {
            utils.clickOnDesiredNumberOfLocator(day2, By.xpath("//p[text()='" + trip + "']"));
        } catch (Exception e) {
            utils.sleep(2000);
            utils.clickOnDesiredNumberOfLocator(4, By.xpath("//span[@class='MuiIconButton-label']"));
            utils.clickOnDesiredNumberOfLocator(day2, By.xpath("//p[text()='" + trip + "']"));
        }
        utils.sleep(1000);
        try {
            utils.clickBtn(By.xpath("//span[text()='VIEW TRIP']"));
        } catch (Exception e) {
            utils.sleep(2000);
            utils.clickOnDesiredNumberOfLocator(4, By.xpath("//span[@class='MuiIconButton-label']"));
            utils.sleep(2000);
            utils.clickOnDesiredNumberOfLocator(day1, By.xpath("//p[text()='" + trip + "']"));
            utils.clickBtn(By.xpath("//span[text()='VIEW TRIP']"));
        }

        utils.sleep(1000);
        if (acrossFullTrip) {
            utils.assertElementPresent(By.xpath("//label[text()='Boarding passengers']/..//p[contains(text(),'" + existingPassenger + "')]"));
            utils.assertElementPresent(By.xpath("//label[text()='Boarding passengers']/..//p[contains(text(),'" + addedPassenger + "')]"));
            utils.assertElementPresent(By.xpath("//label[text()='Disembarking passengers']/..//p[contains(text(),'" + existingPassenger + "')]"));
            utils.assertElementPresent(By.xpath("//label[text()='Disembarking passengers']/..//p[contains(text(),'" + addedPassenger + "')]"));
            Assert.assertNotEquals(utils.getTextBasedOnIndex(3, By.xpath("//input[@class='MuiInputBase-input MuiInput-input MuiInputBase-inputMarginDense MuiInput-inputMarginDense']")), utils.getTextBasedOnIndex(6, By.xpath("//input[@class='MuiInputBase-input MuiInput-input MuiInputBase-inputMarginDense MuiInput-inputMarginDense']")));

        } else {
            utils.assertElementPresent(By.xpath("//label[text()='Boarding passengers']/..//p[contains(text(),'" + existingPassenger + "')]"));
            utils.assertElementAbsent(By.xpath("//label[text()='Boarding passengers']/..//p[contains(text(),'" + addedPassenger + "')]"));
            utils.assertElementPresent(By.xpath("//label[text()='Disembarking passengers']/..//p[contains(text(),'" + existingPassenger + "')]"));
            utils.assertElementAbsent(By.xpath("//label[text()='Disembarking passengers']/..//p[contains(text(),'" + addedPassenger + "')]"));

        }
    }

    public void assertPassengersAreDeletedAcrossTrip(String trip, String removedPassenger, String existingPassenger, boolean fullLength) {
        try {
            utils.clickOnDesiredNumberOfLocator(1, By.xpath("//p[text()='" + trip + "']"));
        } catch (Exception e) {
            utils.sleep(2000);
            utils.clickOnDesiredNumberOfLocator(4, By.xpath("//span[@class='MuiIconButton-label']"));
            utils.clickOnDesiredNumberOfLocator(1, By.xpath("//p[text()='" + trip + "']"));
        }
        utils.sleep(2000);
        utils.clickBtn(By.xpath("//span[text()='VIEW TRIP']"));
        utils.refreshPage();
        utils.assertElementPresent(By.xpath("//label[text()='Boarding passengers']/..//p[contains(text(),'" + existingPassenger + "')]"));
        utils.assertElementAbsent(By.xpath("//label[text()='Boarding passengers']/..//p[contains(text(),'" + removedPassenger + "')]"));
        utils.assertElementPresent(By.xpath("//label[text()='Disembarking passengers']/..//p[contains(text(),'" + existingPassenger + "')]"));
        utils.assertElementAbsent(By.xpath("//label[text()='Disembarking passengers']/..//p[contains(text(),'" + removedPassenger + "')]"));
        //asserting that the passenger is not added to the second day of the trip
        if (fullLength) {
            utils.navigateBack();
            try {
                utils.clickOnDesiredNumberOfLocator(2, By.xpath("//p[text()='" + trip + "']"));
            } catch (Exception e) {
                utils.clickOnDesiredNumberOfLocator(4, By.xpath("//span[@class='MuiIconButton-label']"));
                utils.clickOnDesiredNumberOfLocator(2, By.xpath("//p[text()='" + trip + "']"));

            }
            utils.sleep(2000);
            utils.clickBtn(By.xpath("//span[text()='VIEW TRIP']"));
            utils.sleep(1000);
            utils.assertElementPresent(By.xpath("//label[text()='Boarding passengers']/..//p[text()='" + existingPassenger + "']"));
            utils.assertElementPresent(By.xpath("//label[text()='Disembarking passengers']/..//p[text()='" + existingPassenger + "']"));
            Assert.assertNotEquals(utils.getTextBasedOnIndex(3, By.xpath("//input[@class='MuiInputBase-input MuiInput-input MuiInputBase-inputMarginDense MuiInput-inputMarginDense']")), utils.getTextBasedOnIndex(6, By.xpath("//input[@class='MuiInputBase-input MuiInput-input MuiInputBase-inputMarginDense MuiInput-inputMarginDense']")));
        }
    }


    public void adding_deletingExtraWayPointFromEditScreen(boolean add, String stop) throws InterruptedException {

        if (add) {
            utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), "London Road");
            utils.clickBtn(By.xpath("//p[text()='" + stop + "']"));
            utils.sleep(1000);
            utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
            utils.sendText(By.xpath("//span[text()='" + stop + "']/../..//input[@id='arrivalDateTime_input']"), utils.addDaysToCurrentDate(0, "dd/MM/yy") + ", " + "13:30");
        } else {
            utils.clickBtn(By.xpath("//span[text()='" + stop + "']/../..//span[text()='REMOVE']"));
        }
        commonMethodsPage.clickSave();
        utils.assertElementPresent(By.xpath("//h2[text()='Are you sure you want to make these changes?']"));
        utils.clickBtn(By.xpath("//span[text()='SAVE ANYWAY']"));

    }

    public void createContracts(String contract, String observable) throws InterruptedException {
        utils.assertElementPresent(By.xpath("//h5[text()='Please enter the details of the contract']"));
        utils.refreshPage();
        commonMethodsPage.clickAdd();
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), contract);
        utils.clickBtn(By.id("select-Searchable By"));
        utils.clickBtn(By.xpath("//li[@data-value='" + observable + "']"));
        utils.clickBtn(By.xpath("//div[@id='select-Trigger Type']"));
        utils.clickBtn(By.xpath("//li[@data-value='NO_LOGON']"));
        utils.sendText(By.xpath("//label[text()='minutes']/..//input"), "5");
        utils.clickBtn(By.id("select-Action Type"));
        utils.clickBtn(By.xpath("//li[text()='Actionplan']"));
        utils.clickBtn(By.id("select-Template"));
        utils.clickBtn(By.xpath("//li[@role='option'][contains(text(),'" + actionPlan + "')]"));
        commonMethodsPage.clickSave();
    }

    public void assertCreatedContract(String contract) {
        utils.assertElementPresent(By.xpath("//span[contains(text(),'" + contract + "')]"));

    }

    public void assertAWayPoint(boolean status, String wayPoint) {
        if (status) {
            utils.assertElementPresent(By.xpath("//span[text()='" + wayPoint + "']"));
        } else {
            utils.assertElementAbsent(By.xpath("//span[text()='" + wayPoint + "']"));
        }

    }

    public void addSupplierDetailsForTripSchedule(String supplier, int fromDate, int toDate, String capacity, boolean threeYearTrip, boolean failedAddition) throws InterruptedException {
        utils.assertElementPresent(By.xpath("//h6[text()='Please enter the details of the suppliers']"));
        commonMethodsPage.clickAdd();
        utils.clickOnDesiredNumberOfLocator(5, By.xpath("//div[@role='button']"));
        utils.clickBtn(By.xpath("//li[text()='" + supplier + "']"));
        utils.clickBtn(By.xpath("//label[text()='Primary Driver']"));
        utils.clickBtn(By.xpath("//li[text()='driverone']"));
        utils.clickBtn(By.xpath("//label[text()='Secondary Driver']"));
        utils.clickBtn(By.xpath("//li[text()='drivertwo']"));
        utils.clickBtn(By.xpath("//label[text()='Primary Vehicle']"));
        utils.clickBtn(By.xpath("//span[text()='AB12 QED']"));
        utils.clickBtn(By.xpath("//label[text()='Secondary Vehicle']"));
        utils.clickOnDesiredNumberOfLocator(2, By.xpath("//span[text()='AB12 QED']"));
        utils.sendText(By.xpath("//input[@class='MuiInputBase-input MuiInput-input']"), capacity);
        utils.clickOnDesiredNumberOfLocatorAndSendText(1, By.xpath("//input[@class='rw-widget-input rw-input']"), utils.addDaysToCurrentDate(fromDate, "dd/MM/yy"));
        if (threeYearTrip) {
            utils.clickOnDesiredNumberOfLocatorAndSendText(2, By.xpath("//input[@class='rw-widget-input rw-input']"), utils.addYearsToCurrentDate(3));
        }
       if(!threeYearTrip) {utils.clickOnDesiredNumberOfLocatorAndSendText(2, By.xpath("//input[@class='rw-widget-input rw-input']"), utils.addDaysToCurrentDate(toDate, "dd/MM/yy"));}
        utils.sendText(By.xpath("//label[text()='" + utils.getDayOfWeekBasedOnDaysAdded(fromDate, "EEEE").toLowerCase() + "']/../div/div[1]//input"), "10");
        utils.sendText(By.xpath("//label[text()='" + utils.getDayOfWeekBasedOnDaysAdded(fromDate, "EEEE").toLowerCase() + "']/../div/div[2]//input"), "15");
        utils.sendText(By.xpath("//label[text()='" + utils.getDayOfWeekBasedOnDaysAdded(toDate, "EEEE").toLowerCase() + "']/../div/div[1]//input"), "10");
        utils.sendText(By.xpath("//label[text()='" + utils.getDayOfWeekBasedOnDaysAdded(toDate, "EEEE").toLowerCase() + "']/../div/div[2]//input"), "15");
        commonMethodsPage.clickSave();
        if (failedAddition) {
            return;
        }
        if (threeYearTrip) {
            utils.sleep(250000);
        }
        utils.assertElementPresent(By.xpath("//h2[text()='Task ran successfully']"));
        utils.clickBtn(By.xpath("//span[text()='Close']"));

    }

    public void addSecondSupplier(int fromDate, int toDate) throws InterruptedException {
        utils.assertElementPresent(By.xpath("//h6[text()='Please enter the details of the suppliers']"));
        commonMethodsPage.clickAdd();
        utils.sleep(1000);
        utils.scrollToView(By.xpath("//span[text()='REMOVE']"));
        utils.sleep(1000);
        utils.clickOnDesiredNumberOfLocator(7, By.xpath("//div[@class='MuiInputBase-root MuiInput-root MuiInput-underline MuiInputBase-formControl MuiInput-formControl']"));
        utils.clickBtn(By.xpath("//li[text()='OrgCreatedForAutoTest2']"));
        utils.sleep(2000);
        utils.clickOnDesiredNumberOfLocator(7, By.xpath("//div[@class='MuiInputBase-root MuiInput-root MuiInput-underline MuiInputBase-formControl MuiInput-formControl']"));
        utils.clickBtn(By.xpath("//li[text()='adam']"));
        utils.sleep(2000);
        utils.clickOnDesiredNumberOfLocator(12, By.xpath("//div[@class='MuiFormControl-root']"));
        utils.clickBtn(By.xpath("//li[text()='orgdriver']"));
        utils.sleep(1000);
        utils.clickOnDesiredNumberOfLocator(9, By.xpath("//div[@class='MuiInputBase-root MuiInput-root MuiInput-underline MuiInputBase-formControl MuiInput-formControl']"));
        utils.clickBtn(By.xpath("//span[text()='yx63 ews']"));
        utils.sleep(1000);
        utils.clickOnDesiredNumberOfLocator(10, By.xpath("//div[@class='MuiInputBase-root MuiInput-root MuiInput-underline MuiInputBase-formControl MuiInput-formControl']"));
        utils.clickOnDesiredNumberOfLocator(2, By.xpath("//span[text()='yx63 ews']"));
        utils.clickOnDesiredNumberOfLocatorAndSendText(2, By.xpath("//input[@class='MuiInputBase-input MuiInput-input']"), "4");
        utils.clickOnDesiredNumberOfLocatorAndSendText(3, By.xpath("//input[@class='rw-widget-input rw-input']"), utils.addDaysToCurrentDate(fromDate, "dd/MM/yy"));
        utils.clickOnDesiredNumberOfLocatorAndSendText(4, By.xpath("//input[@class='rw-widget-input rw-input']"), utils.addDaysToCurrentDate(toDate, "dd/MM/yy"));
        utils.sendText(By.xpath("//label[text()='" + utils.getDayOfWeekBasedOnDaysAdded(fromDate, "EEEE").toLowerCase() + "']/../div/div[1]//input"), "10");
        utils.sendText(By.xpath("//label[text()='" + utils.getDayOfWeekBasedOnDaysAdded(fromDate, "EEEE").toLowerCase() + "']/../div/div[2]//input"), "15");
        utils.sendText(By.xpath("//label[text()='" + utils.getDayOfWeekBasedOnDaysAdded(toDate, "EEEE").toLowerCase() + "']/../div/div[1]//input"), "10");
        utils.sendText(By.xpath("//label[text()='" + utils.getDayOfWeekBasedOnDaysAdded(toDate, "EEEE").toLowerCase() + "']/../div/div[2]//input"), "15");
        commonMethodsPage.clickSave();


    }

    public void add_Remove_RoutePointAcrossFullLengthOfTrip(String routePoint, boolean remove, boolean longPropagation) {
        utils.sleep(1000);
        utils.sendText(By.xpath("//input[@id='fromDate_input']"), utils.addDaysToCurrentDate(1, "dd/MM/yy"));
        utils.clickBtn(By.xpath("//h5[text()='Edit Route Points']"));


        if (remove) {
            utils.clickBtn(By.xpath("//span[text()='" + routePoint + "']/../..//span[text()='REMOVE']"));
        } else if (remove == false) {
            if (utils.checkIfElementIsDisplayed(By.xpath("//p[text()='Search for a location']/..//input"))) {
                utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), routePoint);

                utils.clickBtn(By.xpath("//p[text()='" + routePoint + "']"));
                utils.sleep(1000);
                utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
            }

        }
        commonMethodsPage.clickSubmit();
        utils.assertElementPresent(By.xpath("//h2[text()='Are you sure you want to make these changes?']"));
        utils.clickBtn(By.xpath("//span[text()='SAVE ANYWAY']"));
        utils.sleep(3000);
        utils.clickBtn(By.xpath("//*[text()='Close']"));
        if (longPropagation) {
            utils.sleep(100000);
        }
    }


    public void deleteRoutePointAcrossFullTrip(int index, boolean particularDayOnly) {
        utils.sleep(1000);
        utils.sendText(By.xpath("//input[@id='fromDate_input']"), utils.addDaysToCurrentDate(1, "dd/MM/yy"));
        if (particularDayOnly) {
            utils.sendText(By.xpath("//input[@id='toDate_input']"), utils.addDaysToCurrentDate(1, "dd/MM/yy"));
        }

        utils.clickOnDesiredNumberOfLocator(index, By.xpath("//span[text()='REMOVE']"));
        commonMethodsPage.clickSubmit();
        utils.assertElementPresent(By.xpath("//h2[text()='Are you sure you want to make these changes?']"));
        utils.clickBtn(By.xpath("//span[text()='SAVE ANYWAY']"));
        utils.sleep(3000);
        utils.clickBtn(By.xpath("//*[text()='Close']"));


    }

    public void addSupplierForSingleDay(String supplier, String driver, String vehicleReg, String vehicleCapacity) throws InterruptedException {
        utils.assertElementPresent(By.xpath("//h6[text()='Please enter the details of the supplier']"));
        utils.clickBtn(By.id("select-supplierId"));
        utils.sleep(1000);
        utils.actionsClick(By.xpath("//li[text()='" + supplier + "']"));
        utils.clickBtn(By.xpath("//label[text()='Primary Driver']"));
        List<WebElement> names = BaseClass.driver.findElements(By.xpath("//li[@role='option']"));
        for (WebElement element : names) {
            Assert.assertFalse(element.getText().contains("passenger"));
        }
        utils.clickBtn(By.xpath("//li[text()='" + driver + "']"));
        utils.clickBtn(By.xpath("//label[text()='Secondary Driver']"));
        utils.clickBtn(By.xpath("//li[text()='" + driver + "']"));
        utils.clickBtn(By.xpath("//label[text()='Primary Vehicle']"));
        utils.clickBtn(By.xpath("//span[text()='" + vehicleReg + "']"));
        utils.clickBtn(By.xpath("//label[text()='Secondary Vehicle']"));
        utils.clickOnDesiredNumberOfLocator(2, By.xpath("//span[text()='" + vehicleReg + "']"));
        utils.sendText(By.xpath("//input[@name='vehicleCapacity']"), vehicleCapacity);
        utils.sendText(By.xpath("//label[text()='cost']/..//input"), "10");
        utils.sendText(By.xpath("//label[text()='sell']/..//input"), "15");
        commonMethodsPage.clickSave();
        //commonMethodsPage.clickSubmit();

    }


    public void assertSavedSupplierDetails() {
        utils.assertElementPresent(By.xpath("//div[@id='select-supplierId'][text()='SupplierForAutoTest']"));
        utils.assertElementPresent(By.xpath("//label[text()='Primary Driver']/..//div[text()='driverone']"));
        utils.assertElementPresent(By.xpath("//div[@id='select-primaryVehicleId']//span[text()='AB12 QED']"));
        utils.assertElementPresent(By.xpath("//label[text()='Vehicle Capacity']/..//input[@value='10']"));
        utils.assertElementPresent(By.xpath("//label[text()='sell']/..//input[@value='15']"));
        utils.assertElementPresent(By.xpath("//label[text()='cost']/..//input[@value='10']"));
    }

    public void assertSavedSupplierDeleted() {
        utils.assertElementAbsent(By.xpath("//div[@id='select-Supplier'][text()='SupplierForAutoTest']"));
    }

    public void clickSupplierOnEditTripPage() {
        utils.clickBtn(By.xpath("//span[text()='Supplier']"));
    }

    public void assertActionPlanCreation(String actionPlan) {
        utils.assertElementPresent(By.xpath("//span[text()='" + actionPlan + "']"));
    }

    public void editAnActionPlan() throws InterruptedException {
        utils.clickBtn(By.xpath("//span[text()='EDIT PLAN']"));
        utils.sendText(By.xpath("//input[@value='actionPlan']"), "updatedActionPlan");
        commonMethodsPage.clickSave();

    }

    public void bulkUpload(String path, String file) throws AWTException, InterruptedException {
        utils.clickBtn(By.xpath("//p[text()='Drag and drop some files here, or click to select files']"));
        utils.sleep(1000);
        utils.uploadFile(path);
        utils.assertElementPresent(By.xpath("//span[text()='" + file + ".csv']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='UPLOAD']"));
        utils.sleep(2000);
        if (utils.checkIfElementIsDisplayed(By.xpath("//span[text()='UPLOAD']"))) {
            utils.sleep(1000);
            utils.javaScriptExecutorClick(By.xpath("//span[text()='UPLOAD']"));

        }

    }

    public void uploadTandCsAsAPDFAndAssertFileUpload(String path, boolean updateTandCs, String text) throws InterruptedException, AWTException, IOException {
        utils.clickBtn(By.xpath("//p[text()='Drag and drop a pdf here or click']"));
        utils.sleep(1000);
        utils.uploadFile(path);
        utils.clickBtn(By.xpath("//span[text()='SAVE']"));
        utils.assertElementPresent(By.xpath("//h6[text()='Contracts']"));
        if (updateTandCs) {
            utils.refreshPage();
            utils.sleep(10000);
            utils.refreshPage();
            utils.sleep(10000);
            utils.refreshPage();
            utils.sleep(20000);
            utils.refreshPage();

        } else {
            utils.sleep(35000);
            utils.refreshPage();
            utils.sleep(25000);
            utils.refreshPage();
            utils.clickBtn(By.xpath("//span[text()='VIEW BOOKING T&Cs']"));
            try {
                Assert.assertEquals(driver.getWindowHandles().size(), 2);
            } catch (AssertionError a) {
                utils.sleep(25000);
                navigateToEditRules_or_TandCs("newOrg" + randomNumber + "", "searchableByObservers", true);
                utils.clickBtn(By.xpath("//p[text()='Drag and drop a pdf here or click']"));
                utils.sleep(1000);
                utils.uploadFile(path);
                utils.clickBtn(By.xpath("//span[text()='SAVE']"));
                utils.assertElementPresent(By.xpath("//h6[text()='Contracts']"));
                utils.refreshPage();
                utils.clickBtn(By.xpath("//span[text()='VIEW BOOKING T&Cs']"));
                Assert.assertEquals(driver.getWindowHandles().size(), 2);
            }
            utils.switchToNewWindow();
            utils.readTextfromURL(driver.getCurrentUrl(), text);
            driver.close();
            utils.switchBackToPreviousWindow();

        }
    }

    public void checkProcessOfBulkUpLoadAndAssertDataUpload(boolean upload) throws InterruptedException {
        chooseDesiredOption_OrganisationPage("People");
        utils.clickBtn(By.xpath("//span[text()='Bulk Uploads']"));
        utils.assertElementPresent(By.xpath("//h6[text()='Bulk Uploads']"));
        try {
            utils.assertElementPresent(By.xpath("//td[text()='SUCCESS']"));
        } catch (Exception e) {
            utils.sleep(10000);
            utils.refreshPage();
            utils.assertElementPresent(By.xpath("//td[text()='SUCCESS']"));
        }

        utils.navigateBack();
        utils.refreshPage();
        utils.assertElementPresent(By.xpath("//h2[text()='Roles']"));
        searchPersonProfileUnderDesiredRole("Total", "Ali");
        if (upload) {

            utils.assertElementPresent(By.xpath("//span[text()='Ali Anderson']"));
        } else {

            utils.assertElementAbsent(By.xpath("//span[text()='Ali Anderson']"));
        }

    }

    public void checkIfObserverIsAddedToAllPassengers() {
        utils.clickBtn(By.xpath("//span[text()='Ali Anderson']"));
        utils.assertElementPresent(By.xpath("//span[text()='mrs t anderson']"));
        utils.assertElementPresent(By.xpath("//span[text()='dr j anderson']"));
        utils.navigateBack();
        searchPersonProfileUnderDesiredRole("Total", "jia");
        utils.clickBtn(By.xpath("//span[text()='Jia  Anderson']"));
        utils.assertElementPresent(By.xpath("//span[text()='mrs t anderson']"));
        utils.assertElementPresent(By.xpath("//span[text()='dr j anderson']"));
        utils.navigateBack();
        searchPersonProfileUnderDesiredRole("Total", "Roan");
        utils.clickBtn(By.xpath("//span[text()='Roan Anderson']"));
        utils.assertElementPresent(By.xpath("//span[text()='mrs t anderson']"));
        utils.assertElementPresent(By.xpath("//span[text()='dr j anderson']"));
    }

    public void loginToOneSignalAndCheckNotifications(String passenger) {
        utils.loadUrl("https://app.onesignal.com/login");
        //utils.clickBtn(By.xpath("//a[text()='Log In']"));
        utils.sendText(By.xpath("//input[@id='field_email']"), "sysadmin@coachhire.com");
        utils.sendText(By.xpath("//input[@id='field_password']"), "Jk%ADvP3mdb");
        utils.clickBtn(By.xpath("//span[text()='Log In']"));
        utils.clickBtn(By.xpath("//strong[text()='Kura']"));
        utils.clickBtn(By.xpath("//div[text()='Delivery']"));
        utils.assertElementPresent(By.xpath("//a[contains(text(),'minutes late to the expected schedule') and contains(text(),'The vehicle for " + passenger + " is currently')]"));


    }

    public void assertAuthPermissions() {
        utils.clickLocatorBasedOnIndex(1, By.xpath("//div[@class='MuiListItemIcon-root']"));
        homePage.searchAndPickOrganisation("newOrg" + randomNumber + "");
        utils.assertElementPresent(By.xpath("//span[text()='People']"));
        utils.assertElementPresent(By.xpath("//span[text()='Auth Groups']"));
        utils.assertElementPresent(By.xpath("//span[text()='Contracts']"));
        utils.assertElementPresent(By.xpath("//span[text()='Action Plans']"));
        utils.assertElementPresent(By.xpath("//span[text()='Trips']"));
        utils.assertElementPresent(By.xpath("//span[text()='Vehicles']"));
        utils.assertElementAbsent(By.xpath("//span[text()='EDIT ']"));
    }

    public void exportData(String file) throws InterruptedException, IOException {
        utils.deleteAllFilesWithSpecifiedExtension(System.getProperty("user.home") + "\\Downloads", ".csv");
        utils.sleep(1000);
        utils.clickBtn(By.xpath("//button[@title='Export']"));
        utils.clickBtn(By.xpath("//li[text()='Export as CSV']"));
        utils.sleep(2000);
        utils.readFileAndAssertContent(System.getProperty("user.home") + "\\Downloads\\" + file + ".csv", "passengerone");
        utils.deleteAllFilesWithSpecifiedExtension(System.getProperty("user.home") + "\\Downloads", ".csv");

    }


    public void navigateToCreateTripsPageFromOrgPage() throws InterruptedException {
        chooseDesiredOption_OrganisationPage("Trips");
        hoverOverPlusAndClickDesiredOption_OrganisationsPage("1");
    }

    public void navigateToEditRules_or_TandCs(String org, String contract, boolean TandC) {
        utils.clickLocatorBasedOnIndex(1, By.xpath("//div[@class='MuiListItemIcon-root']"));
        homePage.searchAndPickOrganisation(org);
        chooseDesiredOption_OrganisationPage("Contracts");
        try {
            utils.sleep(15000);
            utils.refreshPage();
            utils.assertElementPresent(By.xpath("//span[text()='" + contract + "']/../../..//*[text()='EDIT RULES']"));
        } catch (Exception e) {
            utils.refreshPage();
            utils.sleep(15000);
            utils.refreshPage();
            utils.assertElementPresent(By.xpath("//span[text()='" + contract + "']/../../..//*[text()='EDIT RULES']"));
        }
        if (TandC) {
            utils.clickBtn(By.xpath("//span[text()='" + contract + "']/../../..//*[text()='EDIT BOOKING T&Cs']"));
        } else {
            utils.clickBtn(By.xpath("//span[text()='" + contract + "']/../../..//*[text()='EDIT RULES']"));
        }
    }


    public void overrideClientBooking(String trip) throws InterruptedException {
        utils.assertElementPresent(By.xpath("//label[text()='Override Rules']"));
        utils.scrollToView(By.xpath("//span[text()='SAVE']"));
        try {
            utils.clickBtn(By.xpath("//div[@id='select-Route']"));
        } catch (Exception e) {
            utils.clickOnDesiredNumberOfLocator(2, By.xpath("//span[text()='ADD']"));
            utils.clickBtn(By.xpath("//div[@id='select-Route']"));
        }
        utils.sleep(1000);
        utils.actionsClick(By.xpath("//li[text()='" + trip + "']"));
        utils.clickBtn(By.xpath("//div[text()='" + trip + "']/../../../..//span[text()='REMOVE']"));
        utils.clickOnDesiredNumberOfLocator(2, By.xpath("//span[text()='ADD']"));
        utils.clickBtn(By.xpath("//div[@id='select-Route']"));
        utils.sleep(1000);
        utils.actionsClick(By.xpath("//li[text()='" + trip + "']"));
        utils.scrollToView(By.xpath("//span[text()='SAVE']"));
        utils.clickBtn(By.xpath("//span[text()='Expand']"));
        utils.clickBtn(By.xpath("//div[text()='" + trip + "']/../../../..//span[text()='ADD']"));
        utils.clickBtn(By.xpath("//div[text()='" + trip + "']/../../../..//div[@id='select-Trigger Type']"));
        utils.clickBtn(By.xpath("//li[text()='Expected Disembark']"));
        utils.clickBtn(By.xpath("//div[text()='" + trip + "']/../../../..//div[@id='select-Action Type']"));
        utils.clickBtn(By.xpath("//li[text()='Notification']"));
        utils.clickBtn(By.xpath("//div[text()='" + trip + "']/../../../..//div[@id='select-Audience']"));
        utils.clickBtn(By.xpath("//li[text()='Observers']"));
        utils.clickBtn(By.xpath("//div[text()='" + trip + "']/../../../..//div[@id='select-Template']"));
        utils.clickBtn(By.xpath("//li[text()='Simple Disembark']"));
        utils.clickBtn(By.xpath("//span[text()='SAVE']"));
    }

    public void setSearchableRuleOnAContract(String searchableRule) {
        utils.clickBtn(By.xpath("//div[@id='select-Searchable By']"));
        utils.actionsClick(By.xpath("//li[@data-value='" + searchableRule + "']"));
        utils.clickBtn(By.xpath("//button[@type='submit']"));
    }

    public void navigateToViewSchedule(String passenger) {
        searchPersonProfileUnderDesiredRole("Total", passenger);
        utils.clickBtn(By.xpath("//span[text()='" + passenger + "']"));
        utils.clickBtn(By.xpath("//span[text()[contains(., 'View scheduled trips')]]"));

    }

    public void assertTripUnderPassengerTripSchedule(String trip) {
        try {
            utils.assertElementPresent(By.xpath("//p[text()='" + trip + "']"));
        } catch (Exception e) {
            utils.sleep(90000);
            utils.refreshPage();
            utils.assertElementPresent(By.xpath("//p[text()='" + trip + "']"));
        }
    }

    public void assertNoTripUnderPassengerTripSchedule(String trip) {
        utils.assertElementAbsent(By.xpath("//p[text()='" + trip + "']"));
    }

    public void removePassengerFromTrip_viaPassengerSchedule(String trip) {
        utils.clickBtn(By.xpath("//p[text()='" + trip + "']"));
        utils.clickBtn(By.xpath("//span[text()='Remove']"));
        utils.clickBtn(By.xpath("//span[text()='CONFIRM']"));
        utils.refreshPage();
        if (utils.checkIfElementIsDisplayed(By.xpath("//p[text()='" + trip + "']"))) {
            utils.clickBtn(By.xpath("//p[text()='" + trip + "']"));
            utils.clickBtn(By.xpath("//span[text()='Remove']"));
            utils.clickBtn(By.xpath("//span[text()='CONFIRM']"));

        }
    }

    public void navigateToBookingsPage() {
        utils.clickBtn(By.xpath("//span[text()='Bookings']"));
        utils.assertElementPresent(By.xpath("//h6[text()='Booking Summary']"));
    }

    public void assertPassengerAdd_OrDeleteEntryUnderBookingsPage(boolean remove) {
        if (remove) {
            utils.assertElementPresent(By.xpath("//td[text()='passengerfive']/..//td[text()='REMOVE']/..//td[text()='" + tripName + "']"));
        } else {
            utils.assertElementPresent(By.xpath("//td[text()='passengerfive']/..//td[text()='ADD']/..//td[text()='" + tripName + "']"));
        }
    }

    public void clickEditTripAndSupplier(String org) throws Exception {
        navigateToTripSummaryDropDown(org, 1, tripName);
        clickViewOREditTripOnSummaryDropDown("EDIT TRIP");
        clickSupplierOnEditTripPage();
    }


    public void navigateToEditTripSchedule_AddExclusionDates() throws Exception {
        navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, tripName);
        clickViewOREditTripOnSummaryDropDown("Edit Trip Schedule");
        utils.assertElementPresent(By.xpath("//h5[text()='Please enter the details of the trip schedule']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//button[@aria-label='Open calendar']"));
        utils.sleep(1000);
        List<WebElement> fromDate = driver.findElements(By.xpath("//td[text()='" + utils.addDaysToCurrentDate(1, "dd") + "']"));
        if (fromDate.size() > 1) {
            utils.sleep(1000);
            fromDate.get(1).click();
        } else {
            utils.sleep(1000);
            fromDate.get(0).click();
        }
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//button[@aria-label='Open calendar']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//button[@aria-label='Open calendar']"));
        utils.sleep(1000);
        List<WebElement> toDate = driver.findElements(By.xpath("//td[text()='" + utils.addDaysToCurrentDate(2, "dd") + "']"));
        if (toDate.size() > 1) {
            utils.sleep(1000);
            toDate.get(1).click();
        } else {
            utils.sleep(1000);
            toDate.get(0).click();
        }
        commonMethodsPage.clickSave();
        try {
            utils.assertElementPresent(By.xpath("//h2[text()='Task ran successfully']"));
        } catch (Exception e) {
            utils.sleep(300000);
            utils.assertElementPresent(By.xpath("//h2[text()='Task ran successfully']"));
        }
        utils.assertElementPresent(By.xpath("//span[text()='Close']"));
        utils.clickBtn(By.xpath("//span[text()='Close']"));
    }

    public void sendInvitationForLinkUp(String role, String typeOfInvitation) {
        utils.clickBtn(By.xpath("//p[text()='" + role + "']/../../following-sibling::div//span[text()='" + typeOfInvitation + "']"));
    }

    public void findPersonProfileAndUploadDocument(String personProfile) throws InterruptedException, AWTException {
        utils.sendTxtWithOutClearing(By.xpath("//input[@placeholder='Search']"), personProfile);
        utils.clickBtn(By.xpath("//span[text()='observer" + personProfile + "']"));
        utils.clickBtn(By.xpath("//button[@title='Add']"));
        utils.clickBtn(By.xpath("//p[text()='Drag and drop an image file here or click']"));
        utils.sleep(1000);
        utils.uploadFile(System.getProperty("user.dir") + "\\Bulkupload\\sampleDocument.jpg");
        utils.sendText(By.xpath("//input[@id='title']"), "sampleDocument");
        utils.actionsClick(By.xpath("//div[@id='select-Type']"));
        utils.actionsClick(By.xpath("//li[text()='Dbs']"));
        utils.clickBtn(By.xpath("//span[@class='rw-i rw-i-calendar']"));
        utils.actionsClick(By.xpath("//td[text()='" + utils.addDaysToCurrentDate(0, "dd") + "']"));
        utils.sleep(2000);
        utils.clickOnDesiredNumberOfLocator(2, By.xpath("//span[@class='rw-i rw-i-calendar']"));
        utils.sleep(2000);
        boolean find = true;
        int i = 1;
        while (find) {
            try {
                driver.findElements(By.xpath("//td[text()='" + utils.addDaysToCurrentDate(1, "dd") + "']")).get(1).click();
                find = false;
            } catch (Exception e) {
                i++;
                driver.findElements(By.xpath("//td[text()='" + utils.addDaysToCurrentDate(1, "dd") + "']")).get(i).click();
                break;


            }

        }
        utils.clickBtn(By.xpath("//span[text()='SAVE']"));
    }

    public void assertPaginationWorksOnBookingSummary() {
        String firstLineOnFirstPage = driver.findElement(By.xpath("//tr[@class='mt-MuiTableRow-root']")).getText();
        utils.clickOnDesiredNumberOfLocator(6, By.xpath("//span[@class='MuiIconButton-label']"));
        utils.sleep(1000);
        String firstLineOnNextPage = driver.findElement(By.xpath("//tr[@class='mt-MuiTableRow-root']")).getText();
        Assert.assertNotEquals(firstLineOnFirstPage, firstLineOnNextPage);
    }


    public void navigateToOrgFromAllOrgsPage(String org) {
        utils.sleep(2000);
        utils.clickBtn(By.xpath("//a[text()='" + org + "']"));
        utils.clickLocatorBasedOnIndex(3, By.xpath("//div[@class='MuiListItemIcon-root']"));
    }

    public void navigateToJourneyFromOrgPage() throws InterruptedException {
        chooseDesiredOption_OrganisationPage("Trips");
        utils.sleep(1000);
        utils.sendText(By.xpath("//input[@type='text']"), tripName);
        utils.clickBtn(By.xpath("//td[text()='" + tripName + "']"));
        utils.clickOnDesiredNumberOfLocator(1, By.xpath("//p[text()='" + tripName + "']"));
        clickViewOREditTripOnSummaryDropDown("VIEW TRIP");
        utils.clickBtn(By.xpath("//span[text()='View Journey']"));

    }

    public void navigateToEditAuthGroups(String org, String authGroup) {
        homePage.pickOrganisationFromOrgsPage(org);
        chooseDesiredOption_OrganisationPage("Auth Groups");
        utils.clickBtn(By.xpath("//a[text()='" + authGroup + "']"));
        utils.sleep(1000);
        utils.clickBtn(By.xpath("//span[text()[contains(.,'AuthGroup')]]"));
        utils.sleep(1000);
        utils.clickOnDesiredNumberOfLocator(3, By.xpath("//span[@class='MuiIconButton-label']"));

    }

    public void editJourneyReportingBookingPermissions(boolean assign) throws InterruptedException {
        if (assign) {
            utils.makeSureBoxIsChecked(By.xpath("//input[@value='VIEW_JOURNEY_MAP_SUB']"), By.xpath("//input[@value='VIEW_JOURNEY_MAP_SUB']"));
            utils.makeSureBoxIsChecked(By.xpath("//input[@value='VIEW_JOURNEY_REPORTING_MAP']"), By.xpath("//input[@value='VIEW_JOURNEY_REPORTING_MAP']"));
            utils.makeSureBoxIsChecked(By.xpath("//input[@value='VIEW_BOOKING_SUMMARY']"), By.xpath("//input[@value='VIEW_BOOKING_SUMMARY']"));
        } else {
            utils.makeSureBoxIsUnChecked(By.xpath("//input[@value='VIEW_JOURNEY_MAP_SUB']"), By.xpath("//input[@value='VIEW_JOURNEY_MAP_SUB']"));
            utils.makeSureBoxIsUnChecked(By.xpath("//input[@value='VIEW_JOURNEY_REPORTING_MAP']"), By.xpath("//input[@value='VIEW_JOURNEY_REPORTING_MAP']"));
            utils.makeSureBoxIsUnChecked(By.xpath("//input[@value='VIEW_BOOKING_SUMMARY']"), By.xpath("//input[@value='VIEW_BOOKING_SUMMARY']"));
        }
        commonMethodsPage.clickSave();
    }


    public void assertUserCanOrCannotViewAccessBookingMapsAndReporting(boolean view) {
        if (view) {
            utils.assertElementPresent(By.xpath("//span[text()[contains(.,'Bookings')]]"));
            utils.clickBtn(By.xpath("//span[text()[contains(.,'Bookings')]]"));
            utils.assertElementPresent(By.xpath("//h6[text()[contains(.,'Booking Summary')]]"));
            utils.navigateBack();
            utils.assertElementPresent(By.xpath("//span[text()[contains(.,'Map')]]"));
            utils.clickBtn(By.xpath("//span[text()[contains(.,'Map')]]"));
            Assert.assertEquals(driver.getCurrentUrl(), "https://uat.kura-tech.io/map/" + utils.splitUrlAndExtractOrgId(4) + "");
            utils.navigateBack();
            utils.assertElementPresent(By.xpath("//span[text()[contains(.,'Reporting')]]"));
            utils.clickBtn(By.xpath("//span[text()[contains(.,'Reporting')]]"));
            utils.assertElementPresent(By.xpath("//p[text()='MPH']"));
            utils.assertElementPresent(By.xpath("//span[text()='Line']"));
        } else {
            utils.assertElementAbsent(By.xpath("//span[text()[contains(.,'Bookings')]]"));
            utils.assertElementAbsent(By.xpath("//span[text()[contains(.,'Map')]]"));
            utils.assertElementAbsent(By.xpath("//span[text()[contains(.,'Reporting')]]"));

        }
    }

    public void viewTripsAndAssertRoutePointAndPassengerDeleted(String trip, String routePoint, boolean fullLength) throws Exception {
        navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, trip);
        clickViewOREditTripOnSummaryDropDown("VIEW TRIP");
        utils.assertElementAbsent(By.xpath("//p[text()='passengerone']"));
        utils.assertElementAbsent(By.xpath("//span[text()='" + routePoint + "']"));
        utils.navigateBack();
        if (fullLength) {
            utils.clickOnDesiredNumberOfLocator(2, By.xpath("//p[text()[contains(.,'" + trip + "')]]"));
            clickViewOREditTripOnSummaryDropDown("VIEW TRIP");
            utils.assertElementAbsent(By.xpath("//p[text()='passengerone']"));
            utils.assertElementAbsent(By.xpath("//span[text()='" + routePoint + "']"));
        } else {
            utils.clickOnDesiredNumberOfLocator(2, By.xpath("//p[text()[contains(.,'" + trip + "')]]"));
            clickViewOREditTripOnSummaryDropDown("VIEW TRIP");
            utils.assertElementPresent(By.xpath("//p[text()='passengerone']"));
            utils.assertElementPresent(By.xpath("//span[text()='" + routePoint + "']"));

        }
    }

    public void checkTripsCreatedForDaysSpecified() {
        int xyz = 0;
        for (int i = 0; i < 37; i++) {
            if (i < 1) {
                int fullCount = driver.findElements(By.xpath("//div[@class='rbc-date-cell']")).size();
                int exactNumber = fullCount - Integer.parseInt(utils.addDaysToCurrentDay(0, "dd"));
                int positiveExactNumber = Math.abs(exactNumber) + 1;
                Assert.assertEquals(driver.findElements(By.xpath("//p[text()='" + tripName + "']")).size(), positiveExactNumber);
                utils.sleep(1000);
                utils.clickOnDesiredNumberOfLocator(4, By.xpath("//span[@class='MuiIconButton-label']"));
            } else if (i > 0 && i < 36) {

                utils.sleep(2000);
                xyz = driver.findElements(By.xpath("//div[@class='rbc-date-cell']")).size() + 1;
                Assert.assertEquals(driver.findElements(By.xpath("//p[text()='" + tripName + "']")).size(), xyz);
                utils.sleep(1000);
                utils.clickOnDesiredNumberOfLocator(4, By.xpath("//span[@class='MuiIconButton-label']"));
            } else if (i > 35 && i == 36) {
                int fullCount = driver.findElements(By.xpath("//div[@class='rbc-date-cell']")).size();
                utils.sleep(2000);
                int numberToSubtract;
                numberToSubtract = fullCount - Integer.parseInt(utils.addDaysToCurrentDay(0, "dd"));
                int numberWanted = fullCount - Math.abs(numberToSubtract);
                Assert.assertEquals(driver.findElements(By.xpath("//p[text()='" + tripName + "']")).size(), numberWanted);


            }
        }
    }

    public void checkStuffAddedForDaysSpecified(Boolean passengers, boolean routePoint) throws InterruptedException {
        for (int i = 0; i < 37; i++) {
            utils.sleep(1000);
            utils.clickOnDesiredNumberOfLocator(1, By.xpath("//p[text()='" + tripName + "']"));

            if (passengers) {
                clickViewOREditTripOnSummaryDropDown("VIEW TRIP");
                utils.assertElementPresent(By.xpath("//p[text()[contains(.,'passa')]]"));
                utils.assertElementPresent(By.xpath("//p[text()[contains(.,'apass')]]"));
                utils.assertElementPresent(By.xpath("//p[text()[contains(.,'onepass')]]"));
                utils.assertElementPresent(By.xpath("//p[text()[contains(.,'twentypass')]]"));
                utils.assertElementPresent(By.xpath("//p[text()[contains(.,'thirtypass')]]"));
                utils.assertElementPresent(By.xpath("//p[text()[contains(.,'fourtypass')]]"));
            } else if (passengers == false && routePoint == false) {
                clickViewOREditTripOnSummaryDropDown("EDIT TRIP");
                clickSupplierOnEditTripPage();
                utils.assertElementPresent(By.xpath("//div[@id='select-Supplier'][text()='SupplierForAutoTest']"));
                utils.assertElementPresent(By.xpath("//div[@id='select-Primary Driver'][text()='driverone']"));
                utils.assertElementPresent(By.xpath("//span[text()='AB12 QED']"));
                utils.assertElementPresent(By.xpath("//input[@id='vehicleCapacity']/..//input[@value='10']"));
                utils.navigateBack();
            } else if (routePoint == true) {
                clickViewOREditTripOnSummaryDropDown("VIEW TRIP");
                utils.assertElementPresent(By.xpath("//span[text()[contains(.,'rc club bunker')]]"));

            }
            utils.navigateBack();
            utils.sleep(2000);
            for (int a = 0; a <= i; a++) {
                utils.sleep(1000);
                utils.clickOnDesiredNumberOfLocator(4, By.xpath("//span[@class='MuiIconButton-label']"));
                utils.sleep(1000);
            }
        }

    }

    public void changeRoutePointsViaEditTrip(String passenger, String trip) throws Exception {
        navigateToTripSummaryDropDown("OrgCreatedForAutoTest2", 1, trip);
        clickViewOREditTripOnSummaryDropDown("EDIT TRIP");
        utils.actionsClick(By.xpath("//*[@class='MuiSvgIcon-root MuiChip-deleteIcon']"));
        utils.actionsClick(By.xpath("//*[@class='MuiSvgIcon-root MuiChip-deleteIcon']"));
        utils.sendTextWithOutClearingOnDesiredNumberOfLocator(1, By.xpath("//label[text()='Boarding passengers']/..//input"), passenger);
        utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passenger + "']"));
        utils.sendTextWithOutClearingOnDesiredNumberOfLocator(2, By.xpath("//label[text()='Disembarking passengers']/..//input"), passenger);
        utils.clickBtn(By.xpath("//div[@role='option'][text()='" + passenger + "']"));
        utils.scrollToView(By.xpath("//button[@type='submit']"));
        commonMethodsPage.clickSubmit();
    }

    public void searchPersonProfileUnderDesiredRole(String role, String text) {
        utils.clickBtn(By.xpath("//p[text()='" + role + "']"));
        utils.sleep(2000);
        utils.sendText(By.xpath("//input[@placeholder='Search']"), text);
    }

    public void updateProfileName(String role, String name) throws InterruptedException {
        searchPersonProfileUnderDesiredRole(role, name);
        randomPassengerName = utils.randomName().toLowerCase();
        utils.clickBtn(By.xpath("//span[contains(text(),'" + name + "')]"));
        utils.actionsClick(By.xpath("//span[contains(text(),'EDIT ')]"));
        utils.sendText(By.id("name"), name + " " + randomPassengerName + "");
        commonMethodsPage.clickSave();
    }

    public void downloadLatestBookingSummaryReport() {
        utils.deleteAllFilesWithSpecifiedExtension(System.getProperty("user.home") + "\\Downloads", ".csv");
        utils.assertElementPresent(By.xpath("//h6[text()='Booking Summary']"));
        utils.sleep(2000);
        utils.clickOnDesiredNumberOfLocator(3, By.xpath("//span[@class='MuiIconButton-label']"));
        List<WebElement> allActiveDays = driver.findElements(By.xpath("//td[@class='rw-cell']"));
        utils.clickOnDesiredNumberOfLocator(allActiveDays.size(), By.xpath("//td[@class='rw-cell']"));
        utils.clickOnDesiredNumberOfLocator(4, By.xpath("//span[@class='MuiIconButton-label']"));
    }

    public void errorMessageOnSupplierPage(String message) {
        utils.assertElementPresent(By.xpath("//p[text()[contains(.,'" + message + "')]]"));

    }

    public void checkEmailLinksOnProfileLabels() {
        utils.clickOnDesiredNumberOfLocator(3, By.xpath("//span[@class='MuiIconButton-label']"));
    }

    public void checkPhoneLinkOnProfileLabels() {
        utils.clickOnDesiredNumberOfLocator(4, By.xpath("//span[@class='MuiIconButton-label']"));

    }

    public void downloadBookingSummaryData() throws IOException {
        utils.deleteAllFilesWithSpecifiedExtension(System.getProperty("user.home") + "\\Downloads", ".csv");
        utils.clickOnDesiredNumberOfLocator(3, By.xpath("//span[@class='MuiIconButton-label']"));
        utils.clickOnDesiredNumberOfLocator(1, By.xpath("//td[@role='gridcell']"));
        utils.clickOnDesiredNumberOfLocator(4, By.xpath("//span[@class='MuiIconButton-label']"));
        utils.readFileAndAssertContent(System.getProperty("user.home") + "\\Downloads\\" + utils.getCSVFileName(System.getProperty("user.home") + "\\Downloads"), "KURA OPS");
        utils.deleteAllFilesWithSpecifiedExtension(System.getProperty("user.home") + "\\Downloads", ".csv");


    }

    public void createAPrivateWayPoint(String firstLocationPostCode, String firstWayPoint) {
        utils.sleep(2000);
        try {
            utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), firstLocationPostCode);
        } catch (Exception e) {
            utils.clickBtn(By.xpath("//div[@type='text']"));
            utils.sendText(By.xpath("//p[text()='Search for a location']/..//input"), firstLocationPostCode);
        }
        utils.sleep(2000);
        utils.clickOnDesiredNumberOfLocator(2, By.xpath("//p[text()[contains(.,'" + firstLocationPostCode + "')]]"));
        Actions actions = new Actions(driver);
        for (int i = 0; i < 4; i++) {
            utils.sleep(1000);
            utils.clickBtn(By.xpath("//button[@title='Zoom In']"));
        }
        utils.sleep(1000);
        int x = driver.findElement(By.xpath("//input[@type='checkbox']")).getLocation().getX();
        int y = driver.findElement(By.xpath("//input[@type='checkbox']")).getLocation().getY();
        WebElement element = driver.findElement(By.xpath("//input[@type='checkbox']"));
        actions.moveToElement(element, x, y).click().perform();
        utils.sleep(1000);
        utils.clickOnDesiredNumberOfLocatorAndSendText(3, By.xpath("//input[@class='MuiInputBase-input MuiInput-input']"), firstWayPoint);
        utils.clickBtn(By.xpath("//span[text()='SAVE']"));
    }

    public void navigateToPersonProfile(String org, String profileName) {
        homePage.pickOrganisationFromOrgsPage(org);
        chooseDesiredOption_OrganisationPage("People");
        searchPersonProfileUnderDesiredRole("Total", profileName);
        utils.sleep(1000);
        utils.clickBtn(By.xpath("//span[text()='" + profileName + "']"));
    }

    public void breakProfileLink(String org, String profileName) {
        navigateToPersonProfile(org, profileName);
        if (utils.checkIfElementIsDisplayed(By.xpath("//span[text()='Unlink']"))) {
            utils.clickBtn(By.xpath("//span[text()='Unlink']"));
        }

    }
}




