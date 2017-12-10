package com.shiftedtech.qa.scripts.spree;

import com.shiftedtech.qa.spree.SpreeScriptBase;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by ShiftTeacher on 12/9/2017.
 */
public class SpreeLoginTest extends SpreeScriptBase{

    @Test
    public void test1(){

        spree.bringLoginScreen();
        spree.login("shiftqa01@gmail.com", "shiftedtech");
        spree.verifyLoginSuccess();
    }

    @Test
    public void test2(){
        spree.bringLoginScreen();
        //spree.getUtils().delayFor(1000);
        spree.login("kartick017@yahoo.com", "bangladesh");
        delayFor(1000);
        //spree.getUtils().switchToLastWindow();
        spree.verifyLoginSuccess();
    }
    @Test
    public void test3(){
        spree.bringLoginScreen();
        spree.login("kartick017@yahoo.com", "bangladeshXXX");
        spree.verifyLoginNotSuccess();
    }

    @Test
    public void test4(){
        spree.bringLoginScreen();
        spree.login("kartick017xxx@yahoo.com", "bangladesh");
        spree.verifyLoginNotSuccess();
    }



}
