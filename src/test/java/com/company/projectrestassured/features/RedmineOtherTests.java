package com.company.projectrestassured.features;

import com.company.projectrestassured.config.RedmineConfig;
import com.company.projectrestassured.config.RedmineEndpoints;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RedmineOtherTests extends RedmineConfig {

    @Test
    public void getIssueStartDate(){

        given()
                .pathParam("id", 13).
        when()
                .get(RedmineEndpoints.SINGLE_REDMINE_ISSUE_JSON).
        then()
                .statusCode(200)
                .body("issue.start_date", equalTo("2020-04-25"));
    }

    @Test
    public void getFirstIssueSubject(){

        given().
        when()
                .get(RedmineEndpoints.ALL_REDMINE_ISSUES_JSON).
        then()
                .statusCode(200)
                .body("issues[0].subject", equalTo("Mi issue actualizado desde RestAssured."));
    }

    @Test
    public void getAllIssueData(){

        String responseBody = get("issues/13.json").asString();
        System.out.println(responseBody);
    }

    @Test
    public void getAllIssueDataCheck(){

        Response response =
                            given().
                            when()
                                    .get("issues/13.json").
                            then()
                                    .statusCode(200)
                                    .extract().response();

        String jsonResponseAsString = response.asString();
        System.out.println(jsonResponseAsString);
    }

    @Test
    public void extractHeaders(){
        Response response =
                given().
                        when()
                        .get("issues/13.json").
                        then()
                        .statusCode(200)
                        .extract().response();

        Headers headers = response.getHeaders();

        String contentType = response.getHeader("Content-Type");

        System.out.println(contentType);
    }

    @Test
    public void extractFirstIssueSubject(){

        String firstIssueSubject = get(RedmineEndpoints.ALL_REDMINE_ISSUES_JSON).jsonPath().getString("issues[0].subject");
        System.out.println(firstIssueSubject);
    }


    @Test
    public void extractAllIssueSubjects(){

        Response response =
                            given().
                            when()
                                    .get(RedmineEndpoints.ALL_REDMINE_ISSUES_JSON).
                            then()
                                    .extract().response();

        List<String> subjects = response.path("issues.subject");

        for(String subject : subjects){
            System.out.println(subject);
        }
    }
}