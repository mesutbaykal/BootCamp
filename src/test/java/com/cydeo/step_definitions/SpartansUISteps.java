package com.cydeo.step_definitions;

import com.cydeo.pages.SpartanMainPage;
import com.cydeo.pages.SpartanViewPage;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SpartansUISteps {

    public static String UIname;

    @Given("Commander is at the main page")
    public void commander_is_at_the_main_page() {
        Driver.get().get(ConfigurationReader.get("spartan.webUrl"));
    }
    @When("Commander clicks on view button of the spartan with the ID number {string}")
    public void commander_clicks_on_view_button_of_the_spartan_with_the_id_number(String id) {
        new SpartanMainPage().getSpartanView(id).click();  // clicks on view button selected Spartan ID
        UIname = new SpartanViewPage().name.getAttribute("value");
    }
    @Then("Verifies the name of the spartan is {string}")
    public void verifies_the_name_of_the_spartan_is(String expectedName) {
        String actualName = UIname;
       // not Working: System.out.println(new SpartanViewPage().name.getText());
        Assert.assertEquals("Name is NOT same",expectedName,actualName);

    }

}
