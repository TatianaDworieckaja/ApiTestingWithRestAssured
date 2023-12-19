package tatiana_dworieckaja.reqres.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tatiana_dworieckaja.reqres.specifications.Specifications;

import static io.restassured.RestAssured.given;

public class DeleteUserTest {
    private static final String URL = "https://reqres.in";

    @BeforeClass
    public void setup() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(204));
    }

    @Test
    public void deleteUserTest() {
        given()
                .when()
                .delete("/api/users/2")
                .then().log().all();

    }
}
