package com.shiftedtech.qa.spree;

import com.shiftedtech.qa.spree.framework.SpreeFramework;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by ShiftTeacher on 12/9/2017.
 */
public class SpreeScriptBase {

    protected WebDriver driver;
    protected SpreeFramework spree;

    @Before
    public void setUp(){
        ChromeDriverManager.getInstance().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);

        spree = new SpreeFramework(driver);

        driver.navigate().to("http://spree.shiftedtech.com");
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    public void delayFor(int timeInMili){
        //spree.getUtils().delayFor(timeInMili);
        spree.delayFor(timeInMili);
    }


}
