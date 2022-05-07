package com.cydeo.apiBootCamp;

import com.cydeo.utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;

import com.github.fge.jsonschema.main.JsonSchema;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.testng.Assert.*;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SpartanAPItestsWithTestNG {

    String spartanUrl = ConfigurationReader.get("spartan.apiUrl");
    String hrUrl = ConfigurationReader.get("hr.apiUrl");

    @Test
    public void Test1() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("nameContains", "Fe")
                .and().queryParam("gender", "Female")
                .get(spartanUrl + "/api/spartans/search");
        response.prettyPrint();
        assertTrue(response.statusCode() == 200);
        assertEquals(response.contentType(), "application/json");
        assertEquals(response.header("Transfer-Encoding"), "chunked");
        assertTrue(response.headers().hasHeaderWithName("Date"));
        assertEquals(response.body().path("content[0].name"), "Fenelia");
        System.out.println("response.getTime() = " + response.getTime());
    }

    @Test
    public void Test2() {
        // get request to all spartans
        // use jspnPath, Gpath syntax

        Response response = given().accept(ContentType.JSON)
                .get(spartanUrl + "/api/spartans");
        //   response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        List<String> maleSpartansNames = jsonPath.getList("findAll{it.gender=='Male'}.name");

        System.out.println("maleSpartansNames.size() = " + maleSpartansNames.size());

        System.out.println("maleSpartansNames.get(1) = " + maleSpartansNames.get(1));

    }

    @Test
    public void Test3() {
        /*
        Send a GET request to ORDS API all regions end point
        check all when the "region_id": 10 then   "region_name" is equal "Cybertek Germany"
         */

        Response response = given().accept(ContentType.JSON)
                .get(hrUrl + "/regions");

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        String expectedRegionName = "Cybertek Germany";
        String actualRegionName = jsonPath.getString("items.findAll{it.region_id==10}[0].region_name"); // this is looping inside your response


        // when we use GPATH syntax with it operator, it is returning a List
        System.out.println("actualRegionName = " + actualRegionName);
        Assert.assertTrue(actualRegionName.contains(expectedRegionName)); // if we don't put index number we need to verify with contains method
        Assert.assertEquals(actualRegionName, expectedRegionName);

        // get all region ids when we have region name equals to "Cybertek Germany"

        List<Integer> idList = jsonPath.getList("items.findAll{it.region_name=='Cybertek Germany'}.region_id");
        System.out.println(idList); //  [1000, 10]

        String idListString = jsonPath.getString("items.findAll{it.region_name=='Cybertek Germany'}.region_id");
        System.out.println(idListString); // [1000, 10]

    }


    @Test
    public void Test4() {
        // chain methods even without using Hemcrest Matchers, however we need this Matchers library for the header and body part assertions

        given().log().all()
            .accept(ContentType.JSON)
                .and().queryParam("nameContains", "Fe")
                .and().queryParam("gender", "Female")
           .when()
                .get(spartanUrl + "/api/spartans/search")
                .then().assertThat().statusCode(200).contentType("application/json")  // for this part no need for Hemcrest Matchers
                .assertThat().header("Transfer-Encoding",Matchers.equalTo("chunked"))
                .assertThat().body("content[0].name", Matchers.equalTo("Fenelia"))
                .log().all();


    }

    @Test
    public void Test5(){

        given().accept(ContentType.JSON)
                .and()
                .pathParam("id",8)
                .when()
                .get(spartanUrl+"/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));


    }



}
