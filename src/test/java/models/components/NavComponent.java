package models.components;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavComponent {
    private final AppiumDriver appiumDriver;
    private final static By homeIconSel = AppiumBy.accessibilityId("");
    private final static By webviewIconSel = AppiumBy.accessibilityId("");
    private final static By loginIconSel = AppiumBy.accessibilityId("Login");
    private final static By formsIconSel = AppiumBy.accessibilityId("");
    private final static By swipeIconSel = AppiumBy.accessibilityId("");
    private final static By dragIconSel = AppiumBy.accessibilityId("");
    public NavComponent(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void clickOnLoginIcon(){
        this.appiumDriver.findElement(loginIconSel).click();

        // TODO: Make sure we are on the Login screen. Implement below...


    }
}
