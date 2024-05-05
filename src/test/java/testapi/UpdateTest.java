package testapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import model.User;
import request.UpdateUserEndpoint;

public class UpdateTest {
    @Test
    public void testUpdateTitle() {
        // menyiapkan data test
        String userId = "60d0fe4f5311236168a10a09";
        String fieldName = "title";
        String value = "mr";

        Response response = UpdateUserEndpoint.updateOneField(userId, fieldName, value);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("user-schema.json"));//

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
}
