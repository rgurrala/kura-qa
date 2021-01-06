package Pages;

import Support.ElementUtils;

import org.openqa.selenium.By;


public class AlarmsPage {

    ElementUtils utils = new ElementUtils();

    public void checkMedical_FailedToBoard_CallDriverAlarms_closeAll(String trip) throws InterruptedException {
        utils.sleep(4000);
        utils.sendTxtWithOutClearing(By.xpath("//input[@type='text']"), trip);
        utils.assertElementPresent(By.xpath("//td[text()='Manual message sent by driver: MEDICAL']/..//td[text()='" + trip + "']"));
        utils.assertElementPresent(By.xpath("//td[text()='Failed To board']/..//td[text()='" + trip + "']"));
        utils.assertElementPresent(By.xpath("//td[text()='call driver']/..//td[text()='" + trip + "']"));
        utils.sleep(4000);
        //TODO
//     imp stuff regarding alarms
//        utils.actionsClick(By.xpath("//td[text()='Manual message sent by driver: MEDICAL']/..//td[text()='"+trip+"']"));
//        utils.actionsClick(By.xpath("//span[text()='Mark as completed']"));
//
//        utils.sleep(4000);
//        utils.sendTxtWithOutClearing(By.xpath("//input[@type='text']"),trip);
//        utils.sleep(4000);
//        utils.actionsClick(By.xpath("//td[text()='Failed To board']/..//td[text()='"+trip+"']"));
//        utils.clickBtn(By.xpath("//span[text()='Mark as completed']"));
//
//        utils.sleep(4000);
//        utils.sendTxtWithOutClearing(By.xpath("//input[@type='text']"),trip);
//        utils.sleep(4000);
//        utils.actionsClick(By.xpath("//td[text()='call driver']/..//td[text()='"+trip+"']"));
//        utils.clickBtn(By.xpath("//span[text()='Mark as completed']"));
//
//        utils.sleep(4000);
//        utils.sendTxtWithOutClearing(By.xpath("//input[@type='text']"),trip);
//        utils.assertElementAbsent(By.xpath("//td[text()='call driver']/..//td[text()='"+trip+"']"));

    }

    public void captureEarlyScheduleAlarm(String trip) throws InterruptedException {
        utils.sleep(4000);
        utils.sendTxtWithOutClearing(By.xpath("//input[@type='text']"), trip);
        utils.sleep(4000);
        utils.actionsClick(By.xpath("//td[text()[contains(.,'We are early')]]/..//td[text()='" + trip + "']"));
        utils.clickBtn(By.xpath("//span[text()='Save']"));

        utils.sleep(4000);
        utils.sendTxtWithOutClearing(By.xpath("//input[@type='text']"), trip);
        utils.assertElementPresent(By.xpath("//span[text()='IN_PROGRESS']/../../../following-sibling::tr//td[text()[contains(.,'We are early')]]"));
        utils.sleep(4000);
        utils.actionsClick(By.xpath("//span[text()='IN_PROGRESS']/../../../following-sibling::tr//td[text()[contains(.,'We are early')]]"));
        utils.clickBtn(By.xpath("//span[text()='Mark as completed']"));

        utils.sleep(4000);
        utils.sendTxtWithOutClearing(By.xpath("//input[@type='text']"), trip);
        utils.sleep(4000);
        utils.actionsClick(By.xpath("//td[text()[contains(.,'We are early')]]/..//td[text()='" + trip + "']"));
        utils.clickBtn(By.xpath("//span[text()='Mark as completed']"));

        utils.sleep(4000);
        utils.sendText(By.xpath("//input[@type='text']"), trip);
        utils.assertElementAbsent(By.xpath("//td[text()[contains(.,'We are early')]]/..//td[text()='" + trip + "']"));
    }

    public void checkLate_NoLogon_closeAll(String trip) throws InterruptedException {

        utils.sleep(4000);
        utils.sendTxtWithOutClearing(By.xpath("//input[@type='text']"), trip);
        utils.assertElementPresent(By.xpath("//td[text()[contains(.,'mins late')]]/..//td[text()='" + trip + "']"));
        utils.assertElementPresent(By.xpath("//td[text()='No Logon has occurred']/..//td[text()='" + trip + "']"));

        utils.sleep(4000);
        utils.actionsClick(By.xpath("//td[text()[contains(.,'mins late')]]/..//td[text()='" + trip + "']"));
        utils.clickBtn(By.xpath("//span[text()='Mark as completed']"));

        utils.sleep(4000);
        utils.sendTxtWithOutClearing(By.xpath("//input[@type='text']"), trip);
        utils.sleep(4000);
        utils.actionsClick(By.xpath("//td[text()[contains(.,'mins late')]]/..//td[text()='" + trip + "']"));
        utils.clickBtn(By.xpath("//span[text()='Mark as completed']"));

        utils.sleep(4000);
        utils.sendTxtWithOutClearing(By.xpath("//input[@type='text']"), trip);
        utils.sleep(4000);
        utils.actionsClick(By.xpath("//td[text()='No Logon has occurred']/..//td[text()='" + trip + "']"));
        utils.clickBtn(By.xpath("//span[text()='Mark as completed']"));

        utils.sleep(4000);
        utils.sendTxtWithOutClearing(By.xpath("//input[@type='text']"), trip);
        utils.assertElementAbsent(By.xpath("//td[text()='No Logon has occurred']/..//td[text()='" + trip + "']"));


    }


}