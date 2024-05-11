package examples;

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

public class SwipeVertically {
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            By formBtnLoc = AppiumBy.accessibilityId("Swipe");

            // Navigate to [Swipe] screen
            appiumDriver.findElement(formBtnLoc).click();

            // Make sure that We are on the target screen
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(15L));
            wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Swipe horizontal\")")));

            // Get dimension before swipe
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenWidth = windowSize.getWidth();
            int screenHeight = windowSize.getHeight();

            System.out.printf("%d x %d\n", screenWidth, screenHeight);

            // Constructor coordinators
            int startX = 50 * screenWidth / 100;
            int endX = startX;
            int startY = 45 * screenHeight / 100;
            int endY = 10 * screenHeight / 100;

            By logoImageLoc = AppiumBy.accessibilityId("WebdriverIO logo");
            if (isTheTargetFound(appiumDriver, logoImageLoc)){
                System.out.println(isTheTargetFound(appiumDriver, logoImageLoc));
                System.out.println("Hooray!!! Found the WebdriverIO logo!");
            }
            else {
                boolean isFoundTheTargetElement = false;
                while (!isFoundTheTargetElement){
                    scrollFeatures.scrollScreen(appiumDriver, startX, endX, startY, endY);
                    System.out.println(isTheTargetFound(appiumDriver, logoImageLoc));
                    if (isTheTargetFound(appiumDriver, logoImageLoc)){
                        isFoundTheTargetElement = true;
                        System.out.println("Hooray!!! Found the WebdriverIO logo!");
                        break;
                    }
                }
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

    private static boolean isTheTargetFound(AppiumDriver appiumDriver, By locator){
        return !appiumDriver.findElements(locator).isEmpty();
    }
}
