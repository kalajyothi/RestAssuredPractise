package org.sample;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToCompressingWhiteSpace;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.startsWith;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class APITests {

    @Test
    public void testGetRequest () {
        given ().get ("https://api.restful-api.dev/objects")
            .then ()
            .statusCode (200)
            .assertThat ()
            .body ("[0].name", equalTo ("Google Pixel 6 Pro"))
            .body ("[0].name", equalToIgnoringCase ("GoOGle Pixel 6 PRO"))
            .body ("[0].data.color", containsString ("White"))
            .body ("[0].name", startsWith ("G"))
            .body ("[0].name", endsWith ("o"))
            .body ("[0].name", equalToCompressingWhiteSpace ("    Google Pixel    6    Pro    "));
    }

    @Test
    public void testPostRequest () {
        String requestBody = """
            {
              "name": "Apple MacBook Pro 16",
              "data": {
                "year": 2019,
                "price": 1849.99,
                "CPU model": "Intel Core i9",
                "Hard disk size": "1 TB"
              }
            }
            """;
        given ().contentType ("application/json")
            .when ()
            .log ()
            .all ()
            .body (requestBody)
            .post ("https://api.restful-api.dev/objects")
            .then ()
            .log ()
            .all ()
            .statusCode (200);
    }

    @Test
    public void testPutRequest () {
        String requestBody = """
              {
              "name": "Apple MacBook Pro 16",
              "data": {
                "year": 2019,
                "price": 2049.99,
                "CPU model": "Intel Core i9",
                "Hard disk size": "1 TB",
                "color": "silver"
              }
            }
            """;
        given ().contentType (ContentType.JSON)
            .when ()
            .body (requestBody)
            .put ("https://api.restful-api.dev/objects/ff8081819d82fab6019e1f3b461d2f67")
            .then ()
            .log ()
            .all ()
            .statusCode (200);
    }

}

