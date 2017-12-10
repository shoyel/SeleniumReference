package com.shiftedtech.qa.spree.utils;

import com.shiftedtech.qa.spree.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by ShiftTeacher on 12/10/2017.
 */
public class WebElementUtils extends SeleniumUtils {
    public WebElementUtils(WebDriver driver) {
        super(driver);
    }

    public void typeText(By by, String text){

        WebElement element = waitForElementDisplayed(by,SeleniumUtils.DEFAULT_WAIT_TIME);
        highlight(element);
        element.clear();
        element.sendKeys(text);
    }

    public void click(By by){
        WebElement element = waitForElementDisplayed(by,SeleniumUtils.DEFAULT_WAIT_TIME);
        highlight(element);
        element.click();
    }
}
