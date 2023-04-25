package com.apiTestTrello;

import com.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.Random;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {


    @Before
    public void background(){
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
                        .queryParam("name", "BoardAnil2")
                        .post("boards")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON).log().all()
                        .assertThat()
                        .extract().path("id");

        System.out.println("boardID = " + boardID);

        String listId =
                given()
                        .contentType("application/json")
                        .when()
                        .queryParam("key", ConfigReader.get("key"))
                        .queryParam("token", ConfigReader.get("token"))
                        .queryParam("name","TrelloList")
                        .post("/boards/"+boardID+"/lists")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON).log().all()
                        .assertThat()
                        .body("name", equalTo("TrelloList"))
                        .body("idBoard", equalTo(boardID))
                        .extract().path("id");

        String[] cardsIdArr = new String[2];


        for (int i = 0; i < 2; i++) {
            cardsIdArr[i] = given()
                    .contentType("application/json").
                    when()
                    .queryParam("key", ConfigReader.get("key"))
                    .queryParam("token", ConfigReader.get("token"))
                    .queryParam("name","newCard"+i)
                    .queryParam("idList",listId)
                    .post("/cards").
                    then()
                    .statusCode(200)
                    .contentType(ContentType.JSON).log().all().
                    assertThat()
                    .body("name", equalTo("newCard"+i))
                    .extract().path("id");
        }

        System.out.println(Arrays.toString(cardsIdArr));

        //Update one of the cards
        Random rn = new Random();
        int a = rn.nextInt(2);
        given()
                .contentType("application/json").
                when()
                .queryParam("key", ConfigReader.get("key"))
                .queryParam("token", ConfigReader.get("token"))
                .queryParam("name","newCardRandom")
                .queryParam("desc","edited Test Description")
                .put("/cards/"+cardsIdArr[a]).
                then()
                .statusCode(200)
                .contentType(ContentType.JSON).log().all().
                assertThat()
                .body("desc", equalTo("edited Test Description"))
                .extract().path("id");

        //Delete all of the cards
        for (int i = 0; i < 2; i++) {
            given()
                    .contentType("application/json").
                    when()
                    .queryParam("key", ConfigReader.get("key"))
                    .queryParam("token", ConfigReader.get("token"))
                    .delete("/cards/"+cardsIdArr[i]).
                    then()
                    .statusCode(200).log().all();
        }

        //Delete board
        given()
                .contentType("application/json").
                when()
                .queryParam("key", ConfigReader.get("key"))
                .queryParam("token", ConfigReader.get("token"))
                .pathParam("id",boardID)
                .delete("/boards/{id}").
                then()
                .statusCode(200);


    }


}
