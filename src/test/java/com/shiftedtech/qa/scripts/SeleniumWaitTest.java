package com.shiftedtech.qa.scripts;

import com.google.common.base.Function;
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
public class SeleniumWaitTest {

    private static final int DEFAULT_WAIT_TIME = 10000;

    private WebDriver driver;

    @Before
    public void setUp(){
        ChromeDriverManager.getInstance().setup();

        driver = new ChromeDriver();


        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

       // driver.navigate().to("http://jquery.eisbehr.de/lazy/#examples");

        // Start application
        driver.get("http://seleniumpractise.blogspot.in/2016/08/how-to-use-explicit-wait-in-selenium.html");




    }

    @Test
    public void test1(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Click on timer button to start
        driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();

        driver.findElement(By.xpath("//p[text()='WebDriver']")).click();


    }

    @Test
    public void test2(){
        driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();

        WebElement element = waitForElement(By.xpath("//p[text()='WebDriver']"),10000);

        element.click();

    }

    @Test
    public void test3(){
        driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();

        WebElement element = waitForElementDisplayed(By.xpath("//p[text()='WebDriver']"),10000);

        element.click();

    }

    @Test
    public void test4(){

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


    @After
    public void tearDown(){

    }


    public WebElement waitForElement(final By locator, int timeToWaitInSec) {
        driver.manage().timeouts().implicitlyWait(100,TimeUnit.MILLISECONDS);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeToWaitInSec, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });

        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIME,TimeUnit.MILLISECONDS);
        return foo;
    }

    public WebElement waitForElementDisplayed(final By locator, int timeToWaitInSec) {

        driver.manage().timeouts().implicitlyWait(100,TimeUnit.MILLISECONDS);

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeToWaitInSec, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                if (element != null && element.isDisplayed()) {
                    return element;
                }
                return null;
            }
        });

        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIME,TimeUnit.MILLISECONDS);
        return foo;
    }

    public FluentWait<WebDriver> fluentWait() {
        return fluentWait(DEFAULT_WAIT_TIME,TimeUnit.MILLISECONDS);
    }

    public FluentWait<WebDriver> fluentWait(int duration, TimeUnit timeUnit) {
        return new FluentWait<WebDriver>(driver)       //<3>
                .withTimeout(duration, timeUnit)
                .pollingEvery(50, TimeUnit.MILLISECONDS)
                .ignoreAll(new ArrayList<Class<? extends Throwable>>() {
                    {
                        add(StaleElementReferenceException.class);
                        add(NoSuchElementException.class);
                    }
                }).withMessage("Selenium TimeoutException");
    }

    public void waitForVisibilityOfElement(WebElement element){
        FluentWait<WebDriver> wait = fluentWait();
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForVisibilityOfElement(By locator){
        WebElement element = driver.findElement(locator);
        waitForVisibilityOfElement(element);
    }

    public void waitForPageTitle(String title){
        FluentWait<WebDriver> wait = fluentWait();
        wait.until(ExpectedConditions.titleIs(title));
    }

    public void waitForPageTitleContains(String title){
        FluentWait<WebDriver> wait = fluentWait();
        wait.until(ExpectedConditions.titleContains(title));
    }

    public void waitForInvisibilityOfElement(By locator){
        FluentWait<WebDriver> wait = fluentWait();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementAttributeContains(WebElement element,String attributeName, String attributeValue){
        FluentWait<WebDriver> wait = fluentWait();
        wait.until(ExpectedConditions.attributeContains(element,attributeName,attributeValue));
    }
    public void waitForElementAttributeContains(By locator,String attributeName, String attributeValue){
        WebElement element = driver.findElement(locator);
        waitForElementAttributeContains(element,attributeName,attributeValue);
    }

    public void waitForElementTextToBe(By locator, String text){
        FluentWait<WebDriver> wait = fluentWait();
        wait.until(ExpectedConditions.textToBe(locator,text));
    }
}
