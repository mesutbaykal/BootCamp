package com.cydeo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(name = "username")
    public WebElement uNBox;
    @FindBy(name = "password")
    public WebElement pwBox;
    @FindBy(xpath = "//button[.='Login']")
    public WebElement button;
}
