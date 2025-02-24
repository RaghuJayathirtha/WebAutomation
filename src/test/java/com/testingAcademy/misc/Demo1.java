package com.testingAcademy.misc;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class Demo1 {

    WebDriver driver;

    @BeforeTest
    public void startBrowser()
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void demo1()
    {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement dropdown = driver.findElement(
                By.xpath("//select[@id='dropdown-class-example']"));
        Select select = new Select(dropdown);
        //select.selectByIndex(1);
        //select.selectByVisibleText("Option2");
        select.selectByValue("option3");
    }

    @Test
    public void demo2()
    {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        //driver.findElement(By.xpath("//label/input[@value='radio1']")).click();

        List<WebElement> input = driver.findElements(By.xpath("//label/input"));
        for (WebElement webElement: input)
        {

            String attribute = webElement.getAttribute("value");
            System.out.println(attribute);
            if (attribute.contains("radio2"))
            {
                webElement.click();
                break;
            }
        }
    }

    @Test
    public void demo3() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement autoDropdown = driver.findElement(By.xpath("//input[@id='autocomplete']"));
        autoDropdown.sendKeys("USA");

        Thread.sleep(3000);

        List<WebElement> countryNames = driver.findElements(By.xpath("//ul[@id='ui-id-1']/li/div"));

        for (WebElement o: countryNames)
        {
            System.out.println(o.getText());
            if(o.getText().contains("USA"))
            {
                o.click();
                break;
            }
        }
    }

    @Test
    public void demo4()
    {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement openWindow = driver.findElement(
                By.xpath("//button[contains(text(), 'Open Window')]"));
        openWindow.click();

        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for(String o: windowHandles)
        {
            if(!o.equals(parentWindow))
            {
                driver.switchTo().window(o);
                if(driver.getTitle().contains("QAClick Academy"))
                {
                    System.out.println(driver.getTitle());
                    break;
                }
            }
        }
    }

    @Test
    public void demo5() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        Thread.sleep(3000);
        driver.findElement(By.id("opentab")).click();
        //System.out.println(driver.getTitle());

        Set<String> windows = driver.getWindowHandles();

        for(String o:windows)
        {
            driver.switchTo().window(o);
            if(driver.getTitle().contains("QAClick Academy"))
            {
                System.out.println(driver.getTitle());
                break;
            }

        }
    }

    @Test
    public void demo6()
    {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.id("name")).sendKeys("Raghu");

        driver.findElement(By.id("alertbtn")).click();
        Alert alert = driver.switchTo().alert();
        String message = alert.getText();
        Assert.assertTrue(message.contains("Raghu"),
                    "Message does not contain expected text");
        alert.accept();
    }

    @Test
    public void demo7() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement mouseHover = driver.findElement(By.id("mousehover"));

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);",mouseHover);

        Actions actions = new Actions(driver);
        actions.moveToElement(mouseHover).perform();
        Thread.sleep(3000);
        actions.moveToElement(driver.findElement(By.xpath("//a[text()='Top']")))
                .click().perform();
    }

    @Test
    public void demo8() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        String parentWindow = driver.getWindowHandle();
        WebElement iframe = driver.findElement(By.id("courses-iframe"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",iframe);

        driver.switchTo().frame(iframe);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Practice']")).click();

        Thread.sleep(3000);

        driver.switchTo().window(parentWindow);
        js.executeScript("window.scrollBy(0,-1000);");

    }

    @Test
    public void demo9() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement link1 = driver.findElement(By.xpath("//li/a[text()='REST API']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",link1);

        List<WebElement> links = driver.findElements(By.xpath("//li/a"));

        for(WebElement o: links)
        {
            System.out.println(o.getText());
        }
    }
}
