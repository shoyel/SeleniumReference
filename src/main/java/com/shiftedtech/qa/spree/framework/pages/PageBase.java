package com.shiftedtech.qa.spree.framework.pages;

import com.shiftedtech.qa.spree.utils.WebElementUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

/**
 * Created by ShiftTeacher on 12/17/2017.
 */
public class PageBase extends WebElementUtils{

    public PageBase(WebDriver driver){
       super(driver);
    }

    public void verifyPageTitle(String expectedTitle){
        String title = driver.getTitle();
        Assert.assertEquals(expectedTitle,title);
    }

}
