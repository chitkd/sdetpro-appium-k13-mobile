package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import models.pages.LoginPage02;
import models.pages.LoginPage03;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPageTest03 {
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
        try {
            By navLoginBtnLoc = AppiumBy.accessibilityId("Login");
            WebElement navLoginBtnEle = appiumDriver.findElement(navLoginBtnLoc);
            navLoginBtnEle.click();
            LoginPage03 loginPage = new LoginPage03(appiumDriver);
            loginPage.inputUsername("cindydinh@sth.com")
                    .inputPassword("9876543210")
                    .clickLoginBtn();
        } catch (Exception e){
            e.printStackTrace();
        }
        appiumDriver.quit();
    }
}
