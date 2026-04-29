package gittutorial;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class PostExampleTest {

    @Test
    public void testCreateOrder() {

        String order = "[\n" +
                "  {\n" +
                "    \"user_id\": \"31\",\n" +
                "    \"product_id\": \"1\",\n" +
                "    \"product_name\": 1234,\n" +
                "    \"product_amount\": 10,\n" +
                "    \"qty\": 1,\n" +
                "    \"tax_amt\": \"hjghjm\",\n" +
                "    \"total_amt\": 12\n" +
                "  }\n" +
                "]";


        given().contentType(ContentType.JSON)
                .when()
                .log()
                .all()
                .body(order)
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
}
