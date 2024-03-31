package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginForminteraction {
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

       try{
           // Login Action
           By navLoginBtnLoc = By.xpath("//android.view.View[@content-desc='Login']");
           WebElement navLoginBtnEle = appiumDriver.findElement(navLoginBtnLoc);
           navLoginBtnEle.click();

           // Input username
           By emailFieldLoc = By.xpath("//android.widget.EditText[@content-desc='input-email']");
           WebElement emailFieldEle = appiumDriver.findElement(emailFieldLoc);
           emailFieldEle.sendKeys("chi@sth.com");

           // Input password
           By passwordLoc = By.xpath("//android.widget.EditText[@content-desc='input-password']");
           WebElement passwordEle = appiumDriver.findElement(passwordLoc);
           passwordEle.sendKeys("9876543210");

           // Click on Login btn
           By loginBtnLoc = By.xpath("//android.view.ViewGroup[@content-desc='button-LOGIN']/android.view.ViewGroup");
           WebElement loginBtnEle = appiumDriver.findElement(loginBtnLoc);
           loginBtnEle.click();

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
