package com.shiftedtech.qa.spree;

import com.shiftedtech.qa.spree.framework.SpreeFramework;
import com.shiftedtech.qa.spree.framework.pages.HomePage;
import com.shiftedtech.qa.spree.framework.pages.LoginPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by ShiftTeacher on 12/17/2017.
 */
public class SpreePageObjectModelScriptBase {
    protected WebDriver driver;

    protected HomePage homePage;
    protected LoginPage loginPage;


    @Before
    public void setUp(){
        ChromeDriverManager.getInstance().setup();

        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

        driver.navigate().to("http://spree.shiftedtech.com");
    }

    @After
    public void tearDown(){
        homePage = null;
        loginPage = null;
        driver.close();
        driver.quit();
    }

}
