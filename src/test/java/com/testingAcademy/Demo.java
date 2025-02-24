package com.testingAcademy;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Demo {

    WebDriver driver;

    @BeforeMethod
    public void fireBrowser()
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito--");
        driver = new ChromeDriver(chromeOptions);

    }
    @Test
    public void demo()  {

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
       /* FluentWait wait1 = new FluentWait(driver).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);*/

        /*System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());*/
        driver.get("https://app.vwo.com/#/login");
        WebElement username = driver.findElement(By.id("login-username"));
        username.sendKeys("admin");

        WebElement password= driver.findElement(By.name("password"));
        password.sendKeys("password");

        WebElement submitBtn = driver.findElement(By.xpath("//button[1][@type='submit']"));
        submitBtn.click();

        WebElement loginErrorMsg = driver.findElement(By.id("js-notification-box-msg"));
        wait.until(ExpectedConditions.textToBePresentInElement(loginErrorMsg,"Your email, password, IP address or location did not match"));
        System.out.println(loginErrorMsg.getText());
        Assert.assertEquals(loginErrorMsg.getText(),"Your email, password, IP address or location did not match");
    }

    @Test
    public void demo1() {
        driver.get("https://katalon-demo-cura.herokuapp.com/");
        WebElement makeAppointment = driver.findElement(By.linkText("Make Appointment"));
        makeAppointment.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void demo2()
    {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement dropdown = driver.findElement(By.xpath("//select[@id='dropdown-class-example']"));
        Select s1 = new Select(dropdown);
        //s1.selectByIndex(1);
        s1.selectByVisibleText("Option1");


        /*WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("alertbtn")));*/

        WebElement clickAlert = driver.findElement(By.id("alertbtn"));
        clickAlert.click();

        System.out.println("Alert Element clicked");

        Alert al1 = driver.switchTo().alert();
        al1.accept();

    }

    @Test
    public void demo3()
    {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        int numRows = driver.findElements(By.xpath("//table[@name='courses']/tbody/tr")).size();
        System.out.println("The number of rows in table are " +numRows);

        int numHeaders = driver.findElements(By.xpath("//table[@name='courses']/tbody/tr[1]/th")).size();
        System.out.println("The number of columns in table are " +numHeaders);

        for(int i=1;i<=10;i++)
        {
            List<WebElement> tableList = driver.findElements(By.xpath("//table[@name='courses']/tbody/tr[i]"));
            System.out.println(tableList);
        }
    }

    @Test
    public void demo4() throws InterruptedException {
        driver.get("https://www.makemytrip.com/");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-cy='closeModal']")));

        //To close the login popup
        driver.findElement(By.xpath("//span[@data-cy='closeModal']")).click();

        WebElement fromCity = driver.findElement(By.id("fromCity"));
        fromCity.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='From']")));

        WebElement fromInput = driver.findElement(By.xpath("//input[@placeholder='From']"));
        wait.until(ExpectedConditions.visibilityOf(fromInput));

        fromInput.sendKeys("Bengaluru");

        Actions actions = new Actions(driver);
        /*actions.sendKeys(Keys.ARROW_DOWN).perform();
        Thread.sleep(5000);
        actions.sendKeys(Keys.ENTER).perform();*/

        List<WebElement> fromList = driver.findElements(By.xpath("//ul[@role='listbox']/li"));

        //wait.until(ExpectedConditions.visibilityOfAllElements(fromList));

        for (WebElement o:fromList)
        {
            if(o.getText().contains("Bengaluru"))
            {
                o.click();
                break;
            }
            //System.out.println(o.getText());
        }

    }

    @Test
    public void demo5()
    {
        driver.get("https://the-internet.herokuapp.com/upload");
        driver.manage().window().maximize();

        //File upload code
        WebElement uploadFile = driver.findElement(By.id("file-upload"));
        uploadFile.sendKeys("C:\\Users\\djrag\\Downloads\\30 Days Plan to Learn Java.docx");

        driver.findElement(By.id("file-submit")).click();

    }

    @Test
    public void demo6()
    {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();

         String mainWindow = driver.getWindowHandle();
         driver.findElement(By.id("opentab")).click();

         Set<String> tabs = driver.getWindowHandles();

        for(String tab: tabs)
        {
            driver.switchTo().window(tab);
            System.out.println(driver.getTitle());
            if(driver.getTitle().contains("QAClick Academy"))
            {
                break;
            }
        }
    }

    @Test
    public void demo7()
    {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        try{
            WebElement noId = driver.findElement(By.id("no id"));
            noId.click();
        }
        catch (NoSuchElementException e)
        {
            System.out.println("No element found");
        }

        System.out.println("I'm out of try method");

    }

    @Test
    public void demo8()
    {
        driver.get("https://www.flipkart.com/");
        driver.manage().window().maximize();

        // //*[name()='svg']/*[name()='path' and @stroke="#717478"][1]

        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("AC");

        //SVG example
        WebElement searchBtn = driver.findElement(By.xpath(
                "//*[name()='svg']/*[name()='path' and @stroke=\"#717478\"][1]"));

        Actions actions = new Actions(driver);
        actions.moveToElement(searchBtn).click().perform();
    }

    @AfterMethod
    public void closeBrowser()
    {
        //driver.quit();
    }
}
