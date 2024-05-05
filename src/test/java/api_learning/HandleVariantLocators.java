package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ElementHandler;

import java.util.Map;

public class HandleVariantLocators {
    private static Map<Platform, By> navLoginBtnLocMap = Map.of(
            Platform.ANDROID, AppiumBy.accessibilityId("Login"),
            Platform.IOS, AppiumBy.accessibilityId("login-btn-ios")
    );
    private static By emailFieldLoc = AppiumBy.accessibilityId("input-email");
    private static By passwordFieldLoc = AppiumBy.accessibilityId("input-password");
    private static By loginBtnLoc = AppiumBy.accessibilityId("button-LOGIN");
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try{
            ElementHandler elementHandler = new ElementHandler(appiumDriver);
            WebElement navLoginBtnEle = elementHandler.findElement(navLoginBtnLocMap);
            navLoginBtnEle.click();

            WebElement emailFieldEle = appiumDriver.findElement(emailFieldLoc);
            emailFieldEle.clear();
            emailFieldEle.sendKeys("cindy@sth.co");

            WebElement passwordFieldEle = appiumDriver.findElement(passwordFieldLoc);
            passwordFieldEle.sendKeys("9876543210");

            WebElement loginBtnEle = appiumDriver.findElement(loginBtnLoc);
            loginBtnEle.click();


            // Debug purpose only
            Thread.sleep(3000);
        } catch (Exception e){
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
