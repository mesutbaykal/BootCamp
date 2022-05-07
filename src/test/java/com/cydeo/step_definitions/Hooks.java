package com.cydeo.step_definitions;

import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.DBUtils;
import com.cydeo.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;


import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;

public class Hooks {



    @Before
    public void setUp(){
        Driver.get().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @After
    public void tearDown(){
       Driver.closeDriver();
    }

    @Before("@db")
    public void connectDB(){
        DBUtils.createConnection();
    }
    @After("@db")
    public void closeDB(){
        DBUtils.destroy();
    }

}
