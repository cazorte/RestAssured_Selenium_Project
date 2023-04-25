package com.apiTestTrello;

import com.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ApiTest {


    @BeforeAll
    public static void background(){
        RestAssured.baseURI = ConfigReader.get("baseURI");
        RestAssured.basePath = ConfigReader.get("basePath");
    }

    @Test
    public void BoardCard_CRUD() {

        String boardID =
                RestAssured
                        .given()
                        .contentType("application/json").
                        when()
                        .queryParam("key", ConfigReader.get("key"))
                        .queryParam("token", ConfigReader.get("token"))
                        .queryParam("name", "BoardAnil")
                        .post("boards")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON).log().all()
                        .assertThat()
                        .extract().path("id");

        System.out.println("boardID = " + boardID);


    }


}
