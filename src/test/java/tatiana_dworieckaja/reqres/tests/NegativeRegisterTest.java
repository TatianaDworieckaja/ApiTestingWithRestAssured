package tatiana_dworieckaja.reqres.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tatiana_dworieckaja.reqres.api.SuccessReg;
import tatiana_dworieckaja.reqres.api.UnSuccessReg;
import tatiana_dworieckaja.reqres.payload.Register;
import tatiana_dworieckaja.reqres.specifications.Specifications;

import static io.restassured.RestAssured.given;

public class NegativeRegisterTest {
    SoftAssert softAssert;
    private final static String URL = "https://reqres.in";
    @BeforeClass
    public void setup(){
        softAssert = new SoftAssert();
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError400());
    }

    @Test
    public void unSuccessRegTest(){
        Register user =  new Register("sydney@fife", "");
        UnSuccessReg unSuccessReg = given()
                .body(user)
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().as(UnSuccessReg.class);
        softAssert.assertNotNull(unSuccessReg.getError(), "The correct error message is not displayed.");
        softAssert.assertEquals(unSuccessReg.getError(), "Missing password", "Error message is incorrect");
    }
}
