package com.shiftedtech.qa.scripts;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

/**
 * Created by ShiftTeacher on 10/22/2017.
 */
public class ButtonTest {

    private WebDriver driver;

    @BeforeClass
    public static void beforeClass(){
        // System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe");

    }

    @Before
    public void setUp(){

        //System.setProperty("webdriver.gecko.driver","C:/MyDevelopment/SSMB/SeleniumReference/src/main/resources/drivers/geckodriver.exe");
        System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();


        //driver.manage().window().maximize();

        // System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
        //driver = new ChromeDriver();

        driver.get("http://shifttest.shiftedtech.com/components/button");
    }
    @Test
    public void test1(){
        WebElement element = driver.findElement(By.id("html-button-01"));

        String label = element.getText();
        System.out.println("Label: " + label);
        Assert.assertEquals("Button",label);

        element.click();



    }

    @Test
    public void test2(){
        List<WebElement> listOfElement =  driver.findElements(By.xpath("//div[@class='col-md-6']/*[@type='button']"));

        for(WebElement element : listOfElement) {

            highlight(element);
            String label = element.getText();
           // if(label.trim() == ""){}
            if(label.trim().contentEquals("")){
                label = element.getAttribute("value");
            }

            System.out.println("Label: " + label);
            //Assert.assertEquals("Button", label);
            //element.click();
        }


    }

    @Test
    public void test3(){
        WebElement element = driver.findElement(By.id("html-button-01"));
        //element.click();
        jsClickEx(element);
    }

    @Test
    public void test4(){
        //WebElement element = driver.findElement(By.xpath("//button[@id='split-button-01']/../button[contains(@class,'dropdown-toggle')]"));
        WebElement element = driver.findElement(By.xpath("//button[@id='split-button-01']/following-sibling::button"));
        element.click();
        delayFor(1000);
        WebElement popUpMenu = driver.findElement(By.xpath("//button[@id='split-button-01']/following-sibling::ul//a[text()='Another action']"));
        popUpMenu.click();
    }

    @Test
    public void test5(){
        clickButtonGroupPopup(By.xpath("//button[@id='split-button-01']"),"Action");
    }
    @Test
    public void test6(){
        clickButtonGroupPopup(By.xpath("//button[@id='split-button-02']"),"Something else here");
    }
    @Test
    public void test7(){
        clickButtonGroupPopupEx(By.xpath("//button[@id='split-button-02']"),"Something else Here");
    }

    public void clickButtonGroupPopup(By by, String itrmToClick){
        WebElement element = driver.findElement(by);
        WebElement dropDown = element.findElement(By.xpath("./following-sibling::button"));
        dropDown.click();
        delayFor(1000);
        //WebElement popUpMenu = driver.findElement(By.xpath("//button[@id='split-button-01']/following-sibling::ul//a[text()='Another action']"));
       // WebElement popUpMenu = driver.findElement(By.xpath("//button[@id='split-button-01']/following-sibling::ul//a[text()='" + "Another action" + "']"));
       // WebElement popUpMenu = driver.findElement(By.xpath("//button[@id='split-button-01']/following-sibling::ul//a[text()='" + itrmToClick + "']"));
        WebElement popUpMenu = element.findElement(By.xpath("./following-sibling::ul//a[text()='" + itrmToClick + "']"));
        popUpMenu.click();
    }

    public void clickButtonGroupPopupEx(By by, String itrmToClick){
        WebElement element = driver.findElement(by);
        WebElement dropDown = element.findElement(By.xpath("./following-sibling::button"));
        dropDown.click();
        delayFor(1000);
        List<WebElement> popUpMenus = element.findElements(By.xpath("./following-sibling::ul//a"));
        for(WebElement item : popUpMenus){
            String text = item.getText();
            if(text.equalsIgnoreCase(itrmToClick)){
                item.click();
                break;
            }
        }
    }


    public void delayFor(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void highlight(WebElement element) {
        for (int i = 0; i < 2; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 2px solid red;");
            delayFor(400);
            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);", element, "");
            delayFor(1000);
        }
    }
    public void jsClickEx(WebElement element){
        ((JavascriptExecutor) driver).executeScript("var el=arguments[0]; setTimeout(function() { el.click(); }, 100);",  element);
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

    @AfterClass
    public static void afterClass(){

    }
}
