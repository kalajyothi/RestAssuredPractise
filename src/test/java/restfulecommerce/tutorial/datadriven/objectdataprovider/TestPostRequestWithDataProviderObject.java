package restfulecommerce.tutorial.datadriven.objectdataprovider;

import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestPostRequestWithDataProviderObject {


//    int[][] arr = new int[3][3];
//👉  This creates: 3 rows and 3 columns
//     All values = 0 (default)

//    Method 2: Direct values
//    int[][] arr = {
//            {1, 2, 3},
//            {4, 5, 6},
//            {7, 8, 9}
//    };

//    4. How to access elements
//    System.out.println(arr[0][0]); // 1
//    System.out.println(arr[1][2]); // 6

//    Formula:
//
//    arr[row][column]
//    Index starts from 0
//    arr[1][2] → 2nd row, 3rd column

//    5. How to print a 2D array (IMPORTANT)
//
//    Use nested loops:
//
//            for(int i = 0; i < arr.length; i++) {          // rows
//        for(int j = 0; j < arr[i].length; j++) {   // columns
//            System.out.print(arr[i][j] + " ");
//        }
//        System.out.println();
//    }
//
//👉 Output:
//
//            1 2 3
//            4 5 6
//            7 8 9

    @DataProvider(name = "orderData")
    public Object[][] getOrderData() {

        return new Object[][]{{new Order("1", "90", "Colgate Gel", 109, 1, 10, 119, 201)},
                {new Order("1", "13", "Perfume", 299, 1, 30, 329, 201)},
                {new Order("4", "79", "Fresh Milk", 12, 1, 1, 13, 201)},
                {new Order("6", "81", "Yogurt", 16, 2, 3, 35, 201)},
                {new Order("2", "63", "Bath Soap", 5, 5, 3, 28, 201)}};


    }

    @Test(dataProvider = "orderData")
    public void testCreateOrder(Order order) {

        List<Order> orders = new ArrayList<>();
        orders.add(order);
        given().contentType(ContentType.JSON)
                .when().log().all()
                .body(orders)
                .post("http://localhost:3004/addOrder")
                .then().log().all()
                .statusCode(order.getExpectedStatus())
                .and().assertThat()
                .body("message", equalTo("Orders added successfully!"));

    }


}
