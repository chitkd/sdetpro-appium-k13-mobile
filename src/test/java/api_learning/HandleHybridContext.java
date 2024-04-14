package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.internal.CapabilityHelpers;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HandleHybridContext {
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try{
            // Click on the Webview button
            By formBtnLoc = AppiumBy.accessibilityId("Webview");

            // Navigate to [Webview] screen
            appiumDriver.findElement(formBtnLoc).click();

            // Get platform info under test session
            Capabilities caps = appiumDriver.getCapabilities();
            String currentPlatform = CapabilityHelpers.getCapability(caps, "platformName", String.class);

            if (Platform.valueOf(currentPlatform).equals(Platform.ANDROID)){
                System.out.println(((AndroidDriver) appiumDriver).getContextHandles());
            } else {
                System.out.println(((IOSDriver) appiumDriver).getContextHandles());
            }

            // Custom Explicit wait
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(10L));

            // DEBUG PURPOSE ONLY
            Thread.sleep(1000);
        } catch (Exception e){
            e.printStackTrace();
        }
        appiumDriver.quit();
    }

}
