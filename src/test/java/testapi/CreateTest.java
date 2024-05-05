package testapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.junit.jupiter.api.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import model.Location;
import model.User;
import request.CreateUserEndpoint;
import request.UpdateUserEndpoint;

public class CreateTest {
    // Eksekusi TC-1-2
    @Test
    public void testCreateWithNonExistAppID() {
        // Prepare test data
        String firstName = "Amel";
        String lastName = "Dewi";
        String email = "amelidew@examples.com";
        String appId = "66273c13e26079618814e16"; // Intentionally incorrect or non-existent app ID

        // Execute the API request
        Response response = CreateUserEndpoint.createValidRequired(firstName, lastName, email, appId);

        // Check actual status code
        assertEquals(403, response.getStatusCode(), "Expected HTTP status 403 but got " + response.getStatusCode());

        // Validate the error message in the response body
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains("\"error\":\"APP_ID_NOT_EXIST\""), "Expected error message not found in response body");

        // Conditional schema validation
        if (response.getStatusCode() == 200) {
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("user-schema.json"));
        }
    }

    // Eksekusi TC-1-4
    @Test
    public void testCreateValidRequired() {
        // menyiapkan data test
        String firstName = "Sa";
        String lastName = "Aliya";
        String email = "saaliyaasss@example.com";

        String appId = "66273c13e26079618814ec16";

        Response response = CreateUserEndpoint.createValidRequired(firstName, lastName, email, appId);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("user-schema.json"));

        // periksa actual status code
        assertEquals(200, response.getStatusCode());

        // periksa actual response body
        User actualBody = response.as(User.class);

        assertEquals(firstName, actualBody.getFirstName());
        assertEquals(lastName, actualBody.getLastName());
        assertEquals(email, actualBody.getEmail());

        // Check for auto-generated fields by the server
        assertNotNull(actualBody.getId());

        assertNotNull(actualBody.getRegisterDate());
        assertNotNull(actualBody.getUpdatedDate());
    }

    // Eksekusi TC-1-7
    @Test
    public void testCreateFirstName50Char() {
        // Prepare test data
        String firstName = "Christopher";
        String lastName = "Fitzirewielliamshiretoningstonsmytheirtonhamshires";
        String email = "crifitzs@examples.com";
        String appId = "66273c13e26079618814ec16";

        // Execute the API request
        Response response = CreateUserEndpoint.createValidRequired(firstName, lastName, email, appId);
        
        // Check if the response matches the expected HTTP status code
        if (response.getStatusCode() != 200) {
            // Deserialize response body for detailed error reporting
            String responseBody = response.getBody().asString();
            // Use fail to enforce the failure and log the response body in the test report
            fail("Expected HTTP status code 200 \n{\r\n" + //
                                "    \"id\": (generated),\r\n" + //
                                "    \"firstName\" : \"Christopher\",\r\n" + //
                                "    \"lastName\" : \"Fitzirewielliamshiretoningstonsmytheirtonhamshires\",\r\n" + //
                                "    \"email\": \"crifitz@examples.com\",\r\n" + //
                                "    \"registerDate\" : (generated, now()),\r\n" + //
                                "     \"updatedDate\" : (generated, now())\r\n" + //
                                "}\nBut was " + response.getStatusCode() + ". Response Body: " + responseBody);
        }

        // Assert response body matches the JSON schema if the response is successful
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("user-schema.json"));

        // Deserialize response body
        User actualBody = response.as(User.class);

        // Validate actual response data
        assertEquals(firstName, actualBody.getFirstName());
        assertEquals(lastName, actualBody.getLastName());
        assertEquals(email, actualBody.getEmail());

        // Check for auto-generated fields by the server
        assertNotNull(actualBody.getId());
        assertNotNull(actualBody.getRegisterDate());
        assertNotNull(actualBody.getUpdatedDate());
    }

    // Eksekusi TC-1-8
    @Test
    public void testCreateValidAll() {
        // Prepare test data
        User testData = new User();
        testData.setTitle("ms");
        testData.setFirstName("Emily");
        testData.setLastName("Johnson");
        testData.setGender("female");
        testData.setEmail("emiliieyss@examples.com");  // Consider using a unique email or cleaning up before tests
        testData.setDateOfBirth("1990-04-25T00:00:00.000Z");
        testData.setPhone("+1234567890");
        testData.setPicture("https://example.com/profile_picture.jpg");

        Location location = new Location();
        location.setStreet("456 Oak Street");
        location.setCity("Los Angeles");
        location.setState("California");
        location.setCountry("United States");
        location.setTimezone("-08:00");
        testData.setLocation(location);

        // Execute the API request
        Response response = CreateUserEndpoint.createAllField(testData);

        // Check if the response matches the expected HTTP status code
        if (response.getStatusCode() != 200) {
            // Deserialize response body for detailed error reporting
            String responseBody = response.getBody().asString();
            // Use fail to enforce the failure and log the response body in the test report
            fail("Expected HTTP status code 200 but was " + response.getStatusCode() + ". Response Body: " + responseBody);
        }

        // Assert response body matches the JSON schema if the response is successful
        response.then().assertThat().body(matchesJsonSchemaInClasspath("user-schema.json"));

        // Deserialize response to User model
        User actualBody = response.as(User.class);

        // Validate the details are correct
        assertEquals(testData.getTitle(), actualBody.getTitle());
        assertEquals(testData.getFirstName(), actualBody.getFirstName());
        assertEquals(testData.getLastName(), actualBody.getLastName());
        assertEquals(testData.getGender(), actualBody.getGender());
        assertEquals(testData.getEmail(), actualBody.getEmail());
        assertEquals(testData.getDateOfBirth(), actualBody.getDateOfBirth());
        assertEquals(testData.getPhone(), actualBody.getPhone());
        assertEquals(testData.getPicture(), actualBody.getPicture());
        assertEquals(testData.getLocation().getStreet(), actualBody.getLocation().getStreet());
        assertEquals(testData.getLocation().getCity(), actualBody.getLocation().getCity());
        assertEquals(testData.getLocation().getState(), actualBody.getLocation().getState());
        assertEquals(testData.getLocation().getCountry(), actualBody.getLocation().getCountry());
        assertEquals(testData.getLocation().getTimezone(), actualBody.getLocation().getTimezone());

        // Check for auto-generated fields by the server
        assertNotNull(actualBody.getId());
        assertNotNull(actualBody.getRegisterDate());
        assertNotNull(actualBody.getUpdatedDate());
    }
    
    // Eksekusi TC-1-30
    @Test
    public void testCreateLocationStreetIs101Char() {
        // Prepare test data
        User testData = new User();
        testData.setTitle("ms");
        testData.setFirstName("Jiyeon");
        testData.setLastName("Kim");
        testData.setGender("female");
        testData.setEmail("jiyeoniiiee.kim@examples.com");  // Consider using a unique email or cleaning up before tests
        testData.setDateOfBirth("1990-06-12T00:00:00.000Z");
        testData.setPhone("+82101234567");
        testData.setPicture("https://example.com/jiyeonkim.jpg");

        Location location = new Location();
        location.setStreet("123 Yeoksam-ro, Gangnam-gu, Gangnam-gu, Gangnam-gu, Gangnam-gu, 136 Gangnam-gu. 45 Yeoksam, Yeoksi-ro");
        location.setCity("Seoul");
        location.setState("");
        location.setCountry("South Korea");
        location.setTimezone("+09:00");
        testData.setLocation(location);

        // Execute the API request
        Response response = CreateUserEndpoint.createAllField(testData);

        // Check if the response matches the expected HTTP status code
        if (response.getStatusCode() != 400) {
            // Deserialize response body for detailed error reporting
            String responseBody = response.getBody().asString();
            // Use fail to enforce the failure and log the response body in the test report
            fail("Expected HTTP status code 400\n{\r\n" + //
                                "    \"error\": \"BODY_NOT_VALID\"\r\n" + //
                                "} \nBut was " + response.getStatusCode() + ". Response Body: " + responseBody);
        }

        // Assert response body matches the JSON schema if the response is successful
        response.then().assertThat().body(matchesJsonSchemaInClasspath("user-schema.json"));

        // Deserialize response to User model
        User actualBody = response.as(User.class);

        // Validate the details are correct
        assertEquals(testData.getTitle(), actualBody.getTitle());
        assertEquals(testData.getFirstName(), actualBody.getFirstName());
        assertEquals(testData.getLastName(), actualBody.getLastName());
        assertEquals(testData.getGender(), actualBody.getGender());
        assertEquals(testData.getEmail(), actualBody.getEmail());
        assertEquals(testData.getDateOfBirth(), actualBody.getDateOfBirth());
        assertEquals(testData.getPhone(), actualBody.getPhone());
        assertEquals(testData.getPicture(), actualBody.getPicture());
        assertEquals(testData.getLocation().getStreet(), actualBody.getLocation().getStreet());
        assertEquals(testData.getLocation().getCity(), actualBody.getLocation().getCity());
        assertEquals(testData.getLocation().getState(), actualBody.getLocation().getState());
        assertEquals(testData.getLocation().getCountry(), actualBody.getLocation().getCountry());
        assertEquals(testData.getLocation().getTimezone(), actualBody.getLocation().getTimezone());

        // Check for auto-generated fields by the server
        assertNotNull(actualBody.getId());
        assertNotNull(actualBody.getRegisterDate());
        assertNotNull(actualBody.getUpdatedDate());
    }
}
