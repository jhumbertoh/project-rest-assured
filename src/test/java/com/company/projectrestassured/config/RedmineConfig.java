package com.company.projectrestassured.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.junit.BeforeClass;

public class RedmineConfig {

    public static RequestSpecification requestSpecification;

    public static ResponseSpecification responseSpecification;


    @BeforeClass
    public static void setup(){

        requestSpecification = new RequestSpecBuilder()
                        .setBaseUri("http://45.55.55.186")
                        .setBasePath("/")
                        .setPort(8081)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json")
                        .addFilter(new RequestLoggingFilter())
                        .addFilter(new ResponseLoggingFilter())
                        .addHeader("X-Redmine-API-Key","c2481248780185b6944b74fb8a2ca8835cd35ab7")
                        .build();

        responseSpecification = new ResponseSpecBuilder()
                        //.expectStatusCode(200)
                        .build();


        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }
}