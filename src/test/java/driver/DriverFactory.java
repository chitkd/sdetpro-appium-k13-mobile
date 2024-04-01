package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class DriverFactory {
    public static AppiumDriver getDriver(Platform platform){
        AppiumDriver appiumDriver = null;
        // DesiredCaps
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        desiredCapabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
        URL appiumServer = null;

        try{
            appiumServer = new URL("http://localhost:4723");
        } catch (Exception e){
            e.printStackTrace();
        }

        if (appiumServer == null){
            throw new RuntimeException("Can't construct the appium server URL");
        }

        switch (platform){
            case ANDROID:
                appiumDriver = new AndroidDriver(appiumServer, desiredCapabilities);
                break;
            case IOS:
                appiumDriver = new IOSDriver(appiumServer, desiredCapabilities);
                break;
        }


        // Need one more thing here that we will talk in next lesson
        // global wait time applied for the WHOLE driver session - Implicit wait

        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));

        return appiumDriver;
    }
}
