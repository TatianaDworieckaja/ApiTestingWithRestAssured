package tatiana_dworieckaja.petstore.endpoints;

//created to perform CRUD operation in User module

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import tatiana_dworieckaja.petstore.payload.userPayLoad.User;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

    public static Response createUser(User payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.CREATE_USER_URL);

    }

    public static Response getUser(String username) {
        return given()
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .when()
                .get(Routes.GET_USER_URL);
    }

    public static Response updateUser(String username, User payload){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
                .when()
                .put(Routes.UPDATE_USER_URL);
    }

    public static Response deleteUser(String username){
        return given()
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .when()
                        .delete(Routes.DELETE_USER_URL);
    }

}
