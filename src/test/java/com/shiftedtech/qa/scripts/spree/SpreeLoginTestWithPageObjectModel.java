package com.shiftedtech.qa.scripts.spree;

import com.shiftedtech.qa.spree.SpreePageObjectModelScriptBase;
import org.junit.Test;

/**
 * Created by ShiftTeacher on 12/17/2017.
 */
public class SpreeLoginTestWithPageObjectModel extends SpreePageObjectModelScriptBase{

    @Test
    public void test1(){
      homePage.verifyPageTitle("Spree Demo Site");
      homePage.navigateToLoginPage();
      loginPage.verifyPageTitle("Login - Spree Demo Site");
      loginPage.login("shiftqa01@gmail.com", "shiftedtech");
      homePage.verifyLoginSuccess();
    }
    @Test
    public void test2(){
        homePage.navigateToLoginPage();
        loginPage.login("shiftqa01@gmail.com", "invalid");
        loginPage.verifyLoginNotSuccess();
    }

}
