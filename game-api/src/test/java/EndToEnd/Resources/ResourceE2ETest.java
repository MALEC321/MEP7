package EndToEnd.Resources;

import ca.ulaval.glo4002.game.GameServer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.*;

import java.util.HashMap;
import java.util.Map;

public class ResourceE2ETest {
    private static Thread thread;
    private static Map<String, Integer> validResource = new HashMap<>();
    private static Map<String, Integer> invalidResource = new HashMap<>();

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
        //RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.port = 8181;
        RestAssured.basePath = "/";
        RestAssured.baseURI = "http://localhost";
        RestAssured.defaultParser = Parser.JSON;

        validResource.put("qtyBurger", 0);
        validResource.put("qtySalad", 0);
        validResource.put("qtyWater", 0);

        invalidResource.put("qtyBurger", -1);
        invalidResource.put("qtySalad", 0);
        invalidResource.put("qtySalad", 0);
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
                .statusCode(200);
    }

    @Test
    public void givenAnInvalidResourceQuantity_whenPostResources_thenReturnStatusCode400()
    {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .log().uri()
                .log().body()
                .log().params()
                .body(invalidResource)
                .when()
                .post("/resources")
                .then()
                .statusCode(400);
    }

    @Test
    public void testEnonceRes()
    {
        System.out.println("DÉBUT TEST testEnonceRes *******************************");
        RequestSpecification httpRequest;
        Response response;
        JsonPath jsonPathEvaluator;
        int qtyBurger;
        Map<String, Integer> oneBurger = new HashMap<>();
        oneBurger.put("qtyBurger", 1);
        oneBurger.put("qtySalad", 0);
        oneBurger.put("qtyWater", 0);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(oneBurger)
                .when()
                .post("/resources")
                .then()
                .statusCode(200);

        httpRequest = RestAssured.given();
        response = httpRequest.get("/resources");
        jsonPathEvaluator = response.jsonPath();
        qtyBurger = jsonPathEvaluator.get("fresh.qtyBurger");
        Assert.assertEquals(qtyBurger, 0);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/turn")
                .then()
                .statusCode(200);

        httpRequest = RestAssured.given();
        response = httpRequest.get("/resources");
        jsonPathEvaluator = response.jsonPath();
        qtyBurger = jsonPathEvaluator.get("fresh.qtyBurger");
        Assert.assertEquals(qtyBurger, 101);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/turn")
                .then()
                .statusCode(200);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/turn")
                .then()
                .statusCode(200);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/turn")
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
        response = httpRequest.get("/resources");
        ResponseBody body = response.getBody().prettyPeek();
        //Copier-coller de la réponse fournit sur la page RES dont j'enlève les espaces et les retours de ligne
        String expectedBody = "{\n" +
            "  \"fresh\": {\n" +
            "    \"qtyBurger\": 400,\n" +
            "    \"qtySalad\": 750,\n" +
            "    \"qtyWater\": 50000\n" +
            "  },\n" +
            "  \"expired\": {\n" +
            "    \"qtyBurger\": 101,\n" +
            "    \"qtySalad\": 500,\n" +
            "    \"qtyWater\": 0\n" +
            "  },\n" +
            "  \"consumed\": {\n" +
            "    \"qtyBurger\": 0,\n" +
            "    \"qtySalad\": 0,\n" +
            "    \"qtyWater\": 0\n" +
            "  }\n" +
            "}";
        expectedBody = expectedBody.replace("\n", "");
        expectedBody = expectedBody.replace(" ", "");
        Assert.assertEquals(body.asString(), expectedBody);
        System.out.println("FIN TEST testEnonceRes *******************************");
    }
}