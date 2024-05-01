package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import utils.scrollFeatures;

import java.util.List;

public class FindAnElementBySwipingHorizontally {
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
            int startX = 70 * screenWidth / 100;
            int startY = 70 * screenHeight / 100;
            int endX = 30 * screenWidth / 100;
            int endY = startY;

            boolean isFoundElement = false;
            By foundParentEleLoc = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"card\"])//android.widget.TextView");
            List<WebElement> elementList = appiumDriver.findElements(foundParentEleLoc);
            while (!isFoundElement){
                if (!elementList.isEmpty()){
                    for (WebElement element : elementList) {
                        Point cardCoordinates = element.getLocation();
                        System.out.println(cardCoordinates.getX());
                        if (element.getText().equals("JS.FOUNDATION")){
                            isFoundElement = true;
                            return;
                        }
                    }
                }
                elementList = appiumDriver.findElements(foundParentEleLoc);
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
