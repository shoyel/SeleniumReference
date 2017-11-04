package com.shiftedtech.qa.scripts;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

/**
 * Created by ShiftTeacher on 10/29/2017.
 */
public class CheckBoxTest {
    private WebDriver driver;

    @Before
    public void setUp(){
        //System.setProperty("webdriver.gecko.driver","C:/MyDevelopment/SSMB/SeleniumReference/src/main/resources/drivers/geckodriver.exe");
        System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        // System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
        //driver = new ChromeDriver();

        driver.get("http://shifttest.shiftedtech.com/components/check_box");
    }

    @Test
    public void test1(){

        By by = By.id("html-chk-box-01");
        WebElement element1 = driver.findElement(by);
        element1.click();

        WebElement element2 = driver.findElement(By.id("html-chk-box-02"));
        element2.click();



    }

    @Test
    public void test2(){
        selectHtmlOption("Option 1");
        selectHtmlOption("Option 2");
    }
    @Test
    public void test3(){
        selectHtmlOptionEx("Option 1");
        selectHtmlOptionEx("Option 2");
    }

    @Test
    public void test4(){
        By by = By.xpath(".//form[@action='form-checkbox']");

        selectHtmlOptionEx2(by,"Option 1");
        selectHtmlOptionEx2(by,"Option 2");
    }

    @Test
    public void test5(){
        By checkBoxGroup = By.xpath("//div[@id='css-chk-box-grp-01']");

        checkUncheckBootstrapCheckbox(checkBoxGroup,"Option 1",true);
        checkUncheckBootstrapCheckbox(checkBoxGroup,"Option 2",false);
        delayFor(2000);
        checkUncheckBootstrapCheckbox(checkBoxGroup,"Option 1",false);

        boolean status = isBootstrapCheckboxSelected(checkBoxGroup,"Option 1");
        Assert.assertFalse(status);

    }



    public void selectHtmlOption(String optionName){
        By by = null;
        if(optionName.equalsIgnoreCase("Option 1")) {
             by = By.id("html-chk-box-01");
        }else if(optionName.equalsIgnoreCase("Option 2")) {
            by = By.id("html-chk-box-02");
        }

        WebElement element = driver.findElement(by);
        element.click();
    }

    public void selectHtmlOptionEx(String optionName){
        //By by = By.xpath(".//form[@action='form-checkbox']/label");

        WebElement CheckboxGroup = driver.findElement(By.xpath(".//form[@action='form-checkbox']"));
        List<WebElement> labelList = CheckboxGroup.findElements(By.tagName("label"));
        for (WebElement item : labelList){
            String text = item.getText();
            if(text.equalsIgnoreCase(optionName)){
                WebElement checkbox = item.findElement(By.xpath("./preceding-sibling::input[1]"));
                checkbox.click();
                break;
            }
        }
    }

    public void selectHtmlOptionEx2(By checkboxGroup, String optionName){
        WebElement CheckboxGroup = driver.findElement(checkboxGroup);
        List<WebElement> labelList = CheckboxGroup.findElements(By.tagName("label"));
        for (WebElement item : labelList){
            String text = item.getText();
            if(text.equalsIgnoreCase(optionName)){
                WebElement checkbox = item.findElement(By.xpath("./preceding-sibling::input[1]"));
                checkbox.click();
                break;
            }
        }
    }

    public void checkUncheckBootstrapCheckbox(By checkboxGroup, String optionName, boolean checked){
        WebElement CheckboxGroup = driver.findElement(checkboxGroup);
        List<WebElement> labelList = CheckboxGroup.findElements(By.xpath(".//label"));
        for (WebElement item : labelList){
            String text = item.getText();
            if(text.equalsIgnoreCase(optionName)){
                WebElement checkbox = item.findElement(By.tagName("input"));
                boolean checkStatus =  Boolean.parseBoolean(checkbox.getAttribute("checked"));
                if (checked && !checkStatus || !checked && checkStatus) {
                    checkbox.click();
                }
                break;
            }
        }
    }
    public boolean isBootstrapCheckboxSelected(By checkboxGroup, String optionName){
        WebElement CheckboxGroup = driver.findElement(checkboxGroup);
        List<WebElement> labelList = CheckboxGroup.findElements(By.xpath(".//label"));
        for (WebElement item : labelList){
            String text = item.getText();
            if(text.equalsIgnoreCase(optionName)){
                WebElement checkbox = item.findElement(By.tagName("input"));
                boolean checkStatus =  Boolean.parseBoolean(checkbox.getAttribute("checked"));
                return checkStatus;
            }
        }
        return false;
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
