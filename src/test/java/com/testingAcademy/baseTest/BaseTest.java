package com.testingAcademy.baseTest;

import com.testingAcademy.driver.DriverManagerTL;
import com.testingAcademy.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    //To call the webdriver and close the webdriver

    WebDriver driver;
    @BeforeMethod
    protected void setUp()
    {
        DriverManagerTL.init();
    }

    @AfterMethod
    protected void down(ITestResult result)
    {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.captureScreenshot(); // Capture screenshot on failure
        }
        DriverManagerTL.teardown();
    }
}
