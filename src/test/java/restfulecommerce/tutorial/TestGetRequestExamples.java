package restfulecommerce.tutorial;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestGetRequestExamples {

    @Test
    public void testGetAllOrders() {

        given().when()
                .log()
                .all()
                .get("http://localhost:3004/getAllOrders")
                .then()
                .log()
                .all()
                .statusCode(200);

    }

    @Test
    public void testGetOrderWithQueryParam() {

        given().when()
                .log().all()
                .queryParam("id", 1)
                .get("http://localhost:3004/getOrder")
                .then().log().all()
                .statusCode(200).and()
                .assertThat()
                .body("orders[0].user_id", equalTo("2"));
    }

    @Test
    public void testGetOrderWithMultipleQueryParam() {
        given().when()
                .log().all()
                .queryParams("id", 1, "user_id", 2)
                .get("http://localhost:3004/getOrder")
                .then().log().all()
                .statusCode(200).and()
                .body("orders[0].product_id", equalTo("string1"));
    }

    @Test
    public void testGetOrderWithMultipleQueryParameters() {

        given().when()
                .log().all()
                .queryParam("id", 10)
                .queryParam("user_id", 31)
                .queryParam("product_id", 1)
                .get("http://localhost:3004/getOrder")
                .then().log().all()
                .statusCode(200).and()
                .body("orders[0].id", equalTo(10));
    }

    @Test
    public void testGetOrderWithMultipleQueryParamWithMap() {

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", 10);
        queryParams.put("user_id", 31);
        queryParams.put("product_id", 1);

        given().when()
                .log().all()
                .queryParams(queryParams)
                .get("http://localhost:3004/getOrder")
                .then().log().all()
                .statusCode(200).and()
                .body("orders[0].id", equalTo(10));
    }

    @Test
    public void testGetBookingWithPathParam() {
        given().when()
                .log().all()
                .pathParam("id", 1)
                .get("https://restful-booker.herokuapp.com/booking/{id}")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void testAuthHeader() {


    }


    @Test
    public void testGetAllOrdersWithHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        given().headers(headers)
                .when()
                .get("http://localhost:3004/getAllOrders")
                .then()
                .statusCode(200);
    }

    @Test
    public void testExtractResponseBody() {

        String responseBody = given().when()
                .get("http://localhost:3004/getAllOrders")
                .then()
                .statusCode(200)
                .extract().response().asPrettyString(); //asString() also works here
        System.out.println(responseBody);

    }

    @Test
    public void testExtractFieldValueFromResponse() {

        int orderId = given().when()
                .get("http://localhost:3004/getAllOrders")
                .then().statusCode(200)
                .extract().response().path("orders[0].id");

        System.out.println("Order Id is: " + orderId);


    }

    @Test
    public void testVerifyResponseHeader() {

        given().when()
                .get("http://localhost:3004/getAllOrders")
                .then()
                .headers("Content-Type", "application/json; charset=utf-8")
                .statusCode(200);

    }

    @Test
    public void testVerifyResponseTime() {

        given().when()
                .get("http://localhost:3004/getAllOrders")
                .then().statusCode(200)
                .time(lessThan(500L), TimeUnit.MILLISECONDS);
    }

    @Test
    public void testResponseTime() {

        Response response = given().get("http://localhost:3004/getAllOrders");
        System.out.println(response.getTimeIn(TimeUnit.MILLISECONDS));
    }

    @Test
    public void testVerifyResponseSize() {

        given().when()
                .get("http://localhost:3004/getAllOrders")
                .then().statusCode(200).body("orders.size()", greaterThan(0));
    }

    @Test
    public void testResponseBody() {

        given().when()
                .queryParam("id", 1)
                .get("http://localhost:3004/getOrder")
                .then().statusCode(200).and()
                .body("message", equalTo("Order found!!"))
                .body("orders[0].id", notNullValue())
                .body("orders[0].id", equalTo(1));
    }

    @Test
    public void testResponseBodyMultipleAssertions() {

        given().when()
                .queryParam("id", 1)
                .get("http://localhost:3004/getOrder")
                .then().statusCode(200).and()
                .body("message", equalTo("Order found!!"), "orders[0].id", notNullValue(), "orders[0].id", equalTo(1));
    }

    @Test
    public void testBasicAuthWithGetRequest() {

    }
}
