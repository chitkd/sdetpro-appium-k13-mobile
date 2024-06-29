package models.pages;

import driver.Platform;
import io.appium.java_client.AppiumDriver;
import models.components.Component;
import models.components.global.NavComponent;
import org.openqa.selenium.By;

public class BasePage extends Component {
    protected final AppiumDriver appiumDriver;
    public BasePage(AppiumDriver appiumDriver, String platformName) {
        //super(appiumDriver, appiumDriver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"wdiodemoapp\"]/XCUIElementTypeWindow")), platformName);
        super(appiumDriver, appiumDriver.findElement(By.xpath("//hierarchy/android.widget.FrameLayout")), platformName);
        this.appiumDriver = appiumDriver;
    }

    public NavComponent navComponent(){
        return findComponent(NavComponent.class);
    }
}
