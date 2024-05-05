package testapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import model.Location;
import model.User;
import request.UpdateUserEndpoint;

public class UpdateTest {
    // Eksekusi TC-3-4
    @Test
    public void testUpdateAll() {
        // menyiapkan data test
        String userId = "60d0fe4f5311236168a10a04";
        User testData = new User();

        testData.setTitle("ms");
        testData.setFirstName("Titi");
        testData.setLastName("Jane");
        testData.setGender("female");
        testData.setDateOfBirth("1995-08-15");
        testData.setPhone("+62817287187");
        testData.setPicture("https://example.com/profile_picture_tati.jpg");

        Location location = new Location();
        location.setStreet("123 Oak Street");
        location.setCity("Los Angeles");
        location.setState("California");
        location.setCountry("United States");
        location.setTimezone("-08:00");
        testData.setLocation(location);

        Response response = UpdateUserEndpoint.updateAllField(userId, testData);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("user-schema.json"));

        // periksa actual status code
        assertEquals(200, response.getStatusCode());

        // periksa actual response body
        User expectedBody = UpdateUserEndpoint.prepareTestUpdateAll();
        User actualBody = response.as(User.class);

        assertEquals(expectedBody.getId(), actualBody.getId());
        assertEquals(expectedBody.getTitle(), actualBody.getTitle());
        assertEquals(expectedBody.getFirstName(), actualBody.getFirstName());
        assertEquals(expectedBody.getLastName(), actualBody.getLastName());
        assertEquals(expectedBody.getPicture(), actualBody.getPicture());
        assertEquals(expectedBody.getGender(), actualBody.getGender());
        assertEquals(expectedBody.getEmail(), actualBody.getEmail());
        assertEquals(expectedBody.getDateOfBirth(), actualBody.getDateOfBirth());
        assertEquals(expectedBody.getPhone(), actualBody.getPhone());

        assertEquals(expectedBody.getLocation().getStreet(), actualBody.getLocation().getStreet());
        assertEquals(expectedBody.getLocation().getCity(), actualBody.getLocation().getCity());
        assertEquals(expectedBody.getLocation().getState(), actualBody.getLocation().getState());
        assertEquals(expectedBody.getLocation().getCountry(), actualBody.getLocation().getCountry());
        assertEquals(expectedBody.getLocation().getTimezone(), actualBody.getLocation().getTimezone());

        assertEquals(expectedBody.getRegisterDate(), actualBody.getRegisterDate());
        assertEquals(expectedBody.getUpdatedDate(), actualBody.getUpdatedDate());
    }

    // Eksekusi TC-3-9
    @Test
    public void testUpdateTitle() {
        // menyiapkan data test
        String userId = "60d0fe4f5311236168a10a09";
        String fieldName = "title";
        String value = "mr";

        Response response = UpdateUserEndpoint.updateOneField(userId, fieldName, value);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("user-schema.json"));

        // periksa actual status code
        assertEquals(200, response.getStatusCode());

        // periksa actual response body
        User expectedBody = UpdateUserEndpoint.prepareTestUpdateTitle();
        User actualBody = response.as(User.class);

        assertEquals(expectedBody.getId(), actualBody.getId());
        assertEquals(expectedBody.getTitle(), actualBody.getTitle());
        assertEquals(expectedBody.getFirstName(), actualBody.getFirstName());
        assertEquals(expectedBody.getLastName(), actualBody.getLastName());
        assertEquals(expectedBody.getPicture(), actualBody.getPicture());
        assertEquals(expectedBody.getGender(), actualBody.getGender());
        assertEquals(expectedBody.getEmail(), actualBody.getEmail());
        assertEquals(expectedBody.getDateOfBirth(), actualBody.getDateOfBirth());
        assertEquals(expectedBody.getPhone(), actualBody.getPhone());

        assertEquals(expectedBody.getLocation().getStreet(), actualBody.getLocation().getStreet());
        assertEquals(expectedBody.getLocation().getCity(), actualBody.getLocation().getCity());
        assertEquals(expectedBody.getLocation().getState(), actualBody.getLocation().getState());
        assertEquals(expectedBody.getLocation().getCountry(), actualBody.getLocation().getCountry());
        assertEquals(expectedBody.getLocation().getTimezone(), actualBody.getLocation().getTimezone());

        assertEquals(expectedBody.getRegisterDate(), actualBody.getRegisterDate());
        assertEquals(expectedBody.getUpdatedDate(), actualBody.getUpdatedDate());
    }

    // Eksekusi TC-3-18
    @Test
    public void testUpdateGender() {
        // menyiapkan data test
        String userId = "60d0fe4f5311236168a10a18";
        String fieldName = "gender";
        String value = "";

        Response response = UpdateUserEndpoint.updateOneField(userId, fieldName, value);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("user-schema.json"));

        // periksa actual status code
        assertEquals(200, response.getStatusCode());

        // periksa actual response body
        User expectedBody = UpdateUserEndpoint.prepareTestUpdateGender();
        User actualBody = response.as(User.class);

        assertEquals(expectedBody.getId(), actualBody.getId());
        assertEquals(expectedBody.getTitle(), actualBody.getTitle());
        assertEquals(expectedBody.getFirstName(), actualBody.getFirstName());
        assertEquals(expectedBody.getLastName(), actualBody.getLastName());
        assertEquals(expectedBody.getPicture(), actualBody.getPicture());
        assertEquals(expectedBody.getGender(), actualBody.getGender());
        assertEquals(expectedBody.getEmail(), actualBody.getEmail());
        assertEquals(expectedBody.getDateOfBirth(), actualBody.getDateOfBirth());
        assertEquals(expectedBody.getPhone(), actualBody.getPhone());

        assertEquals(expectedBody.getLocation().getStreet(), actualBody.getLocation().getStreet());
        assertEquals(expectedBody.getLocation().getCity(), actualBody.getLocation().getCity());
        assertEquals(expectedBody.getLocation().getState(), actualBody.getLocation().getState());
        assertEquals(expectedBody.getLocation().getCountry(), actualBody.getLocation().getCountry());
        assertEquals(expectedBody.getLocation().getTimezone(), actualBody.getLocation().getTimezone());

        assertEquals(expectedBody.getRegisterDate(), actualBody.getRegisterDate());
        assertEquals(expectedBody.getUpdatedDate(), actualBody.getUpdatedDate());
    }

    // Eksekusi TC-3-27
    @Test
    public void testUpdateEmail() {
        // menyiapkan data test
        String userId = "60d0fe4f5311236168a10a27";
        String fieldName = "email";
        String value = "emails@example.com";

        Response response = UpdateUserEndpoint.updateOneField(userId, fieldName, value);

        // periksa actual status code
        assertEquals(400, response.getStatusCode());

        // periksa actual response body
        String expectedResult = "BODY_NOT_VALID";
        String actualResult = response.getBody().jsonPath().getString("error");
        assertEquals(expectedResult, actualResult);
    }

    // Eksekusi TC-3-55
    @Test
    public void testUpdateNotExistedId() {
        // menyiapkan data test
        String userId = "60d0fe4f5311236168a10a35";
        User testData = new User();
        testData.setTitle("mr");
        testData.setFirstName("Tomothy");
        testData.setLastName("Hawkins");

        Response response = UpdateUserEndpoint.updateAllField(userId, testData);

        // periksa actual status code
        assertEquals(404, response.getStatusCode());

        // periksa actual response body
        String expectedResult = "RESOURCE_NOT_FOUND";
        String actualResult = response.getBody().jsonPath().getString("error");
        assertEquals(expectedResult, actualResult);
    }
}
