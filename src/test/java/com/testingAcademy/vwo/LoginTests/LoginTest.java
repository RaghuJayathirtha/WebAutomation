package com.testingAcademy.vwo.LoginTests;

import com.testingAcademy.baseTest.BaseTest;
import com.testingAcademy.driver.DriverManagerTL;
import com.testingAcademy.pages.POM.Dashboard_POM;
import com.testingAcademy.pages.POM.LoginPage_POM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.IMethodInstance;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;

public class LoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    @Test
    public void testLoginPositive(Method method) throws FileNotFoundException {
        LoginPage_POM pagePom = new LoginPage_POM();
        pagePom.openURL("https://app.vwo.com/#/login");
        logger.info("Launched url");
        Dashboard_POM dashboardPom = pagePom.loginToVwo().afterLogin();
        logger.info("User logged into the application");
        takeScreenshot(method.getName(),DriverManagerTL.getDriver());

    }

    @Test
    public void testLoginNegative(Method method)
    {
        LoginPage_POM pagePom = new LoginPage_POM();
        pagePom.openURL("https://app.vwo.com/#/login");
        String errorMsg = pagePom.LoginToVwoNegative();
        takeScreenshot(method.getName(), DriverManagerTL.getDriver());
        Assertions.assertThat(errorMsg)
                .contains("Your email, password, IP address or location did not match");

    }
}
