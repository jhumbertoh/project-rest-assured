package com.company.projectrestassured.features;

import com.company.projectrestassured.config.RedmineConfig;
import com.company.projectrestassured.config.RedmineEndpoints;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RedmineTests extends RedmineConfig {

    @Test
    public void createNewIssueByJson(){

        String issueBody = "{\n" +
                "  \"issue\": {\n" +
                "\t  \"project_id\": 3,\n" +
                "\t  \"subject\": \"Mi segundo issue creado desde RestAssured\",\n" +
                "\t  \"description\": \"As an admin user, I cannot create an user when...\",\n" +
                "\t  \"status_id\": 1,\n" +
                "\t  \"priority_id\": 1\n" +
                "\t}\n" +
                "}";

        given()
                .body(issueBody).
        when()
                .post(RedmineEndpoints.ALL_REDMINE_ISSUES_JSON).
        then()
                .statusCode(201);
    }

    @Test
    public void createNewIssueByXML(){
        String issueBodyXml = "<issue>\n" +
                "    <subject>Mi issue creado desde RestAssured con XML.</subject>\n" +
                "    <description>As an admin user, I cannot create an user when xml...</description>\n" +
                "    <project_id>3</project_id>\n" +
                "    <status_id>1</status_id>\n" +
                "    <priority_id>1</priority_id>\n" +
                "</issue>";

        given()
                .body(issueBodyXml)
                .header("Content-Type", "application/xml")
                .header("Accept", "application/xml").
        when()
                .post(RedmineEndpoints.ALL_REDMINE_ISSUES_XML).
        then()
                .statusCode(201);
    }


    @Test
    public void updateIssue(){

        String issueBodyJson = "{\n" +
                "    \"issue\": {\n" +
                "        \"subject\": \"Mi issue actualizado desde RestAssured.\",\n" +
                "        \"status_id\": 3\n" +
                "    }\n" +
                "}";

        given()
                .body(issueBodyJson).
        when()
                .put("issues/13.json").
        then()
                .statusCode(204);
    }

    @Test
    public void deleteIssue(){

        given().
        when()
                .delete("issues/11.json").
        then()
                .statusCode(204);
    }

    @Test
    public void getSingleIssueJSON(){
        given()
                .pathParam("id", 13).
        when()
                .get(RedmineEndpoints.SINGLE_REDMINE_ISSUE_JSON).
        then()
                .statusCode(200);
    }

    @Test
    public void getSingleIssueXML(){
        given()
                .pathParam("id", 13).
        when()
                .get(RedmineEndpoints.SINGLE_REDMINE_ISSUE_XML).
        then()
                .statusCode(200);
    }
}