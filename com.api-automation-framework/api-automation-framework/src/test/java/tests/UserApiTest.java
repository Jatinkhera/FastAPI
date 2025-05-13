package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import config.ConfigManager;
import org.json.JSONObject;

public class UserApiTest {
    private static String baseUrl = ConfigManager.get("baseUrl");
    private static int userId;

    @Test(priority = 1)
    public void createUser() {
        JSONObject payload = new JSONObject();
        payload.put("name", "Jatin");
        payload.put("email", "jatin@.com");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload.toString())
                .post(baseUrl + "/users");

        Assert.assertEquals(response.getStatusCode(), 201);
        userId = response.jsonPath().getInt("id");
    }

    @Test(priority = 2, dependsOnMethods = "createUser")
    public void getUser() {
        Response response = RestAssured.get(baseUrl + "/users/" + userId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "Jatin");
    }

    @Test(priority = 3, dependsOnMethods = "getUser")
    public void updateUser() {
        JSONObject payload = new JSONObject();
        payload.put("name", "Johnny");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload.toString())
                .put(baseUrl + "/users/" + userId);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "Johnny");
    }

    @Test(priority = 4, dependsOnMethods = "updateUser")
    public void deleteUser() {
        Response response = RestAssured.delete(baseUrl + "/users/" + userId);
        Assert.assertEquals(response.getStatusCode(), 204);
    }

    @Test(priority = 5)
    public void createUserWithInvalidData() {
        JSONObject payload = new JSONObject(); // missing required fields

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload.toString())
                .post(baseUrl + "/users");

        Assert.assertTrue(response.getStatusCode() >= 400);
    }
}
