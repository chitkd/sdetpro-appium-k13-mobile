package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginForminteraction {
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

       try{
           // Login Action
         //  By navLoginBtnLoc = By.xpath("//android.view.View[@content-desc='Login']");
           By navLoginBtnLoc = AppiumBy.accessibilityId("Login");
           WebElement navLoginBtnEle = appiumDriver.findElement(navLoginBtnLoc);
           navLoginBtnEle.click();

           // Input username
           By emailFieldLoc = AppiumBy.accessibilityId("input-email");
           WebElement emailFieldEle = appiumDriver.findElement(emailFieldLoc);
           emailFieldEle.sendKeys("cindy@sth.com");

           // Input password
           By passwordLoc = AppiumBy.accessibilityId("input-password");
           WebElement passwordEle = appiumDriver.findElement(passwordLoc);
           passwordEle.sendKeys("0987654321");

           // Try to generate another exception

           // Click on Login btn
           By loginBtnLoc = AppiumBy.accessibilityId("button-LOGIN");
           WebElement loginBtnEle = appiumDriver.findElement(loginBtnLoc);
           loginBtnEle.click();

           // Wait for the dialog displayed
           By dialogMsgLoc = AppiumBy.id("android:id/message");
           By dialogBtnLoc = AppiumBy.id("android:id/button1");

           // Using explicit wait
           WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(15));
           WebElement dialogMsgEle = wait.until(ExpectedConditions.visibilityOfElementLocated(dialogMsgLoc));
           System.out.printf("Dialog msg: %s\n", dialogMsgEle.getText());
           appiumDriver.findElement(dialogBtnLoc).click();
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

    /*
    * Locator | By | from AppiumBy
     */
}
