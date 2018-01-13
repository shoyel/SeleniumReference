package com.shiftedtech.qa.scripts.spreekeyword;

import com.shiftedtech.qa.spree.SpreeScriptBaseTestNg;
import com.shiftedtech.qa.spree.utils.ExcelReader;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Created by ShiftTeacher on 1/13/2018.
 */
public class SpreeDriverEx3 extends SpreeScriptBaseTestNg {

    @Test(dataProvider = "scriptNames")
    public void driverScript(String index, String scriptFile, String scriptName, String runScript){
        Object[][] data = null;
        String dataFile = System.getProperty("user.dir") + scriptFile;

        if(runScript.equalsIgnoreCase("N")){
            throw new SkipException("Script[" + scriptName + "] " + "skipped");
        }

        ExcelReader reader = new ExcelReader(dataFile);
        data = reader.getExcelSheetData(scriptName,true);
        for(int i = 0; i < data.length; i++){
            System.out.println("Row[" + i + "] " + Arrays.deepToString(data[i]));

            String stepNumber = data[i][0].toString();
            String keyword = data[i][1].toString();
            String keywordData = data[i][2].toString();
            System.out.println("Step[" + stepNumber + "] " + keyword);

            if(keyword.equalsIgnoreCase("verifyPageTitle")) {
                homePage.verifyPageTitle(keywordData);
            }else if(keyword.equalsIgnoreCase("navigateToLoginPage")) {
                homePage.navigateToLoginPage();
            }else if(keyword.equalsIgnoreCase("login")) {
                String dataPart[] = keywordData.split("\\|");
                loginPage.login(dataPart[0], dataPart[1]);
            }else if(keyword.equalsIgnoreCase("verifyLoginSuccess")) {
                homePage.verifyLoginSuccess();
            }else if(keyword.equalsIgnoreCase("verifyLoginNotSuccess")) {
                loginPage.verifyLoginNotSuccess();
            }else{
                System.out.println("******** Unknown KEY WORD *************");
            }
        }
    }

    @DataProvider
    public static Object[][] scriptNames(){
        Object[][] data = null;
        String dataFile = System.getProperty("user.dir") + "/src/test/resources/KeywordScriptList.xlsx";

        ExcelReader reader = new ExcelReader(dataFile);
        data = reader.getExcelSheetData("ScriptList",true);
        return data;
    }
}
