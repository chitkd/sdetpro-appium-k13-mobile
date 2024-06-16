package models.components.global;

import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import models.components.Component;
import models.components.ComponentXpathSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ElementHandler;

import java.util.Map;

@ComponentXpathSelector(value = "//android.view.ViewGroup/android.view.ViewGroup[2]/android.view.View")
public class NavComponent extends Component {
    private final AppiumDriver appiumDriver;
    private final static By homeIconSel = AppiumBy.accessibilityId("abc");
    private final static By webviewIconSel = AppiumBy.accessibilityId("c");
    private final static By loginIconSel = AppiumBy.accessibilityId("Login");
    private final static By formsIconSel = AppiumBy.accessibilityId("ac");
    private final static By swipeIconSel = AppiumBy.accessibilityId("sfs");
    private final static By dragIconSel = AppiumBy.accessibilityId("acs");

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
        By targetScreenEleLocator = new ElementHandler(appiumDriver).getElementLocatorFrom(navloginBtnLocMap);

    }
}
