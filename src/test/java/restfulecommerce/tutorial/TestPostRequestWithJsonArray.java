package restfulecommerce.tutorial.pojo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestPostRequestWithJsonArray {

    @Test
    public void testCreateOrder() {
        JsonObject orderOne = new JsonObject();
        orderOne.addProperty("user_id", "11");
        orderOne.addProperty("product_id", "12");
        orderOne.addProperty("product_name", "iphone");
        orderOne.addProperty("product_amount", 60000);
        orderOne.addProperty("qty", "1");
        orderOne.addProperty("tax_amt", "1000");
        orderOne.addProperty("total_amt", "70000");

        JsonObject orderTwo = new JsonObject();
        orderTwo.addProperty("user_id", "12");
        orderTwo.addProperty("product_id", "13");
        orderTwo.addProperty("product_name", "oneplus");
        orderTwo.addProperty("product_amount", 50000);
        orderTwo.addProperty("qty", "1");
        orderTwo.addProperty("tax_amt", "800");
        orderTwo.addProperty("total_amt", "60000");


        JsonArray orderArray = new JsonArray();
        orderArray.add(orderOne);
        orderArray.add(orderTwo);

        given().contentType(ContentType.JSON)
                .when()
                .log()
                .all()
                .body(orderArray.toString()) //Rest Assured does NOT automatically serialize Gson objects
                .post("http://localhost:3004/addOrder")
                .then()
                .log()
                .all()
                .statusCode(201)
                .and()
                .assertThat()
                .body("message", equalTo("Orders added successfully!"));


    }


    @Test
    public void testCreateOrderWithcollections() {

        Map<String, Object> orderOne = new LinkedHashMap<>();
        orderOne.put("user_id", "21");
        orderOne.put("product_id", "22");
        orderOne.put("product_name", "samsung");
        orderOne.put("product_amount", 40000);
        orderOne.put("qty", 1);
        orderOne.put("tax_amt", 700);
        orderOne.put("total_amt", 50000);

        System.out.println(orderOne);

        Map<String, Object> orderTwo = new LinkedHashMap<>();
        orderTwo.put("user_id", "22");
        orderTwo.put("product_id", "23");
        orderTwo.put("product_name", "poco");
        orderTwo.put("product_amount", 30000);
        orderTwo.put("qty", 1);
        orderTwo.put("tax_amt", 600);
        orderTwo.put("total_amt", 40000);


        List<Map<String, Object>> orderList = new ArrayList<>();

        orderList.add(orderOne);
        orderList.add(orderTwo);
        System.out.println(orderList);


        given().contentType(ContentType.JSON)
                .when()
                .log()
                .all()
                .body(orderList) //Rest Assured automatically serializes Java objects
                .post("http://localhost:3004/addOrder")
                .then()
                .log()
                .all()
                .statusCode(201)
                .and()
                .assertThat()
                .body("message", equalTo("Orders added successfully!"));


    }


}