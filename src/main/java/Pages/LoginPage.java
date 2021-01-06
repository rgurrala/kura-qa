package Pages;


import Support.ElementUtils;
import org.openqa.selenium.By;


public class LoginPage {
    ElementUtils utils = new ElementUtils();

    public void assertLoginPage() {
        utils.assertElementPresent(By.xpath("//span[text()='Sign in to your account']"));
    }

    public void login(String userName, String password) {
        utils.sendText(By.xpath("//input[@placeholder='Enter your email']"), userName);
        utils.sendText(By.xpath("//input[@placeholder='Enter your password']"), password);
        utils.clickBtn(By.xpath("//button[@type='submit']"));


    }


}
