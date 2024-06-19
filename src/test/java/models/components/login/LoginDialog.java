package models.components.login;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import models.components.ComponentIdSelector;
import models.components.ComponentXpathSelector;
import models.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

// This is an exception component where it acts as a whole page
//@ComponentIdSelector(value = "android:id/parentPanel")
@ComponentXpathSelector(value = "//XCUIElementTypeAlert[@name=\"This button is\"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]")
public class LoginDialog extends BasePage {
    private final static By dialogMsgLoc = AppiumBy.id("android:id/message");
    private final static By dialogBtnSel = AppiumBy.id("android:id/button1");

    public LoginDialog(AppiumDriver appiumDriver, String platformName) {
        super(appiumDriver, platformName);
    }

    public String getDialogMsg(){
        return (wait.until(ExpectedConditions.visibilityOfElementLocated(dialogMsgLoc))).getText();
    }

    public WebElement okBtn(){
        return component.findElement(dialogBtnSel);
    }
}
