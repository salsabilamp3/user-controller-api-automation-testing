package request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Location;
import model.User;
import model.UserRequired;
import io.restassured.http.ContentType;

public class CreateUserEndpoint {
    public static Response createAllField(User user) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("app-id", "66273c13e26079618814ec16");
        request.body(user);
        return request.post(EndPoint.CREATE_USER);
    }

    public static Response createValidRequired(String firstName, String lastName, String email, String appId) {
        // Create a user request object
        UserRequired userRequired = new UserRequired(firstName, lastName, email);

        // Prepare and send the HTTP POST request
        RequestSpecification request = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("app-id", appId)
            .body(userRequired) 
            .contentType(ContentType.JSON);

        return request.post(EndPoint.CREATE_USER);
    }
}
