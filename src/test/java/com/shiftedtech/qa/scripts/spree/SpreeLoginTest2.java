package com.shiftedtech.qa.scripts.spree;

import com.shiftedtech.qa.spree.framework.Browser;
import static com.shiftedtech.qa.spree.framework.objectrepo.LoginPageObjectRepo.*;

import com.shiftedtech.qa.spree.framework.objectrepo.ObjectRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Created by ShiftTeacher on 12/16/2017.
 */
public class SpreeLoginTest2 {
    private Browser browser;
    private ObjectRepo objRepo;

    @Before
    public void setUp(){
        browser = new Browser();
        objRepo = new ObjectRepo();
    }

    @Test
    public void test1(){
        browser.navigate("http://spree.shiftedtech.com");
        browser.bringLoginScreen();
        browser.login("shiftqa01@gmail.com", "shiftedtech");
        browser.verifyLoginSuccess();
    }
    @Test
    public void test2(){
        browser.navigate("http://spree.shiftedtech.com");
        browser.bringLoginScreen();

        browser.typeText(By.id("spree_user_email"), "shiftqa01@gmail.com");
        browser.typeText(By.id("spree_user_password"), "shiftedtech");
        browser.click(By.xpath("//input[@name='commit']"));

        browser.verifyLoginSuccess();
    }
    @Test
    public void test3(){
        browser.navigate("http://spree.shiftedtech.com");
        browser.bringLoginScreen();

        browser.getDriver().findElement(By.id("spree_user_email")).sendKeys();
        browser.getDriver().findElement(By.id("spree_user_password")).sendKeys("shiftedtech");
        browser.getDriver().findElement(By.xpath("//input[@name='commit']")).click();

        browser.verifyLoginSuccess();
    }
    @Test
    public void test4(){
        browser.navigate("http://spree.shiftedtech.com");
        browser.bringLoginScreen();

        browser.getDriver().findElement(By.id("spree_user_email")).sendKeys();
        browser.getDriver().findElement(By.id("spree_user_password")).sendKeys("xxx");
        browser.getDriver().findElement(By.xpath("//input[@name='commit']")).click();

        browser.verifyLoginNotSuccess();
    }
    @Test
    public void test5(){
        browser.navigate("http://spree.shiftedtech.com");
        browser.bringLoginScreen();

        browser.getDriver().findElement(EMAIL_TEXTBOX).sendKeys();
        browser.getDriver().findElement(PASSWORD_TEXTBOX).sendKeys("xxx");
        browser.getDriver().findElement(LOGIN_BUTTON).click();

        browser.verifyLoginNotSuccess();
    }

    @Test
    public void test6(){
        browser.navigate("http://spree.shiftedtech.com");

        browser.getDriver().findElement(objRepo.testObject("HomePage.LoginLink")).click();

        browser.getDriver().findElement(EMAIL_TEXTBOX).sendKeys();
        browser.getDriver().findElement(PASSWORD_TEXTBOX).sendKeys("xxx");
        browser.getDriver().findElement(LOGIN_BUTTON).click();

        browser.verifyLoginNotSuccess();
    }

    @After
    public void tearDown(){
        browser.closeBrowser();
        browser.quitBrowser();
    }
}
