package testapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.restassured.response.Response;
import request.GetUserEndpoint;

public class GetTest {

    @Test
    public void testGetUserWithoutAppId() {
        String userId = "66364cfab72aea99a7ea9670"; // Contoh ID valid
        Response response = GetUserEndpoint.getUser(userId, ""); // App ID tidak diatur
        
        assertEquals(403, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("\"error\":\"APP_ID_MISSING\""));
    }

    @Test
    public void testGetUserWithWrongAppId() {
        String userId = "66364cfab72aea99a7ea9670"; // Contoh ID valid
        String appId = "66273be9e26079c76814ec99"; // App ID salah
        Response response = GetUserEndpoint.getUser(userId, appId);
        
        assertEquals(403, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("\"error\":\"APP_ID_NOT_EXIST\""));
    }

    @Test
    public void testGetUserWithValidIdAndRegistered() {
        String userId = "66364cfab72aea99a7ea9670"; // Contoh ID valid dan terdaftar
        String appId = "66273c13e26079618814ec16"; // App ID valid
        Response response = GetUserEndpoint.getUser(userId, appId);
        
        assertEquals(200, response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    public void testGetUserWithValidIdAndNotRegistered() {
        String userId = "60d0fe4f5311236168a10999"; // Contoh ID valid tetapi tidak terdaftar
        String appId = "66273c13e26079618814ec16"; // App ID valid
        Response response = GetUserEndpoint.getUser(userId, appId);
        
        assertEquals(404, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("\"error\":\"RESOURCE_NOT_FOUND\""));
    }

    @Test
    public void testGetUserWithInvalidIdFormat() {
        String userId = "123abc"; // Contoh ID dengan format salah
        String appId = "66273c13e26079618814ec16"; // App ID valid
        Response response = GetUserEndpoint.getUser(userId, appId);
        
        assertEquals(400, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("\"error\":\"PARAMS_NOT_VALID\""));
    }
}
