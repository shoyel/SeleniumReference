package com.shiftedtech.qa.scripts;

import com.shiftedtech.qa.ScriptBase;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
        delayFor(5000);
        String text =  driver.switchTo().alert().getText();
        System.out.println("Test: " + text);
        driver.switchTo().alert().sendKeys("Masihur");
        delayFor(5000);
    }

}
