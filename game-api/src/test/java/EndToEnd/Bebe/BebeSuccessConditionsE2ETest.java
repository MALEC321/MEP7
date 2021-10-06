package EndToEnd.Bebe;

import ca.ulaval.glo4002.game.GameServer;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BebeSuccessConditionsE2ETest{
    private static Thread thread;
    RequestSpecification httpRequest;
    Response response;
    JsonPath jsonPathEvaluator;
    JSONObject request;

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

        System.out.println("1. Le père doit être un dinosaure mâle et la mère un dinosaure femelle.");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/reset")
                .then()
                .statusCode(200);

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
        request.put("species", "Triceratops");
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
                .statusCode(200);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/turn")
                .then()
                .statusCode(200);
    }

    @Test
    public void givenCreatedBaby_whenGetDinosaurs_thenParentsAreMaleAndFemale(){
        //redondant
    }

    @Test
    public void givenCreatedBaby_whenPostTurn_thenWeightIs1Kg() {
        httpRequest = RestAssured.given();
        response = httpRequest.get("/dinosaurs/Bebe test");
        jsonPathEvaluator = response.jsonPath();
        int weight = jsonPathEvaluator.get("weight");
        Assert.assertEquals(weight, 1);

    }

    @Test
    public void givenCreatedBaby_whenTwicePostTurn_thenResourcesConsumedIsTwiceNormal() {
        //Comment faire?
    }

    @Test
    public void givenCreatedBaby_whenPostTurn_thenNameIsUnique() {
        httpRequest = RestAssured.given();
        response = httpRequest.get("/dinosaurs");
        jsonPathEvaluator = response.jsonPath();

        List<String> listDinoNames = jsonPathEvaluator.getList("name");
        Boolean nomIdentique = false;
        Set<String> setDinoNames = new HashSet<>();
        for (String name : listDinoNames)
        {
            if (!setDinoNames.add(name))
                nomIdentique = true;

        }
        Assert.assertEquals(false, nomIdentique);
    }

    @Test
    public void givenCreatedBaby_whenNoPostTurnAndGetDinosaurs_thenBabyIsNotPresent() {
        request = new JSONObject();
        request.put("name", "Bebe test 2");
        request.put("fatherName", "Father test");
        request.put("motherName", "Mother test");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/breed")
                .then()
                .statusCode(200);

        httpRequest = RestAssured.given();
        response = httpRequest.get("/dinosaurs");
        jsonPathEvaluator = response.jsonPath();

        List<String> listDinoNames = jsonPathEvaluator.getList("name");
        Assert.assertFalse(listDinoNames.contains("Bebe Test 2"));
    }

    @Test
    public void givenCreatedBaby_whenPostTurnAndGetDinosaurs_thenBabyIsPresent() {
        httpRequest = RestAssured.given();
        response = httpRequest.get("/dinosaurs");
        jsonPathEvaluator = response.jsonPath();

        List<String> listDinoNames = jsonPathEvaluator.getList("name");
        Assert.assertTrue(listDinoNames.contains("Bebe Test"));
    }

    @Test
    public void givenInvalidBaby_whenPostTurnAndGetDinosaurs_thenBabyIsNotPresent() {
        request = new JSONObject();
        request.put("name", "Bebe test 2");
        request.put("fatherName", "Non-existent father");
        request.put("motherName", "Non-existent Mother");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/breed")
                .then()
                .statusCode(200);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/turn")
                .then()
                .statusCode(200);

        httpRequest = RestAssured.given();
        response = httpRequest.get("/dinosaurs");
        jsonPathEvaluator = response.jsonPath();

        List<String> listDinoNames = jsonPathEvaluator.getList("name");
        Assert.assertFalse(listDinoNames.contains("Bebe Test 2"));
    }

    @Test
    public void givenBothParentsDead_whenGetDinosaurs_thenBabyIsDead() {
        //Difficile à faire
    }
}



