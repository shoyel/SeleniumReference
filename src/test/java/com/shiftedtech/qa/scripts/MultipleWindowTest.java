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

        String newWInHandle = winListNew.toArray()[winListNew.size() -1].toString();
        System.out.println("New window handle: " + newWInHandle);
        driver.switchTo().window(newWInHandle);

        System.out.println("Win Title: " + driver.getTitle());

        driver.close();

        driver.switchTo().window(winHandle);

    }

    @Test
    public void test2(){

        WebElement element = driver.findElement(By.xpath(".//*[@id='window-01']/button"));
        element.click();


        switchToLastWindow();

        System.out.println("Win Title: " + driver.getTitle());

        closeLastWindow();

        switchToLastWindow();

    }

    @Test
    public void test3(){

        WebElement element = driver.findElement(By.xpath(".//*[@id='window-01']/button"));
        element.click();


        switchToWindow("Form Testing – Shift Education of Technology");

        System.out.println("Win Title: " + driver.getTitle());

        driver.close();

        switchToLastWindow();

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
        throw new RuntimeException("Window with the title contain '" + winTitle + "' not found.");
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
