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
    private static Map<Platform, By> navLoginBtnLocMap = Map.of(
            Platform.ANDROID, AppiumBy.id("login-btn-android"),
            Platform.IOS, AppiumBy.id("login-btn-ios")
    );
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try{
            // Get platform info under test session
            String currentPlatform = getCurrentPlatform(appiumDriver);

            // Get locator based on current platform
            By loginLocator = getLocator(navLoginBtnLocMap, currentPlatform);

            Thread.sleep(300);
        } catch (Exception e){
            e.printStackTrace();
        }

        appiumDriver.quit();
    }

    private static String getCurrentPlatform(AppiumDriver appiumDriver){
        Capabilities caps = appiumDriver.getCapabilities();
        return CapabilityHelpers.getCapability(caps, "platformName", String.class);
    }

    private static By getLocator(Map<Platform, By> locatorMap, String platformName){
        return locatorMap.get(Platform.valueOf(platformName));
    }
}
