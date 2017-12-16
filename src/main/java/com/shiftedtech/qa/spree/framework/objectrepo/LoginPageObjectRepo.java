package com.shiftedtech.qa.spree.framework.objectrepo;

import org.openqa.selenium.By;

/**
 * Created by ShiftTeacher on 12/16/2017.
 */
public class LoginPageObjectRepo {
    public static final By EMAIL_TEXTBOX = By.id("spree_user_email");
    public static final By PASSWORD_TEXTBOX = By.id("spree_user_password");
    public static final By LOGIN_BUTTON =  By.xpath("//input[@name='commit']");
}
