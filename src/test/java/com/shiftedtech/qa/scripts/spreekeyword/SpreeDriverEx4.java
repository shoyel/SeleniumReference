package com.shiftedtech.qa.scripts.spreekeyword;

import com.shiftedtech.qa.spree.SpreeScriptBaseTestNg;
import com.shiftedtech.qa.spree.utils.ExcelReader;
import com.shiftedtech.qa.spree.utils.KeywordDriver;
import com.shiftedtech.qa.spree.utils.TestObject;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by ShiftTeacher on 1/13/2018.
 */
public class SpreeDriverEx4 extends SpreeScriptBaseTestNg {
    @Test(dataProvider = "scriptNames", dataProviderClass = KeywordDriver.class)
    public void driverScript(String index, String scriptFile, String scriptName, String runScript){
        kd.processKeywordScript(scriptFile,scriptName,runScript);
    }
}
