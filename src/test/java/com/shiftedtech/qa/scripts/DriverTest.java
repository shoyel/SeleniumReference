package com.shiftedtech.qa.scripts;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * Created by ShiftTeacher on 11/5/2017.
 */
public class DriverTest {
    private WebDriver driver = null;

    //http://seleniumhq.github.io/selenium/docs/api/java/index.html

    @Before
    public void setUp() throws MalformedURLException {
        //System.setProperty("webdriver.gecko.driver","C:/MyDevelopment/SSMB/SeleniumReference/src/main/resources/drivers/geckodriver.exe");
        //System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe");
        //driver = new FirefoxDriver();

         System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();




        //driver.navigate().to("http://shifttest.shiftedtech.com/components/check_box");
       // try {
            driver.navigate().to(new URL("http://stage.shiftvision.com"));
       // } catch (MalformedURLException e) {
        //    e.printStackTrace();
      //  }

        //driver.get("http://shifttest.shiftedtech.com/components/check_box");

        //driver.manage().window().maximize();

        //Dimension dim = driver.manage().window().getSize();
        //System.out.println(dim.toString());
        //driver.manage().window().setSize(new Dimension(dim.getWidth() / 2,dim.getHeight()/2));
        //driver.manage().window().setPosition(new Point(500,200));

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        try {
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        }
        catch (org.openqa.selenium.TimeoutException ex){
            ex.printStackTrace();
        }
        driver.manage().timeouts().setScriptTimeout(60,TimeUnit.SECONDS);
    }

    @Test
    public void test1() {
        WebElement element = driver.findElement(By.linkText("Sign in"));
        element.click();
    }


}
