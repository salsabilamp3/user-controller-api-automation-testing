package testapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.restassured.response.Response;
import request.DeleteUserEndpoint;

public class DeleteTest {

    @Test
    public void testDeleteWithoutAppId() {
        String userId = "663780ea10f9d46f6643c06c"; // Contoh ID valid
        Response response = DeleteUserEndpoint.deleteUser(userId, ""); // App ID tidak diatur
        
        assertEquals(403, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("\"error\":\"APP_ID_MISSING\""));
    }

    @Test
    public void testDeleteWithWrongAppId() {
        String userId = "663780ea10f9d46f6643c06c"; // Contoh ID valid
        String appId = "66273c13e26079618814e16"; // App ID salah
        Response response = DeleteUserEndpoint.deleteUser(userId, appId);
        
        assertEquals(403, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("\"error\":\"APP_ID_NOT_EXIST\""));
    }

    @Test
    public void testDeleteWithValidIdAndRegistered() {
        String userId = "663780ea10f9d46f6643c06c"; // Contoh ID valid dan terdaftar
        String appId = "66273c13e26079618814ec16"; // App ID valid
        Response response = DeleteUserEndpoint.deleteUser(userId, appId);
        
        assertEquals(200, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("\"id\":\"" + userId + "\""));
    }

    @Test
    public void testDeleteWithValidIdAndNotRegistered() {
        String userId = "663780ea10f9d46f6643c06c"; // Contoh ID valid tetapi tidak terdaftar
        String appId = "66273c13e26079618814ec16"; // App ID valid
        Response response = DeleteUserEndpoint.deleteUser(userId, appId);
        
        assertEquals(404, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("\"error\":\"RESOURCE_NOT_FOUND\""));
    }

    @Test
    public void testDeleteWithInvalidIdFormat() {
        String userId = "15"; // Contoh ID dengan format salah
        String appId = "66273c13e26079618814ec16"; // App ID valid
        Response response = DeleteUserEndpoint.deleteUser(userId, appId);
        
        assertEquals(400, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("\"error\":\"PARAMS_NOT_VALID\""));
    }

    @Test
    public void testDeleteWithEmptyId() {
        String appId = "66273c13e26079618814ec16"; // App ID valid
        Response response = DeleteUserEndpoint.deleteUser("", appId); // Mengosongkan parameter ID
        
        assertEquals(404, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("\"error\":\"PATH_NOT_FOUND\""));
    }
}
