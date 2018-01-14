package com.shiftedtech.qa.spree.utils;

import com.shiftedtech.qa.spree.framework.pages.HomePage;
import com.shiftedtech.qa.spree.framework.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by ShiftTeacher on 1/14/2018.
 */
public class KeywordDriver {
    private static String KW_SCRIPT_LIST = System.getProperty("user.dir") + "/src/test/resources/KeywordScriptList.xlsx";
    private static String KW_OBJECT_REPOSITORY = System.getProperty("user.dir") + "/src/test/resources/ObjectRepo.xlsx";

    private WebDriver driver;
    private HashMap<String,TestObject> objectRepository = null;

    protected WebElementUtils webElementUtils;
    protected HomePage homePage;
    protected LoginPage loginPage;


    public KeywordDriver(WebDriver driver){
        this.driver = driver;
        objectRepository = new HashMap<String, TestObject>();

        webElementUtils = new WebElementUtils(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

    }

    @DataProvider
    public static Object[][] scriptNames(){
        Object[][] data = null;

        ExcelReader reader = new ExcelReader(KW_SCRIPT_LIST);
        data = reader.getExcelSheetData("ScriptList",true);
        return data;
    }

    public void processKeywordScript(String scriptFile,String scriptName, String runScript ){
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
            String pageName = data[i][2].toString();
            String toName = data[i][3].toString();
            String keywordData = data[i][4].toString();

            System.out.println("Step[" + stepNumber + "] " + keyword);

            if(keyword.equalsIgnoreCase("click")) {
                By byObj = getTestObjectBy(pageName,toName);
                webElementUtils.click(byObj);

            }else if(keyword.equalsIgnoreCase("typeText")) {
                By byObj = getTestObjectBy(pageName,toName);
                webElementUtils.typeText(byObj,keywordData);
            }else if(keyword.equalsIgnoreCase("verifyText")) {
                By byObj = getTestObjectBy(pageName,toName);
                webElementUtils.verifyText(byObj,keywordData);
            }else if(keyword.equalsIgnoreCase("verifyPageTitle")) {
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
    public void loadObjectRepo() throws Exception {
        loadObjectRepo(KW_OBJECT_REPOSITORY);
    }
    public void loadObjectRepo(String... fineNames) throws Exception {

        for(String file : fineNames){

            ExcelReader reader = new ExcelReader(file);
            String[][] data = reader.getExcelSheetData("OR",true);
            for(int i = 0; i < data.length; i++){
                System.out.println("**************** TestObject [ " + i + " ]**********************");

                TestObject ts = new TestObject();
                ts.setTestObjectId(data[i][0]);
                ts.setPage(data[i][1]);
                ts.setTestObjectName(data[i][2]);
                ts.setDescription(data[i][3]);
                ts.setBy(data[i][4]);
                ts.setUsing(data[i][5]);

                System.out.println(ts.toString());

                String KEY = ts.getPage().toUpperCase() + "." + ts.getTestObjectName().toUpperCase();
                if(!objectRepository.containsKey(KEY)) {
                    objectRepository.put(KEY, ts);
                }
            }
        }
    }

    public By getTestObjectBy(String pageName, String toName){
        By byObj = null;
        String KEY = pageName.toUpperCase() + "." + toName.toUpperCase();
        if(objectRepository.containsKey(KEY)) {
            TestObject to = objectRepository.get(KEY);
            byObj = to.getFindBy();
        }
        return  byObj;
    }
}
