package com.company.projectrestassured.features;

import com.company.projectrestassured.config.RedmineConfig;
import com.company.projectrestassured.config.RedmineEndpoints;
import com.company.projectrestassured.dataentities.Entity;
import com.company.projectrestassured.dataentities.Project;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RedmineProjectsTests extends RedmineConfig {

    @Test
    public void testProjectValidateSchemaJSON() {

        given()
                .pathParam("id", 45).
                when()
                .get(RedmineEndpoints.SINGLE_REDMINE_PROJECT_JSON).
                then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("project_schema.json"));
    }

    @Test
    public void testProjectValidateSchemaXML() {

        given()
                .pathParam("id", 45)
                .header("Content-Type", "application/xml")
                .header("Accept", "application/xml").
                when()
                .get(RedmineEndpoints.SINGLE_REDMINE_PROJECT_XML).
                then()
                .statusCode(200)
                .body(matchesXsdInClasspath("project_schema.xsd"));
    }

    @Test
    public void testProjectSerialization() {

        Project project = new Project("RedmineProject163219", "redmineproject169419", "Redmine Project for api testing", false, true);

        Entity entity = new Entity(project);

        given()
                .body(entity).
        when()
                .post(RedmineEndpoints.ALL_REDMINE_PROJECTS_JSON).
        then()
                .statusCode(201);
    }

    @Test
    public void testProjectDeSerialization(){

        Response response =
                            given()
                                    .pathParam("id", 45).
                            when()
                                    .get(RedmineEndpoints.SINGLE_REDMINE_PROJECT_JSON);

        response.then().statusCode(200);

        Entity entity = response.getBody().as(Entity.class);
        Project project = entity.getProject();

        System.out.println(project.toString());
    }
}