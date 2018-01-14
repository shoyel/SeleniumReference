package com.shiftedtech.qa.spree.utils;

import com.shiftedtech.qa.spree.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ShiftTeacher on 12/10/2017.
 */
public class WebElementUtils extends SeleniumUtils {
    public WebElementUtils(WebDriver driver) {
        super(driver);
    }

    public By getFindBy(String by, String using) {

        if (by.trim().toUpperCase().contentEquals("ID")) {
            return By.id(using);
        } else if (by.trim().toUpperCase().contentEquals("NAME")) {
            return By.name(using);
        } else if (by.trim().toUpperCase().contentEquals("XPATH")) {
            return By.xpath(using);
        } else if (by.trim().toUpperCase().contentEquals("CSS")) {
            return By.cssSelector(using);
        } else if (by.trim().toUpperCase().contentEquals("CLASS_NAME")) {
            return By.className(using);
        } else if (by.trim().toUpperCase().contentEquals("LINK_TEXT")) {
            return By.linkText(using);
        } else if (by.trim().toUpperCase().contentEquals("TAGNAME")) {
            return By.tagName(using);
        } else if (by.trim().toUpperCase().contentEquals("PARTIAL_LINK_TEXT")) {
            return By.partialLinkText(using);
        } else {
            return null;
        }
    }


    public void typeText(By by, String text){
        WebElement element = waitForElementDisplayed(by,SeleniumUtils.DEFAULT_WAIT_TIME);
        typeText(element,text);
    }
    public void typeText(WebElement element, String text){
        highlight(element);
        element.clear();
        element.sendKeys(text);
    }

    public void click(By by){
        WebElement element = waitForElementDisplayed(by,SeleniumUtils.DEFAULT_WAIT_TIME);
        click(element);
    }
    public void click(WebElement element){
        highlight(element);
        element.click();
    }
    public void verifyText(By by, String textToVerify){
        WebElement element = waitForElementDisplayed(by,10);
        String actualText = element.getText();
        assertThat(actualText,equalTo(textToVerify));
    }
}
