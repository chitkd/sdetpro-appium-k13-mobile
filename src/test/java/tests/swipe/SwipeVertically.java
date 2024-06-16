package tests.swipe;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.scrollFeatures;

import java.time.Duration;

public class SwipeVertically extends BaseTest{
    @Test
    public void swipeVertically() {
        By formBtnLoc = AppiumBy.accessibilityId("Forms");
        By activeBtnLoc = AppiumBy.accessibilityId("button-Active");
        By dialogOKBtnLoc = AppiumBy.id("android:id/button1");

        AppiumDriver appiumDriver = getDriver();

        // Navigate to [Forms] screen
        appiumDriver.findElement(formBtnLoc).click();

        // Make sure that We are on the target screen before swiping up/down/left/right/any direction
        WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(15L));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Form components\")")));


        // Swipe up before interacting
        Dimension windowSize = appiumDriver.manage().window().getSize();
        int screenWidth = windowSize.getWidth();
        int screenHeight = windowSize.getHeight();

        // Constructor coordinators
        int startX = screenWidth / 2;
        int startY = screenHeight / 2;
        int endX = startX;
        int endY = 20 * screenHeight / 100;

        scrollFeatures.scrollScreen(appiumDriver, startX, endX, startY, endY);

        // Interact with one element on the screen
        appiumDriver.findElement(activeBtnLoc).click();
        appiumDriver.findElement(dialogOKBtnLoc).click();
    }
}
