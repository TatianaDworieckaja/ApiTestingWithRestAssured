package tatiana_dworieckaja.reqres.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tatiana_dworieckaja.reqres.api.UpdateUserResponse;
import tatiana_dworieckaja.reqres.payload.UserUpdateRequest;
import tatiana_dworieckaja.reqres.specifications.Specifications;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class UpdateUserTest {

    private final static String URL = "https://reqres.in";

    @Test
    public void verifyTimeTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        UserUpdateRequest userRequest = new UserUpdateRequest("morpheus", "zion resident");
        UpdateUserResponse userResponse = given()
                .body(userRequest)
                .when()
                .put("/api/users/2")
                .then().log().all()
                .extract().as(UpdateUserResponse.class);
        String regex = "\\.[A-Za-z0-9]+";
        String actualDateTime = LocalDateTime.now().toString().replaceAll(regex, "");
        String expectedDateTime = userResponse.getUpdatedAt().replaceAll(regex, "");
        Assert.assertEquals(actualDateTime, expectedDateTime);
        System.out.println("*************" + actualDateTime);
    }

}
