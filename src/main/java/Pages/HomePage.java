package Pages;

import Support.ElementUtils;
import org.openqa.selenium.By;


public class HomePage {
    ElementUtils utils = new ElementUtils();
    CommonMethodsPage commonMethodsPage = new CommonMethodsPage();
    public static String randomNumber;

    public void assertHomePage() {
        utils.assertElementPresent(By.xpath("//h6[text()='Home']"));
    }

    public void assertAddOrgPage() {
        utils.assertElementPresent(By.xpath("//h5[text()='Please enter the details of the organisation']"));

    }

    public void addOrganisation(String orgName, String organisationType) throws InterruptedException {
        assertAddOrgPage();
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), orgName);
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("legalName")), orgName);
        utils.clickBtn(By.id("select-Organisation Type"));
        utils.clickBtn(By.xpath("//li[text()='" + organisationType + "']"));
        utils.clickBtn(By.xpath("//div[@id='select-Authorisation Group']"));
        utils.sleep(1000);
        utils.javaScriptExecutorClick(By.xpath("//li[text()='Ops']"));
        commonMethodsPage.clickSubmit();
    }

    public void magicSearch(String text) {
        utils.sleep(2000);
        utils.clickOnDesiredNumberOfLocator(1, By.xpath("//div[@class='MuiListItemIcon-root']"));
        utils.sleep(1000);
        utils.sendText(By.xpath("//input[@id='search']"), text);
    }

    public void searchAndAssertOrganisation(boolean search, String org, String type) {
        if (search) {
            magicSearch(org);
            utils.assertElementPresent(By.xpath("//span[text()='" + org + "']"));

        } else {
            utils.assertElementPresent(By.xpath("//input[@value='" + org + "']"));

        }
    }

    public void editOrganisation(boolean editWithNoSpace, String orgName) {
        commonMethodsPage.clickEdit(editWithNoSpace);
        utils.sendText(By.xpath(commonMethodsPage.xpathGenerator_inputTag("name")), orgName);
        commonMethodsPage.clickSubmit();
    }

    public void searchAndPickOrganisation(String org) {
        magicSearch(org);
        try {
            utils.sleep(1000);
            utils.clickBtn(By.xpath("//span[text()='" + org + "']/../..//div/../following-sibling::div//span[text()='VIEW']"));
        } catch (Exception e) {
            magicSearch(org);
            try {
                utils.clickBtn(By.xpath("//span[text()='" + org + "']/../..//div/../following-sibling::div//span[text()='VIEW']"));
            } catch (Exception e1) {

                pickOrganisationFromOrgsPage(org);
            }
        }
    }

    public void pickOrganisationFromOrgsPage(String org) {
        chooseDesiredIconFromVerticalIndex(3);
        utils.clickBtn(By.xpath("//a[text()='" + org + "']"));
    }

    public void chooseDesiredIconFromVerticalIndex(int option) {
        utils.sleep(2000);
        utils.clickOnDesiredNumberOfLocator(option, By.xpath("//div[@class='MuiListItemIcon-root']"));
    }

    public void logoutOfKura() {
        utils.sleep(2000);
        utils.javaScriptExecutorClick(By.xpath("//*[@id='root']/div/div/header/div/button[2]"));
        utils.sleep(1000);
        utils.actionsClick(By.xpath("//li[text()='Logout']"));
        utils.sleep(1000);
    }

}
