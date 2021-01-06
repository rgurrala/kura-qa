package Pages;

import Support.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.html5.Location;

import static Pages.KuraAPPpage.mobileDriver;
import static Support.BaseClass.driver;

public class CommonMethodsPage {

    ElementUtils utils = new ElementUtils();

    public void clickEdit(boolean editWithNoSpace) {
        if (editWithNoSpace) {
            utils.clickBtn(By.xpath("//span[text()='EDIT']"));
        } else {
            utils.clickBtn(By.xpath("//span[text()='EDIT ']"));
        }
    }

    public void clickSubmit() {
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//button[@type='submit']"));
    }

    public void clickSave() throws InterruptedException {
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='SAVE']"));
    }


    public void clickAdd() throws InterruptedException {
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//span[text()='ADD']"));
    }


    public String xpathGenerator_inputTag(String id) {
        return "//input[@id='" + id + "']";
    }

    public void setGeoLocation(double latitude, double longitude, int altitude) {
        //utils.updateSettingsApp();
        Location location = new Location(latitude, longitude, altitude);
        mobileDriver.setLocation(location);

    }

    public void switchToAndCloseBluePopUp() {
        try {
            utils.sleep(2000);
            driver.switchTo().frame(0);
            utils.sleep(1000);
            utils.javaScriptExecutorClick(By.xpath("//div[@class='frame-wrapper ']/..//div[3]/button"));
            driver.switchTo().parentFrame();
        } catch (Exception e) {
            System.out.println("no blue pup");
        }

    }
}
