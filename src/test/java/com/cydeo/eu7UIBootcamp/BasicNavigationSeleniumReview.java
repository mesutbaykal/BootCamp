package com.cydeo.eu7UIBootcamp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicNavigationSeleniumReview {
    public static void main(String[] args) throws InterruptedException {
//1. setup browser driver
        WebDriverManager.chromedriver().setup();
//2. create your driver object: opens an empty browser
        WebDriver driver = new ChromeDriver();
        // maximize the window
        driver.manage().window().maximize();

//3. navigate to the webPage we want
        driver.get("https://web-table-2.cydeo.com/login");



    // wait for 2 seconds- Static waiting
        Thread.sleep(3000);
    // get the title of the page
        String expectedTitle = "Cydeo Web Table App";
        String actualTitle = driver.getTitle();

        if(expectedTitle.equals(actualTitle))
            System.out.println("Title test passed");
    }
}
