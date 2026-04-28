package restfulecommerce.tutorial.datadriven.iteratordataprovider;

import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestPostRequesWithDataProviderObject {

    @DataProvider(name = "orderData")
    public Iterator<Order> getOrderData() {

        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("1", "79", "IPhone 17 Pro", 6999, 1, 699, 7698));
        orderList.add(new Order("2", "81", "Samsung Galaxy S25", 7999, 1, 799, 8798));
        orderList.add(new Order("2", "87", "iPad SE", 4550, 1, 455, 5005));
        orderList.add(new Order("4", "13", "Macbook Pro", 9999, 1, 999, 10098));
        orderList.add(new Order("5", "19", "Macbook Air", 8999, 1, 899, 9898));

        return orderList.iterator();

    }

    @Test(dataProvider = "orderData")
    public void testCreateOrder(Order order) {

        List<Order> orderList = List.of(order);

        given().contentType(ContentType.JSON)
                .when().log().all()
                .body(orderList)
                .post("http://localhost:3004/addOrder")
                .then().log().all()
                .statusCode(201)
                .and()
                .assertThat()
                .body("message", equalTo("Orders added successfully!"));
    }

}
