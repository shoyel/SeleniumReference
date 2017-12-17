package com.shiftedtech.qa.spree.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by ShiftTeacher on 12/17/2017.
 */
public class LoginPage extends  PageBase{

    @FindBy(how = How.ID, using="spree_user_email")
    private WebElement emailTextBox;
    @FindBy(id="spree_user_password")
    private WebElement passwordTextbox;
    @FindBy(xpath = "//input[@name='commit']")
    private WebElement loginButton;
    @FindBy(xpath = "//div[@id='content']/div[contains(@class,'alert-error')]")
    private WebElement alertMsg;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    private WebElement getAlertMsg(){
        WebElement alertMsg;
        alertMsg = waitForElementDisplayed(By.xpath("//div[@id='content']/div[contains(@class,'alert-error')]"),2);
        return  alertMsg;
    }


    public void login(String email, String password){
        typeText(emailTextBox,email);
        typeText(passwordTextbox,password);
        click(loginButton);
    }
    public void verifyLoginNotSuccess(){
       //String successText = alertMsg.getText();
        String successText = getAlertMsg().getText();
        Assert.assertEquals("Invalid email or password.",successText);
    }
}
