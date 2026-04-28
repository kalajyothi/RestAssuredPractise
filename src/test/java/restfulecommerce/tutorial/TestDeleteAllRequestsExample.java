package restfulecommerce.tutorial;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;


public class TestDeleteAllRequestsExample {

    private String token;

    @Test
    public void testDeleteAllRequests() {

        given().header("Authorization", token)
                .when().log().all()
                .delete("http://localhost:3004/deleteAllOrders")
                .then().log().all()
                .statusCode(204);
    }

    @Test
    public void testTokenGeneration() {

        String reqBody = """
                {
                  "username": "admin",
                  "password": "secretPass123"
                }
                """;
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
}
