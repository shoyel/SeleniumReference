package com.shiftedtech.qa.scripts.spree;

import com.shiftedtech.qa.spree.SpreeScriptBaseJUnit;
import org.junit.Test;

/**
 * Created by ShiftTeacher on 12/9/2017.
 */
public class SpreeLoginTest extends SpreeScriptBaseJUnit {

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
