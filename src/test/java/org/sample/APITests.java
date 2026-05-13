package org.sample;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToCompressingWhiteSpace;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.Test;

public class APITests {

    @Test
    public void testGetRequest() {
        given ()
            .get ("https://api.restful-api.dev/objects")
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
}
