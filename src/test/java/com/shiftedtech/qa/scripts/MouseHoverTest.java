package com.shiftedtech.qa.scripts;

import com.shiftedtech.qa.ScriptBase;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.Locatable;

/**
 * Created by iivaan on 12/2/17.
 */
public class MouseHoverTest extends ScriptBase {

    @Before
    @Override
    public void setUp(){
        super.setUp();
        driver.get("http://shifttest.shiftedtech.com/components/hover");
    }

    @Test
    public void hoverWithActionClass(){
        WebElement element = driver.findElement(By.id("dropdown-menu-hover-01"));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();

        WebElement itemToClick = driver.findElement(By.xpath(".//*[@id='hover-01']//a[text()='Action']"));
        itemToClick.click();

        delayFor(5000);


    }

    @Test
    public void hoverWithLocatable(){
        WebElement element = driver.findElement(By.id("dropdown-menu-hover-01"));

        Locatable hoverItem = (Locatable) element;
        Mouse mouse = ((HasInputDevices) driver).getMouse();
        mouse.mouseMove(hoverItem.getCoordinates());

        WebElement itemToClick = driver.findElement(By.xpath(".//*[@id='hover-01']//a[text()='Action']"));
        itemToClick.click();

        delayFor(5000);


    }

    @Test
    public void hoverWithJS(){

        WebElement element = driver.findElement(By.id("dropdown-menu-hover-01"));

        delayFor(2000);

        //String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
        //((JavascriptExecutor) driver).executeScript(mouseOverScript,element);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].onmouseover()", element);

        WebElement itemToClick = driver.findElement(By.xpath(".//*[@id='hover-01']//a[text()='Action']"));
        itemToClick.click();

        delayFor(5000);

    }


    /**
     * Mouse Hover element with Action class
     * @param element
     */
    private void hoverAction(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    /**
     * Mouse Hover element with Locatable and Mouse class
     * @param element
     */
    private void hoverLocatable(WebElement element){
        Locatable hoverItem = (Locatable) element;
        Mouse mouse = ((HasInputDevices) driver).getMouse();
        mouse.mouseMove(hoverItem.getCoordinates());
    }

    /**
     * Mouse Hover element with JavascriptExecutor
     * @param HoverElement
     */
    public void hoverJScript(WebElement HoverElement) {
        try {
            if (isElementPresent(HoverElement)) {
                String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
                ((JavascriptExecutor) driver).executeScript(mouseOverScript,HoverElement);
            } else {
                System.out.println("Element was not visible to hover " + "\n");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Element with " + HoverElement
                    + "is not attached to the page document"
                    + e.getStackTrace());
        }
    }

    public static boolean isElementPresent(WebElement element) {
        boolean flag = false;
        try {
            if(element.isDisplayed() || element.isEnabled()) flag = true;
        } catch (NoSuchElementException e) {
            flag = false;
        }
        return flag;
    }
}
