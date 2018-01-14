package com.shiftedtech.qa.spree;

import com.shiftedtech.qa.spree.framework.SpreeFramework;
import com.shiftedtech.qa.spree.framework.pages.HomePage;
import com.shiftedtech.qa.spree.framework.pages.LoginPage;
import com.shiftedtech.qa.spree.utils.KeywordDriver;
import com.shiftedtech.qa.spree.utils.WebElementUtils;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * Created by ShiftTeacher on 1/7/2018.
 */
public class SpreeScriptBaseTestNg {
    protected WebDriver driver;
    protected KeywordDriver kd = null;

    protected WebElementUtils webElementUtils;
    protected HomePage homePage;
    protected LoginPage loginPage;


    @BeforeClass
    public void beforeClass() throws Exception {

    }

    @BeforeMethod
    public void beforeMethod() throws Exception {


        ChromeDriverManager.getInstance().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);

        webElementUtils = new WebElementUtils(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

        kd = new KeywordDriver(driver);
        kd.loadObjectRepo();

        driver.navigate().to("http://spree.shiftedtech.com");

    }

    @AfterMethod
    public void afterMethod(){
        driver.close();
        driver.quit();

        webElementUtils = null;
        homePage = null;
        loginPage = null;
        kd = null;
    }

    public void delayFor(int timeInMili){
        //spree.getUtils().delayFor(timeInMili);
        homePage.delayFor(timeInMili);
    }

    //BeforeClass
    //BeforeMethod
    //Test
    //AfterMethod
    //BeforeMethod
    //Test
    //AfterMethod
    //AfterClass
}
