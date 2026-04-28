package org.sample;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestPostRequest {
    @Test
    public void testCreateOrder() {
        String orderPayload = "[\n" +
                "  {\n" +
                "    \"user_id\": \"1\",\n" +
                "    \"product_id\": \"2\",\n" +
                "    \"product_name\": \"ldf\",\n" +
                "    \"product_amount\": 20,\n" +
                "    \"qty\": 2,\n" +
                "    \"tax_amt\": 22,\n" +
                "    \"total_amt\": 34\n" +
                "  }\n" +
                "]";
        given().contentType(ContentType.JSON).when().log().all()
                .body(orderPayload).post("http://localhost:3004/addOrder").then().log().all()
                .statusCode(201).and()
                .body("message", equalTo("Orders added successfully!"));


    }
}
