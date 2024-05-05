package request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetUserEndpoint {
    public static Response getUser(String userId, String appId) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("app-id", appId);
        
        return request.get(EndPoint.GET_USER_BY_ID + userId);
    }
}
