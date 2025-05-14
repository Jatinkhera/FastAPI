package tests;

import config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserApiTest {
	private static String base_url = ConfigManager.get("base_url");
    private String token;
    private String userId;

    @BeforeClass
    public void getToken() {
        JSONObject credentials = new JSONObject();
        credentials.put("client_id", ConfigManager.get("client_id"));
        credentials.put("client_secret", ConfigManager.get("client_secret"));

        Response res = RestAssured
                .given()
                .contentType("application/json")
                .body(credentials.toString())
                .post(ConfigManager.get("token_url"));

        Assert.assertEquals(res.getStatusCode(), 200);
        token = res.jsonPath().getString("access_token");
    }

    @Test(priority = 1)
    public void testCreateUser() {
        JSONObject payload = new JSONObject();
        payload.put("name", "Jatin");
        payload.put("email", "jatin@abc.com");

        Response res = RestAssured
                .given()
                .auth().oauth2(token)
                .contentType("application/json")
                .body(payload.toString())
                .post(ConfigManager.get("base_url") + "/users");

        Assert.assertEquals(res.getStatusCode(), 201);
        userId = res.jsonPath().getString("id");
    }

    @Test(priority = 2, dependsOnMethods = "testCreateUser")
    public void testGetUser() {
        Response res = RestAssured
                .given()
                .auth().oauth2(token)
                .get(ConfigManager.get("base_url") + "/users/" + userId);

        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("name"), "Jatin");
    }

    @Test(priority = 3, dependsOnMethods = "testGetUser")
    public void testUpdateUser() {
        JSONObject update = new JSONObject();
        update.put("name", "Jatin khera Updated");

        Response res = RestAssured
                .given()
                .auth().oauth2(token)
                .contentType("application/json")
                .body(update.toString())
                .put(ConfigManager.get("base_url") + "/users/" + userId);

        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(priority = 4, dependsOnMethods = "testUpdateUser")
    public void testDeleteUser() {
        Response res = RestAssured
                .given()
                .auth().oauth2(token)
                .delete(ConfigManager.get("base_url") + "/users/" + userId);

        Assert.assertEquals(res.getStatusCode(), 204);
    }
    
    @Test(priority = 5)
    public void createUserWithInvalidData() {
        JSONObject payload = new JSONObject(); // missing required fields

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(payload.toString())
                .post(base_url + "/users");

        Assert.assertTrue(response.getStatusCode() >= 400);
    }
}