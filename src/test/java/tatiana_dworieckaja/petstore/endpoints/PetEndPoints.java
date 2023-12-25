package tatiana_dworieckaja.petstore.endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import tatiana_dworieckaja.petstore.payload.petPayload.RootPet;
import tatiana_dworieckaja.petstore.specifications.Specifications;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class PetEndPoints {
    static ResourceBundle getUrl(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }

    public static Response createPet(RootPet payload) {
        return given()
                .spec(Specifications.requestSpec())
                .body(payload)
                .when()
                .post(getUrl().getString("post_pet_url"))
                .then().log().all()
                .spec(Specifications.responseSpecOK200())
                .extract().response();
    }

    public static Response getPetById(int petId) {
        return given()
                .accept(ContentType.JSON)
                .pathParam("petId", petId)
                .when()
                .get(getUrl().getString("get_pet_url"))
                .then()
                .spec(Specifications.responseSpecOK200())
                .extract().response();
    }

   //public static Response updateUser(String username, User payload){
   //    return given()
   //            .contentType(ContentType.JSON)
   //            .accept(ContentType.JSON)
   //            .pathParam("username", username)
   //            .body(payload)
   //            .when()
   //            .put(getUrl().getString("update_url"));
   //}

   //public static Response deleteUser(String username){
   //    return given()
   //            .accept(ContentType.JSON)
   //            .pathParam("username", username)
   //            .when()
   //            .delete(getUrl().getString("delete_url"));
   //}
}
