package test_flows;

import io.appium.java_client.AppiumDriver;
import models.pages.BasePage;

public class BaseFlow {
    protected final AppiumDriver appiumDriver;
    protected final String platformName;

    public BaseFlow(AppiumDriver appiumDriver, String platformName) {
        this.appiumDriver = appiumDriver;
        this.platformName = platformName;
    }

    public void gotoLoginScreen(){
        new BasePage(appiumDriver, platformName).navComponent().clickOnLoginIcon();
    }
}
