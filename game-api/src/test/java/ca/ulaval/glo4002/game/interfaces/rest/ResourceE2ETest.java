package ca.ulaval.glo4002.game.interfaces.rest;

import ca.ulaval.glo4002.game.GameServer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;




public class ResourceE2ETest {
    private static Thread thread;
    private static Map<String, Integer> validResource = new HashMap<>();

    @BeforeClass
    public static void groundZero() {
        thread =
            new Thread(
            () -> {
            GameServer app = new GameServer();
            app.run();
        });
        thread.start();
    }

    @AfterClass
    public static void cleanUp() {
        try {
        thread.interrupt();
        } catch (SecurityException e) {
        e.printStackTrace();
        }
        }

    @Before
    public void setup() {
        RestAssured.port = 8181;
        RestAssured.basePath = "/";
        RestAssured.baseURI = "http://localhost";
        RestAssured.defaultParser = Parser.JSON;

        validResource.put("qtyBurger", 1);
        validResource.put("qtySalad", 0);
        validResource.put("qtyWater", 0);
    }

    @Test
    public void whenGetHeartBeat_thenReturnStatusCode200(){
        RestAssured.given()
        .contentType(ContentType.JSON)
        .body("{}")
        .when()
        .get("/heartbeat")
        .then()
        .statusCode(200);
    }

    @Test
    public void givenAValidResource_whenPostResource_thenReturnStatusCode200()
    {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(validResource)
                .when()
                .post("/resources")
                .then()
                .statusCode(201);
    }

}