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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class BebeSuccessConditionsE2ETest{
    private static Thread thread;
    RequestSpecification httpRequest;
    Response response;
    JsonPath jsonPathEvaluator;

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
    public void givenCreatedBaby_whenPostTurn_thenSpeciesIsSameAsParents(){

        RestAssured.given()
                .get("/dinosaurs/Bebe test").then().
                statusCode(200).
                body("name", equalTo("Bebe test")).
                body("weight", equalTo(1)).
                body("species", equalTo("Triceratops")).
                log().all();

        RestAssured.given()
                .get("/dinosaurs").then().
                statusCode(400).
                body("name", hasItems("Bebe test", "Bebe test")).
                log().all();
    }

    @Test
    public void givenCreatedBaby_whenPostTurn_thenParentsAreMaleAndFemale(){
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
    public void givenCreatedBaby_whenGetDinosaurs_thenBabyIsNotPresent() {

    }

    @Test
    public void givenCreatedBaby_whenPostTurnAndGetDinosaurs_thenBabyIsPresent() {

    }

    @Test
    public void givenInvalidBaby_whenPostTurnAndGetDinosaurs_thenBabyIsNotPresent() {

    }

    @Test
    public void givenValidBabys_whenGetDinosaurs_thenAtLeastOneParentLives() {

    }
}



