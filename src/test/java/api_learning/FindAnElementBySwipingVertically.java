package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.scrollFeatures;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FindAnElementBySwipingVertically {
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            By formBtnLoc = AppiumBy.accessibilityId("Swipe");
            By activeBtnLoc = AppiumBy.accessibilityId("button-Active");

            // Navigate to [Forms] screen
            appiumDriver.findElement(formBtnLoc).click();

            // Swipe up before interacting
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenWidth = windowSize.getWidth();
            int screenHeight = windowSize.getHeight();

            // Constructor coordinators
            int startX = 10 * screenWidth / 100;
            int startY = 95 * screenHeight / 100;
            int endX = startX;
            int endY = 5 * screenHeight / 100;
            boolean isFoundElement = false;
            By foundEleLoc = AppiumBy.xpath("//android.widget.ScrollView[@content-desc=\"Swipe-screen\"]/android.view.ViewGroup/android.widget.TextView");
            List<WebElement> elementList = appiumDriver.findElements(foundEleLoc);
            while (!isFoundElement){
                if (!elementList.isEmpty()){
                    if (elementList.get(0).getText().equals("You found me!!!")){
                        isFoundElement = true;
                        break;
                    }
                }
                elementList = appiumDriver.findElements(foundEleLoc);
                scrollFeatures.scrollScreen(appiumDriver, startX, endX, startY, endY);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        // DEBUG PURPOSE ONLY
        try {
            Thread.sleep(3000);
        } catch (Exception ignored) {
        }
        appiumDriver.quit();
    }
}
