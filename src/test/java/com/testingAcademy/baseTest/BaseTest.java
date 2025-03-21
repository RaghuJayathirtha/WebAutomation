package com.testingAcademy.baseTest;

import com.testingAcademy.driver.DriverManagerTL;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;

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
        DriverManagerTL.teardown();
    }

    public void takeScreenshot(String name, WebDriver driver)
    {
        Allure.addAttachment(name,new
                ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
    }
}
