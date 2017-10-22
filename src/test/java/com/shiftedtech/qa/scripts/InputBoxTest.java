package com.shiftedtech.qa.scripts;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by ShiftStudent on 10/15/2017.
 */
public class InputBoxTest {

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
        driver.manage().window().maximize();

       // System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
        //driver = new ChromeDriver();

        driver.get("http://shifttest.shiftedtech.com/components/text_box");
    }

    @Test
    public void test1(){

        //driver.findElement(By.id("username-text")).sendKeys("Shiftedtech");

        WebElement element = driver.findElement(By.id("username-text"));

        highlight(element);

        element.clear();
        element.sendKeys("Shiftedtech");
        element.sendKeys(Keys.TAB);
        String value = element.getAttribute("value");

        System.out.println("Value: " + value);

        Assert.assertEquals("Shiftedtech"  ,value);



    }

    @Test
    public void password(){
        WebElement element = driver.findElement(By.id("password-text"));
        String password = "password";
        element.sendKeys(password);
    }

    @Test
    public void dateInput(){


        WebElement element = driver.findElement(By.xpath("//input[@id='date' and @class='form-control']"));

        if(driver instanceof FirefoxDriver) {
            element.sendKeys("10/28/2017");
        }
        else {
            element.sendKeys("10");
            //element.sendKeys(Keys.TAB);
            element.sendKeys("28");
            // element.sendKeys(Keys.TAB);
            element.sendKeys("2017");
            // element.sendKeys(Keys.TAB);
        }

    }

    @Test
    public void numberInputBoxClick(){
        WebElement element = driver.findElement(By.xpath("//table//input[@id='number']"));
        Actions actionBuilder = new Actions(driver);
        actionBuilder.moveToElement(element,152,1).click().build().perform();

    }

    @Test
    public void numberInputBox(){
        WebElement element = driver.findElement(By.xpath("//table//input[@id='number']"));
        numberInput(element,20);
    }

    private void numberInput(WebElement element, int number) {
        for(int i = 1; i < number + 1; i++) {
            element.sendKeys(Keys.ARROW_UP);
        }
    }

    @Test
    public void rangeInputBox() throws InterruptedException {
        WebElement element = driver.findElement(By.id("range"));

        System.out.println("Value:" + element.getAttribute("value"));

        highlight(element);
        rangeScroll(element,90);

        System.out.println("Value: " + element.getAttribute("value"));

    }

    public void rangeScroll(WebElement element, int percent){
        int height = element.getSize().getHeight();
        int width = element.getSize().getWidth();


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",element);
        delayFor(1000);

        Actions actionBuilder = new Actions(driver);
        Action action = actionBuilder.clickAndHold(element)
                .moveByOffset(-(width/2),0)
                .moveByOffset((width/100)*percent,0)
                .release()
                .build();

        action.perform();
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

    @AfterClass
    public static void afterClass(){

    }

}
