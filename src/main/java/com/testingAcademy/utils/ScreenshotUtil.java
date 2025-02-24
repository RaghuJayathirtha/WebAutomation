package com.testingAcademy.utils;

import com.testingAcademy.driver.DriverManagerTL;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtil {

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] captureScreenshot() {
        WebDriver driver = DriverManagerTL.getDriver();
        if (driver == null) {
            System.out.println("ERROR: WebDriver is NULL. Screenshot cannot be captured!");
            return null;
        }
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            return Files.readAllBytes(srcFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
