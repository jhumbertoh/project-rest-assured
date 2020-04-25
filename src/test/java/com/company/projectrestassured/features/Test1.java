package com.company.projectrestassured.features;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;


import static io.restassured.RestAssured.given;

public class Test1 {

    private static RequestSpecification request;
    private static Response response;


    @Test
    public void requestGetProjectsRedmine_checkResponseCode_expect200_and_total_count10() {

        //Codigo Http Respuesta
        //Codigo del servicio satisfactorio
        //Validar el Esquema del servicio
        //El contenido de la respuesta del servicio

        request = given()
                .contentType("application/json");

        response = request.when()
                .get("http://192.168.2.110:8081/projects.json");


        response.then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);

        JsonPath jsonPath = new JsonPath(response.getBody().asString());

        //Assertions.assertEquals(1, jsonPath.getInt("total_count"));

    }


    @Test
    public void requestGetIssuesRedmine_checkResponseCode_expect200() {

        request = given()
                .contentType("application/json");

        response = request
                .when()
                .get("http://192.168.2.110:8081/issues.json");

        response.then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);

        JsonPath jsonPath = new JsonPath(response.getBody().asString());

        //Assertions.assertEquals(2, jsonPath.getInt("total_count"));


    }

    @Test
    public void  requestPostCreateAIssueRedmine_checkResponseCode_expect201(){
        request = given()
                .contentType("application/json");

        response = request
                .when()
                .header("X-Redmine-API-Key", "475338c5e8fe95ef9f9c1cae8cb69610e2d413ac")
                .body("{\n" +
                        "  \"issue\": {\n" +
                        "\t  \"project_id\": 1,\n" +
                        "\t  \"subject\": \"Mi segundo issue creado desde RestAssured\",\n" +
                        "\t  \"description\": \"As an admin user, I cannot create an user when...\",\n" +
                        "\t  \"status_id\": 1,\n" +
                        "\t  \"priority_id\": 1\n" +
                        "\t}\n" +
                        "}")
                .post("http://192.168.2.110:8081/issues.json");

        response.then()
                .log()
                .all()
                .assertThat()
                .statusCode(201);
    }
}