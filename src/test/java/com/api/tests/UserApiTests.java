package com.api.tests;

import com.api.client.UserApiClient;
import com.api.models.UserRequest;
import com.api.models.UserResponse;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.InputStream;

import static org.hamcrest.Matchers.*;

public class UserApiTests {

    private UserApiClient apiClient;

    @BeforeClass
    public void setUp() {
        apiClient = new UserApiClient();
    }

    @Test(description = "Verify GET /api/users returns 200 and valid user list")
    public void testGetUsersList() {
        Response response = apiClient.getUsers(2);
        
        response.then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data", not(empty()))
                .body("data[0].email", notNullValue());
    }

    @Test(description = "Verify POST /api/users creates a new user and returns POJO response")
    public void testCreateUser() {
        UserRequest request = new UserRequest("Dhwani Parikh", "Senior Automation Lead");
        UserResponse response = apiClient.createUser(request);

        Assert.assertNotNull(response.getId(), "User ID should be generated");
        Assert.assertEquals(response.getName(), "Dhwani Parikh");
        Assert.assertEquals(response.getJob(), "Senior Automation Lead");
        Assert.assertNotNull(response.getCreatedAt(), "CreatedAt timestamp should not be null");
    }

    @Test(description = "Verify PUT /api/users/{id} updates user details")
    public void testUpdateUser() {
        UserRequest request = new UserRequest("Dhwani Parikh", "Principal QA Architect");
        UserResponse response = apiClient.updateUser(2, request);

        Assert.assertEquals(response.getName(), "Dhwani Parikh");
        Assert.assertEquals(response.getJob(), "Principal QA Architect");
        Assert.assertNotNull(response.getUpdatedAt(), "UpdatedAt timestamp should not be null");
    }

    @Test(description = "Verify DELETE /api/users/{id} returns 204 No Content")
    public void testDeleteUser() {
        Response response = apiClient.deleteUser(2);
        response.then().statusCode(204);
    }

    @Test(description = "Verify GET /api/users/23 returns 404 Not Found for non-existent user")
    public void testUserNotFound() {
        Response response = apiClient.getUserById(23);
        response.then().statusCode(404);
    }
}

