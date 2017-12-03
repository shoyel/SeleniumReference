package com.shiftedtech.qa.scripts;

import com.shiftedtech.qa.ScriptBase;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by iivaan on 12/2/17.
 */
public class MultipleWindowTest extends ScriptBase {

    @Before
    @Override
    public void setUp(){
        super.setUp();
        driver.get("http://shifttest.shiftedtech.com/components/windows");
    }

    @Test
    public void test1(){

        String winHandle = driver.getWindowHandle();
        System.out.println("Current window handle: " + winHandle);

        Set<String> winList =  driver.getWindowHandles();
        System.out.println("Window count: " + winList.size());

        WebElement element = driver.findElement(By.xpath(".//*[@id='window-01']/button"));
        element.click();

        Set<String> winListNew =  driver.getWindowHandles();
        System.out.println("Window count after new window: " + winListNew.size());

        Object[] arrayWin = winListNew.toArray();
        String newWInHandle = arrayWin[arrayWin.length -1].toString();
        //String newWInHandle = winListNew.toArray()[winListNew.size() -1].toString();
        System.out.println("New window handle: " + newWInHandle);
        driver.switchTo().window(newWInHandle);

        System.out.println("Win Title: " + driver.getTitle());

        driver.close();

        //driver.switchTo().window(winHandle);
    }

    @Test
    public void test2(){

        WebElement element = driver.findElement(By.xpath(".//*[@id='window-01']/button"));
        element.click();


        switchToLastWindow();

        System.out.println("Win Title: " + driver.getTitle());

        closeLastWindow();

        //switchToLastWindow();

    }

    @Test
    public void test3(){

        WebElement element = driver.findElement(By.xpath(".//*[@id='window-01']/button"));
        element.click();


        switchToWindow("Form Testing – Shift Education of Technology");

        System.out.println("Win Title: " + driver.getTitle());

        driver.close();

        //switchToLastWindow();

    }

    @Test
    public void test3_URL(){

        WebElement element = driver.findElement(By.xpath(".//*[@id='window-01']/button"));
        element.click();


        switchToWindowByURL("http://shifttest.shiftedtech.com/components/form");

        System.out.println("Win Title: " + driver.getTitle());

        driver.close();

        //switchToLastWindow();

    }

    @Test
    public void test4(){

        WebElement element = driver.findElement(By.xpath(".//*[@id='window-01']/button"));
        element.click();


        switchToWindow(1);

        System.out.println("Win Title: " + driver.getTitle());

        driver.close();

        switchToLastWindow();

    }

    @Test
    public void test5(){

        WebElement element = driver.findElement(By.xpath(".//*[@id='window-01']/button"));
        element.click();

        closeWindow("Form Testing – Shift Education of Technology");

        System.out.println("Win Title: " + driver.getTitle());

    }

    @Test
    public void test6(){

        WebElement element = driver.findElement(By.xpath(".//*[@id='window-01']/button"));
        element.click();

        closeWindow(1);

        System.out.println("Win Title: " + driver.getTitle());

    }

    @Test
    public void test7(){

        String winHandle = driver.getWindowHandle();
        System.out.println("Current window handle: " + winHandle);

        WebElement element = driver.findElement(By.xpath(".//*[@id='window-01']/button"));
        element.click();

        System.out.println("closeAllOpenWindowExceptCurrent");
        closeAllOpenWindowExceptCurrent();

        winHandle = driver.getWindowHandle();
        System.out.println("Current window handle: " + winHandle);
    }
}
