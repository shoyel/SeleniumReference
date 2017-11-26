package com.shiftedtech.qa.scripts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ShiftTeacher on 11/26/2017.
 */
public class TableTest {
    private WebDriver driver;


    @Before
    public void setUp(){
        //System.setProperty("webdriver.gecko.driver","C:/MyDevelopment/SSMB/SeleniumReference/src/main/resources/drivers/geckodriver.exe");
        System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();

        // System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
        //driver = new ChromeDriver();

        //driver.manage().window().maximize();
        driver.get("http://shifttest.shiftedtech.com/components/table");
    }
    @Test
    public void test1(){
        for(int i = 1 ; i <= 3; i++){
            for(int j = 1; j <= 3; j++){
                WebElement cellElement = driver.findElement(By.xpath(".//*[@id='html-table']/table/tbody/tr[" + i + "]/td[" + j + "]"));
                String text = cellElement.getText();
                System.out.println("Cell text: " + text);
            }
        }
    }

    @Test
    public void test2(){

        String text = getCellText(2,3);
        System.out.println("Cell text: " + text);


    }

    @Test
    public void test3(){

        getCellText();

    }

    @Test
    public void test4(){

        String tableText[][] = getCellTextArray();
        System.out.println(Arrays.deepToString(tableText));

        System.out.println("Cell 2,2: " + tableText[2][2]);

    }

    @Test
    public void test5_1(){
       int row = 3;
       int col = 3;

       WebElement cellElement = driver.findElement(By.xpath(".//*[@id='html-table']/table/tbody/tr[" + row + "]/td[" + col + "]"));
       String text = cellElement.getText();
       System.out.println("Cell text 2,2: " + text);
    }

    @Test
    public void test5_2(){
        int row = 6;
        int col = 3;

        WebElement table = driver.findElement(By.xpath(".//*[@id='html-table']/table"));

        WebElement cellElement = table.findElement(By.xpath("./tbody/tr[" + row + "]/td[" + col + "]"));
        String text = cellElement.getText();
        System.out.println("Cell text 2,2: " + text);
    }

    @Test
    public void test5_2_1(){
        int row = 6;
        int col = 3;

        WebElement table = driver.findElement(By.xpath(".//*[@id='html-table']/table"));

        try {
            WebElement cellElement = table.findElement(By.xpath("./tbody/tr[" + row + "]/td[" + col + "]"));
            String text = cellElement.getText();
            System.out.println("Cell text 2,2: " + text);
        }
        catch (Exception ex){
            System.out.println("Unable to get the cell text " + ex.getMessage());
        }
    }

    @Test
    public void test5_3(){
        String text = null;
        int rowIndex = 6;
        int colIndex = 2;

        WebElement table = driver.findElement(By.xpath(".//*[@id='html-table']/table"));
        List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));
        if(rowIndex <= rows.size()) {
            WebElement row = rows.get(rowIndex);
            List<WebElement> cols = row.findElements(By.tagName("td"));
            if (colIndex <= cols.size()) {
                WebElement cell = cols.get(colIndex);
                text = cell.getText();
            }
            else{
                System.out.println("Invalid column number " + colIndex);
            }
        }
        else
        {
            System.out.println("Invalid row number " + rowIndex);
        }
        System.out.println("Cell text [" + rowIndex + "," + colIndex + "]: " + text);
    }
    @Test
    public void test5_4(){

        WebElement table = driver.findElement(By.xpath(".//*[@id='striped-table']/table"));
        String tableText[][] = getCellTextArray(table);
        System.out.println(Arrays.deepToString(tableText));

        System.out.println("Cell 2,2: " + tableText[2][2]);

    }

    @Test
    public void verifyCellData(){

        String expectedCellData = "Mary";

        WebElement table = driver.findElement(By.xpath(".//*[@id='striped-table']/table"));
        String tableText[][] = getCellTextArray(table);
        String actualCellData = tableText[1][0];
        Assert.assertEquals(expectedCellData,actualCellData);

    }
    @Test
    public void verifyRowData(){

        String[] expectedRowData = {"Mary2","Moe","mary@example.com"};


        WebElement table = driver.findElement(By.xpath(".//*[@id='striped-table']/table"));
        String tableText[][] = getCellTextArray(table);
        String[] actualRowData = tableText[1];
        Assert.assertArrayEquals(expectedRowData,actualRowData);

    }
    @Test
    public void verifyTableData(){

        String[][] expectedTableData = {
                                        {"John","Doe","john@example.com"},
                                        {"Mary","Moe","mary@example.com2"},
                                        {"July","Dooley","july@example.com"}
                                       };


        WebElement table = driver.findElement(By.xpath(".//*[@id='striped-table']/table"));
        String tableText[][] = getCellTextArray(table);

        Assert.assertArrayEquals(expectedTableData,tableText);

    }


    public String getCellText(int row, int col){
        WebElement cellElement = driver.findElement(By.xpath(".//*[@id='html-table']/table/tbody/tr[" + row + "]/td[" + col + "]"));
        String text = cellElement.getText();
        return text;
    }
    public void getCellText(){
        List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='html-table']/table/tbody/tr"));

        for(int i = 0 ; i < rows.size(); i++){
            WebElement row = rows.get(i);
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for(int j = 0; j < cols.size(); j++){
                WebElement col = cols.get(j);
                String text = col.getText();
                System.out.println("Cell text [" + i + "," + j + "]: " + text);
            }
        }
    }

    public String[][] getCellTextArray(){
        String[][] tableText = null;
        List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='html-table']/table/tbody/tr"));
        tableText = new String[rows.size()][];

        for(int i = 0 ; i < rows.size(); i++){
            WebElement row = rows.get(i);
            List<WebElement> cols = row.findElements(By.tagName("td"));
            tableText[i] = new String[cols.size()];
            for(int j = 0; j < cols.size(); j++){
                WebElement col = cols.get(j);
                String text = col.getText();
                tableText[i][j]=text;
                System.out.println("Cell text [" + i + "," + j + "]: " + text);
            }
        }

        return  tableText;
    }

    public String[][] getCellTextArray(WebElement table){
        String[][] tableText = null;
        List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));
        tableText = new String[rows.size()][];
        for(int i = 0 ; i < rows.size(); i++){
            WebElement row = rows.get(i);
            List<WebElement> cols = row.findElements(By.tagName("td"));
            tableText[i] = new String[cols.size()];
            for(int j = 0; j < cols.size(); j++){
                WebElement col = cols.get(j);
                String text = col.getText();
                tableText[i][j]=text;
                System.out.println("Cell text [" + i + "," + j + "]: " + text);
            }
        }

        return  tableText;
    }

    @After
    public void tearDown(){

        //driver.close();
        try {
            //driver.quit();
        }
        catch (Exception ex){
        }
    }
}
