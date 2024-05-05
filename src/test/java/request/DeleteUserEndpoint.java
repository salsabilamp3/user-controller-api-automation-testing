package request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;

public class DeleteUserEndpoint {
    public static Response deleteUser(String userId, String appId) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("app-id", appId);
        
        // Menggunakan path parameter untuk mengirim request delete
        return request.delete(EndPoint.DELETE_USER + userId);
    }
}
