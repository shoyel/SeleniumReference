package com.shiftedtech.qa.scripts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ShiftTeacher on 11/4/2017.
 */
public class ComboBoxTest {
    private WebDriver driver;

    @Before
    public void setUp(){
        //System.setProperty("webdriver.gecko.driver","C:/MyDevelopment/SSMB/SeleniumReference/src/main/resources/drivers/geckodriver.exe");
        System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        // System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
        //driver = new ChromeDriver();

        driver.get("http://shifttest.shiftedtech.com/components/dropdown_menu");
    }

    @Test
    public void test1(){

        By by = By.xpath("//*[@id='html-select-01']/select");
        WebElement element1 = driver.findElement(by);

        Select select = new Select(element1);
        select.selectByVisibleText("Option 4");
        //select.selectByIndex(2);
        //select.selectByValue("Option 3");
    }

    @Test
    public void test2(){

        By by = By.xpath("//*[@id='html-select-01']/select");
        WebElement element = driver.findElement(by);
        element.click();
        delayFor(1000);
        List<WebElement> options = element.findElements(By.tagName("option"));
        for (WebElement item : options){
            String text = item.getText();
            if(text.contains("Option 3")){
                item.click();
                delayFor(100);
                element.click();
                break;
            }
        }


        //Select select = new Select(element1);
        //select.selectByVisibleText("Option 4");
        //select.selectByIndex(2);
        //select.selectByValue("Option 3");
    }
    @Test
    public void test3(){

        By by = By.xpath(".//*[@id='html-select-03']/select");
        WebElement element1 = driver.findElement(by);

        Select select = new Select(element1);
        select.selectByVisibleText("Option 4");
        select.selectByVisibleText("Option 2");

    }
    @Test
    public void test4(){

        By by = By.xpath(".//*[@id='html-select-03']/select");
        WebElement element1 = driver.findElement(by);

        Select select = new Select(element1);

        List<WebElement> options = select.getOptions();
        for (WebElement item : options){
            String text = item.getText();
            if(text.contains("3") || text.contains("4")){
                item.click();
            }
        }
    }
    @Test
    public void test5(){

        By by = By.xpath(".//*[@id='html-select-03']/select");
        WebElement element1 = driver.findElement(by);

        Select select = new Select(element1);
        String[] items = new String[2];
        items[0] = "Option 4";
        items[1] = "Option 1";
        //multiSelectList(select,items);

        String[] items2 = {"Option 4","Option 1"};
        multiSelectList(select,items2);

       // multiSelectList2(select,"Option 4","Option 1");
    }

    @Test
    public void test6(){

        By by = By.xpath(".//*[@id='html-select-03']/select");
        WebElement element1 = driver.findElement(by);

        Select select = new Select(element1);
        multiSelectList(select,"Option 4","Option 2", "Option 1");

        String[] items2 = {"Option 4","Option 1"};
        multiSelectList(select,items2);

    }


    public void multiSelectList(Select select, String... itemtoSelect){

        List<WebElement> options = select.getOptions();
        for (WebElement item : options){
            String text = item.getText();
            if(Arrays.asList(itemtoSelect).contains(text))
            {
             item.click();
            }
        }

    }

    public void multiSelectList2(Select select, String[] itemtoSelect){

        List<WebElement> options = select.getOptions();
        for (WebElement item : options){
            String text = item.getText();
            if(Arrays.asList(itemtoSelect).contains(text))
            {
                item.click();
            }
        }

    }


    @Test
    public void test7(){

        WebElement btn = driver.findElement(By.id("dropdown-menu-basic"));
        if(btn != null){
            btn.click();
            //WebElement parent = btn.findElement(By.xpath("./.."));
            WebElement parent = btn.findElement(By.xpath("./parent::*"));
            List<WebElement> list = parent.findElements(By.xpath("./ul/li/a"));
            for(WebElement item : list){
                String text = item.getText();
                System.out.println("Item found:" + text);
                if(text.contains("Action")){
                    item.click();
                    System.out.println("Item Clicked:" + text);
                    break;
                }
            }
        }
        else
        {
            System.out.println("Could not found the button");
        }

    }

    @Test
    public void test8(){

        WebElement btn = driver.findElement(By.id("dropdown-menu-basic"));
        if(btn != null){
            btn.click();
            WebElement sibling = btn.findElement(By.xpath("./following-sibling::*[1]"));
            List<WebElement> list = sibling.findElements(By.xpath("./li/a"));
            for(WebElement item : list){
                String text = item.getText();
                System.out.println("Item found:" + text);
                if(text.contains("Action")){
                    item.click();
                    System.out.println("Item Clicked:" + text);
                    break;
                }
            }
        }
        else
        {
            System.out.println("Could not found the button");
        }

    }



    public void delayFor(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @After
    public void tearDown(){

        //driver.close();
        try {
            //driver.quit();
        }
        catch (Exception ex){
        }
    }
}
