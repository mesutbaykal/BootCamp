package com.cydeo.step_definitions;

import io.cucumber.java.en.Then;
import org.junit.Assert;

public class MultiEnvironmentTests {

   // How can I carry these variables into this class?

    @Then("Spartan names from UI and DB should match")
    public void spartanNamesFromUIAndDBShouldMatch() {

        System.out.println("SpartansUISteps.UIname = " + SpartansUISteps.UIname);
        System.out.println("SpartanDBSteps.DBname = " + SpartanDBSteps.DBname);

        Assert.assertEquals(SpartanDBSteps.DBname,SpartansUISteps.UIname);

    }
}
