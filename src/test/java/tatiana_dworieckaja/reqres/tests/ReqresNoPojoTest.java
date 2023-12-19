package tatiana_dworieckaja.reqres.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import tatiana_dworieckaja.reqres.specifications.Specifications;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ReqresNoPojoTest {
    private final static String URL = "https://reqres.in";

    @Test
    public void getAllUsersTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Response response = given()
                .when()
                .get("/api/users?page=2")
                .then().log().all()
                .body("page", equalTo(2))
                .body("data.id", notNullValue())
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.avatar", notNullValue())
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> emails = jsonPath.get("data.email");
        List<Integer> ids = jsonPath.get("data.id");
        List<String> avatars = jsonPath.get("data.avatar");

        for(int i =0; i< avatars.size(); i ++){
            Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()), "Avatar of " + i + " element is incorrect");

            Assert.assertTrue(emails.stream().allMatch(x->x.endsWith("@reqres.in")));
        }
    }

    @Test
    public void successRegistrationTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", "eve.holt@reqres.in");
        credentials.put("password", "pistol");

        given()
                .body(credentials)
                .when()
                .post("/api/register")
                .then().log().all()
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void unSuccessRegistrationTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError400());
        Map<String, String> credentials = new HashMap<>();
        credentials.put( "email", "sydney@fife");
        Response response  = given()
                .body(credentials)
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertEquals(error, "Missing password");
    }

}
