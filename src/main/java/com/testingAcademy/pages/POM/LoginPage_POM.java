package com.testingAcademy.pages.POM;

import com.testingAcademy.base.BasePage;
import com.testingAcademy.driver.DriverManagerTL;
import com.testingAcademy.utils.PropertyReader;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;

public class LoginPage_POM extends BasePage {

    By username = By.id("login-username");
    By password = By.id("login-password");
    By signinBtn = By.id("js-login-btn");
    By loginErrorMsg = By.xpath("//div[@id='js-notification-box-msg']");

    public LoginPage_POM()
    {
        super();
    }

    public LoginPage_POM loginToVwo() throws FileNotFoundException {
        enterText(username, PropertyReader.readKey("username"));
        enterText(password, PropertyReader.readKey("password"));
        click(signinBtn);
        return this;
    }

    public String LoginToVwoNegative()
    {
        enterText(username,"admin");
        enterText(password,"password");
        click(signinBtn);
        visibilityOfElement(loginErrorMsg);
        return getElement(loginErrorMsg).getText();
    }

    public void openURL(String url)
    {
        DriverManagerTL.getDriver().get(url);
    }

    public Dashboard_POM afterLogin()
    {
        return new Dashboard_POM();
    }
}