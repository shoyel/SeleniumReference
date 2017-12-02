package com.shiftedtech.qa.scripts;

import com.google.common.base.Function;
import com.shiftedtech.qa.ScriptBase;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by iivaan on 12/1/17.
 */
public class SeleniumWaitTest extends ScriptBase{


    @Before
    @Override
    public void setUp(){
        super.setUp();
        // Start application
        driver.get("http://seleniumpractise.blogspot.in/2016/08/how-to-use-explicit-wait-in-selenium.html");
    }

    @Test
    public void test1(){
        //driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Click on timer button to start
        driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();

        driver.findElement(By.xpath("//p[text()='WebDriver']")).click();

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test
    public void test2(){
        driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();

        WebElement element = waitForElement(By.xpath("//p[text()='WebDriver']"),60);

        element.click();

    }

    @Test
    public void test3(){
        driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();

        WebElement element = waitForElementDisplayed(By.xpath("//p[text()='WebDriver']"),60);

        element.click();
    }

    @Test
    public void test4(){

        //https://seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                                        .withTimeout(10, TimeUnit.SECONDS)
                                        .pollingEvery(50, TimeUnit.MILLISECONDS)
                                        .ignoreAll(new ArrayList<Class<? extends Throwable>>() {
                                            {
                                                add(StaleElementReferenceException.class);
                                                add(NoSuchElementException.class);
                                            }
                                        }).withMessage("Selenium TimeoutException");

        driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();

        // Here we will wait until element is not visible, if element is visible then it will return web element
        // or else it will throw exception
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='WebDriver']")));

        element.click();

    }

    @Test
    public void test5(){

        driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();

        // Here we will wait until element is not visible, if element is visible then it will return web element
        // or else it will throw exception
        //WebElement element = fluentWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='WebDriver']")));
        WebElement element = fluentWait(30,TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='WebDriver']")));

        element.click();

    }

    @Test
    public void test6(){

        driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();

        Boolean found = false;
        try {
            found = fluentWait(10, TimeUnit.SECONDS).until(ExpectedConditions.textToBePresentInElementLocated(By.id("demo"), "WebDriverXX"));
        }
        catch (TimeoutException ex){
            ex.printStackTrace();
        }

        if(found) {
            WebElement element = driver.findElement(By.id("demo"));
            element.click();
        }
        else{
            System.out.println("Element with the text not found");
        }

    }

    @Test
    public void test7(){

        driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();

        WebElement element = textToBePresentInElementLocated(By.id("demo"),"WebDriver",10,TimeUnit.SECONDS);
        element.click();

    }

    @Test
    public void test8(){

        driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();

        WebElement element = textToBePresentInElementLocated(By.id("demo"),"WebDriverXXX",10,TimeUnit.SECONDS);
        element.click();

    }

    @Test
    public void test9(){

        driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();
        delayFor(10000);
        WebElement element = driver.findElement(By.xpath("//p[text()='WebDriver']"));
        element.click();

    }


}
