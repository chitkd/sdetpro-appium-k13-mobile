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
import utils.scrollFeatures;

import java.time.Duration;

public class HandleMultipleApps {
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Login Action
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

            // Click on Login btn
            By loginBtnLoc = AppiumBy.accessibilityId("button-LOGIN");
            WebElement loginBtnEle = appiumDriver.findElement(loginBtnLoc);
            loginBtnEle.click();

            // SWITCH to another app | Handle multiple apps on the same devices
            Capabilities caps = appiumDriver.getCapabilities();
            String currentPlatform = CapabilityHelpers.getCapability(caps, "platformName", String.class);
            if (Platform.valueOf(currentPlatform).equals(Platform.ANDROID)) {
                AndroidDriver androidDriver = ((AndroidDriver) appiumDriver);

                // put the current app under background till we call it back
                androidDriver.runAppInBackground(Duration.ofSeconds(-2));

                // Switch to another app and to do something
                //androidDriver.activateApp("com.android.settings");
                openNotification(appiumDriver);

                By internetIconLoc = AppiumBy.id("com.android.systemui:id/tile_label");
                WebElement internetIconEle = appiumDriver.findElement(internetIconLoc);
                internetIconEle.click();

                By mobileDataStatusLoc = AppiumBy.accessibilityId("Mobile data");
                WebElement mobileDataStatusEle = appiumDriver.findElement(mobileDataStatusLoc);
                mobileDataStatusEle.click();

                By doneBtnLoc = AppiumBy.id("com.android.systemui:id/done_button");
                WebElement doneBtnEle = appiumDriver.findElement(doneBtnLoc);
                doneBtnEle.click();
                Thread.sleep(1000);
                closeNotification(appiumDriver);

                Thread.sleep(2000);
                // Switch back to the app under test to continue the flow
                androidDriver.activateApp("com.wdiodemoapp");

            }


            // Wait for the dialog displayed
            By dialogMsgLoc = AppiumBy.id("android:id/message");
            By dialogBtnLoc = AppiumBy.id("android:id/button1");

            // Using explicit wait
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(15));
            WebElement dialogMsgEle = wait.until(ExpectedConditions.visibilityOfElementLocated(dialogMsgLoc));
            System.out.printf("Dialog msg: %s\n", dialogMsgEle.getText());
            Thread.sleep(2000);
            appiumDriver.findElement(dialogBtnLoc).click();

            // DEBUG PURPOSE ONLY
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openNotification(AppiumDriver appiumDriver) {
        // Swipe up before interacting
        Dimension windowSize = appiumDriver.manage().window().getSize();
        int screenWidth = windowSize.getWidth();
        int screenHeight = windowSize.getHeight();

        // Constructor coordinators
        int startX = 50 * screenWidth / 100;
        int startY = 0;
        int endX = startX;
        int endY = 50 * screenHeight / 100;
        scrollFeatures.scrollScreen(appiumDriver, startX, endX, startY, endY);

        // close the Notification
        startY = endY;
        endY = 0;
    }

    public static void closeNotification(AppiumDriver appiumDriver) {
        // Swipe up before interacting
        Dimension windowSize = appiumDriver.manage().window().getSize();
        int screenWidth = windowSize.getWidth();
        int screenHeight = windowSize.getHeight();

        // Constructor coordinators
        int startX = 50 * screenWidth / 100;
        int startY = 50 * screenHeight / 100;
        int endY = 0;
        scrollFeatures.scrollScreen(appiumDriver, startX, startX, startY, endY);
    }
}
