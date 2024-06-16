package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.scrollFeatures;

import java.time.Duration;

public class SwipeHorizontally {
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            By formBtnLoc = AppiumBy.accessibilityId("Swipe");
            By activeBtnLoc = AppiumBy.accessibilityId("button-Active");

            // Navigate to [Forms] screen
            appiumDriver.findElement(formBtnLoc).click();

            // Make sure that We are on the target screen before swiping up/down/left/right/any direction
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(15L));
            wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Swipe horizontal\")")));

            // Check to see the active button displayed
            boolean isActiveBtnDisplay = false;

            // Get dimension before swipe
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenWidth = windowSize.getWidth();
            int screenHeight = windowSize.getHeight();

            // Constructor coordinators
            int startX = 70 * screenWidth / 100;
            int startY = 70 * screenHeight / 100;
            int endX = 30 * screenWidth / 100;
            int endY = startY;

            // Specify PointerInput as [TOUCH] with name [finger1]
            PointerInput pointerInput = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

            boolean isFoundElement = false;
            while (!isFoundElement){

                scrollFeatures.scrollScreen(appiumDriver, startX, endX, startY, endY);
                Thread.sleep(300);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        // DEBUG PURPOSE ONLY
        try {
            Thread.sleep(1000);
        } catch (Exception ignored) {
        }
        appiumDriver.quit();
    }
    /*
    * Locator | By | form AppiumBy
    * Element | WebElement
     */
}
