package tests;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.util.*;

public class BaseTest {
    private static final List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;
    private String platformName = "ANDROID";
    private String systemPort;
    private String udid;

    protected AppiumDriver getDriver() {
        // get the correct thread
        return driverThread.get().getDriver(Platform.valueOf(platformName), systemPort, udid);
    }

    @BeforeTest
    @Parameters({"systemPort", "udid"})
    public void initAppiumDriverSession(String systemPort, String udid){
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory driverThread = new DriverFactory();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @BeforeClass
    @Parameters({"systemPort", "udid"})
    public void getTestParams(String systemPort, String udid){
        this.systemPort = systemPort;
        this.udid = udid;
    }

    @AfterTest(alwaysRun = true)
    public void quitAppiumSession(){
        driverThread.get().quitAppiumSession();
    }

    @AfterMethod(description = "Capture screenshot when test is failed")
    public void captureScreenshot(ITestResult results){
        Map <Integer, String> statusMap = new HashMap<>();
        boolean testIsFailed = results.getStatus() == ITestResult.FAILURE;
        if (testIsFailed){
            // Capture screenshot here
            File screenshotBase64Data = getDriver().getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshotBase64Data,
                        new File(System.getProperty("user.dir") + "/screenshots/" + "failure.png"));
            } catch (Exception e){
                e.printStackTrace();
            }


        }
    }
}
