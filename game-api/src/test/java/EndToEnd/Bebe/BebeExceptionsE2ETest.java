package EndToEnd.Bebe;

import ca.ulaval.glo4002.game.GameServer;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BebeExceptionsE2ETest {
    private static Thread thread;

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
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.port = 8181;
        RestAssured.basePath = "/";
        RestAssured.baseURI = "http://localhost";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void givenParentsOfDifferentSpecies_whenCreatingBabyDino_thenReturnErrorCode400() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/reset")
                .then()
                .statusCode(200);

        JSONObject request;

        request = new JSONObject();
        request.put("name", "Mother test");
        request.put("weight", 5);
        request.put("gender", "f");
        request.put("species", "Triceratops");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/dinosaurs")
                .then()
                .statusCode(200);

        request = new JSONObject();
        request.put("name", "Father test");
        request.put("weight", 5);
        request.put("gender", "m");
        request.put("species", "Megalosaurus");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/dinosaurs")
                .then()
                .statusCode(200);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/turn")
                .then()
                .statusCode(200);

        request = new JSONObject();
        request.put("name", "Bebe test");
        request.put("fatherName", "Father test");
        request.put("motherName", "Mother test");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/breed")
                .then()
                .statusCode(400);
    }


}
