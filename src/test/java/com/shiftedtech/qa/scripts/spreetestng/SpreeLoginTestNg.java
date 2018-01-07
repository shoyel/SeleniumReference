package com.shiftedtech.qa.scripts.spreetestng;

import com.shiftedtech.qa.spree.SpreeScriptBaseTestNg;
import com.shiftedtech.qa.spree.utils.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by ShiftTeacher on 1/7/2018.
 */
public class SpreeLoginTestNg extends SpreeScriptBaseTestNg {

    @Test(dataProvider = "loginDataProviderAsExcelWithPOI")
    public void verifySuccessfullLogin(String email, String password){
        homePage.verifyPageTitle("Spree Demo Site");
        homePage.navigateToLoginPage();
        loginPage.verifyPageTitle("Login - Spree Demo Site");
        loginPage.login(email, password);
        homePage.verifyLoginSuccess();
    }

    @DataProvider
    public static Object[][] loginDataProviderAsExcelWithPOI(){
        Object[][] data = null;
        String dataFile = System.getProperty("user.dir") + "/src/test/resources/SpreeLoginData.xls";

        ExcelReader reader = new ExcelReader(dataFile);
        data = reader.getExcelSheetData("Sheet1",true);
        return data;
    }
}
