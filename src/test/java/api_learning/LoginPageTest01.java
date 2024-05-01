package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import models.pages.LoginPage01;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPageTest01 {
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
        try {
            By navLoginBtnLoc = AppiumBy.accessibilityId("Login");
            WebElement navLoginBtnEle = appiumDriver.findElement(navLoginBtnLoc);
            navLoginBtnEle.click();
            LoginPage01 loginPage = new LoginPage01(appiumDriver);
            loginPage.username().sendKeys("cindydinh@sth.com");
            loginPage.password().sendKeys("9876543210");
            loginPage.loginBtn().click();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}