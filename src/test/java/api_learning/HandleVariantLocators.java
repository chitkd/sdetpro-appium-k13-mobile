package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.internal.CapabilityHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

public class HandleVariantLocators {
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try{
            // Get platform info under test session
            Capabilities caps = appiumDriver.getCapabilities();
            String currentPlatform = CapabilityHelpers.getCapability(caps, "platformName", String.class);
            System.out.println("Platform name is: " + currentPlatform);

            // Construct the locator map
            Map<Platform, By> loginBtnLocMap = Map.of(
                    Platform.ANDROID, AppiumBy.id("login-btn-android"),
                    Platform.IOS, AppiumBy.id("login-btn-ios")
            );


            // Get locator based on current platform
            System.out.println(loginBtnLocMap.get(Platform.valueOf(currentPlatform)));

            Thread.sleep(300);
        } catch (Exception e){
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
