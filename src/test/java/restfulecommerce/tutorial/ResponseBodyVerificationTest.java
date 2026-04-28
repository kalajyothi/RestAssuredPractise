package restfulecommerce.tutorial.pojo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;


public class ResponseBodyVerificationTest {

    @Test
    public void testResponseWithMatchers() {

        given().when()
                .queryParam("id", 2)
                .log().all()
                .get("http://localhost:3004/getOrder")
                .then().log().all()
                .statusCode(200)
                .and().assertThat()
                .body("message", equalTo("Order found!!"))
                .body("orders[0].id", equalTo(2));

    }

    @Test
    public void testExtractResponseAndVerify() {

        Response response = given().when()
                .log().all()
                .get("http://localhost:3004/getAllOrders")
                .then().log().all()
                .statusCode(200)
                .extract().response();

        String orderMsg = response.jsonPath().getString("message");
        assertEquals(orderMsg, "Orders fetched successfully!");

        String orderTwoUserId = response.jsonPath().getString("orders[1].user_id");
        assertEquals(orderTwoUserId, "1");

        JsonPath jsonPath = new JsonPath(response.asString());
        assertEquals(jsonPath.getString("orders[0].product_name"), "iPhone");

    }

    @Test
    public void testExtractResponseWithGson() {

        String response = given().when()
                .log().all()
                .get("http://localhost:3004/getAllOrders")
                .then().log().all().statusCode(200)
                .extract().response().asPrettyString();

        Gson gson = new Gson();
        JsonObject responseObject = gson.fromJson(response, JsonObject.class);
        String orderText = responseObject.get("message").getAsString();
        assertEquals(orderText, "Orders fetched successfully!");

        JsonArray orderArray = responseObject.getAsJsonArray("orders");
        String orderTwoProductName = orderArray.get(1).getAsJsonObject()
                .get("product_name").getAsString();
        assertEquals(orderTwoProductName, "iPad");


    }

    @Test
    public void testMultipleResponseConditions() {

        given().when()
                .log().all()
                .get("http://localhost:3004/getAllOrders")
                .then().log().all()
                .statusCode(200)
                .and().assertThat()
                .body("message", equalTo("Orders fetched successfully!"), "orders[0].user_id", equalTo("1"),
                        "orders[0].product_id", equalTo("1"), "orders[0].product_name", equalTo("iPhone"), "orders[0].product_amount",
                        equalTo(500));

    }

    @Test
    public void testDeserializeResponseWithPojo() {

        OrderResponse orderResponse = given().when()
                .log().all()
                .get("http://localhost:3004/getAllOrders")
                .then().log().all()
                .statusCode(200)
                .extract().body().as(OrderResponse.class);

        assertEquals(orderResponse.getMessage(), "Orders fetched successfully!");
        assertEquals(orderResponse.getOrders().get(0).getId(), 1);
        assertEquals(orderResponse.getOrders().get(1).getTaxAmt(), 7.99);

    }

    @Test
    public void testResponseFromJsonFile() {

        String actualResponse = given().when()
                .log().all()
                .get("http://localhost:3004/getAllOrders")
                .then().log().all()
                .statusCode(200)
                .extract().response().asString();
        String expectedJson = null;
        try {
            InputStream is = Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("AllOrders.json"));
            expectedJson = new String(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JSONAssert.assertEquals(expectedJson, actualResponse, true);

    }

}
