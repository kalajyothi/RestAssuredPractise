package restfulecommerce.tutorial;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import restfulecommerce.tutorial.data.Orders;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestPostRequestWithPOJO {


    @Test
    public void testCreateOrder() {

        Orders orderOne = new Orders("3", "4", "Google Pixel 9 Pro", 659.87, 1, 65.97, 725.84);
        Orders orderTwo = new Orders("4", "5", "iphone", 559.87, 1, 60.97, 700.84);
        Orders[] orders = {orderOne, orderTwo};

        given().contentType(ContentType.JSON)
                .when()
                .log().all()
                .body(orders)
                .post("http://localhost:3004/addOrder")
                .then().log().all()
                .statusCode(201)
                .and().assertThat()
                .body("message", equalTo("Orders added successfully!"));

    }

    @Test
    public void testCreateOrderWithList() {

        Orders orderOne = new Orders("3", "4", "Google Pixel 9 Pro", 659.87, 1, 65.97, 725.84);
        Orders orderTwo = new Orders("4", "5", "iphone", 559.87, 1, 60.97, 700.84);

        List<Orders> orders = new ArrayList<>();
        orders.add(orderOne);
        orders.add(orderTwo);

        given().contentType(ContentType.JSON)
                .when().log().all()
                .body(orders)
                .post("http://localhost:3004/addOrder")
                .then().log().all()
                .statusCode(201)
                .and().assertThat()
                .body("message", equalTo("Orders added successfully!"));


    }

}
