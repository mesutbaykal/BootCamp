package com.cydeo.eu7UIBootcamp;

import com.cydeo.pages.LoginPage;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import com.cydeo.utilities.WebDriverFactory;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestsUsingSingletonDriver {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage = new LoginPage();
    @BeforeMethod
    public void setUp(){
        driver = Driver.get();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.get("webTable.url"));
        wait = new WebDriverWait(driver,10);  // explicit wait object
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
    @Test
    public void Test2(){
        // Test Scenario: Check the order of WebElement boxes Username->Password->Button

        Point locationUserNameBox = loginPage.uNBox.getLocation();
        int y_CoordinateOfUserNameBox = locationUserNameBox.getY();
        System.out.println("y_CoordinateOfUserNameBox = " + y_CoordinateOfUserNameBox);
        int y_CoordinateOfPasswordBox = loginPage.pwBox.getLocation().getY();
        System.out.println("y_CoordinateOfPasswordBox = " + y_CoordinateOfPasswordBox);
        int y_CoordinateOfButton = loginPage.button.getLocation().getY();
        System.out.println("y_CoordinateOfButton = " + y_CoordinateOfButton);
        Assert.assertTrue(y_CoordinateOfUserNameBox<y_CoordinateOfPasswordBox);
        Assert.assertTrue(y_CoordinateOfPasswordBox<y_CoordinateOfButton);



    }
}
