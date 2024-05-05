package examples;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.scrollFeatures;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SwipeHorizontally {
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

            // Constructor coordinators
            int startX = 70 * screenWidth / 100;
            int startY = 70 * screenHeight / 100;
            int endX = 30 * screenWidth / 100;
            int endY = startY;

            final int MAX_SWIPE_TIME = 5;
            boolean isTargetCardFound = false;
            for (int swipeCounter = 0; swipeCounter < MAX_SWIPE_TIME && !isTargetCardFound; swipeCounter++) {
                List<WebElement> cardElements = appiumDriver.findElements(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='slideTextContainer']//android.widget.TextView"));
                if (cardElements.isEmpty()){
                    throw new RuntimeException("There is no cards on the screen");
                }
                List<Integer> xNums = new ArrayList<>();
                List<WebElement> filteredElems = new ArrayList<>();
                for (WebElement cardElement : cardElements) {
                    Point cardCoordinates = cardElement.getLocation();
                    if (cardCoordinates.getX() < screenWidth / 2){
                        xNums.add(cardCoordinates.getX());
                        filteredElems.add(cardElement);
                    }
                }

                if (!filteredElems.isEmpty()){
                    String currentMiddleCardText = filteredElems.get(0).getText().trim();
                    if (currentMiddleCardText.equals("JS.FOUNDATION")){
                        System.out.println("Swipe time: " + (swipeCounter + 1));
                        System.out.println("Middle card's Z coordinate: " + xNums.get(0));
                        System.out.println("Current middle card text is: " + currentMiddleCardText);
                        break;
                    }
                    scrollFeatures.scrollScreen(appiumDriver, startX, endX, startY, endY);
                    // Wait on purpose
                    Thread.sleep(500);
                }

//                if (!filteredElems.isEmpty()){
//                    String currentMiddleCardText = filteredElems.get(0).getText().trim();
//                    int currentMiddleCardX = xNums.get(0);
//                    if (currentMiddleCardX > screenWidth / 2){
//                        throw new RuntimeException("The middle card is shifted to the right");
//                    }
//                    if (currentMiddleCardText.equals("JS.FOUNDATION ")){
//                        System.out.println("Swipe time: " + (swipeCounter + 1));
//                        System.out.println("Middle card's Z coordinate: " + xNums.get(0));
//                        System.out.println("Current middle card text is: " + currentMiddleCardText);
//                        break;
//                    }
//                    scrollFeatures.scrollScreen(appiumDriver, startX, endX, startY, endY);
//                    // Wait on purpose
//                    Thread.sleep(500);
//                }
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
}
