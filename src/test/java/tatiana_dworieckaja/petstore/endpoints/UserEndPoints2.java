package tatiana_dworieckaja.petstore.endpoints;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import tatiana_dworieckaja.petstore.payload.User;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints2 {

    static ResourceBundle getUrl(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }

    public static Response createUser(User payload) {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(getUrl().getString("post_pet_url"));

    }

    public static Response getUser(String username) {
        return given()
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .when()
                .get(getUrl().getString("get_url"));
    }

    public static Response updateUser(String username, User payload){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
                .when()
                .put(getUrl().getString("update_url"));
    }

    public static Response deleteUser(String username){
        return given()
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .when()
                        .delete(getUrl().getString("delete_url"));
    }

}
