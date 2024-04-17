package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.internal.CapabilityHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import util.*;

public class HandleMultipleApps {
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try{
            // Login Action
            //  By navLoginBtnLoc = By.xpath("//android.view.View[@content-desc='Login']");
            By navLoginBtnLoc = AppiumBy.accessibilityId("Login");
            WebElement navLoginBtnEle = appiumDriver.findElement(navLoginBtnLoc);
            navLoginBtnEle.click();

            // Input username
            By emailFieldLoc = AppiumBy.accessibilityId("input-email");
            WebElement emailFieldEle = appiumDriver.findElement(emailFieldLoc);
            emailFieldEle.sendKeys("cindy@sth.com");

            // Input password
            By passwordLoc = AppiumBy.accessibilityId("input-password");
            WebElement passwordEle = appiumDriver.findElement(passwordLoc);
            passwordEle.sendKeys("0987654321");

            // Try to generate another exception

            // Click on Login btn
            By loginBtnLoc = AppiumBy.accessibilityId("button-LOGIN");
            // Get dimension before swipe
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenWidth = windowSize.getWidth();
            int screenHeight = windowSize.getHeight();

            // Constructor coordinators
            int startX = 70 * screenWidth / 100;
            int startY = 70 * screenHeight / 100;
            int endX = 30 * screenWidth / 100;
            int endY = startY;
            scrollFeatures.scrollScreen(appiumDriver, startX, endX, startY, endY);
            WebElement loginBtnEle = appiumDriver.findElement(loginBtnLoc);
            loginBtnEle.click();
            Thread.sleep(1000);

            // SWITCH to another app | Handle multiple apps on the same devices
            Capabilities caps = appiumDriver.getCapabilities();
            String currentPlatform = CapabilityHelpers.getCapability(caps, "platformName", String.class);

            if (Platform.valueOf(currentPlatform).equals(Platform.ANDROID)) {
                AndroidDriver androidDriver = ((AndroidDriver) appiumDriver);

                // put the current app under background till we call it back
                androidDriver.runAppInBackground(Duration.ofMinutes(-1));
            }

            // Switch to another app and to do something

            // Switch back to the app under test to continue the flow

            // Wait for the dialog displayed
            By dialogMsgLoc = AppiumBy.id("android:id/message");
            By dialogBtnLoc = AppiumBy.id("android:id/button1");

            // Using explicit wait
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(15));
            WebElement dialogMsgEle = wait.until(ExpectedConditions.visibilityOfElementLocated(dialogMsgLoc));
            System.out.printf("Dialog msg: %s\n", dialogMsgEle.getText());
            appiumDriver.findElement(dialogBtnLoc).click();
        } catch (Exception e){
            e.printStackTrace();
        }

        // DEBUG PURPOSE ONLY
        try {
            Thread.sleep(1000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
