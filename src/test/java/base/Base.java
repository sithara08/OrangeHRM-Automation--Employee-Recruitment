package base;

import com.aventstack.chaintest.plugins.ChainTestListener;
import drivers.DriverManager;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utills.PathUtil;
import utills.PropFileReaderUtil;
import utills.ScreenshotUtil;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class Base extends DriverManager {

    /// Utility classes
    PropFileReaderUtil propFileReaderUtil = new PropFileReaderUtil();
    ScreenshotUtil screenshotUtils = new ScreenshotUtil();

    /// Base URL from config
    String baseUrl = propFileReaderUtil.getProperty("config", "baseUrl");

    /// Sets up the browser before each test method.
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser){
        initializeDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(baseUrl);
    }

    /// Logs the test result and takes a screenshot if the test fails.
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.SUCCESS){
            ChainTestListener.log("Test passed.");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            ChainTestListener.log("Test Failed.");
            screenshotUtils.takeScreenshot(driver, result.getName());
            String screenshotPath = PathUtil.getScreenshotRelativePath(result.getName(), screenshotUtils.getTimestamp());
            ChainTestListener.embed(new File(screenshotPath), "image/png");
        } else if (result.getStatus() == ITestResult.SKIP) {
            ChainTestListener.log("Test skipped.");
        }
        quitDriver();
    }

    /// Quits the driver after the test class.
    @AfterClass
    public void afterClass(){
        quitDriver();
    }
}
