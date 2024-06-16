package tests;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {
    private static final List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;
    private String platformName;
    private String platformVersion;
    private String systemPort;
    private String udid;

    protected AppiumDriver getDriver() {
        // get the correct thread
        return driverThread.get().getDriver(Platform.valueOf(platformName), systemPort, udid, platformVersion);
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
    @Parameters({"systemPort", "udid", "platformName", "platformVersion"})
    public void getTestParams(String systemPort, String udid, String platformName, @Optional("platformVersion") String platformVersion){
        this.systemPort = systemPort;
        this.udid = udid;
        this.platformName = platformName;
        this.platformVersion = platformVersion;
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
            String randomScreenshotName = generateScreenshotNameBaseOnTime(results);
            // Capture screenshot and attach to the report
            File screenshotBase64Data = getDriver().getScreenshotAs(OutputType.FILE);
            try {
                String screenshotLocation =
                        System.getProperty("user.dir") + "/screenshots/" + randomScreenshotName;
                FileUtils.copyFile(screenshotBase64Data,
                        new File(screenshotLocation));

                Path screenshotContentPath = Paths.get(screenshotLocation);
                InputStream  inputStream = Files.newInputStream(screenshotContentPath);
                Allure.attachment(results.getName(), inputStream);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private String generateScreenshotNameBaseOnTime(ITestResult results) {
        // 1. Get the test method name
        String methodName = results.getName();

        // 2. Get current time | yyyy-mm-dd-hr-m-s
        Calendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        int hr = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);
        String takenTime = year + "-" + month + "-" + day + "-" + hr + "-" + min + "-" + sec;
        return methodName + takenTime +  ".png";
    }
}
