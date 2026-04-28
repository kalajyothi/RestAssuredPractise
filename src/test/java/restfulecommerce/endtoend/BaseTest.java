package restfulecommerce.endtoend;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

public class BaseTest {

    @BeforeClass
    public void configSetup() {

        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("http://localhost:3004")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addFilter(new RequestLoggingFilter())  // This print details in console --> Request log: URL, Headers, Body
                .addFilter(new ResponseLoggingFilter()) // This print details in console --> Response log: Status Code, Response Body
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectResponseTime(lessThan(20000L)).build();


        RestAssured.requestSpecification = requestSpecification; //“Use this request configuration as default for all requests”
        RestAssured.responseSpecification = responseSpecification;


    }

}
