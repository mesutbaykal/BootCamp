package com.cydeo.eu7UIBootcamp;

import com.cydeo.pages.LoginPage;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BasicNavigationWithTestNG {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage = new LoginPage();
    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver(ConfigurationReader.get("browser"));
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
    public void Test1(){


        // there are some other methods that we can use that are not covered during the class
        System.out.println(driver.findElement(By.name("username")).getLocation());// (128, 285) -- x and y coordinates of the webelement in the page
        // size of the webElement
        System.out.println(driver.findElement(By.name("username")).getSize());

        // to send some value to the webPage we used sendKeys
        driver.findElement(By.name("username")).sendKeys(ConfigurationReader.get("web.table.username"));
        // send password
        driver.findElement(By.name("password")).sendKeys(ConfigurationReader.get("web.table.pw"));

        // using text with xpath
        WebElement buttonElement = driver.findElement(By.xpath("//button[.='Login']"));

        // specific condition to a single webelement
        wait.until(ExpectedConditions.elementToBeClickable(buttonElement));
        buttonElement.click();

        String expectedUrl = "https://web-table-2.cydeo.com/orders";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl,expectedUrl,"URL is not as expected");



    }


}
