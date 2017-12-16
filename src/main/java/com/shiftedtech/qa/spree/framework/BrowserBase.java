package com.shiftedtech.qa.spree.framework;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by ShiftTeacher on 12/16/2017.
 */
public class BrowserBase {
    protected WebDriver driver;

    public BrowserBase(){
        ChromeDriverManager.getInstance().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public void closeBrowser(){
        this.driver.close();
    }
    public void quitBrowser(){
        this.driver.quit();
    }
}
