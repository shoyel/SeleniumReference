package com.shiftedtech.qa.scripts;

import com.shiftedtech.qa.ScriptBase;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

/**
 * Created by iivaan on 12/2/17.
 */
public class AlertHandleTest extends ScriptBase {

    @Before
    @Override
    public void setUp(){
        super.setUp();
        driver.get("http://shifttest.shiftedtech.com/components/alert");
    }

    @Test
    public void acceptAlertPopUpTest(){
        WebElement element  = driver.findElement(By.id("alert-01"));
        element.click();
        delayFor(5000);
        //driver.switchTo().alert().dismiss();
        String text =  driver.switchTo().alert().getText();
        System.out.println("Test: " + text);
        driver.switchTo().alert().accept();
        delayFor(5000);
    }

    @Test
    public void acceptAlertPopUpTest_Wait(){
        WebElement element  = driver.findElement(By.id("alert-01"));
        element.click();

        Alert alert = fluentWait(10,TimeUnit.SECONDS).until(ExpectedConditions.alertIsPresent());
        String text =  alert.getText();
        System.out.println("Test: " + text);

        alert.accept();

        delayFor(5000);
    }


    @Test
    public void dismissAlertPopUpTest(){
        WebElement element  = driver.findElement(By.id("alert-01"));
        element.click();
        delayFor(5000);
        String text =  driver.switchTo().alert().getText();
        System.out.println("Test: " + text);
        driver.switchTo().alert().dismiss();
        delayFor(5000);
    }

    @Test
    public void checkBoxAlertPopUpTest() throws Exception{
        WebElement element  = driver.findElement(By.id("alert-03"));
        element.click();
        delayFor(3000);
        String text =  driver.switchTo().alert().getText();
        System.out.println("Test: " + text);
        driver.switchTo().alert().sendKeys("Iftekhar");
        delayFor(5000);
    }

}
