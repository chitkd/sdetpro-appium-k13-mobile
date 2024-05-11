package models.components;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class NavComponent extends Component{
    private final AppiumDriver appiumDriver;
    private final static By homeIconSel = AppiumBy.accessibilityId("");
    private final static By webviewIconSel = AppiumBy.accessibilityId("");
    private final static By loginIconSel = AppiumBy.accessibilityId("Login");
    private final static By formsIconSel = AppiumBy.accessibilityId("");
    private final static By swipeIconSel = AppiumBy.accessibilityId("");
    private final static By dragIconSel = AppiumBy.accessibilityId("");

    private static Map<Platform, By> navloginBtnLocMap = Map.of(
            Platform.ANDROID, AppiumBy.accessibilityId("Login"),
            Platform.IOS, AppiumBy.accessibilityId("IOS-Login")
    );
    public NavComponent(AppiumDriver appiumDriver, WebElement component) {
        super(appiumDriver, component);
        this.appiumDriver = appiumDriver;
    }

    public void clickOnLoginIcon(){
        this.appiumDriver.findElement(loginIconSel).click();

        // TODO: Make sure we are on the Login screen. Implement below...
        // By targetScreen...
    }
}
