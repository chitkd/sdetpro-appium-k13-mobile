package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class DriverFactory {
    private AppiumDriver appiumDriver;
    public static AppiumDriver getDriver(Platform platform){
        AppiumDriver appiumDriver = null;
        // DesiredCaps
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(AndroidCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(AndroidCapabilityType.AUTOMATION_NAME_OPTION, "uiautomator2");
        desiredCapabilities.setCapability(AndroidCapabilityType.UDID_OPTION, "emulator-5554");
        desiredCapabilities.setCapability(AndroidCapabilityType.APP_PACKAGE_OPTION, "com.wdiodemoapp");
        desiredCapabilities.setCapability(AndroidCapabilityType.APP_ACTIVITY_OPTION, "com.wdiodemoapp.MainActivity");
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

        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2L));

        return appiumDriver;
    }
    public AppiumDriver getDriver(Platform platform, String systemPort, String uuid, String platformVersion){
       if (appiumDriver == null){
           URL appiumServer = null;

           try{
               appiumServer = new URL("http://localhost:4723");
           } catch (Exception e){
               e.printStackTrace();
           }

           if (appiumServer == null){
               throw new RuntimeException("Can't construct the appium server URL");
           }

           DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

           switch (platform){
               case ANDROID:
                   desiredCapabilities = new DesiredCapabilities();
                   desiredCapabilities.setCapability(AndroidCapabilityType.PLATFORM_NAME, "Android");
                   desiredCapabilities.setCapability(AndroidCapabilityType.AUTOMATION_NAME_OPTION, "uiautomator2");
                   desiredCapabilities.setCapability(AndroidCapabilityType.SYSTEM_PORT, systemPort);
                   desiredCapabilities.setCapability(AndroidCapabilityType.UDID_OPTION, uuid);
                   desiredCapabilities.setCapability(AndroidCapabilityType.APP_PACKAGE_OPTION, "com.wdiodemoapp");
                   desiredCapabilities.setCapability(AndroidCapabilityType.APP_ACTIVITY_OPTION, "com.wdiodemoapp.MainActivity");
                   appiumDriver = new AndroidDriver(appiumServer, desiredCapabilities);
                   break;
               case IOS:
                   desiredCapabilities = new DesiredCapabilities();
                   desiredCapabilities.setCapability(IOSCapabilityType.AUTOMATION_NAME_OPTION, "XCUITest");
                   desiredCapabilities.setCapability(IOSCapabilityType.DEVICE_NAME_OPTION, uuid);
                   desiredCapabilities.setCapability(IOSCapabilityType.PLATFORM_VERSION_OPTION, platformVersion);
                   desiredCapabilities.setCapability(IOSCapabilityType.BUNDLE_ID, "org.reactjs.native.example.wdiodemoapp");
                   desiredCapabilities.setCapability(XCUITestOptions.WDA_LOCAL_PORT_OPTION, systemPort);
                   appiumDriver = new IOSDriver(appiumServer, desiredCapabilities);
                   break;
           }

           // Need one more thing here that we will talk in next lesson
           // global wait time applied for the WHOLE driver session - Implicit wait
           appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2L));
       }
        return appiumDriver;
    }

    public void quitAppiumSession(){
        if (appiumDriver != null){
            appiumDriver.quit();
            appiumDriver = null;
        }
    }
}
