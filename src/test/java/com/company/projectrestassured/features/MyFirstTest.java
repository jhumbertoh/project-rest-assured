package com.company.projectrestassured.features;

import com.company.projectrestassured.config.RedmineConfig;
import com.company.projectrestassured.config.RedmineEndpoints;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class MyFirstTest extends RedmineConfig
{

    @Test
    public void myFirstTestWithEndpoint(){
       get(RedmineEndpoints.ALL_REDMINE_ISSUES_JSON)
       .then().log().all();
    }

}