package com.company.projectrestassured.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class RedmineConfig {

    private static RequestSpecification requestSpecification;

    private static ResponseSpecification responseSpecification;

    @BeforeAll
    public static void setup(){

        requestSpecification = new RequestSpecBuilder()
                        .setBaseUri("http://localhost")
                        .setBasePath("/")
                        .setPort(8081)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json")
                        .addHeader("X-Redmine-API-Key","c2481248780185b6944b74fb8a2ca8835cd35ab7")
                        .addFilter(new RequestLoggingFilter())
                        .addFilter(new ResponseLoggingFilter())
                        .build();

        responseSpecification = new ResponseSpecBuilder()
                        .build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }

    @AfterAll
    public static void cleanUp(){
        RestAssured.reset();
    }
}