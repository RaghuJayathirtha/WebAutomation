package com.testingAcademy.base;

import com.testingAcademy.driver.DriverManagerTL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public BasePage()
    {

    }

    public void implicitWait()
    {
        DriverManagerTL.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public WebElement waitTillPresenceOfElement(final By element)
    {
        WebDriverWait wait = new WebDriverWait(DriverManagerTL.getDriver(), Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public WebElement visibilityOfElement(final By elementLocation) {
        return new WebDriverWait(DriverManagerTL.getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(elementLocation));
    }


    public void click(By by)
    {
        DriverManagerTL.getDriver().findElement(by).click();
    }

    public void enterText(By by,String text)
    {
        DriverManagerTL.getDriver().findElement(by).sendKeys(text);
    }

    public WebElement getElement(By element)
    {
         return DriverManagerTL.getDriver().findElement(element);
    }
}
