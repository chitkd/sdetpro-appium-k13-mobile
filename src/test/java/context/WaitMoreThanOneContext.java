package context;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WaitMoreThanOneContext implements ExpectedCondition<Boolean> {
    private final AppiumDriver appiumDriver;

    public WaitMoreThanOneContext(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    @Override
    public Boolean apply(WebDriver input) {
        return null;
    }
}
