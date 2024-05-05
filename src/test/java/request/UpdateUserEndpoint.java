package request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.User;

public class UpdateUserEndpoint {
    public static Response updateOneField(String userId, String fieldName, String fieldValue) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("app-id", "66273c13e26079618814ec13");
        request.body("{\"" + fieldName + "\":\"" + fieldValue + "\"}");
        return request.put(EndPoint.UPDATE_USER + userId);
    }

    public static Response updateAllField(String userId, User user) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("app-id", "66273c13e26079618814ec13");
        request.body(user);
        return request.put(EndPoint.UPDATE_USER + userId);
    }
}
