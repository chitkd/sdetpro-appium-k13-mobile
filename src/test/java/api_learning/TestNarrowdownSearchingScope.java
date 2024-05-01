package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.scrollFeatures;

import java.time.Duration;

public class TestNarrowdownSearchingScope {
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
        try{
            By formBtnLoc = AppiumBy.accessibilityId("Forms");

            // Navigate to [Forms] screen
            appiumDriver.findElement(formBtnLoc).click();

            // Make sure that We are on the target screen before swiping up/down/left/right/any direction
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(15L));
            wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Form components\")")));

            // Check to see the active button displayed
            boolean isActiveBtnDisplay = false;

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
            Thread.sleep(2000);

            // close the Notification
            startY = endY;
            endY = 0;
            scrollFeatures.scrollScreen(appiumDriver, startX, endX, startY, endY);
        } catch (Exception e){
            e.printStackTrace();
        }

        // DEBUG PURPOSE ONLY
        try {
            Thread.sleep(3000);
        } catch (Exception ignored){
        }

        appiumDriver.quit();
    }
}
