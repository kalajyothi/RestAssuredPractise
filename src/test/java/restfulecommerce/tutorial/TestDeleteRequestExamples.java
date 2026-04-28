package restfulecommerce.tutorial;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class TestDeleteRequestExamples {

    private String token;
    int orderId = 1;

    @Test
    public void testDeleteOrder() {

        given().header("Authorization", token)
                .log().all().when()
                .delete("http://localhost:3004/deleteOrder/" + orderId)
                .then().log().all()
                .statusCode(204);

    }

    @Test
    public void testTokenGeneration() {
        String reqBody = """
                {
                  "username": "admin",
                  "password": "secretPass123"
                }""";

        token = given().contentType(ContentType.JSON)
                .when()
                .body(reqBody)
                .post("http://localhost:3004/auth")
                .then()
                .statusCode(201)
                .and()
                .body("token", notNullValue())
                .extract().path("token");

    }

    @Test
    public void testFetchingDeletedOrder() {

        given().when()
                .queryParam("id", 1)
                .get("http://localhost:3004/getOrder")
                .then().statusCode(404);


    }
}
