package com.shiftedtech.qa.scripts;

import com.shiftedtech.qa.ScriptBase;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by iivaan on 12/2/17.
 */
public class IframeHandleTest extends ScriptBase {

    @Before
    @Override
    public void setUp(){
        super.setUp();
        driver.get("http://shifttest.shiftedtech.com/components/iframe");
    }

    @Test
    public void switchFrameByName(){
        scrollToElement(driver.findElement(By.xpath("//h2[text()='Iframe 01']")));
        driver.switchTo().frame("iframe-01");
        WebElement element = driver.findElement(By.xpath(".//*[@id='topnavbar']/div/div[1]/button"));
        highlight(element);
        element.click();

        delayFor(5000);
    }

    @Test
    public void switchFrameByIndex(){
        scrollToElement(driver.findElement(By.xpath("//h2[text()='Iframe 02']")));
        driver.switchTo().frame(1);
        WebElement element = driver.findElement(By.xpath(".//*[@id='topnavbar']/div/div[1]/button"));
        highlight(element);
        element.click();
        delayFor(5000);
    }

    @Test
    public void switchFrameByWebElement(){
        scrollToElement(driver.findElement(By.xpath("//h2[text()='Iframe 01']")));
        List<WebElement> iFrameList = driver.findElements(By.tagName("iframe"));
        for(WebElement iframe : iFrameList){
            String src = iframe.getAttribute("src");
            System.out.println("Src: " + src);
            if(src.contains("/components")){
                driver.switchTo().frame(iframe);
                break;
            }
        }
        WebElement element = driver.findElement(By.xpath(".//*[@id='topnavbar']/div/div[1]/button"));
        highlight(element);
        element.click();
        delayFor(5000);
        // Move back to original page
        driver.switchTo().defaultContent();
        delayFor(5000);
        scrollToElement(driver.findElement(By.xpath("//h1")));
    }

    @Test
    public void numberOfIFrames(){
        //By executing a java script
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        Integer numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
        System.out.println("Number of iframes on the page are " + numberOfFrames);

        //By finding all the web elements using iframe tag
        List<WebElement> iframeElements = driver.findElements(By.tagName("iframe"));
        System.out.println("The total number of iframes are " + iframeElements.size());
    }

    @Test
    public void test1() {
        driver.navigate().to("http://allwebco-templates.com/support/S_script_IFrame.htm");

        WebElement iFrame = driver.findElement(By.xpath("//iframe[@name='Framename'][1]"));
        driver.switchTo().frame(iFrame);

        WebElement spanElement = driver.findElement(By.xpath("//div[1]/span"));

        highlight(spanElement);

        delayFor(5000);

    }

}
