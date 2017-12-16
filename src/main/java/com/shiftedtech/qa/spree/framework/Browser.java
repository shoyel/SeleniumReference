package com.shiftedtech.qa.spree.framework;

import com.google.common.base.Function;
import com.shiftedtech.qa.spree.utils.SeleniumUtils;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by ShiftTeacher on 12/16/2017.
 */
public class Browser extends BrowserBase {
    public static final int DEFAULT_WAIT_TIME = 10;

    public Browser(){
        super();
    }

    public void login(String email, String password) {
        typeText(By.id("spree_user_email"), email);
        typeText(By.id("spree_user_password"), password);
        click(By.xpath("//input[@name='commit']"));
    }

    public void bringLoginScreen() {
        WebElement loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();
    }

    public void verifyLoginSuccess(){
        WebElement loginSuccessLabel = driver.findElement(By.xpath("//div[@id='content']/div[contains(@class,'alert-success')]"));
        String successText = loginSuccessLabel.getText();
        Assert.assertEquals("Logged in successfully",successText);
    }

    public void verifyLoginNotSuccess(){
        WebElement loginSuccessLabel = driver.findElement(By.xpath("//div[@id='content']/div[contains(@class,'alert-error')]"));
        String successText = loginSuccessLabel.getText();
        Assert.assertEquals("Invalid email or password.",successText);
    }


    public void navigate(String url){
        this.driver.navigate().to(url);
    }
    public void typeText(By by, String text){

        WebElement element = waitForElementDisplayed(by, SeleniumUtils.DEFAULT_WAIT_TIME);
        highlight(element);
        element.clear();
        element.sendKeys(text);
    }

    public void click(By by){
        WebElement element = waitForElementDisplayed(by,SeleniumUtils.DEFAULT_WAIT_TIME);
        highlight(element);
        element.click();
    }

    public void delayFor(int timeInMili){
        try {
            Thread.sleep(timeInMili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement waitForElement(final By locator, int timeToWaitInSec) {
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeToWaitInSec, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });

        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIME,TimeUnit.SECONDS);
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

    public WebElement textToBePresentInElementLocated(By by, String textToWait, int timeToWait, TimeUnit timeUnit){
        Boolean found = false;
        WebElement element = null;
        try {
            element = driver.findElement(by);
            found = fluentWait(timeToWait, timeUnit).until(ExpectedConditions.textToBePresentInElementLocated(by, textToWait));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        if(!found) {
            System.out.println("Element with the text '" + textToWait + "' not found");
            return  null;
        }

        return element;
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
    public FluentWait<WebDriver> fluentWait() {
        return fluentWait(DEFAULT_WAIT_TIME,TimeUnit.SECONDS);
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


    public void scrollToElement(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        delayFor(3000);
    }

    protected void highlight(WebElement element) {
        for (int i = 0; i < 2; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 2px solid red;");
            delayFor(200);
            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "");
            delayFor(200);
        }
    }


    public String getLastWIndowHandle(){
        Set<String> winHdls = driver.getWindowHandles();
        return winHdls.toArray()[winHdls.size() - 1].toString();
    }

    public void switchToLastWindow(){
        String win = getLastWIndowHandle();
        driver.switchTo().window(win);
    }

    public void closeLastWindow(){
        switchToLastWindow();
        driver.close();
    }

    public void switchToWindow(String winTitle){
        Set<String> winHdls = driver.getWindowHandles();
        Iterator<String> iterator = winHdls.iterator();
        while (iterator.hasNext()){
            String win = iterator.next();
            driver.switchTo().window(win);
            String currentTitle = driver.getTitle();
            if(currentTitle.contains(winTitle)){
                return;
            }
        }
        throw new RuntimeException("Window with the title contain '" + winTitle + "' was not found.");
    }
    public void switchToWindowByURL(String url){
        Set<String> winHdls = driver.getWindowHandles();
        Iterator<String> iterator = winHdls.iterator();
        while (iterator.hasNext()){
            String win = iterator.next();
            driver.switchTo().window(win);
            String currentUrl = driver.getCurrentUrl();
            if(currentUrl.contains(url)){
                return;
            }
        }
        throw new RuntimeException("Window with the URL contain '" + url + "' was not found.");
    }

    public void switchToWindow(int winIndex){
        Set<String> winHdls = driver.getWindowHandles();

        if(winIndex < winHdls.size()) {
            String win = winHdls.toArray()[winIndex].toString();
            driver.switchTo().window(win);
        }
        else
        {
            throw new RuntimeException("Window with the index '" + winIndex + "' not found.");
        }
    }

    public void closeWindow(String title){
        switchToWindow(title);
        driver.close();
        switchToLastWindow();
    }

    public void closeWindow(int winIndex){
        switchToWindow(winIndex);
        driver.close();
        switchToLastWindow();
    }

    public void closeAllOpenWindowExceptCurrent(){
        String currentWindowHnd = driver.getWindowHandle();
        Set<String> windowList = driver.getWindowHandles();
        for(String window : windowList){
            if(!currentWindowHnd.contentEquals(window)){
                driver.switchTo().window(window);
                driver.close();
            }
        }
        driver.switchTo().window(currentWindowHnd);
    }

}
