package com.shiftedtech.qa.scripts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Created by ShiftTeacher on 11/26/2017.
 */
public class BootstrapTableTest {
    private WebDriver driver;


    @Before
    public void setUp(){
        //System.setProperty("webdriver.gecko.driver","C:/MyDevelopment/SSMB/SeleniumReference/src/main/resources/drivers/geckodriver.exe");
        System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();

        // System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
        //driver = new ChromeDriver();

        //driver.manage().window().maximize();
        driver.get("https://editor.datatables.net/examples/styling/bootstrap.html");
    }

    @Test
    public void test1(){
        WebElement table = driver.findElement(By.xpath(".//*[@id='example']"));

        List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));
        WebElement row = rows.get(1);
        List<WebElement> cols = row.findElements(By.tagName("td"));
        WebElement cell = cols.get(0);
        cell.click();

        WebElement editButton = driver.findElement(By.xpath(".//*[@id='example_wrapper']/div[1]/div[1]/div/a[2]"));

        editButton.click();

        WebElement salaryTb = driver.findElement(By.xpath(".//*[@id='DTE_Field_salary']"));
        salaryTb.clear();
        salaryTb.sendKeys("3000");

        WebElement updateButton = driver.findElement(By.cssSelector(".btn.btn-default.btn-primary"));
        updateButton.click();

        WebElement salaryCell = cols.get(5);
        String text = salaryCell.getText();
        System.out.println("Salary: " + text);
        Assert.assertEquals("$2,000",text);

    }

    @Test
    public void test2(){
        driver.navigate().to("https://editor.datatables.net/examples/inline-editing/simple.html");
        WebElement table = driver.findElement(By.xpath(".//*[@id='example']"));

        List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));
        WebElement row = rows.get(1);

        List<WebElement> cols = row.findElements(By.tagName("td"));
        WebElement cell = cols.get(0);
        cell.click();


    }
    @Test
    public void test3(){
        driver.navigate().to("https://editor.datatables.net/examples/inline-editing/simple.html");
        WebElement table = driver.findElement(By.xpath(".//*[@id='example']"));

        List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));
        WebElement row = rows.get(1);

        List<WebElement> cols = row.findElements(By.tagName("td"));
        WebElement cell = cols.get(6);
        cell.click();
        cell.click();

        WebElement textBox  = cell.findElement(By.tagName("input"));
        textBox.clear();
        textBox.sendKeys("2000");
        textBox.sendKeys(Keys.ENTER);
    }
    @Test
    public void test3_1(){
        driver.navigate().to("https://editor.datatables.net/examples/inline-editing/simple.html");
        WebElement table = driver.findElement(By.xpath(".//*[@id='example']"));

        List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));
        WebElement row = rows.get(1);

        List<WebElement> cols = row.findElements(By.tagName("td"));
        WebElement cell = cols.get(6);
        Actions actions = new Actions(driver);
        actions.doubleClick(cell).build().perform();

        WebElement textBox  = cell.findElement(By.tagName("input"));
        textBox.clear();
        textBox.sendKeys("2000");
        textBox.sendKeys(Keys.ENTER);
    }

    @Test
    public void test4(){
        driver.navigate().to("https://editor.datatables.net/examples/simple/inTableControls.html");
        WebElement table = driver.findElement(By.xpath(".//*[@id='example']"));

        List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));
        WebElement row = rows.get(1);

        List<WebElement> cols = row.findElements(By.tagName("td"));
        WebElement cell = cols.get(6);
        WebElement editLink  = cell.findElement(By.xpath("./a[text()='Edit']"));
        editLink.click();
    }

    @Test
    public void test45(){
        driver.navigate().to("https://editor.datatables.net/examples/simple/inTableControls.html");
        WebElement table = driver.findElement(By.xpath(".//*[@id='example']"));

        List<WebElement> rows = table.findElements(By.xpath("./thead/tr"));
        WebElement row = rows.get(0);

        List<WebElement> cols = row.findElements(By.tagName("th"));
        WebElement cell = cols.get(1);
        String text = cell.getText();
        System.out.println(text);
    }


}
