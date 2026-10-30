package com.api.client;

import com.api.models.UserRequest;
import com.api.models.UserResponse;
import com.api.specs.SpecFactory;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    private static final String USERS_ENDPOINT = "/api/users";

    public Response getUsers(int page) {
        return given()
                .spec(SpecFactory.getRequestSpec())
                .queryParam("page", page)
                .when()
                .get(USERS_ENDPOINT);
    }

    public Response getUserById(int userId) {
        return given()
                .spec(SpecFactory.getRequestSpec())
                .pathParam("id", userId)
                .when()
                .get(USERS_ENDPOINT + "/{id}");
    }

    public UserResponse createUser(UserRequest userRequest) {
        return given()
                .spec(SpecFactory.getRequestSpec())
                .body(userRequest)
                .when()
                .post(USERS_ENDPOINT)
                .then()
                .spec(SpecFactory.getResponseSpec(201))
                .extract()
                .as(UserResponse.class);
    }

    public UserResponse updateUser(int userId, UserRequest userRequest) {
        return given()
                .spec(SpecFactory.getRequestSpec())
                .pathParam("id", userId)
                .body(userRequest)
                .when()
                .put(USERS_ENDPOINT + "/{id}")
                .then()
                .spec(SpecFactory.getResponseSpec(200))
                .extract()
                .as(UserResponse.class);
    }

    public Response deleteUser(int userId) {
        return given()
                .spec(SpecFactory.getRequestSpec())
                .pathParam("id", userId)
                .when()
                .delete(USERS_ENDPOINT + "/{id}");
    }
}

