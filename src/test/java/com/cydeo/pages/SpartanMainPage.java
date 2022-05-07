package com.cydeo.pages;

import com.cydeo.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpartanMainPage extends SpartanBasePage{

// create a useful method when I pass ID number, it should return me view button of that spartan

    public WebElement getSpartanView(String id){
        return Driver.get().findElement(By.xpath("//tbody//tr//td[.='"+id+"']/../td[5]"));
    }


}
