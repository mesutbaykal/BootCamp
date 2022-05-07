package com.cydeo.step_definitions;

import com.cydeo.pages.pojo.Spartan;
import com.cydeo.utilities.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

public class AllEnvironmentSteps {

    String nameFromAPI;
    @When("User sends a request to spartan API using id {int}")
    public void userSendsARequestToSpartanAPIUsingId(int id) {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",id)
                .get(ConfigurationReader.get("spartan.apiUrl")+"/api/spartans/{id}");

        Map<String,Object> oneSpartan = response.body().as(Map.class);
        nameFromAPI = (String) oneSpartan.get("name");
        System.out.println("nameFromAPI = " + nameFromAPI);

        Spartan oneSpartanFromPOJO = response.body().as(Spartan.class);
        System.out.println("oneSpartanFromPOJO.getName() = " + oneSpartanFromPOJO.getName());
    }

    @Then("Info of Spartan should be same at all environments")
    public void infoOfSpartanShouldBeSameAtAllEnvironments() {
        // verify API and UI
        Assert.assertEquals(nameFromAPI,SpartansUISteps.UIname);

        // verify API against DB
        Assert.assertEquals(nameFromAPI,SpartanDBSteps.DBname);

        // DB against UI now?  NO NEED TO DO THIS, it is redundant


    }
}
