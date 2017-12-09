package com.shiftedtech.qa.spree;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by ShiftTeacher on 12/9/2017.
 */
public class SpreeFramework {

    protected WebDriver driver;

    public SpreeFramework(WebDriver driver){
        this.driver = driver;
    }

    public void login(String email, String password) {
        WebElement emailTextbox = driver.findElement(By.id("spree_user_email"));
        emailTextbox.sendKeys(email);

        WebElement passwordTextbox = driver.findElement(By.id("spree_user_password"));
        passwordTextbox.sendKeys(password);

        WebElement loginButton = driver.findElement(By.xpath("//input[@name='commit']"));
        loginButton.click();
    }

    public void bringLoginScreen() {
        WebElement loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();
    }

    public void verifyLoginSuccess(){
        WebElement loginSuccessLabel = driver.findElement(By.xpath("//div[@id='content']/div[contains(@class,'alert-success')]"));
        String successText = loginSuccessLabel.getText();
        Assert.assertEquals("Logged in successfully",successText);
    }

    public void verifyLoginNotSuccess(){
        WebElement loginSuccessLabel = driver.findElement(By.xpath("//div[@id='content']/div[contains(@class,'alert-error')]"));
        String successText = loginSuccessLabel.getText();
        Assert.assertEquals("Invalid email or password.",successText);
    }

}
